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
<%@taglib prefix="netui-databinding" uri="netui-tags-databinding.tld"%>
<style>
<!--table
.style0
   {mso-number-format:General;
   text-align:general;
   vertical-align:bottom;
   white-space:nowrap;
   mso-rotate:0;
   mso-background-source:auto;
   mso-pattern:auto;
   color:windowtext;
   font-size:10.0pt;
   font-weight:400;
   font-style:normal;
   text-decoration:none;
   font-family:Arial;
   mso-generic-font-family:auto;
   mso-font-charset:0;
   border:none;
   mso-protection:locked visible;
   mso-style-name:Normal;
   mso-style-id:0;}
.xl25
   {mso-style-parent:style0;
   border-top:none;
   border-right:none;
   border-bottom:.5pt solid windowtext;
   border-left:none;}
-->
</style>

<netui-template:template templatePage="./../resources/jsp/template.jsp">
<netui-template:section name="bodySection">
            <netui:anchor action="Logout" style="font-size:10pt;"></netui:anchor>

    <blockquote>
        <netui-databinding:repeater dataSource="{pageFlow.accounts}">
            <netui-databinding:repeaterHeader>
                <table cellpadding="0" class="tablebody" cellspacing="0" border="0">
                    <tr>
                        <td colspan="6">
                        <p class="pagehead" align="left">
                        <big>Accounts Page</big>
        &nbsp;&nbsp;&nbsp;<netui:anchor style="font-size:10pt; font-weight:noraml;font-family:verdana;" action="GetCustomers">
            (Refresh)
            <netui:parameter name="refresh" value="refresh" />
        </netui:anchor>
                        </p><p>
                        </td>
                    </tr>
                    <tr class="tablehead">
                        <td><b>Name</b></td>
                        <td>&nbsp;</td>
                        <td><b>Rating</b></td>
                        <td><b>Industry</b></td>
                        <td><b>City</b></td>
                        <td><b>State</b></td>
                    </tr>
            </netui-databinding:repeaterHeader>

            <netui-databinding:repeaterItem>
                <tr>
                    <td class="xl25">
                        <netui:anchor action="GetCustomerDetail">
                            <netui:parameter name="accountid" value="{container.item.id}"/>
                            <netui:parameter name="customername" value="{container.item.name}"/>
                            <netui:label value="{container.item.name}"/>
                        </netui:anchor>
                    </td>
                    <td class="xl25">
                        <netui:anchor action="GetOpportunities">
                            <netui:parameter name="accountid" value="{container.item.id}"/>
                            <netui:parameter name="customername" value="{container.item.name}"/>
                            Opportunities
                        </netui:anchor>            
                    </td>
                    <td class="xl25">
                        &nbsp;<netui:label value="{container.item.rating}"></netui:label>
                    </td>
                    <td class="xl25">
                        &nbsp;<netui:label value="{container.item.industry}"></netui:label>
                    </td>
                    <td class="xl25">
                        &nbsp;<netui:label value="{container.item.billingCity}"></netui:label>
                    </td>
                    <td class="xl25">
                        &nbsp;<netui:label value="{container.item.billingState}"></netui:label>
                    </td>
                </tr>
                </netui-databinding:repeaterItem>
            <netui-databinding:repeaterFooter>
                </table>
            </netui-databinding:repeaterFooter>
        </netui-databinding:repeater>

</blockquote>


    </netui-template:section>
</netui-template:template>

    