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

<%@ page import="com.bea.netuix.servlets.controls.window.WindowPresentationContext"%>
<%@ page session="false"%>
<%@ taglib uri="render.tld" prefix="render" %>

<%
    WindowPresentationContext window = WindowPresentationContext.getWindowPresentationContext(request);
    String title = window.getTitle();
    title = (title == null) ? "" : title;
%>

<render:beginRender>
        <table >
            <tr>
                <td style="font-weight:bold"><%= title %></td>
</render:beginRender>
<render:endRender>
            </tr>
        </table>
</render:endRender>
