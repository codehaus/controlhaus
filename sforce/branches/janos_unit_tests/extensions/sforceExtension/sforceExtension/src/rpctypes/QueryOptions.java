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

// original type: ['urn:partner.soap.sforce.com']:QueryOptions


public  class QueryOptions 
  
  implements java.io.Serializable
{

  public QueryOptions() {}

  public QueryOptions(java.lang.Integer p_batchSize) 
  {
     this.batchSize = p_batchSize;
     this.__isset_batchSize = true;


  }




  
  private java.lang.Integer batchSize ;

  /**
  <br>  Derived from batchSize.

  <br>  schema name = ['urn:partner.soap.sforce.com']:batchSize
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:int
  <br>  schema formQualified = true
  */
  public java.lang.Integer getBatchSize() {
    return batchSize;
  }

  public void setBatchSize(java.lang.Integer v) {
    
    this.batchSize = v;
    this.__isset_batchSize = true;

  }

  private boolean __isset_batchSize;
  public boolean _isSetBatchSize() {
    return this.__isset_batchSize;
  }
  public void _unsetBatchSize() {
    this.__isset_batchSize = false;
  }









  public java.lang.String toString() {
  return "QueryOptions" + "{"
             + " batchSize=<" + getBatchSize() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof QueryOptions) {
      
      QueryOptions __obj__ =  (QueryOptions) __other__;


      return true
            && (batchSize==null ? __obj__.getBatchSize()==null : batchSize.equals(__obj__.getBatchSize()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (batchSize==null ? 0 : batchSize.hashCode()) ;
    

    return __hash__result__;
  }

}


