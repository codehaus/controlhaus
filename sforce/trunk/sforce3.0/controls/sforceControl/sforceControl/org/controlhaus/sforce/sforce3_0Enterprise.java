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

package org.controlhaus.sforce; 

import com.bea.control.Control;

public interface sforce3_0Enterprise extends Control
{ 
    /**
     * Start a session with the sforce web service.  
     * See the <a href="http://www.sforce.com/us/docs/sforce30/sforce_API_calls_search.html">Online API reference</a> for usage
     * @param username  An sforce user name (email format)
     * @param password  The corresponding sforce password
     * @return LoginResult Contains the session key and a new endpoint URL to use for subsequent calls
     */

    com.sforce.soap.enterprise.LoginResult login(java.lang.String username, java.lang.String password);

    /**
     * Query for sforce objects based on a query string similar to a SQL SELECT statement.
     * See the <a href="http://www.sforce.com/us/docs/sforce30/sforce_API_calls_query.html">Online API reference</a> for usage
     * @param queryString  A string specifying SELECT <field names> FROM <sObject type> WHERE <criteria>
     * @return QueryResult The objects that match the criteria specified by the query
     */
    com.sforce.soap.enterprise.QueryResult query(java.lang.String queryString);

    /**
     * Query for more results based on a previous call to query() that returned more than the 
     * current batch size (default 2000).
     * @param queryLocator  The identifier returned by the query() method if number of objects that 
     * qualify exceeds the batch size
     * @return QueryResult The objects that match the criteria specified by the query
     */
    com.sforce.soap.enterprise.QueryResult queryMore(java.lang.String queryLocator);

    /**
     * Search among sforce objects based on a search string, similar to internet search syntax.
     * See the <a href="http://www.sforce.com/us/docs/sforce30/sforce_API_calls_search.html">Online API reference</a> for usage
     * @param searchString
     * @return SearchResult The result of the search
     */
    com.sforce.soap.enterprise.SearchResult search(java.lang.String searchString);

    /**
     * Use the retrieve call to retrieve individual sforce objects when you knowo their ID.
     * object. User passes the fields to retrieve, the object, and the ID to retrieve.
     * See the <a href="http://www.sforce.com/us/docs/sforce30/sforce_API_calls_retrieve.html">Online API reference</a> for usage
     * @param fieldList Field to retrieve
     * @param sObjectType The sforce object
     * @param id The ID to retireve
     * @return SObject A standard base sforce object
     */
    com.sforce.soap.enterprise.sobject.SObject retrieve(java.lang.String fieldList, java.lang.String sObjectType, java.lang.String id);

    /**
     * A variant of the retrieve method that uses document-type XMLBean objects for the input and return.  
     * For use in Java Process Flows.
     * @param requestDoc A document-type XMLBean containing the parameters to the retrieve method.
     * @return CreateResponseDocument The returned document-type XMLBean that contains the retrieved sObject.
     */
    com.sforce.soap.enterprise.RetrieveResponseDocument retrieveDoc(com.sforce.soap.enterprise.RetrieveDocument requestDoc);

    /**
     * Create one or more sforce objects
     * See the <a href="http://www.sforce.com/us/docs/sforce30/sforce_API_calls_create.html">Online API reference</a> for usage
     * @param sObjects An array of any type of sforce objects (objects in package com.sforce.soap.sobject)
     * @return SaveResult[] Result array of the save operation
     */
    com.sforce.soap.enterprise.SaveResult[] create(com.sforce.soap.enterprise.sobject.SObject[] sObjects);

    /**
     * A variant of the create method that uses document-type XMLBean objects for the input and return.  
     * For use in Java Process Flows.
     * @param requestDoc A document-type XMLBean containing the array of sObjects to create.
     * @return CreateResponseDocument The returned document-type XMLBean that contains the SaveResult array.
     */
    com.sforce.soap.enterprise.CreateResponseDocument createDoc(com.sforce.soap.enterprise.CreateDocument requestDoc);

    /**
     * A simplified variant of the create method for a single object instead of an array of objects.
     * @param sObject A single instance of an sforce object. Can be any subclass of sObject.
     * @return SaveResult Result of the create operation
     */
    com.sforce.soap.enterprise.SaveResult createOne(com.sforce.soap.enterprise.sobject.SObject sObject);

    /**
     * Deletes one or more sforce objects
     * See the <a href="http://www.sforce.com/us/docs/sforce30/sforce_API_calls_delete.html">Online API reference</a> for usage
     * @param sObjects An array of any type of sforce objects (objects in package com.sforce.soap.sobject)
     * @return DeleteResult[] Result array of the delete operation
     */
    com.sforce.soap.enterprise.DeleteResult[] delete(java.lang.String[] ids);

    /**
     * A variant of the delete method that uses document-type XMLBean objects for the input and return.  
     * For use in Java Process Flows.
     * @param requestDoc A document-type XMLBean containing the array of sObjects to delete.
     * @return DeleteResponseDocument The returned document-type XMLBean that contains the DeleteResult array.
     */
    com.sforce.soap.enterprise.DeleteResponseDocument deleteDoc(com.sforce.soap.enterprise.DeleteDocument requestDoc);

    /**
     * A simplified variant of the delete method for a single object instead of an array of objects.
     * @param sObject A single instance of an sforce object. Can be any subclass of sObject.
     * @return DeleteResult Result of the delete operation
     */
    com.sforce.soap.enterprise.DeleteResult deleteOne(java.lang.String id);

    /**
     * Retrieves the list of individual objects that have been deleted within the
     * given timespan for the specified object
     * @param sObjectType sforce object type
     * @param startDate Date where to start
     * @param endDate Date where to end
     * @return GetDeletedResult The result in SOAP object
     */
    com.sforce.soap.enterprise.GetDeletedResult getDeleted(java.lang.String sObjectType, java.util.Calendar startDate, java.util.Calendar endDate);

    /**
     * Update one or more sforce objects
     * See the <a href="http://www.sforce.com/us/docs/sforce30/sforce_API_calls_update.html">Online API reference</a> for usage
     * @param sObjects An array of any type of sforce objects (objects in package com.sforce.soap.sobject)
     * @return SaveResult[] Result array of the save operation
     */
    com.sforce.soap.enterprise.SaveResult[] update(com.sforce.soap.enterprise.sobject.SObject[] sObjects);
    
    /**
     * A variant of the update method that takes a document-type object, used in Java Process Flows
     * @param requestDoc The document-type XMLBean containing the array of sObjects to update 
     * @return UpdateResponseDocument The retrieved document-type object containing the SaveResult array
     */
    com.sforce.soap.enterprise.UpdateResponseDocument updateDoc(com.sforce.soap.enterprise.UpdateDocument requestDoc);

    /**
     * A simplified variant of the update method for a single object instead of an array of objects.
     * @param sObject A single instance of an sforce object. Can be any subclass of sObject.
     * @return SaveResult Result of the update operation
     */
    com.sforce.soap.enterprise.SaveResult updateOne(com.sforce.soap.enterprise.sobject.SObject sObject);
    
    /**
     * Retrieves the list of individual objects that have been updated within the
     * given timespan for the specified object
     * @param sObjectType sforce object type
     * @param startDate Date where to start
     * @param endDate Date where to end
     * @return GetUpdatedResult The result in SOAP object
     */
    com.sforce.soap.enterprise.GetUpdatedResult getUpdated(java.lang.String sObjectType, java.util.Calendar startDate, java.util.Calendar endDate);

    /**
     * Use describeGlobal to obtain the list of available objects for your
     * organization.
     * See the <a href="http://www.sforce.com/us/docs/sforce30/sforce_API_calls_describeGlobal.html">Online API reference</a> for usage
     * @return DescribeGlobalResult use this object to navigate through.
     */
    com.sforce.soap.enterprise.DescribeGlobalResult describeGlobal();

    /**
     * Describes metadata (field list and object properties) for the specified
     * object.
     * See the <a href="http://www.sforce.com/us/docs/sforce30/sforce_API_calls_describeSObject.html">Online API reference</a> for usage
     * @param sObjectType Typwe of the sforce object
     * @return DescribeSObjectResult The described object properties
     */
    com.sforce.soap.enterprise.DescribeSObjectResult describeSObject(java.lang.String sObjectType);

    /**
     * Retrieves the current system timestamp (GMT) from the sforce Web service.
     * @return Calendar The server timestamp
     */
    java.util.Calendar getServerTimestamp();

    /**
     * Retrieves personal information for the user associated with the current session.
     * @return GetUserInfoResult The returned user information.
     */
    com.sforce.soap.enterprise.GetUserInfoResult getUserInfo();

    /**
     * Use resetPassword to request that the sforce Web service change a user's
     * password and return the server-generated password string. Use setPassword
     * instead if you want to set the password to a specific value.
     * @param userId the id of the logged user
     * @return String The password
     */
    java.lang.String resetPassword(java.lang.String userId);

    /**
     * Use setPassword to change a user's password to a value that you specify.
     * For example, a client application might prompt a user to specify a
     * different password, and then invoke setPassword to change the user's login
     * password. Use resetPassword instead if you want to reset the password with
     * an sforce Web service-generated value. Your must be logged in with
     * sufficient access rights to change the password for the specified user
     * @param userId the id of the user
     * @param userId the password of the user
     * @return String The password
     */
    com.sforce.soap.enterprise.SetPasswordResult setPassword(java.lang.String userId, java.lang.String password);

    /**
     * Set the query-batch-size value programmatically, overriding the value of the corresponding @jc:sforce-properties 
     * (if any) or the system defaults. The new options stay in effect until  another call to setQueryOptions 
     * or a call to resetQueryOptionsToDefault.
     * @param batchSize The new batch size value, setting the maximum number of objects to return
     * in one call to query() or queryMore()
     */
    void setQueryOptions(int batchSize);

    /**
     * Set the save options programatically, overriding the value of the corresponding @jc:sforce-properties 
     * (if any) or the system defaults. The new options stay in effect until  another call to setSaveOptions 
     * or a call to resetSaveOptionsToDefault.
     * @param assignmentRuleId The new assignment rule Id
     * @param autoAssign The new autoAssign parameter. By default is true
     */
    void setSaveOptions(java.lang.String assignmentRuleId, boolean autoAssign);

    /**
     * Resets query options to the @jc:sforce-properties query-batch-size value, if one
     * is specified, or reset to the system default (2000) if the query-batch-size is not specified.
     */
    void resetQueryOptionsToDefault();

    /**
     * Resets save options to the @jc:sforce-properties save-auto-assign and save-assign-rule-id values
     * if specified, or reset to the system defaults if values are not specified on the control.
     */
    void resetSaveOptionsToDefault();

} 
