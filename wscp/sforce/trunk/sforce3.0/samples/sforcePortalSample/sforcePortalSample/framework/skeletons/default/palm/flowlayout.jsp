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
                 com.bea.netuix.servlets.controls.layout.FlowLayoutPresentationContext,
                 java.util.List,
                 com.bea.netuix.servlets.controls.PresentationContext"
%>
<%@ page session="false"%>
<%@ taglib uri="render.tld" prefix="render" %>

<%
    FlowLayoutPresentationContext layout = FlowLayoutPresentationContext.getFlowLayoutPresentationContext(request);
%>

<render:beginRender>
    <%-- Begin Flow Layout--%>
    <table cellspacing="0" >
        <tr>
    <%
        List children = layout.getChildren("layout:placeholder");
        int size = children.size();

        for (int i = 0; i < size; i++)
        {
            PlaceholderPresentationContext child = (PlaceholderPresentationContext) children.get(i);
    %>
            <td
                <render:writeAttribute name="width" value="<%= child.getWidth() %>"/>
            ><render:renderChild presentationContext="<%= child %>"/></td>
    <%
            // start mobile
            //if (layout.isVertical())
			if (true) 
            // end mobile
            {
                if (i != size - 1)
                {
    %>           </tr>
                 <tr>
    <%
                }
            }
        }
    %>
        </tr>
    </table>
    <%-- End Flow Layout--%>
</render:beginRender>
<render:endRender>
</render:endRender>
