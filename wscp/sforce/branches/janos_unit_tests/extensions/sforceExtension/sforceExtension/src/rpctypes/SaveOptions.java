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

/**
 * This code was automatically generated at 2:42:16 AM on May 5, 2004
 * by weblogic.xml.schema.binding.internal.codegen.BeanImplGenerator -- do not edit.
 *
 * @version WebLogic Server 8.1 SP2  Fri Dec 5 15:01:51 PST 2003 316284 
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

package rpctypes;

// original type: ['urn:partner.soap.sforce.com']:SaveOptions


public  class SaveOptions 
  
  implements java.io.Serializable
{

  public SaveOptions() {}

  public SaveOptions(boolean p_autoAssign
,     java.lang.String p_assignmentRuleId) 
  {
     this.autoAssign = p_autoAssign;
     
     this.assignmentRuleId = p_assignmentRuleId;
     

  }




  
  private boolean autoAssign ;

  /**
  <br>  Derived from autoAssign.

  <br>  schema name = ['urn:partner.soap.sforce.com']:autoAssign
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getAutoAssign() {
    return autoAssign;
  }

  public void setAutoAssign(boolean v) {
    
    this.autoAssign = v;
    
  }





  
  private java.lang.String assignmentRuleId ;

  /**
  <br>  Derived from assignmentRuleId.

  <br>  schema name = ['urn:partner.soap.sforce.com']:assignmentRuleId
  <br>  schema type = ['urn:partner.soap.sforce.com']:ID
  <br>  schema formQualified = true
  */
  public java.lang.String getAssignmentRuleId() {
    return assignmentRuleId;
  }

  public void setAssignmentRuleId(java.lang.String v) {
    
    this.assignmentRuleId = v;
    
  }










  public java.lang.String toString() {
  return "SaveOptions" + "{"
             + " autoAssign=<" + getAutoAssign() + ">"
             + " assignmentRuleId=<" + getAssignmentRuleId() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof SaveOptions) {
      
      SaveOptions __obj__ =  (SaveOptions) __other__;


      return true
            && (autoAssign == __obj__.getAutoAssign())
            && (assignmentRuleId==null ? __obj__.getAssignmentRuleId()==null : assignmentRuleId.equals(__obj__.getAssignmentRuleId()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (autoAssign ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (assignmentRuleId==null ? 0 : assignmentRuleId.hashCode()) ;
    

    return __hash__result__;
  }

}


