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
                 com.bea.netuix.servlets.controls.page.BookPresentationContext,
                 com.bea.netuix.servlets.controls.page.MenuPresentationContext,
                 java.util.List,
                 java.util.Iterator,
                 com.bea.netuix.servlets.controls.page.PagePresentationContext" %>
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

    final String menuClass = bookClass + "-menu";
    final String menuContainerClass = menuClass + "-container";
    final String menuItemClass = menuClass + "-item";
    final String menuItemActiveClass = menuItemClass + "-active";
    final String menuItemLinkClass = menuItemClass + "-link";
    final String menuHookClass = menuClass + "-hook";
    final String menuButtonsClass = menuItemClass + "-buttons";
    List menuChildren = menu.getChildren();
%>

<render:beginRender>
    <%-- Begin Multi Level Menu --%>
        <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tr>
                <td class="<%= menuContainerClass %>" align="left" nowrap>
                    <ul
                        <render:writeAttribute name="id" value="<%= menu.getPresentationId() %>"/>
                        <render:writeAttribute name="class" value="<%= menu.getPresentationClass() %>" defaultValue="<%= menuClass %>"/>
                        <render:writeAttribute name="style" value="<%= menu.getPresentationStyle() %>"/>
                    ><%
                        request.setAttribute(BookPresentationContext.class.getName() + ".root-flag", Boolean.TRUE);
                        request.setAttribute(BookPresentationContext.class.getName() + ".menu-item", book);
                        request.setAttribute(BookPresentationContext.class.getName() + ".menu-class", menuClass);
                        request.setAttribute(BookPresentationContext.class.getName() + ".menu-item-class", menuItemClass);
                        request.setAttribute(BookPresentationContext.class.getName() + ".menu-item-active-class", menuItemActiveClass);
                        request.setAttribute(BookPresentationContext.class.getName() + ".menu-item-link-class", menuItemLinkClass);
                        %><jsp:include page="submenu.jsp"/><%
                        request.removeAttribute(BookPresentationContext.class.getName() + ".root-flag");
                        request.removeAttribute(BookPresentationContext.class.getName() + ".menu-item");
                        request.removeAttribute(BookPresentationContext.class.getName() + ".menu-class");
                        request.removeAttribute(BookPresentationContext.class.getName() + ".menu-item-class");
                        request.removeAttribute(BookPresentationContext.class.getName() + ".menu-item-active-class");
                        request.removeAttribute(BookPresentationContext.class.getName() + ".menu-item-link-class");
                    %></ul>
                </td>
<%
    if (menuChildren != null && menuChildren.size() > 0)
    {
%>
                <td class="<%= menuButtonsClass %>" align="right" nowrap>
<%
    }
%>
</render:beginRender>
<render:endRender>
<%
    if (menuChildren != null && menuChildren.size() > 0)
    {
%>
                </td>
<%
    }
%>
            </tr>
        </table>
		<HR>
    <%-- End Multi Level Menu --%>
</render:endRender>
