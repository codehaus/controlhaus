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
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<netui-template:template templatePage="/resources/jsp/template.jsp">
    <netui-template:setAttribute value="Google Search Results" name="title"/>
    <netui-template:section name="bodySection">
        <p><netui:anchor action="googleSearchLink">Search Again</netui:anchor></p>
        
        <p>Results <netui:label value="{request.results.startIndex}"/> to <netui:label value="{request.results.endIndex}"/> of <netui:label value="{request.results.estimatedTotalResultsCount}"/></p>
        
        <netui-data:repeater dataSource="{request.results.resultElements}">
            <netui-data:repeaterHeader>
                <table border="1">
                    <tr>
                        <td><b>Title</b></td>
                        <td><b>Snippet</b></td> 
                        <td><b>URL</b></td> 
                    </tr>
            </netui-data:repeaterHeader>
            
            <netui-data:repeaterItem>
                    <tr>
                        <td>
                            <netui:content value="{container.item.title}" />
                        </td>
                        <td>
                            <netui:content value="{container.item.snippet}" />
                        </td>
                        <td>
                            <a href="<netui:label value="{container.item.URL}" />"><netui:label value="{container.item.URL}" /></a>
                        </td>

                    </tr>
            </netui-data:repeaterItem>


            <netui:label value="{container.item.title}"/>
            <netui-data:repeaterFooter>
                </table>
            </netui-data:repeaterFooter> 

        </netui-data:repeater>
    </netui-template:section>
</netui-template:template>
