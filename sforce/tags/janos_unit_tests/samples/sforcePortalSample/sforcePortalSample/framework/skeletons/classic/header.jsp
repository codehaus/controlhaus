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

<%@ page import="com.bea.netuix.servlets.controls.application.HeaderPresentationContext" %>
<%@ taglib uri="render.tld" prefix="render" %>
<%@taglib prefix="netui" uri="netui-tags-html.tld"%>


<%
    HeaderPresentationContext header = HeaderPresentationContext.getHeaderPresentationContext(request);
%>

<render:beginRender>
    <!-- Begin Body Header -->
    <table width="100%">
    <tr>
    <td>    
    <div
        <render:writeAttribute name="id" value="<%= header.getPresentationId() %>"/>
        <render:writeAttribute name="class" value="<%= header.getPresentationClass() %>" defaultValue="page-book-header"/>
        <render:writeAttribute name="style" value="<%= header.getPresentationStyle() %>"/>
    >
    <netui:image src="/SForcePortalDemo/framework/skins/classic/images/sforce-header.gif">
    </netui:image>
    </div>
    </td>
    <td valign="bottom" align="right">
    
    <a target="new" href="http://www.sforce.com/us/resources/api.jspp">Help and Training</a>
    
    </tr>
    </table>
</render:beginRender>
<render:endRender>
    
    <!-- End Body Header -->
</render:endRender>
