
package org.controlhaus.jdbc.parser;

import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.type.DeclaredType;
import com.sun.mirror.type.TypeMirror;
import com.sun.mirror.util.DeclarationFilter;
import org.apache.beehive.controls.api.ControlException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Does compile-time checking of reflection parameteres in the SQL annotations's statement
 * element vs. method parameters. Invoked by the JdbcControlChecker.
 */
public class ParameterChecker {

    /**
     * Verify that all reflection parameters in the statement element can be mapped to method parameters.
     *
     * @param statement The parsed statement element.
     * @param methodDecl The method declaration which was annotated.
     */
    public static void checkReflectionParameters(SqlFragmentContainer statement, MethodDeclaration methodDecl) {

        ArrayList<ParameterDeclaration> params =
                new ArrayList<ParameterDeclaration>(methodDecl.getParameters());
        HashMap<String, ParameterDeclaration> paramMap = new HashMap<String, ParameterDeclaration>();

        // don't run these checks if this is a compiled class file (method names replaced with arg0, arg1, etc)
        if (params.size() > 0 && params.get(0).getSimpleName().equals("arg0")) {
            return;
        }

        for (int i = 0; i < params.size(); i++) {
            paramMap.put(params.get(i).getSimpleName(), params.get(i));
        }

        doCheck(statement, paramMap, methodDecl);
    }

    /**
     * Walk the tree of children of the statement, process all children of type ReflectionFragment.
     *
     * @param statement The parsed statement element.
     * @param paramMap The method parameters, keyed by name.
     * @param method The method declaration which was annotated.
     */
    private static void doCheck(SqlFragmentContainer statement, HashMap<String, ParameterDeclaration> paramMap,
                         final MethodDeclaration method)
    {

        SqlFragment[] fragments = statement.getChildren();
        for (SqlFragment fragment : fragments) {

            // if the fragment is a container check all of its children.
            if (fragment instanceof SqlFragmentContainer) {
                doCheck((SqlFragmentContainer) fragment, paramMap, method);

                // reflection fragment - make sure it can be mapped using the method's param values.
            } else if (fragment instanceof ReflectionFragment) {
                checkReflectionFragment((ReflectionFragment) fragment, paramMap, method);
            }
        }
    }

    /**
     * Check the fragment.  Must be able to resolve references like 'foo.bar' -> 'foo' where 'foo' is a method param
     * of a type which contains a public getter or field named 'getBar()' or 'bar' respectively.
     *
     * @param fragment The reflection fragment to check.
     * @param paramMap The method parameters, keyed by name.
     * @param method The method declaration which was annotated.
     */
    private static void checkReflectionFragment(ReflectionFragment fragment,
                                                HashMap<String, ParameterDeclaration> paramMap, MethodDeclaration method)
    {

        final String[] paramNameQualifiers = ((ReflectionFragment) fragment).getParameterNameQualifiers();
        final String parameterName = ((ReflectionFragment) fragment).getParameterName();

        if (paramMap.containsKey(paramNameQualifiers[0]) == false) {
            throw new ControlException(buildMessage(parameterName, method.getSimpleName()));
        }

        ParameterDeclaration tpd = paramMap.get(paramNameQualifiers[0]);
        TypeMirror type = tpd.getType();

        MethodDeclaration getterMethod = null;
        FieldDeclaration field = null;

        for (int i = 1; i < paramNameQualifiers.length; i++) {

            getterMethod = null;
            field = null;

            // loop through superclasses until we find a match or run out of superclasses
            while (type != null) {

                if (type instanceof DeclaredType == false) {
                    throw new ControlException(buildMessage(parameterName, method.getSimpleName()));
                }

                TypeDeclaration td = ((DeclaredType) type).getDeclaration();
                //
                // abort if Map!!! No further checking can be done.
                //
                if (td.getQualifiedName().equals("java.util.Map")) {
                    return;
                }

                Collection<? extends MethodDeclaration> methods =
                        DeclarationFilter.FILTER_PUBLIC.filter(td.getMethods());
                for (MethodDeclaration m : methods) {
                    String upperFirst = paramNameQualifiers[i].substring(0,1).toUpperCase();
                    if (paramNameQualifiers[i].length() > 1) {
                        upperFirst = upperFirst + paramNameQualifiers[i].substring(1);
                    }
                    if (m.getSimpleName().equals("get" + upperFirst)
                        || m.getSimpleName().equals("is" + upperFirst)) {
                        getterMethod = m;
                    }
                }

                if (getterMethod == null) {
                    Collection<FieldDeclaration> fields =
                            DeclarationFilter.FILTER_PUBLIC.filter(td.getFields());
                    for (FieldDeclaration fd : fields) {
                        if (fd.getSimpleName().equals(paramNameQualifiers[i])) {
                            field = fd;
                        }
                    }
                }

                // try the super-class
                if (getterMethod == null && field == null) {
                    if (td instanceof ClassDeclaration) {
                        type = ((ClassDeclaration) td).getSuperclass();
                        continue;
                    }
                }

                break;
            } // while

            // found a match, get its type and continue within the for loop
            if (getterMethod != null) {
                type = getterMethod.getReturnType();
            } else if (field != null) {
                type = field.getType();
            } else {
                throw new ControlException(buildMessage(parameterName, method.getSimpleName()));
            }
        }
    }

    /**
     * build the error message for this module.
     * 
     * @param parameterName
     * @param methodName
     * @return
     */
    private static String buildMessage(String parameterName, String methodName) {
        StringBuilder message = new StringBuilder();
        message.append("Unable to map the parameter in SQL statement ");
        message.append(parameterName);
        message.append(" to a parameter of the method ");
        message.append(methodName);
        message.append(". Mapping is accomplished by matching a method parameter name to ");
        message.append("a value delimited by '{' and '}' in the statement element.");
        return message.toString();
    }
}
