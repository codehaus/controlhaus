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
<netui-template:template templatePage="/resources/jsp/template.jsp">
    <netui-template:setAttribute value="Choose Lucene Sample Action" name="title"/>
    
    <netui-template:section name="bodySection">
    This sample application simulates adding email messages to the Lucene search index, and then performing searches on that index.</p>
    
    Choose the action you would like to perform: add "emails" to the search index, or execute a search.<p>
    
    <table>
        <tr><td><a href="addEmails.jsp">Add "emails"</a></td></tr>
        <tr><td><a href="executeSearch.jsp">Execute search</a></td></tr>
    </table>
    </netui-template:section>
</netui-template:template>