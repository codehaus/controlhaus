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
import com.bea.ide.core.MessageSvc;
import com.bea.ide.core.ResourceSvc;
import com.bea.ide.ui.browser.BrowserSvc;
import com.bea.ide.workspace.IWorkspace;
import com.bea.wlw.runtime.core.util.CryptUtil;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.xml.transform.*;
import org.w3c.dom.*;
import javax.xml.rpc.*;
import java.rmi.*;
import java.net.*;
import javax.xml.namespace.*;
import java.io.*;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import org.controlhaus.sforce.workshop.sforceSettings;

/**
 * Dialog panel for salesforce.com login 
 *     
 */
public class GetWsdlDialog extends JDialog implements ActionListener{

    JPanel panel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    GridBagLayout gridBagLayout3 = new GridBagLayout();
    JPanel jPanel1 = new JPanel();
    JButton jButtonLogin = new JButton();
    JButton jButtonCancel = new JButton();
    JButton jButtonSignUp = new JButton();
    GridBagLayout gridBagLayout2 = new GridBagLayout();
    JTextField jTextFieldUserName = new JTextField();
    JPasswordField jPasswordFieldPassword = new JPasswordField();
    GridBagLayout gridBagLayout4 = new GridBagLayout();
    TitledBorder titledBorder1;
    
    /**
     * true if cancel button was pressed
     */
    boolean cancelled = true;

    /**
     * 
     * @param frame
     * @param title
     * @param modal
     */
    public GetWsdlDialog(Frame frame, String title, boolean modal) {        
        super(frame, title, modal);
        try {    
            initComponents();
            pack();
            setSize(new Dimension(420, 130));
            centerOnScreen();
       }catch (Exception ex) {
            MessageSvc.get().showInfoDialog("", "Error : "+ex.getMessage());            
       }
    }
    
    /**
     * 
     * @throws Exception
     */
    private void initComponents() throws Exception {

        layoutDialog();
        titledBorder1 = new TitledBorder("");

        jButtonLogin.setActionCommand("login");
        jButtonCancel.setActionCommand("cancel");
        jButtonSignUp.setActionCommand("signup");        
        registerButtonListeners(this);
        
        cancelled = true;
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
    }
    
    
    /**
     * 
     *
     */
    private void layoutDialog() {

        this.setResizable(false);
        this.setTitle("Login to salesforce.com");
        this.getContentPane().setLayout(gridBagLayout2);
        jLabel1.setHorizontalAlignment(SwingConstants.LEADING);
        jLabel1.setText("Username");
        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.5F);
        jPanel1.setDebugGraphicsOptions(0);
        jPanel1.setLayout(gridBagLayout3);
        
        jButtonLogin.setSelectedIcon(null);
        jButtonLogin.setText("Login");
        jButtonLogin.setVerticalAlignment(SwingConstants.CENTER);
        jButtonCancel.setText("Cancel");
        jButtonSignUp.setText("Sign Up...");
        jLabel2.setText("Password");

        panel1.setLayout(gridBagLayout4);
        panel1.setEnabled(true);
        panel1.setAlignmentX(0.0F);
        panel1.setAlignmentY(0.0F);
        panel1.setDoubleBuffered(false);
        
        panel1.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.NONE,
            new Insets(5, 5, 0, 0), 0, 0));
        panel1.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(5, 5, 0, 0), 0, 0));
            
        panel1.add(jPasswordFieldPassword,
             new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            , GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(5, 0, 0, 5), 0, 0));
        panel1.add(jTextFieldUserName, 
            new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
            new Insets(5, 0, 0, 5), 0, 0));
            
        jPanel1.add(jButtonSignUp, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
            new Insets(0, 57, 0, 0), 3, 0));
        jPanel1.add(jButtonCancel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
            new Insets(0, 57, 0, 0), 3, 0));
        jPanel1.add(jButtonLogin, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE,
            new Insets(0, 55, 0, 0), 11, 0));
            
        panel1.add(jPanel1, new GridBagConstraints(0, 2, 2, 1, 1.0, 1.0
            , GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 205, 27));
            
        this.getContentPane().add(panel1,
            new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            , GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
            new Insets(10, 0, 0, 0), 0, 0));
                    
    }
            
    /**
     * 
     * @param a
     */
    private void registerButtonListeners(ActionListener a){
        jButtonLogin.addActionListener(a);
        jButtonCancel.addActionListener(a);
        jButtonSignUp.addActionListener(a);
    }
    
    private void centerOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation( (screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }
    
    public boolean wasCancelled(){
        return cancelled;
    }
    
    public void actionPerformed(ActionEvent e) {
            
        if(e.getActionCommand().equals("login")){
            loginButton_actionPerformed(e);            
        }else
        if(e.getActionCommand().equals("cancel")){
            cancelButton_actionPerformed(e);            
        }else
        if(e.getActionCommand().equals("signup")){
            signUpButton_actionPerformed(e);            
        }
            
    }// actionPerformed
    

    // button action handlers 
    
    /**
     * 
     */
    private void loginButton_actionPerformed(ActionEvent e) {
    
        cancelled = false;
        
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
    
        ExtensionUtils utils = new ExtensionUtils();
        
        String msgs = utils.checkDirectories();
        if (msgs!=null)
        {
            // can't do checks, just bail and let them insert
            MessageSvc.get().showInfoDialog("Directory Check", "Error : "+msgs );                        
            hide();                  
            return;
        }
    
        try{
        
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            utils.downloadWsdl(  jTextFieldUserName.getText(),
                                new String(this.jPasswordFieldPassword.getPassword())  );
          
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));          
            
            hide();                  
            JOptionPane.showMessageDialog(this, "Enterprise.wsdl sucessfully downloaded!", "Download successfull", JOptionPane.INFORMATION_MESSAGE);                  
                            
        }catch(sforceLoginException ex){
        
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));        
            String errorMessage = ex.getMessage() +
                                "\n Please check your username and password and try again.";            
            JOptionPane.showMessageDialog(this, errorMessage, "Login Failed", JOptionPane.ERROR_MESSAGE);        
        
        }catch(sforceDownloadException ex){
        
            hide();
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));        
                
            String errorMessage = ex.getMessage()+
                "\nPressing OK button you can start a new browser session on sforce.com and try to "+
                "\n donwload it manually. Login and save the sforce.com enterprise.wsdl to your local "+
                "\n workstation as \"enterprise.wsdl\". Then Import the WSDL file from your download "+
                "\n directory into the Schema project.";
            
            int option = JOptionPane.showConfirmDialog(this,errorMessage,"Wsdl download failed",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
            if(option==JOptionPane.YES_OPTION) {                          
                try {
                    URL urlGetWsdl = new URL(sforceSettings.GET_WSDL_MANUAL_URL);
                    BrowserSvc.get().invokeBrowser(urlGetWsdl,true);
                }catch (MalformedURLException me) {   
                    JOptionPane.showMessageDialog(this, me.getMessage(), "Error opening browser", JOptionPane.ERROR_MESSAGE);                  
                } // catch
            }// if
        }// catch
                                
    }

	/**
	 * 
	 * @param e
	 */
	private void cancelButton_actionPerformed(ActionEvent e) {
        cancelled = true;
        hide();
    }
    
	/**
	 * 
	 * @param e
	 */
	private void signUpButton_actionPerformed(ActionEvent e) {
        try{        
            URL urlGetWsdl = new URL(sforceSettings.SIGNUP_URL);
            BrowserSvc.get().invokeBrowser(urlGetWsdl,true);
        }catch(MalformedURLException me){
            JOptionPane.showMessageDialog(this, me.getMessage(), "Error opening browser", JOptionPane.ERROR_MESSAGE);                  
        }    
    }// actionPerformed    

}
