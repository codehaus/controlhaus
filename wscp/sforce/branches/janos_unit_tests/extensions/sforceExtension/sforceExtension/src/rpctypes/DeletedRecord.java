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
 * This code was automatically generated at 2:42:15 AM on May 5, 2004
 * by weblogic.xml.schema.binding.internal.codegen.BeanImplGenerator -- do not edit.
 *
 * @version WebLogic Server 8.1 SP2  Fri Dec 5 15:01:51 PST 2003 316284 
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

package rpctypes;

// original type: ['urn:partner.soap.sforce.com']:DeletedRecord


public  class DeletedRecord 
  
  implements java.io.Serializable
{

  public DeletedRecord() {}

  public DeletedRecord(java.lang.String p_id
,     java.util.Calendar p_deletedDate) 
  {
     this.id = p_id;
     
     this.deletedDate = p_deletedDate;
     

  }




  
  private java.lang.String id ;

  /**
  <br>  Derived from id.

  <br>  schema name = ['urn:partner.soap.sforce.com']:id
  <br>  schema type = ['urn:partner.soap.sforce.com']:ID
  <br>  schema formQualified = true
  */
  public java.lang.String getId() {
    return id;
  }

  public void setId(java.lang.String v) {
    
    this.id = v;
    
  }





  
  private java.util.Calendar deletedDate ;

  /**
  <br>  Derived from deletedDate.

  <br>  schema name = ['urn:partner.soap.sforce.com']:deletedDate
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:dateTime
  <br>  schema formQualified = true
  */
  public java.util.Calendar getDeletedDate() {
    return deletedDate;
  }

  public void setDeletedDate(java.util.Calendar v) {
    
    this.deletedDate = v;
    
  }










  public java.lang.String toString() {
  return "DeletedRecord" + "{"
             + " id=<" + getId() + ">"
             + " deletedDate=<" + getDeletedDate() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof DeletedRecord) {
      
      DeletedRecord __obj__ =  (DeletedRecord) __other__;


      return true
            && (id==null ? __obj__.getId()==null : id.equals(__obj__.getId()))
            && (deletedDate==null ? __obj__.getDeletedDate()==null : deletedDate.equals(__obj__.getDeletedDate()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (id==null ? 0 : id.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (deletedDate==null ? 0 : deletedDate.hashCode()) ;
    

    return __hash__result__;
  }

}


