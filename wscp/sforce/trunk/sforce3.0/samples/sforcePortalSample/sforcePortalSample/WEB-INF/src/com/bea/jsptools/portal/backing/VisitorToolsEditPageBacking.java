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

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bea.netuix.servlets.controls.content.backing.AbstractJspBacking;
import com.bea.netuix.servlets.controls.content.JspContentContext;

import com.bea.jsptools.portal.PortalVisitorConstants;
import com.bea.jsptools.portal.PortalVisitorManager;
import com.bea.jsptools.portal.placement.BookPlacement;
import com.bea.jsptools.portal.placement.PlaceablePlacement;
import com.bea.jsptools.portal.placement.PortletPlacement;

import com.bea.portlet.GenericURL;
import com.bea.portlet.PostbackURL;

/**
 * A simple backing file to fire a page change event
 */
public class VisitorToolsEditPageBacking extends AbstractJspBacking
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

        String webAppName    = request.getParameter(PortalVisitorConstants.WEB_APP_NAME);
        String portalPath    = request.getParameter(PortalVisitorConstants.PORTAL_PATH);
        String desktopPath   = request.getParameter(PortalVisitorConstants.DESKTOP_PATH);
        String pageId        = request.getParameter(PortalVisitorConstants.EDIT_PAGE);
        String mainBook      = request.getParameter(PortalVisitorConstants.MAIN_BOOK);

        PostbackURL url = PostbackURL.createPostbackURL(request, response);

        if (mainBook != null)
        {
            url.addParameter(GenericURL.WINDOW_LABEL_PARAM, mainBook);
        }
        url.addParameter(GenericURL.MODE_PARAM, "edit");

        // Save New Page Placement.  
        if (request.getParameter(PortalVisitorConstants.SAVE_PLACEHOLDERS) != null)
        {
            String movePlaceableIds = request.getParameter(PortalVisitorConstants.MOVE_PLACEABLE_IDS);
            String movePlaceablePlaceholders = request.getParameter(PortalVisitorConstants.MOVE_PLACEABLE_PLACEHOLDERS);
            String movePlaceablePositions = request.getParameter(PortalVisitorConstants.MOVE_PLACEABLE_POSITIONS);
            String deletePlaceableIds = request.getParameter(PortalVisitorConstants.DELETE_PLACEABLE_IDS);
            String addPlaceableIds = request.getParameter(PortalVisitorConstants.ADD_PLACEABLE_IDS);
            String addPlaceablePlaceholders = request.getParameter(PortalVisitorConstants.ADD_PLACEABLE_PLACEHOLDERS);
            String addPlaceablePositions = request.getParameter(PortalVisitorConstants.ADD_PLACEABLE_POSITIONS);
        
            if (movePlaceableIds != null && movePlaceablePlaceholders != null && movePlaceablePositions != null)
            {
                StringTokenizer placeableIds = new StringTokenizer(movePlaceableIds, ",");
                StringTokenizer placeablePlaceholders = new StringTokenizer(movePlaceablePlaceholders, ",");
                StringTokenizer placeablePositions = new StringTokenizer(movePlaceablePositions, ",");

                int idLength = placeableIds.countTokens();
                int placeholderLength = placeablePlaceholders.countTokens();
                int positionLength = placeablePositions.countTokens();

                if ((idLength != placeholderLength) || (idLength != positionLength))
                {
                    //error
                }
                else
                {
                    PlaceablePlacement[] placeables = new PlaceablePlacement[idLength];
                    int pCount = 0;

                    while (placeableIds.hasMoreTokens())
                    {
                        String tmp    = placeableIds.nextToken();
                        String tmp2[] = tmp.split("_", 2);
                        String placeableType = tmp2[0];
                        String placeableId   = tmp2[1];

                        if (placeableType.equals(PortalVisitorConstants.PORTLET))
                        {
                            placeables[pCount] = new PortletPlacement(placeableId, pageId, pageId, placeablePlaceholders.nextToken(), placeablePositions.nextToken());
                        }
                        else if (placeableType.equals(PortalVisitorConstants.BOOK))
                        {
                            placeables[pCount] = new BookPlacement(placeableId, pageId, pageId, placeablePlaceholders.nextToken(), placeablePositions.nextToken());
                        }
                        pCount++;
                    }

                    PortalVisitorManager.movePlaceableInstances(placeables, webAppName, portalPath, desktopPath, request);
                }

            }
            
            if (addPlaceableIds != null && addPlaceablePlaceholders != null && addPlaceablePositions != null)
            {
                StringTokenizer addPlaceableIdsTok = new StringTokenizer(addPlaceableIds, ",");
                StringTokenizer addPlaceablePlaceholdersTok = new StringTokenizer(addPlaceablePlaceholders, ",");
                StringTokenizer addPlaceablePositionsTok = new StringTokenizer(addPlaceablePositions, ",");

                int addIdLength = addPlaceableIdsTok.countTokens();
                int addPlaceholderLength = addPlaceablePlaceholdersTok.countTokens();
                int addPositionLength = addPlaceablePositionsTok.countTokens();

                if ((addIdLength != addPlaceholderLength) || (addIdLength != addPositionLength))
                {
                    //error
                }
                else
                {
                    PlaceablePlacement[] addPlaceables = new PlaceablePlacement[addIdLength];
                    int addPCount = 0;

                    while (addPlaceableIdsTok.hasMoreTokens())
                    {
                        String addTmp    = addPlaceableIdsTok.nextToken();
                        String addTmp2[] = addTmp.split("_", 3);
                        String addPlaceableType = addTmp2[0];
                        // addTmp2[1] is garbage used only for correct dhtml functionality
                        String addPlaceableId   = addTmp2[2];

                        if (addPlaceableType.equals(PortalVisitorConstants.PORTLET))
                        {
                            addPlaceables[addPCount] = new PortletPlacement(addPlaceableId, "-1", pageId, addPlaceablePlaceholdersTok.nextToken(), addPlaceablePositionsTok.nextToken());
                        }
                        else if (addPlaceableType.equals(PortalVisitorConstants.BOOK))
                        {
                            addPlaceables[addPCount] = new BookPlacement(addPlaceableId, "-1", pageId, addPlaceablePlaceholdersTok.nextToken(), addPlaceablePositionsTok.nextToken());
                        }
                        else if (addPlaceableType.equals(PortalVisitorConstants.NEW_BOOK))
                        {
                            String newBookStuff[] = addPlaceableId.split(":", 2);
                            String newBookMenu = newBookStuff[0];
                            String newBookName = newBookStuff[1];

                            if (newBookMenu.equals("-1"))
                            {
                                newBookMenu = null;
                            }

                            String newBookId = PortalVisitorManager.createBookDefinition(webAppName, newBookName, "", newBookMenu, request.getLocale(), request);
                            addPlaceables[addPCount] = new BookPlacement(newBookId, "-1", pageId, addPlaceablePlaceholdersTok.nextToken(), addPlaceablePositionsTok.nextToken());
                        }

                        addPCount++;
                    }

                    PortalVisitorManager.addPlaceableInstances(addPlaceables, webAppName, portalPath, desktopPath, request);
                }

            }

            if (deletePlaceableIds != null)
            {
                StringTokenizer deletedIds = new StringTokenizer(deletePlaceableIds, ",");
                int deletedLength = deletedIds.countTokens();

                PlaceablePlacement[] placeables2 = new PlaceablePlacement[deletedLength];
                int pCount2 = 0;

                while (deletedIds.hasMoreTokens())
                {
                    String tmp3    = deletedIds.nextToken();
                    String tmp4[] = tmp3.split("_", 2);
                    String placeableType2 = tmp4[0];
                    String placeableId2   = tmp4[1];

                    if (placeableType2.equals(PortalVisitorConstants.PORTLET))
                    {
                        placeables2[pCount2] = new PortletPlacement(placeableId2, "-1", "-1", "-1", "-1");
                    }
                    else if (placeableType2.equals(PortalVisitorConstants.BOOK))
                    {
                        placeables2[pCount2] = new BookPlacement(placeableId2, "-1", "-1", "-1", "-1");
                    }
                    pCount2++;
                }

                PortalVisitorManager.removePlaceableInstances(placeables2, webAppName, portalPath, desktopPath, request);
            }

            contentContext.sendRedirect(url.toString());
        }

        // Change Layout.  
        String layoutId = request.getParameter(PortalVisitorConstants.LAYOUT_ID);
        if (layoutId != null)
        {
            int newPageInstanceId = PortalVisitorManager.updatePageInstanceLayout(pageId, layoutId, webAppName, portalPath, desktopPath, request.getLocale(), request);

            // Set new page instance id as current id, as it has changed.  We don't want to edit/view the old one.
            url.addParameter(PortalVisitorConstants.EDIT_PAGE, Integer.toString(newPageInstanceId));
            String pageName = request.getParameter(PortalVisitorConstants.EDIT_PAGE_NAME);
            if (pageName != null)
            {
                url.addParameter(PortalVisitorConstants.EDIT_PAGE_NAME, pageName);
            }
            url.addParameter("_pageLabel", PortalVisitorConstants.VISITOR_TOOLS_EDIT_PAGE);
            
            contentContext.sendRedirect(url.toString());
        }

        if(newPageLabel != null)
        {
            contentContext.setActivePage(newPageLabel);
        }

        return true;
    }
}
