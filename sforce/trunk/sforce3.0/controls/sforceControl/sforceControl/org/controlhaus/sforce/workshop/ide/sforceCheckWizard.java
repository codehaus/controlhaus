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
import com.bea.ide.control.ControlWizardSimple;
import javax.swing.JComponent;
import java.util.ArrayList;
import com.bea.ide.control.EditorContext;
import com.bea.ide.core.MessageSvc;
import com.bea.ide.workspace.ServerSvc;
import javax.swing.JOptionPane;

/*
 * The insert-wizard-class attribute in the jc-jar file for this
 * control project specifies that this is the class WebLogic Workshop
 * should use to provide a custom insert wizard. 
 */
public class sforceCheckWizard extends ControlWizardSimple
{ 
    private sforceWizardPanel m_panel;
    private boolean _createExtension = false;
    private  EditorContext _context;

    /* 
     * Initializes the user interface. UI components
     * are assembled in the ServerCheckWizardPanel class.
     */
    public sforceCheckWizard()
    {
        m_panel = new sforceWizardPanel();
    }

    /*
     * Tells WebLogic Workshop whether to display an option
     * to create a JCX file. The constant returned here
     * tells it that an instance is all that is needed.
     */
    public int getConfigurationInfo()
    {
        return CONFIG_INSERT_INSTANCE;
    }
    /**
     * Gives the wizard a way to interact with the IDE.
     */
    public void setContext(EditorContext ctx)
    {
        _context = ctx;
        m_panel.setContext(ctx);
    }

    /**
     * Provides a way for subclasses to get the context as needed.
     */
    public EditorContext getContext()
    {
        return _context;
    }

     /*
     * Returns the user interface component to use.
     */
    public JComponent getComponent()
    {
        return m_panel;
    }

    /*
     * Provides a place to execute code when the control's user
     * clicks the "Create" button on the insert dialog.
     */
    public boolean onFinish()
    {
        try {       
            if (super.onFinish() == false){
                return false;
            }
            if (doResourceChecks() == false){
                return false;
            }

            if (m_panel.isClassPathUpdateInProgress()) {
                return false; 
            }
            if (null != m_panel.getServerURL()) {
                return true;
            }
        }
        catch (Exception e) {
            String message = "Error : " + e.getMessage();
            JOptionPane.showMessageDialog(m_panel, message, "Connection Check", JOptionPane.INFORMATION_MESSAGE);        
        }
        return false;
    }

    /**
     * 
     * @return
     */
    protected boolean doResourceChecks()
    {
        ExtensionUtils utils = new ExtensionUtils();
        
        String msgs;
        String prompt;
        
        msgs = utils.checkDirectories();
        if (msgs!=null)
        {
            // can't do checks, just bail and let them insert
            MessageSvc.get().showInfoDialog("Directory Check", "Error : "+msgs );                        
            return false;
        }
        
        msgs = utils.checkSchemas();
        if (msgs!=null)
        {            
            prompt = "\nPlease press OK and login to salesforces.com in order to download schema.";
            msgs += prompt;
            MessageSvc.get().showInfoDialog("Schemas Check", msgs);
            GetWsdlDialog getWsdlDialog = new GetWsdlDialog(null,"",true); 
            getWsdlDialog.show();
            if(getWsdlDialog.wasCancelled()){
                return false;
            }
            return true;                    
        }
        
        //Extensions are optional
        msgs = utils.checkExtensions();
        if (msgs!=null)
        {
            try{
                utils.installExtensions();
                MessageSvc.get().showInfoDialog("", "Extensions succesfully installed!\nYou have to restart Workshop.");            
            }catch(Exception e){
                JOptionPane.showMessageDialog(m_panel, e.getMessage(), "Error installing extensions", JOptionPane.ERROR_MESSAGE);                      
            }            
        }
        return true;
    }

    /*
     * Provides to WebLogic Workshop an ArrayList containing
     * the annotations it should write preceding the control
     * instance.
     */
	public ArrayList getInstanceAnnotations()
	{
        // An ArrayList for the annotation attributes. There are four.
        ArrayList attributeList = new ArrayList();
        // An ArrayList for this control's two annotations.
        ArrayList annotationsList = new ArrayList();

        /*
         * The attribute ArrayList is filled with TagAttributeValue
         * instances containing attribute name/value mappings. These
         * are used below for the jc:server-data annotation.
         * 
         * Attribute values are retrieved from the insert dialog box UI.
         */
        attributeList.add(new TagAttributeValue("http-url", m_panel.getServerURL()));
        
        /*
         * The annotation ArrayList is filled with TagAttributeList instances
         * containing the annotation name/attribute-list mappings.
         * Note that the second annotation uses the attribute ArrayList.
         */
        annotationsList.add(new TagAttributeList("common:control", null));
        annotationsList.add(new TagAttributeList("jc:sforce-properties", attributeList));
		return annotationsList;
	}
} 
