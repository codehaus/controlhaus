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

<netui:form action="saveAccount">


<table width="100%">

<% if(request.getAttribute("errorMsg")!=null) {%>
    <tr>
        <td colspan="2" class="errorMsg"><%= request.getAttribute("errorMsg")%></td>
    </tr>
<% } %>

<tr>
    <netui-data:getData resultId="screenMode" value="{pageFlow.screenMode}" />    
    <%if(((Integer)pageContext.getAttribute("screenMode")).intValue() == 2) { %>
        <td align="center">    
            <netui:button type="submit" value="Create" styleClass="button" />                
        </td>
    <% } else {  %>
        <td align="center">    
            <netui:button type="submit" value="Save" styleClass="button" />        
            <netui:button type="submit" value="Delete" action="deleteAccount" styleClass="button" />                    
        </td>    
    <% } %>
</tr>
<tr>
    <td>
   	<p class="pagehead" align="left"> 
	     <big><b><u><netui:label value="{pageFlow.selectedAccount.name}" /></u></b></big>    
	</p>        
    </td>
</tr>

<tr><td>
    <table class="tablebody" width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td class="requiredInput">Name&nbsp;</td>
            <td class="xl25">                
                <netui:textBox dataSource="{pageFlow.selectedAccount.name}"></netui:textBox>
            </td>
            <td class="dataLabel">Rating&nbsp;</td>
            <td class="xl25">                
                <netui:select optionsDataSource="{pageFlow.rateOptions}" dataSource="{pageFlow.selectedAccount.rating}" />
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Industry&nbsp;</td>        
            <td class="xl25">                
                <netui:select optionsDataSource="{pageFlow.industryOptions}" dataSource="{pageFlow.selectedAccount.industry}" />
            </td>
            <td width=85 class="dataLabel">
                Account Number&nbsp;
            </td>
            <td class="xl25">                
                <netui:textBox dataSource="{pageFlow.selectedAccount.accountNumber}" />            
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Site&nbsp;</td>         
            <td class="xl25">                
                <netui:textBox dataSource="{pageFlow.selectedAccount.site}"/>        
            </td>
            <td class="dataLabel">Type&nbsp;</td>         
            <td class="xl25">
                <netui:select optionsDataSource="{pageFlow.typeOptions}" dataSource="{pageFlow.selectedAccount.type}" />
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Account Phone&nbsp;</td>         
            <td class="xl25">            
                <netui:textBox dataSource="{pageFlow.selectedAccount.accountPhone}" />        
            </td>
            <td class="dataLabel">Ticker&nbsp;</td>        
            <td class="xl25">
                <netui:textBox dataSource="{pageFlow.selectedAccount.ticker}" />
            </td>                    
        </tr>
    <!--
    <tr>
        <td class="xl25">
            Url 
        
        <td class="xl25">
            <netui:anchor href="{pageFlow.selectedAccount.url}" target="_blank"><netui:label value="{pageFlow.selectedAccount.url}"></netui:label></netui:anchor>
    </tr>
    -->
        <tr>
            <td class="dataLabel">Ownership&nbsp;</td>        
            <td class="xl25">                
                <netui:select optionsDataSource="{pageFlow.ownerShipOptions}" dataSource="{pageFlow.selectedAccount.ownership}" />        
            </td>
            <td class="dataLabel">Employees&nbsp;</td>        
            <td class="xl25">                
                <netui:textBox dataSource="{pageFlow.selectedAccount.employees}" />
            </td>            
        </tr>   
        <tr>
            <td class="dataLabel">Billing Street&nbsp;</td>         
            <td class="xl25">                
                <netui:textBox dataSource="{pageFlow.selectedAccount.billingStreet}" />
            </td>        
            <td class="dataLabel">Billing City&nbsp;</td>        
            <td class="xl25">                
                <netui:textBox dataSource="{pageFlow.selectedAccount.billingCity}" />
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Billing State &nbsp;</td>        
            <td class="xl25">                
                <netui:textBox dataSource="{pageFlow.selectedAccount.billingState}" />
            </td>
            <td class="dataLabel">Billing Postal Code &nbsp;</td>        
            <td class="xl25">                
                <netui:textBox dataSource="{pageFlow.selectedAccount.billingPostalCode}" />
            </td>                    
        </tr>
        <tr>
            <td nowrap class="dataLabel">Billing Country&nbsp;</td>
            <td class="xl25">                
                <netui:textBox dataSource="{pageFlow.selectedAccount.billingCountry}" />
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
    <%if(((Integer)pageContext.getAttribute("screenMode")).intValue() == 2) { %>
    <td align="center">    
        <netui:button type="submit" value="Create" styleClass="button" />                
    </td>
    <% } else {  %>
    <td align="center">    
        <netui:button type="submit" value="Save" styleClass="button" />        
        <netui:button type="submit" value="Delete" action="deleteAccount" styleClass="button" />                    
    </td>    
    <% } %>
</tr>

</table>
</netui:form>        



