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
import rpctypes.LoginFault;
import rpctypes.LoginResult;
import rpctypes.UnexpectedErrorFault;

public class LoginDialog
    extends JDialog {

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
    public static boolean cancelled = true;
    String axisFault = "";
    String axisFaultCode = "";
    private sf des; // = new sf();
    
    public LoginDialog(Frame frame, String title, boolean modal, sf binding) {
        super(frame, title, modal);
        java.awt.Image im = new ImageIcon(ResourceSvc.get().getImage("images/sf.gif")).getImage();
        ((Frame) super.getParent()).setIconImage(im);
        try {
            des = binding;
            jbInit();
            pack();
            this.setSize(new Dimension(420, 150));
            centerOnScreen();
       }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LoginDialog(sf binding) {
        this(null, "", false, binding);
    }

    public sf getBinding() {
        return des;
    }
    
    private void layoutDialog() {
        //this.setContentPane(panel1);
        this.setResizable(true);
        this.setTitle("Login to salesforce.com");
        this.getContentPane().setLayout(gridBagLayout2);
        jLabel1.setHorizontalAlignment(SwingConstants.LEADING);
        jLabel1.setText("Username:");
        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.5F);
        jPanel1.setDebugGraphicsOptions(0);
        jPanel1.setLayout(gridBagLayout3);
        jButtonLogin.setSelectedIcon(null);
        jButtonLogin.setText("Login");
        jButtonLogin.setVerticalAlignment(SwingConstants.CENTER);
        jButtonCancel.setText("Cancel");
        jButtonSignUp.setText("Sign Up...");
        jLabel2.setText("Password:");
        jTextFieldUserName.setText("jTextField1");
        jPasswordFieldPassword.setText("jPasswordField1");
        panel1.setLayout(gridBagLayout4);
        panel1.setEnabled(true);
        panel1.setAlignmentX(0.0F);
        panel1.setAlignmentY(0.0F);
        panel1.setDoubleBuffered(false);jPanel1.add(jButtonCancel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
            new Insets( -1, 10, 1, 0), 25, 6));jPanel1.add(jButtonSignUp, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
            new Insets(0, 15, 2, 0), 6, 6));panel1.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                               , GridBagConstraints.WEST,
                                               GridBagConstraints.NONE,
                                               new Insets(5, 5, 0, 0), 0, 0));
        panel1.add(jLabel2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                                               , GridBagConstraints.CENTER,
                                               GridBagConstraints.NONE,
                                               new Insets(5, 5, 0, 0), 0, 0));jPanel1.add(jButtonLogin, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
            new Insets(0, 0, 2, 10), 25, 6));panel1.add(jPasswordFieldPassword,
               new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
                                      , GridBagConstraints.CENTER,
                                      GridBagConstraints.HORIZONTAL,
                                      new Insets(5, 0, 0, 5), 0, 0));
        panel1.add(jTextFieldUserName, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
            , GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
            new Insets(5, 0, 0, 5), 0, 0));
        this.getContentPane().add(panel1,
                              new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            , GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));jPanel1.add(jButtonSignUp, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
            new Insets(0, 57, 0, 0), 3, 6));
        jPanel1.add(jButtonCancel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
            new Insets(0, 57, 0, 0), 3, 6));
        jPanel1.add(jButtonLogin, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            , GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE,
            new Insets(0, 55, 0, 0), 11, 6));
        panel1.add(jPanel1, new GridBagConstraints(0, 2, 2, 1, 1.0, 1.0
                                               , GridBagConstraints.NORTH,
                                               GridBagConstraints.HORIZONTAL,
                                               new Insets(0, 0, 0, 0), 205, 27));
        
    //this.setSize(new Dimension(420, 110));
    }
    
    private void getPreferences() {
        IWorkspace workspace = (IWorkspace)Application.get().getProperty(Application.PROP_ActiveWorkspace);
 
        Preferences prefs = Application.get().systemNodeForPackage(SFDCPrefsPanel.class);
        jTextFieldUserName.setText(prefs.get(SforceSettings.USERNAME, ""));
        String password = prefs.get(SforceSettings.PASSWORD, null);
        if (password != null)
        {
            try
            {
                password = CryptUtil.get().deobfuscate(password);
                jPasswordFieldPassword.setText(password);
            }
            catch (Exception e)
            {
                MessageSvc.get().debugLog("Exception while attempting to decrypt password: " + e);   
            }
        }
    }
    
    private void jbInit() throws Exception {

        layoutDialog();
        titledBorder1 = new TitledBorder("");
        getPreferences();
        //jTextFieldUserName.setText("");
        //jPasswordFieldPassword.setText("");
        jButtonLogin.addActionListener(new LoginDialog_jButtonLogin_actionAdapter(this));
        jButtonCancel.addActionListener(new LoginDialog_jButtonCancel_actionAdapter(this));
        jButtonSignUp.addActionListener(new LoginDialog_jButtonSignUp_actionAdapter(this));
                   cancelled = true;
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.addWindowListener(new LoginDialog_this_windowAdapter(this));

    }
    
    public void centerOnScreen() {
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

  void jButtonLogin_actionPerformed(ActionEvent e) {
    this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    if (login(this.jTextFieldUserName.getText(),
              new String(this.jPasswordFieldPassword.getPassword()))) {
      cancelled = false;
      this.hide();
    }
    else {
        String errorMessage = this.axisFault;
        errorMessage += "\n" +
          "Please check your username and password and try again.";
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        String message = errorMessage;
        JOptionPane.showMessageDialog(this, message, "Login Failed", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  private boolean login(String username, String password) {

    LoginResult lr = null;
    try {
        if (des == null) des = new sf();
        lr = des.login(username, password);
    }
    catch (LoginFault ex1) {
        this.axisFault = ex1.getMessage();
        this.axisFaultCode = "LOGIN_ERROR";
        return false;
    } catch (UnexpectedErrorFault ex1) {
        this.axisFault = ex1.getMessage();
        this.axisFaultCode = "UNEXPECTED_ERROR";
        return false;
    } catch (Exception ex1) {
        this.axisFault = ex1.getMessage();
        this.axisFaultCode = "JAVA_EXCEPTION";
        return false;
    }

    return true;
  }

  void jButtonCancel_actionPerformed(ActionEvent e) {
    cancelled = true;
    this.hide();
  }

  void this_windowOpened(WindowEvent e) {
  }

  void jButtonSignUp_actionPerformed(ActionEvent e) {
    System.out.println(e.ACTION_PERFORMED);
    String url = "https://www.sforce.com/us/orderEntry/signup.jsp?ide=weblogic_workshop_8.1";
    Launcher.displayURL(url);
  }
}

class LoginDialog_jButtonLogin_actionAdapter
    implements java.awt.event.ActionListener {
  LoginDialog adaptee;

  LoginDialog_jButtonLogin_actionAdapter(LoginDialog adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonLogin_actionPerformed(e);
  }
}

class LoginDialog_jButtonCancel_actionAdapter
    implements java.awt.event.ActionListener {
  LoginDialog adaptee;

  LoginDialog_jButtonCancel_actionAdapter(LoginDialog adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonCancel_actionPerformed(e);
  }
}

class LoginDialog_this_windowAdapter
    extends java.awt.event.WindowAdapter {
  LoginDialog adaptee;

  LoginDialog_this_windowAdapter(LoginDialog adaptee) {
    this.adaptee = adaptee;
  }

  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class LoginDialog_jButtonSignUp_actionAdapter
    implements java.awt.event.ActionListener {
  LoginDialog adaptee;

  LoginDialog_jButtonSignUp_actionAdapter(LoginDialog adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonSignUp_actionPerformed(e);
  }
}
