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

import java.awt.*;

public class NodeInfo {
  private String nodeLabel;
  private int nodeType;
  private boolean nodeLoaded;
  private String nodeKind;
  private Color nodeFontColor = Color.BLACK;
  private Font nodeFont = null;
  private Color nodeBackColor = Color.WHITE;
  private String toolTipText = "";

  public void setToolTipText(String toolTipText) {
    this.toolTipText = toolTipText;
  }

  public void setNodeFont(Font font) {
    nodeFont = font;
  }

  public void setNodeLoaded(boolean nodeLoaded) {
    this.nodeLoaded = nodeLoaded;
  }

  public void setNodeBackColor(Color nodeBackColor) {
    this.nodeBackColor = nodeBackColor;
  }

  public void setNodeType(int nodeType) {
    this.nodeType = nodeType;
  }

  public void setNodeKind(String nodeKind) {
    this.nodeKind = nodeKind;
  }

  public void setNodeFontColor(Color fontColor) {
    this.nodeFontColor = fontColor;
  }

  public void setNodeLabel(String nodeLabel) {
    this.nodeLabel = nodeLabel;
  }

  public String getToolTipText() {
    return toolTipText;
  }

  public Font getNodeFont() {
    return nodeFont;
  }

  public boolean isNodeLoaded() {
    return nodeLoaded;
  }

  public Color getNodeBackColor() {
    return nodeBackColor;
  }

  public int getNodeType() {
    return nodeType;
  }

  public String getNodeKind() {
    return nodeKind;
  }

  public Color getNodeFontColor() {
    return nodeFontColor;
  }

  public String getNodeLabel() {
    return nodeLabel;
  }

  public NodeInfo(String NodeLabel, int NodeType, boolean NodeLoaded, String NodeKind,
                  Font NodeFont, Color NodeColor) {
    nodeLabel = NodeLabel;
    nodeType = NodeType;
    nodeLoaded = NodeLoaded;
    nodeKind = NodeKind;
    nodeFont = NodeFont;
    nodeFontColor = NodeColor;
    nodeBackColor = Color.WHITE;
  }

  public String toString() {
    return nodeLabel;
  }
}
