<%-- 
   Copyright 2004 Salesforce.com

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

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>

<%System.out.println("loadlist3"); %>
        <netui-data:repeater dataSource="{pageFlow.accounts}">
            <netui-data:repeaterHeader>
                <table cellpadding="0" cellspacing="0" border="0">
                    <tr style="padding:0px 3px 0px 3px;">
                        <td><b>Name</b></td>                        
                        <td><b>Rating</b></td>
                        <td><b>Industry</b></td>
                        <td><b>City</b></td>
                        <td><b>State</b></td>
                    </tr>
            </netui-data:repeaterHeader>

            <netui-data:repeaterItem>
                <netui-data:getData resultId="index" value="{container.index}" />
                <tr style="background-color:#<%= ((Integer)pageContext.getAttribute("index")).intValue() % 2 == 0 ? "EEEEEE" : "DDDDDD"%> ">
                    <td style="padding:0px 3px 0px 3px;">
                        <netui:anchor action="viewAccount">
                            <netui:parameter name="accountid" value="{container.item.id}"/>                            
                            <netui:label value="{container.item.name}"/>
                        </netui:anchor>
                    </td>
                    <td style="padding:0px 3px 0px 3px;">
                        <netui:label value="{container.item.rating}"></netui:label>
                    </td>
                    <td style="padding:0px 3px 0px 3px;">
                        <netui:label value="{container.item.industry}"></netui:label>
                    </td>
                    <td style="padding:0px 3px 0px 3px;">
                        <netui:label value="{container.item.billingCity}"></netui:label>
                    </td>
                    <td style="padding:0px 3px 0px 3px;">
                        <netui:label value="{container.item.billingState}"></netui:label>
                    </td>
                </tr>
                </netui-data:repeaterItem>
            <netui-data:repeaterFooter>
                </table>
            </netui-data:repeaterFooter>
        </netui-data:repeater>
