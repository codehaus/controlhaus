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
    <netui-template:setAttribute value="evaluate" name="title"/>
    <netui-template:section name="bodySection">
        
        <h2>Velocity Templating</h2>        
        
        <b>Please enter below, the velocity template to be evaluated.</b>
        <br/>
        <br/>
        In this sample, please provide variable type of reference only (as displayed below). This sample does not support Property or Method references, however control supports all of them.
        <br/>
        <br/>
        
        <br/>
        <netui:form action="evaluate">
            <table class="tablebody">                
                <tr>
                    <td valign="top">
                        <b><i>Template : </i></b>
                    </td>
                    <td>
                        <netui:textArea dataSource="{actionForm.template}" cols="50" rows="4" />
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        &nbsp;
                    </td>
                    <td>
                        &nbsp;
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        &nbsp;
                    </td>
                    <td>
                        &nbsp;
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        &nbsp;
                    </td>
                    <td>
                        &nbsp;
                    </td>
                </tr>                
                <tr>
                    <td valign="top">
                        <b><i>Context : </i></b>
                    </td>
                    <td>
                        Please provide key-value pair(s) below, based on references (or variables) in your template.
                    </td>
                </tr>                
                <tr>
                    <td valign="top">
                        &nbsp;
                    </td>
                    <td>
                        &nbsp;
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        Key 1 : <netui:textBox dataSource="{actionForm.key1}" />
                    </td>
                    <td>
                        Value 1 : <netui:textBox dataSource="{actionForm.value1}" />
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        Key 2 : <netui:textBox dataSource="{actionForm.key2}" />
                    </td>
                    <td>
                        Value 2 : <netui:textBox dataSource="{actionForm.value2}" />
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        Key 3 : <netui:textBox dataSource="{actionForm.key3}" />
                    </td>
                    <td>
                        Value 3 : <netui:textBox dataSource="{actionForm.value3}" />
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        Key 4 : <netui:textBox dataSource="{actionForm.key4}" />
                    </td>
                    <td>
                        Value 4 : <netui:textBox dataSource="{actionForm.value4}" />
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        Key 5 : <netui:textBox dataSource="{actionForm.key5}" />
                    </td>
                    <td>
                        Value 5 : <netui:textBox dataSource="{actionForm.value5}" />
                    </td>
                </tr>
                
            </table>
            <br/>&nbsp;
            <netui:button value="Evaulate Template" type="submit"/>
        </netui:form>
    </netui-template:section>
</netui-template:template>
