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

package com.sforce.workshop.explorer; 

import com.bea.ide.Application;
import com.bea.ide.core.MessageSvc;
import com.bea.ide.core.ResourceSvc;
import com.bea.ide.swing.TitledTopBorder;
import com.bea.ide.workspace.IPropertyPanel;
import com.bea.ide.workspace.IWorkspace;
import com.bea.wlw.runtime.core.util.CryptUtil;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.prefs.Preferences;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SFDCPrefsPanel extends JPanel implements IPropertyPanel
{     
    static ResourceSvc.IResourcePkg s_pkg = 
        ResourceSvc.get().getResourcePackage(SFDCPrefsPanel.class, "sforce");
        
    private JPanel _ftpSettingsPanel;
    private JLabel _hostNameLabel;
    private JTextField _hostName;
    private JLabel _userNameLabel;
    private JTextField _userName;
    private JLabel _passwordLabel;
    private JPasswordField _password;
    private JPanel _filler;
    private JLabel _remoteDirectoryTip;
    private JLabel _portDefaultTip;


    public SFDCPrefsPanel()
    {
        super();
        initComponents();   
    }
        
    public void loadProperties()
    {
        Preferences prefs = Application.get().systemNodeForPackage(SFDCPrefsPanel.class);
        _hostName.setText(prefs.get(SforceSettings.SERVER_URL, ""));
        _userName.setText(prefs.get(SforceSettings.USERNAME, ""));
        String password = prefs.get(SforceSettings.PASSWORD, null);
        if (password != null)
        {
            try
            {
                password = CryptUtil.get().deobfuscate(password);
                _password.setText(password);
            }
            catch (Exception e)
            {
                MessageSvc.get().debugLog("Exception while attempting to decrypt password: " + e);   
            }
        }
    }
    
        
    public void storeProperties()
    {
        Preferences prefs = Application.get().systemNodeForPackage(SFDCPrefsPanel.class);
        
        String hostName = _hostName.getText();
        if(!hostName.equals(prefs.get(SforceSettings.SERVER_URL, null)))
        {
            prefs.put(SforceSettings.SERVER_URL, hostName);   
        }        
        String userName = _userName.getText();
        if(!userName.equals(prefs.get(SforceSettings.USERNAME, null)))
        {
            prefs.put(SforceSettings.USERNAME, userName);   
        }
        
        String password = _password.getText();
        String oldPassword = prefs.get(SforceSettings.PASSWORD, null);
        if (oldPassword != null)
        {
            try
            {
                oldPassword = CryptUtil.get().deobfuscate(oldPassword);
            }
            catch (Exception e)
            {
                MessageSvc.get().debugLog("Exception while attempting to decrypt password: " + e);
                oldPassword = null;
            }
        }
        if (!password.equals(oldPassword))
        {
            try
            {
                prefs.put(SforceSettings.PASSWORD, CryptUtil.get().obfuscate(password));
            }
            catch (Exception e)
            {
                MessageSvc.get().debugLog("Exception while attempting to encrypt proxy password: " + e);
            }   
        }        
    }

    
    public boolean validateEntries(JDialog dialog)
    {
        return true;
    }  
    
    public void cancel()
    {
    }

    
    public void initComponents()
    {
        setLayout(new GridBagLayout());
        
        _ftpSettingsPanel = new JPanel();
                
        _hostNameLabel = new JLabel(s_pkg.getString("serverURL"));
        _hostName = new JTextField();
        
        _userNameLabel = new JLabel(s_pkg.getString("username"));
        _userName = new JTextField();
       
        _passwordLabel = new JLabel(s_pkg.getString("password"));
        _password = new JPasswordField();
       
        _ftpSettingsPanel.setBorder(new TitledTopBorder(s_pkg.getString("SforceSettings")));
        _ftpSettingsPanel.setLayout(new GridBagLayout());
        
        _filler = new JPanel();

        GridBagConstraints gbc = new GridBagConstraints();        
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        gbc.insets = new Insets(2, 2, 2, 6);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        _ftpSettingsPanel.add(_hostNameLabel, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        _ftpSettingsPanel.add(_hostName, gbc);

        GridBagConstraints fillerGBC = new GridBagConstraints();
        fillerGBC.gridwidth = GridBagConstraints.REMAINDER;
        fillerGBC.anchor = GridBagConstraints.WEST;
        fillerGBC.fill = GridBagConstraints.HORIZONTAL;      
        fillerGBC.insets = new Insets(8, 0, 8, 0);
        fillerGBC.weightx = 1.0;
        fillerGBC.weighty = 1.0;
        _ftpSettingsPanel.add(_filler, fillerGBC);
        
         gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        _ftpSettingsPanel.add(_userNameLabel, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        _ftpSettingsPanel.add(_userName, gbc);

        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        _ftpSettingsPanel.add(_passwordLabel, gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        _ftpSettingsPanel.add(_password, gbc);
  
        GridBagConstraints topGbc = new GridBagConstraints();
        topGbc.gridwidth = GridBagConstraints.REMAINDER;
        topGbc.anchor = GridBagConstraints.WEST;
        topGbc.fill = GridBagConstraints.HORIZONTAL;      
        topGbc.insets = new Insets(4, 1, 8, 1);
        topGbc.weightx = 1.0;
        topGbc.weighty = 1.0;
        add(_ftpSettingsPanel, topGbc);        
        add(new JPanel(), topGbc);
        
    }
} 
