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

<%@ page import="com.bea.netuix.application.view.BookView" %>
<%@ page import="com.bea.netuix.application.view.MenuView" %>
<%@ page import="com.bea.netuix.application.view.NavigableView" %>

<%@ page import="com.bea.netuix.application.identifier.BookInstanceId" %>
<%@ page import="com.bea.netuix.application.identifier.MenuDefinitionId" %>
<%@ page import="com.bea.netuix.application.identifier.NavigableInstanceId" %>
<%@ page import="com.bea.netuix.application.identifier.PageInstanceId" %>

<%@ page import="com.bea.netuix.application.definition.BookDefinition" %>
<%@ page import="com.bea.netuix.application.definition.LayoutDefinition" %>
<%@ page import="com.bea.netuix.application.definition.MenuDefinition" %>
<%@ page import="com.bea.netuix.application.definition.PageDefinition" %>

<%@ page import="com.bea.jsptools.portal.PortalVisitorConstants" %>
<%@ page import="com.bea.jsptools.portal.PortalVisitorManager" %>

<%@ page import="com.bea.jsptools.portal.placement.BookPlacement" %>
<%@ page import="com.bea.jsptools.portal.placement.NavigablePlacement" %>
<%@ page import="com.bea.jsptools.portal.placement.PagePlacement" %>

<%@ page import="com.bea.p13n.management.ApplicationHelper"%>

<%@ page import="com.bea.netuix.application.localization.definition.LocalizationResource" %>

<%@ page import="javax.servlet.http.HttpServletRequest" %>

<%!
    private BookPresentationContext findBookInBook(BookPresentationContext bookPC, String bookIdToFind, HttpServletRequest request)
    {
        BookPresentationContext theFoundBook = null;

        String webAppName1 = ApplicationHelper.getWebAppName(request);

        List bp = bookPC.getEntitledPagePresentationContexts();
        Iterator it = bp.iterator();
        for (int i = 0; it.hasNext(); i++ )
        {
            PresentationContext pc = (PresentationContext)it.next();

            if (pc instanceof BookPresentationContext)
            {
                BookPresentationContext bpc = (BookPresentationContext) pc;
                String bookInstanceId = bpc.getInstanceId();
                if (bookInstanceId.equals(bookIdToFind))
                {
                    return bpc;
                }
                else
                {
                    theFoundBook = findBookInBook(bpc, bookIdToFind, request);
                    if (theFoundBook != null)
                    {
                        return theFoundBook;
                    }
                }
            }
            else if (pc instanceof PagePresentationContext)
            {
                PagePresentationContext ppc = (PagePresentationContext) pc;
                String pageInstanceId = ppc.getInstanceId();
                theFoundBook = findBookInPage(ppc, bookIdToFind, request);
                if (theFoundBook != null)
                {
                    return theFoundBook;
                }
            }
        }

        return null;

    }

    private BookPresentationContext findBookInPage(PagePresentationContext pagePC, String bookIdToFind, HttpServletRequest request)
    {
        BookPresentationContext theFoundBook = null;

        String webAppName1 = ApplicationHelper.getWebAppName(request);

        String parentPageInstanceId = pagePC.getInstanceId();

        List pp = pagePC.getWindowPresentationContexts();
        Iterator it = pp.iterator();
        for (int i = 0; it.hasNext(); i++ )
        {
            PresentationContext pc = (PresentationContext)it.next();

            if (pc instanceof BookPresentationContext)
            {
                BookPresentationContext bpc = (BookPresentationContext) pc;
                String bookInstanceId = bpc.getInstanceId();

                if (bookInstanceId.equals(bookIdToFind))
                {
                    return bpc;
                }
                else
                {
                    theFoundBook = findBookInBook(bpc, bookIdToFind, request);
                    if (theFoundBook != null)
                    {
                        return theFoundBook;
                    }
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

    String bookId = null;
    // Gotta check to see if we set a new book id in the backing file, pointing to our 
    // possible new instance
    bookId = (String) request.getAttribute(PortalVisitorConstants.EDIT_BOOK);
    if (bookId == null)
    {
        bookId = request.getParameter(PortalVisitorConstants.EDIT_BOOK);
    }
    if (bookId == null)
    {
        bookId = "0";
    }

    String bookName = request.getParameter(PortalVisitorConstants.EDIT_BOOK_NAME);
    if (bookName == null)
    {
        bookName = "";
    }

    String fixedBookName = bookName.replaceAll("\'", "\\\\u0027");
    fixedBookName = fixedBookName.replaceAll("\"", "\\\\u0022");

    DesktopPresentationContext desktopPresentationContext = DesktopPresentationContext.getDesktopPresentationContext(request);
    BookPresentationContext bookPresentationContext = desktopPresentationContext.getBookPresentationContext();
    String theBook = bookPresentationContext.getLabel();

    BookPresentationContext theBookPC = null;
    String primaryBookId = bookPresentationContext.getInstanceId();
    if (primaryBookId.equals(bookId))
    {
        theBookPC = bookPresentationContext; 
    }
    else
    {
        theBookPC = findBookInBook(bookPresentationContext, bookId, request);
    }

    if (theBookPC != null)
    {
%>
<div style="padding-left:6px;padding-right:6px;padding-bottom:6px;padding-top:0px;">
  <h2>Portal Configuration - Edit Book</h2>
    <table cellpadding="3" cellspacing="0" border="0" width="1%" align="left">
        <tr>
            <td width="300" valign="top" align="left" nowrap>
               <p>To move Book items use the "Remove Item" or "Add Item" buttons.</p>
            </td>            
            <td width="1%" valign="top" align="left" nowrap>
                <div class="bea-portal-window">
                    <div class="bea-portal-window-titlebar">Menu (Tabs)</div>
                    <div style="padding:6px;white-space:nowrap;">
                        <form method="post" id="UpdateMenuForm" name="UpdateMenuForm">
                        Choose Menu:<br>
                            <select name="<%=PortalVisitorConstants.MENU_ID%>" id="<%=PortalVisitorConstants.MENU_ID%>">
                                <option value="-1">No Menu
<%
                int selectedMenuId = -1;

                BookView bookView = null;
                bookView = PortalVisitorManager.getBookView(bookId, webAppName, portalPath, desktopPath, request.getLocale(), request);

                MenuView mv = bookView.getMenuView();
                if (mv != null)
                {
                    MenuDefinitionId mdi = mv.getMenuDefinitionId();
                    if (mdi != null)
                    {
                        selectedMenuId = mdi.getId();
                    }
                }

                MenuDefinition[] menuArray = PortalVisitorManager.getMenuDefinitions(webAppName, request.getLocale(), request);

                if (menuArray != null)
                {
                    for (int i=0; i < menuArray.length; i++)
                    {
                        MenuDefinition tmpMenu = menuArray[i];
                        int menuId = tmpMenu.getMenuDefinitionId().getId();
                        LocalizationResource lr = tmpMenu.getLocalizationResource();
%>
                                <option value="<%=menuId%>" <% if (menuId == selectedMenuId){%>selected<%}%>><%=lr.getTitle()%>
<%
                    }
                }

%>
                            </select>&nbsp;<input type="submit" value="Apply Menu">
                            <input type="hidden" name="<%=PortalVisitorConstants.EDIT_BOOK%>" value="<%=bookId%>"/>
                            <input type="hidden" name="<%=PortalVisitorConstants.EDIT_BOOK_NAME%>" value="<%=fixedBookName%>"/>
                            <input type="hidden" name="<%=PortalVisitorConstants.WEB_APP_NAME%>" value="<%=webAppName%>"/>
                            <input type="hidden" name="<%=PortalVisitorConstants.PORTAL_PATH%>" value="<%=portalPath%>"/>
                            <input type="hidden" name="<%=PortalVisitorConstants.DESKTOP_PATH%>" value="<%=desktopPath%>"/>
                            <input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_EDIT_BOOK%>"/>
                            <input type="hidden" name="<%=PortalVisitorConstants.MAIN_BOOK%>" value="<%=theBook%>"/>
                        </form>
                    </div>
                </div>
            </td>
		</tr>		
		<tr>
		    <td width="300" valign="top" align="left" nowrap>
		       <p>To change the order in which the Pages and Books will be displayed, select a resource and move it using the "Move up" and or "Move Down" arrows.</p>
               <p>Determine Menu selections by using the "Choose Menu" drop down box and selecting "Apply Menu".</p>
			</td>
            <td width="1%" valign="top" align="left" nowrap>
                <div class="bea-portal-window">
                    <div class="bea-portal-window-titlebar">Book Items</div>
                    <div style="padding:20px">
                        <div class="bea-portal-window">
                            <table width="100%" class="portlet-section-alternate">
                                <tr>
                                    <td width="99%" valign="top" align="center" nowrap>
                                        <select name="allNavs" id="allNavs" size="6" style="width:100%;">
<%
                                        List bp = theBookPC.getEntitledPagePresentationContexts();
                                        Iterator it = bp.iterator();
                                        for (int i = 0; it.hasNext(); i++ )
                                        {
                                            String navId = "";
                                            String title = "";
                                            PresentationContext pc = (PresentationContext)it.next();

                                            if (pc instanceof BookPresentationContext)
                                            {
                                                BookPresentationContext bpc = (BookPresentationContext) pc;
                                                String bookInstanceId = bpc.getInstanceId();
                                                title = bpc.getTitle();

                                                navId = PortalVisitorConstants.BOOK + "_" + bookInstanceId;
                                            }
                                            else if (pc instanceof PagePresentationContext)
                                            {
                                                PagePresentationContext ppc = (PagePresentationContext) pc;
                                                String pageInstanceId = ppc.getInstanceId();
                                                title = ppc.getTitle();
                
                                                navId = PortalVisitorConstants.PAGE + "_" + pageInstanceId;
                                            }
%>
                                            <option value="<%=navId%>"><%=title%>
<%
                                        }
%>
                                        </select>
                                    </td>
                                    <td width="1%" valign="middle" align="center" nowrap>
                                        <a href="javascript:moveNavUp()"><img border="0" src='/<%=webAppName%>/visitorTools/images/up.gif' alt="Move Up"></a><br>
                                        <a href="javascript:moveNavDown()"><img border="0" src='/<%=webAppName%>/visitorTools/images/down.gif' alt="Move Down"></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="99%" valign="top" align="center" nowrap>
                                        <input type="button" value="Remove Item" title="Remove the selected item from the Book" onClick="removeNav()">
                                        <br>
                                        <input type="button" value="Add Item" title="Add an item to the Book" onClick="addContentToBook()">
                                        <br>&nbsp;<br>
                                        <input type="button" value="Add New Book" title="Add a new Book to this Book" onClick="addNewBookToBook()">
                                        <br>
                                        <input type="button" value="Add New Page" title="Add a new Page to this Book" onClick="addNewPageToBook()">
                                    </td>
                                    <td width="1%" valign="middle" align="center" nowrap>&nbsp;

                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div align="center">
                            <br>
                            <input type="button" value="Save Changes" onClick="saveNavChanges()">&nbsp;&nbsp;
                            <input type="button" value="Back" onClick="cancelNavChanges()">
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>

<div id="AddContentToBookDialog" align="center" class="bea-portal-window" style="position:absolute;visibility:hidden;left:0;top:0;height:0;width:0;z-index:5001">
  <table width='400' height='250' cellpadding="0" cellspacing="12">
    <tr>
      <td valign="top" align="left" nowrap class="bea-portal-window" width="50%">
        <div class="bea-portal-window-titlebar">Pages:</div>
        <div id="pageSelectList" class="portlet-section-alternate" align="center" style="height:100%;">
            <select name="allPages" id="allPages" size="10" style="width:80%;">
<%
            PageDefinition[] pages = PortalVisitorManager.getPublicPageDefinitions(webAppName, portalPath, desktopPath, request.getLocale(), request);
            for (int i = 0; i < pages.length; i++)
            {
                PageDefinition tmp = pages[i];
                if (tmp.isPublic())
                {
                    LocalizationResource lr = tmp.getLocalizationResource();
                    String title = "";
                    if (lr != null)
                    {
                        title = lr.getTitle();
                    }
                    int id = tmp.getPageDefinitionId().getId();
%>
              <option value="<%=id%>"><%=title%>
<%
                }
            }
%>
            </select>
            <br>
            <input type="button" value="Add Page" onClick="addPageToBook()">
        </div>
      </td>
      <td valign="top" align="left" nowrap class="bea-portal-window" width="50%">
        <div class="bea-portal-window-titlebar">Books:</div>
        <div id="bookSelectList" class="portlet-section-alternate" align="center" style="height:100%;">
            <select name="allBookBooks" id="allBookBooks" size="10" style="width:80%;">
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
            <input type="button" value="Add Book" onClick="addBookToBook()"><br>
        </div>
      </td>
    </tr>
    <tr>
      <td colspan="2" valign="middle" align="center">
          <input type="button" value="Cancel" onClick="toggleDialogBox('AddContentToBookDialog')">
      </td>
    </tr>
  </table>
</div><%-- end Add content to book dialog div --%>

<div id="AddNewBookToBookDialog" align="center" class="bea-portal-window" style="position:absolute;visibility:hidden;left:0;top:0;height:0;width:0;z-index:5002">
  <table width="100%" cellpadding="0" cellspacing="12">
    <tr>
      <td valign="top" align="left" nowrap class="bea-portal-window" width="100%">
            <div class="bea-portal-window-titlebar">Menu (Tabs)</div>
            <div style="padding:6px;white-space:nowrap;">
                Choose Menu:
                <select name="newBookMenuId" id="newBookMenuId">
                    <option value="-1">No Menu
<%
                MenuDefinition[] menuArray2 = PortalVisitorManager.getMenuDefinitions(webAppName, request.getLocale(), request);

                if (menuArray2 != null)
                {
                    for (int i=0; i < menuArray2.length; i++)
                    {
                        MenuDefinition tmpMenu = menuArray2[i];
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
          <input type="button" value="OK" onClick="saveNewBookInBook()">
          <input type="button" value="Cancel" onClick="toggleDialogBox('AddNewBookToBookDialog')">
      </td>
    </tr>
  </table>
</div><%-- end Add New Book dialog div --%>

<div id="AddNewPageToBookDialog" align="center" class="bea-portal-window" style="position:absolute;visibility:hidden;left:0;top:0;height:0;width:0;z-index:5002">
  <table width="100%" cellpadding="0" cellspacing="12">
    <tr>
      <td valign="top" align="left" nowrap class="bea-portal-window" width="100%">
            <div class="bea-portal-window-titlebar">Layout</div>
            <div style="padding:6px;white-space:nowrap;">
                Choose Layout:
                <select name="newPageLayoutId" id="newPageLayoutId">
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
					<option value="<%=layoutId%>"><%=lr.getTitle()%></OPTION>
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
        <div class="bea-portal-window-titlebar">Page Properties</div>
        <div style="padding:6px;white-space:nowrap;">
            Page Name: <input type="text" size="20" name="newPageName" id="newPageName">
        </div>
      </td>
    </tr>
    <tr>
      <td valign="middle" align="center">
          <input type="button" value="OK" onClick="saveNewPageInBook()">
          <input type="button" value="Cancel" onClick="toggleDialogBox('AddNewPageToBookDialog')">
      </td>
    </tr>
  </table>
</div><%-- end Add New Page dialog div --%>

<form method="post" name="CancelNavChangesForm" id="CancelNavChangesForm">
    <input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_MAIN_PAGE%>"/>
</form>

<form method="post" name="SaveNavigablesForm" id="SaveNavigablesForm">
    <input type="hidden" name="<%=PortalVisitorConstants.SAVE_BOOK%>" value="1" />
    <input type="hidden" name="<%=PortalVisitorConstants.EDIT_BOOK%>" value="" />
    <input type="hidden" name="<%=PortalVisitorConstants.EDIT_BOOK_NAME%>" value="" />
    <input type="hidden" name="<%=PortalVisitorConstants.MOVE_NAVIGABLE_IDS%>" value="" />
    <input type="hidden" name="<%=PortalVisitorConstants.MOVE_NAVIGABLE_POSITIONS%>" value="" />
    <input type="hidden" name="<%=PortalVisitorConstants.DELETE_NAVIGABLE_IDS%>" value="" />
    <input type="hidden" name="<%=PortalVisitorConstants.ADD_NAVIGABLE_IDS%>" value="" />
    <input type="hidden" name="<%=PortalVisitorConstants.ADD_NAVIGABLE_POSITIONS%>" value="" />
    <input type="hidden" name="<%=PortalVisitorConstants.WEB_APP_NAME%>" value="<%=webAppName%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.PORTAL_PATH%>" value="<%=portalPath%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.DESKTOP_PATH%>" value="<%=desktopPath%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_MAIN_PAGE%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.MAIN_BOOK%>" value="<%=theBook%>"/>
</form>

<script language="javascript">

    var numAddedResources = 0;

    /**
     *   Function to find all navigables that have changed position and need to be updated
     */
    function findMovedNavigables(originalState, newState)
    {
        movedNavigables = new Array(0);

        for (i=0; i<originalState.length; i++)
        {
            for (j=0; j<newState.length; j++)
            {
                if (originalState[i] == newState[j])
                {
                    if (i != j)
                    {
                        movedNavigables.push(new Array(newState[j], j));
                    }
                    break;
                }
            }
        }

        return movedNavigables;
    }

    /**
     *   Function to find all navigables that have been deleted
     */
    function findDeletedNavigables(originalState, newState)
    {
        deletedNavigables = new Array(0);

        for (i=0; i<originalState.length; i++)
        {
            found = 0;
            for (j=0; j<newState.length; j++)
            {
                if (originalState[i] == newState[j])
                {
                    found = 1;
                    break;
                }
            }
            if (!found)
            {
                deletedNavigables.push(originalState[i]);
            }
        }

        return deletedNavigables;
    }

    /**
     *   Function to find all navigables that have been added
     */
    function findAddedNavigables(originalState, newState)
    {
        addedNavigables = new Array(0);

        for (i=0; i<newState.length; i++)
        {
            found = 0;
            for (j=0; j<originalState.length; j++)
            {
                if (newState[i] == originalState[j])
                {
                    found = 1;
                    break;
                }
            }
            if (!found)
            {
                addedNavigables.push(new Array(newState[i], i));
            }
        }

        return addedNavigables;
    }

    function saveNavChanges()
    {
        var newState = getNavigableState();
        allMovedNavigables = findMovedNavigables(originalNavigableState, newState);
        allDeletedNavigables = findDeletedNavigables(originalNavigableState, newState);
        allAddedNavigables = findAddedNavigables(originalNavigableState, newState);
        movedNavigableIds = "";
        movedNavigablePositions = "";
        deletedNavigableIds = "";
        addedNavigableIds = "";
        addedNavigablePositions = "";

        for (i=0; i<allMovedNavigables.length; i++)
        {
            movedNavigableIds = movedNavigableIds + allMovedNavigables[i][0] + ",";
            movedNavigablePositions = movedNavigablePositions + allMovedNavigables[i][1] + ",";
        }

        for (j=0; j<allDeletedNavigables.length; j++)
        {
            deletedNavigableIds = deletedNavigableIds + allDeletedNavigables[j] + ",";
        }

        for (k=0; k<allAddedNavigables.length; k++)
        {
            addedNavigableIds = addedNavigableIds + allAddedNavigables[k][0] + ",";
            addedNavigablePositions = addedNavigablePositions + allAddedNavigables[k][1] + ",";
        }

        if (allMovedNavigables.length < 1 && allDeletedNavigables.length < 1 && allAddedNavigables.length < 1)
        {
            alert("Nothing to save, book hasn't changed");
        }
        else
        {
            document.SaveNavigablesForm.<%=PortalVisitorConstants.EDIT_BOOK%>.value = "<%=bookId%>";
            document.SaveNavigablesForm.<%=PortalVisitorConstants.EDIT_BOOK_NAME%>.value = "<%=fixedBookName%>";
            document.SaveNavigablesForm.<%=PortalVisitorConstants.MOVE_NAVIGABLE_IDS%>.value = movedNavigableIds;
            document.SaveNavigablesForm.<%=PortalVisitorConstants.MOVE_NAVIGABLE_POSITIONS%>.value = movedNavigablePositions;
            document.SaveNavigablesForm.<%=PortalVisitorConstants.DELETE_NAVIGABLE_IDS%>.value = deletedNavigableIds;
            document.SaveNavigablesForm.<%=PortalVisitorConstants.ADD_NAVIGABLE_IDS%>.value = addedNavigableIds;
            document.SaveNavigablesForm.<%=PortalVisitorConstants.ADD_NAVIGABLE_POSITIONS%>.value = addedNavigablePositions;
            document.SaveNavigablesForm.submit();
        }
    }

    function cancelNavChanges()
    {
        document.CancelNavChangesForm.submit();
    }

    function saveNewPageInBook()
    {
        var layoutId = document.getElementById("newPageLayoutId").value;
        var pageName = document.getElementById("newPageName").value;

		if (pageName != "")
	    {
		   var pageValue = "<%=PortalVisitorConstants.NEW_PAGE%>_new" + numAddedResources + "_" + layoutId + ":" + pageName;
		   numAddedResources++;

		   var theLength = document.getElementById("allNavs").options.length;
		   document.getElementById("allNavs").options[theLength] = new Option(pageName, pageValue);
		}

        toggleDialogBox('AddNewPageToBookDialog');
    }

    function saveNewBookInBook()
    {
        var menuId = document.getElementById("newBookMenuId").value;
        var bookName = document.getElementById("newBookName").value;
		if (bookName != "")
	    {
		   var bookValue = "<%=PortalVisitorConstants.NEW_BOOK%>_new" + numAddedResources + "_" + menuId + ":" + bookName;
		   numAddedResources++;

		   var theLength = document.getElementById("allNavs").options.length;
		   document.getElementById("allNavs").options[theLength] = new Option(bookName, bookValue);
		}

        toggleDialogBox('AddNewBookToBookDialog');
    }

    function moveNavUp()
    {
        var idx = document.getElementById("allNavs").options.selectedIndex;
        if (idx < 0)
        {
            return;
        }
        var newIdx = idx - 1;

        if (idx <= 0)
        {
            return;
        }
        var selectedName = document.getElementById("allNavs").options[newIdx].text;
        var selectedValue = document.getElementById("allNavs").options[newIdx].value;
        document.getElementById("allNavs").options[newIdx].text = document.getElementById("allNavs").options[idx].text;
        document.getElementById("allNavs").options[newIdx].value = document.getElementById("allNavs").options[idx].value;
        document.getElementById("allNavs").options[idx].text = selectedName;
        document.getElementById("allNavs").options[idx].value = selectedValue;
        document.getElementById("allNavs").options[newIdx].selected = "1";

    }

    function moveNavDown()
    {
        var idx = document.getElementById("allNavs").options.selectedIndex;
        if (idx < 0)
        {
            return;
        }
        var num = document.getElementById("allNavs").options.length;
        var newIdx = idx + 1;

        if (idx == num - 1)
        {
            return;
        }
        var selectedName = document.getElementById("allNavs").options[newIdx].text;
        var selectedValue = document.getElementById("allNavs").options[newIdx].value;
        document.getElementById("allNavs").options[newIdx].text = document.getElementById("allNavs").options[idx].text;
        document.getElementById("allNavs").options[newIdx].value = document.getElementById("allNavs").options[idx].value;
        document.getElementById("allNavs").options[idx].text = selectedName;
        document.getElementById("allNavs").options[idx].value = selectedValue;
        document.getElementById("allNavs").options[newIdx].selected = "1";
    }

    function removeNav()
    {
        var idx = document.getElementById("allNavs").options.selectedIndex;
        if (idx < 0)
        {
            return;
        }
        document.getElementById("allNavs").options[idx] = null;
    }

    function addPageToBook()
    {
        var idx = document.getElementById("allPages").options.selectedIndex;
        var pageName = document.getElementById("allPages").options[idx].text;
        var pageValue = "<%=PortalVisitorConstants.PAGE%>_new" + numAddedResources + "_" + document.getElementById("allPages").options[idx].value;
        numAddedResources++;

        var theLength = document.getElementById("allNavs").options.length;
        document.getElementById("allNavs").options[theLength] = new Option(pageName, pageValue);
        toggleDialogBox('AddContentToBookDialog');
    }

    function addBookToBook()
    {
        var idx = document.getElementById("allBookBooks").options.selectedIndex;
        var bookName = document.getElementById("allBookBooks").options[idx].text;
        var bookValue = "<%=PortalVisitorConstants.BOOK%>_new" + numAddedResources + "_" + document.getElementById("allBookBooks").options[idx].value;
        numAddedResources++;

        var theLength = document.getElementById("allNavs").options.length;
        document.getElementById("allNavs").options[theLength] = new Option(bookName, bookValue);
        toggleDialogBox('AddContentToBookDialog');
    }

    function addContentToBook()
    {
        toggleDialogBox("AddContentToBookDialog",370,400,"Available Resources");
    }

    function addNewPageToBook()
    {
        toggleDialogBox("AddNewPageToBookDialog",240,300,"Add New Page");
    }

    function addNewBookToBook()
    {
        toggleDialogBox("AddNewBookToBookDialog",240,300,"Add New Book");
    }

    /**
    *   Function to return current state of navigables
    */
    function getNavigableState()
    {
        var theLength = document.getElementById("allNavs").options.length;

        navigableArray = new Array(theLength);

        for (i=0; i<theLength; i++)
        {
            navigableArray[i] = document.getElementById("allNavs").options[i].value;
        }

        return navigableArray;
    }

    var originalNavigableState = getNavigableState();

</script>

<div id="dialogBackgroundDiv" style="position: absolute;visibility: hidden;left: 0;top: 0;height: 0;width: 0;background-color: #000000;z-index: 5000;filter: alpha(opacity=75);-moz-opacity:0.75;">
  &nbsp;
</div>

<%
// end of if/else for whether Book Presentation Context was found...
}
%>

<%
// end of if/else for whether user is logged in or not...
}
%>
