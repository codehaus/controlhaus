<%-- 
   Copyright 2004 BEA Systems, Inc.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="SpellCheckerSample.SpellCheckerSampleController"%>
<%@ page import="SpellCheckerSample.SpellingResults"%>
<%@ page import="org.controlhaus.misc.spellcheck.SpellingError"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<netui-template:template templatePage="/resources/jsp/template.jsp">
    <netui-template:setAttribute value="Index" name="title"/>
    <netui-template:section name="bodySection">       
                
        <h2>Spell Checker Results</h2>        
        
        <%
            SpellingError[] errors = (SpellingError[]) request.getAttribute("results");            
            SpellingResults results = new SpellingResults(errors);
            SpellCheckerSampleController.CheckSpellingForm form = (SpellCheckerSampleController.CheckSpellingForm) request.getAttribute("formBean");
        %>        
        
        You provided the words mentioned below.
        <br/><br/>
        
        <table class="tablebody">                
            <tr class="tablebody">
                <td class="tablebody"><i>First Word :</i></td>
                <td class="tablebody">                        
                    <%=form.getWord1()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td class="tablebody"><i>Second Word :</i></td>
                <td class="tablebody">                        
                    <%=form.getWord2()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td class="tablebody"><i>Third Word :</i></td>
                <td class="tablebody">                        
                    <%=form.getWord3()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td class="tablebody"><i>Fourth Word :</i></td>
                <td class="tablebody">                        
                    <%=form.getWord4()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td class="tablebody"><i>Fifth Word :</i></td>
                <td class="tablebody">                        
                    <%=form.getWord5()%>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
        </table>
        
        <b><%=results.getMessage()%></b>
        <br/>
        <br/>       
        
        
        <% if(results.hasErrors()){%>
                
        <table class="tablebody">                
                <tr class="tablebody" align="center">
                    <td bgcolor="#cccccc" align="center">&nbsp;&nbsp;&nbsp;&nbsp; Index Postion in word(s) provided &nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td bgcolor="#cccccc" align="center">&nbsp;&nbsp;&nbsp;&nbsp; Orignal Word &nbsp;&nbsp;&nbsp;&nbsp;</td>                    
                    <td bgcolor="#cccccc" align="center">&nbsp;&nbsp;&nbsp;&nbsp; Suggestions &nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>                
                
                <%
                    for (int x = 0 ; x < errors.length ; x++){
                        SpellingError error = errors[x];
                %>                
                
                <tr class="tablebody" align="center">
                    <td align="center"><%=error.getErrorIndex()%></td>
                    <td align="center"><%=error.getOrignalWord()%></td>
                    <td align="center"><%=error.suggestionsAsString()%></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                
                <%
                }
                %>
                                             
            </table>            
        <%}%>
        <br/>        
    </netui-template:section>
</netui-template:template>