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
    <%-- Begin Grid Layout--%>
    <table cellspacing="0" >
    <%
        PlaceholderPresentationContext[][] grid = layout.getPlaceholderGrid();

        // start mobile
        // create a new grid that's 1xn
        int length = 0;
        for (int i=0; i<grid.length; i++)  
        {
			length += grid[i].length;
        }

		PlaceholderPresentationContext[][] onegrid = new PlaceholderPresentationContext[length][1];

        int cur = 0;
        for (int i=0; i<grid.length; i++) 
        {
            for (int j=0; j<grid[i].length; j++)  
            {
				onegrid[cur][0] = grid[i][j];
				cur++;
            }
        }

        // use the one column-grid from here on out
		grid = onegrid;
        // end mobile

        for (int i = 0; i < grid.length; i++)
        {
    %>
        <tr>
    <%
            for (int j = 0; j < grid[i].length; j++)
            {
    %>
            <td 
                <render:writeAttribute name="width" value="<%=  grid[i][j] != null ? grid[i][j].getWidth() : null %>"/>
            ><render:renderChild presentationContext="<%= grid[i][j] %>"/></td>
    <%
            }
    %>
        </tr>
    <%
        }
    %>
    </table>
    <%-- End Grid Layout--%>
</render:beginRender>
<render:endRender>
</render:endRender>
