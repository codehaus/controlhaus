/*   Copyright 2004 Salesforce.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.bea.jsptools.portal.backing;

import com.bea.netuix.servlets.controls.content.backing.AbstractJspBacking;
import com.bea.netuix.servlets.controls.content.JspContentContext;

import com.bea.jsptools.portal.PortalVisitorConstants;
import com.bea.jsptools.portal.PortalVisitorManager;
import com.bea.jsptools.portal.placement.BookPlacement;
import com.bea.jsptools.portal.placement.NavigablePlacement;
import com.bea.jsptools.portal.placement.PagePlacement;

import com.bea.portlet.GenericURL;
import com.bea.portlet.PostbackURL;

import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * A simple backing file to fire a page change event
 */
public class VisitorToolsEditBookBacking extends AbstractJspBacking
{
    public boolean handlePostbackData(HttpServletRequest request, HttpServletResponse response)
    {
        if (request.getRemoteUser() == null)
        {
            return true;
        }

        JspContentContext contentContext = JspContentContext.getJspContentContext(request);
        assert contentContext != null;

        String newPageLabel = request.getParameter(PortalVisitorConstants.TARGET_PAGE_ID);

        String webAppName = request.getParameter(PortalVisitorConstants.WEB_APP_NAME);
        String portalPath = request.getParameter(PortalVisitorConstants.PORTAL_PATH);
        String desktopPath = request.getParameter(PortalVisitorConstants.DESKTOP_PATH);
        String bookId      = request.getParameter(PortalVisitorConstants.EDIT_BOOK);
        String mainBook      = request.getParameter(PortalVisitorConstants.MAIN_BOOK);

        PostbackURL url = PostbackURL.createPostbackURL(request, response);
        if (mainBook != null)
        {
            url.addParameter(GenericURL.WINDOW_LABEL_PARAM, mainBook);
        }
        url.addParameter(GenericURL.MODE_PARAM, "edit");

        // Change Menu.  
        String menuId = request.getParameter(PortalVisitorConstants.MENU_ID);
        if (menuId != null)
        {
            if (menuId.equals("-1"))
            {
                menuId = null;
            }
            int newBookInstanceId = PortalVisitorManager.updateBookInstanceMenu(bookId, menuId, webAppName, portalPath, desktopPath, request.getLocale(), request);
            
            // Set new book instance id as current id, as it has changed.  We don't want to edit/view the old one.
            url.addParameter(PortalVisitorConstants.EDIT_BOOK, Integer.toString(newBookInstanceId));
            url.addParameter("_pageLabel", PortalVisitorConstants.VISITOR_TOOLS_EDIT_BOOK);
            
            contentContext.sendRedirect(url.toString());
        }

        if (request.getParameter(PortalVisitorConstants.SAVE_BOOK) != null)
        {
            String moveNavigableIds = request.getParameter(PortalVisitorConstants.MOVE_NAVIGABLE_IDS);
            String moveNavigablePositions = request.getParameter(PortalVisitorConstants.MOVE_NAVIGABLE_POSITIONS);
            String deleteNavigableIds = request.getParameter(PortalVisitorConstants.DELETE_NAVIGABLE_IDS);
            String addNavigableIds = request.getParameter(PortalVisitorConstants.ADD_NAVIGABLE_IDS);
            String addNavigablePositions = request.getParameter(PortalVisitorConstants.ADD_NAVIGABLE_POSITIONS);

            if (moveNavigableIds != null && moveNavigablePositions != null)
            {
                StringTokenizer navigableIds = new StringTokenizer(moveNavigableIds, ",");
                StringTokenizer navigablePositions = new StringTokenizer(moveNavigablePositions, ",");

                int idLength = navigableIds.countTokens();
                int positionLength = navigablePositions.countTokens();

                if (idLength != positionLength)
                {
                    //error
                }
                else
                {
                    NavigablePlacement[] navigables = new NavigablePlacement[idLength];
                    int pCount = 0;

                    while (navigableIds.hasMoreTokens())
                    {
                        String tmp    = navigableIds.nextToken();
                        String tmp2[] = tmp.split("_", 2);
                        String navigableType = tmp2[0];
                        String navigableId   = tmp2[1];

                        if (navigableType.equals(PortalVisitorConstants.PAGE))
                        {
                            navigables[pCount] = new PagePlacement(navigableId, bookId, bookId, navigablePositions.nextToken());
                        }
                        else if (navigableType.equals(PortalVisitorConstants.BOOK))
                        {
                            navigables[pCount] = new BookPlacement(navigableId, bookId, bookId, navigablePositions.nextToken());
                        }
                        pCount++;
                    }

                    PortalVisitorManager.moveNavigableInstances(navigables, webAppName, portalPath, desktopPath, request);
                }

            }

            if (addNavigableIds != null && addNavigablePositions != null)
            {
                StringTokenizer addNavigableIdsTok = new StringTokenizer(addNavigableIds, ",");
                StringTokenizer addNavigablePositionsTok = new StringTokenizer(addNavigablePositions, ",");

                int addIdLength = addNavigableIdsTok.countTokens();
                int addPositionLength = addNavigablePositionsTok.countTokens();

                if (addIdLength != addPositionLength)
                {
                    //error
                }
                else
                {
                    NavigablePlacement[] addNavigables = new NavigablePlacement[addIdLength];
                    int addPCount = 0;

                    while (addNavigableIdsTok.hasMoreTokens())
                    {
                        String addTmp    = addNavigableIdsTok.nextToken();
                        String addTmp2[] = addTmp.split("_", 3);
                        String addNavigableType = addTmp2[0];
                        // addTmp2[1] is garbage used only for correct dhtml functionality
                        String addNavigableId   = addTmp2[2];

                        if (addNavigableType.equals(PortalVisitorConstants.PAGE))
                        {
                            addNavigables[addPCount] = new PagePlacement(addNavigableId, "-1", bookId, addNavigablePositionsTok.nextToken());
                        }
                        else if (addNavigableType.equals(PortalVisitorConstants.BOOK))
                        {
                            addNavigables[addPCount] = new BookPlacement(addNavigableId, "-1", bookId, addNavigablePositionsTok.nextToken());
                        }
                        else if (addNavigableType.equals(PortalVisitorConstants.NEW_PAGE))
                        {
                            String newPageStuff[] = addNavigableId.split(":", 2);
                            String newPageLayout = newPageStuff[0];
                            String newPageName = newPageStuff[1];

                            String newPageId = PortalVisitorManager.createPageDefinition(webAppName, newPageName, "", newPageLayout, request.getLocale(), request);
                            addNavigables[addPCount] = new PagePlacement(newPageId, "-1", bookId, addNavigablePositionsTok.nextToken());
                        }
                        else if (addNavigableType.equals(PortalVisitorConstants.NEW_BOOK))
                        {
                            String newBookStuff[] = addNavigableId.split(":", 2);
                            String newBookMenu = newBookStuff[0];
                            String newBookName = newBookStuff[1];

                            if (newBookMenu.equals("-1"))
                            {
                                newBookMenu = null;
                            }

                            String newBookId = PortalVisitorManager.createBookDefinition(webAppName, newBookName, "", newBookMenu, request.getLocale(), request);
                            addNavigables[addPCount] = new BookPlacement(newBookId, "-1", bookId, addNavigablePositionsTok.nextToken());
                        }

                        addPCount++;
                    }

                    PortalVisitorManager.addNavigableInstances(addNavigables, webAppName, portalPath, desktopPath, request);
                }

            }

            if (deleteNavigableIds != null)
            {
                StringTokenizer deletedIds = new StringTokenizer(deleteNavigableIds, ",");
                int deletedLength = deletedIds.countTokens();

                NavigablePlacement[] deleteNavigables = new NavigablePlacement[deletedLength];
                int pCount2 = 0;

                while (deletedIds.hasMoreTokens())
                {
                    String tmp3    = deletedIds.nextToken();
                    String tmp4[] = tmp3.split("_", 2);
                    String navigableType2 = tmp4[0];
                    String navigableId2   = tmp4[1];

                    if (navigableType2.equals(PortalVisitorConstants.PAGE))
                    {
                        deleteNavigables[pCount2] = new PagePlacement(navigableId2, "-1", "-1", "-1");
                    }
                    else if (navigableType2.equals(PortalVisitorConstants.BOOK))
                    {
                        deleteNavigables[pCount2] = new BookPlacement(navigableId2, "-1", "-1", "-1");
                    }
                    pCount2++;
                }

                PortalVisitorManager.removeNavigableInstances(deleteNavigables, webAppName, portalPath, desktopPath, request);
            }        

            // Need to add this for the case where you delete all the pages/books in your main book.  Otherwise
            // the main main doesn't refresh correctly.  
            url.addParameter("customizePortal", PortalVisitorConstants.VISITOR_TOOLS_MAIN_PAGE);
            contentContext.sendRedirect(url.toString());
        }
        
        if(newPageLabel != null)
        {
            contentContext.setActivePage(newPageLabel);
        }

        return true;
    }
}
