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

// original type: ['urn:partner.soap.sforce.com']:GetUserInfoResult


public  class GetUserInfoResult 
  
  implements java.io.Serializable
{

  public GetUserInfoResult() {}

  public GetUserInfoResult(java.lang.String p_currencySymbol
,     java.lang.String p_organizationId
,     boolean p_organizationMultiCurrency
,     java.lang.String p_organizationName
,     java.lang.String p_userDefaultCurrencyIsoCode
,     java.lang.String p_userEmail
,     java.lang.String p_userFullName
,     java.lang.String p_userId
,     java.lang.String p_userLanguage
,     java.lang.String p_userLocale
,     java.lang.String p_userTimeZone) 
  {
     this.currencySymbol = p_currencySymbol;
     
     this.organizationId = p_organizationId;
     
     this.organizationMultiCurrency = p_organizationMultiCurrency;
     
     this.organizationName = p_organizationName;
     
     this.userDefaultCurrencyIsoCode = p_userDefaultCurrencyIsoCode;
     
     this.userEmail = p_userEmail;
     
     this.userFullName = p_userFullName;
     
     this.userId = p_userId;
     
     this.userLanguage = p_userLanguage;
     
     this.userLocale = p_userLocale;
     
     this.userTimeZone = p_userTimeZone;
     

  }




  
  private java.lang.String currencySymbol ;

  /**
  <br>  Derived from currencySymbol.

  <br>  schema name = ['urn:partner.soap.sforce.com']:currencySymbol
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getCurrencySymbol() {
    return currencySymbol;
  }

  public void setCurrencySymbol(java.lang.String v) {
    
    this.currencySymbol = v;
    
  }





  
  private java.lang.String organizationId ;

  /**
  <br>  Derived from organizationId.

  <br>  schema name = ['urn:partner.soap.sforce.com']:organizationId
  <br>  schema type = ['urn:partner.soap.sforce.com']:ID
  <br>  schema formQualified = true
  */
  public java.lang.String getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(java.lang.String v) {
    
    this.organizationId = v;
    
  }





  
  private boolean organizationMultiCurrency ;

  /**
  <br>  Derived from organizationMultiCurrency.

  <br>  schema name = ['urn:partner.soap.sforce.com']:organizationMultiCurrency
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getOrganizationMultiCurrency() {
    return organizationMultiCurrency;
  }

  public void setOrganizationMultiCurrency(boolean v) {
    
    this.organizationMultiCurrency = v;
    
  }





  
  private java.lang.String organizationName ;

  /**
  <br>  Derived from organizationName.

  <br>  schema name = ['urn:partner.soap.sforce.com']:organizationName
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getOrganizationName() {
    return organizationName;
  }

  public void setOrganizationName(java.lang.String v) {
    
    this.organizationName = v;
    
  }





  
  private java.lang.String userDefaultCurrencyIsoCode ;

  /**
  <br>  Derived from userDefaultCurrencyIsoCode.

  <br>  schema name = ['urn:partner.soap.sforce.com']:userDefaultCurrencyIsoCode
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getUserDefaultCurrencyIsoCode() {
    return userDefaultCurrencyIsoCode;
  }

  public void setUserDefaultCurrencyIsoCode(java.lang.String v) {
    
    this.userDefaultCurrencyIsoCode = v;
    
  }





  
  private java.lang.String userEmail ;

  /**
  <br>  Derived from userEmail.

  <br>  schema name = ['urn:partner.soap.sforce.com']:userEmail
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(java.lang.String v) {
    
    this.userEmail = v;
    
  }





  
  private java.lang.String userFullName ;

  /**
  <br>  Derived from userFullName.

  <br>  schema name = ['urn:partner.soap.sforce.com']:userFullName
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getUserFullName() {
    return userFullName;
  }

  public void setUserFullName(java.lang.String v) {
    
    this.userFullName = v;
    
  }





  
  private java.lang.String userId ;

  /**
  <br>  Derived from userId.

  <br>  schema name = ['urn:partner.soap.sforce.com']:userId
  <br>  schema type = ['urn:partner.soap.sforce.com']:ID
  <br>  schema formQualified = true
  */
  public java.lang.String getUserId() {
    return userId;
  }

  public void setUserId(java.lang.String v) {
    
    this.userId = v;
    
  }





  
  private java.lang.String userLanguage ;

  /**
  <br>  Derived from userLanguage.

  <br>  schema name = ['urn:partner.soap.sforce.com']:userLanguage
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getUserLanguage() {
    return userLanguage;
  }

  public void setUserLanguage(java.lang.String v) {
    
    this.userLanguage = v;
    
  }





  
  private java.lang.String userLocale ;

  /**
  <br>  Derived from userLocale.

  <br>  schema name = ['urn:partner.soap.sforce.com']:userLocale
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getUserLocale() {
    return userLocale;
  }

  public void setUserLocale(java.lang.String v) {
    
    this.userLocale = v;
    
  }





  
  private java.lang.String userTimeZone ;

  /**
  <br>  Derived from userTimeZone.

  <br>  schema name = ['urn:partner.soap.sforce.com']:userTimeZone
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getUserTimeZone() {
    return userTimeZone;
  }

  public void setUserTimeZone(java.lang.String v) {
    
    this.userTimeZone = v;
    
  }










  public java.lang.String toString() {
  return "GetUserInfoResult" + "{"
             + " currencySymbol=<" + getCurrencySymbol() + ">"
             + " organizationId=<" + getOrganizationId() + ">"
             + " organizationMultiCurrency=<" + getOrganizationMultiCurrency() + ">"
             + " organizationName=<" + getOrganizationName() + ">"
             + " userDefaultCurrencyIsoCode=<" + getUserDefaultCurrencyIsoCode() + ">"
             + " userEmail=<" + getUserEmail() + ">"
             + " userFullName=<" + getUserFullName() + ">"
             + " userId=<" + getUserId() + ">"
             + " userLanguage=<" + getUserLanguage() + ">"
             + " userLocale=<" + getUserLocale() + ">"
             + " userTimeZone=<" + getUserTimeZone() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof GetUserInfoResult) {
      
      GetUserInfoResult __obj__ =  (GetUserInfoResult) __other__;


      return true
            && (organizationMultiCurrency == __obj__.getOrganizationMultiCurrency())
            && (currencySymbol==null ? __obj__.getCurrencySymbol()==null : currencySymbol.equals(__obj__.getCurrencySymbol()))
            && (organizationId==null ? __obj__.getOrganizationId()==null : organizationId.equals(__obj__.getOrganizationId()))
            && (organizationName==null ? __obj__.getOrganizationName()==null : organizationName.equals(__obj__.getOrganizationName()))
            && (userDefaultCurrencyIsoCode==null ? __obj__.getUserDefaultCurrencyIsoCode()==null : userDefaultCurrencyIsoCode.equals(__obj__.getUserDefaultCurrencyIsoCode()))
            && (userEmail==null ? __obj__.getUserEmail()==null : userEmail.equals(__obj__.getUserEmail()))
            && (userFullName==null ? __obj__.getUserFullName()==null : userFullName.equals(__obj__.getUserFullName()))
            && (userId==null ? __obj__.getUserId()==null : userId.equals(__obj__.getUserId()))
            && (userLanguage==null ? __obj__.getUserLanguage()==null : userLanguage.equals(__obj__.getUserLanguage()))
            && (userLocale==null ? __obj__.getUserLocale()==null : userLocale.equals(__obj__.getUserLocale()))
            && (userTimeZone==null ? __obj__.getUserTimeZone()==null : userTimeZone.equals(__obj__.getUserTimeZone()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (currencySymbol==null ? 0 : currencySymbol.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (organizationId==null ? 0 : organizationId.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (organizationMultiCurrency ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (organizationName==null ? 0 : organizationName.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (userDefaultCurrencyIsoCode==null ? 0 : userDefaultCurrencyIsoCode.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (userEmail==null ? 0 : userEmail.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (userFullName==null ? 0 : userFullName.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (userId==null ? 0 : userId.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (userLanguage==null ? 0 : userLanguage.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (userLocale==null ? 0 : userLocale.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (userTimeZone==null ? 0 : userTimeZone.hashCode()) ;
    

    return __hash__result__;
  }

}


