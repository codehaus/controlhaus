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
   border:1;
   mso-protection:locked visible;
   mso-style-name:Normal;
   mso-style-id:0;}
.xl25
   {mso-style-parent:style0;
   border-top:none;
   border-right:none;
   border-bottom:.5pt solid windowtext;
   border-left:none;}
   }
-->
</style>

<netui-template:template templatePage="./../resources/jsp/template.jsp">
    <netui-template:section name="bodySection"> &nbsp;&nbsp;&nbsp;

    <blockquote>
            <netui:anchor action="Logout" style="font-size:10pt;"></netui:anchor>
<table width="100%">
<TR>
<TD>
    <p class="pagehead" align="left"> 
    <big><b><u><netui:label value="{pageFlow.selectedAccount.name}" /></u></b></big><br><br>
    </p>
    <p>
    <netui:anchor action="GetOpportunities">
        <netui:parameter name="accountid" value='<%=request.getParameter("accountid")%>'/>
        <netui:parameter name="customername" value='<%=request.getParameter("customername")%>'/>
        Opportunities
    </netui:anchor> &nbsp;&nbsp;|&nbsp;&nbsp;  
    <netui:anchor action="GetCustomers">Customers Page</netui:anchor> &nbsp;&nbsp;|&nbsp;&nbsp;
    <netui:anchor action="GetCustomerDetail">
        Refresh
        <netui:parameter name="refresh" value="refresh" />
        <netui:parameter name="accountid" value="{pageFlow.selectedAccount.id}" />
    </netui:anchor>
    </p>
        
    <netui-databinding:repeater dataSource="{pageFlow.selectedAccount}">
    <netui-databinding:repeaterHeader>
    <table class="tablebody" width="90%" align="left" cellpadding="0" cellspacing="0" border="0">
        <tr class="tablehead">
            <td width="20%">
                <b>Attribute Name</b>
            </td>
            <td width="80%">
                <b>Attribute Value</b>
            </td>
        </tr>
    </netui-databinding:repeaterHeader>
    <netui-databinding:repeaterItem>
            <tr>
        <td class="xl25">
            Name
        </td>
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.name}"></netui:label>
        </td>
    </tr>
            <tr>
        <td class="xl25">
            Rating
        </td>
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.rating}"></netui:label>
        </td>
    </tr>
            <tr>
        <td class="xl25">
            Industry 
        
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.industry}"></netui:label>
        
    </tr>
            <tr>
        <td width=85 class="xl25">
            <nobr>Account Number</nobr>
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.accountNumber}"></netui:label>
        
    </tr>
            <tr>
        <td class="xl25">
            Site 
        
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.site}"></netui:label>
        
    </tr>
            <tr>
        <td class="xl25">
            Type 
        
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.type}"></netui:label>
        
    </tr>
            <tr>
        <td class="xl25">
            Account Phone 
        
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.accountPhone}"></netui:label>
        
    </tr>
    <!--
    <tr>
        <td class="xl25">
            Url 
        
        <td class="xl25">
            &nbsp;
            <netui:anchor href="{container.item.url}" target="_blank"><netui:label value="{container.item.url}"></netui:label></netui:anchor>
    </tr>
    -->
            <tr>
        <td class="xl25">
            Ticker 
        
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.ticker}"></netui:label>
        
    </tr>
            <tr>
        <td class="xl25">
            Ownership 
        
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.ownership}"></netui:label>
        
    </tr>
            <tr>
        <td class="xl25">
            Employees 
        
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.employees}"></netui:label>
        
    </tr>
            <tr>
        <td class="xl25">
            Billing Street 
        
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.billingStreet}"></netui:label>
        
    </tr>
            <tr>
        <td class="xl25">
            Billing City 
        
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.billingCity}"></netui:label>
        
    </tr>
            <tr>
        <td class="xl25">
            Billing State 
        
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.billingState}"></netui:label>
        
    </tr>
            <tr>
        <td class="xl25">
            Billing Postal Code 
        
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.billingPostalCode}"></netui:label>
        
    </tr>
            <tr>
        <td class="xl25">
            Billing Country
        </td>
        <td class="xl25">
            &nbsp;
            <netui:label value="{container.item.billingCountry}"></netui:label>
        
    </tr>
        </netui-databinding:repeaterItem>
    <netui-databinding:repeaterFooter>
        </table>
    </netui-databinding:repeaterFooter>
</netui-databinding:repeater>
</td>
</tr>
<TR>
<TD>
        <BR><p class="pagehead" align="left"><big><b>Contacts</b></big> </p>
        <netui:anchor action="newContact">[Add New Contact]
			<netui:parameter name="accountid" value="{pageFlow.selectedAccount.id}"/>
			<netui:parameter name="customername" value="{pageFlow.selectedAccount.name}"/>
        </netui:anchor>

		<netui:form action="prepareEmail">
			<netui:parameter name="accountid" value="{pageFlow.selectedAccount.id}"/>
			<netui:parameter name="customername" value="{pageFlow.selectedAccount.name}"/>


        <netui-databinding:repeater dataSource="{pageFlow.selectedAccount.contacts}">
            <netui-databinding:repeaterHeader>
                <table align="left" width="90%" cellpadding="0" class="tablebody" cellspacing="0" border="0">
                <tr class="tablehead">
                    <td><b>Name</b></td>
                    <td><b>Title</b></td>
                    <td><b>Department</b></td>
                    <td><b>Phone</b></td>
                    <td><b>Email Address</b></td>
                    <td><b>Location</b></td>
                </tr>
            </netui-databinding:repeaterHeader>
            <netui-databinding:repeaterItem>
                <tr>
                    <td class="xl25">
                        <netui:label value="{container.item.salutation}"></netui:label>&nbsp;
                        <netui:label value="{container.item.firstName}"></netui:label>&nbsp;
                        <netui:label value="{container.item.lastName}"></netui:label>
                    </td>
                    <td class="xl25"><netui:label value="{container.item.title}"></netui:label></td>
                    <td class="xl25"><netui:label value="{container.item.department}"></netui:label></td>
                    <td class="xl25"><nobr>(B) <netui:label value="{container.item.businessPhone}"></netui:label></nobr><BR>
                    <nobr>(M) <netui:label value="{container.item.mobilePhone}"></netui:label></nobr></td>

					<td class="xl25">
                        <netui:anchor action="prepareEmail">
                            <netui:parameter name="containerIndex" value="{container.index}" />
                            <netui:parameter name="email" value="{container.item.email}" />
                            <netui:label value="{container.item.email}"></netui:label>
                        </netui:anchor>
					</td>


                    <td class="xl25">
                        <netui:label value="{container.item.mailingStreet}"></netui:label><BR>
                        <netui:label value="{container.item.mailingCity}"></netui:label>,&nbsp;
                        <netui:label value="{container.item.mailingState}"></netui:label>
                    </td>
                </tr>
            </netui-databinding:repeaterItem>
            <netui-databinding:repeaterFooter>
                                </table>
            </netui-databinding:repeaterFooter>
        </netui-databinding:repeater>
		</netui:form>
<BR><BR><BR><BR>
<HR>
</td>
</tr>
</table>
    </blockquote>

    </netui-template:section>
</netui-template:template>

