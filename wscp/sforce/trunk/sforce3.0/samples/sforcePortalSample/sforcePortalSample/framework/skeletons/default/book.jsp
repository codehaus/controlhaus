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

<%@ page import="com.bea.netuix.servlets.controls.page.BookPresentationContext,
                 com.bea.netuix.servlets.controls.page.MenuPresentationContext"
%>
<%@ page session="false"%>
<%@ taglib uri="render.tld" prefix="render" %>

<render:beginRender>
<%
    BookPresentationContext book = BookPresentationContext.getBookPresentationContext(request);
    MenuPresentationContext menu = (MenuPresentationContext) book.getFirstChild("page:menu");
    String bookClass = "bea-portal-book";
    String useBookClass = bookClass;

    if (book.isDesktopBook())
    {
        bookClass += "-primary";
        useBookClass = bookClass;
    }
    else if (book.isLikePage())
    {
        useBookClass += "-invisible";
    }

    String bookContentClass = bookClass + "-content";
%>
    <%-- Begin Book --%>
    <div
        <render:writeAttribute name="id" value="<%= book.getPresentationId() %>"/>
        <render:writeAttribute name="class" value="<%= book.getPresentationClass()%>" defaultValue="<%= useBookClass %>"/>
        <render:writeAttribute name="style" value="<%= book.getPresentationStyle() %>"/>
    >
        <render:renderChild presentationContext="<%= menu %>"/>
        <%-- Begin Book Content --%>
        <div
            <render:writeAttribute name="class" value="<%= book.getContentPresentationClass()%>" defaultValue="<%=  bookContentClass %>"/>
            <render:writeAttribute name="style" value="<%= book.getContentPresentationStyle() %>"/>
        >
</render:beginRender>
<render:endRender>
        </div>
        <%-- End Book Content --%>
    </div>
    <%-- End Book --%>
</render:endRender>
