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

<%@ page import="com.bea.netuix.servlets.controls.window.ToggleButtonPresentationContext,
                 com.bea.netuix.servlets.controls.window.TitlebarPresentationContext,
                 com.bea.netuix.servlets.controls.window.WindowPresentationContext"
%>
<%@ page session="false"%>
<%@ taglib uri="render.tld" prefix="render" %><render:beginRender><%
    ToggleButtonPresentationContext button = ToggleButtonPresentationContext.getToggleButtonPresentationContext(request);
    String buttonClass = button.getPresentationClass();
    String defaultButtonClass = "bea-portal-button";
%><a
    <render:writeAttribute name="id" value="<%= button.getPresentationId() %>"/>
    <render:writeAttribute name="class" value="<%= buttonClass %>" defaultValue="<%= defaultButtonClass %>"/>
    <render:writeAttribute name="style" value="<%= button.getPresentationStyle() %>"/>
    href="<render:toggleButtonUrl/>"><%= button.getAltText() %></render:beginRender><render:endRender></a><br/></render:endRender>