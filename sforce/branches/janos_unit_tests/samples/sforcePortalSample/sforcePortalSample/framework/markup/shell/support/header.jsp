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

<%@ page import="com.bea.netuix.servlets.controls.application.DesktopPresentationContext,
                 com.bea.netuix.servlets.controls.page.BookPresentationContext,
                 com.bea.netuix.servlets.controls.window.WindowCapabilities,
                 com.bea.netuix.servlets.manager.AppContext,
                 java.util.List,
                 java.util.Iterator,
                 com.bea.netuix.servlets.controls.window.Edit,
                 com.bea.netuix.servlets.controls.page.Menu,
                 com.bea.netuix.servlets.controls.page.MenuPresentationContext,
                 com.bea.netuix.servlets.controls.window.ToggleButtonPresentationContext"%>
<%@ taglib uri="render.tld" prefix="render" %>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
<%
    // Verify that this is not a .portal file
    AppContext ac = AppContext.getAppContext(request);
    if ( !ac.isDotPortal() )
    {
        DesktopPresentationContext desktopPresentationContext = DesktopPresentationContext.getDesktopPresentationContext(request);
        BookPresentationContext bookPresentationContext = desktopPresentationContext.getBookPresentationContext();

        if (bookPresentationContext != null
            && bookPresentationContext.isCapable(WindowCapabilities.EDIT) )
        {
            boolean foundEdit = false;

            MenuPresentationContext menu = null;
            List children = bookPresentationContext.getChildren();
            for (Iterator iterator = children.iterator(); iterator.hasNext();)
            {
                Object o = (Object) iterator.next();
                if ( o instanceof MenuPresentationContext )
                {
                    menu = (MenuPresentationContext)o;
                    break;
                }
            }
            if ( menu != null )
            {
                List mList = menu.getChildren();
                for (Iterator iterator = mList.iterator(); iterator.hasNext();)
                {
                    Object o = (Object) iterator.next();
                    if ( o instanceof ToggleButtonPresentationContext )
                    {
                        ToggleButtonPresentationContext ctx = (ToggleButtonPresentationContext)o;
                        if ( WindowCapabilities.EDIT.getName().equals(ctx.getName()) )
                        {
                            foundEdit = true;
                            break;
                        }
                    }
                }
            }


            if( foundEdit )
            {
                String mainBookName = bookPresentationContext.getDefinitionLabel();
                if (WindowCapabilities.EDIT.equals(bookPresentationContext.getWindowMode()))
                {
%>
                    <tr>
                        <td align="right" colspan="2" nowrap>
                            <a href='<render:windowUrl windowLabel="<%= mainBookName %>" windowMode="<%=WindowCapabilities.VIEW.getName()%>"/>&_nfls=false'><span class="header-links">Return to Portal</span></a>&nbsp;
                        </td>
                    </tr>
<%
                }
                else
                {
%>
                    <tr>
                        <td align="left" nowrap>
                        &nbsp;
                        </td>
                        <td align="right" nowrap>
<%
                    if (request.getRemoteUser() == null)
                    {
%>
                        &nbsp;
<%
                    }
                    else
                    {
%>
                        &nbsp;<a href='<render:jspContentUrl windowLabel="<%= mainBookName %>" windowMode="<%=WindowCapabilities.EDIT.getName()%>"/>&customizePortal=VisitorToolsMainPage'><span class="header-links">Customize My Portal</span></a>&nbsp;
<%
                    }
%>
                    </td>
                </tr>
<%
                }
            }
        }
    }
%>

</table>
