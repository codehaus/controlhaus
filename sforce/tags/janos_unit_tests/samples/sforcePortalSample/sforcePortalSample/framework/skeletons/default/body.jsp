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

<%@ page import="com.bea.netuix.servlets.controls.application.BodyPresentationContext" %>
<%@ page session="false"%>
<%@ taglib uri="render.tld" prefix="render" %>

<%
    BodyPresentationContext body = BodyPresentationContext.getBodyPresentationContext(request);
%>

<render:beginRender>
    <%-- Begin Body --%>
    <body
        <render:writeAttribute name="id" value="<%= body.getPresentationId() %>" />
        <render:writeAttribute name="class" value="<%= body.getPresentationClass() %>" defaultValue="bea-portal-body"/>
        <render:writeAttribute name="style" value="<%= body.getPresentationStyle() %>"/>
        <render:writeAttribute name="onload" value="<%= body.getOnloadScript() %>"/>
        <render:writeAttribute name="onunload" value="<%= body.getOnunloadScript() %>"/>
    >
        <%-- Begin Body Content --%>
        <div class="bea-portal-body-content">
</render:beginRender>
<render:endRender>
        </div>
        <%-- End Body Content --%>
    </body>
    <%-- End Body--%>
</render:endRender>
