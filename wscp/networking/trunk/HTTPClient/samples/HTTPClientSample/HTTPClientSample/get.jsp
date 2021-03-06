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
<netui-template:template templatePage="/resources/jsp/template.jsp">
    <netui-template:setAttribute value="get" name="title"/>
    <netui-template:section name="bodySection">
    This sample uses the HttpControl to make a GET request of the Yahoo.com stock quote service.
        <br/>
        <netui:form action="get">
            <table class="tablebody">
                <tr>
                    <td valign="top">
                        Ticker Symbol:
                    </td>
                    <td class="prompt">                        
                        <input type="text" name="symbol" value="BEAS" />
                    </td>
                </tr>
            </table>
            <br/>&nbsp;
            <netui:button value="Submit" type="submit"/>
        </netui:form>
    </netui-template:section>
</netui-template:template>
