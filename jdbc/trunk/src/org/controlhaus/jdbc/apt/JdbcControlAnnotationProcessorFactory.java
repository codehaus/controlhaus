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

package org.controlhaus.jdbc.apt;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.type.ArrayType;
import com.sun.mirror.type.InterfaceType;
import com.sun.mirror.type.MirroredTypeException;
import com.sun.mirror.type.PrimitiveType;
import com.sun.mirror.type.TypeMirror;
import com.sun.mirror.type.ClassType;
import com.sun.mirror.type.DeclaredType;
import com.sun.mirror.util.DeclarationVisitors;
import com.sun.mirror.util.SimpleDeclarationVisitor;
import org.apache.beehive.controls.api.ControlException;
import org.controlhaus.jdbc.JdbcControl;
import org.controlhaus.jdbc.parser.SqlParser;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * The JdbcControlAnnotationProcessorFactory creates a JdbcControlAnnotationProcessor. This apt is the annotation
 * processor which validates SQL annotations at compile time for the JdbcControl.  It checks basic
 * constraints for the annotation member values and also parses the SQL contained within the
 * statement member.
 */
public class JdbcControlAnnotationProcessorFactory implements AnnotationProcessorFactory {

    // define the collection of supported annotations
    private static final Collection<String> _supportedAnnotations =
            Collections.unmodifiableCollection(Arrays.asList("org.controlhaus.jdbc.JdbcControl.SQL",
                                                             "org.controlhaus.jdbc.JdbcControl.ConnectionDriver",
                                                             "org.controlhaus.jdbc.JdbcControl.ConnectionDataSource",
                                                             "org.controlhaus.jdbc.JdbcControl.ConnectionOptions"));

    // define any supported apt options
    private static final Collection<String> _supportedOptions = Collections.emptySet();

    /**
     * Returns the options recognized by this factory or by any of the
     * processors it may create.
     * Only {@linkplain AnnotationProcessorEnvironment#getOptions()
     * processor-specific} options are included, each of which begins
     * with <tt>"-A"</tt>.  For example, if this factory recognizes
     * options such as <tt>-Adebug -Aloglevel=3</tt>, it will
     * return the strings <tt>"-Adebug"</tt> and <tt>"-Aloglevel"</tt>.
     * <p/>
     * <p> A tool might use this information to determine if any
     * options provided by a user are unrecognized by any processor,
     * in which case it may wish to report an error.
     *
     * @return the options recognized by this factory or by any of the
     *         processors it may create, or an empty collection if none
     */
    public Collection<String> supportedOptions() {
        return _supportedOptions;
    }

    /**
     * Returns the names of the annotation types supported by this factory.
     * An element of the result may be the canonical (fully qualified) name
     * of a supported annotation type.  Alternately it may be of the form
     * <tt>"<i>name</i>.*"</tt>
     * representing the set of all annotation types
     * with canonical names beginning with <tt>"<i>name</i>."</tt>
     * Finally, <tt>"*"</tt> by itself represents the set of all
     * annotation types.
     *
     * @return the names of the annotation types supported by this factory
     */
    public Collection<String> supportedAnnotationTypes() {
        return _supportedAnnotations;
    }

    /**
     * Returns an JdbcControlAnnotationProcessor
     *
     * @param atds type declarations of the annotation types to be processed
     * @param env  environment to use during processing
     * @return an JdbcControlAnnotationProcessor
     */
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds, AnnotationProcessorEnvironment env) {
        return new JdbcControlAnnotationProcessor(env);
    }

    /**
     * Annotation processor for SQL annotations
     */
    private static class JdbcControlAnnotationProcessor implements AnnotationProcessor {

        final AnnotationProcessorEnvironment _env;

        JdbcControlAnnotationProcessor(AnnotationProcessorEnvironment env) {
            _env = env;
        }

        /**
         * Process all program elements supported by this annotation processor.
         */
        public void process() {
            for (TypeDeclaration typeDecl : _env.getSpecifiedTypeDeclarations()) {
                typeDecl.accept(DeclarationVisitors.getDeclarationScanner(new SqlVisitor(_env), DeclarationVisitors.NO_OP));
            }
        }


        /**
         * Visitor inner class implementation
         */
        private static class SqlVisitor extends SimpleDeclarationVisitor {
            final AnnotationProcessorEnvironment _env;

            /**
             * Constructor
             *
             * @param env an apt env instance
             */
            public SqlVisitor(AnnotationProcessorEnvironment env) {
                _env = env;
            }

            /**
             * Visitor method for class level annotation declarations
             *
             * @param d ClassDeclaration which annotation occured.
             */
            public void visitClassDeclaration(ClassDeclaration d) {
                //      System.out.println(d.getQualifiedName());
            }

            /**
             * Visitor method for method level annotation declarations.
             *
             * @param m Method declaration which annotation occured.::
             */
            public void visitMethodDeclaration(MethodDeclaration m) {

                final JdbcControl.SQL methodSQL = m.getAnnotation(JdbcControl.SQL.class);
                if (methodSQL == null) {
                    return;
                }

                //
                // check for empty SQL statement member
                //
                if (methodSQL.statement() == null || methodSQL.statement().length() == 0) {
                    _env.getMessager().printError(m.getPosition(), "@SQL annotation on method: " + m.getSimpleName()
                                                                   + " contains an empty statement member.");
                    return;
                }

                //
                //
                // parse the SQL
                //
                //
                SqlParser _p = new SqlParser();
                try {
                    _p.parse(methodSQL.statement());
                } catch (ControlException ce) {
                    _env.getMessager().printError(m.getPosition(), "Error parsing SQL statment on method: " + m.getSimpleName() + ": " + ce.toString());
                    return;
                }

                //
                // check for case of generatedKeyColumns being set, when getGeneratedKeys is not set to true
                //
                final boolean getGeneratedKeys = methodSQL.getGeneratedKeys();
                final String[] generatedKeyColumnNames = methodSQL.generatedKeyColumnNames();
                final int[] generatedKeyIndexes = methodSQL.generatedKeyColumnIndexes();
                if (getGeneratedKeys == false && (generatedKeyColumnNames.length != 0 || generatedKeyIndexes.length != 0)) {
                    _env.getMessager().printError(m.getPosition(), "@SQL annotation on method: " + m.getSimpleName()
                                                                   + " getGeneratedKeys must be set to true in order to specify generatedKeyColumnNames or generatedKeyColumnIndexes.");
                    return;
                }

                //
                // check that both generatedKeyColumnNames and generatedKeyColumnIndexes are not set
                if (generatedKeyColumnNames.length > 0 && generatedKeyIndexes.length > 0) {
                    _env.getMessager().printError(m.getPosition(), "@SQL annotation on method: " + m.getSimpleName()
                                                                   + " only one of generatedKeyColumnNames or generatedKeyColumnIndexes may be set in the method annotation.");

                    return;
                }

                //
                // batch update methods must return int[]
                //
                final boolean batchUpdate = methodSQL.batchUpdate();
                final TypeMirror returnType = m.getReturnType();
                if (batchUpdate) {
                    if (returnType instanceof ArrayType == true) {
                        final TypeMirror aType = ((ArrayType) returnType).getComponentType();
                        if (aType instanceof PrimitiveType == false || ((PrimitiveType) aType).getKind() != PrimitiveType.Kind.INT) {
                            _env.getMessager().printError(m.getPosition(), "@SQL annotation on method: " + m.getSimpleName()
                                                                           + " if batchUpdate is set to true, method must return an array of integers.");
                            return;
                        }
                    } else {
                        _env.getMessager().printError(m.getPosition(), "@SQL annotation on method: " + m.getSimpleName()
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
                            _env.getMessager().printError(m.getPosition(), "@SQL annotation on method: " + m.getSimpleName()
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
                            typeName = ((DeclaredType)returnType).getDeclaration().getQualifiedName();
                        }
                        
                        if (typeName == null || "java.sql.ResultSet".equals(typeName) == false) {
                            _env.getMessager().printError(m.getPosition(), "@SQL annotation on method: " + m.getSimpleName()
                                                                           + " element scrollableResultSet specified but method does not return a ResultSet.");
                            break;
                        }
                    case FORWARD_ONLY:
                    default:
                        break;
                }
            } // visitMethodDeclaration
        } // SqlVisitor

    } // JdbcControlAnnotationProcessor
} // JdbcControlAnnotationProcessorFactory
