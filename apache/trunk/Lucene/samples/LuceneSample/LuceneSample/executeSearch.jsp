<%-- 
   Copyright 2004 BEA Systems, Inc.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

--%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="c.tld" %>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<netui-template:template templatePage="/resources/jsp/template.jsp">
    <netui-template:setAttribute value="Search for an email" name="title"/>
    
    <netui-template:section name="bodySection">
    You can now specify a query and execute it against the Lucene index.
    
    <netui:form action="searchEmails">
    <table>
        <tr>
            <td>Field to search:</td>
            <td>
                <select name="field">
                    <option value="to">To:
                    <option value="from">From:
                    <option value="subject">Subject:
                    <option value="message">Message                    
                </select>
            </td>
        </tr>
        <tr><td>Query String</td><td><input type="text" name="query"/></td></tr>
    </table>
    <netui:button value="Submit" type="submit"/>
    </netui:form>
    </netui-template:section>
</netui-template:template>