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

// original type: ['urn:partner.soap.sforce.com']:DescribeGlobalResult


public  class DescribeGlobalResult 
  
  implements java.io.Serializable
{

  public DescribeGlobalResult() {}

  public DescribeGlobalResult(java.lang.String p_encoding
,     int p_maxBatchSize
,     java.lang.String[] p_types) 
  {
     this.encoding = p_encoding;
     
     this.maxBatchSize = p_maxBatchSize;
     
     this.types = p_types;
     this.__isset_types = true;


  }




  
  private java.lang.String encoding ;

  /**
  <br>  Derived from encoding.

  <br>  schema name = ['urn:partner.soap.sforce.com']:encoding
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getEncoding() {
    return encoding;
  }

  public void setEncoding(java.lang.String v) {
    
    this.encoding = v;
    
  }





  
  private int maxBatchSize ;

  /**
  <br>  Derived from maxBatchSize.

  <br>  schema name = ['urn:partner.soap.sforce.com']:maxBatchSize
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:int
  <br>  schema formQualified = true
  */
  public int getMaxBatchSize() {
    return maxBatchSize;
  }

  public void setMaxBatchSize(int v) {
    
    this.maxBatchSize = v;
    
  }





  
  private java.lang.String[] types ;

  /**
  <br>  Derived from types.

  <br>  schema name = ['urn:partner.soap.sforce.com']:types
  <br>  schema type = ['urn:partner.soap.sforce.com']:DescribeGlobalResult__types__ArrayAnonType
  <br>  schema formQualified = true
  */
  public java.lang.String[] getTypes() {
    return types;
  }

  public void setTypes(java.lang.String[] v) {
    
    this.types = v;
    this.__isset_types = true;

  }

  private boolean __isset_types;
  public boolean _isSetTypes() {
    return this.__isset_types;
  }
  public void _unsetTypes() {
    this.__isset_types = false;
  }









  public java.lang.String toString() {
  return "DescribeGlobalResult" + "{"
             + " encoding=<" + getEncoding() + ">"
             + " maxBatchSize=<" + getMaxBatchSize() + ">"
             + " types=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getTypes() ) + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof DescribeGlobalResult) {
      
      DescribeGlobalResult __obj__ =  (DescribeGlobalResult) __other__;


      return true
            && (maxBatchSize == __obj__.getMaxBatchSize())
            && (encoding==null ? __obj__.getEncoding()==null : encoding.equals(__obj__.getEncoding()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(types, __obj__.getTypes())


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (encoding==null ? 0 : encoding.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + maxBatchSize ;
    __hash__result__ = 37*__hash__result__ + (types==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(types)) ;
    

    return __hash__result__;
  }

}


