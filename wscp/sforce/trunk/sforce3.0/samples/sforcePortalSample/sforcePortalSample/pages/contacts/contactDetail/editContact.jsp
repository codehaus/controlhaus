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

<netui:form action="saveContact">


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
            <netui:button type="submit" value="Delete" action="deleteContact" styleClass="button" />                    
        </td>    
    <% } %>
</tr>
<tr>
    <td>
   	<p class="pagehead" align="left"> 
	     <big><b><u><netui:label value="{pageFlow.selectedContact.firstName}" /></u></b></big>    
	</p>        
    </td>
</tr>

<tr><td>
    <table class="tablebody" width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td class="dataLabel">Name&nbsp;</td>
            <td>                
                <netui:select optionsDataSource="{pageFlow.salutationOptions}" dataSource="{pageFlow.selectedContact.salutation}" />
                <netui:textBox dataSource="{pageFlow.selectedContact.firstName}" />
            </td>
            <td class="dataLabel">Mobile Phone</td>
            <td>                
                <netui:textBox dataSource="{pageFlow.selectedContact.mobilePhone}" />            
            </td>            
        </tr>
        <tr>
            <td class="requiredInput">Last Name</td>        
            <td>                
                <netui:textBox dataSource="{pageFlow.selectedContact.lastName}" />            
            </td>
            <td width=85 class="dataLabel">
                Business Phone&nbsp;
            </td>
            <td>                
                <netui:textBox dataSource="{pageFlow.selectedContact.businessPhone}" />            
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Title&nbsp;</td>         
            <td>                
                <netui:textBox dataSource="{pageFlow.selectedContact.title}"/>        
            </td>
            <td class="dataLabel">Email&nbsp;</td>         
            <td>
                <netui:textBox dataSource="{pageFlow.selectedContact.email}" />        
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Department&nbsp;</td>        
            <td>
                <netui:textBox dataSource="{pageFlow.selectedContact.department}" />
            </td>                    
            <td class="dataLabel">Mailing Street&nbsp;</td>        
            <td>                
                <netui:textBox dataSource="{pageFlow.selectedContact.mailingStreet}" />
            </td>
        </tr>   
        <tr>
            <td class="dataLabel">Mailing City&nbsp;</td>        
            <td>                
                <netui:textBox dataSource="{pageFlow.selectedContact.mailingCity}" />
            </td>            
            <td class="dataLabel">Mailing State&nbsp;</td>         
            <td>                
                <netui:textBox dataSource="{pageFlow.selectedContact.mailingState}" />
            </td>        
        </tr>   
        <tr>
            <td class="dataLabel">Mailing Country&nbsp;</td>        
            <td>                
                <netui:textBox dataSource="{pageFlow.selectedContact.mailingCountry}" />
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
    <%if(((Integer)pageContext.getAttribute("screenMode")).intValue() == 2) { %>
    <td align="center">    
        <netui:button type="submit" value="Create" styleClass="button" />                
    </td>
    <% } else {  %>
    <td align="center">    
        <netui:button type="submit" value="Save" styleClass="button" />        
        <netui:button type="submit" value="Delete" action="deleteContact" styleClass="button" />                    
    </td>    
    <% } %>
</tr>

</table>
</netui:form>        



