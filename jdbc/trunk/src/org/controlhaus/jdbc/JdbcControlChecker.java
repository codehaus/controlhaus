/*
* Copyright 2004 BEA Systems, Inc.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
*/

package org.controlhaus.jdbc;

import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.type.ArrayType;
import com.sun.mirror.type.DeclaredType;
import com.sun.mirror.type.InterfaceType;
import com.sun.mirror.type.MirroredTypeException;
import com.sun.mirror.type.PrimitiveType;
import com.sun.mirror.type.TypeMirror;
import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.bean.ControlChecker;
import org.controlhaus.jdbc.parser.ParameterChecker;
import org.controlhaus.jdbc.parser.SqlParser;
import org.controlhaus.jdbc.parser.SqlStatement;

import java.util.Collection;

/**
 * Annotation checker for the JdbcControl.  Invoked at compile time by the controls framework.
 */
public class JdbcControlChecker implements ControlChecker {

    /**
     * Invoked by the control build-time infrastructure to process a declaration of
     * a control extension (ie, an interface annotated with @ControlExtension), or
     * a field instance of a control type.
     */
    public void check(Declaration decl, AnnotationProcessorEnvironment env) {

        if (decl instanceof TypeDeclaration) {

            //
            // Check class annotations
            //
            checkConnectionDataSource((TypeDeclaration) decl, env);
            checkConnectionDriver((TypeDeclaration) decl, env);
            checkConnectionOptions((TypeDeclaration) decl, env);

            //
            // Check method annotations
            //
            Collection<? extends MethodDeclaration> methods = ((TypeDeclaration) decl).getMethods();
            for (MethodDeclaration method : methods) {
                checkSQL(method, env);

            }
        } else if (decl instanceof FieldDeclaration) {

            //
            // NOOP
            //
        } else {

            //
            // NOOP
            //
        }
    }

    /**
     * Check the ConnectionDataSource annotation.
     *
     * @param decl
     * @param env
     */
    private void checkConnectionDataSource(TypeDeclaration decl, AnnotationProcessorEnvironment env) {
        final JdbcControl.ConnectionDataSource cds =
                decl.getAnnotation(JdbcControl.ConnectionDataSource.class);
        if (cds == null) {
            return;
        }

        //
        // NOOP
        //
    }

    /**
     * Check the ConnectionDriver annotation.
     *
     * @param decl
     * @param env
     */
    private void checkConnectionDriver(TypeDeclaration decl, AnnotationProcessorEnvironment env) {
        final JdbcControl.ConnectionDriver cd = decl.getAnnotation(JdbcControl.ConnectionDriver.class);
        if (cd == null) {
            return;
        }

        //
        // NOOP
        //
    }

    /**
     * Check the ConnectionOptions annotation.
     *
     * @param decl
     * @param env
     */
    private void checkConnectionOptions(TypeDeclaration decl, AnnotationProcessorEnvironment env) {
        final JdbcControl.ConnectionOptions co = decl.getAnnotation(JdbcControl.ConnectionOptions.class);
        if (co == null) {
            return;
        }

        //
        // NOOP
        //
    }

    /**
     * Check the SQL method annotation.  Lots to check here, stop checking as soon as an error is found.
     *
     * @param method
     * @param env
     */
    private void checkSQL(MethodDeclaration method, AnnotationProcessorEnvironment env) {

        final JdbcControl.SQL methodSQL = method.getAnnotation(JdbcControl.SQL.class);
        if (methodSQL == null) {
            return;
        }

        //
        // check for empty SQL statement member
        //
        if (methodSQL.statement() == null || methodSQL.statement().length() == 0) {
            env.getMessager().printError(method.getPosition(), "@SQL annotation on method: " + method.getSimpleName()
                                                               + " contains an empty statement member.");
            return;
        }

        //
        // Make sure maxrows is not set to some negative number other than -1
        //
        int maxRows = methodSQL.maxRows();
        if (maxRows < JdbcControl.MAXROWS_ALL) {
            env.getMessager().printError(method.getPosition(), "@SQL annotation on method: " + method.getSimpleName()
                                                               + " maxRows set to invalid value: " + maxRows);
            return;
        }

        //
        // Make sure maxArrayLength is not set to some negative number
        //
        int arrayMax = methodSQL.arrayMaxLength();
        if (arrayMax < 1) {
            env.getMessager().printError(method.getPosition(), "@SQL annotation on method: " + method.getSimpleName()
                                                               + " arrayMaxLength set to invalid value (must be greater than 0): "
                                                               + arrayMax);
            return;
        }


        //
        //
        // parse the SQL
        //
        //
        SqlParser _p = new SqlParser();
        SqlStatement _statement = null;
        try {
            _statement = _p.parse(methodSQL.statement());
        } catch (ControlException ce) {
            env.getMessager().printError(method.getPosition(), "Error parsing SQL statment on method: " + method.getSimpleName() + ": " + ce.toString());
            return;
        }

        //
        // Check that the any statement element params (delimited by '{' and '}' can be
        // matched to method parameter names. NOTE: This check is only valid on non-compiled files,
        // once compiled to a class file method parameter names are replaced with 'arg0', 'arg1', etc.
        // and cannot be used for this check.
        //
        try {
            ParameterChecker.checkReflectionParameters(_statement, method);
        } catch (Exception e) {
            env.getMessager().printError(method.getPosition(), e.getMessage());
            return;
        }

        //
        // check for case of generatedKeyColumns being set, when getGeneratedKeys is not set to true
        //
        final boolean getGeneratedKeys = methodSQL.getGeneratedKeys();
        final String[] generatedKeyColumnNames = methodSQL.generatedKeyColumnNames();
        final int[] generatedKeyIndexes = methodSQL.generatedKeyColumnIndexes();
        if (getGeneratedKeys == false && (generatedKeyColumnNames.length != 0 || generatedKeyIndexes.length != 0)) {
            env.getMessager().printError(method.getPosition(), "@SQL annotation on method: " + method.getSimpleName()
                                                               + " getGeneratedKeys must be set to true in order to specify generatedKeyColumnNames or generatedKeyColumnIndexes.");
            return;
        }

        //
        // check that both generatedKeyColumnNames and generatedKeyColumnIndexes are not set
        if (generatedKeyColumnNames.length > 0 && generatedKeyIndexes.length > 0) {
            env.getMessager().printError(method.getPosition(), "@SQL annotation on method: " + method.getSimpleName()
                                                               + " only one of generatedKeyColumnNames or generatedKeyColumnIndexes may be set in the method annotation.");

            return;
        }

        //
        // batch update methods must return int[]
        //
        final boolean batchUpdate = methodSQL.batchUpdate();
        final TypeMirror returnType = method.getReturnType();
        if (batchUpdate) {
            if (returnType instanceof ArrayType == true) {
                final TypeMirror aType = ((ArrayType) returnType).getComponentType();
                if (aType instanceof PrimitiveType == false || ((PrimitiveType) aType).getKind() != PrimitiveType.Kind.INT) {
                    env.getMessager().printError(method.getPosition(), "@SQL annotation on method: " + method.getSimpleName()
                                                                       + " if batchUpdate is set to true, method must return an array of integers.");
                    return;
                }
            } else {
                env.getMessager().printError(method.getPosition(), "@SQL annotation on method: " + method.getSimpleName()
                                                                   + " if batchUpdate is set to true, method must return an array of integers.");
                return;
            }

        }

        //
        // iterator type check match
        //
        if (returnType instanceof InterfaceType) {
            String iName = ((InterfaceType) returnType).getDeclaration().getQualifiedName();
            if ("java.util.Iterator".equals(iName)) {
                String iteratorClassName = null;
                try {
                    // this should always except
                    methodSQL.iteratorElementType();
                } catch (MirroredTypeException mte) {
                    iteratorClassName = mte.getQualifiedName();
                }

                if ("org.controlhaus.jdbc.JdbcControl.UndefinedIteratorType".equals(iteratorClassName)) {
                    env.getMessager().printError(method.getPosition(), "@SQL annotation on method: " + method.getSimpleName()
                                                                       + " iteratorElementType must be specified if method returns an Iterator.");
                    return;

                }
            }
        }

        //
        // scrollable result set check
        //
        final JdbcControl.ScrollType scrollable = methodSQL.scrollableResultSet();
        switch (scrollable) {
            case SCROLL_INSENSITIVE:
            case SCROLL_SENSITIVE:
            case SCROLL_INSENSITIVE_UPDATABLE:
            case SCROLL_SENSITIVE_UPDATABLE:
            case FORWARD_ONLY_UPDATABLE:
                String typeName = null;
                if (returnType instanceof DeclaredType) {
                    typeName = ((DeclaredType) returnType).getDeclaration().getQualifiedName();
                }

                if (typeName == null || "java.sql.ResultSet".equals(typeName) == false) {
                    env.getMessager().printError(method.getPosition(), "@SQL annotation on method: " + method.getSimpleName()
                                                                       + " element scrollableResultSet specified but method does not return a ResultSet.");
                    break;
                }
            case FORWARD_ONLY:
            default:
                break;
        }
    } // checkSQL
}
