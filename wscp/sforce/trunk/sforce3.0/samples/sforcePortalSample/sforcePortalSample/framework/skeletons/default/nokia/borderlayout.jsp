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
                 com.bea.netuix.servlets.controls.layout.BorderLayoutPresentationContext"
%>
<%@ page session="false"%>
<%@ taglib uri="render.tld" prefix="render" %>

<%
    BorderLayoutPresentationContext layout = BorderLayoutPresentationContext.getBorderLayoutPresentationContext(request);
    PlaceholderPresentationContext north = layout.north();
    PlaceholderPresentationContext west = layout.west();
    PlaceholderPresentationContext center = layout.center();
    PlaceholderPresentationContext east = layout.east();
    PlaceholderPresentationContext south = layout.south();
    int columns = layout.columns();

    // mobile
	columns = 1;
%>

<render:beginRender>
    <%-- Begin Border Layout --%>
    <table width="100%" >
	<%-- mobile - override so that it's only one column --%>
    <%
        if (north != null)
        {
    %>
        <tr>
            <td colspan="<%= columns %>" width="100%" ><render:renderChild presentationContext="<%= north %>"/></td>
        </tr>
    <%
        }

        if (west != null || center != null || east != null)
        {
    %>
    <%
            if (west != null)
            {
    %>
        <tr>
            <td width="100%" ><render:renderChild presentationContext="<%= west %>"/></td> 
		</TR>
    <%
            }

            if (center != null)
            {
    %>
        <tr>
            <td width="100%" ><render:renderChild presentationContext="<%= center %>"/></td>
		</TR>
    <%
            }

            if (east != null)
            {
    %>
        <tr>
            <td width="100%" ><render:renderChild presentationContext="<%= east %>"/></td>
        </tr>
		 
    <%
            }
        }

        if (south != null)
        {
    %>
        <tr>
            <td colspan="<%= columns %>" width="100%"><render:renderChild presentationContext="<%= south %>"/></td>
        </tr>
    <%
        }
    %>
    </table>
    <%-- End Border Layout --%>
</render:beginRender>
<render:endRender>
</render:endRender>
