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

<%@taglib prefix="netui-template" uri="netui-tags-template.tld"%>
<%@taglib prefix="netui" uri="netui-tags-html.tld"%>
<netui-template:template templatePage="./../resources/jsp/template.jsp">
    <netui-template:section name="bodySection">
    <blockquote>
        <p class="pagehead">
            <big style="font-size:18pt;">&nbsp;Welcome to BEA Weblogic Workshop demo with salesforce.com </big>
        </p>

        <table width="100%" cellpadding="0" class="tablebody" cellspacing="0">
            <tr>
                <td valign="top">
                    <table width="100%" class="tablebody">
                        <tr class="tablehead">
                            <td><big><b>Please login</b></big></td>
                        </tr>
                        <tr>
                            <td height="20">
                        </td>
                    </tr>
                        <tr>
                            <td>
                                <netui:form action="Login" focus=""><table width="100%">
                                    <tr>
                                        <th align="left" valign="top" width="100">Username:</th>
                                        <td align="left">
                                            <netui:textBox dataSource="{actionForm.username}" size="32" maxlength="30" defaultValue="peter.hussey@bea.com"></netui:textBox>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th align="left" valign="top">Password:</th>
                                        <td align="left">
                                            <netui:textBox dataSource="{actionForm.password}" size="32" maxlength="18" defaultValue="password" password="true"></netui:textBox>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right"></td>
                                        <td align="left">
                                            <netui:button value="Login"></netui:button>
                                        </td>
                                    </tr>
                                </table></netui:form>
                            </td>
                        </tr>
                    </table>
                </td>
             </TR>
         </TABLE>
</blockquote>
    </netui-template:section>
</netui-template:template>