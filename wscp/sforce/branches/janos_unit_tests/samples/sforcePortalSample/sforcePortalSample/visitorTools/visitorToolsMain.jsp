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

<%@ page import="com.bea.netuix.application.instance.BookInstance" %>
<%@ page import="com.bea.netuix.application.instance.PageInstance" %>
<%@ page import="com.bea.netuix.application.instance.PortletInstance" %>

<%@ page import="com.bea.netuix.application.view.BookView" %>
<%@ page import="com.bea.netuix.application.view.DesktopView" %>
<%@ page import="com.bea.netuix.application.view.NavigableView" %>
<%@ page import="com.bea.netuix.application.view.PageView" %>
<%@ page import="com.bea.netuix.application.view.PlaceableView" %>
<%@ page import="com.bea.netuix.application.view.PortletView" %>

<%@ page import="com.bea.netuix.application.identifier.ThemeDefinitionId" %>

<%@ page import="com.bea.netuix.application.definition.BookDefinition" %>
<%@ page import="com.bea.netuix.application.definition.LookAndFeelDefinition" %>
<%@ page import="com.bea.netuix.application.definition.PageDefinition" %>
<%@ page import="com.bea.netuix.application.definition.ThemeDefinition" %>

<%@ page import="com.bea.jsptools.portal.PortalVisitorConstants" %>
<%@ page import="com.bea.jsptools.portal.PortalVisitorManager" %>

<%@ page import="com.bea.jsptools.portal.placement.BookPlacement" %>
<%@ page import="com.bea.jsptools.portal.placement.NavigablePlacement" %>
<%@ page import="com.bea.jsptools.portal.placement.PagePlacement" %>
<%@ page import="com.bea.jsptools.portal.placement.PlaceablePlacement" %>
<%@ page import="com.bea.jsptools.portal.placement.PortletPlacement" %>

<%@ page import="com.bea.p13n.management.ApplicationHelper"%>

<%@ page import="com.bea.netuix.application.localization.definition.LocalizationResource" %>

<%@ page import="javax.servlet.http.HttpServletRequest" %>

<script language="JavaScript" src="<%=request.getContextPath()%>/visitorTools/dialog.js" type="text/javascript"></script>

<%!
    private int pageCount = 0;
    private int bookCount = 0;
    private int portletCount = 0;

    private String getBookChildren(BookPresentationContext bookPC, HttpServletRequest request)
    {
        String webAppName1 = ApplicationHelper.getWebAppName(request);
        String returnHtml = "";

        String parentBookInstanceId = bookPC.getInstanceId();

        List bp = bookPC.getEntitledPagePresentationContexts();
        Iterator it = bp.iterator();
        for (int i = 0; it.hasNext(); i++ )
        {
            PresentationContext pc = (PresentationContext)it.next();

            if (pc instanceof BookPresentationContext)
            {
                BookPresentationContext bpc = (BookPresentationContext) pc;
                String bookInstanceId = bpc.getInstanceId();

                String nextBookTitle = bpc.getTitle();

                String fixedBookTitle = nextBookTitle.replaceAll("\'", "\\\\u0027");
                fixedBookTitle = fixedBookTitle.replaceAll("\"", "\\\\u0022");

                BookInstance bookInstance = PortalVisitorManager.getBookInstance(bookInstanceId, request.getLocale(), request);
                int bookThemeId = -1;
                ThemeDefinitionId tdi = bookInstance.getThemeDefinitionId();
                if (tdi != null)
                {
                    bookThemeId = tdi.getId();
                }

                returnHtml = returnHtml + "  <div class=\"bookchild\" style=\"display:none;position:relative\">\n";
                returnHtml = returnHtml + "    <blockquote style='margin-left:21px;margin-right:0px;margin-top:0px;margin-bottom:0px'>\n";
                returnHtml = returnHtml + "      <div style=\"display:block;\" id=\"book" + String.valueOf(bookCount) + "On\">\n";
                returnHtml = returnHtml + "        <img src=\"/" + webAppName1 + "/visitorTools/images/book-16.gif\"><a href='javascript:selectBook(\"" + bookInstanceId + "\", \"" + parentBookInstanceId + "\", \"book\", \"" + bookThemeId + "\", \"" + fixedBookTitle + "\", \"" + String.valueOf(bookCount) + "\")'>" + nextBookTitle + "</a>\n";
                returnHtml = returnHtml + "      </div>\n";
                returnHtml = returnHtml + "      <div style=\"display:none;color:#999999;\" id=\"book" + String.valueOf(bookCount++) + "Off\">\n";
                returnHtml = returnHtml + "        <img src=\"/" + webAppName1 + "/visitorTools/images/book-16-off.gif\">" + nextBookTitle + "\n";
                returnHtml = returnHtml + "      </div>\n";
                returnHtml = returnHtml + getBookChildren(bpc, request);
                returnHtml = returnHtml + "    </blockquote>\n";
                returnHtml = returnHtml + "  </div>\n";
            }
            else if (pc instanceof PagePresentationContext)
            {
                PagePresentationContext ppc = (PagePresentationContext) pc;
                String pageInstanceId = ppc.getInstanceId();

                String pageTitle = ppc.getTitle();

                String fixedPageTitle = pageTitle.replaceAll("\'", "\\\\u0027");
                fixedPageTitle = fixedPageTitle.replaceAll("\"", "\\\\u0022");

                PageInstance pageInstance = PortalVisitorManager.getPageInstance(pageInstanceId, request.getLocale(), request);
                int pageThemeId = -1;
                ThemeDefinitionId tdi = pageInstance.getThemeDefinitionId();
                if (tdi != null)
                {
                    pageThemeId = tdi.getId();
                }

                returnHtml = returnHtml + "  <div class=\"pagechild\" style=\"display:none;position:relative\">\n";
                returnHtml = returnHtml + "    <blockquote style='margin-left:21px;margin-right:0px;margin-top:0px;margin-bottom:0px'>\n";
                returnHtml = returnHtml + "      <div style=\"display:block;\" id=\"page" + String.valueOf(pageCount) + "On\">\n";
                returnHtml = returnHtml + "        <img src=\"/" + webAppName1 + "/visitorTools/images/page-16.gif\"><a href='javascript:selectPage(\"" + pageInstanceId + "\", \"" + parentBookInstanceId + "\", \"book\", \"" + pageThemeId + "\", \"" + fixedPageTitle + "\")'>" + pageTitle + "</a>\n";
                returnHtml = returnHtml + "      </div>\n";
                returnHtml = returnHtml + "      <div style=\"display:none;color:#999999\" id=\"page" + String.valueOf(pageCount++) + "Off\">\n";
                returnHtml = returnHtml + "        <img src=\"/" + webAppName1 + "/visitorTools/images/page-16-off.gif\">" + pageTitle + "\n";
                returnHtml = returnHtml + "      </div>\n";
                returnHtml = returnHtml + getPageChildren(ppc, request);
                returnHtml = returnHtml + "    </blockquote>\n";
                returnHtml = returnHtml + "  </div>\n";
            }
        }

        return returnHtml;

    }

    private String getPageChildren(PagePresentationContext pagePC, HttpServletRequest request)
    {
        String webAppName1 = ApplicationHelper.getWebAppName(request);
        String returnHtml = "";

        String parentPageInstanceId = pagePC.getInstanceId();

        List pp = pagePC.getWindowPresentationContexts();
        Iterator it = pp.iterator();
        for (int i = 0; it.hasNext(); i++ )
        {
            PresentationContext pc = (PresentationContext)it.next();

            if (pc instanceof PortletPresentationContext)
            {
                
                PortletPresentationContext ppc = (PortletPresentationContext) pc;
                String portletInstanceId = ppc.getInstanceId();
               
                String portletTitle = ppc.getTitle();

                String fixedPortletTitle = portletTitle.replaceAll("\'", "\\\\u0027");
                fixedPortletTitle = fixedPortletTitle.replaceAll("\"", "\\\\u0022");
                
                PortletInstance portletInstance = PortalVisitorManager.getPortletInstance(portletInstanceId, request.getLocale(), request);
                int portletThemeId = -1;
                ThemeDefinitionId tdi = portletInstance.getThemeDefinitionId();
                if (tdi != null)
                {
                    portletThemeId = tdi.getId();
                }

                returnHtml = returnHtml + "  <div class=\"portletchild\" style=\"display:none;position:relative\">\n";
                returnHtml = returnHtml + "    <blockquote style='margin-left:21px;margin-right:0px;margin-top:0px;margin-bottom:0px'>\n";
                returnHtml = returnHtml + "      <div style=\"display:block;\" id=\"portlet" + String.valueOf(portletCount) + "On\">\n";
                returnHtml = returnHtml + "        <img src=\"/" + webAppName1 + "/visitorTools/images/portlet-16.gif\"><a href='javascript:selectPortlet(\"" + portletInstanceId + "\", \"" + parentPageInstanceId + "\", \"page\", \"" + portletThemeId + "\", \"" + fixedPortletTitle + "\")'>" + portletTitle + "</a>\n";
                returnHtml = returnHtml + "      </div>\n";
                returnHtml = returnHtml + "      <div style=\"display:none;color:#999999;\" id=\"portlet" + String.valueOf(portletCount++) + "Off\">\n";
                returnHtml = returnHtml + "        <img src=\"/" + webAppName1 + "/visitorTools/images/portlet-16-off.gif\">" + portletTitle + "\n";
                returnHtml = returnHtml + "      </div>\n";
                returnHtml = returnHtml + "    </blockquote>\n";
                returnHtml = returnHtml + "  </div>\n";
            }
            else
            {
                BookPresentationContext bpc = (BookPresentationContext) pc;
                String bookInstanceId = bpc.getInstanceId();

                String bookTitle = bpc.getTitle();

                String fixedBookTitle = bookTitle.replaceAll("\'", "\\\\u0027");
                fixedBookTitle = fixedBookTitle.replaceAll("\"", "\\\\u0022");

                BookInstance bookInstance = PortalVisitorManager.getBookInstance(bookInstanceId, request.getLocale(), request);
                int bookThemeId = -1;
                ThemeDefinitionId tdi = bookInstance.getThemeDefinitionId();
                if (tdi != null)
                {
                    bookThemeId = tdi.getId();
                }
                
                returnHtml = returnHtml + "  <div class=\"bookchild\" style=\"display:none;position:relative\">\n";
                returnHtml = returnHtml + "    <blockquote style='margin-left:21px;margin-right:0px;margin-top:0px;margin-bottom:0px'>\n";
                returnHtml = returnHtml + "      <div style=\"display:block;\" id=\"book" + String.valueOf(bookCount) + "On\">\n";
                returnHtml = returnHtml + "        <img src=\"/" + webAppName1 + "/visitorTools/images/book-16.gif\"><a href='javascript:selectBook(\"" + bookInstanceId + "\", \"" + parentPageInstanceId + "\", \"page\", \"" + bookThemeId + "\", \"" + fixedBookTitle + "\" , \"" +  String.valueOf(bookCount) + "\")'>" + bookTitle + "</a>\n";
                returnHtml = returnHtml + "      </div>\n";
                returnHtml = returnHtml + "      <div style=\"display:none;color:#999999;\" id=\"book" + String.valueOf(bookCount++) + "Off\">\n";
                returnHtml = returnHtml + "        <img src=\"/" + webAppName1 + "/visitorTools/images/book-16-off.gif\">" + bookTitle + "\n";
                returnHtml = returnHtml + "      </div>\n";
                returnHtml = returnHtml + getBookChildren(bpc, request);
                returnHtml = returnHtml + "    </blockquote>\n";
                returnHtml = returnHtml + "  </div>\n";
            }
        }


        return returnHtml;
    }

%>

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

    DesktopPresentationContext desktopPresentationContext = DesktopPresentationContext.getDesktopPresentationContext(request);
    BookPresentationContext bookPresentationContext = desktopPresentationContext.getBookPresentationContext();
    String theBook = bookPresentationContext.getLabel();
    // Get primary book info
    String primaryBookInstanceId = bookPresentationContext.getInstanceId();
    String primaryBookTitle = "";
    int primaryBookThemeId = -1;
    if (primaryBookInstanceId != null)
    {
        BookInstance primaryBookInstance = PortalVisitorManager.getBookInstance(primaryBookInstanceId, request.getLocale(), request);
        primaryBookTitle = primaryBookInstance.getInstanceTitle();
        ThemeDefinitionId ptdi = primaryBookInstance.getThemeDefinitionId();
        if (ptdi != null)
        {
            primaryBookThemeId = ptdi.getId();
        }
    }

    ArrayList pages = new ArrayList();

    if (bookPresentationContext != null)
    {
        List bp = bookPresentationContext.getEntitledPagePresentationContexts();
        if (bp != null)
        {
            Iterator it = bp.iterator();
            for (int i = 0; it.hasNext(); i++ )
            {
                PresentationContext pc = (PresentationContext)it.next();

                if (pc instanceof BookPresentationContext)
                {
                    BookPresentationContext bpc = (BookPresentationContext)pc;
                    String bookInstanceId = bpc.getInstanceId();
                    if (bookInstanceId != null)
                    {
                        pages.add(bpc);
                    }
                }
                else
                {
                    PagePresentationContext ppc = (PagePresentationContext)pc;
                    String pageInstanceId = ppc.getInstanceId();
                    if (pageInstanceId != null)
                    {
                        pages.add(ppc);
                    }
                }
            }
        }
    }

    Iterator pageIt = pages.iterator();
%>
<div style="padding-left:6px;padding-right:6px;padding-bottom:6px;padding-top:0px;">
  <h2>Customize your view of the Portal</h2>

  <table border="0" cellpadding="3" cellspacing="0" align="left">
    <tr>
      <td valign="top" align="left" width="250" nowrap>
        Click to select and customize Portal, Book, and Page behavior.  Actions that are available for each resource become active when the resource is selected.
      </td>
      <td valign="top" align="left">
          <div class="bea-portal-window">
              <div class="bea-portal-window-titlebar">Portal Resources</div>
              <div class="portlet-section-alternate">
                 &nbsp;
                 <input id="showPageContentsCheckbox" type="checkbox" name="showPageContentsName" onClick="showPageContents()" style="margin:0px;"/>
                 <span style="font-weight:normal;font-size:80%;">Show Page Contents</span>
              </div>
              <table border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td valign="top" align="left" nowrap>
                    <div id="PortalTree" style="margin:12px;">
                        <div><img src="/<%=webAppName%>/visitorTools/images/portal-16.gif"/><a href='javascript:selectPortal("<%=primaryBookInstanceId%>", "<%=primaryBookThemeId%>", "<%=primaryBookTitle%>")'>Portal</a></div>
<%
                    for (int j = 0; pageIt.hasNext(); j++ )
                    {
                        PresentationContext pc = (PresentationContext)pageIt.next();

                        if (pc instanceof BookPresentationContext) 
                        {
                            BookPresentationContext bookPC = (BookPresentationContext) pc;
                            String bookTitle = bookPC.getTitle();

                            String fixedBookTitle = bookTitle.replaceAll("\'", "\\\\u0027");
                            fixedBookTitle = fixedBookTitle.replaceAll("\"", "\\\\u0022");
                            
                            String bookId = bookPC.getInstanceId();
                            BookInstance bi = PortalVisitorManager.getBookInstance(bookId, request.getLocale(), request);
                            int bookThemeId = -1;
                            ThemeDefinitionId tdi = bi.getThemeDefinitionId();
                            if (tdi != null)
                            {
                                bookThemeId = tdi.getId();
                            }
%>

                  <div class="book" style="position:relative"> 
                    <blockquote style='margin-left:21px;margin-right:0px;margin-top:0px;margin-bottom:0px'> 
                      <div style="display:block;" id="book<%=String.valueOf(bookCount)%>On"> 
                        <img src="/<%=webAppName%>/visitorTools/images/book-16.gif"><a href='javascript:selectBook("<%=bookId%>", "<%=primaryBookInstanceId%>", "<%=PortalVisitorConstants.BOOK%>", "<%=bookThemeId%>", "<%=fixedBookTitle%>", "<%=String.valueOf(bookCount)%>")'><%=bookTitle%></a> 
                      </div>
                      <div style="display:none;color:#999999;" id="book<%=String.valueOf(bookCount++)%>Off"> 
                        <img src="/<%=webAppName%>/visitorTools/images/book-16-off.gif"><%=bookTitle%> 
                      </div>
                      <%
                            out.println(getBookChildren(bookPC, request));
%>
                    </blockquote>
                  </div>
<%
                        }
                        else if (pc instanceof PagePresentationContext)
                        {
                            PagePresentationContext pagePC = (PagePresentationContext) pc;
                            String pageTitle = pagePC.getTitle();

                            String fixedPageTitle = pageTitle.replaceAll("\'", "\\\\u0027");
                            fixedPageTitle = fixedPageTitle.replaceAll("\"", "\\\\u0022");
                            
                            String pageId = pagePC.getInstanceId();
                            PageInstance pi = PortalVisitorManager.getPageInstance(pageId, request.getLocale(), request);
                            int pageThemeId = -1;
                            ThemeDefinitionId tdi = pi.getThemeDefinitionId();
                            if (tdi != null)
                            {
                                pageThemeId = tdi.getId();
                            }
%>

                  <div class="page" style="position:relative"> 
                    <blockquote style='margin-left:21px;margin-right:0px;margin-top:0px;margin-bottom:0px'> 
                      <div style="display:block;" id="page<%=String.valueOf(pageCount)%>On"> 
                        <img src="/<%=webAppName%>/visitorTools/images/page-16.gif"><a href='javascript:selectPage("<%=pageId%>", "<%=primaryBookInstanceId%>", "<%=PortalVisitorConstants.BOOK%>", "<%=pageThemeId%>", "<%=fixedPageTitle%>")'><%=pageTitle%></a> 
                      </div>
                      <div style="display:none;color:#999999;" id="page<%=String.valueOf(pageCount++)%>Off"> 
                        <img src="/<%=webAppName%>/visitorTools/images/page-16-off.gif"><%=pageTitle%> 
                      </div>
                      <%
                        out.println(getPageChildren(pagePC, request));
%>
                    </blockquote>
                  </div>
<%
                        }
                    }
%>
                    </div>
                  </td>
                  <td valign="top" align="left">
                    <div class="bea-portal-window" style="margin:12px;">
                        <div id="selectedResourceDiv" class="portlet-section-alternate" style="white-space: nowrap;text-align:left;">Selected Item:&nbsp;None</div>
                        <div id="moveDiv" style="visibility:hidden;position:absolute">
                          <br />
                          <table border="0" cellpadding="6" cellspacing="0">
                            <tr>
                              <td valign="top" align="right" nowrap><b>Move To:</b></td>
                              <td id="itemToMoveTo" valign="top" align="left" nowrap> </td>
                            </tr>
                            <tr>
                              <td nowrap>
                                 <div id="finishMoveButtonOn" style="position:absolute;visibility:hidden;">
                                     <input type="button" value="Finish Move" onClick="finishMoveResource()">
                                 </div>
                                 <div id="finishMoveButtonOff">
                                     <input type="button" value="Finish Move" disabled="true">
                                 </div>
                               </td>
                              <td nowrap> <input type="button" value="Cancel Move" onClick="cancelMove()">
                              </td>
                            </tr>
                          </table>
                        </div>

                  <div id="editItemDiv" style="position:relative;padding:6px;white-space:nowrap;"> 
                    <form method="post" name="UpdateThemeForm" id="UpdateThemeForm">
                      <div id="editContentsButtonOn" style="display:none;"> 
                        <input type="button" value="Edit Contents" onClick="editResource()">
                      </div>
                      <div id="editContentsButtonOff" disabled="true" style="display:block;"> 
                        <input type="button"
                                   value="Edit Contents"
                                   style="color:#999999"
                                   alt="Please choose an item first, then edit its Contents"
                                   title="Please choose an item first, then edit its Contents">
                      </div>
                      <div id="renameButtonOn" style="display:none;"> 
                        <input type="button" value="Rename" onClick="renameResource()">
                      </div>
                      <div id="renameButtonOff" disabled="true" style="display:block;"> 
                        <input type="button"
                                   value="Rename"
                                   style="color:#999999"
                                   alt="Please choose an item first, then Rename it"
                                   title="Please choose an item first, then Rename it">
                      </div>
                      <div id="moveButtonOn" style="display:none;"> 
                        <input type="button" value="Move" onClick="moveResource()">
                      </div>
                      <div id="moveButtonOff" disabled="true" style="display:block;"> 
                        <input type="button"
                               value="Move"
                               style="color:#999999"
                               alt="Please choose an item first, then Move it"
                               title="Please choose an item first, then Move it">
                      </div>
                      <hr/>
                      <div id="chooseThemeButtonOn" style="display:none;text-align:left;"> 
                        <b>Choose Theme:</b><br />
                        <div style="width:1%;"> &nbsp;&nbsp;&nbsp;&nbsp; 
                          <select name="<%=PortalVisitorConstants.THEME_ID%>" id="<%=PortalVisitorConstants.THEME_ID%>">
                            <option value="-1">None 
                            <%
                                int selectedThemeId = 0;
                                ThemeDefinition[] themeArray = PortalVisitorManager.getThemeDefinitions(webAppName, request.getLocale(), request);

                                if (themeArray != null)
                                {
                                    for (int i=0; i < themeArray.length; i++)
                                    {
                                        ThemeDefinition tmpTheme = themeArray[i];
                                        int themeId = tmpTheme.getThemeDefinitionId().getId();
                                        LocalizationResource lr2 = tmpTheme.getLocalizationResource();
%>
                            <option value="<%=themeId%>" <% if (themeId == selectedThemeId){%>selected<%}%>><%=lr2.getTitle()%> 
                            <%
                                    }
                                }
%>
                          </select>
                          <br/>
                          &nbsp;&nbsp;&nbsp;&nbsp; 
                          <input type="button" value="Apply Theme" onClick="applyTheme()">
                        </div>
                      </div>
                      <div id="chooseThemeButtonOff" style="display:block;color:#999999;" disabled="true"> 
                        <b>Choose Theme:</b><br />
                        <div style="width:1%;"> &nbsp;&nbsp;&nbsp;&nbsp; 
                          <select name="themeIdDisabled" id="themeIdDisabled" disabled="true">
                            <option value="-1">None</option>
                          </select>
                          <br/>
                          &nbsp;&nbsp;&nbsp;&nbsp; 
                          <input type="button"
                                   disabled="true"
                                   value="Apply Theme"
                                   style="color:#999999"
                                   alt="Please choose an item first, then apply a Theme"
                                   title="Please choose an item first, then apply a Theme">
                        </div>
                      </div>
                      <input type="hidden" name="<%=PortalVisitorConstants.RESOURCE_ID%>" value=""/>
                      <input type="hidden" name="<%=PortalVisitorConstants.RESOURCE_TYPE%>" value=""/>
                      <input type="hidden" name="<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>" value=""/>
                      <input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_MAIN_PAGE%>"/>
                      <input type="hidden" name="<%=PortalVisitorConstants.WEB_APP_NAME%>" value="<%=webAppName%>"/>
                      <input type="hidden" name="<%=PortalVisitorConstants.PORTAL_PATH%>" value="<%=portalPath%>"/>
                      <input type="hidden" name="<%=PortalVisitorConstants.DESKTOP_PATH%>" value="<%=desktopPath%>"/>
                      <input type="hidden" name="<%=PortalVisitorConstants.MAIN_BOOK%>" value="<%=theBook%>"/>
                    </form>
                  </div>
                    </div>
                  </td>
                </tr>
              </table>
            </div>
        </td>
    </tr>
    <tr>
      <td valign="top" align="left">
        Select and apply a Portal Look & Feel 
      </td>
      <td valign="top" align="left">
          <div class="bea-portal-window">
                <div class="bea-portal-window-titlebar">Portal Look &amp; Feel</div>
                <div style="padding:6px;">
                    <form method="post" name="UpdateLookAndFeelForm" id="UpdateLookAndFeelForm">
                  <b>Choose a Look &amp; Feel:</b>
                  <br />
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <select name="<%=PortalVisitorConstants.LOOK_AND_FEEL_ID%>" id="<%=PortalVisitorConstants.LOOK_AND_FEEL_ID%>">
<%
                    int selectedLookId = 0;
                    DesktopView dv = PortalVisitorManager.getDesktopView(portalPath, webAppName, desktopPath, request.getLocale(), request);
                    selectedLookId = dv.getLookAndFeelView().getLookAndFeelDefinitionId().getId();
                    LookAndFeelDefinition[] lookArray = PortalVisitorManager.getLookAndFeelDefinitions(webAppName, request.getLocale(), request);

                    if (lookArray != null)
                    {
                        for (int i=0; i < lookArray.length; i++)
                        {
                            LookAndFeelDefinition tmpLook = lookArray[i];
                            int lookId = tmpLook.getLookAndFeelDefinitionId().getId();
                            LocalizationResource lr = tmpLook.getLocalizationResource();
%>
                        <option value="<%=lookId%>" <% if (lookId == selectedLookId){%>selected<%}%>><%=lr.getTitle()%>
<%
                        }
                    }

%>
                      </select>&nbsp;<input type="button" value="Apply Look &amp; Feel" onClick="applyLookAndFeel()">
                      <input type="hidden" name="<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>" value=""/>
                      <input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_MAIN_PAGE%>"/>
                      <input type="hidden" name="<%=PortalVisitorConstants.WEB_APP_NAME%>" value="<%=webAppName%>"/>
                      <input type="hidden" name="<%=PortalVisitorConstants.PORTAL_PATH%>" value="<%=portalPath%>"/>
                      <input type="hidden" name="<%=PortalVisitorConstants.DESKTOP_PATH%>" value="<%=desktopPath%>"/>
                      <input type="hidden" name="<%=PortalVisitorConstants.MAIN_BOOK%>" value="<%=theBook%>"/>
                    </form>
                </div>
            </div>
        </td>
    </tr>
  </table>
</div>
<div id="renameResourceDialog" align="center" class="bea-portal-window" style="position:absolute;visibility:hidden;left:0;top:0;height:0;width:0;z-index:5001">
  <table width='250' height='125' cellpadding="3" cellspacing="0">
    <tr>
      <td valign="top" align="center" nowrap>
		<input name="renameInput" id="renameInput" type="text" value=""/>
      </td>
    </tr>
    <tr>
      <td valign="top" align="center">
          <input type="button" value="Rename" onClick="renameResourceSubmit(getElementById('renameInput').value);">&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" value="Cancel" onClick="toggleDialogBox('renameResourceDialog')">
      </td>
    </tr>
  </table>
</div><%-- end Add content dialog div --%>

<form method="post" name="MoveToBookForm" id="MoveToBookForm" action="<%=request.getRequestURI()%>">
    <input type="hidden" name="<%=PortalVisitorConstants.BOOK_TO_MOVE_RESOURCE_TO%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.RESOURCE_TO_MOVE%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.RESOURCE_TO_MOVE_FROM%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE_FROM%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_MAIN_PAGE%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.WEB_APP_NAME%>" value="<%=webAppName%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.MAIN_BOOK%>" value="<%=theBook%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.PORTAL_PATH%>" value="<%=portalPath%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.DESKTOP_PATH%>" value="<%=desktopPath%>"/>
</form>

<form method="post" name="EditBookForm" id="EditBookForm" action="<%=request.getRequestURI()%>">
    <input type="hidden" name="<%=PortalVisitorConstants.EDIT_BOOK%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.EDIT_BOOK_NAME%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_EDIT_BOOK%>"/>
</form>

<form method="post" name="MoveToPageForm" id="MoveToPageForm" action="<%=request.getRequestURI()%>">
    <input type="hidden" name="<%=PortalVisitorConstants.PAGE_TO_MOVE_RESOURCE_TO%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.RESOURCE_TO_MOVE%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.RESOURCE_TO_MOVE_FROM%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE_FROM%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_MAIN_PAGE%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.WEB_APP_NAME%>" value="<%=webAppName%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.MAIN_BOOK%>" value="<%=theBook%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.PORTAL_PATH%>" value="<%=portalPath%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.DESKTOP_PATH%>" value="<%=desktopPath%>"/>
</form>

<form method="post" name="EditPageForm" id="EditPageForm" action="<%=request.getRequestURI()%>">
    <input type="hidden" name="<%=PortalVisitorConstants.EDIT_PAGE%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.EDIT_PAGE_NAME%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_EDIT_PAGE%>"/>
</form>

<form method="post" name="RenameResourceForm" id="RenameResourceForm">
    <input type="hidden" name="<%=PortalVisitorConstants.RESOURCE_ID%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.RESOURCE_TYPE%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.RENAME_RESOURCE%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>" value=""/>
    <input type="hidden" name="<%=PortalVisitorConstants.TARGET_PAGE_ID%>" value="<%=PortalVisitorConstants.VISITOR_TOOLS_MAIN_PAGE%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.WEB_APP_NAME%>" value="<%=webAppName%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.MAIN_BOOK%>" value="<%=theBook%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.PORTAL_PATH%>" value="<%=portalPath%>"/>
    <input type="hidden" name="<%=PortalVisitorConstants.DESKTOP_PATH%>" value="<%=desktopPath%>"/>
</form>

<script language="javascript">

var selectedType = "<%=PortalVisitorConstants.BOOK%>";
var selectedId = "0";
var selectedParentId = "0";
var selectedParentType = "<%=PortalVisitorConstants.BOOK%>";
var selectedName = "";
var moveBit = "0";
var selectedMoveParentBit = 0;
var selectedMoveType = "<%=PortalVisitorConstants.BOOK%>";
var selectedMoveId = "0";
var selectedMoveName = "";
var oldInnerHTML = "";
var moveToPageOK = false;
var moveToBookOK = false;
var selectedBookDivNum = "";

document.vlinkColor = document.linkColor;
document.alinkColor = document.linkColor;

function cancelMove()
{
    moveBit = 0;
    selectedMoveId = "";
    selectedMoveName = "";

    disableItems("portlet",false);
    disableItems("book",false);
    disableItems("page",false);

    if (selectedMoveParentBit == 1)
    {
        tmpHTML = document.getElementById("finishMoveButtonOff").innerHTML;
        document.getElementById("finishMoveButtonOff").innerHTML = document.getElementById("finishMoveButtonOn").innerHTML;
        document.getElementById("finishMoveButtonOn").innerHTML = tmpHTML;
    }
    selectedMoveParentBit = 0;

    document.getElementById("moveDiv").innerHTML = document.getElementById("editItemDiv").innerHTML;
    document.getElementById("editItemDiv").innerHTML = oldInnerHTML;
    oldInnerHTML = "";
}

function moveResource()
{
    moveBit = 1;

    disableItems("portlet",true);

    if (selectedType == "<%=PortalVisitorConstants.PORTLET%>")
    {
        disableItems("book",true);
        document.getElementById("itemToMoveTo").innerHTML = "(Please click a Page)";
    }
    else if (selectedType == "<%=PortalVisitorConstants.PAGE%>")
    {
        disableItems("page",true);
        document.getElementById("itemToMoveTo").innerHTML = "(Please click a Book or the Portal)";
    } else if (selectedType == "<%=PortalVisitorConstants.BOOK%>")
    {
        if (selectedParentType == "<%=PortalVisitorConstants.BOOK%>")
        {
            disableItems("page",true);
            document.getElementById("itemToMoveTo").innerHTML = "(Please click a Book or the Portal)";
        }
        else if (selectedParentType == "<%=PortalVisitorConstants.PAGE%>")
        {
            disableItems("book",true);
            document.getElementById("itemToMoveTo").innerHTML = "(Please click a Page)";
        }
        // Turn off the book itself to prevent recursion
        document.getElementById("book" + selectedBookDivNum + "On").style.display = "none";
        document.getElementById("book" + selectedBookDivNum + "Off").style.display = "block";
    }

    oldInnerHTML = document.getElementById("editItemDiv").innerHTML;
    document.getElementById("editItemDiv").innerHTML = document.getElementById("moveDiv").innerHTML;
    document.getElementById("moveDiv").innerHTML = "";
}

function disableItems(type,bool)
{
    var keepGoing = true;
    var num = 0;
    while (keepGoing)
    {
        var eltOn = document.getElementById(type + num + (bool ? "On" : "Off"));
        var eltOff = document.getElementById(type + num + (bool ? "Off" : "On"));
        if (eltOn == null)
        {
            keepGoing = false;
        } else
        {
            eltOn.style.display = "none";
            eltOff.style.display = "block";
            num++;
        }
    }
}

function showPageContents()
{
    if (document.getElementById("showPageContentsCheckbox").checked)
    {

        divElements = document.getElementById("PortalTree").getElementsByTagName('DIV');
        for (i=0; i<divElements.length; i++)
        {
            if (divElements[i].className=="pagechild" || divElements[i].className=="bookchild" || divElements[i].className=="portletchild")
            {
                //divElements[i].style.visibility = "visible";
                divElements[i].style.display = "block";
            }
        }
    }
    else
    {
        divElements = document.getElementById("PortalTree").getElementsByTagName('DIV');
        for (i=0; i<divElements.length; i++)
        {
            if (divElements[i].className=="pagechild" || divElements[i].className=="bookchild" || divElements[i].className=="portletchild")
            {
                //divElements[i].style.visibility = "hidden";
                divElements[i].style.display = "none";
            }
        }
    }
}

function editResource()
{
    checkVal = "0";
    showContents = document.getElementById("showPageContentsCheckbox");
    if (showContents.checked)
    {
        checkVal = "1";
    }
    
    if (selectedType == "<%=PortalVisitorConstants.PAGE%>")
    {
        document.EditPageForm.<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>.value = checkVal;
        document.EditPageForm.submit();
    }
    else
    {
        document.EditBookForm.<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>.value = checkVal;
        document.EditBookForm.submit();
    }
}

function applyTheme()
{
    checkVal = "0";
    showContents = document.getElementById("showPageContentsCheckbox");
    if (showContents.checked)
    {
        checkVal = "1";
    }

    document.UpdateThemeForm.<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>.value = checkVal;
    document.UpdateThemeForm.submit();
}

function applyLookAndFeel()
{
    checkVal = "0";
    showContents = document.getElementById("showPageContentsCheckbox");
    if (showContents.checked)
    {
        checkVal = "1";
    }

    document.UpdateLookAndFeelForm.<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>.value = checkVal;
    document.UpdateLookAndFeelForm.submit();
}

function renameResource()
{
	toggleDialogBox("renameResourceDialog",125,250,"Please enter the new name:");
}

function renameResourceSubmit(newName)
{
    checkVal = "0";
    showContents = document.getElementById("showPageContentsCheckbox");
    if (showContents.checked)
    {
        checkVal = "1";
    }
    
    if(newName != null)
    {
        if (newName != "")
        {
            document.RenameResourceForm.<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>.value = checkVal;
            document.RenameResourceForm.<%=PortalVisitorConstants.RENAME_RESOURCE%>.value = newName;
            document.RenameResourceForm.submit();
        }
        else
        {
            alert("You must enter a name");
        }
    }
}

function finishMoveResource()
{
    checkVal = "0";
    showContents = document.getElementById("showPageContentsCheckbox");
    if (showContents.checked)
    {
        checkVal = "1";
    }

    if (selectedType == "<%=PortalVisitorConstants.PORTLET%>")
    {
        document.MoveToPageForm.<%=PortalVisitorConstants.PAGE_TO_MOVE_RESOURCE_TO%>.value = selectedMoveId;
        document.MoveToPageForm.<%=PortalVisitorConstants.RESOURCE_TO_MOVE%>.value = selectedId;
        document.MoveToPageForm.<%=PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE%>.value = selectedType;
        document.MoveToPageForm.<%=PortalVisitorConstants.RESOURCE_TO_MOVE_FROM%>.value = selectedParentId;
        document.MoveToPageForm.<%=PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE_FROM%>.value = selectedParentType;
        document.MoveToPageForm.<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>.value = checkVal;
    
        document.MoveToPageForm.submit();
    }
    else if (selectedType == "<%=PortalVisitorConstants.PAGE%>")
    {
        document.MoveToBookForm.<%=PortalVisitorConstants.BOOK_TO_MOVE_RESOURCE_TO%>.value = selectedMoveId;
        document.MoveToBookForm.<%=PortalVisitorConstants.RESOURCE_TO_MOVE%>.value = selectedId;
        document.MoveToBookForm.<%=PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE%>.value = selectedType;
        document.MoveToBookForm.<%=PortalVisitorConstants.RESOURCE_TO_MOVE_FROM%>.value = selectedParentId;
        document.MoveToBookForm.<%=PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE_FROM%>.value = selectedParentType;
        document.MoveToBookForm.<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>.value = checkVal;

        document.MoveToBookForm.submit();
    }
    else if (selectedType == "<%=PortalVisitorConstants.BOOK%>")
    {
        if (selectedMoveType == "<%=PortalVisitorConstants.PAGE%>")
        {
            document.MoveToPageForm.<%=PortalVisitorConstants.PAGE_TO_MOVE_RESOURCE_TO%>.value = selectedMoveId;
            document.MoveToPageForm.<%=PortalVisitorConstants.RESOURCE_TO_MOVE%>.value = selectedId;
            document.MoveToPageForm.<%=PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE%>.value = selectedType;
            document.MoveToPageForm.<%=PortalVisitorConstants.RESOURCE_TO_MOVE_FROM%>.value = selectedParentId;
            document.MoveToPageForm.<%=PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE_FROM%>.value = selectedParentType;
            document.MoveToPageForm.<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>.value = checkVal;
        
            document.MoveToPageForm.submit();
        }
        else
        {
            document.MoveToBookForm.<%=PortalVisitorConstants.BOOK_TO_MOVE_RESOURCE_TO%>.value = selectedMoveId;
            document.MoveToBookForm.<%=PortalVisitorConstants.RESOURCE_TO_MOVE%>.value = selectedId;
            document.MoveToBookForm.<%=PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE%>.value = selectedType;
            document.MoveToBookForm.<%=PortalVisitorConstants.RESOURCE_TO_MOVE_FROM%>.value = selectedParentId;
            document.MoveToBookForm.<%=PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE_FROM%>.value = selectedParentType;
            document.MoveToBookForm.<%=PortalVisitorConstants.SHOW_PAGE_CONTENTS%>.value = checkVal;
            
            document.MoveToBookForm.submit();
        }
    }
}

function selectPortlet(portletId, parentId, parentType, themeId, portletName)
{
    if (moveBit == 1)
    {
    }
    else
    {
        selectedType = "<%=PortalVisitorConstants.PORTLET%>";
        selectedId = portletId;
        selectedParentId = parentId;
        selectedParentType = parentType;
        selectedName = portletName;
        moveToPageOK = true;
        moveToBookOK = false;

        document.RenameResourceForm.<%=PortalVisitorConstants.RESOURCE_ID%>.value = portletId;
        document.RenameResourceForm.<%=PortalVisitorConstants.RESOURCE_TYPE%>.value = "<%=PortalVisitorConstants.PORTLET%>";
        document.UpdateThemeForm.<%=PortalVisitorConstants.RESOURCE_ID%>.value = portletId;
        document.UpdateThemeForm.<%=PortalVisitorConstants.RESOURCE_TYPE%>.value = "<%=PortalVisitorConstants.PORTLET%>";

        document.getElementById("selectedResourceDiv").innerHTML = "Selected Portlet: &quot;" + portletName + "&quot;";

        var num = document.forms.UpdateThemeForm.<%=PortalVisitorConstants.THEME_ID%>.options.length;
        for (var x=0; x < num; x++)
        {
            if (themeId == document.forms.UpdateThemeForm.<%=PortalVisitorConstants.THEME_ID%>.options[x].value)
            {
                document.forms.UpdateThemeForm.<%=PortalVisitorConstants.THEME_ID%>[x].selected = "1";
            }
        }

        activateEditMode();
    }
}

function selectBook(bookId, parentId, parentType, themeId, bookName, divNum)
{
    if (moveBit == 1)
    {
        if (moveToBookOK)
        {
            selectedMoveType = "<%=PortalVisitorConstants.BOOK%>";
            selectedMoveId = bookId;
            selectedMoveName = bookName;

            document.getElementById("itemToMoveTo").innerHTML = bookName;
            if (selectedMoveParentBit == 0)
            {
                tmpHTML = document.getElementById("finishMoveButtonOff").innerHTML;
                document.getElementById("finishMoveButtonOff").innerHTML = document.getElementById("finishMoveButtonOn").innerHTML;
                document.getElementById("finishMoveButtonOn").innerHTML = tmpHTML;
            }
            selectedMoveParentBit = 1;
        }
    }
    else
    {
        selectedType = "<%=PortalVisitorConstants.BOOK%>";
        selectedId = bookId;
        selectedParentId = parentId;
        selectedParentType = parentType;
        selectedName = bookName;
        moveToPageOK = true;
        moveToBookOK = true;
        selectedBookDivNum = divNum;

        document.RenameResourceForm.<%=PortalVisitorConstants.RESOURCE_ID%>.value = bookId;
        document.RenameResourceForm.<%=PortalVisitorConstants.RESOURCE_TYPE%>.value = "<%=PortalVisitorConstants.BOOK%>";
        document.UpdateThemeForm.<%=PortalVisitorConstants.RESOURCE_ID%>.value = bookId;
        document.UpdateThemeForm.<%=PortalVisitorConstants.RESOURCE_TYPE%>.value = "<%=PortalVisitorConstants.BOOK%>";
        document.EditBookForm.<%=PortalVisitorConstants.EDIT_BOOK%>.value = bookId;
        document.EditBookForm.<%=PortalVisitorConstants.EDIT_BOOK_NAME%>.value = bookName;

        document.getElementById("selectedResourceDiv").innerHTML = "Selected Book: &quot;" + bookName + "&quot;";


        var num = document.forms.UpdateThemeForm.<%=PortalVisitorConstants.THEME_ID%>.options.length;
        for (var x=0; x < num; x++)
        {
            if (themeId == document.forms.UpdateThemeForm.<%=PortalVisitorConstants.THEME_ID%>.options[x].value)
            {
                document.forms.UpdateThemeForm.<%=PortalVisitorConstants.THEME_ID%>[x].selected = "1";
            }
        }

        activateEditMode();
    }
}

function selectPage(pageId, parentId, parentType, themeId, pageName)
{
    if (moveBit == 1)
    {
        if (moveToPageOK)
        {
            selectedMoveType = "<%=PortalVisitorConstants.PAGE%>";
            selectedMoveId = pageId;
            selectedMoveName = pageName;
            document.getElementById("itemToMoveTo").innerHTML = pageName;
            if (selectedMoveParentBit == 0)
            {
                tmpHTML = document.getElementById("finishMoveButtonOff").innerHTML;
                document.getElementById("finishMoveButtonOff").innerHTML = document.getElementById("finishMoveButtonOn").innerHTML;
                document.getElementById("finishMoveButtonOn").innerHTML = tmpHTML;
            }
            selectedMoveParentBit = 1;
        }
    }
    else
    {
        selectedType = "<%=PortalVisitorConstants.PAGE%>";
        selectedId = pageId;
        selectedParentId = parentId;
        selectedParentType = parentType;
        selectedName = pageName;
        moveToPageOK = false;
        moveToBookOK = true;

        document.RenameResourceForm.<%=PortalVisitorConstants.RESOURCE_ID%>.value = pageId;
        document.RenameResourceForm.<%=PortalVisitorConstants.RESOURCE_TYPE%>.value = "<%=PortalVisitorConstants.PAGE%>";
        document.UpdateThemeForm.<%=PortalVisitorConstants.RESOURCE_ID%>.value = pageId;
        document.UpdateThemeForm.<%=PortalVisitorConstants.RESOURCE_TYPE%>.value = "<%=PortalVisitorConstants.PAGE%>";
        document.EditPageForm.<%=PortalVisitorConstants.EDIT_PAGE%>.value = pageId;
        document.EditPageForm.<%=PortalVisitorConstants.EDIT_PAGE_NAME%>.value = pageName;

        document.getElementById("selectedResourceDiv").innerHTML = "Selected Page: &quot;" + pageName + "&quot;";

        var num = document.forms.UpdateThemeForm.<%=PortalVisitorConstants.THEME_ID%>.options.length;
        for (var x=0; x < num; x++)
        {
            if (themeId == document.forms.UpdateThemeForm.<%=PortalVisitorConstants.THEME_ID%>.options[x].value)
            {
                document.forms.UpdateThemeForm.<%=PortalVisitorConstants.THEME_ID%>[x].selected = "1";
            }
        }

        activateEditMode();
    }
}

function selectPortal(bookId, themeId, bookName)
{
    if (moveBit == 1)
    {
        if (moveToBookOK)
        {
            selectedMoveType = "<%=PortalVisitorConstants.BOOK%>";
            selectedMoveId = bookId;
            selectedMoveName = bookName;
            document.getElementById("itemToMoveTo").innerHTML = "Portal (Top Level)";
            if (selectedMoveParentBit == 0)
            {
                tmpHTML = document.getElementById("finishMoveButtonOff").innerHTML;
                document.getElementById("finishMoveButtonOff").innerHTML = document.getElementById("finishMoveButtonOn").innerHTML;
                document.getElementById("finishMoveButtonOn").innerHTML = tmpHTML;
            }
            selectedMoveParentBit = 1;
        }
    }
    else
    {
        selectedType = "portal";
        selectedId = bookId;
        selectedName = bookName;
        moveToPageOK = false;
        moveToBookOK = false;

        document.EditBookForm.<%=PortalVisitorConstants.EDIT_BOOK%>.value = bookId;
        document.EditBookForm.<%=PortalVisitorConstants.EDIT_BOOK_NAME%>.value = bookName;
        document.UpdateThemeForm.<%=PortalVisitorConstants.RESOURCE_ID%>.value = bookId;
        document.UpdateThemeForm.<%=PortalVisitorConstants.RESOURCE_TYPE%>.value = "<%=PortalVisitorConstants.BOOK%>";

        document.getElementById("selectedResourceDiv").innerHTML = "Selected: Portal (Top Level)";

        var num = document.forms.UpdateThemeForm.<%=PortalVisitorConstants.THEME_ID%>.options.length;
        for (var x=0; x < num; x++)
        {
            if (themeId == document.forms.UpdateThemeForm.<%=PortalVisitorConstants.THEME_ID%>.options[x].value)
            {
                document.forms.UpdateThemeForm.<%=PortalVisitorConstants.THEME_ID%>[x].selected = "1";
            }
        }

        activateEditMode();
    }
}


function activateEditMode()
{
    if (selectedType == "<%=PortalVisitorConstants.PORTLET%>")
    {
        document.getElementById("editContentsButtonOn").style.display = "none"
        document.getElementById("editContentsButtonOff").style.display = "block"
    } else
    {
        document.getElementById("editContentsButtonOn").style.display = "block"
        document.getElementById("editContentsButtonOff").style.display = "none"
    }

    document.getElementById("chooseThemeButtonOff").style.display = "none";
    document.getElementById("chooseThemeButtonOn").style.display = "block";

    if (selectedType == "portal")
    {
        document.getElementById("renameButtonOn").style.display = "none"
        document.getElementById("renameButtonOff").style.display = "block"
        document.getElementById("moveButtonOn").style.display = "none"
        document.getElementById("moveButtonOff").style.display = "block"
    } else
    {
        document.getElementById("renameButtonOn").style.display = "block"
        document.getElementById("renameButtonOff").style.display = "none"
        document.getElementById("moveButtonOn").style.display = "block"
        document.getElementById("moveButtonOff").style.display = "none"
    }
}

</script>

<%
    pageCount = 0;
    bookCount = 0;
    portletCount = 0;
%>
<div id="dialogBackgroundDiv" style="position: absolute;visibility: hidden;left: 0;top: 0;height: 0;width: 0;background-color: #000000;z-index: 5000;filter: alpha(opacity=75);-moz-opacity:0.75;">
  &nbsp;
</div>

<script language="javascript">

<% //TODO get rid of the request check.
    String showPageContentsRequest = request.getParameter(PortalVisitorConstants.SHOW_PAGE_CONTENTS);
    if (showPageContentsRequest != null)
    {
        if (showPageContentsRequest.equals("1"))
        {
%>
            showContentsElement = document.getElementById("showPageContentsCheckbox");
            showContentsElement.checked = "true";
            showPageContents();
<%
        }
    }
    else
    {
        String showContents = (String)session.getAttribute(PortalVisitorConstants.SHOW_PAGE_CONTENTS);
        if (showContents != null)
        {
            if (showContents.equals("1"))
            {
%>            
                showContentsElement = document.getElementById("showPageContentsCheckbox");
                showContentsElement.checked = "true";
                showPageContents();
<%            
            }
        }
    }
%>    
</script>

<%
// closing if/else if logged in...
}
%>
