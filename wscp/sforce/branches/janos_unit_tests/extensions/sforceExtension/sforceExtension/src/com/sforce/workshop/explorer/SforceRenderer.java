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

import com.bea.ide.core.ResourceSvc;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;

public class SforceRenderer
    extends DefaultTreeCellRenderer {
  ImageIcon tutorialIcon;
  ImageIcon entityIcon; //1
  ImageIcon fieldIcon; //2
  ImageIcon fieldsIcon; //3
  ImageIcon entityAccessIcon; //4
  ImageIcon deleteIcon; //5
  ImageIcon idListIcon; //6
  ImageIcon insertIcon; //7
  ImageIcon queryIcon; //8
  ImageIcon searchIcon; //9
  ImageIcon updateIcon; //10
  ImageIcon attributeIcon; //11
  ImageIcon attributeItemIcon; //12
  ImageIcon filterIcon; //13
  ImageIcon fieldAccessIcon; //14
  ImageIcon selectIcon; //15
  ImageIcon pickListItemIcon; //16

  ImageIcon filterableIcon; //100
  ImageIcon selectableIcon; //101
  ImageIcon replicateableIcon; //102
  ImageIcon nillableIcon; //103
  ImageIcon searchableIcon; //104
  ImageIcon retrieveableIcon; //105
  ImageIcon createableIcon; //106
  ImageIcon activateableIcon; //107
  ImageIcon updateableIcon; //108
  ImageIcon deleteableIcon; //109
  ImageIcon queryableIcon; //110
  ImageIcon customfieldIcon; //111
  ImageIcon customtableIcon; //112
  ImageIcon requiredIcon; //113

  public SforceRenderer() {

    /*  loading icons was changed from
    
    filterableIcon = new ImageIcon(SforceRenderer.class.getResource(
        "images/filterable.gif"));
        
    to using ResourceSvc as below
    */
    
    filterableIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/filterable.gif"));
    selectableIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/selectable.gif"));
    replicateableIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/replicateable.gif"));
    nillableIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/nillable.gif"));
    searchableIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/searchable.gif"));
    retrieveableIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/retrieveable.gif"));
    createableIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/createable.gif"));
    activateableIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/activateable.gif"));
    updateableIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/updateable.gif"));
    deleteableIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/deleteable.gif"));
    queryableIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/queryable.gif"));
    customfieldIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/customfield.gif"));
    customtableIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/customtable.gif"));
    requiredIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/required.gif"));

    entityIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/entity.gif"));
    fieldIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/field.gif"));
    fieldsIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/fields.gif"));
    entityAccessIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/entityAccess.gif"));
    deleteIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/delete.gif"));
    idListIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/idList.gif"));
    insertIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/insert.gif"));
    queryIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/query.gif"));
    searchIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/search.gif"));
    updateIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/update.gif"));
    attributeIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/attribute.gif"));
    attributeItemIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/attributeItem.gif"));
    filterIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/filter.gif"));
    fieldAccessIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/fieldAccess.gif"));
    selectIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/select.gif"));
    pickListItemIcon = new ImageIcon(ResourceSvc.get().getImage(
        "images/pickListItem.gif"));

  }

  private ImageIcon getImage(String pathToIcon) {
    try {
      ImageIcon newImage = new ImageIcon(FrameView.class.getClass().
                                         getResource(pathToIcon));
      System.out.println("successfully loaded image: " + pathToIcon);
      return newImage;
    }
    catch (Exception ex) {
      System.out.println("error loading image: " + pathToIcon + ", " +
                         ex.getMessage());
      return null;
    }
  }

  public Component getTreeCellRendererComponent(
      JTree tree,
      Object value,
      boolean sel,
      boolean expanded,
      boolean leaf,
      int row,
      boolean hasFocus) {

    super.getTreeCellRendererComponent(
        tree, value, sel,
        expanded, leaf, row,
        hasFocus);
    DefaultMutableTreeNode vNode = (DefaultMutableTreeNode) value;
    if (vNode.getUserObject() instanceof NodeInfo) {
      NodeInfo ni = (NodeInfo) vNode.getUserObject();

      this.setForeground(ni.getNodeFontColor());
      this.setFont(ni.getNodeFont());
      this.setBackground(ni.getNodeBackColor());
      ToolTipManager.sharedInstance().setEnabled(true);
      this.setToolTipText(ni.getToolTipText());
      Dimension d = this.getPreferredSize();

      if (ni.getNodeType() >= 100) {
        d.setSize(d.getWidth(), 20);
      }
      else {
        d.setSize(d.getWidth(), 18);
      }
      this.setPreferredSize(d);

      try {
        setIcon(getIcon(ni.getNodeType(), ni.getNodeLabel()));
      }
      catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
    return this;
  }

  protected ImageIcon getIcon(int nodeType, String nodeLabel) {
    switch (nodeType) {
      case 1:
        return entityIcon;
      case 4:
        if (nodeLabel.equals("insert") || nodeLabel.equals("createable")) {
          return insertIcon;
        }
        else if (nodeLabel.equals("delete") || nodeLabel.equals("deletable")) {
          return deleteIcon;
        }
        else if (nodeLabel.equals("update") || nodeLabel.equals("updateable")) {
          return updateIcon;
        }
        else if (nodeLabel.equals("idList") || nodeLabel.equals("selectable")) {
          return idListIcon;
        }
        else if (nodeLabel.equals("query") || nodeLabel.equals("queryable")) {
          return queryIcon;
        }
        else if (nodeLabel.equals("search") || nodeLabel.equals("searchable")) {
          return searchIcon;
        }
        else if (nodeLabel.equals("filter") || nodeLabel.equals("filterable")) {
          return filterIcon;
        }
        else if (nodeLabel.equals("select") || nodeLabel.equals("selectable")) {
          return selectIcon;
        }
        else {
          return entityAccessIcon;
        }
      case 3:
        return fieldsIcon;
      case 2:
        return fieldIcon;
      case 11:
        return attributeIcon;
      case 12:
        return attributeItemIcon;
      case 14:
        if (nodeLabel.equals("insert") || nodeLabel.equals("createable")) {
          return insertIcon;
        }
        else if (nodeLabel.equals("delete") || nodeLabel.equals("deleteable")) {
          return deleteIcon;
        }
        else if (nodeLabel.equals("update") || nodeLabel.equals("updateable")) {
          return updateIcon;
        }
        else if (nodeLabel.equals("idList") || nodeLabel.equals("selectable")) {
          return idListIcon;
        }
        else if (nodeLabel.equals("query") || nodeLabel.equals("queryable")) {
          return queryIcon;
        }
        else if (nodeLabel.equals("search") || nodeLabel.equals("searchable")) {
          return searchIcon;
        }
        else if (nodeLabel.equals("filter") || nodeLabel.equals("filterable")) {
          return filterIcon;
        }
        else if (nodeLabel.equals("select") || nodeLabel.equals("selectable")) {
          return selectIcon;
        }
        else {
          return entityAccessIcon;
        }
      case 16:
        return pickListItemIcon;
      case 100:
        return filterableIcon;
      case 101:
        return selectableIcon;
      case 102:
        return replicateableIcon;
      case 103:
        return nillableIcon;
      case 104:
        return searchableIcon;
      case 105:
        return retrieveableIcon;
      case 106:
        return createableIcon;
      case 107:
        return activateableIcon;
      case 108:
        return updateableIcon;
      case 109:
        return deleteableIcon;
      case 110:
        return queryableIcon;
      case 111:
        return customfieldIcon;
      case 112:
        return customtableIcon;
      case 113:
        return requiredIcon;
      default:
        return attributeItemIcon;
    }
  }

}
