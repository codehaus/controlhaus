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

<%@ taglib uri="render.tld" prefix="render" %>

<%@ page import="java.util.*" %>

<%@ page import="com.bea.netuix.servlets.controls.application.DesktopPresentationContext" %>
<%@ page import="com.bea.netuix.servlets.controls.PresentationContext" %>
<%@ page import="com.bea.netuix.servlets.controls.page.BookPresentationContext" %>
<%@ page import="com.bea.netuix.servlets.controls.page.PagePresentationContext" %>
<%@ page import="com.bea.netuix.servlets.controls.portlet.PortletPresentationContext" %>
<%@ page import="com.bea.netuix.servlets.controls.window.WindowCapabilities" %>

<%@ page import="com.bea.netuix.application.view.PageView" %>
<%@ page import="com.bea.netuix.application.view.PlaceableView" %>
<%@ page import="com.bea.netuix.application.view.PlaceholderView" %>

<%@ page import="com.bea.netuix.application.identifier.BookInstanceId" %>
<%@ page import="com.bea.netuix.application.identifier.PortletInstanceId" %>
<%@ page import="com.bea.netuix.application.identifier.PlaceableInstanceId" %>

<%@ page import="com.bea.netuix.application.definition.BookDefinition" %>
<%@ page import="com.bea.netuix.application.definition.LayoutDefinition" %>
<%@ page import="com.bea.netuix.application.definition.MenuDefinition" %>
<%@ page import="com.bea.netuix.application.definition.PortletDefinition" %>

<%@ page import="com.bea.jsptools.portal.PortalVisitorConstants" %>
<%@ page import="com.bea.jsptools.portal.PortalVisitorManager" %>

<%@ page import="com.bea.jsptools.portal.placement.BookPlacement" %>
<%@ page import="com.bea.jsptools.portal.placement.PlaceablePlacement" %>
<%@ page import="com.bea.jsptools.portal.placement.PortletPlacement" %>

<%@ page import="com.bea.p13n.management.ApplicationHelper"%>

<%@ page import="com.bea.netuix.application.localization.definition.LocalizationResource" %>

<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%!
    private PagePresentationContext findPageInBook(BookPresentationContext bookPC, String pageIdToFind, HttpServletRequest request)
    {
        PagePresentationContext theFoundPage = null;

        String webAppName1 = ApplicationHelper.getWebAppName(request);

        List bp = bookPC.getEntitledPagePresentationContexts();
        Iterator it = bp.iterator();
        for (int i = 0; it.hasNext(); i++ )
        {
            PresentationContext pc = (PresentationContext)it.next();

            if (pc instanceof BookPresentationContext)
            {
                BookPresentationContext bpc = (BookPresentationContext) pc;
                theFoundPage = findPageInBook(bpc, pageIdToFind, request);
                if (theFoundPage != null)
                {
                    return theFoundPage;
                }
            }
            else if (pc instanceof PagePresentationContext)
            {
                PagePresentationContext ppc = (PagePresentationContext) pc;
                String pageInstanceId = ppc.getInstanceId();
                if (pageInstanceId.equals(pageIdToFind))
                {
                    return ppc;
                }
                else
                {
                    theFoundPage = findPageInPage(ppc, pageIdToFind, request);
                    if (theFoundPage != null)
                    {
                        return theFoundPage;
                    }
                }
                
            }
        }
        return null;
    }

    private PagePresentationContext findPageInPage(PagePresentationContext pagePC, String pageIdToFind, HttpServletRequest request)
    {
        PagePresentationContext theFoundPage = null;

        String webAppName1 = ApplicationHelper.getWebAppName(request);

        List bp = pagePC.getWindowPresentationContexts();
        Iterator it = bp.iterator();
        for (int i = 0; it.hasNext(); i++ )
        {
            PresentationContext pc = (PresentationContext)it.next();

            if (pc instanceof BookPresentationContext)
            {
                BookPresentationContext bpc = (BookPresentationContext) pc;
                theFoundPage = findPageInBook(bpc, pageIdToFind, request);
                if (theFoundPage != null)
                {
                    return theFoundPage;
                }
            }
        }
        return null;
    }
%>

<script language="JavaScript" src="<%=request.getContextPath()%>/visitorTools/dialog.js" type="text/javascript"></script>

<%
if (request.getRemoteUser() == null)
{
%>
    Please login to customize your Portal.
<%
}
else
{
    String webAppName = ApplicationHelper.getWebAppName(request);
    /** path in format of '/portalpath/desktoppath' **/
    String pathInfo = request.getPathInfo();
    String portalPath = pathInfo.substring(1, pathInfo.lastIndexOf("/"));
    String desktopPath = pathInfo.substring(pathInfo.lastIndexOf("/") + 1, pathInfo.length());

    String pageId = null;
    // Gotta check to see if we set a new page id in the backing file, pointing to our
    // possible new instance
    pageId = (String) request.getAttribute(PortalVisitorConstants.EDIT_PAGE);
    if (pageId == null)
    {
        pageId = request.getParameter(PortalVisitorConstants.EDIT_PAGE);
    }
    String pageName = request.getParameter(PortalVisitorConstants.EDIT_PAGE_NAME);

    if (pageName == null)
    {
        pageName = "";
    }
    if (pageId == null)
    {
        pageId = "0";
    }

    String newLayoutHTML = "";
    String layoutLockedString = "";
    int selectedLayoutId = 0;
    int pNum = 0;

    String fixedPageName = pageName.replaceAll("\'", "\\\\u0027");
    fixedPageName = fixedPageName.replaceAll("\"", "\\\\u0022");

    DesktopPresentationContext desktopPresentationContext = DesktopPresentationContext.getDesktopPresentationContext(request);
    BookPresentationContext bookPresentationContext = desktopPresentationContext.getBookPresentationContext();
    String theBook = bookPresentationContext.getLabel();

    ArrayList entitledBooks = new ArrayList();
    HashMap entitledPortlets = new HashMap();
    PagePresentationContext thePagePC = findPageInBook(bookPresentationContext, pageId, request);
    if (thePagePC != null)
    {
        List pp = thePagePC.getWindowPresentationContexts();
        Iterator it = pp.iterator();
        for (int i = 0; it.hasNext(); i++ )
        {
            PresentationContext pc = (PresentationContext)it.next();

            if (pc instanceof BookPresentationContext)
            {
                BookPresentationContext bpc = (BookPresentationContext) pc;
                entitledBooks.add(bpc.getInstanceId());
            }
            else if (pc instanceof PortletPresentationContext)
            {
                PortletPresentationContext ppc = (PortletPresentationContext) pc;
                entitledPortlets.put(ppc.getInstanceId(), ppc);
            }
        }
    }

    PageView pageView = null;
    pageView = PortalVisitorManager.getPageView(pageId, webAppName, portalPath, desktopPath, request.getLocale(), request);
    if (pageView != null)
    {
        String layoutHTMLPath   = pageView.getLayoutView().getHtmlLayoutUri();
        selectedLayoutId = pageView.getLayoutView().getLayoutDefinitionId().getId();

        String layoutHTML = PortalVisitorManager.getLayoutHtml(layoutHTMLPath, webAppName);

        PlaceholderView[] placeholderViews = pageView.getPlaceholderViews();
        int numFullPlaceholders = placeholderViews.length;

        if (layoutHTML != null)
        {
            HashMap placeables            = new HashMap();
            int     startIndex            = 0;
            int     placeholderStartIndex = 0;
            int     placeholderEndIndex   = 0;

            placeholderStartIndex = layoutHTML.indexOf("<placeholder", startIndex);

            while (placeholderStartIndex != -1)
            {
                placeholderEndIndex = layoutHTML.indexOf(">", placeholderStartIndex);
                String tmpPlaceholder = layoutHTML.substring(placeholderStartIndex, placeholderEndIndex);
                String[] vals = tmpPlaceholder.split("\"");
                String tableId = "portlets_" + vals[1];

                String lockedString = "";
                String checkedString = "";

                if (PortalVisitorManager.isPlaceholderLocked(webAppName, portalPath, desktopPath, pageId, vals[1], request))
                {
                    lockedString = "disabled=\"true\" ";
                    checkedString = "checked ";
                    layoutLockedString = " disabled=\"true\"";
                }

                newLayoutHTML = newLayoutHTML + layoutHTML.substring(startIndex, placeholderStartIndex);
                String replacementHTML = "\n";
                replacementHTML = replacementHTML + "   <div class=\"bea-portal-window\" id=\"placeholder" + vals[1] + "\">\n";
                replacementHTML = replacementHTML + "    <input style=\"display:none;\" type=\"checkbox\" " + checkedString + "id=\"lockPlaceholder" + vals[1] + "\" name=\"lockPlaceholder" + vals[1] + "\">";
                replacementHTML = replacementHTML + "     <div class=\"portlet-section-alternate\" style='white-space:nowrap;'>\n";
                replacementHTML = replacementHTML + "        <div " + lockedString + "id=\"portlets" + vals[1] + "\">\n";
                replacementHTML = replacementHTML + "          <table id=\"" + tableId + "\">\n";
                replacementHTML = replacementHTML + "            <tr>\n";
                replacementHTML = replacementHTML + "              <td width=\"99%\">\n";
                replacementHTML = replacementHTML + "                 <select " + lockedString + "id=\"placeholderList" + vals[1] + "\" name=\"placeholderList" + vals[1] + "\" size=\"5\" style=\"width:100%;\" onChange=\"checkRemove('" + vals[1] + "')\">\n";

                for (int x=0; x<numFullPlaceholders; x++)
                {
                    if (placeholderViews[x].getLocation() == Integer.parseInt(vals[1]))
                    {
                        PlaceableView[] portletViews = placeholderViews[x].getPlaceableViews();

                        for (int k=0; k<portletViews.length; k++)
                        {
                                String portletId = "";
                                boolean entitled = false;
                                boolean entitledRemove = false;

                                PlaceableInstanceId pInstanceId = portletViews[k].getPlaceableInstance().getPlaceableInstanceId();
                                if (pInstanceId instanceof PortletInstanceId)
                                {
                                    PortletInstanceId portletInstanceId = (PortletInstanceId) pInstanceId;
                                    if (entitledPortlets.containsKey((String)Integer.toString(portletInstanceId.getId())))
                                    {
                                        entitled = true;
                                        PortletPresentationContext thePPC = (PortletPresentationContext) entitledPortlets.get((String)Integer.toString(portletInstanceId.getId()));
                                        entitledRemove = thePPC.isCapable(WindowCapabilities.DELETE);
                                    }
                                    portletId = PortalVisitorConstants.PORTLET + "_" + Integer.toString(portletInstanceId.getId());
                                }
                                else if (pInstanceId instanceof BookInstanceId)
                                {
                                    BookInstanceId bookInstanceId = (BookInstanceId) pInstanceId;
                                    if (entitledBooks.contains(Integer.toString(bookInstanceId.getId())))
                                    {
                                        entitled = true;
                                        entitledRemove = true;
                                    }
                                    portletId = PortalVisitorConstants.BOOK + "_" + Integer.toString(bookInstanceId.getId());
                                }

                                if (entitled)
                                {
                                    String portletTitle = portletViews[k].getTitle();
                                    if (entitledRemove)
                                    {
                                        replacementHTML = replacementHTML + "                       <option value=\"" + portletId + "\">" + portletTitle;
                                    }
                                    else
                                    {
                                        replacementHTML = replacementHTML + "                       <option value=\"" + portletId + "\" label=\"noop\">" + portletTitle;
                                    }
                                }
                        }
                        break;
                    }
                }

                replacementHTML = replacementHTML + "                </select>\n";
                replacementHTML = replacementHTML + "              </td>\n";
                replacementHTML = replacementHTML + "              <td width=\"1%\">\n";
                replacementHTML = replacementHTML + "                <a href='javascript:moveUp(\"" + vals[1] + "\")'><img border='0' src='/" + webAppName + "/visitorTools/images/up.gif'";
                replacementHTML = replacementHTML + " title='Move the selected item up within this area'></a><br>\n";
                replacementHTML = replacementHTML + "                <a href='javascript:moveDown(\"" + vals[1] + "\")'><img border='0' src='/" + webAppName + "/visitorTools/images/down.gif'";
                replacementHTML = replacementHTML + " title='Move the selected item down within this area'></a>\n";
                replacementHTML = replacementHTML + "              </td>\n";
                replacementHTML = replacementHTML + "            </tr>\n";
                replacementHTML = replacementHTML + "            <tr>\n";
                replacementHTML = replacementHTML + "              <td align='center'>\n";
                replacementHTML = replacementHTML + "                 <a href='javascript:moveLeft(\"" + vals[1] + "\")'><img border='0' src='/" + webAppName + "/visitorTools/images/left.gif'";
                replacementHTML = replacementHTML + " title='Move the selected item to the previous area on the page'></a>\n";
                replacementHTML = replacementHTML + "                 <a href='javascript:moveRight(\"" + vals[1] + "\")'><img border='0' src='/" + webAppName + "/visitorTools/images/right.gif'";
                replacementHTML = replacementHTML + " title='Move the selected item to the next area on the page'></a>\n";
                replacementHTML = replacementHTML + "              </td>\n";
                replacementHTML = replacementHTML + "            </tr>\n";
                replacementHTML = replacementHTML + "            <tr>\n";
                replacementHTML = replacementHTML + "              <td valign=\"top\" colspan=\"2\" align=\"center\">\n";
				if (lockedString.equals("")) 
				{
					replacementHTML = replacementHTML + "                  <input type='button' id='removeButton" + vals[1] + "' value='Remove Item' onClick='removeItem(\"" + vals[1] + "\")'";
				}
				else
				{
					replacementHTML = replacementHTML + "                  <input type='button' id='removeButton" + vals[1] + "' value='Remove Item'";
				}
                replacementHTML = replacementHTML + " title='Remove the selected item from this area and from the page'>\n";
                replacementHTML = replacementHTML + "              </td>\n";
                replacementHTML = replacementHTML + "            </tr>\n";
                replacementHTML = replacementHTML + "            <tr>\n";
                replacementHTML = replacementHTML + "              <td valign=\"top\" colspan=\"2\" align=\"center\">\n";
				if (lockedString.equals("")) 
				{
					replacementHTML = replacementHTML + "                <br><input type='button' value='Add New Item' onClick='addContent(\"" + vals[1] + "\")'";
				}
				else
				{
					replacementHTML = replacementHTML + "                <br><input type='button' value='Add New Item'";
				}
                replacementHTML = replacementHTML + " title='Add a new item to this area of the page'>\n";
                replacementHTML = replacementHTML + "              </td>\n";
                replacementHTML = replacementHTML + "            </tr>\n";
                replacementHTML = replacementHTML + "          </table>\n";
                replacementHTML = replacementHTML + "        </div>\n";
                replacementHTML = replacementHTML + "      </div>\n";
                replacementHTML = replacementHTML + "    </div>\n";
                newLayoutHTML = newLayoutHTML + replacementHTML;
                startIndex = placeholderEndIndex + 1;
                placeholderStartIndex = layoutHTML.indexOf("<placeholder", startIndex);

                pNum++;
            }
            newLayoutHTML = newLayoutHTML + layoutHTML.substring(startIndex, layoutHTML.length());
        }
        else
        {
            newLayoutHTML = "<h2> Error:  No layout file associated with layout </h2>";
        }
    }

    // Generate Portlet Category information
    PortalVisitorManager.generatePortletCategoryArrays(webAppName, "&nbsp;&nbsp;", request.getLocale(), request);
%>
<script language="JavaScript">
<%= PortalVisitorManager.categoryJavaScriptArrays %>
function SetOptions(the_select, text_array, value_array) {
    the_select.options.length = text_array.length;
    for (loop=0; loop < text_array.length; loop++) {
        the_select.options[loop].text = text_array[loop];
        the_select.options[loop].value = value_array[loop];
    }
}
</script>
<div style="padding-left:6px;padding-right:6px;padding-bottom:6px;padding-top:0px;">
  <h2>Portal Configuration - Edit Page</h2>
    <table cellpadding="0" cellspacing="0" border="0" align="left" width="1%">
	<tr>
		<td width="300" valign="top" align="left" nowrap>
            <p>To change the arrangement of rows and columns on this page, choose a Layout from the drop down box and click "Apply Layout".</p>
			<p>To remove Books or Portlets, select their names and then click the "Remove Item" button</p>
			<p>To add Books or Portlets, click the "Add New Item" button, select a resource, and then click the associated "Add" button.</p>
			<p>To change the order in which the resources will be displayed, select a resource and move it using the up/down and left/right arrows.</p>
		</td>
		<td width="1%" valign="top" align="left" nowrap>
			<div class="bea-portal-window">
				<div class="bea-portal-window-titlebar">Editing Page: <%=pageName%></div>
				<div<%=layoutLockedString%>>
					<form method="post" name="UpdateLayoutForm" id="UpdateLayoutForm">
					<table border="0" cellpadding="3" cellspacing="0" width="100%" class="portlet-section-alternate">
						<tr>
							<td valign="middle" align="left" width="1%" nowrap><span style="font-weight:normal;">&nbsp;&nbsp;&nbsp;&nbsp;Layout:</span></td>
							<td valign="middle" align="left" width="1%" nowrap>
								<select name="<%=PortalVisitorConstants.LAYOUT_ID%>" id="<%=PortalVisitorConstants.LAYOUT_ID%>">
<%
							LayoutDefinition[] layoutArray = PortalVisitorManager.getLayoutDefinitions(webAppName, request.getLocale(), request);
					
							if (layoutArray != null)
							{
								for (int i=0; i < layoutArray.length; i++)
								{
									LayoutDefinition tmpLayout = layoutArray[i];
									int layoutId = tmpLayout.getLayoutDefinitionId().getId();
									LocalizationResource lr = tmpLayout.getLocalizationResource();
%>
									<option value="<%=layoutId%>" <% if (layoutId == selectedLayoutId){%>selected<%}%>><%=lr.getTitle()%>
<%
								}
							}
%>
								</select>
							</td>
							<td valign="middle" align="left" width="98%" nowrap>
<%
                            if (layoutLockedString.equals(""))
                            {
%>                            
								<input type="submit" value="Apply Layout">
<%
                            }
                            else
                            {
%>
								<input type="button" value="Apply Layout">
<%
                            }
%>                            
								<input type="hidden" name="<%=PortalVisitorConstants.EDIT_PAGE%>" value="<%=pageId%>"/>
								<input type="hidden" name="<%=PortalVisitorConstants.EDIT_PAGE_NAME%>" value="<%=fixedPageName%>"/>
								<input type="hidden" name="<%=PortalVisitorConstants.WEB_APP_NAME%>" value="<%=webAppName%>"/>
								<input type="hidden" name="<%=PortalVisitorConstants.PORTAL_PATH%>" value="<%=portalPath%>"/>
								<input type="hidden" name="<%=PortalVisitorConstants.DESKTOP_PATH%>" value="<%=desktopPath%>"/>
								<input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_EDIT_PAGE%>"/>
                                <input type="hidden" name="<%=PortalVisitorConstants.MAIN_BOOK%>" value="<%=theBook%>"/>
							</td>
						</tr>
					</table>
					</form>
				</div>
				<div style="padding:20px">
					<%=newLayoutHTML%>
					<br>
					<input type="button" value="Save Changes" onClick="saveChanges()">&nbsp;&nbsp;
					<input type="button" value="Back" onClick="cancelChanges()">
				</div>
			</div>
        </td>
	  </tr>
    </table>
</div>

<div id="AddContentDialog" align="center" class="bea-portal-window" style="position:absolute;visibility:hidden;left:0;top:0;height:0;width:0;z-index:5001">
  <table width='400' height='250' cellpadding="0" cellspacing="12">
    <tr>
      <td valign="top" align="left" nowrap class="bea-portal-window" width="50%">
        <div class="bea-portal-window-titlebar">
			<table>
				<tr>
					<td align="left" valign="middle">Portlets:&nbsp;</td>
<%
                    if (PortalVisitorManager.hasAnyCategories)
                    {
%>
					<td align="right" valign="middle">
						<select name="selectPortletCategory"
                                id="selectPortletCategory"
                                onchange="SetOptions(document.getElementById('allPortlets'), eval(document.getElementById('selectPortletCategory').options[selectedIndex].value), eval(document.getElementById('selectPortletCategory').options[selectedIndex].value + 'Values'))">
							<%= PortalVisitorManager.categoryOptions %>
						</select>
					</td>
<%
                    }
%>
				</tr>
			</table>
        </div>
        <div id="portletSelectList" class="portlet-section-alternate" align="center" style="height:100%;">
            <select name="allPortlets" id="allPortlets" size="10" style="width:80%;">
                <%= PortalVisitorManager.allPortletOptions %>
            </select>
            <br>
            <input type="button" value="Add Portlet" onClick="addPortlet()">
        </div>
      </td>
      <td valign="top" align="left" nowrap class="bea-portal-window" width="50%">
        <div class="bea-portal-window-titlebar">Books:</div>
        <div id="bookSelectList" class="portlet-section-alternate" align="center" style="height:100%;">
            <select name="allBooks" id="allBooks" size="10" style="width:80%;">
<%
            BookDefinition[] books = PortalVisitorManager.getPublicBookDefinitions(webAppName, portalPath, desktopPath, request.getLocale(), request);
            for (int j = 0; j < books.length; j++)
            {
                BookDefinition tmp2 = books[j];
                if (tmp2.isPublic())
                {
                    LocalizationResource lr2 = tmp2.getLocalizationResource();
                    String title2 = "";
                    if (lr2 != null)
                    {
                        title2 = lr2.getTitle();
                    }
                    int id2 = tmp2.getBookDefinitionId().getId();
%>
              <option value="<%=id2%>"><%=title2%>
<%
                }
            }
%>
            </select>
            <br>
            <input type="button" value="Add Book" onClick="addBook()"><br>
            <input type="button" value="Add New Book" onClick="addNewBook()">
        </div>
      </td>
    </tr>
    <tr>
      <td colspan="2" valign="middle" align="center">
          <input type="button" value="Cancel" onClick="toggleDialogBox('AddContentDialog')">
      </td>
    </tr>
  </table>
</div><%-- end Add content dialog div --%>

<div id="AddNewBookDialog" align="center" class="bea-portal-window" style="position:absolute;visibility:hidden;left:0;top:0;height:0;width:0;z-index:5002">
  <table width="100%" cellpadding="0" cellspacing="12">
    <tr>
      <td valign="top" align="left" nowrap class="bea-portal-window" width="100%">
            <div class="bea-portal-window-titlebar">Menu (Tabs)</div>
            <div style="padding:6px;white-space:nowrap;">
                Choose Menu:
                <select name="newBookMenuId" id="newBookMenuId">
                    <option value="-1">No Menu
<%
                MenuDefinition[] menuArray = PortalVisitorManager.getMenuDefinitions(webAppName, request.getLocale(), request);

                if (menuArray != null)
                {
                    for (int i=0; i < menuArray.length; i++)
                    {
                        MenuDefinition tmpMenu = menuArray[i];
                        int menuId = tmpMenu.getMenuDefinitionId().getId();
                        LocalizationResource lr = tmpMenu.getLocalizationResource();
%>
                    <option value="<%=menuId%>"> <%=lr.getTitle()%>
<%
                    }
                }

%>
               </select>
            </div>
      </td>
    </tr>
    <tr>
      <td valign="top" align="left" nowrap class="bea-portal-window" width="100%">
        <div class="bea-portal-window-titlebar">Book Properties:</div>
        <div style="padding:6px;white-space:nowrap;">
            Book Name: <input type="text" size="20" name="newBookName" id="newBookName">
        </div>
      </td>
    </tr>
    <tr>
      <td valign="middle" align="center">
          <input type="button" value="OK" onClick="saveNewBook()">
          <input type="button" value="Cancel" onClick="toggleDialogBox2('AddNewBookDialog')">
      </td>
    </tr>
  </table>
</div><%-- end Add New Book dialog div --%>

    <form method="post" name="CancelChangesForm" id="CancelChangesForm">
        <input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_MAIN_PAGE%>"/>
    </form>

    <form method="post" name="SavePlaceholdersForm" id="SavePlaceholdersForm">
        <input type="hidden" name="<%=PortalVisitorConstants.SAVE_PLACEHOLDERS%>" value="1" />
        <input type="hidden" name="<%=PortalVisitorConstants.EDIT_PAGE%>" value="" />
        <input type="hidden" name="<%=PortalVisitorConstants.MOVE_PLACEABLE_IDS%>" value="" />
        <input type="hidden" name="<%=PortalVisitorConstants.MOVE_PLACEABLE_POSITIONS%>" value="" />
        <input type="hidden" name="<%=PortalVisitorConstants.MOVE_PLACEABLE_PLACEHOLDERS%>" value="" />
        <input type="hidden" name="<%=PortalVisitorConstants.DELETE_PLACEABLE_IDS%>" value="" />
        <input type="hidden" name="<%=PortalVisitorConstants.ADD_PLACEABLE_IDS%>" value="" />
        <input type="hidden" name="<%=PortalVisitorConstants.ADD_PLACEABLE_POSITIONS%>" value="" />
        <input type="hidden" name="<%=PortalVisitorConstants.ADD_PLACEABLE_PLACEHOLDERS%>" value="" />
        <input type="hidden" name="<%=PortalVisitorConstants.WEB_APP_NAME%>" value="<%=webAppName%>"/>
        <input type="hidden" name="<%=PortalVisitorConstants.PORTAL_PATH%>" value="<%=portalPath%>"/>
        <input type="hidden" name="<%=PortalVisitorConstants.DESKTOP_PATH%>" value="<%=desktopPath%>"/>
        <input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_MAIN_PAGE%>"/>
        <input type="hidden" name="<%=PortalVisitorConstants.MAIN_BOOK%>" value="<%=theBook%>"/>
    </form>

    <script language="javascript">

    var numPlaceholders = <%=pNum%>;
    var currentHotPlaceholder = 0;
    var numAddedResources= 0;

    function saveNewBook()
    {
        var theId = "placeholderList" + currentHotPlaceholder;

        var idx = document.getElementById("allBooks").options.selectedIndex;
        var menuId = document.getElementById("newBookMenuId").value;
        var bookName = document.getElementById("newBookName").value;
        var tmpBookName = bookName;
        var regEx = / /gi;
        var finalCheck = tmpBookName.replace(regEx, "");
        if (bookName != "" && finalCheck != "")
        {
            var bookValue = "<%=PortalVisitorConstants.NEW_BOOK%>_new" + numAddedResources + "_" + menuId + ":" + bookName;
            numAddedResources++;
    
            var theLength = document.getElementById(theId).options.length;
            document.getElementById(theId).options[theLength] = new Option(bookName, bookValue);
        }

        toggleDialogBox2('AddNewBookDialog');
        toggleDialogBox('AddContentDialog');
    }

    function removeItem(placeholderNum)
    {
        var theId = "placeholderList" + placeholderNum;
        var idx = document.getElementById(theId).options.selectedIndex;
        if (idx < 0)
        {
            return;
        }
        var checkVal = document.getElementById(theId).options[idx].label;
        if (checkVal == "noop")
        {
            return;
        }
        document.getElementById(theId).options[idx] = null;
    }

    /**
     *  Function to return current state of locked placeholders
    */
    function getLockedState(num)
    {
        var totalPlaceholders = parseInt(num);

        lockedArray = new Array(totalPlaceholders);

        for (i=0; i < totalPlaceholders; i++)
        {
            lockedId = "lockPlaceholder" + i;
            lockedElement = document.getElementById(lockedId);
            if (lockedElement.checked)
            {
                lockedArray[i] = 1;
            }
            else
            {
                lockedArray[i] = 0;
            }
        }

        return lockedArray;
    }

    function moveLeft(placeholderNum)
    {
        var theId = "placeholderList" + placeholderNum;
        var idx = document.getElementById(theId).options.selectedIndex;
        if (idx < 0)
        {
            return;
        }

        var sum = parseInt(placeholderNum) - 1;
        lockCheck = 0;
        lockedState = getLockedState(numPlaceholders);
        while (!lockCheck)
        {
            if (sum < 0)
            {
                sum = numPlaceholders - 1;
            }

            if (lockedState[sum] == 1)
            {
                sum--;
            }
            else
            {
                lockCheck = 1;
            }
        }

        var theNextId = "placeholderList" + sum;

        var idxText = document.getElementById(theId).options[idx].text;
        var idxName = document.getElementById(theId).options[idx].label;
        var idxValue = document.getElementById(theId).options[idx].value;
        document.getElementById(theId).options[idx] = null;
        var nextLength = document.getElementById(theNextId).options.length;
        document.getElementById(theNextId).options[nextLength] = new Option(idxText, idxValue);
        document.getElementById(theNextId).options[nextLength].label = idxName; 
    }

    function moveRight(placeholderNum)
    {
        var theId = "placeholderList" + placeholderNum;
        var idx = document.getElementById(theId).options.selectedIndex;
        if (idx < 0)
        {
            return;
        }

        var sum = parseInt(placeholderNum) + 1;
        lockCheck = 0;
        lockedState = getLockedState(numPlaceholders);
        while (!lockCheck)
        {
            if (sum == numPlaceholders)
            {
                sum = 0;
            }

            if (lockedState[sum] == 1)
            {
                sum++;
            }
            else
            {
                lockCheck = 1;
            }
        }

        var theNextId = "placeholderList" + sum;

        var idxText = document.getElementById(theId).options[idx].text;
        var idxName = document.getElementById(theId).options[idx].label;
        var idxValue = document.getElementById(theId).options[idx].value;
        document.getElementById(theId).options[idx] = null;
        var nextLength = document.getElementById(theNextId).options.length;
        document.getElementById(theNextId).options[nextLength] = new Option(idxText, idxValue);
        document.getElementById(theNextId).options[nextLength].label = idxName; 
    }

    function moveUp(placeholderNum)
    {
        var theId = "placeholderList" + placeholderNum;
        var idx = document.getElementById(theId).options.selectedIndex;
        if (idx < 0)
        {
            return;
        }

        var newIdx = idx - 1;

        if (idx <= 0)
        {
            return;
        }
        var selectedText = document.getElementById(theId).options[newIdx].text;
        var selectedName = document.getElementById(theId).options[newIdx].label;
        var selectedValue = document.getElementById(theId).options[newIdx].value;
        document.getElementById(theId).options[newIdx].text = document.getElementById(theId).options[idx].text;
        document.getElementById(theId).options[newIdx].label = document.getElementById(theId).options[idx].label;
        document.getElementById(theId).options[newIdx].value = document.getElementById(theId).options[idx].value;
        document.getElementById(theId).options[idx].text = selectedText;
        document.getElementById(theId).options[idx].label = selectedName;
        document.getElementById(theId).options[idx].value = selectedValue;
        document.getElementById(theId).options[newIdx].selected = "1";

    }

    function moveDown(placeholderNum)
    {
        var theId = "placeholderList" + placeholderNum;
        var idx = document.getElementById(theId).options.selectedIndex;
        if (idx < 0)
        {
            return;
        }

        var num = document.getElementById(theId).options.length;
        var newIdx = idx + 1;

        if (idx == num - 1)
        {
            return;
        }
        var selectedText = document.getElementById(theId).options[newIdx].text;
        var selectedName = document.getElementById(theId).options[newIdx].label;
        var selectedValue = document.getElementById(theId).options[newIdx].value;
        document.getElementById(theId).options[newIdx].text = document.getElementById(theId).options[idx].text;
        document.getElementById(theId).options[newIdx].label = document.getElementById(theId).options[idx].label;
        document.getElementById(theId).options[newIdx].value = document.getElementById(theId).options[idx].value;
        document.getElementById(theId).options[idx].text = selectedText;
        document.getElementById(theId).options[idx].label = selectedName;
        document.getElementById(theId).options[idx].value = selectedValue;
        document.getElementById(theId).options[newIdx].selected = "1";
    }

    function checkRemove(placeholderNum)
    {
        var theId = "placeholderList" + placeholderNum;
        var idx = document.getElementById(theId).options.selectedIndex;
        if (idx < 0)
        {
            return;
        }
        var checkVal = document.getElementById(theId).options[idx].label;
        buttonString = "removeButton" + placeholderNum;
        if (checkVal == "noop")
        {
            var removeButtonVar = document.getElementById(buttonString);
            removeButtonVar.disabled = 1;
        }
        else
        {
            var removeButtonVar = document.getElementById(buttonString);
            removeButtonVar.disabled = 0;
        }
    }

    function cancelChanges()
    {
        document.CancelChangesForm.submit();
    }

    function addPortlet()
    {
        var theId = "placeholderList" + currentHotPlaceholder;

        var idx = document.getElementById("allPortlets").options.selectedIndex;
        if (idx > -1)
        {
            var portletName = document.getElementById("allPortlets").options[idx].text;
            var portletValue = "<%=PortalVisitorConstants.PORTLET%>_new" + numAddedResources + "_" + document.getElementById("allPortlets").options[idx].value;
            numAddedResources++;
    
            var theLength = document.getElementById(theId).options.length;
            document.getElementById(theId).options[theLength] = new Option(portletName, portletValue);
            toggleDialogBox('AddContentDialog');
        } else
        {
            alert ("Please select a Portlet to add.");
        }
    }

    function addBook()
    {
        var theId = "placeholderList" + currentHotPlaceholder;

        var idx = document.getElementById("allBooks").options.selectedIndex;
        if (idx > -1)
        {
            var bookName = document.getElementById("allBooks").options[idx].text;
            var bookValue = "<%=PortalVisitorConstants.BOOK%>_new" + numAddedResources + "_" + document.getElementById("allBooks").options[idx].value;
            numAddedResources++;
    
            var theLength = document.getElementById(theId).options.length;
            document.getElementById(theId).options[theLength] = new Option(bookName, bookValue);
            toggleDialogBox('AddContentDialog');
        } else
        {
            alert ("Please select a Book to add.");
        }
    }

    function addNewBook()
    {
        toggleDialogBox2("AddNewBookDialog", 230,300,"Add New Book");
    }

    function addContent(placeholderNum)
    {
        currentHotPlaceholder = placeholderNum;
        toggleDialogBox("AddContentDialog",370,430,"Add New Item");
    }

    /**
     *   Function to find all placeables that have changed position and need to be updated
     */
    function findMovedPlaceables(originalState, newState)
    {
        movedPlaceables = new Array(0);

        for (i=0; i<originalState.length; i++)
        {
            for (j=0; j<originalState[i].length; j++)
            {
                for (k=0; k<newState.length; k++)
                {
                    for (l=0; l<newState[k].length; l++)
                    {
                        if (originalState[i][j] == newState[k][l])
                        {
                            if (i != k || j != l)
                            {
                                movedPlaceables.push(new Array(newState[k][l], k, l));
                            }
                            k = newState.length;
                            break;
                        }
                    }
                }
            }
        }

        return movedPlaceables;
    }

    /**
     *   Function to find all placeables that have been added
     */
    function findAddedPlaceables(originalState, newState)
    {
        addedPlaceables = new Array(0);

        for (i=0; i<newState.length; i++)
        {
            for (j=0; j<newState[i].length; j++)
            {
                found = 0;
                for (k=0; k<originalState.length; k++)
                {
                    for (l=0; l<originalState[k].length; l++)
                    {
                        if (newState[i][j] == originalState[k][l])
                        {
                            found = 1;
                            k = newState.length;
                            break;
                        }
                    }
                }
                if (!found)
                {
                    addedPlaceables.push(new Array(newState[i][j], i, j));
                }
            }
        }

        return addedPlaceables;
    }

    /**
     *   Function to find all placeables that have been deleted
     */
    function findDeletedPlaceables(originalState, newState)
    {
        deletedPlaceables = new Array(0);

        for (i=0; i<originalState.length; i++)
        {
            for (j=0; j<originalState[i].length; j++)
            {
                found = 0;
                for (k=0; k<newState.length; k++)
                {
                    for (l=0; l<newState[k].length; l++)
                    {
                        if (originalState[i][j] == newState[k][l])
                        {
                            found = 1;
                            k = newState.length;
                            break;
                        }
                    }
                }
                if (!found)
                {
                    deletedPlaceables.push(originalState[i][j]);
                }
            }
        }

        return deletedPlaceables;
    }

    /**
     *   Function to find all placeables that have changed position and need to be updated
     */
    function findChangedPlaceables(originalState, newState)
    {
        changedPlaceables = new Array(0);

        for (i=0; i<newState.length; i++)
        {
            for (j=0; j<newState[i].length; j++)
            {
                if (newState[i][j] != originalState[i][j])
                {
                    changedPlaceables.push(new Array(newState[i][j], i, j));
                }
            }
        }

        return changedPlaceables;
    }

    function saveChanges()
    {
        var newState = getPlaceableState();
        allMovedPlaceables = findMovedPlaceables(originalPlaceableState, newState);
        allDeletedPlaceables = findDeletedPlaceables(originalPlaceableState, newState);
        allAddedPlaceables = findAddedPlaceables(originalPlaceableState, newState);
        movedPlaceableIds = "";
        movedPlaceablePlaceholders = "";
        movedPlaceablePositions = "";
        deletedPlaceableIds = "";
        addedPlaceableIds = "";
        addedPlaceablePlaceholders = "";
        addedPlaceablePositions = "";

        for (i=0; i<allMovedPlaceables.length; i++)
        {
            movedPlaceableIds = movedPlaceableIds + allMovedPlaceables[i][0] + ",";
            movedPlaceablePlaceholders = movedPlaceablePlaceholders + allMovedPlaceables[i][1] + ",";
            movedPlaceablePositions = movedPlaceablePositions + allMovedPlaceables[i][2] + ",";
        }

        for (j=0; j<allDeletedPlaceables.length; j++)
        {
            deletedPlaceableIds = deletedPlaceableIds + allDeletedPlaceables[j] + ",";
        }

        for (k=0; k<allAddedPlaceables.length; k++)
        {
            addedPlaceableIds = addedPlaceableIds + allAddedPlaceables[k][0] + ",";
            addedPlaceablePlaceholders = addedPlaceablePlaceholders + allAddedPlaceables[k][1] + ",";
            addedPlaceablePositions = addedPlaceablePositions + allAddedPlaceables[k][2] + ",";
        }

        if (allMovedPlaceables.length < 1 && allDeletedPlaceables.length < 1 && allAddedPlaceables.length < 1)
        {
            alert("Nothing to save, layout hasn't changed");
        }
        else
        {
            document.SavePlaceholdersForm.<%=PortalVisitorConstants.EDIT_PAGE%>.value = "<%=pageId%>";
            document.SavePlaceholdersForm.<%=PortalVisitorConstants.MOVE_PLACEABLE_IDS%>.value = movedPlaceableIds;
            document.SavePlaceholdersForm.<%=PortalVisitorConstants.MOVE_PLACEABLE_PLACEHOLDERS%>.value = movedPlaceablePlaceholders;
            document.SavePlaceholdersForm.<%=PortalVisitorConstants.MOVE_PLACEABLE_POSITIONS%>.value = movedPlaceablePositions;
            document.SavePlaceholdersForm.<%=PortalVisitorConstants.DELETE_PLACEABLE_IDS%>.value = deletedPlaceableIds;
            document.SavePlaceholdersForm.<%=PortalVisitorConstants.ADD_PLACEABLE_IDS%>.value = addedPlaceableIds;
            document.SavePlaceholdersForm.<%=PortalVisitorConstants.ADD_PLACEABLE_PLACEHOLDERS%>.value = addedPlaceablePlaceholders;
            document.SavePlaceholdersForm.<%=PortalVisitorConstants.ADD_PLACEABLE_POSITIONS%>.value = addedPlaceablePositions;
            document.SavePlaceholdersForm.submit();
        }
    }

    /**
    *   Function to return current state of placeables
    */
    function getPlaceableState()
    {
        placeableArray = new Array(numPlaceholders);

        for (i=0; i < numPlaceholders; i++)
        {
            var theId = "placeholderList" + i;
            var theLength = document.getElementById(theId).options.length;

            placeableArray[i] = new Array(theLength);

            for (j=0; j<theLength; j++)
            {
                placeableArray[i][j] = document.getElementById(theId).options[j].value;
            }
        }

        return placeableArray;
    }

    var originalPlaceableState = getPlaceableState();

    </script>

<div id="dialogBackgroundDiv" style="position: absolute;visibility: hidden;left: 0;top: 0;height: 0;width: 0;background-color: #000000;z-index: 5000;filter: alpha(opacity=75);-moz-opacity:0.75;">
  &nbsp;
</div>

<%
// end of if/else whether user is logged in or not...
}
%>
