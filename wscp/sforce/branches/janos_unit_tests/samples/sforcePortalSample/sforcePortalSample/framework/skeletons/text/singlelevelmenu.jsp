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
    String bookClass = "bea-portal-book";

    if (book.isDesktopBook())
    {
        bookClass += "-primary";
    }
    else if (book.isContained())
    {
        bookClass += "-contained";
    }

    String menuClass = bookClass + "-menu-single";
    String menuItemClass = menuClass + "-item";
    String menuItemActiveClass = menuItemClass + "-active";
    String menuContainerClass = menuClass + "-container";
    String menuChildrenClass = menuClass + "-children";
%>

<render:beginRender>
    <%-- Begin Single Level Menu --%>
    <div class="bea-portal-ie-table-buffer-div">
    <table border="0" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="<%= menuContainerClass %>" nowrap="nowrap">
    <ul
        <render:writeAttribute name="id" value="<%= menu.getPresentationId() %>"/>
        <render:writeAttribute name="class" value="<%= menu.getPresentationClass() %>" defaultValue="<%= menuClass %>"/>
        <render:writeAttribute name="style" value="<%= menu.getPresentationStyle() %>"/>
    ><%
    // Only render tabs when in view mode
    if (book.getWindowMode().equals(WindowCapabilities.VIEW))
    {

        Iterator pages = book.getPagePresentationContexts().iterator();

        while (pages.hasNext())
        {
            PagePresentationContext pageCtx = (PagePresentationContext) pages.next();

            if (!pageCtx.isHidden() && pageCtx.isVisible())
            {
                if (pageCtx.isActive())
                {
    %><li class="<%= menuItemActiveClass %>" ><span><%= pageCtx.getTitle() %></span></li><%
                }
                else
                {
    %><li class="<%= menuItemClass %>" ><a href="<render:pageUrl pageLabel="<%=pageCtx.getDefinitionLabel()%>"/>"><%= pageCtx.getTitle() %></a></li><%
                }
            }
        }
    }
%></ul>
            </td>
            <td class="<%= menuChildrenClass %>">
</render:beginRender>
<render:endRender>
            </td>
        </tr>
    </table>
    </div>
    <%-- End Single Level Menu --%>
</render:endRender>
