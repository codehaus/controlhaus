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
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib prefix="c" uri="c.tld" %>
<netui-template:template templatePage="/resources/jsp/template.jsp">
    <netui-template:setAttribute value="Book Details" name="title"/>
    <netui-template:section name="bodySection">
        <br/>
        <c:choose>
        <c:when test='${results.error}'>
            <h2>There are an error executing the Amazon search:</h2>
            <c:out value="${results.errorMessage}"/><p>
        </c:when>
        <c:when test='${results.numResults != 1}'>
            <h4>Expected exactly one match, got <c:out value="${results.numResults}" /></h4>
            
        </c:when>
        <c:otherwise>
        <table>
            <tr>
                <td></td>
                <td><img src="<c:out value="${results.bigImageUrl}"/>" /></td>
            </tr>

            <tr>
                <td><b>Title</b></td>
                <td><c:out value="${results.title}" /></td>
            </tr>
            <tr>
                <td><b>Authors</b></td>
                <td><c:out value="${results.authors}" /></td>
            </tr>            
            <tr>
                <td><b>Release Date</b></td>
                <td><c:out value="${results.releaseDate}" /></td>
            </tr>            
            <tr>
                <td><b>Manufacturer</b></td>
                <td><c:out value="${results.manufacturer}" /></td>
            </tr>            
            <tr>
                <td><b>List Price</b></td>
                <td><c:out value="${results.listPrice}" /></td>
            </tr>            
            <tr>
                <td><b>Amazon Price</b></td>
                <td><c:out value="${results.amazonPrice}" /></td>
            </tr>            
            
        </table>
        </c:otherwise>
        </c:choose>
        
    </netui-template:section>
</netui-template:template>

