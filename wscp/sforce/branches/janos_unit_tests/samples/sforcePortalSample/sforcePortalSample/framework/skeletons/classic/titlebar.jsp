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

<%@ page import="com.bea.netuix.servlets.controls.window.TitlebarPresentationContext,
                 com.bea.netuix.servlets.controls.window.WindowPresentationContext,
                 com.bea.netuix.servlets.controls.PresentationContext"
%>
<%@ taglib uri="render.tld" prefix="render" %>
<%@ page session="false"%>
<render:beginRender>
<%
    WindowPresentationContext window = WindowPresentationContext.getWindowPresentationContext(request);
    TitlebarPresentationContext titlebar = TitlebarPresentationContext.getTitlebarPresentationContext(request);
    String title = window.getTitle();
    title = (title == null) ? "" : title;
    String imageIcon = titlebar.getIconUrl();
    boolean useIcon = (imageIcon != null && imageIcon.length() > 0);
%>
    <div
        <render:writeAttribute name="id" value="<%= titlebar.getPresentationId() %>"/>
        <render:writeAttribute name="class" value="<%= titlebar.getPresentationClass() %>" defaultValue="bea-portal-window-titlebar"/>
        <render:writeAttribute name="style" value="<%= titlebar.getPresentationStyle() %>"/>
    >
<%
    if (useIcon)
    {
%>
        <img class="bea-portal-window-icon" src="<%= imageIcon %>"/>
<%
    }
%>
        <span class="bea-portal-window-titlebar-title"><%= title %></span>
    </div>
<%
    String ctxId = PresentationContext.class.getName() + ".current";
    request.setAttribute(ctxId, titlebar);
%>
    <jsp:include page="buttonbar.jsp"/>
</render:beginRender>
<render:endRender>
</render:endRender>
