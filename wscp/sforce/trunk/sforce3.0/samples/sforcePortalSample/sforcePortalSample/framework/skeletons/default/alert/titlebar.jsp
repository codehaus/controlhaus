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
                 com.bea.netuix.servlets.controls.window.WindowPresentationContext"
%>
<%@ page session="false"%>
<%@ taglib uri="render.tld" prefix="render" %>

<%
    WindowPresentationContext window = WindowPresentationContext.getWindowPresentationContext(request);
    TitlebarPresentationContext titlebar = TitlebarPresentationContext.getTitlebarPresentationContext(request);
    String title = window.getTitle();
    title = (title == null) ? "" : title;
    String imageIcon = titlebar.getIconUrl();
    boolean useIcon = (imageIcon != null && imageIcon.length() > 0);
%>

<render:beginRender>
    <%-- Begin Titlebar --%>
    <div
        <render:writeAttribute name="id" value="<%= titlebar.getPresentationId() %>"/>
        <render:writeAttribute name="class" value="<%= titlebar.getPresentationClass() %>" defaultValue="bea-portal-alert-window-titlebar"/>
        <render:writeAttribute name="style" value="<%= titlebar.getPresentationStyle() %>"/>
    >
        <div class="bea-portal-ie-table-buffer-div">
            <table class="bea-portal-alert-window-titlebar-container" cellspacing="0">
                <tr>
<%
    if (useIcon)
    {
%>
                    <td class="bea-portal-alert-window-icon"><img src="<%= imageIcon %>"/></td>
<%
    }
%>
                    <td class="bea-portal-alert-window-titlebar-title" nowrap="nowrap"><%= title %></td>
                    <td class="bea-portal-alert-window-titlebar-buttons" nowrap="nowrap"></render:beginRender><%--
                        This marks the break between begin and end render blocks; buttons will be output here.
                    --%><render:endRender></td>
                </tr>
            </table>
        </div>
    </div>
    <%-- End Titlebar --%>
</render:endRender>
