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

import com.bea.netuix.application.manager.persistence.BookDefinitionManager;
import com.bea.netuix.application.manager.persistence.BookDefinitionManagerHome;
import com.bea.netuix.application.manager.persistence.DesktopDefinitionManager;
import com.bea.netuix.application.manager.persistence.DesktopDefinitionManagerHome;
import com.bea.netuix.application.manager.persistence.LookAndFeelDefinitionManager;
import com.bea.netuix.application.manager.persistence.LookAndFeelDefinitionManagerHome;
import com.bea.netuix.application.manager.persistence.MarkupDefinitionManagerHome;
import com.bea.netuix.application.manager.persistence.MarkupDefinitionManager;
import com.bea.netuix.application.manager.persistence.PageDefinitionManagerHome;
import com.bea.netuix.application.manager.persistence.PageDefinitionManager;
import com.bea.netuix.application.manager.persistence.PortalCustomizationManager;
import com.bea.netuix.application.manager.persistence.PortalCustomizationManagerHome;
import com.bea.netuix.application.manager.persistence.PortalDefinitionManager;
import com.bea.netuix.application.manager.persistence.PortalDefinitionManagerHome;
import com.bea.netuix.application.manager.persistence.PortletDefinitionManager;
import com.bea.netuix.application.manager.persistence.PortletDefinitionManagerHome;
import com.bea.netuix.application.manager.persistence.PortletCategoryManager;
import com.bea.netuix.application.manager.persistence.PortletCategoryManagerHome;
import com.bea.netuix.application.manager.persistence.ShellDefinitionManager;
import com.bea.netuix.application.manager.persistence.ShellDefinitionManagerHome;
import com.bea.netuix.application.localization.manager.persistence.LocalizationManagerHome;
import com.bea.netuix.application.localization.manager.persistence.LocalizationManager;

import com.bea.p13n.management.ApplicationHelper;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class PortalBeanManager
{
    private static PortalDefinitionManager          portalDefinitionManager         = null;
    private static BookDefinitionManager            bookDefinitionManager           = null;
    private static DesktopDefinitionManager         desktopDefinitionManager        = null;
    private static LookAndFeelDefinitionManager     lookAndFeelDefinitionManager    = null;
    private static PageDefinitionManager            pageDefinitionManager           = null;
    private static PortletCategoryManager           portletCategoryManager          = null;
    private static PortletDefinitionManager         portletDefinitionManager        = null;
    private static ShellDefinitionManager           shellDefinitionManager          = null;
    private static MarkupDefinitionManager          markupDefinitionManager         = null;
    private static LocalizationManager              localizationManager             = null;
    private static PortalCustomizationManager       portalCustomizationManager      = null;

    public PortalBeanManager()
    {

    }

    public static void resetManager()
    {
        try {
            portletDefinitionManager.remove();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        portletDefinitionManager = null;
    }

    /**
     * Return an instance of the PortletDefinitionManager session bean
     */
    public static PortletDefinitionManager getPortletDefinitionManager()
    {
        if ( portletDefinitionManager == null )
        {
            try
            {
                PortletDefinitionManagerHome home =
                  (PortletDefinitionManagerHome)PortableRemoteObject.narrow(
                      new InitialContext().lookup(ApplicationHelper.getApplicationName() + ".BEA_netuix.PortletDefinitionManager"),
                      PortletDefinitionManagerHome.class );
                portletDefinitionManager = home.create();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        
        return portletDefinitionManager;        
    }

    /**
     * Return an instance of the PortletCategoryDefinitionManager session bean
     */
    public static PortletCategoryManager getPortletCategoryManager()
    {
        if ( portletCategoryManager == null )
        {
            try
            {
                PortletCategoryManagerHome home =
                  (PortletCategoryManagerHome)PortableRemoteObject.narrow(
                      new InitialContext().lookup(ApplicationHelper.getApplicationName() + ".BEA_netuix.PortletCategoryManager"),
                      PortletCategoryManagerHome.class );
                portletCategoryManager = home.create();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        
        return portletCategoryManager;        
    }

    /**
     * Return an instance of the DesktopDefinitionManager session bean
     */
    public static DesktopDefinitionManager getDesktopDefinitionManager()
    {
        if ( desktopDefinitionManager == null )
        {
            try
            {
                DesktopDefinitionManagerHome home =
                  (DesktopDefinitionManagerHome)PortableRemoteObject.narrow(
                      new InitialContext().lookup(ApplicationHelper.getApplicationName() + ".BEA_netuix.DesktopDefinitionManager"),
                      DesktopDefinitionManagerHome.class );
                desktopDefinitionManager = home.create();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        
        return desktopDefinitionManager;        
    }

    /**
     * Return an instance of the LookAndFeelDefinitionManager session bean
     */
    public static LookAndFeelDefinitionManager getLookAndFeelDefinitionManager()
    {
        if ( lookAndFeelDefinitionManager == null )
        {
            try
            {
                LookAndFeelDefinitionManagerHome home =
                  (LookAndFeelDefinitionManagerHome)PortableRemoteObject.narrow(
                      new InitialContext().lookup(ApplicationHelper.getApplicationName() + ".BEA_netuix.LookAndFeelDefinitionManager"),
                      LookAndFeelDefinitionManagerHome.class );
                lookAndFeelDefinitionManager = home.create();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        
        return lookAndFeelDefinitionManager;        
    }

    /**
     * Return an instance of the ShellDefinitionManager session bean
     */
    public static ShellDefinitionManager getShellDefinitionManager()
    {
        if ( shellDefinitionManager == null )
        {
            try
            {
                ShellDefinitionManagerHome home =
                  (ShellDefinitionManagerHome)PortableRemoteObject.narrow(
                      new InitialContext().lookup(ApplicationHelper.getApplicationName() + ".BEA_netuix.ShellDefinitionManager"),
                      ShellDefinitionManagerHome.class );
                shellDefinitionManager = home.create();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        
        return shellDefinitionManager;        
    }

    /**
     * Return an instance of the MarkupDefinitionManager session bean
     */
    public static MarkupDefinitionManager getMarkupDefinitionManager()
    {
        if ( markupDefinitionManager == null )
        {
            try
            {
                MarkupDefinitionManagerHome home =
                  (MarkupDefinitionManagerHome)PortableRemoteObject.narrow(
                      new InitialContext().lookup(ApplicationHelper.getApplicationName() + ".BEA_netuix.MarkupDefinitionManager"),
                      MarkupDefinitionManagerHome.class );
                markupDefinitionManager = home.create();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        
        return markupDefinitionManager;        
    }

    /**
     * Return an instance of the MarkupDefinitionManager session bean
     */
    public static LocalizationManager getLocalizationManager()
    {
        if ( localizationManager == null )
        {
            try
            {
                LocalizationManagerHome home =
                  (LocalizationManagerHome)PortableRemoteObject.narrow(
                      new InitialContext().lookup(ApplicationHelper.getApplicationName() + ".BEA_netuix.LocalizationManager"),
                      LocalizationManagerHome.class );
                localizationManager = home.create();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        
        return localizationManager;        
    }

    /**
     * Return an instance of the BookDefinitionManager session bean
     */
    public static BookDefinitionManager getBookDefinitionManager()
    {
        if ( bookDefinitionManager == null )
        {
            try
            {
                BookDefinitionManagerHome home =
                  (BookDefinitionManagerHome)PortableRemoteObject.narrow(
                      new InitialContext().lookup(ApplicationHelper.getApplicationName() + ".BEA_netuix.BookDefinitionManager"),
                      BookDefinitionManagerHome.class );
                bookDefinitionManager = home.create();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        
        return bookDefinitionManager;        
    }

    /**
     * Return an instance of the PageDefinitionManager session bean
     */
    public static PageDefinitionManager getPageDefinitionManager()
    {
        if ( pageDefinitionManager == null )
        {
            try
            {
                PageDefinitionManagerHome home =
                  (PageDefinitionManagerHome)PortableRemoteObject.narrow(
                      new InitialContext().lookup(ApplicationHelper.getApplicationName() + ".BEA_netuix.PageDefinitionManager"),
                      PageDefinitionManagerHome.class );
                pageDefinitionManager = home.create();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        
        return pageDefinitionManager;        
    }

    /**
     * Return an instance of the PortalDefinitionManager session bean
     */
    public static PortalDefinitionManager getPortalDefinitionManager()
    {
        if ( portalDefinitionManager == null )
        {
            try
            {
                PortalDefinitionManagerHome home =
                  (PortalDefinitionManagerHome)PortableRemoteObject.narrow(
                      new InitialContext().lookup(ApplicationHelper.getApplicationName() + ".BEA_netuix.PortalDefinitionManager"),
                      PortalDefinitionManagerHome.class );
                portalDefinitionManager = home.create();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        
        return portalDefinitionManager;        
    }

    /**
     * Return an instance of the PortalCustomizationManager session bean
     */
    public static PortalCustomizationManager getPortalCustomizationManager()
    {
        if ( portalCustomizationManager == null )
        {
            try
            {
                PortalCustomizationManagerHome home =
                  (PortalCustomizationManagerHome)PortableRemoteObject.narrow(
                      new InitialContext().lookup(ApplicationHelper.getApplicationName() + ".BEA_netuix.PortalCustomizationManager"),
                      PortalCustomizationManagerHome.class );
                portalCustomizationManager = home.create();
            }
            catch ( Exception e )
            {
                e.printStackTrace();
            }
        }
        
        return portalCustomizationManager;        
    }
}
