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

<%@ page import="com.bea.netuix.servlets.controls.page.PagePresentationContext,
                 com.bea.netuix.servlets.controls.page.BookPresentationContext"
%>
<%@ page session="false"%>
<%@ taglib uri="render.tld" prefix="render" %>

<render:beginRender>
<%
    PagePresentationContext pageCtx = PagePresentationContext.getPagePresentationContext(request);
    BookPresentationContext book = BookPresentationContext.getBookPresentationContext(request);
    String bookClass = "bea-portal-book";

    if (book.isDesktopBook())
    {
        bookClass += "-primary";
    }
    else if (book.isContained())
    {
        bookClass += "-contained";
    }

    String pageClass = bookClass + "-page";
%>

    <div
        <render:writeAttribute name="id" value="<%= pageCtx.getPresentationId() %>"/>
        <render:writeAttribute name="class" value="<%= pageCtx.getPresentationClass() %>" defaultValue="<%= pageClass %>"/>
        <render:writeAttribute name="style" value="<%= pageCtx.getPresentationStyle() %>"/>
    >
</render:beginRender>
<render:endRender>
    </div>
</render:endRender>
