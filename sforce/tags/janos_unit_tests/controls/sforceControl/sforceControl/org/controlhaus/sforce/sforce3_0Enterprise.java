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

    com.sforce.soap.enterprise.LoginResult login(java.lang.String username, java.lang.String password);

    com.sforce.soap.enterprise.QueryResult query(java.lang.String queryString);

    com.sforce.soap.enterprise.QueryResult queryMore(java.lang.String queryLocator);

    /**
     * Create a Sales Force object array
     * @param sObjects A base Sales Force object
     * @return SaveResult[] Result array of the save operation
     */
    com.sforce.soap.enterprise.SaveResult[] create(com.sforce.soap.enterprise.sobject.SObject[] sObjects);

    /**
     * Retrieves a SOAP message document
     * @param requestDoc A SOAP request document
     * @return CreateResponseDocument The retrieved SOAP message document
     */
    com.sforce.soap.enterprise.CreateResponseDocument createDoc(com.sforce.soap.enterprise.CreateDocument requestDoc);

    /**
     * Create a Sales Force object - It is a simplified method, user can avoid
     * tedious array coding usin this method
     * @param sObjects A base Sales Force object
     * @return SaveResult Result of the save operation
     */
    com.sforce.soap.enterprise.SaveResult createOne(com.sforce.soap.enterprise.sobject.SObject sObject);

    /**
     * Deletes a Sales Force object array
     * @param sObjects A base Sales Force object
     * @return DeleteResult[] Result array of the delete operation
     */
    com.sforce.soap.enterprise.DeleteResult[] delete(java.lang.String[] ids);

    /**
     * Retrieves a SOAP message document
     * @param requestDoc A SOAP request document
     * @return DeleteResponseDocument The retrieved SOAP message document
     */
    com.sforce.soap.enterprise.DeleteResponseDocument deleteDoc(com.sforce.soap.enterprise.DeleteDocument requestDoc);

    /**
     * Deletes a Sales Force object - It is a simplified method, user can avoid
     * tedious array coding usin this method
     * @param sObjects A base Sales Force object
     * @return DeleteResult Result of the update operation
     */
    com.sforce.soap.enterprise.DeleteResult deleteOne(java.lang.String id);

    /**
     * Retrieves the list of individual objects that have been deleted within the
     * given timespan for the specified object
     * @param sObjectType Sales Force object type
     * @param startDate Date where to start
     * @param endDate Date where to end
     * @return GetDeletedResult The result in SOAP object
     */
    com.sforce.soap.enterprise.GetDeletedResult getDeleted(java.lang.String sObjectType, java.util.Calendar startDate, java.util.Calendar endDate);

    /**
     * Retrieves the list of individual objects that have been updated within the
     * given timespan for the specified object
     * @param sObjectType Sales Force object type
     * @param startDate Date where to start
     * @param endDate Date where to end
     * @return GetUpdatedResult The result in SOAP object
     */
    com.sforce.soap.enterprise.GetUpdatedResult getUpdated(java.lang.String sObjectType, java.util.Calendar startDate, java.util.Calendar endDate);

    /**
     * Use describeGlobal to obtain the list of available objects for your
     * organization.
     * @return DescribeGlobalResult use this object to navigate through.
     */
    com.sforce.soap.enterprise.DescribeGlobalResult describeGlobal();

    /**
     * Describes metadata (field list and object properties) for the specified
     * object.
     * @param sObjectType Typwe of the Sales Force object
     * @return DescribeSObjectResult The described object properties
     */
    com.sforce.soap.enterprise.DescribeSObjectResult describeSObject(java.lang.String sObjectType);

    /**
     * Retrieves the current system timestamp (GMT) from the sforce Web service.
     * @return Calendar The server timestamp
     */
    java.util.Calendar getServerTimestamp();

    /**
     * Retrieves personal information for the user associated with the current
     * session.
     * @return GetUserInfoResult The returned result
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
     * use this call to set the query option programmatically. It lasts until
     * user calls resetQueryOptionsToDefault - default means annotation value, if
     * any, or the system default
     * @param batchSize The new batch size
     */
    void setQueryOptions(int batchSize);

    /**
     * Reset query options to default - default means annotation value, if any,
     * or the system default
     */
    void resetQueryOptionsToDefault();

    /**
     * Use this call to set the save options programmatically. It lasts until
     * user call resetSaveOptionsToDefault - default means annotation value, if
     * any, or the system default
     * @param assignmentRuleId The new assignment rule Id
     * @param autoAssign The new autoAssign parameter. By default is true
     */
    void setSaveOptions(java.lang.String assignmentRuleId, boolean autoAssign);

    /**
     * Reset query options to default - default means annotation value, if any,
     * or the system default
     */
    void resetSaveOptionsToDefault();

    /**
     * Search among Sales Force objects
     * @param searchString
     * @return SearchResult The result of the search
     */
    com.sforce.soap.enterprise.SearchResult search(java.lang.String searchString);

    /**
     * Use the retrieve call to retrieve individual objects from an sforce API
     * object. User passes the field to retrieve, the object, and the ID to
     * retrieve.
     * @param fieldList Field to retrieve
     * @param sObjectType The Sales Force object
     * @param id The ID to retireve
     * @return SObject A standard base Sales Force object
     */
    com.sforce.soap.enterprise.sobject.SObject retrieve(java.lang.String fieldList, java.lang.String sObjectType, java.lang.String id);

    /**
     * Retrieves a SOAP message document
     * @param requestDoc A SOAP request document
     * @return UpdateResponseDocument The retrieved SOAP message document
     */
    com.sforce.soap.enterprise.UpdateResponseDocument updateDoc(com.sforce.soap.enterprise.UpdateDocument requestDoc);

    /**
     * Retrieves a SOAP message document
     * @param requestDoc A SOAP request document
     * @return RetrieveResponseDocument The retrieved SOAP message document
     */
    com.sforce.soap.enterprise.RetrieveResponseDocument retrieveDoc(com.sforce.soap.enterprise.RetrieveDocument requestDoc);

    /**
     * Updates a Sales Force object - It is a simplified method, user can avoid
     * tedious array coding usin this method
     * @param sObjects A base Sales Force object
     * @return SaveResult Result of the update operation
     */
    com.sforce.soap.enterprise.SaveResult updateOne(com.sforce.soap.enterprise.sobject.SObject sObject);
} 
