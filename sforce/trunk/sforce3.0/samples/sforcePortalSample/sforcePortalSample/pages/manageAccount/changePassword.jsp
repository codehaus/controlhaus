<%-- 
   Copyright 2004 Salesforce.com

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
<netui:html>
    <table cellpadding=0 cellspacing=0 border="0" width=100%>
        <tr>
            <td align="left">
            <table width="100%" cellpadding=0 cellspacing=0 border="0">
                <tr>
                    <td align="center"> </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>
                    Please enter and verify a new password below.
                    <br>
                    <br>
                    Remember that your new password:
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&#8226;&nbsp;Must be at least 5 characters
                    long. </td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&#8226;&nbsp;Cannot equal or contain your
                    username.</td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&#8226;&nbsp;Must be case sensitive. </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>
                   <netui:form action="setNewPassword">
                        
                </td>
                </tr>
                <tr>
                <td>
                <table border=0 cellspacing=1 cellpadding=0 id="ep">
                    <tr>
                        <td colspan=5>&nbsp;</td>
                    </tr>
                    <tr id="head_1_ep">
                        <td colspan=4 nowrap><netui:label value="{pageFlow.message}" /></td>
                    </tr>
                    <tr>
                        <td nowrap >User Name: </td>
                        <td><netui:label value="{pageFlow.userName}" /></td>
                    </tr>
                    <tr>
                        <td nowrap>Old Password: </td>
                        <td><netui:textBox password="true" maxlength="20" dataSource="{actionForm.oldPassword}"/></td>
                        
                    </tr>
                    <tr>
                        <td nowrap>New Password: </td>
                        <td><netui:textBox password="true" maxlength="20" dataSource="{actionForm.newPassword}"/></td>
                    </tr>
                    <tr>
                        <td nowrap>Verify New
                        Password: </td>
                        <td><netui:textBox password="true" maxlength="20" dataSource="{actionForm.verifyNewPassword}"/></td>
                    </tr>
                    <tr id="btn">
                        <td colspan=5 align=center> <netui:button value="Save"  styleClass="button"/> &nbsp;&nbsp;&nbsp;<netui:button value="Cancel" styleClass="button"/></td>
                    </tr>
                </table>
                </td>
            </tr>          
    </netui:form>
            </table>
            </td>
        </tr>
    </table>
</netui:html>
