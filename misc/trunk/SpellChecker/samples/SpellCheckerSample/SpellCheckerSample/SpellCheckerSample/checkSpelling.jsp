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
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<netui-template:template templatePage="/resources/jsp/template.jsp">
    <netui-template:setAttribute value="checkSpelling" name="title"/>
    <netui-template:section name="bodySection">
        
        <h2>Spell Checker</h2>        
        
        <b>Please enter words below, for which you would like to check spellings.</b>
        <br/>
        
        <netui:form action="checkSpelling">           
            <table class="tablebody">                
                <tr class="tablebody">
                    <td><i>First Word :</i></td>
                    <td class="prompt">                        
                        <netui:textBox dataSource="{actionForm.word1}" maxlength="15" size="15"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr class="tablebody">
                    <td><i>Second Word :</i></td>
                    <td class="prompt">                        
                        <netui:textBox dataSource="{actionForm.word2}" maxlength="15" size="15"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr class="tablebody">
                    <td><i>Third Word :</i></td>
                    <td class="prompt">                        
                        <netui:textBox dataSource="{actionForm.word3}" maxlength="15" size="15"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr class="tablebody">
                    <td><i>Fourth Word :</i></td>
                    <td class="prompt">                        
                        <netui:textBox dataSource="{actionForm.word4}" maxlength="15" size="15"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr class="tablebody">
                    <td><i>Fifth Word :</i></td>
                    <td class="prompt">                        
                        <netui:textBox dataSource="{actionForm.word5}" maxlength="15" size="15"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
            <br/>&nbsp;
            <netui:button value="Spell Check" type="submit"/>
        </netui:form>
    </netui-template:section>
</netui-template:template>
