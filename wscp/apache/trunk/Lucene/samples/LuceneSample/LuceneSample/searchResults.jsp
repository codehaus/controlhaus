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
    <netui-template:setAttribute value="Search Results" name="title"/>    
    <netui-template:section name="bodySection">
    
    <table>
        <tr><td>Score</td><td>From</td><td>To</td><td>Subject</td><td>Message</td></tr>
        
        <c:forEach var="hit" items="${results}">
            <tr>
                <td>
                    <c:out value="${hit.score}"/>
                </td>
                <td>
                    <c:out value="${hit.fields['from'].value}"/>
                </td>
                <td>
                    <c:out value="${hit.fields['to'].value}"/>
                </td>
                <td>
                    <c:out value="${hit.fields['subject'].value}"/>
                </td>
                <td>
                    <c:out value="${hit.fields['message'].value}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    
    </netui-template:section>
</netui-template:template>