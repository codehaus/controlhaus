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

package org.controlhaus.sforce.workshop.ide; 

import com.bea.ide.Application;
import com.bea.ide.actions.ActionSvc;
import weblogic.management.MBeanHome;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import com.bea.ide.control.EditorContext;
import com.bea.ide.core.MessageSvc;
import com.bea.ide.core.ResourceSvc;
import com.bea.ide.core.asynctask.AsyncTaskSvc;
import com.bea.ide.filesystem.FileFilterName;
import com.bea.ide.filesystem.FileSvc;
import com.bea.ide.filesystem.FileSvc.IFileSaver;
import com.bea.ide.filesystem.IFile;
import com.bea.ide.filesystem.IFileFilter;
import com.bea.ide.swing.SwingUtil;
import com.bea.ide.swing.WsFileChooser;
import com.bea.ide.ui.browser.BrowserSvc;
import com.bea.ide.util.URIUtil;
import com.bea.ide.workspace.IProject;
import com.bea.ide.workspace.IWorkspace;
import com.bea.ide.workspace.WorkspaceSvc;
import java.util.Set;
import java.util.Iterator;
import java.awt.FocusTraversalPolicy;
import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Container;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import javax.swing.JFileChooser;
import org.controlhaus.sforce.workshop.sforceSettings;

/*
 * Represents the user interface for the sForce wrapper control
 * insert dialog. 
 */
public class sforceWizardPanel extends JPanel implements ActionListener
{ 
  
	JPanel m_pnlServerInfo;
	JTextField m_txtServerURL;
	JLabel m_lblServerURL;
	JButton m_btnGetEnterpriseWsdl;
    EditorContext _ctx;
    boolean fClassPathUpdateInProgress=false;
    final private PropertyChangeListener cpListener;
    
    public sforceWizardPanel()
    {
        
        cpListener = new PropertyChangeListener() {
             public void propertyChange(PropertyChangeEvent e)
                {   
                    IWorkspace ws = Application.getWorkspace();
                    if ((ws != null) && ws.getBooleanProperty(IWorkspace.STATE_IsBuilding))
                        setClassPathUpdateInProgress(true);
                    if ((ws != null) && ws.getState() != null)
                        setClassPathUpdateInProgress(false);
                     }};

        initComponents();
    }
    
    /**
     * 
     * @param ctx
     */
    public void setContext(EditorContext ctx)
    {
        _ctx = ctx;
        m_pnlServerInfo.setFocusCycleRoot(true);
        m_pnlServerInfo.setFocusTraversalPolicy(_newFtp);
    }
                
    /*
     * Executes when the user clicks the "Get WSDL" button
     * in the insert dialog. 
     */
    public void actionPerformed(ActionEvent e) {
        GetWsdlDialog getWsdlDialog = new GetWsdlDialog(null,"",true); 
        getWsdlDialog.show();    
    }

 
    private FocusTraversalPolicy _newFtp = new FocusTraversalPolicy()
        {
            public Component getComponentAfter(Container focusCycleRoot, Component aComponent)
            {                
                if (aComponent==m_btnGetEnterpriseWsdl)
                    return m_txtServerURL;
                if (aComponent==m_txtServerURL)
                    return m_btnGetEnterpriseWsdl;                
                 return null;
            }

            public Component getComponentBefore(Container focusCycleRoot, Component aComponent)
            {
                if (aComponent==m_btnGetEnterpriseWsdl)
                    return m_txtServerURL;
                if (aComponent==m_txtServerURL)
                    return m_btnGetEnterpriseWsdl;
                 return null;
            }

            public Component getDefaultComponent(Container focusCycleRoot)
            {
                  return m_btnGetEnterpriseWsdl;
            }

            public Component getFirstComponent(Container focusCycleRoot)
            {
                    return m_txtServerURL;
            }

            public Component getLastComponent(Container focusCycleRoot)
            {
                  return m_btnGetEnterpriseWsdl;                
            }
        };

    /*
     * Assembles user interface components that make up the insert
     * dialog.
     */
    private void initComponents() {
      
        m_pnlServerInfo = new JPanel();
        m_txtServerURL = new JTextField();
        m_txtServerURL.setEnabled(false);
        m_lblServerURL = new JLabel();        
        m_btnGetEnterpriseWsdl = new JButton();
             
        GridBagConstraints gridBagConstraints;

        setLayout(new GridBagLayout());
        setEnabled(true);
                                

		m_lblServerURL.setText("Server URL:       ");
        m_lblServerURL.setLabelFor(m_txtServerURL);
        m_lblServerURL.setDisplayedMnemonic('r');
        //m_txtServerURL.setFocusTraversalKeysEnabled(true);        
        
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
        add(m_lblServerURL, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 0);
        add(m_txtServerURL, gridBagConstraints);

        m_txtServerURL.setText(sforceSettings.SERVER_URL_ANNOTATION_DEFAULT);

		m_btnGetEnterpriseWsdl.setText("Get Enterprise WSDL");
        m_btnGetEnterpriseWsdl.setMnemonic('C');
        m_btnGetEnterpriseWsdl.addActionListener(this);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 6, 250);
        add(m_btnGetEnterpriseWsdl, gridBagConstraints);
                
    }
    
    /*
     * A set of accessors that simplify retrieving the values
     * that the control's user has entered into the insert dialog.
     */        
    public String getServerURL() throws IllegalArgumentException
    {
        
        String sUrl = m_txtServerURL.getText();
        try 
        {            
            URL serverURL = new URL(sUrl);
            return serverURL.toString();
        }
        catch (MalformedURLException mfue)
        {
            throw new IllegalArgumentException("Server URL must be of form http://localhost:7001");
        }
    }
    
    public boolean isClassPathUpdateInProgress()
    {
        return this.fClassPathUpdateInProgress;
    }
    
    public void setClassPathUpdateInProgress(boolean f) 
    {
        this.fClassPathUpdateInProgress = f;
        return;
    }
} 
