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

// original type: ['urn:partner.soap.sforce.com']:StatusCode


public class StatusCode
  implements java.io.Serializable
{

  private java.lang.String __value;

  protected StatusCode(java.lang.String value) {
    __value = value;
  }

  public static final java.lang.String _INSUFFICIENT_ACCESS_ON_CROSS_REFERENCE_ENTITY = "INSUFFICIENT_ACCESS_ON_CROSS_REFERENCE_ENTITY";
  public static final rpctypes.StatusCode INSUFFICIENT_ACCESS_ON_CROSS_REFERENCE_ENTITY = new rpctypes.StatusCode(_INSUFFICIENT_ACCESS_ON_CROSS_REFERENCE_ENTITY);

  public static final java.lang.String _UNKNOWN_EXCEPTION = "UNKNOWN_EXCEPTION";
  public static final rpctypes.StatusCode UNKNOWN_EXCEPTION = new rpctypes.StatusCode(_UNKNOWN_EXCEPTION);

  public static final java.lang.String _MAX_ACTIVE_RULES_EXCEEDED = "MAX_ACTIVE_RULES_EXCEEDED";
  public static final rpctypes.StatusCode MAX_ACTIVE_RULES_EXCEEDED = new rpctypes.StatusCode(_MAX_ACTIVE_RULES_EXCEEDED);

  public static final java.lang.String _DUPLICATE_MASTER_LABEL = "DUPLICATE_MASTER_LABEL";
  public static final rpctypes.StatusCode DUPLICATE_MASTER_LABEL = new rpctypes.StatusCode(_DUPLICATE_MASTER_LABEL);

  public static final java.lang.String _MISSING_ARGUMENT = "MISSING_ARGUMENT";
  public static final rpctypes.StatusCode MISSING_ARGUMENT = new rpctypes.StatusCode(_MISSING_ARGUMENT);

  public static final java.lang.String _ENTITY_IS_ARCHIVED = "ENTITY_IS_ARCHIVED";
  public static final rpctypes.StatusCode ENTITY_IS_ARCHIVED = new rpctypes.StatusCode(_ENTITY_IS_ARCHIVED);

  public static final java.lang.String _DUPLICATE_CASE_SOLUTION = "DUPLICATE_CASE_SOLUTION";
  public static final rpctypes.StatusCode DUPLICATE_CASE_SOLUTION = new rpctypes.StatusCode(_DUPLICATE_CASE_SOLUTION);

  public static final java.lang.String _UNSPECIFIED_EMAIL_ADDRESS = "UNSPECIFIED_EMAIL_ADDRESS";
  public static final rpctypes.StatusCode UNSPECIFIED_EMAIL_ADDRESS = new rpctypes.StatusCode(_UNSPECIFIED_EMAIL_ADDRESS);

  public static final java.lang.String _FIELD_INTEGRITY_EXCEPTION = "FIELD_INTEGRITY_EXCEPTION";
  public static final rpctypes.StatusCode FIELD_INTEGRITY_EXCEPTION = new rpctypes.StatusCode(_FIELD_INTEGRITY_EXCEPTION);

  public static final java.lang.String _INVALID_CROSS_REFERENCE_TYPE_FOR_FIELD = "INVALID_CROSS_REFERENCE_TYPE_FOR_FIELD";
  public static final rpctypes.StatusCode INVALID_CROSS_REFERENCE_TYPE_FOR_FIELD = new rpctypes.StatusCode(_INVALID_CROSS_REFERENCE_TYPE_FOR_FIELD);

  public static final java.lang.String _INVALID_FILTER_ACTION = "INVALID_FILTER_ACTION";
  public static final rpctypes.StatusCode INVALID_FILTER_ACTION = new rpctypes.StatusCode(_INVALID_FILTER_ACTION);

  public static final java.lang.String _CHILD_SHARE_FAILS_PARENT = "CHILD_SHARE_FAILS_PARENT";
  public static final rpctypes.StatusCode CHILD_SHARE_FAILS_PARENT = new rpctypes.StatusCode(_CHILD_SHARE_FAILS_PARENT);

  public static final java.lang.String _REQUIRED_FIELD_MISSING = "REQUIRED_FIELD_MISSING";
  public static final rpctypes.StatusCode REQUIRED_FIELD_MISSING = new rpctypes.StatusCode(_REQUIRED_FIELD_MISSING);

  public static final java.lang.String _CANT_DISABLE_CORP_CURRENCY = "CANT_DISABLE_CORP_CURRENCY";
  public static final rpctypes.StatusCode CANT_DISABLE_CORP_CURRENCY = new rpctypes.StatusCode(_CANT_DISABLE_CORP_CURRENCY);

  public static final java.lang.String _TEXT_DATA_OUTSIDE_SUPPORTED_CHARSET = "TEXT_DATA_OUTSIDE_SUPPORTED_CHARSET";
  public static final rpctypes.StatusCode TEXT_DATA_OUTSIDE_SUPPORTED_CHARSET = new rpctypes.StatusCode(_TEXT_DATA_OUTSIDE_SUPPORTED_CHARSET);

  public static final java.lang.String _INACTIVE_OWNER_OR_USER = "INACTIVE_OWNER_OR_USER";
  public static final rpctypes.StatusCode INACTIVE_OWNER_OR_USER = new rpctypes.StatusCode(_INACTIVE_OWNER_OR_USER);

  public static final java.lang.String _INVALID_STATUS = "INVALID_STATUS";
  public static final rpctypes.StatusCode INVALID_STATUS = new rpctypes.StatusCode(_INVALID_STATUS);

  public static final java.lang.String _CANT_UNSET_CORP_CURRENCY = "CANT_UNSET_CORP_CURRENCY";
  public static final rpctypes.StatusCode CANT_UNSET_CORP_CURRENCY = new rpctypes.StatusCode(_CANT_UNSET_CORP_CURRENCY);

  public static final java.lang.String _MALFORMED_ID = "MALFORMED_ID";
  public static final rpctypes.StatusCode MALFORMED_ID = new rpctypes.StatusCode(_MALFORMED_ID);

  public static final java.lang.String _ENTITY_IS_DELETED = "ENTITY_IS_DELETED";
  public static final rpctypes.StatusCode ENTITY_IS_DELETED = new rpctypes.StatusCode(_ENTITY_IS_DELETED);

  public static final java.lang.String _INVALID_EMPTY_KEY_OWNER = "INVALID_EMPTY_KEY_OWNER";
  public static final rpctypes.StatusCode INVALID_EMPTY_KEY_OWNER = new rpctypes.StatusCode(_INVALID_EMPTY_KEY_OWNER);

  public static final java.lang.String _INVALID_BATCH_OPERATION = "INVALID_BATCH_OPERATION";
  public static final rpctypes.StatusCode INVALID_BATCH_OPERATION = new rpctypes.StatusCode(_INVALID_BATCH_OPERATION);

  public static final java.lang.String _MAXIMUM_DASHBOARD_COMPONENTS_EXCEEDED = "MAXIMUM_DASHBOARD_COMPONENTS_EXCEEDED";
  public static final rpctypes.StatusCode MAXIMUM_DASHBOARD_COMPONENTS_EXCEEDED = new rpctypes.StatusCode(_MAXIMUM_DASHBOARD_COMPONENTS_EXCEEDED);

  public static final java.lang.String _INVALID_ACCESS_LEVEL = "INVALID_ACCESS_LEVEL";
  public static final rpctypes.StatusCode INVALID_ACCESS_LEVEL = new rpctypes.StatusCode(_INVALID_ACCESS_LEVEL);

  public static final java.lang.String _CANNOT_REPARENT_RECORD = "CANNOT_REPARENT_RECORD";
  public static final rpctypes.StatusCode CANNOT_REPARENT_RECORD = new rpctypes.StatusCode(_CANNOT_REPARENT_RECORD);

  public static final java.lang.String _LAST_MODIFIED_SINCE_TOO_OLD = "LAST_MODIFIED_SINCE_TOO_OLD";
  public static final rpctypes.StatusCode LAST_MODIFIED_SINCE_TOO_OLD = new rpctypes.StatusCode(_LAST_MODIFIED_SINCE_TOO_OLD);

  public static final java.lang.String _INVALID_ASSIGNMENT_RULE = "INVALID_ASSIGNMENT_RULE";
  public static final rpctypes.StatusCode INVALID_ASSIGNMENT_RULE = new rpctypes.StatusCode(_INVALID_ASSIGNMENT_RULE);

  public static final java.lang.String _INVALID_TYPE = "INVALID_TYPE";
  public static final rpctypes.StatusCode INVALID_TYPE = new rpctypes.StatusCode(_INVALID_TYPE);

  public static final java.lang.String _INVALID_ASSIGNEE_TYPE = "INVALID_ASSIGNEE_TYPE";
  public static final rpctypes.StatusCode INVALID_ASSIGNEE_TYPE = new rpctypes.StatusCode(_INVALID_ASSIGNEE_TYPE);

  public static final java.lang.String _ENTITY_IS_LOCKED = "ENTITY_IS_LOCKED";
  public static final rpctypes.StatusCode ENTITY_IS_LOCKED = new rpctypes.StatusCode(_ENTITY_IS_LOCKED);

  public static final java.lang.String _CUSTOM_ENTITY_OR_FIELD_LIMIT = "CUSTOM_ENTITY_OR_FIELD_LIMIT";
  public static final rpctypes.StatusCode CUSTOM_ENTITY_OR_FIELD_LIMIT = new rpctypes.StatusCode(_CUSTOM_ENTITY_OR_FIELD_LIMIT);

  public static final java.lang.String _INVALID_OPERATOR = "INVALID_OPERATOR";
  public static final rpctypes.StatusCode INVALID_OPERATOR = new rpctypes.StatusCode(_INVALID_OPERATOR);

  public static final java.lang.String _NUMBER_OUTSIDE_VALID_RANGE = "NUMBER_OUTSIDE_VALID_RANGE";
  public static final rpctypes.StatusCode NUMBER_OUTSIDE_VALID_RANGE = new rpctypes.StatusCode(_NUMBER_OUTSIDE_VALID_RANGE);

  public static final java.lang.String _MAX_ACTIONS_PER_RULE_EXCEEDED = "MAX_ACTIONS_PER_RULE_EXCEEDED";
  public static final rpctypes.StatusCode MAX_ACTIONS_PER_RULE_EXCEEDED = new rpctypes.StatusCode(_MAX_ACTIONS_PER_RULE_EXCEEDED);

  public static final java.lang.String _ENTITY_FAILED_IFLASTMODIFIED_ON_UPDATE = "ENTITY_FAILED_IFLASTMODIFIED_ON_UPDATE";
  public static final rpctypes.StatusCode ENTITY_FAILED_IFLASTMODIFIED_ON_UPDATE = new rpctypes.StatusCode(_ENTITY_FAILED_IFLASTMODIFIED_ON_UPDATE);

  public static final java.lang.String _DELETE_FAILED = "DELETE_FAILED";
  public static final rpctypes.StatusCode DELETE_FAILED = new rpctypes.StatusCode(_DELETE_FAILED);

  public static final java.lang.String _INVALID_TYPE_FOR_OPERATION = "INVALID_TYPE_FOR_OPERATION";
  public static final rpctypes.StatusCode INVALID_TYPE_FOR_OPERATION = new rpctypes.StatusCode(_INVALID_TYPE_FOR_OPERATION);

  public static final java.lang.String _CANNOT_INSERT_UPDATE_ACTIVATE_ENTITY = "CANNOT_INSERT_UPDATE_ACTIVATE_ENTITY";
  public static final rpctypes.StatusCode CANNOT_INSERT_UPDATE_ACTIVATE_ENTITY = new rpctypes.StatusCode(_CANNOT_INSERT_UPDATE_ACTIVATE_ENTITY);

  public static final java.lang.String _CUSTOM_FIELD_INDEX_LIMIT_EXCEEDED = "CUSTOM_FIELD_INDEX_LIMIT_EXCEEDED";
  public static final rpctypes.StatusCode CUSTOM_FIELD_INDEX_LIMIT_EXCEEDED = new rpctypes.StatusCode(_CUSTOM_FIELD_INDEX_LIMIT_EXCEEDED);

  public static final java.lang.String _DUPLICATE_VALUE = "DUPLICATE_VALUE";
  public static final rpctypes.StatusCode DUPLICATE_VALUE = new rpctypes.StatusCode(_DUPLICATE_VALUE);

  public static final java.lang.String _MAXIMUM_SIZE_OF_ATTACHMENT = "MAXIMUM_SIZE_OF_ATTACHMENT";
  public static final rpctypes.StatusCode MAXIMUM_SIZE_OF_ATTACHMENT = new rpctypes.StatusCode(_MAXIMUM_SIZE_OF_ATTACHMENT);

  public static final java.lang.String _INVALID_ID_FIELD = "INVALID_ID_FIELD";
  public static final rpctypes.StatusCode INVALID_ID_FIELD = new rpctypes.StatusCode(_INVALID_ID_FIELD);

  public static final java.lang.String _MAXIMUM_CCEMAILS_EXCEEDED = "MAXIMUM_CCEMAILS_EXCEEDED";
  public static final rpctypes.StatusCode MAXIMUM_CCEMAILS_EXCEEDED = new rpctypes.StatusCode(_MAXIMUM_CCEMAILS_EXCEEDED);

  public static final java.lang.String _STANDARD_PRICE_NOT_DEFINED = "STANDARD_PRICE_NOT_DEFINED";
  public static final rpctypes.StatusCode STANDARD_PRICE_NOT_DEFINED = new rpctypes.StatusCode(_STANDARD_PRICE_NOT_DEFINED);

  public static final java.lang.String _CANNOT_UPDATE_CONVERTED_LEAD = "CANNOT_UPDATE_CONVERTED_LEAD";
  public static final rpctypes.StatusCode CANNOT_UPDATE_CONVERTED_LEAD = new rpctypes.StatusCode(_CANNOT_UPDATE_CONVERTED_LEAD);

  public static final java.lang.String _INVALID_EMAIL_ADDRESS = "INVALID_EMAIL_ADDRESS";
  public static final rpctypes.StatusCode INVALID_EMAIL_ADDRESS = new rpctypes.StatusCode(_INVALID_EMAIL_ADDRESS);

  public static final java.lang.String _INVALID_ARGUMENT_TYPE = "INVALID_ARGUMENT_TYPE";
  public static final rpctypes.StatusCode INVALID_ARGUMENT_TYPE = new rpctypes.StatusCode(_INVALID_ARGUMENT_TYPE);

  public static final java.lang.String _INVALID_LINEITEM_CLONE_STATE = "INVALID_LINEITEM_CLONE_STATE";
  public static final rpctypes.StatusCode INVALID_LINEITEM_CLONE_STATE = new rpctypes.StatusCode(_INVALID_LINEITEM_CLONE_STATE);

  public static final java.lang.String _STORAGE_LIMIT_EXCEEDED = "STORAGE_LIMIT_EXCEEDED";
  public static final rpctypes.StatusCode STORAGE_LIMIT_EXCEEDED = new rpctypes.StatusCode(_STORAGE_LIMIT_EXCEEDED);

  public static final java.lang.String _MAXIMUM_SIZE_OF_DOCUMENT = "MAXIMUM_SIZE_OF_DOCUMENT";
  public static final rpctypes.StatusCode MAXIMUM_SIZE_OF_DOCUMENT = new rpctypes.StatusCode(_MAXIMUM_SIZE_OF_DOCUMENT);

  public static final java.lang.String _INVALID_CURRENCY_ISO = "INVALID_CURRENCY_ISO";
  public static final rpctypes.StatusCode INVALID_CURRENCY_ISO = new rpctypes.StatusCode(_INVALID_CURRENCY_ISO);

  public static final java.lang.String _DEPENDENCY_EXISTS = "DEPENDENCY_EXISTS";
  public static final rpctypes.StatusCode DEPENDENCY_EXISTS = new rpctypes.StatusCode(_DEPENDENCY_EXISTS);

  public static final java.lang.String _ASSIGNEE_TYPE_REQUIRED = "ASSIGNEE_TYPE_REQUIRED";
  public static final rpctypes.StatusCode ASSIGNEE_TYPE_REQUIRED = new rpctypes.StatusCode(_ASSIGNEE_TYPE_REQUIRED);

  public static final java.lang.String _INVALID_CROSS_REFERENCE_KEY = "INVALID_CROSS_REFERENCE_KEY";
  public static final rpctypes.StatusCode INVALID_CROSS_REFERENCE_KEY = new rpctypes.StatusCode(_INVALID_CROSS_REFERENCE_KEY);

  public static final java.lang.String _MAX_TASK_DESCRIPTION_EXCEEEDED = "MAX_TASK_DESCRIPTION_EXCEEEDED";
  public static final rpctypes.StatusCode MAX_TASK_DESCRIPTION_EXCEEEDED = new rpctypes.StatusCode(_MAX_TASK_DESCRIPTION_EXCEEEDED);

  public static final java.lang.String _CANNOT_RESOLVE_NAME = "CANNOT_RESOLVE_NAME";
  public static final rpctypes.StatusCode CANNOT_RESOLVE_NAME = new rpctypes.StatusCode(_CANNOT_RESOLVE_NAME);

  public static final java.lang.String _DUPLICATE_USERNAME = "DUPLICATE_USERNAME";
  public static final rpctypes.StatusCode DUPLICATE_USERNAME = new rpctypes.StatusCode(_DUPLICATE_USERNAME);

  public static final java.lang.String _DUPLICATE_DEVELOPER_NAME = "DUPLICATE_DEVELOPER_NAME";
  public static final rpctypes.StatusCode DUPLICATE_DEVELOPER_NAME = new rpctypes.StatusCode(_DUPLICATE_DEVELOPER_NAME);

  public static final java.lang.String _FAILED_ACTIVATION = "FAILED_ACTIVATION";
  public static final rpctypes.StatusCode FAILED_ACTIVATION = new rpctypes.StatusCode(_FAILED_ACTIVATION);

  public static final java.lang.String _INVALID_TYPE_ON_FIELD_IN_RECORD = "INVALID_TYPE_ON_FIELD_IN_RECORD";
  public static final rpctypes.StatusCode INVALID_TYPE_ON_FIELD_IN_RECORD = new rpctypes.StatusCode(_INVALID_TYPE_ON_FIELD_IN_RECORD);

  public static final java.lang.String _INSUFFICIENT_ACCESS_OR_READONLY = "INSUFFICIENT_ACCESS_OR_READONLY";
  public static final rpctypes.StatusCode INSUFFICIENT_ACCESS_OR_READONLY = new rpctypes.StatusCode(_INSUFFICIENT_ACCESS_OR_READONLY);

  public static final java.lang.String _INVALID_CREDIT_CARD_INFO = "INVALID_CREDIT_CARD_INFO";
  public static final rpctypes.StatusCode INVALID_CREDIT_CARD_INFO = new rpctypes.StatusCode(_INVALID_CREDIT_CARD_INFO);

  public static final java.lang.String _INVALID_OR_NULL_FOR_RESTRICTED_PICKLIST = "INVALID_OR_NULL_FOR_RESTRICTED_PICKLIST";
  public static final rpctypes.StatusCode INVALID_OR_NULL_FOR_RESTRICTED_PICKLIST = new rpctypes.StatusCode(_INVALID_OR_NULL_FOR_RESTRICTED_PICKLIST);

  public static final java.lang.String _CIRCULAR_DEPENDENCY = "CIRCULAR_DEPENDENCY";
  public static final rpctypes.StatusCode CIRCULAR_DEPENDENCY = new rpctypes.StatusCode(_CIRCULAR_DEPENDENCY);

  public static final java.lang.String _CANNOT_CASCADE_PRODUCT_ACTIVE = "CANNOT_CASCADE_PRODUCT_ACTIVE";
  public static final rpctypes.StatusCode CANNOT_CASCADE_PRODUCT_ACTIVE = new rpctypes.StatusCode(_CANNOT_CASCADE_PRODUCT_ACTIVE);

  public static final java.lang.String _SHARE_NEEDED_FOR_CHILD_OWNER = "SHARE_NEEDED_FOR_CHILD_OWNER";
  public static final rpctypes.StatusCode SHARE_NEEDED_FOR_CHILD_OWNER = new rpctypes.StatusCode(_SHARE_NEEDED_FOR_CHILD_OWNER);

  public static final java.lang.String _INVALID_FIELD_FOR_INSERT_UPDATE = "INVALID_FIELD_FOR_INSERT_UPDATE";
  public static final rpctypes.StatusCode INVALID_FIELD_FOR_INSERT_UPDATE = new rpctypes.StatusCode(_INVALID_FIELD_FOR_INSERT_UPDATE);

  public static final java.lang.String _LICENSE_LIMIT_EXCEEDED = "LICENSE_LIMIT_EXCEEDED";
  public static final rpctypes.StatusCode LICENSE_LIMIT_EXCEEDED = new rpctypes.StatusCode(_LICENSE_LIMIT_EXCEEDED);



  private static final java.util.Map _valueMap = _buildValueMap();


  // Gets the value for a enumerated value
  public java.lang.String getValue() {
    return __value;
  }


  // Gets enumeration with a specific value
  // throws java.lang.IllegalArgumentException if
  // any invalid value is specified
   public static  rpctypes.StatusCode fromValue(java.lang.String value) {
    Object obj = _valueMap.get(value);
    if (obj==null) {
      java.lang.String msg = invalidValueMsg(""+value);
    msg=msg+(" valmap="+_valueMap);
      throw new java.lang.IllegalArgumentException(msg);
    }
    return (rpctypes.StatusCode)obj;

   }

  private static java.lang.String invalidValueMsg(java.lang.String value) {
    java.lang.String msg = "invalid enumeration value: " + value;
    return msg;
  }


   // Gets enumeration from a String
   // throws java.lang.IllegalArgumentException if
   // any invalid value is specified
   public static rpctypes.StatusCode fromString(java.lang.String value) {
    java.lang.String __tmp = 
     __tmp = value;
    return fromValue(__tmp);

   }


   // Returns String representation of the enumerated value
   public java.lang.String toString() {
     return java.lang.String.valueOf(__value);
   }

   public boolean equals(java.lang.Object obj) {
     if (obj instanceof rpctypes.StatusCode) {
       java.lang.String tmp_val = ((rpctypes.StatusCode) obj).getValue();
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

    __valmap.put( _INSUFFICIENT_ACCESS_ON_CROSS_REFERENCE_ENTITY, INSUFFICIENT_ACCESS_ON_CROSS_REFERENCE_ENTITY);
    __valmap.put( _UNKNOWN_EXCEPTION, UNKNOWN_EXCEPTION);
    __valmap.put( _MAX_ACTIVE_RULES_EXCEEDED, MAX_ACTIVE_RULES_EXCEEDED);
    __valmap.put( _DUPLICATE_MASTER_LABEL, DUPLICATE_MASTER_LABEL);
    __valmap.put( _MISSING_ARGUMENT, MISSING_ARGUMENT);
    __valmap.put( _ENTITY_IS_ARCHIVED, ENTITY_IS_ARCHIVED);
    __valmap.put( _DUPLICATE_CASE_SOLUTION, DUPLICATE_CASE_SOLUTION);
    __valmap.put( _UNSPECIFIED_EMAIL_ADDRESS, UNSPECIFIED_EMAIL_ADDRESS);
    __valmap.put( _FIELD_INTEGRITY_EXCEPTION, FIELD_INTEGRITY_EXCEPTION);
    __valmap.put( _INVALID_CROSS_REFERENCE_TYPE_FOR_FIELD, INVALID_CROSS_REFERENCE_TYPE_FOR_FIELD);
    __valmap.put( _INVALID_FILTER_ACTION, INVALID_FILTER_ACTION);
    __valmap.put( _CHILD_SHARE_FAILS_PARENT, CHILD_SHARE_FAILS_PARENT);
    __valmap.put( _REQUIRED_FIELD_MISSING, REQUIRED_FIELD_MISSING);
    __valmap.put( _CANT_DISABLE_CORP_CURRENCY, CANT_DISABLE_CORP_CURRENCY);
    __valmap.put( _TEXT_DATA_OUTSIDE_SUPPORTED_CHARSET, TEXT_DATA_OUTSIDE_SUPPORTED_CHARSET);
    __valmap.put( _INACTIVE_OWNER_OR_USER, INACTIVE_OWNER_OR_USER);
    __valmap.put( _INVALID_STATUS, INVALID_STATUS);
    __valmap.put( _CANT_UNSET_CORP_CURRENCY, CANT_UNSET_CORP_CURRENCY);
    __valmap.put( _MALFORMED_ID, MALFORMED_ID);
    __valmap.put( _ENTITY_IS_DELETED, ENTITY_IS_DELETED);
    __valmap.put( _INVALID_EMPTY_KEY_OWNER, INVALID_EMPTY_KEY_OWNER);
    __valmap.put( _INVALID_BATCH_OPERATION, INVALID_BATCH_OPERATION);
    __valmap.put( _MAXIMUM_DASHBOARD_COMPONENTS_EXCEEDED, MAXIMUM_DASHBOARD_COMPONENTS_EXCEEDED);
    __valmap.put( _INVALID_ACCESS_LEVEL, INVALID_ACCESS_LEVEL);
    __valmap.put( _CANNOT_REPARENT_RECORD, CANNOT_REPARENT_RECORD);
    __valmap.put( _LAST_MODIFIED_SINCE_TOO_OLD, LAST_MODIFIED_SINCE_TOO_OLD);
    __valmap.put( _INVALID_ASSIGNMENT_RULE, INVALID_ASSIGNMENT_RULE);
    __valmap.put( _INVALID_TYPE, INVALID_TYPE);
    __valmap.put( _INVALID_ASSIGNEE_TYPE, INVALID_ASSIGNEE_TYPE);
    __valmap.put( _ENTITY_IS_LOCKED, ENTITY_IS_LOCKED);
    __valmap.put( _CUSTOM_ENTITY_OR_FIELD_LIMIT, CUSTOM_ENTITY_OR_FIELD_LIMIT);
    __valmap.put( _INVALID_OPERATOR, INVALID_OPERATOR);
    __valmap.put( _NUMBER_OUTSIDE_VALID_RANGE, NUMBER_OUTSIDE_VALID_RANGE);
    __valmap.put( _MAX_ACTIONS_PER_RULE_EXCEEDED, MAX_ACTIONS_PER_RULE_EXCEEDED);
    __valmap.put( _ENTITY_FAILED_IFLASTMODIFIED_ON_UPDATE, ENTITY_FAILED_IFLASTMODIFIED_ON_UPDATE);
    __valmap.put( _DELETE_FAILED, DELETE_FAILED);
    __valmap.put( _INVALID_TYPE_FOR_OPERATION, INVALID_TYPE_FOR_OPERATION);
    __valmap.put( _CANNOT_INSERT_UPDATE_ACTIVATE_ENTITY, CANNOT_INSERT_UPDATE_ACTIVATE_ENTITY);
    __valmap.put( _CUSTOM_FIELD_INDEX_LIMIT_EXCEEDED, CUSTOM_FIELD_INDEX_LIMIT_EXCEEDED);
    __valmap.put( _DUPLICATE_VALUE, DUPLICATE_VALUE);
    __valmap.put( _MAXIMUM_SIZE_OF_ATTACHMENT, MAXIMUM_SIZE_OF_ATTACHMENT);
    __valmap.put( _INVALID_ID_FIELD, INVALID_ID_FIELD);
    __valmap.put( _MAXIMUM_CCEMAILS_EXCEEDED, MAXIMUM_CCEMAILS_EXCEEDED);
    __valmap.put( _STANDARD_PRICE_NOT_DEFINED, STANDARD_PRICE_NOT_DEFINED);
    __valmap.put( _CANNOT_UPDATE_CONVERTED_LEAD, CANNOT_UPDATE_CONVERTED_LEAD);
    __valmap.put( _INVALID_EMAIL_ADDRESS, INVALID_EMAIL_ADDRESS);
    __valmap.put( _INVALID_ARGUMENT_TYPE, INVALID_ARGUMENT_TYPE);
    __valmap.put( _INVALID_LINEITEM_CLONE_STATE, INVALID_LINEITEM_CLONE_STATE);
    __valmap.put( _STORAGE_LIMIT_EXCEEDED, STORAGE_LIMIT_EXCEEDED);
    __valmap.put( _MAXIMUM_SIZE_OF_DOCUMENT, MAXIMUM_SIZE_OF_DOCUMENT);
    __valmap.put( _INVALID_CURRENCY_ISO, INVALID_CURRENCY_ISO);
    __valmap.put( _DEPENDENCY_EXISTS, DEPENDENCY_EXISTS);
    __valmap.put( _ASSIGNEE_TYPE_REQUIRED, ASSIGNEE_TYPE_REQUIRED);
    __valmap.put( _INVALID_CROSS_REFERENCE_KEY, INVALID_CROSS_REFERENCE_KEY);
    __valmap.put( _MAX_TASK_DESCRIPTION_EXCEEEDED, MAX_TASK_DESCRIPTION_EXCEEEDED);
    __valmap.put( _CANNOT_RESOLVE_NAME, CANNOT_RESOLVE_NAME);
    __valmap.put( _DUPLICATE_USERNAME, DUPLICATE_USERNAME);
    __valmap.put( _DUPLICATE_DEVELOPER_NAME, DUPLICATE_DEVELOPER_NAME);
    __valmap.put( _FAILED_ACTIVATION, FAILED_ACTIVATION);
    __valmap.put( _INVALID_TYPE_ON_FIELD_IN_RECORD, INVALID_TYPE_ON_FIELD_IN_RECORD);
    __valmap.put( _INSUFFICIENT_ACCESS_OR_READONLY, INSUFFICIENT_ACCESS_OR_READONLY);
    __valmap.put( _INVALID_CREDIT_CARD_INFO, INVALID_CREDIT_CARD_INFO);
    __valmap.put( _INVALID_OR_NULL_FOR_RESTRICTED_PICKLIST, INVALID_OR_NULL_FOR_RESTRICTED_PICKLIST);
    __valmap.put( _CIRCULAR_DEPENDENCY, CIRCULAR_DEPENDENCY);
    __valmap.put( _CANNOT_CASCADE_PRODUCT_ACTIVE, CANNOT_CASCADE_PRODUCT_ACTIVE);
    __valmap.put( _SHARE_NEEDED_FOR_CHILD_OWNER, SHARE_NEEDED_FOR_CHILD_OWNER);
    __valmap.put( _INVALID_FIELD_FOR_INSERT_UPDATE, INVALID_FIELD_FOR_INSERT_UPDATE);
    __valmap.put( _LICENSE_LIMIT_EXCEEDED, LICENSE_LIMIT_EXCEEDED);


    return __valmap;
  }


}


