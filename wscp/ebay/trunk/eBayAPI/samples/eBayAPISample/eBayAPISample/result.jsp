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
<%@ page import="eBayAPISample.EBayResponse"%>
<%@ page import="org.controlhaus.ebay.EBayXmlResponse"%>
<%@ page import="org.controlhaus.ebay.EBayDocument.EBay"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<netui-template:template templatePage="/resources/jsp/template.jsp">
    <netui-template:setAttribute value="Index" name="title"/>
    <netui-template:section name="bodySection">
        <%
            EBayXmlResponse xmlResponse = (EBayXmlResponse)request.getAttribute("eBayResponse");
            EBayResponse eBayResponse = new EBayResponse(xmlResponse);            
        %>
    
        <p class="pagehead">
            <b> EBay Response </b>
        </p>
        <br>
        
        <table class="tablebody">
            <tr class="tablebody">
                <td>
                    <b>EBay Time :</b>                
                </td>
                <td>
                    <%=eBayResponse.getEBayTime()%>                    
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    &nbsp;
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <!---Incase of erros -->
            <%if(eBayResponse.responseHasErrors()){%>
            
            <tr class="tablebody">
                <td>
                    <b>Some Error has occured, please find more information below.</b>
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    &nbsp;
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Error Code :</i>
                </td>
                <td>
                    <%=eBayResponse.getErrorCode()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Error Severity Code :</i>
                </td>
                <td>
                    <%=eBayResponse.getErrorSeverityCode()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Error Severity :</i>
                </td>
                <td>
                    <%=eBayResponse.getErrorSeverity()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Error Short Message :</i>
                </td>
                <td>
                    <%=eBayResponse.getErrorShortMessage()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Error Long Message :</i>
                </td>
                <td>
                    <%=eBayResponse.getErrorLongMessage()%>
                </td>
            </tr>
            <%}else{%>
            
            <!-- Success : Item Details -->
            <tr class="tablebody">
                <td>
                    <b>Item Info :</b>
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Item Id :</i>
                </td>
                <td>
                    <%=eBayResponse.getItemId()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Title :</i>
                </td>
                <td>
                    <%=eBayResponse.getTitle()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Picture URL :</i>
                </td>
                <td>
                    <img src="<%=eBayResponse.getPictureURL()%>">
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    &nbsp;
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <b>Category Info :</b>
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Category Id :</i>
                </td>
                <td>
                    <%=eBayResponse.getCategoryId()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Category Full Name :</i>
                </td>
                <td>
                    <%=eBayResponse.getCategoryFullName()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    &nbsp;
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <b>Bid Info :</b>
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Bid Count :</i>
                </td>
                <td>
                    <%=eBayResponse.getBidCount()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Bid Increment :</i>
                </td>
                <td>
                    <%=eBayResponse.getBidIncrement()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Buy it now price :</i>
                </td>
                <td>
                    <%=eBayResponse.getBuyItNowPrice()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    &nbsp;
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <b>Pricing Info :</b>
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Currency Id :</i>
                </td>
                <td>
                    <%=eBayResponse.getCurrenyId()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Current Price :</i>
                </td>
                <td>
                    <%=eBayResponse.getCurrentPrice()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>End Time :</i>
                </td>
                <td>
                    <%=eBayResponse.getEndTime()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    &nbsp;
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <b>Seller Info :</b>
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Seller Level :</i>
                </td>
                <td>
                    <%=eBayResponse.getSellerLevel()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>User Id :</i>
                </td>
                <td>
                    <%=eBayResponse.getUserid()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    &nbsp;
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <b>Other Info :</b>
                </td>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Quantity :</i>
                </td>
                <td>
                    <%=eBayResponse.getQuantity()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Start Price :</i>
                </td>
                <td>
                    <%=eBayResponse.getStartPrice()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Start Time :</i>
                </td>
                <td>
                    <%=eBayResponse.getStartTime()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Time Left (Days) :</i>
                </td>
                <td>
                    <%=eBayResponse.getDaysLeft()%>
                </td>
            </tr>
            <tr class="tablebody">
                <td>
                    <i>Time Left (Hours) :</i>
                </td>
                <td>
                    <%=eBayResponse.getHoursLeft()%>
                </td>
            </tr>
            
            <%}%>           
            
        </table>
        <br/>        
    </netui-template:section>
</netui-template:template>
