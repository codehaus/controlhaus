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

<%@ page import="com.bea.netuix.servlets.controls.window.WindowPresentationContext,
                 com.bea.netuix.servlets.controls.window.TitlebarPresentationContext,
                 java.util.Iterator,
                 com.bea.netuix.servlets.controls.page.PagePresentationContext,
                 com.bea.netuix.servlets.controls.page.BookPresentationContext,
                 com.bea.netuix.servlets.controls.page.MenuPresentationContext,
                 com.bea.netuix.servlets.controls.PresentationContext,
                 com.bea.netuix.servlets.controls.window.WindowCapabilities" %>
<%@ page session="false"%>
<%@ taglib uri="render.tld" prefix="render" %>

<%
    BookPresentationContext book = BookPresentationContext.getBookPresentationContext(request);
    MenuPresentationContext menu = MenuPresentationContext.getMenuPresentationContext(request);

%>

<render:beginRender>
<%
    // Hack until a better context stack is available on the request...
    String ctxId = PresentationContext.class.getName() + ".current";
    request.setAttribute(ctxId, menu);
%>
    <%-- Begin Single Level Menu --%>
	<TABLE>
	<TR><%
    // Only render tabs when in view mode
    if (book.getWindowMode().equals(WindowCapabilities.VIEW))
    {
        Iterator pages = book.getPagePresentationContexts().iterator();

        while (pages.hasNext())
        {
            PagePresentationContext pageCtx = (PagePresentationContext) pages.next();
            String activeImage = pageCtx.getActiveImage();
            String inactiveImage = pageCtx.getInactiveImage();
            String rolloverImage = pageCtx.getRolloverImage();

            if (!pageCtx.isHidden() && pageCtx.isVisible())
            {
                if (pageCtx.isActive())
                {
                    if (activeImage == null)
                    {
                        %><td><span><%= pageCtx.getTitle() %></span></td><%
                    }
                    else if (rolloverImage == null)
                    {
                        %><td><span><img src="<%= activeImage %>"></span></td><%
                    }
                    else
                    {
                        %><td><span><img src="<%= activeImage %>" longDesc="<%= rolloverImage %>"></span></td><%
                    }
                }
                else
                {
                    if (inactiveImage == null)
                    {
                        %><td style="background-color: #c1c1d5"><a href="<render:pageUrl pageLabel="<%= pageCtx.getDefinitionLabel() %>"/>"><%= pageCtx.getTitle() %></a></td><%
                    }
                    else if (rolloverImage == null)
                    {
                        %><td><a href="<render:pageUrl pageLabel="<%= pageCtx.getDefinitionLabel() %>"/>"><img src="<%= inactiveImage %>"></a></td><%
                    }
                    else
                    {
                        %><td><a href="<render:pageUrl pageLabel="<%= pageCtx.getDefinitionLabel() %>"/>"><img src="<%= inactiveImage %>" longDesc="<%= rolloverImage %>"></a></td><%
                    }
                }
            }
        }
    }
%><jsp:include page="buttonbar.jsp"/></tr></TABLE>
    <%-- End Single Level Menu --%>
</render:beginRender>
<render:endRender>
</render:endRender>
