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

import com.bea.ide.core.datatransfer.DataTransferSvc;
import com.bea.ide.ui.frame.FrameSvc;
import com.bea.ide.ui.frame.IFrameView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.transform.TransformerException;
import rpctypes.DescribeGlobalResult;
import rpctypes.DescribeSObjectResult;
import rpctypes.Field;
import rpctypes.LoginResult;
import rpctypes.PicklistEntry;
import weblogic.utils.Debug;
import workshop.util.jni.ide.jspdesigner.Browser;

public class FrameView extends JPanel implements com.bea.ide.ui.frame.IFrameView 
{
    JLabel hi = new JLabel();
    private sf des; // = new sf();
    private JTree theTree = new JTree();
    protected DefaultTreeModel model;
    protected DefaultMutableTreeNode root;
    protected JScrollPane sp = new JScrollPane();
    static public String sid = "";
    static public String userID = "";
    static public boolean loggedin = false;
    private JPopupMenu popMenu = new JPopupMenu();
    private JMenuItem mnuLogout = new JMenuItem();
    private JMenuItem mnuForums = new JMenuItem();
    private JPanel contentPane;
    private BorderLayout borderLayout1 = new BorderLayout();
    
	public FrameView()
	{
        super();
        init();
	}

    private void init() {
        this.setLayout(borderLayout1);
        this.add(sp, BorderLayout.CENTER);
        sp.getViewport().add(theTree, null);
        
        theTree.setCellRenderer(new SforceRenderer());
        root = new DefaultMutableTreeNode(new NodeInfo("sforce Entities", 1, false,
            "root", this.getFont(), this.getForeground()));
        root.add(new DefaultMutableTreeNode(new NodeInfo("asdfa", 2, false,
            "holder", this.getFont(), this.getForeground())));

        model = new DefaultTreeModel(root);
        theTree.setModel(model);
        theTree.setRootVisible(true);
        theTree.setLargeModel(false);
        theTree.setShowsRootHandles(true);
        
        mnuLogout.setText("Logout");
        mnuLogout.addActionListener(new SforceObjects_mnuLogout_actionAdapter(this));
        popMenu.add(mnuLogout);

        mnuForums.setText("sforce Community");
        mnuForums.addActionListener(new SforceObjects_mnuForums_actionAdapter(this));
        popMenu.add(mnuForums);
        theTree.collapsePath(new TreePath(root));
        ( (NodeInfo) root.getUserObject()).setToolTipText(
            "Expand this node to login.");
    
        addMouseListener();
        addTreeListenerWillExpand();
        DataTransferSvc.get().registerDnDSupport(theTree,
                                new SforceDragDropSource(),
        //                        new SforceDragTextDriver(),
                                javax.swing.TransferHandler.COPY,
                                true);
                                
    }
    	
    private void ResetTree() {
        theTree.removeAll();
        theTree.setCellRenderer(new SforceRenderer());
        root = new DefaultMutableTreeNode(new NodeInfo("sforce Entities", 1, false,
            "root", this.getFont(), this.getForeground()));
        root.add(new DefaultMutableTreeNode(new NodeInfo("asdfa", 2, false,
            "holder", this.getFont(), this.getForeground())));

        model = new DefaultTreeModel(root);
        theTree.setModel(model);
        theTree.setRootVisible(true);
        theTree.setLargeModel(false);
        theTree.setShowsRootHandles(true);
        
        theTree.collapsePath(new TreePath(root));
        ( (NodeInfo) root.getUserObject()).setToolTipText(
            "Expand this node to login.");
    }
    
	public Component getView(String id)
	{
        try {
        init();
        } catch (Exception ex) {
            hi.setText(hi.getText() + ",  Error");
        }
		return this;
	}

    private void logon(String userName, String password) {
        
        try {
            LoginResult lr = des.login(userName, password);
            hi.setText(lr.getSessionId());
        }
        catch (Exception ex) {
            hi.setText("error");
        }
    }	
    
	private boolean _isAvailable = false;    
    
    // alternately make this frame appear or disappear on document, view, and layout switches.
	public boolean isAvailable()
	{	
		_isAvailable = !_isAvailable;        
		return true;
	} 

    private void addMouseListener() {
        theTree.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) ( (JTree) e.
                getSource()).getClosestPathForLocation(e.getX(), e.getY()).
                getLastPathComponent();
                if (node.getUserObject() instanceof NodeInfo) {
                    NodeInfo ni = (NodeInfo) node.getUserObject();
                    String nk = ni.getNodeKind();
                    boolean nl = ni.isNodeLoaded();
                    if (nk.equals("entity")) {
                        nl = ( (NodeInfo) ( (DefaultMutableTreeNode) node.getParent()).
                        getUserObject()).isNodeLoaded();
                    }
                    if (nl) {
                        if (nk.equals("root") || nk.equals("entity")) {
                            if (e.getModifiers() == java.awt.Event.META_MASK) {
                                // Make the jPopupMenu visible relative to the current mouse position in the container.
                                popMenu.show(sp, e.getX(), e.getY());
                            }
                        }
                    }
                }
            }

            public void mouseEntered(MouseEvent arg0) {
            }

            public void mouseExited(MouseEvent arg0) {
            }

            public void mousePressed(MouseEvent arg0) {
            }

            public void mouseReleased(MouseEvent arg0) {
            }
        });
    }        
    
    private void addTreeListenerWillExpand() {
        theTree.addTreeWillExpandListener(new TreeWillExpandListener() {
            public void treeWillCollapse(TreeExpansionEvent event) {
            }

            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                event.getPath().getLastPathComponent();
                NodeInfo nodeInfo = (NodeInfo) node.getUserObject();
                if (!nodeInfo.isNodeLoaded()) {
                    if (nodeInfo.getNodeKind().equals("root")) {
                        if (!loadTree(node)) {
                            throw new ExpandVetoException(event);
                        }
                        else {
                            ( (NodeInfo) node.getUserObject()).setNodeLoaded(true);
                            model = new DefaultTreeModel(root);
                            theTree.setModel(model);
                        } 
                    }
                    else if (nodeInfo.getNodeKind().equals("entity")) {
                        try {
                            loadNodes(node);
                        }
                        catch (TransformerException ex) {
                            des = new sf();
                            ResetTree();
                        }
                        catch (RemoteException ex) {
                            des = new sf();
                            ResetTree();
                        } finally {
                            ( (NodeInfo) node.getUserObject()).setNodeLoaded(true);
                            model = new DefaultTreeModel(root);
                            theTree.setModel(model);
                            theTree.expandPath(new TreePath(node.getFirstChild()));
                        }
                    }
                }
            } //treeWillExpand
        }); //addTreeWillExpandListener
    }
    boolean loadTree(DefaultMutableTreeNode rootNode) {

        DescribeGlobalResult global = null;
        if (loggedin == false) {
            this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            LoginDialog ld = new LoginDialog(null, "Login to sforce", true, des);
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            ld.show();
            if (ld.cancelled == true) {
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                return false;
            }
            else {
                des = ld.getBinding();
                loggedin = true;
            }
        }
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        try {
            global = des.describeGlobal();
        }
            catch (RemoteException ex) {
            System.out.println(ex.getMessage());
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            return false;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            return false;
        }
        theTree.setCellRenderer(new SforceRenderer());

        rootNode.removeAllChildren();

        theTree.getSelectionModel().setSelectionMode(TreeSelectionModel.
                                                 SINGLE_TREE_SELECTION);
        rootNode.removeAllChildren();
        for (int i = 0; i < global.getTypes().length; i++) {
            DefaultMutableTreeNode somenode = new DefaultMutableTreeNode(new NodeInfo(
            global.getTypes()[i], 1, false, "entity", this.getFont(),
            this.getForeground()));
            rootNode.add(somenode);
            somenode.add(new DefaultMutableTreeNode(new NodeInfo("", 2, false, "",
            this.getFont(), this.getForeground())));
            theTree.collapsePath(new TreePath(somenode));
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        theTree.setToolTipText("This is a test.");
        ( (NodeInfo) root.getUserObject()).setToolTipText(
            "Right click change login credentials.");
        return true;
    }

  void loadNodes(DefaultMutableTreeNode node) throws RemoteException,
      TransformerException {

    this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    String entityName = ( (NodeInfo) node.getUserObject()).getNodeLabel();
    DescribeSObjectResult dr = null;
    
    try {
        dr = des.getObjectDefinition(entityName);
    } catch (Exception ex) {
        Debug.say(ex.getMessage());
    }

    node.removeAllChildren();

    DefaultMutableTreeNode accessTNode = new DefaultMutableTreeNode(new
        NodeInfo("Access", 11, false, "ea", this.getFont(), this.getForeground()));
    node.add(accessTNode);
    
    if (dr.getActivateable()) {
      accessTNode.add(new DefaultMutableTreeNode(new NodeInfo("activateable",
          107, true,
          "ea", this.getFont(), this.getForeground())));

    }
    if (dr.getCustom()) {
      accessTNode.add(new DefaultMutableTreeNode(new NodeInfo("custom", 112, true,
          "ea", this.getFont(), this.getForeground())));

    }
    if (dr.getCreateable()) {
      accessTNode.add(new DefaultMutableTreeNode(new NodeInfo("createable", 106, true,
          "ea", this.getFont(), this.getForeground())));

    }
    if (dr.getDeletable()) {
      accessTNode.add(new DefaultMutableTreeNode(new NodeInfo("deleteable", 109, true,
          "ea", this.getFont(), this.getForeground())));

    }
    if (dr.getQueryable()) {
      accessTNode.add(new DefaultMutableTreeNode(new NodeInfo("queryable", 110, true,
          "ea", this.getFont(), this.getForeground())));

    }
    if (dr.getReplicateable()) {
      accessTNode.add(new DefaultMutableTreeNode(new NodeInfo("replicatable",
          102, true,
          "ea", this.getFont(), this.getForeground())));

    }
    if (dr.getRetrieveable()) {
      accessTNode.add(new DefaultMutableTreeNode(new NodeInfo("retrieveable",
          105, true,
          "ea", this.getFont(), this.getForeground())));

    }
    if (dr.getSearchable()) {
      accessTNode.add(new DefaultMutableTreeNode(new NodeInfo("searchable", 104, true,
          "ea", this.getFont(), this.getForeground())));

    }
    if (dr.getUpdateable()) {
      accessTNode.add(new DefaultMutableTreeNode(new NodeInfo("updateable", 108, true,
          "ea", this.getFont(), this.getForeground())));

    }
    DefaultMutableTreeNode fieldsTNode = new DefaultMutableTreeNode(new
        NodeInfo("Fields", 3, true, "ea", this.getFont(), this.getForeground()));
    node.add(fieldsTNode);

    Field[] fields = dr.getFields();
    String outputVal;
    String fldName;
    DefaultMutableTreeNode fieldTNode = null;

    for (int i = 0; i < fields.length; i++) {
      Field fldNode = fields[i];
      fldName = fldNode.getName();

      if (fldNode.getCustom()) {
        fieldTNode = new DefaultMutableTreeNode(new NodeInfo(fldName +
            " (custom)", 2, true,
            "ea", new Font(this.getFont().getName(), Font.ITALIC,
                           this.getFont().getSize()), Color.MAGENTA));
      }
      else {
        fieldTNode = new DefaultMutableTreeNode(new NodeInfo(fldName, 2, true,
            "ea",
            new Font(this.getFont().getName(), Font.PLAIN,
                     this.getFont().getSize()), Color.BLUE));
      }
      fieldsTNode.add(fieldTNode);

      DefaultMutableTreeNode fieldANode = new DefaultMutableTreeNode(new
          NodeInfo("Access", 11, true, "ea", this.getFont(), this.getForeground()));

      if (fldNode.getCreateable()) {
        fieldANode.add(new DefaultMutableTreeNode(new NodeInfo("createable",
            106, true,
            "ea", this.getFont(), this.getForeground())));

      }
      if (fldNode.getCustom()) {
        fieldANode.add(new DefaultMutableTreeNode(new NodeInfo("custom", 111, true,
            "ea", this.getFont(), this.getForeground())));

      }
      if (fldNode.getFilterable()) {
        fieldANode.add(new DefaultMutableTreeNode(new NodeInfo("filterable",
            100, true,
            "ea", this.getFont(), this.getForeground())));

      }
      if (fldNode.getNillable()) {
        fieldANode.add(new DefaultMutableTreeNode(new NodeInfo("nillable", 103, true,
            "ea", this.getFont(), this.getForeground())));

      }
      if (fldNode.getRestrictedPicklist()) {
        fieldANode.add(new DefaultMutableTreeNode(new NodeInfo("restricted",
            4, true, "ea", this.getFont(), Color.RED)));

      }
      if (fldNode.getUpdateable()) {
        fieldANode.add(new DefaultMutableTreeNode(new NodeInfo("updateable",
            108, true,
            "ea", this.getFont(), this.getForeground())));

      }
      fieldTNode.add(fieldANode);

      //label
      if (fldNode.getLabel() != null) {
        DefaultMutableTreeNode labelTNode = new DefaultMutableTreeNode(new
            NodeInfo("Label", 11, true, "ea", this.getFont(),
                     this.getForeground()));
        fieldTNode.add(labelTNode);
        labelTNode.add(new DefaultMutableTreeNode(new NodeInfo(fldNode.
            getLabel(),
            12, true, "ea", this.getFont(), this.getForeground())));
      }

      //data type
      DefaultMutableTreeNode typeTNode = new DefaultMutableTreeNode(new
          NodeInfo("Type", 11, true, "ea", this.getFont(), this.getForeground()));
      fieldTNode.add(typeTNode);

      if (fldNode.getPicklistValues() != null) {
        typeTNode.add(new DefaultMutableTreeNode(new NodeInfo(fldNode.getType() +
            " - Picklist",
            12, true, "ea", this.getFont(), this.getForeground())));
      }
      else {
        typeTNode.add(new DefaultMutableTreeNode(new NodeInfo(fldNode.getType().
            getValue(),
            12, true, "ea", this.getFont(), this.getForeground())));

        //Integers
      }
      if (fldNode.getType().getValue().equals("int")) {
        typeTNode.add(new DefaultMutableTreeNode(new NodeInfo("Digits: " +
            new Integer(fldNode.getDigits()).toString(),
            12, true, "ea", this.getFont(), this.getForeground())));

        //doubles
      }
      if (fldNode.getType().getValue().equals("double")) {
        if (fldNode.getPrecision() > 0) {
          typeTNode.add(new DefaultMutableTreeNode(new NodeInfo("Precision: " +
              new Integer(fldNode.getPrecision()).toString(),
              12, true, "ea", this.getFont(), this.getForeground())));
        }
        if (fldNode.getScale() > 0) {
          typeTNode.add(new DefaultMutableTreeNode(new NodeInfo("Scale: " +
              new Integer(fldNode.getScale()).toString(),
              12, true, "ea", this.getFont(), this.getForeground())));
        }
      }

      //length
      if (fldNode.getLength() > 0) {
        typeTNode.add(new DefaultMutableTreeNode(new NodeInfo("Length: " +
            new Integer(fldNode.getLength()).toString(),
            12, true, "ea", this.getFont(), this.getForeground())));

        //byte length
      }
      
      if (fldNode.getByteLength() > 0) {
        typeTNode.add(new DefaultMutableTreeNode(new NodeInfo("Byte length: " +
            new Integer(fldNode.getByteLength()).toString(),
            12, true, "ea", this.getFont(), this.getForeground())));

      }
      
      if (fldNode.getPicklistValues() != null) {
        DefaultMutableTreeNode plTNode = new DefaultMutableTreeNode(new
            NodeInfo("Picklist values", 12, true, "ea", this.getFont(),
                     this.getForeground()));
        typeTNode.add(plTNode);
        PicklistEntry[] pes = fldNode.getPicklistValues();
        for (int k = 0; k < pes.length; k++) {
          PicklistEntry pe = pes[k];
          String caption = "";
          if (pe.getLabel() != null) {
            caption = pe.getLabel();
          }
          else {
            caption = pe.getValue();
          }
          DefaultMutableTreeNode pleTNode;

          pleTNode = new DefaultMutableTreeNode(new NodeInfo(caption, 12, true,
              "ea", this.getFont(), this.getForeground()));
/*
          if (pe.getValue() != null) {
            pleTNode.add(new DefaultMutableTreeNode(new NodeInfo("value: " +
                pe.getValue(), 12, true, "ea", this.getFont(),
                this.getForeground())));
          }
*/
          plTNode.add(pleTNode);
        }
      }

      //reference to
      if (fldNode.getType().getValue().equals("reference")) {
        DefaultMutableTreeNode referenceToTNode = new DefaultMutableTreeNode(new
            NodeInfo("reference to", 11, true, "ea", this.getFont(),
                     this.getForeground()));
        typeTNode.add(referenceToTNode);
        String[] refTo = fldNode.getReferenceTo();

        for (int j = 0; j < refTo.length; j++) {
          referenceToTNode.add(new DefaultMutableTreeNode(new NodeInfo(
              refTo[j], 12, true, "ea", this.getFont(), this.getForeground())));
        }
      }
    }
    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
  }

  void jButton1_actionPerformed(ActionEvent e) {
    mnuLogout_actionPerformed(e);
  }

  void mnuLogout_actionPerformed(ActionEvent e) {
      root.removeAllChildren();
      ( (NodeInfo) root.getUserObject()).setNodeLoaded(false);
      root.add(new DefaultMutableTreeNode(new NodeInfo("asdfa", 2, false,
          "holder", this.getFont(), this.getForeground())));
      model = new DefaultTreeModel(root);
      theTree.setModel(model);
      theTree.collapsePath(new TreePath(root));
      loggedin = false;
      ( (NodeInfo) root.getUserObject()).setToolTipText(
          "Expand this node to login.");
  }

  void mnuForums_actionPerformed(ActionEvent e) {
    Launcher.displayURL("http://www.sforce.com/us/community/index.jsp?ide=wlwworkshop");
  }
  
} 
class SforceObjects_mnuLogout_actionAdapter
    implements java.awt.event.ActionListener {
  FrameView adaptee;

  SforceObjects_mnuLogout_actionAdapter(FrameView adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.mnuLogout_actionPerformed(e);
  }
}
class SforceObjects_mnuForums_actionAdapter
    implements java.awt.event.ActionListener {
  FrameView adaptee;

  SforceObjects_mnuForums_actionAdapter(FrameView adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.mnuForums_actionPerformed(e);
  }
}