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

<%@ page import="java.util.Iterator,
                 java.util.List,
                 com.bea.netuix.servlets.controls.page.BookPresentationContext,
                 com.bea.portlet.PageURL,
                 com.bea.netuix.servlets.controls.page.PagePresentationContext"%>
<%@ page session="false"%>
<%
    Boolean isRoot
        = (Boolean) request.getAttribute(BookPresentationContext.class.getName() + ".root-flag");
    BookPresentationContext bookCtx
        = (BookPresentationContext) request.getAttribute(BookPresentationContext.class.getName() + ".menu-item");
    String menuClass
        = (String) request.getAttribute(BookPresentationContext.class.getName() + ".menu-class");
    String menuItemClass
        = (String) request.getAttribute(BookPresentationContext.class.getName() + ".menu-item-class");
    String menuItemActiveClass
        = (String) request.getAttribute(BookPresentationContext.class.getName() + ".menu-item-active-class");
    String menuItemLinkClass
        = (String) request.getAttribute(BookPresentationContext.class.getName() + ".menu-item-link-class");
%>
<%
    if (!bookCtx.isHidden() && bookCtx.isVisible())
    {
        if (bookCtx instanceof BookPresentationContext)
        {
            List bookChildren = bookCtx.getPagePresentationContexts();
            Iterator it = bookChildren.iterator();

            while (it.hasNext())
            {
                PagePresentationContext childPageCtx = (PagePresentationContext) it.next();

                if (!childPageCtx.isHidden() && childPageCtx.isVisible())
                {
                    %><li><%
                    %><a href="<%= PageURL.createPageURL(request, response, childPageCtx.getDefinitionLabel()).toString() %>"><%= childPageCtx.getTitle() %></a><%

                    if (childPageCtx instanceof BookPresentationContext)
                    {
                        %><ul><%
                        request.setAttribute(BookPresentationContext.class.getName() + ".root-flag", Boolean.FALSE);
                        request.setAttribute(BookPresentationContext.class.getName() + ".menu-item", childPageCtx);
                        %><jsp:include page="submenu.jsp"/><%
                        request.removeAttribute(BookPresentationContext.class.getName() + ".root-flag");
                        request.removeAttribute(BookPresentationContext.class.getName() + ".menu-item");
                        %></ul><%
                    }

                    %></li><%
                }
            }
        }
    }
%>
