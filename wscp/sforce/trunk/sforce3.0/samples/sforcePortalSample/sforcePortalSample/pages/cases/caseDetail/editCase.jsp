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

<netui:form action="saveCase">


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
            <netui:button type="submit" value="Delete" action="deleteCase" styleClass="button" />                    
        </td>    
    <% } %>
</tr>
<tr>

</tr>

<tr><td>
    <table class="tablebody" width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td class="requiredInput">Case owner&nbsp;</td>
            <td class="xl25">                
                <netui:label value="{pageFlow.selectedCase.ownerAlias}"></netui:label>
            </td>
            <td class="dataLabel">Status&nbsp;</td>
            <td class="xl25">                
                <netui:select optionsDataSource="{pageFlow.statusOptions}" dataSource="{pageFlow.selectedCase.status}" />
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Supplied Company&nbsp;</td>        
            <td class="xl25">                
                <netui:textBox  dataSource="{pageFlow.selectedCase.suppliedCompany}" />
            </td>
            <td width=85 class="dataLabel">
                Priority&nbsp;
            </td>
            <td class="xl25">                
                <netui:select optionsDataSource="{pageFlow.priorityOptions}" dataSource="{pageFlow.selectedCase.priority}" />
            </td>            
        </tr>
        <tr>
            <td class="dataLabel">Type&nbsp;</td>         
            <td class="xl25">                
                <netui:select optionsDataSource="{pageFlow.typeOptions}" dataSource="{pageFlow.selectedCase.type}" />
            </td>
            <td class="dataLabel">Subject&nbsp;</td>        
            <td class="xl25">
                <netui:textBox dataSource="{pageFlow.selectedCase.subject}" />
            </td>           
        </tr>
    <!--
    <tr>
        <td class="xl25">
            Url 
        
        <td class="xl25">
            <netui:anchor href="{pageFlow.selectedCase.url}" target="_blank"><netui:label value="{pageFlow.selectedCase.url}"></netui:label></netui:anchor>
    </tr>
    -->
        
    
    
    </table>

</td>
</tr>
<tr>    
  <td align="center">   
    <netui:anchor action="viewCase">
        <netui:parameter name="caseid" value='<%=request.getParameter("caseid")%>'/>
        <netui:parameter name="customername" value='<%=request.getParameter("customername")%>'/>
        Contacts
    </netui:anchor> &nbsp;&nbsp;|&nbsp;&nbsp;  
    <netui:anchor action="viewCase">Cases</netui:anchor> 
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
        <netui:button type="submit" value="Delete" action="deleteCase" styleClass="button" />                    
    </td>    
    <% } %>
</tr>

</table>
</netui:form>        



