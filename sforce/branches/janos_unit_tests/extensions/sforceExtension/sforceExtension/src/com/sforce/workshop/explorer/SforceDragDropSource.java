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

import com.bea.ide.core.MessageSvc;
import com.bea.ide.core.ResourceSvc;
import com.bea.ide.core.datatransfer.DefaultDragDropDriver;
import com.bea.ide.core.datatransfer.IDragDropDriver.IDragSourceInfo;
import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class SforceDragDropSource extends DefaultDragDropDriver
{ 
    public IDragSourceInfo getDragInfo(Component arg0, Point arg1)
    {
        IDragSourceInfo dragInfo=null;
        try {
            JTree sfTree = (JTree)arg0;
            TreeSelectionModel selModel = sfTree.getSelectionModel();
            int selCnt = selModel.getSelectionCount();                    
            switch (selCnt) 
            {
                case(0):
                    break;            
                case(1):
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)(selModel.getSelectionPath().getLastPathComponent());                                  
                    dragInfo = new ColumnsDragInfo(getDropTextForNode(node));
                    break;                                    
                default:
            }
            return dragInfo;
        } catch (Exception e) {
            MessageSvc.get().displayError("error in drag: " + e.getMessage(),1);
            e.printStackTrace();
            return null;   
        }        
    }

    
    private String getDropTextForNode(DefaultMutableTreeNode n) 
    {
        NodeInfo nodeInfo = (NodeInfo)n.getUserObject();
        DefaultMutableTreeNode child;
        NodeInfo childInfo;
        String sOut="";
        StringBuffer sBuf;
        String sField;
        switch (nodeInfo.getNodeType())
        {
            case 1:     // an object node
            case 2:     // Field node
                sField = nodeInfo.getNodeLabel();
                if (sField.endsWith(" (custom)"))
                    sOut= sField.substring(0,sField.length()-9);
                else
                    sOut = sField; 
                break;
            case 3:     // Fields parent node
                int nFields = n.getChildCount();
                sBuf = new StringBuffer();
                for (int i=0;i<nFields;i++)
                {
                    child = (DefaultMutableTreeNode)n.getChildAt(i);
                    childInfo = (NodeInfo)child.getUserObject();
                    if (childInfo.getNodeType() != 2)
                        break;
                    sField = childInfo.getNodeLabel();
                    if (sField.endsWith(" (custom)"))
                        sBuf.append(sField.substring(0,sField.length()-9));
                    else
                        sBuf.append(sField); 

                    if (i<nFields-1)
                        sBuf.append(",");
                }
                sOut = sBuf.toString();
            default:   
        }
        return sOut;
    }
    
    
    class ColumnsDragInfo implements IDragSourceInfo {
        private String _selText;
        
        public ColumnsDragInfo(String selText)
        {
            _selText=selText;    
        };
        
        public Image getDragImage()
        {            
            return ResourceSvc.get().getImage("images/node-16.gif");
        }
           

        public Point getIconOffset()
        {
            return new Point(0,0);
        }

        public Transferable getTransferable()
        {
            Transferable dropData = new StringSelection(_selText); 
            return dropData;
        }
    }
} 
