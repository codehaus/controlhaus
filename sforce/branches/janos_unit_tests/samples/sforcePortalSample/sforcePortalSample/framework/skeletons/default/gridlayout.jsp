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

<%@ page import="com.bea.netuix.servlets.controls.layout.PlaceholderPresentationContext,
                 com.bea.netuix.servlets.controls.layout.GridLayoutPresentationContext"
%>
<%@ page session="false"%>
<%@ taglib uri="render.tld" prefix="render" %>

<%
    GridLayoutPresentationContext layout = GridLayoutPresentationContext.getGridLayoutPresentationContext(request);
%>

<render:beginRender>
    <%-- Begin Grid Layout --%>
    <table
        <render:writeAttribute name="id" value="<%= layout.getPresentationId() %>"/>
        <render:writeAttribute name="class" value="<%= layout.getPresentationClass() %>" defaultValue="bea-portal-layout-grid"/>
        <render:writeAttribute name="style" value="<%= layout.getPresentationStyle() %>"/>
        cellspacing="0"
    >
    <%
        PlaceholderPresentationContext[][] grid = layout.getPlaceholderGrid();

        for (int i = 0; i < grid.length; i++)
        {
    %>
        <tr>
    <%
            for (int j = 0; j < grid[i].length; j++)
            {
    %>
            <%-- Begin Grid Layout Cell [<%= i %>, <%= j %>] --%>
            <td class="bea-portal-layout-placeholder-container"
                <render:writeAttribute name="width" value="<%=  grid[i][j] != null ? grid[i][j].getWidth() : null %>"/>
            ><render:renderChild presentationContext="<%= grid[i][j] %>"/></td>
            <%-- End Grid Layout Cell [<%= i %>, <%= j %>] --%>
    <%
            }
    %>
        </tr>
    <%
        }
    %>
    </table>
    <%-- End Grid Layout --%>
</render:beginRender>
<render:endRender>
</render:endRender>
