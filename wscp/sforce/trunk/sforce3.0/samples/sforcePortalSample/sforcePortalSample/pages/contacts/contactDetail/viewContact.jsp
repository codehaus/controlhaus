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
        <netui:form action="editContact">
            <netui:button type="submit" value="Create New" action="createContact" styleClass="button"/>
            <netui:button type="submit" value="Edit" action="editContact" styleClass="button"/>
            <netui:button type="submit" value="Delete" action="deleteContact" styleClass="button"/>
        </netui:form>        
    </td>
</tr>
<tr>
    <td>
   	<p class="pagehead" align="left"> 
	     <big><b><u><netui:label value="{pageFlow.selectedContact.firstName}" /></u></b></big>    
	</p>        
    </td>
</tr>

<tr><td>
    <table class="tablebody" width="100%" align="center" cellpadding="1" cellspacing="1" border="0">
        <tr>
            <td class="dataLabel">First Name</td>
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedContact.salutation}" />
                <netui:label value="{pageFlow.selectedContact.firstName}" />
            </td>
            <td class="dataLabel">Mobile Phone</td>
            <td class="dataField">                
                <netu:label value="{pageFlow.selectedContact.mobilePhone}" />
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Last Name</td>        
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedContact.lastName}" />
            </td>
            <td width=85 class="dataLabel">Business Phone</td>
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedContact.businessPhone}" />            
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Title</td>         
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedContact.title}"/>        
            </td>
            <td class="dataLabel">Email</td>         
            <td class="dataField">
                <netui:label value="{pageFlow.selectedContact.email}" />
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Department</td>        
            <td class="dataField">
                <netui:label value="{pageFlow.selectedContact.department}" />
            </td>                    
            <td class="dataLabel">Mailing Street</td>        
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedContact.mailingStreet}" />        
            </td>
        </tr>
        <tr>
            <td class="dataLabel">Mailing City</td>        
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedContact.mailingCity}" />
            </td>            
            <td class="dataLabel">Mailing State</td>         
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedContact.mailingState}" />
            </td>        
        </tr>   
        <tr>
            <td class="dataLabel">Mailing Country</td>        
            <td class="dataField">                
                <netui:label value="{pageFlow.selectedContact.mailingCountry}" />
            </td>            
        </tr>    
    
    </table>

</td>
</tr>
<tr>    
  <td align="center">   
    <%
        JspContentContext jspContentContext = JspContentContext.getJspContentContext(request);
        PostbackURL accountPageUrl = jspContentContext.getBaseUrl(request, response, "_pageLabel",  "account_page");
	    PostbackURL casePageUrl = jspContentContext.getBaseUrl(request, response, "_pageLabel",  "case_page");
    %>
    <a href="<%= accountPageUrl.toString()%>">Accounts<a/>
	&nbsp;&nbsp;|&nbsp;&nbsp;  
    <a href="<%= casePageUrl.toString()%>">Cases<a/>  
  </td>
</tr>

<tr>
    <td align="center">    
        <netui:form action="editContact">
            <netui:button type="submit" value="Create New" action="createContact" styleClass="button"/>
            <netui:button type="submit" value="Edit" action="editContact" styleClass="button"/>
            <netui:button type="submit" value="Delete" action="deleteContact" styleClass="button"/>
        </netui:form>        
    </td>
</tr>

</table>

