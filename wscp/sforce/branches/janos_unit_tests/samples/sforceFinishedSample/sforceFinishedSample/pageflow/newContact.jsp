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

<%@taglib prefix="netui-databinding" uri="netui-tags-databinding.tld"%>
<!--Generated by WebLogic Workshop-->
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>

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
	<netui-template:section name="bodySection"> &nbsp;&nbsp;&nbsp; <br>
	<br>
    <blockquote>
	<p class="pagehead" align="Left">
	<big>New Contact</p>
	<netui:form action="createContact">
		<netui:parameter name="accountid" value='<%=request.getParameter("accountid")%>'/>
		<netui:parameter name="customername" value='<%=request.getParameter("customername")%>'/>
        <table class="tablebody" width="50%" align="left" cellpadding="0" cellspacing="0" border="0">
            <tr class="tablehead">
                <td width="20%">
                    <b>Attribute Name</b>
                </td>
                <td width="80%">
                    <b>Attribute Value</b>
                </td>
            </tr>
			<tr valign="top">
				<td>Salutation:</td>
				<td><netui:textBox dataSource="{actionForm.salutation}"/></td>
			</tr>
			<tr valign="top">
				<td>FirstName:</td>
				<td><netui:textBox dataSource="{actionForm.firstName}"/></td>
			</tr>
			<tr valign="top">
				<td>LastName:</td>
				<td><netui:textBox dataSource="{actionForm.lastName}"/></td>
			</tr>
			<tr valign="top">
				<td>Title:</td>
				<td><netui:textBox dataSource="{actionForm.title}"/></td>
			</tr>
			<tr valign="top">
				<td>Department:</td>
				<td><netui:textBox dataSource="{actionForm.department}"/></td>
			</tr>
			<tr valign="top">
				<td>BusinessPhone:</td>
				<td><netui:textBox dataSource="{actionForm.businessPhone}"/></td>
			</tr>
			<tr valign="top">
				<td>MobilePhone:</td>
				<td><netui:textBox dataSource="{actionForm.mobilePhone}"/></td>
			</tr>
			<tr valign="top">
				<td>Email:</td>
				<td><netui:textBox dataSource="{actionForm.email}"/></td>
			</tr>
			<tr valign="top">
				<td>MailingCity:</td>
				<td><netui:textBox dataSource="{actionForm.mailingCity}"/></td>
			</tr>
			<tr valign="top">
				<td>MailingState:</td>
				<td><netui:textBox dataSource="{actionForm.mailingState}"/></td>
			</tr>
			<tr valign="top">
				<td>MailingCountry:</td>
				<td><netui:textBox dataSource="{actionForm.mailingCountry}"/></td>
			</tr>
		</table>
		<br/>&nbsp;
		<netui:button value="Create" type="submit"/>&nbsp;&nbsp;&nbsp;<netui:button value="Cancel" action="GetCustomerDetail" />
	</netui:form>
    </blockquote>
	</netui-template:section>
</netui-template:template>
