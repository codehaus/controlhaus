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
<%@ page import="com.bea.netuix.servlets.controls.content.JspContentContext"%>
<%@ page import="com.bea.portlet.PostbackURL"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netu"%>

<table width="100%">
<% if(request.getAttribute("errorMsg")!=null) {%>
    <tr>
        <td colspan="2" class="errorMsg"><%= request.getAttribute("errorMsg")%></td>
    </tr>
<% } %>
<tr>
    <td align="center">    
        <netui:form action="editAccount">
            <netui:button type="submit" value="Create New" action="createAccount" styleClass="button"/>
            <netui:button type="submit" value="Edit" action="editAccount" styleClass="button"/>
            <netui:button type="submit" value="Delete" action="deleteAccount" styleClass="button"/>
        </netui:form>        
    </td>
</tr>
<tr>
    <td>
   	<p class="pagehead" align="left"> 
	     <big><b><u><netui:label value="{pageFlow.selectedAccount.name}" /></u></b></big>    
	</p>        
    </td>
</tr>

<tr><td>
    <table class="tablebody" width="100%" align="center" cellpadding="1" cellspacing="1" border="0">
        <tr>
            <td class="dataLabel">Name</td>
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedAccount.name}" />
            </td>
            <td class="dataLabel">Rating</td>
            <td class="dataField">                
                <netu:label value="{pageFlow.selectedAccount.rating}" />
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Industry</td>        
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedAccount.industry}" />
            </td>
            <td width=85 class="dataLabel">
                Account Number
            </td>
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedAccount.accountNumber}" />            
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Site</td>         
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedAccount.site}"/>        
            </td>
            <td class="dataLabel">Type</td>         
            <td class="dataField">
                <netui:label value="{pageFlow.selectedAccount.type}" />
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Account Phone</td>         
            <td class="dataField">            
                <netui:label value="{pageFlow.selectedAccount.accountPhone}" />        
            </td>
            <td class="dataLabel">Ticker</td>        
            <td class="dataField">
                <netui:label value="{pageFlow.selectedAccount.ticker}" />
            </td>                    
        </tr>
    <!--
    <tr>
        <td class="dataField">
            Url 
        
        <td class="dataField">
            <netui:anchor href="{pageFlow.selectedAccount.url}" target="_blank"><netui:label value="{pageFlow.selectedAccount.url}"></netui:label></netui:anchor>
    </tr>
    -->
        <tr>
            <td class="dataLabel">Ownership</td>        
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedAccount.ownership}" />        
            </td>
            <td class="dataLabel">Employees</td>        
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedAccount.employees}" />
            </td>            
        </tr>   
        <tr>
            <td class="dataLabel">Billing Street         
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedAccount.billingStreet}" />
            </td>        
            <td class="dataLabel">Billing City</td>        
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedAccount.billingCity}" />
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Billing State </td>        
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedAccount.billingState}" />
            </td>
            <td class="dataLabel">Billing Postal Code </td>        
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedAccount.billingPostalCode}" />
            </td>                    
        </tr>
        <tr>
            <td class="dataLabel">Billing Country</td>
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedAccount.billingCountry}" />
            </td>
        </tr>
    
    
    </table>

</td>
</tr>
<tr>    
    <td align="center">   
    <%
        JspContentContext jspContentContext = JspContentContext.getJspContentContext(request);
        PostbackURL contactPageUrl = jspContentContext.getBaseUrl(request, response, "_pageLabel",  "contact_page");
	    PostbackURL casePageUrl = jspContentContext.getBaseUrl(request, response, "_pageLabel",  "case_page");
    %>
        <a href="<%= contactPageUrl.toString()%>">Contacts<a/>
        &nbsp;&nbsp;|&nbsp;&nbsp;  
        <a href="<%= casePageUrl.toString()%>">Cases<a/>
    </td>
</tr>

<tr>
    <td align="center">    
        <netui:form action="editAccount">
            <netui:button type="submit" value="Create New" action="createAccount" styleClass="button"/>
            <netui:button type="submit" value="Edit" action="editAccount" styleClass="button"/>
            <netui:button type="submit" value="Delete" action="deleteAccount" styleClass="button"/>
        </netui:form>        
    </td>
</tr>

</table>

