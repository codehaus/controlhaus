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

package com.bea.jsptools.portal;

import com.bea.netuix.application.definition.*;

import com.bea.netuix.application.exception.ObjectNotFoundException;
import com.bea.netuix.application.exception.NotEntitledException;
import com.bea.netuix.application.exception.MissingDataException;

import com.bea.netuix.application.identifier.*;

import com.bea.netuix.application.instance.BookInstance;
import com.bea.netuix.application.instance.DesktopInstance;
import com.bea.netuix.application.instance.NavigableInstance;
import com.bea.netuix.application.instance.PageInstance;
import com.bea.netuix.application.instance.PlaceableInstance;
import com.bea.netuix.application.instance.PortletInstance;

import com.bea.netuix.application.localization.definition.LocalizationResource;

import com.bea.netuix.application.view.BookView;
import com.bea.netuix.application.view.DesktopView;
import com.bea.netuix.application.view.PageView;
import com.bea.netuix.application.view.PortletView;

import com.bea.netuix.application.manager.CustomizationContext;

import com.bea.netuix.application.manager.entitlements.PlaceholderEntitlementResource;
import com.bea.netuix.application.manager.entitlements.PortalEntitlementHelper;

import com.bea.jsptools.portal.PortalBeanManager;

import com.bea.jsptools.portal.placement.BookPlacement;
import com.bea.jsptools.portal.placement.NavigablePlacement;
import com.bea.jsptools.portal.placement.PagePlacement;
import com.bea.jsptools.portal.placement.PlaceablePlacement;
import com.bea.jsptools.portal.placement.PortletPlacement;

import com.bea.p13n.management.ApplicationHelper;

import com.bea.p13n.entitlements.Authorization;

import com.bea.p13n.entitlements.common.EntitlementHelper;

import com.bea.p13n.entitlements.management.SecurityPolicyManager;

import com.bea.p13n.entitlements.policy.SecurityPolicyItem;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.*;
import java.rmi.RemoteException;
import java.text.Collator;

import javax.servlet.http.HttpServletRequest;

public class PortalVisitorManager
{
    public static String categoryOptions = "";
    public static String allPortletOptions = "";
    public static String categoryJavaScriptArrays = "";
    public static boolean hasAnyCategories = false;

    public static void updateDesktopInstance(String webapp, String portalPath, String desktopPath, String lookId, Locale locale, HttpServletRequest request)
    {
        DesktopView desktopView = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }
            customizationContext.setVisitorMode(true);

            LookAndFeelDefinitionId lfdi = new LookAndFeelDefinitionId(Integer.parseInt(lookId));

            desktopView = PortalBeanManager.getPortalCustomizationManager().getDesktopView(customizationContext, webapp, new PortalPath(portalPath), new DesktopPath(desktopPath));
            DesktopInstanceId dii = desktopView.getDesktopInstanceId();
            DesktopInstance di = PortalBeanManager.getPortalCustomizationManager().getDesktopInstance(customizationContext, dii);
            di.setLookAndFeelDefinitionId(lfdi); 
            PortalBeanManager.getPortalCustomizationManager().updateDesktopInstance(customizationContext, di);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static BookDefinition[] getPublicBookDefinitions(String webapp, String portalPath, String desktopPath, Locale locale, HttpServletRequest request)
    {
        BookDefinition[] books = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            books = PortalBeanManager.getBookDefinitionManager().getPublicBookDefinitions(customizationContext,  DesktopDefinitionId.createDesktopDefinitionId(webapp, portalPath, desktopPath));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return books;
    }

    public static PageDefinition[] getPublicPageDefinitions(String webapp, String portalPath, String desktopPath,  Locale locale, HttpServletRequest request)
    {
        PageDefinition[] pages = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            pages = PortalBeanManager.getPageDefinitionManager().getPublicPageDefinitions(customizationContext, DesktopDefinitionId.createDesktopDefinitionId(webapp, portalPath, desktopPath));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return pages;
    }

    public static PortletDefinition[] getPortletDefinitions(String webapp, Locale locale, HttpServletRequest request)
    {
        PortletDefinition[] portlets = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            portlets = PortalBeanManager.getPortletDefinitionManager().getPortletDefinitions(customizationContext, webapp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return portlets;

    }

    public static PortletInstance getPortletInstance(String id, Locale locale, HttpServletRequest request)
    {
        PortletInstance portletInstance = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            PortletInstanceId pii = new PortletInstanceId(Integer.parseInt(id));
            portletInstance = PortalBeanManager.getPortalCustomizationManager().getPortletInstance(customizationContext, pii);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return portletInstance;
    }

    public static String getLayoutHtml(String layoutHTMLPath, String currentWebApp)
    {
        String layoutHTML = "";

        String entAppName = ApplicationHelper.getApplicationName();
        String webAppUri = "";
        boolean exists = false;

        try
        {
            webAppUri  = ApplicationHelper.getWebAppUri(entAppName, currentWebApp);
            exists = ApplicationHelper.resourceExists(webAppUri + layoutHTMLPath);
        }
        catch (Exception e)
        {
            return null;
        }
       
        if (layoutHTMLPath != null && exists)
        {  
            InputStream is = null;
            try 
            {
                is = ApplicationHelper.getResourceAsStream(webAppUri + layoutHTMLPath);
                if (is == null)
                {
                    return null;
                }
                else
                {
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);

                    String line = null;
                    while ((line = br.readLine()) != null)
                    {
                        layoutHTML += line + "\n";				 	   
                    }
                    
                    isr.close();
                    br.close();
                }
            }
            catch (IOException ioe)
            {
                return null;
            }
            finally
            {
                try
                {
                    if (is != null)
                    {
                        is.close();
                    }
                }
                catch (IOException ignore)
                {

                }
            }
        }
        else
        {
            return null;
        }

        return layoutHTML;
        
    }

    public static int updateBookInstanceMenu(String bookId, String newMenuId, String webapp, String portalPath, String desktopPath, Locale locale, HttpServletRequest request)
    {
        CustomizationContext customizationContext = null;
        int returnInstanceId = 0;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }
            customizationContext.setVisitorMode(true);

            BookInstanceId bii = new BookInstanceId(Integer.parseInt(bookId));
            BookInstance bookInstance = PortalBeanManager.getPortalCustomizationManager().getBookInstance(customizationContext, bii);

            MenuDefinitionId mdi = null;
            if (newMenuId != null)
            {
                mdi = new MenuDefinitionId(Integer.parseInt(newMenuId));
            }
    
            bookInstance.setMenuDefinitionId(mdi);

            BookInstance newInstance = PortalBeanManager.getPortalCustomizationManager().updateBookInstance(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), bookInstance);
            returnInstanceId = newInstance.getBookInstanceId().getId();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return returnInstanceId;
    }

    public static void updateBookInstanceTheme(String bookId, String newThemeId, String webapp, String portalPath, String desktopPath, Locale locale, HttpServletRequest request)
    {
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }
            customizationContext.setVisitorMode(true);

            BookInstanceId bii = new BookInstanceId(Integer.parseInt(bookId));
            BookInstance bookInstance = PortalBeanManager.getPortalCustomizationManager().getBookInstance(customizationContext, bii);

            ThemeDefinitionId tdi = null;
            if (newThemeId != null)
            {
                tdi = new ThemeDefinitionId(Integer.parseInt(newThemeId));
            }
    
            bookInstance.setThemeDefinitionId(tdi);

            PortalBeanManager.getPortalCustomizationManager().updateBookInstance(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), bookInstance);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void updatePortletInstanceTheme(String portletId, String newThemeId, String webapp, String portalPath, String desktopPath, Locale locale, HttpServletRequest request)
    {
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }
            customizationContext.setVisitorMode(true);

            PortletInstanceId pii = new PortletInstanceId(Integer.parseInt(portletId));
            PortletInstance portletInstance = PortalBeanManager.getPortalCustomizationManager().getPortletInstance(customizationContext, pii);

            ThemeDefinitionId tdi = null;
            if (newThemeId != null)
            {
                tdi = new ThemeDefinitionId(Integer.parseInt(newThemeId));
            }
    
            portletInstance.setThemeDefinitionId(tdi);

            PortalBeanManager.getPortalCustomizationManager().updatePortletInstance(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), portletInstance);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void updatePageInstanceTheme(String pageId, String newThemeId, String webapp, String portalPath, String desktopPath, Locale locale, HttpServletRequest request)
    {
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }
            customizationContext.setVisitorMode(true);

            PageInstanceId pii = new PageInstanceId(Integer.parseInt(pageId));
            PageInstance pageInstance = PortalBeanManager.getPortalCustomizationManager().getPageInstance(customizationContext, pii);

            ThemeDefinitionId tdi = null;
            if (newThemeId != null)
            {
                tdi = new ThemeDefinitionId(Integer.parseInt(newThemeId));
            }
    
            pageInstance.setThemeDefinitionId(tdi);

            PortalBeanManager.getPortalCustomizationManager().updatePageInstance(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), pageInstance);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static int updatePageInstanceLayout(String pageId, String layoutId, String webapp, String portalPath, String desktopPath, Locale locale, HttpServletRequest request)
    {
        CustomizationContext customizationContext = null;
        int returnInstanceId = 0;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }
            customizationContext.setVisitorMode(true);

            PageInstanceId pii = new PageInstanceId(Integer.parseInt(pageId));
            PageInstance pageInstance = PortalBeanManager.getPortalCustomizationManager().getPageInstance(customizationContext, pii);

            LayoutDefinitionId ldi = null;
            if (layoutId != null)
            {
                ldi = new LayoutDefinitionId(Integer.parseInt(layoutId));
            }
    
            pageInstance.setLayoutDefinitionId(ldi);

            PageInstance newInstance = PortalBeanManager.getPortalCustomizationManager().updatePageInstance(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), pageInstance);
            returnInstanceId = newInstance.getPageInstanceId().getId();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return returnInstanceId;
    }

    public static void updatePageInstanceTitle(String pageId, String newTitle, String webapp, String portalPath, String desktopPath, Locale locale, HttpServletRequest request)
    {
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }
            customizationContext.setVisitorMode(true);

            PageInstanceId pii = new PageInstanceId(Integer.parseInt(pageId));
            PageInstance pageInstance = PortalBeanManager.getPortalCustomizationManager().getPageInstance(customizationContext, pii);
            pageInstance.setInstanceTitle(newTitle);

            PortalBeanManager.getPortalCustomizationManager().updatePageInstance(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), pageInstance);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void updateBookInstanceTitle(String bookId, String newTitle, String webapp, String portalPath, String desktopPath, Locale locale, HttpServletRequest request)
    {
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }
            customizationContext.setVisitorMode(true);

            BookInstanceId bii = new BookInstanceId(Integer.parseInt(bookId));
            BookInstance bookInstance = PortalBeanManager.getPortalCustomizationManager().getBookInstance(customizationContext, bii);
            bookInstance.setInstanceTitle(newTitle);

            PortalBeanManager.getPortalCustomizationManager().updateBookInstance(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), bookInstance);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void updatePortletInstanceTitle(String portletId, String newTitle, String webapp, String portalPath, String desktopPath, Locale locale, HttpServletRequest request)
    {
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }
            customizationContext.setVisitorMode(true);

            PortletInstanceId pii = new PortletInstanceId(Integer.parseInt(portletId));
            PortletInstance portletInstance = PortalBeanManager.getPortalCustomizationManager().getPortletInstance(customizationContext, pii);
            portletInstance.setTitle(newTitle);

            PortalBeanManager.getPortalCustomizationManager().updatePortletInstance(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), portletInstance);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static LookAndFeelDefinition[] getLookAndFeelDefinitions(String webapp, Locale locale, HttpServletRequest request)
    {
        LookAndFeelDefinition[] lookAndFeels = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            lookAndFeels = PortalBeanManager.getLookAndFeelDefinitionManager().getLookAndFeelDefinitions(customizationContext, webapp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return lookAndFeels;

    }

    public static MenuDefinition[] getMenuDefinitions(String webapp, Locale locale, HttpServletRequest request)
    {
        MenuDefinition[] menus = null;
        CustomizationContext customizationContext = null;

        try 
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            menus = PortalBeanManager.getBookDefinitionManager().getMenuDefinitions(customizationContext, webapp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return menus;

    }

    public static ThemeDefinition[] getThemeDefinitions(String webapp, Locale locale, HttpServletRequest request)
    {
        ThemeDefinition[] themes = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            themes = PortalBeanManager.getLookAndFeelDefinitionManager().getThemeDefinitions(customizationContext, webapp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return themes;

    }

    public static LayoutDefinition[] getLayoutDefinitions(String webapp, Locale locale, HttpServletRequest request)
    {
        LayoutDefinition[] layouts = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            layouts = PortalBeanManager.getPageDefinitionManager().getLayoutDefinitions(customizationContext, webapp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return layouts;

    }

    public static void addPlaceableInstances(PlaceablePlacement[] placeables, String webapp, String portalPath, String desktopPath, HttpServletRequest request)
    {
        PlaceableDefinitionId placeableDefinitionId = null;
        int thePlaceableId = 0;

        try
        {
            for (int i=0; i<placeables.length; i++)
            {
                CustomizationContext customizationContext = new CustomizationContext(Locale.getDefault(), request);
                customizationContext.setVisitorMode(true);

                PlaceablePlacement placeable = placeables[i];

                PageInstanceId pii = new PageInstanceId(placeable.getContainerId());
                DesktopView desktopView = PortalBeanManager.getPortalCustomizationManager().getDesktopView(customizationContext, webapp, new PortalPath(portalPath), new DesktopPath(desktopPath));
                customizationContext.setAdminDesktopInstanceId(desktopView.getDesktopInstanceId());

                if (placeable instanceof PortletPlacement)
                {
                    placeableDefinitionId = new PortletDefinitionId(placeable.getId());
                }
                else if (placeable instanceof BookPlacement)
                {
                    placeableDefinitionId = new BookDefinitionId(placeable.getId());
                }
    
                PageInstance thePage = PortalBeanManager.getPortalCustomizationManager().getPageInstance(customizationContext, pii);
                LayoutDefinitionId ldi = thePage.getLayoutDefinitionId();
                LayoutDefinition layoutDefinition = PortalBeanManager.getPageDefinitionManager().getLayoutDefinition(customizationContext, ldi);

                PlaceholderDefinition[] placeholderDefinitions = layoutDefinition.getPlaceholderDefinitions();

                int placePos = placeable.getPlaceholder();

                if (placeholderDefinitions.length > placePos)
                {
                    PlaceholderDefinition thePlaceholder = placeholderDefinitions[placePos];
                    PortalBeanManager.getPortalCustomizationManager().addPlaceable(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), pii, placeableDefinitionId, thePlaceholder.getPlaceholderDefinitionId(), placeable.getPosition());
                }
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void removePlaceableInstances(PlaceablePlacement[] placeables, String webapp, String portalPath, String desktopPath, HttpServletRequest request)
    {
        PlaceableInstance placeableInstance = null;
        try
        {
            CustomizationContext customizationContext = new CustomizationContext(request.getLocale(), request);
            customizationContext.setVisitorMode(true);

            for (int i = 0; i < placeables.length; i++)
            {
                PlaceablePlacement placeable = placeables[i];

                if (placeable instanceof PortletPlacement)
                {
                    PortletInstanceId pii = new PortletInstanceId(placeable.getId());
                    placeableInstance = PortalBeanManager.getPortalCustomizationManager().getPortletInstance(customizationContext, pii);
                }
                else if (placeable instanceof BookPlacement)
                {
                    BookInstanceId bii = new BookInstanceId(placeable.getId());
                    placeableInstance = PortalBeanManager.getPortalCustomizationManager().getBookInstance(customizationContext, bii);
                }

                PortalBeanManager.getPortalCustomizationManager().removePlaceable(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), placeableInstance);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void movePlaceableInstances(PlaceablePlacement[] placeables, String webapp, String portalPath, String desktopPath, HttpServletRequest request)
    {
        PlacementId placementId = null;
        int thePlaceableId = 0;

        try
        {
            for (int i=0; i<placeables.length; i++)
            {
                CustomizationContext customizationContext = new CustomizationContext(Locale.getDefault(), request);
                customizationContext.setVisitorMode(true);

                PlaceablePlacement placeable = placeables[i];

                PageInstanceId pii = new PageInstanceId(placeable.getParentId());
                //DesktopView desktopView = PortalBeanManager.getPortalCustomizationManager().getDesktopView(customizationContext, webapp, new PortalPath(portalPath), new DesktopPath(desktopPath));
                //customizationContext.setAdminDesktopInstanceId(desktopView.getDesktopInstanceId());
                DesktopDefinitionId ddi = new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath));
                PageView pageView = PortalBeanManager.getPortalCustomizationManager().getPageView(customizationContext, ddi, pii);

                if (placeable instanceof PortletPlacement)
                {
                    PortletView portletView = pageView.getPortletView(new PortletInstanceId(placeable.getId()));
                    if (portletView != null)
                    {
                        placementId = portletView.getPlacementId();
                    }
                }
                else if (placeable instanceof BookPlacement)
                {
                    BookView bookView = pageView.getBookView(new BookInstanceId(placeable.getId()));
                    if (bookView != null)
                    {
                        placementId = bookView.getPlacementId();
                    }
                }
                
                PageInstanceId pageToMoveTo = new PageInstanceId(placeable.getContainerId());
                PageInstance thePage = PortalBeanManager.getPortalCustomizationManager().getPageInstance(customizationContext, pageToMoveTo);
                LayoutDefinitionId ldi = thePage.getLayoutDefinitionId();
                LayoutDefinition layoutDefinition = PortalBeanManager.getPageDefinitionManager().getLayoutDefinition(customizationContext, ldi);

                PlaceholderDefinition[] placeholderDefinitions = layoutDefinition.getPlaceholderDefinitions();

                int placePos = placeable.getPlaceholder();

                if (placeholderDefinitions.length > placePos)
                {
                    PlaceholderDefinition thePlaceholder = placeholderDefinitions[placePos];
                    PortalBeanManager.getPortalCustomizationManager().movePlaceable(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), placementId, pageToMoveTo, thePlaceholder.getPlaceholderDefinitionId(), placeable.getPosition());
                }
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void addNavigableInstances(NavigablePlacement[] navigables, String webapp, String portalPath, String desktopPath, HttpServletRequest request)
    {
        NavigableDefinitionId navigableDefinitionId = null;
        try
        {
            CustomizationContext customizationContext = new CustomizationContext(request.getLocale(), request);
            customizationContext.setVisitorMode(true);

            for (int i = 0; i < navigables.length; i++)
            {
                NavigablePlacement navigable = navigables[i];
                BookInstanceId bookInstanceId = new BookInstanceId(navigable.getContainerId());

                if (navigable instanceof PagePlacement)
                {
                    navigableDefinitionId = new PageDefinitionId(navigable.getId());
                }
                else if (navigable instanceof BookPlacement)
                {
                    navigableDefinitionId = new BookDefinitionId(navigable.getId());
                }

                //TODO: deal with alignment (passing in "0" right now)
                PortalBeanManager.getPortalCustomizationManager().addNavigable(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), bookInstanceId, navigableDefinitionId, navigable.getPosition(), 0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void removeNavigableInstances(NavigablePlacement[] navigables, String webapp, String portalPath, String desktopPath, HttpServletRequest request)
    {
        NavigableInstance navigableInstance = null;
        try
        {
            CustomizationContext customizationContext = new CustomizationContext(request.getLocale(), request);
            customizationContext.setVisitorMode(true);

            for (int i = 0; i < navigables.length; i++)
            {
                NavigablePlacement navigable = navigables[i];

                if (navigable instanceof PagePlacement)
                {
                    PageInstanceId pii = new PageInstanceId(navigable.getId());
                    navigableInstance = PortalBeanManager.getPortalCustomizationManager().getPageInstance(customizationContext, pii);
                }
                else if (navigable instanceof BookPlacement)
                {
                    BookInstanceId bii = new BookInstanceId(navigable.getId());
                    navigableInstance = PortalBeanManager.getPortalCustomizationManager().getBookInstance(customizationContext, bii);
                }

                PortalBeanManager.getPortalCustomizationManager().removeNavigable(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), navigableInstance);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void moveNavigableInstances(NavigablePlacement[] navigables, String webapp, String portalPath, String desktopPath, HttpServletRequest request)
    {
        NavigableInstanceId navigableInstanceId = null;
        try
        {
            CustomizationContext customizationContext = new CustomizationContext(request.getLocale(), request);
            customizationContext.setVisitorMode(true);

            for (int i = 0; i < navigables.length; i++)
            {
                NavigablePlacement navigable = navigables[i];
                int position = navigable.getPosition();
                BookInstanceId fromBookInstanceId = new BookInstanceId(navigable.getParentId());
                BookInstanceId toBookInstanceId = new BookInstanceId(navigable.getContainerId());

                if (navigable instanceof PagePlacement)
                {
                    navigableInstanceId = new PageInstanceId(navigable.getId());
                }
                else if (navigable instanceof BookPlacement)
                {
                    navigableInstanceId = new BookInstanceId(navigable.getId());
                }
    
                //TODO: deal with alignment (passing in "0" right now)
                PortalBeanManager.getPortalCustomizationManager().moveNavigable(customizationContext, new DesktopDefinitionId(webapp, new PortalPath(portalPath), new DesktopPath(desktopPath)), fromBookInstanceId, toBookInstanceId, navigableInstanceId, position, 0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String createBookDefinition(String webapp, String title, String desc, String menuId, Locale locale, HttpServletRequest request)
    {
        String newBookId = "0";
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            LocalizationResource lr = new LocalizationResource(locale, title, desc);

            MenuDefinitionId mdi = null;
            if (menuId != null)
            {
                mdi = new MenuDefinitionId(Integer.parseInt(menuId));
            }
            BookDefinition bd = new BookDefinition(lr,
                                                   MarkupDefinition.MARKUP_BOOK_NO_MIN_NO_MAX_ID,
                                                   null,
                                                   false,
                                                   false,
                                                   webapp,
                                                   mdi,
                                                   null,
                                                   (short)0);

            BookDefinition newBd = PortalBeanManager.getBookDefinitionManager().createBookDefinition(customizationContext, bd);
            newBookId = String.valueOf(newBd.getBookDefinitionId().getId());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return newBookId;
    }

    public static String createPageDefinition(String webapp, String title, String desc, String layoutId, Locale locale, HttpServletRequest request)
    {
        String newPageId = "0";
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }
            
            LocalizationResource lr = new LocalizationResource(locale, title, desc);

            LayoutDefinitionId ldi = new LayoutDefinitionId(Integer.parseInt(layoutId));
            PageDefinition pd = new PageDefinition(lr,
                                                   MarkupDefinition.MARKUP_PAGE_ID,
                                                   null,
                                                   false,
                                                   false,
                                                   webapp,
                                                   ldi,
                                                   null);

            PageDefinition newPd = PortalBeanManager.getPageDefinitionManager().createPageDefinition(customizationContext, pd);
            newPageId = String.valueOf(newPd.getPageDefinitionId().getId());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return newPageId;
    }

    public static boolean isPlaceholderLocked(String webApp, String portalPath, String desktopPath, String pageId, String placeholderNum, HttpServletRequest request)
    {
        boolean isLocked = false;

        try 
        {
            PageInstanceId pii = new PageInstanceId(Integer.parseInt(pageId));

            CustomizationContext customizationContext = new CustomizationContext(Locale.getDefault(), request);
            PageInstance thePage = PortalBeanManager.getPortalCustomizationManager().getPageInstance(customizationContext, pii);
            
            LayoutDefinitionId ldi = thePage.getLayoutDefinitionId();
            LayoutDefinition layoutDefinition = PortalBeanManager.getPageDefinitionManager().getLayoutDefinition(customizationContext, ldi);

            PlaceholderDefinition[] placeholderDefinitions = layoutDefinition.getPlaceholderDefinitions();

            int placeNum = Integer.parseInt(placeholderNum);

            if (placeNum < placeholderDefinitions.length)
            {
                DesktopDefinitionId ddi = new DesktopDefinitionId(webApp, new PortalPath(portalPath), new DesktopPath(desktopPath));
                PageDefinitionId pdi = thePage.getPageDefinitionId();
                isLocked = PortalVisitorManager.doesPlaceholderPolicyExist(ddi, pdi, placeholderDefinitions[placeNum].getPlaceholderDefinitionId(), request);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return isLocked;
    }

private static boolean doesPlaceholderPolicyExist(DesktopDefinitionId desktopDefId, PageDefinitionId pageDefId, PlaceholderDefinitionId placeholderDefId, HttpServletRequest request)
    {
        try
        {
            PlaceholderEntitlementResource resource = PlaceholderEntitlementResource.createPlaceholderUpdateEntitlementResource(desktopDefId, pageDefId, placeholderDefId);
            // Check if any policy exists on placeholder...
            if (Authorization.isProtectedResource(resource))
            {
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public static BookView getBookView(String bookInstanceId, String webAppName, String portalPath, String desktopPath, Locale locale, HttpServletRequest request)
    {
        BookView bookView = null;
        CustomizationContext customizationContext = null;

        BookInstanceId bii = new BookInstanceId(Integer.parseInt(bookInstanceId));

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            customizationContext.setVisitorMode(true);
            DesktopDefinitionId ddi = new DesktopDefinitionId(webAppName, new PortalPath(portalPath), new DesktopPath(desktopPath));
            bookView = PortalBeanManager.getPortalCustomizationManager().getBookView(customizationContext, ddi, bii);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return bookView;
    }

    public static PageView getPageView(String pageInstanceId, String webAppName, String portalPath, String desktopPath, Locale locale, HttpServletRequest request)
    {
        PageView pageView = null;
        CustomizationContext customizationContext = null;

        PageInstanceId pii = new PageInstanceId(Integer.parseInt(pageInstanceId));

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            customizationContext.setVisitorMode(true);
            DesktopDefinitionId ddi = new DesktopDefinitionId(webAppName, new PortalPath(portalPath), new DesktopPath(desktopPath));
            pageView = PortalBeanManager.getPortalCustomizationManager().getPageView(customizationContext, ddi, pii);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return pageView;
    }

    public static DesktopView getDesktopView(String portalPath, String webAppName, String desktopPath, Locale locale, HttpServletRequest request)
    {
        DesktopView desktopView = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            customizationContext.setVisitorMode(true);
            desktopView = PortalBeanManager.getPortalCustomizationManager().getDesktopView(customizationContext, webAppName, new PortalPath(portalPath), new DesktopPath(desktopPath));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return desktopView;
    }

    public static PageInstance getPageInstance(String id, Locale locale, HttpServletRequest request)
    {
        PageInstance pageInstance = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            PageInstanceId pii = new PageInstanceId(Integer.parseInt(id));
            pageInstance = PortalBeanManager.getPortalCustomizationManager().getPageInstance(customizationContext, pii);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return pageInstance;
    }

    public static BookInstance getBookInstance(String id, Locale locale, HttpServletRequest request)
    {
        BookInstance bookInstance = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            BookInstanceId bii = new BookInstanceId(Integer.parseInt(id));
            bookInstance = PortalBeanManager.getPortalCustomizationManager().getBookInstance(customizationContext, bii);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return bookInstance;
    }

    public static void generatePortletCategoryArrays(String webAppName, String indentStr, Locale locale, HttpServletRequest request)
    {
        // Create the option for the default implicit "All Portlets" root category
        categoryOptions = "<option value='categoryAll'>All Portlets</option>\n";
        allPortletOptions = "";

        // Get the root category definition
        PortletCategoryDefinition rootCategoryDef = getPortletRootCategoryDefinition(webAppName, locale, request);

        PortletDefinition[] portlets = getPortletDefinitions(webAppName, locale, request);
        HashMap portletTitlesHash = getPortletsHash(portlets);

        Set sortedKeySet = portletTitlesHash.keySet();
        ArrayList rootPortlets = new ArrayList(sortedKeySet);
        Collections.sort(rootPortlets, Collator.getInstance(locale));
        String categoryAllJavaScriptArray = "var categoryAll = new Array(";
        String categoryAllValuesJavaScriptArray = "var categoryAllValues = new Array(";

        for (int i = 0; i < rootPortlets.size(); i++)
        {
            String title = (String)rootPortlets.get(i);
            ArrayList portletsOfKey = (ArrayList)portletTitlesHash.get(title);
            for (int j = 0; j < portletsOfKey.size(); j++) {
                HashMap portletInfoHash = (HashMap) portletsOfKey.get(j);
                int id = ((Integer)portletInfoHash.get(title)).intValue();
                allPortletOptions += "<option value='" + id + "'>" + title + "</option>\n";
                categoryAllJavaScriptArray += ((i>0)?",":"") + "\"" + title + "\"";
                categoryAllValuesJavaScriptArray += ((i>0)?",":"") + "\"" + id + "\"";
            }
        }

        categoryAllJavaScriptArray += ");\n";
        categoryAllValuesJavaScriptArray += ");\n";
        categoryJavaScriptArrays = categoryAllJavaScriptArray + categoryAllValuesJavaScriptArray;

        // Create options and arrays for all child categories
        if (rootCategoryDef != null)
        {
            getChildrenCategoryNodes(rootCategoryDef, indentStr, 1, locale, request);
        }
    }

    /*
     *  This is a recursive function that gets all of the children for the passed in portlet category definition.
     *  It also creates the strings needed to generate the <option ...>s needed for each category
     */
    private static void getChildrenCategoryNodes(PortletCategoryDefinition pcd, String indentStr, int indentLevels, Locale locale, HttpServletRequest request)
    {
        PortletCategoryDefinition[] portletCatDefs = getPortletSubCategoryDefinitions(pcd.getPortletCategoryDefinitionId(), locale, request);
        if (portletCatDefs.length > 0)
            hasAnyCategories = true;
        for(int i=0;i<portletCatDefs.length;i++)
        {
            String categoryName = portletCatDefs[i].getLocalizationResource().getTitle();
            String categoryId = portletCatDefs[i].getPortletCategoryDefinitionId().toString();
            String thisIndent = "";
            for (int j=0; j < indentLevels; j++)
            {
                thisIndent += indentStr;
            }
            categoryOptions += "<option value='category" + categoryId + "'>" + thisIndent + categoryName + "</option>\n";

            HashMap portletTitlesHash = getPortletsForCategory(portletCatDefs[i], locale, request);

            Set sortedKeySet = portletTitlesHash.keySet();
            ArrayList portlets = new ArrayList(sortedKeySet);
            Collections.sort(portlets, Collator.getInstance(locale));
            String categoryJavaScriptArray = "var category" + categoryId + " = new Array(";
            String categoryValuesJavaScriptArray = "var category" + categoryId + "Values = new Array(";

            for (int k = 0; k < portlets.size(); k++)
            {
                String title = (String)portlets.get(k);
                ArrayList portletsOfKey = (ArrayList)portletTitlesHash.get(title);
                for (int m = 0; m < portletsOfKey.size(); m++) {
                    HashMap portletInfoHash = (HashMap) portletsOfKey.get(m);
                    int id = ((Integer)portletInfoHash.get(title)).intValue();
                    categoryJavaScriptArray += ((k>0)?",":"") + "\"" + title + "\"";
                    categoryValuesJavaScriptArray += ((k>0)?",":"") + "\"" + id + "\"";
                }
            }

            categoryJavaScriptArray += ");\n";
            categoryValuesJavaScriptArray += ");\n";
            categoryJavaScriptArrays += categoryJavaScriptArray + categoryValuesJavaScriptArray;

            // Now recursively get the children of this child category
            getChildrenCategoryNodes(portletCatDefs[i], indentStr, indentLevels + 1, locale, request);
        }

    }

    public static PortletCategoryDefinition getPortletRootCategoryDefinition(String webAppName, Locale locale, HttpServletRequest request)
    {
        PortletCategoryDefinition portletCategory = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            portletCategory = PortalBeanManager.getPortletCategoryManager().getRootCategory(customizationContext, webAppName);
        }
        catch (RemoteException re)
        {
            re.printStackTrace();
        }
        catch (ObjectNotFoundException onfe)
        {
            try
            {
                portletCategory = PortalBeanManager.getPortletCategoryManager().
                                    createRootCategory(customizationContext,
                                      new PortletCategoryDefinition(
                                        null,
                                        new LocalizationResource(locale, "categoryRoot", "All Portlets"),
                                        webAppName,
                                        true
                                      )
                                    );
            }
            catch (NotEntitledException nee)
            {
                nee.printStackTrace();
            }
            catch (RemoteException re)
            {
                re.printStackTrace();
            }
            catch (MissingDataException mde)
            {
                mde.printStackTrace();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return portletCategory;

    }

    public static PortletCategoryDefinition[] getPortletSubCategoryDefinitions(PortletCategoryDefinitionId pcdId, Locale locale, HttpServletRequest request)
    {
        PortletCategoryDefinition[] portletCategories = null;
        CustomizationContext customizationContext = null;

        try
        {
            if (locale == null)
            {
                customizationContext = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                customizationContext = new CustomizationContext(locale, request);
            }

            portletCategories = PortalBeanManager.getPortletCategoryManager().getSubCategories(customizationContext, pcdId, 0, 9999);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return portletCategories;

    }

    public static HashMap getPortletsForCategory(PortletCategoryDefinition portletCategoryDefinition, Locale locale, HttpServletRequest request)
    {
        HashMap portletTitlesHash = new HashMap();
        CustomizationContext cc;
        try
        {
            if (locale == null)
            {
                cc = new CustomizationContext(Locale.getDefault(), request);
            }
            else
            {
                cc = new CustomizationContext(locale, request);
            }

            //Now get all of the portlets that belong to theis category
            PortletDefinition[] portlets = null;
            try
            {
                portlets = PortalBeanManager.getPortletCategoryManager().getPortletDefinitions(cc, portletCategoryDefinition.getPortletCategoryDefinitionId(), 0, 9999);
                portletTitlesHash = getPortletsHash(portlets);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
         return portletTitlesHash;

    }

    public static HashMap getPortletsHash(PortletDefinition[] portlets)
    {
        HashMap portletTitlesHash = new HashMap();
        for (int i = 0; i < portlets.length; i++)
          {
              PortletDefinition tmp = portlets[i];
              if (tmp != null)
              {
                  LocalizationResource lr = tmp.getLocalizationResource();
                  String title = "&nbsp;";
                  if (lr != null)
                  {
                      title = lr.getTitle();
                  }
                  int id = tmp.getPortletDefinitionId().getId();

                 ArrayList hashList;
                 if (portletTitlesHash.containsKey(title))
                 {
                     hashList = (ArrayList)portletTitlesHash.get(title);
                 } else
                 {
                     hashList = new ArrayList();
                 }

                 HashMap tempHash = new HashMap();
                 tempHash.put(title,new Integer(id));
                 hashList.add(tempHash);
                 portletTitlesHash.put(title,hashList);
             }
          }
        return portletTitlesHash;
    }
}
