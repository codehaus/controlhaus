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

// original type: ['urn:partner.soap.sforce.com']:query


public  class Query 
  
  implements java.io.Serializable
{

  public Query() {}

  public Query(java.lang.String p_queryString) 
  {
     this.queryString = p_queryString;
     

  }




  
  private java.lang.String queryString ;

  /**
  <br>  Derived from queryString.

  <br>  schema name = ['urn:partner.soap.sforce.com']:queryString
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getQueryString() {
    return queryString;
  }

  public void setQueryString(java.lang.String v) {
    
    this.queryString = v;
    
  }










  public java.lang.String toString() {
  return "Query" + "{"
             + " queryString=<" + getQueryString() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof Query) {
      
      Query __obj__ =  (Query) __other__;


      return true
            && (queryString==null ? __obj__.getQueryString()==null : queryString.equals(__obj__.getQueryString()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (queryString==null ? 0 : queryString.hashCode()) ;
    

    return __hash__result__;
  }

}


