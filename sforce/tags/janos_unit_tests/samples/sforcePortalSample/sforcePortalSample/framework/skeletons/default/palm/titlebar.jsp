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
<%@ page session="false"%>
<%@ taglib uri="render.tld" prefix="render" %>

<%
    WindowPresentationContext window = WindowPresentationContext.getWindowPresentationContext(request);
    TitlebarPresentationContext titlebar = TitlebarPresentationContext.getTitlebarPresentationContext(request);
    String title = window.getTitle();
    title = (title == null) ? "" : title;
    String imageIcon = titlebar.getIconUrl();
    boolean useIcon = (imageIcon != null && imageIcon.length() > 2);
%>

<render:beginRender>
<%-- package the title and buttons in a table. --%>
<TABLE>
<TR>
<%
    if (useIcon)
    {
%>      <td width="1%"/><img src="<%= imageIcon %>"/></td>
<%
    }
%>
<td style="background-color: #c1c1d5">
        <%= title %>
</td>		

        <%
              // pad the table with invisible cells
              // just setting the text color white doesn't work; tungsten renders it black.  use a background color
              int WIDTH = 14;
              int width = WIDTH - title.length();
              for (int i=0; i<width; i++)  {
                  %>
        <TD style="background-color: #c1c1d5; color: #c1c1d5">  &nbsp_</TD>
                <%
              }
              %>

<%
    // Hack until a better context stack is available on the request...
    String ctxId = PresentationContext.class.getName() + ".current";
    request.setAttribute(ctxId, titlebar);
%>
    <jsp:include page="buttonbar.jsp"/>
</render:beginRender>
<render:endRender>
</TR>
</TABLE>
</render:endRender>
