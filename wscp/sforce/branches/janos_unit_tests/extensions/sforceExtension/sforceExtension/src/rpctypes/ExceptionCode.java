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
 * by weblogic.xml.schema.binding.internal.codegen.EnumGenerator -- do not edit.
 *
 * @version WebLogic Server 8.1 SP2  Fri Dec 5 15:01:51 PST 2003 316284 
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

package rpctypes;

// original type: ['urn:fault.partner.soap.sforce.com']:ExceptionCode


public class ExceptionCode
  implements java.io.Serializable
{

  private java.lang.String __value;

  protected ExceptionCode(java.lang.String value) {
    __value = value;
  }

  public static final java.lang.String _INVALID_REPLICATION_DATE = "INVALID_REPLICATION_DATE";
  public static final rpctypes.ExceptionCode INVALID_REPLICATION_DATE = new rpctypes.ExceptionCode(_INVALID_REPLICATION_DATE);

  public static final java.lang.String _UNKNOWN_EXCEPTION = "UNKNOWN_EXCEPTION";
  public static final rpctypes.ExceptionCode UNKNOWN_EXCEPTION = new rpctypes.ExceptionCode(_UNKNOWN_EXCEPTION);

  public static final java.lang.String _PASSWORD_LOCKOUT = "PASSWORD_LOCKOUT";
  public static final rpctypes.ExceptionCode PASSWORD_LOCKOUT = new rpctypes.ExceptionCode(_PASSWORD_LOCKOUT);

  public static final java.lang.String _INVALID_LOGIN = "INVALID_LOGIN";
  public static final rpctypes.ExceptionCode INVALID_LOGIN = new rpctypes.ExceptionCode(_INVALID_LOGIN);

  public static final java.lang.String _MALFORMED_QUERY = "MALFORMED_QUERY";
  public static final rpctypes.ExceptionCode MALFORMED_QUERY = new rpctypes.ExceptionCode(_MALFORMED_QUERY);

  public static final java.lang.String _INVALID_QUERY_LOCATOR = "INVALID_QUERY_LOCATOR";
  public static final rpctypes.ExceptionCode INVALID_QUERY_LOCATOR = new rpctypes.ExceptionCode(_INVALID_QUERY_LOCATOR);

  public static final java.lang.String _LOGIN_DURING_RESTRICTED_TIME = "LOGIN_DURING_RESTRICTED_TIME";
  public static final rpctypes.ExceptionCode LOGIN_DURING_RESTRICTED_TIME = new rpctypes.ExceptionCode(_LOGIN_DURING_RESTRICTED_TIME);

  public static final java.lang.String _INVALID_QUERY_FILTER_OPERATOR = "INVALID_QUERY_FILTER_OPERATOR";
  public static final rpctypes.ExceptionCode INVALID_QUERY_FILTER_OPERATOR = new rpctypes.ExceptionCode(_INVALID_QUERY_FILTER_OPERATOR);

  public static final java.lang.String _INVALID_SEARCH_SCOPE = "INVALID_SEARCH_SCOPE";
  public static final rpctypes.ExceptionCode INVALID_SEARCH_SCOPE = new rpctypes.ExceptionCode(_INVALID_SEARCH_SCOPE);

  public static final java.lang.String _INSUFFICIENT_ACCESS = "INSUFFICIENT_ACCESS";
  public static final rpctypes.ExceptionCode INSUFFICIENT_ACCESS = new rpctypes.ExceptionCode(_INSUFFICIENT_ACCESS);

  public static final java.lang.String _SERVER_UNAVAILABLE = "SERVER_UNAVAILABLE";
  public static final rpctypes.ExceptionCode SERVER_UNAVAILABLE = new rpctypes.ExceptionCode(_SERVER_UNAVAILABLE);

  public static final java.lang.String _ORG_LOCKED = "ORG_LOCKED";
  public static final rpctypes.ExceptionCode ORG_LOCKED = new rpctypes.ExceptionCode(_ORG_LOCKED);

  public static final java.lang.String _INVALID_CLIENT = "INVALID_CLIENT";
  public static final rpctypes.ExceptionCode INVALID_CLIENT = new rpctypes.ExceptionCode(_INVALID_CLIENT);

  public static final java.lang.String _MALFORMED_SEARCH = "MALFORMED_SEARCH";
  public static final rpctypes.ExceptionCode MALFORMED_SEARCH = new rpctypes.ExceptionCode(_MALFORMED_SEARCH);

  public static final java.lang.String _UNSUPPORTED_API_VERSION = "UNSUPPORTED_API_VERSION";
  public static final rpctypes.ExceptionCode UNSUPPORTED_API_VERSION = new rpctypes.ExceptionCode(_UNSUPPORTED_API_VERSION);

  public static final java.lang.String _UNSUPPORTED_CLIENT = "UNSUPPORTED_CLIENT";
  public static final rpctypes.ExceptionCode UNSUPPORTED_CLIENT = new rpctypes.ExceptionCode(_UNSUPPORTED_CLIENT);

  public static final java.lang.String _INVALID_SESSION_ID = "INVALID_SESSION_ID";
  public static final rpctypes.ExceptionCode INVALID_SESSION_ID = new rpctypes.ExceptionCode(_INVALID_SESSION_ID);

  public static final java.lang.String _INVALID_TYPE = "INVALID_TYPE";
  public static final rpctypes.ExceptionCode INVALID_TYPE = new rpctypes.ExceptionCode(_INVALID_TYPE);

  public static final java.lang.String _EXCEEDED_RATE_LIMIT = "EXCEEDED_RATE_LIMIT";
  public static final rpctypes.ExceptionCode EXCEEDED_RATE_LIMIT = new rpctypes.ExceptionCode(_EXCEEDED_RATE_LIMIT);

  public static final java.lang.String _TRIAL_EXPIRED = "TRIAL_EXPIRED";
  public static final rpctypes.ExceptionCode TRIAL_EXPIRED = new rpctypes.ExceptionCode(_TRIAL_EXPIRED);

  public static final java.lang.String _API_CURRENTLY_DISABLED = "API_CURRENTLY_DISABLED";
  public static final rpctypes.ExceptionCode API_CURRENTLY_DISABLED = new rpctypes.ExceptionCode(_API_CURRENTLY_DISABLED);

  public static final java.lang.String _EXCEEDED_QUOTA = "EXCEEDED_QUOTA";
  public static final rpctypes.ExceptionCode EXCEEDED_QUOTA = new rpctypes.ExceptionCode(_EXCEEDED_QUOTA);

  public static final java.lang.String _API_DISABLED_FOR_ORG = "API_DISABLED_FOR_ORG";
  public static final rpctypes.ExceptionCode API_DISABLED_FOR_ORG = new rpctypes.ExceptionCode(_API_DISABLED_FOR_ORG);

  public static final java.lang.String _LOGIN_DURING_RESTRICTED_DOMAIN = "LOGIN_DURING_RESTRICTED_DOMAIN";
  public static final rpctypes.ExceptionCode LOGIN_DURING_RESTRICTED_DOMAIN = new rpctypes.ExceptionCode(_LOGIN_DURING_RESTRICTED_DOMAIN);

  public static final java.lang.String _FUNCTIONALITY_NOT_ENABLED = "FUNCTIONALITY_NOT_ENABLED";
  public static final rpctypes.ExceptionCode FUNCTIONALITY_NOT_ENABLED = new rpctypes.ExceptionCode(_FUNCTIONALITY_NOT_ENABLED);

  public static final java.lang.String _EXCEEDED_ID_LIMIT_ON_RETRIEVE = "EXCEEDED_ID_LIMIT_ON_RETRIEVE";
  public static final rpctypes.ExceptionCode EXCEEDED_ID_LIMIT_ON_RETRIEVE = new rpctypes.ExceptionCode(_EXCEEDED_ID_LIMIT_ON_RETRIEVE);

  public static final java.lang.String _INVALID_SEARCH = "INVALID_SEARCH";
  public static final rpctypes.ExceptionCode INVALID_SEARCH = new rpctypes.ExceptionCode(_INVALID_SEARCH);

  public static final java.lang.String _INVALID_FIELD = "INVALID_FIELD";
  public static final rpctypes.ExceptionCode INVALID_FIELD = new rpctypes.ExceptionCode(_INVALID_FIELD);

  public static final java.lang.String _INVALID_BATCH_SIZE = "INVALID_BATCH_SIZE";
  public static final rpctypes.ExceptionCode INVALID_BATCH_SIZE = new rpctypes.ExceptionCode(_INVALID_BATCH_SIZE);



  private static final java.util.Map _valueMap = _buildValueMap();


  // Gets the value for a enumerated value
  public java.lang.String getValue() {
    return __value;
  }


  // Gets enumeration with a specific value
  // throws java.lang.IllegalArgumentException if
  // any invalid value is specified
   public static  rpctypes.ExceptionCode fromValue(java.lang.String value) {
    Object obj = _valueMap.get(value);
    if (obj==null) {
      java.lang.String msg = invalidValueMsg(""+value);
    msg=msg+(" valmap="+_valueMap);
      throw new java.lang.IllegalArgumentException(msg);
    }
    return (rpctypes.ExceptionCode)obj;

   }

  private static java.lang.String invalidValueMsg(java.lang.String value) {
    java.lang.String msg = "invalid enumeration value: " + value;
    return msg;
  }


   // Gets enumeration from a String
   // throws java.lang.IllegalArgumentException if
   // any invalid value is specified
   public static rpctypes.ExceptionCode fromString(java.lang.String value) {
    java.lang.String __tmp = 
     __tmp = value;
    return fromValue(__tmp);

   }


   // Returns String representation of the enumerated value
   public java.lang.String toString() {
     return java.lang.String.valueOf(__value);
   }

   public boolean equals(java.lang.Object obj) {
     if (obj instanceof rpctypes.ExceptionCode) {
       java.lang.String tmp_val = ((rpctypes.ExceptionCode) obj).getValue();
       return tmp_val.equals(__value);

     }
     return false;
   }


  public int hashCode() {
    int __hash__result__ = 17;
    __hash__result__ = 37*__hash__result__ + (__value==null ? 0 : __value.hashCode()) ;
    
    return __hash__result__;
  }

  private java.lang.Object readResolve() throws java.io.ObjectStreamException {
    return fromValue(__value);
  }


  private static java.util.Map _buildValueMap() {
    java.util.Map __valmap = new java.util.HashMap(); 

    __valmap.put( _INVALID_REPLICATION_DATE, INVALID_REPLICATION_DATE);
    __valmap.put( _UNKNOWN_EXCEPTION, UNKNOWN_EXCEPTION);
    __valmap.put( _PASSWORD_LOCKOUT, PASSWORD_LOCKOUT);
    __valmap.put( _INVALID_LOGIN, INVALID_LOGIN);
    __valmap.put( _MALFORMED_QUERY, MALFORMED_QUERY);
    __valmap.put( _INVALID_QUERY_LOCATOR, INVALID_QUERY_LOCATOR);
    __valmap.put( _LOGIN_DURING_RESTRICTED_TIME, LOGIN_DURING_RESTRICTED_TIME);
    __valmap.put( _INVALID_QUERY_FILTER_OPERATOR, INVALID_QUERY_FILTER_OPERATOR);
    __valmap.put( _INVALID_SEARCH_SCOPE, INVALID_SEARCH_SCOPE);
    __valmap.put( _INSUFFICIENT_ACCESS, INSUFFICIENT_ACCESS);
    __valmap.put( _SERVER_UNAVAILABLE, SERVER_UNAVAILABLE);
    __valmap.put( _ORG_LOCKED, ORG_LOCKED);
    __valmap.put( _INVALID_CLIENT, INVALID_CLIENT);
    __valmap.put( _MALFORMED_SEARCH, MALFORMED_SEARCH);
    __valmap.put( _UNSUPPORTED_API_VERSION, UNSUPPORTED_API_VERSION);
    __valmap.put( _UNSUPPORTED_CLIENT, UNSUPPORTED_CLIENT);
    __valmap.put( _INVALID_SESSION_ID, INVALID_SESSION_ID);
    __valmap.put( _INVALID_TYPE, INVALID_TYPE);
    __valmap.put( _EXCEEDED_RATE_LIMIT, EXCEEDED_RATE_LIMIT);
    __valmap.put( _TRIAL_EXPIRED, TRIAL_EXPIRED);
    __valmap.put( _API_CURRENTLY_DISABLED, API_CURRENTLY_DISABLED);
    __valmap.put( _EXCEEDED_QUOTA, EXCEEDED_QUOTA);
    __valmap.put( _API_DISABLED_FOR_ORG, API_DISABLED_FOR_ORG);
    __valmap.put( _LOGIN_DURING_RESTRICTED_DOMAIN, LOGIN_DURING_RESTRICTED_DOMAIN);
    __valmap.put( _FUNCTIONALITY_NOT_ENABLED, FUNCTIONALITY_NOT_ENABLED);
    __valmap.put( _EXCEEDED_ID_LIMIT_ON_RETRIEVE, EXCEEDED_ID_LIMIT_ON_RETRIEVE);
    __valmap.put( _INVALID_SEARCH, INVALID_SEARCH);
    __valmap.put( _INVALID_FIELD, INVALID_FIELD);
    __valmap.put( _INVALID_BATCH_SIZE, INVALID_BATCH_SIZE);


    return __valmap;
  }


}


