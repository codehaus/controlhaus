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

<%@ page import="com.bea.netuix.servlets.manager.AppDescriptor,
                 com.bea.netuix.servlets.manager.AppContext,
                 com.bea.netuix.servlets.controls.window.*,
                 com.bea.netuix.servlets.controls.page.Page"
%><%@ page session="false"%><%@ taglib uri="render.tld" prefix="render" %><%
    ButtonPresentationContext button = ButtonPresentationContext.getButtonPresentationContext(request);
    String buttonClass = "bea-portal-button";
    // if parent window is not Page it is a Portlet
    if ( button.isParentPortlet() )
    {
        buttonClass = "bea-portal-button-delete";
    }

%><render:resourceUrl var="rolloverImgSrc" path="<%= button.getRolloverImageSrc() %>"/><%
     String userName = request.getRemoteUser();
     boolean isCustomizationEnabled = AppDescriptor.getInstance().isCustomizationEnabled();
     if(userName != null && isCustomizationEnabled && buttonClass != null &&
        buttonClass.equals("bea-portal-button-delete") && ! AppContext.getAppContext(request).isDotPortal())
     {
%><render:beginRender>
<td>
<a href="<render:toggleButtonUrl/>"> <%=button.getAltText()%>
</render:beginRender><render:endRender></a></td></render:endRender><%}
     else if(buttonClass != null && buttonClass.equals("bea-portal-button-delete")) {
%><render:beginRender><td><a href="<render:toggleButtonUrl/>"> <%=button.getAltText()%>
</render:beginRender><render:endRender></td></a></render:endRender><%
}%>
