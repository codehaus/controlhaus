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
<netui:html>
    
    <netui-data:getData resultId="userUniqueId" value="{pageFlow.userUniqueId}"/>
    <netui-data:getData resultId="userFullName" value="{pageFlow.userFullName}"/>
    <netui-data:getData resultId="userOrganizationName" value="{pageFlow.userOrganizationName}"/>
    <netui-data:getData resultId="userOrganizationId" value="{pageFlow.userOrganizationId}"/>
    <netui-data:getData resultId="userTimeZone" value="{pageFlow.userTimeZone}"/>
    <netui-data:getData resultId="serverTimestamp" value="{pageFlow.serverTimestamp}"/>
    <body>
        <p>
        <table border="1">
            <tr>
                <td><b>User's unique ID:</b></td>
                <td><%=(String) pageContext.getAttribute("userUniqueId")%></td>
                <td><b>User's full name:</b></td>
                <td><%=(String) pageContext.getAttribute("userFullName")%></td>
            </tr>
            <tr>
                <td><b>Organization unique ID:</b></td>
                <td><%=(String) pageContext.getAttribute("userOrganizationId")%></td>
                <td><b>Organization full name:</b></td>
                <td><%=(String) pageContext.getAttribute("userOrganizationName")%></td>
            </tr>
            <tr>
                <td><b>User's time zone:</b></td>
                <td><%=(String) pageContext.getAttribute("userTimeZone")%></td>
                <td><b>Server's current time stamp</b></td>
                <td><%=(String) pageContext.getAttribute("serverTimestamp")%></td>
            </tr>
        </table>

    </body>
</netui:html>