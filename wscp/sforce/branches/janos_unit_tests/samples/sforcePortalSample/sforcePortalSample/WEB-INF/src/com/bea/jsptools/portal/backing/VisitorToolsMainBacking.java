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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bea.jsptools.portal.PortalVisitorConstants;
import com.bea.jsptools.portal.PortalVisitorManager;
import com.bea.jsptools.portal.placement.*;

import com.bea.portlet.GenericURL;
import com.bea.portlet.PostbackURL;

/**
 * A simple backing file to fire a page change event
 */
public class VisitorToolsMainBacking extends AbstractJspBacking
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
        String mainBook    = request.getParameter(PortalVisitorConstants.MAIN_BOOK);

        String resourceId = null;
        String resourceType = null;

        PostbackURL url = PostbackURL.createPostbackURL(request, response);
        if (mainBook != null)
        {
            url.addParameter(GenericURL.WINDOW_LABEL_PARAM, mainBook);
        }
        url.addParameter(GenericURL.MODE_PARAM, "edit");
            
        // Rename the Resource
        String renameResource = request.getParameter(PortalVisitorConstants.RENAME_RESOURCE);
        if (renameResource != null)
        {
            resourceId = request.getParameter(PortalVisitorConstants.RESOURCE_ID);
            resourceType = request.getParameter(PortalVisitorConstants.RESOURCE_TYPE);
            
            if (resourceType.equals(PortalVisitorConstants.BOOK))
            {
                PortalVisitorManager.updateBookInstanceTitle(resourceId, renameResource, webAppName, portalPath, desktopPath, request.getLocale(), request);
            }
            else if (resourceType.equals(PortalVisitorConstants.PAGE))
            {
                PortalVisitorManager.updatePageInstanceTitle(resourceId, renameResource, webAppName, portalPath, desktopPath, request.getLocale(), request);
            }
            else if (resourceType.equals(PortalVisitorConstants.PORTLET))
            {
                PortalVisitorManager.updatePortletInstanceTitle(resourceId, renameResource, webAppName, portalPath, desktopPath, request.getLocale(), request);
            }

            contentContext.sendRedirect(url.toString());
        }

        // Change Theme of Resource.
        String themeId = request.getParameter(PortalVisitorConstants.THEME_ID);
        if (themeId != null)
        {
            resourceId = request.getParameter(PortalVisitorConstants.RESOURCE_ID);
            resourceType = request.getParameter(PortalVisitorConstants.RESOURCE_TYPE);

            if (themeId.equals("-1"))
            {
                themeId = null;
            }

            if (resourceType.equals(PortalVisitorConstants.BOOK))
            {
                PortalVisitorManager.updateBookInstanceTheme(resourceId, themeId, webAppName, portalPath, desktopPath, request.getLocale(), request);
            }
            else if (resourceType.equals(PortalVisitorConstants.PAGE))
            {
                PortalVisitorManager.updatePageInstanceTheme(resourceId, themeId, webAppName, portalPath, desktopPath, request.getLocale(), request);
            }
            else if (resourceType.equals(PortalVisitorConstants.PORTLET))
            {
                PortalVisitorManager.updatePortletInstanceTheme(resourceId, themeId, webAppName, portalPath, desktopPath, request.getLocale(), request);
            }

            contentContext.sendRedirect(url.toString());
        }

        // Move Resource to a new Book 
        String bookToMoveResourceTo = request.getParameter(PortalVisitorConstants.BOOK_TO_MOVE_RESOURCE_TO);
        if (bookToMoveResourceTo != null)
        {
            String resourceToMove2 = request.getParameter(PortalVisitorConstants.RESOURCE_TO_MOVE);
            String resourceTypeToMove2 = request.getParameter(PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE);
            String resourceToMoveFrom2 = request.getParameter(PortalVisitorConstants.RESOURCE_TO_MOVE_FROM);

            NavigablePlacement[] navigables = new NavigablePlacement[1];

            if (resourceTypeToMove2.equals(PortalVisitorConstants.PAGE))
            {
                navigables[0] = new PagePlacement(Integer.parseInt(resourceToMove2), Integer.parseInt(resourceToMoveFrom2), Integer.parseInt(bookToMoveResourceTo), 0);
            }
            else if (resourceTypeToMove2.equals(PortalVisitorConstants.BOOK))
            {
                navigables[0] = new BookPlacement(Integer.parseInt(resourceToMove2), Integer.parseInt(resourceToMoveFrom2), Integer.parseInt(bookToMoveResourceTo), 0);
            }

            PortalVisitorManager.moveNavigableInstances(navigables, webAppName, portalPath, desktopPath, request);

            contentContext.sendRedirect(url.toString());
        }

        // Move Resource to a new Page 
        String pageToMoveResourceTo = request.getParameter(PortalVisitorConstants.PAGE_TO_MOVE_RESOURCE_TO);
        if (pageToMoveResourceTo != null)
        {
            String resourceToMove1 = request.getParameter(PortalVisitorConstants.RESOURCE_TO_MOVE);
            String resourceTypeToMove1 = request.getParameter(PortalVisitorConstants.RESOURCE_TYPE_TO_MOVE);
            String resourceToMoveFrom1 = request.getParameter(PortalVisitorConstants.RESOURCE_TO_MOVE_FROM);

            PlaceablePlacement[] placeables = new PlaceablePlacement[1];
            if (resourceTypeToMove1.equals(PortalVisitorConstants.PORTLET))
            {
                placeables[0] = new PortletPlacement(Integer.parseInt(resourceToMove1), Integer.parseInt(resourceToMoveFrom1), Integer.parseInt(pageToMoveResourceTo), 0, 0);
            }
            else if (resourceTypeToMove1.equals(PortalVisitorConstants.BOOK))
            {
                placeables[0] = new BookPlacement(Integer.parseInt(resourceToMove1), Integer.parseInt(resourceToMoveFrom1), Integer.parseInt(pageToMoveResourceTo), 0, 0);
            }

            PortalVisitorManager.movePlaceableInstances(placeables, webAppName, portalPath, desktopPath, request);

            contentContext.sendRedirect(url.toString());
        }

        // Change Look & Feel.  Need to send redirect upon completion so page reloads with new L&F.
        String lookId = request.getParameter(PortalVisitorConstants.LOOK_AND_FEEL_ID);
        if (lookId != null)
        {
            PortalVisitorManager.updateDesktopInstance(webAppName, portalPath, desktopPath, lookId, request.getLocale(), request);

            contentContext.sendRedirect(url.toString());
        }

        // If the user had 'Show Page Contents' selected, we put this in the session...
        String showPageContents = request.getParameter(PortalVisitorConstants.SHOW_PAGE_CONTENTS);
        if (showPageContents != null)
        {
            request.getSession().setAttribute(PortalVisitorConstants.SHOW_PAGE_CONTENTS, showPageContents);
        }

        if(newPageLabel != null)
        {
            contentContext.setActivePage(newPageLabel);
        }

        String customizePortal = request.getParameter("customizePortal");
        if (customizePortal != null)
        {
            contentContext.sendRedirect(url.toString());
        }

        return true;
    }
}
