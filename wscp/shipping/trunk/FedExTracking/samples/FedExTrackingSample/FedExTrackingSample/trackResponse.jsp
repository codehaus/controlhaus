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
<%@ page import="org.controlhaus.fedex.impl.FedexTrackReplyBean"%>
<%@ page import="org.controlhaus.schema.fedex.FDXTrackReplyDocument"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<netui-template:template templatePage="/resources/jsp/template.jsp">
    <netui-template:setAttribute value="track" name="title"/>
    <netui-template:section name="bodySection">
    
        <%
            FDXTrackReplyDocument.FDXTrackReply fdxTrackReply = (FDXTrackReplyDocument.FDXTrackReply)request.getAttribute("FEDEX_TRACK_REPLY");
            org.controlhaus.schema.fedex.Error error = fdxTrackReply.getError();
            if(error != null){
                out.println("<h3>Some Error has occurred.</h3>");
                out.println("<br>");                
                out.println("<b>Error Code :</b>"+error.getCode());
                out.println("<br>");
                out.println("<b>Error Message :</b>"+error.getMessage());                
            }else{
            
                FedexTrackReplyBean fedexTrackReplyBean = new  FedexTrackReplyBean(fdxTrackReply);
        %>
        
        <netui:form action="track">
        
        <table style="text-align: left; background-color: rgb(255, 255, 255); width: 80%;" cellspacing="4" cellpadding="6">
            <tbody>
            <tr>
            <td style="vertical-align: top; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Customer Transaction Identifier : </span><br>
            </td>
            <td style="vertical-align: top;"><%=fedexTrackReplyBean.getCustomerTransactionIdentifier()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top;"><br>
            </td>
            <td style="vertical-align: top;"><br>
            </td>
            </tr>
            
            <%
                for (Iterator i = fedexTrackReplyBean.getTrackProfileBeans().iterator() ; i.hasNext() ; ){
                    FedexTrackReplyBean.TrackProfileBean trackProfileBean = (FedexTrackReplyBean.TrackProfileBean) i.next();
            %>
            
            <tr>
            <td
            style="vertical-align: top; font-weight: bold; font-style: italic; width: 30%; text-align: right;">Tracking Number :<br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getTrackingNumber()%><br>
            </td>
            </tr>
            <tr>
            <td
            style="vertical-align: top; font-style: italic; font-weight: bold; width: 30%; text-align: right;">Carrier Code :<br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getCarrierCode()%><br>
            </td>
            </tr>
            <tr>
            <td
            style="vertical-align: top; background-color: rgb(255, 255, 255); width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Other Identifier Count :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getOtherIdentifierCount()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Ship Date :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getShipDate()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Destination Address :</span><br>
            </td>
            <td style="vertical-align: top;">Destination address details below
            <table style="text-align: left; width: 100%;" cellspacing="5"
            cellpadding="10">
            <tbody>
            <tr align="left">
            <td
            style="vertical-align: top; width: 25%; text-align: left; font-style: italic;"><span
            style="font-weight: bold;">City :</span><br>
            </td>
            <td style="vertical-align: top; width: 80%;"><%=trackProfileBean.getDestinationCity()%><br>
            </td>
            </tr>
            <tr align="left">
            <td
            style="vertical-align: top; width: 25%; text-align: left; font-style: italic;"><span
            style="font-weight: bold;">State or Province Code: </span><br>
            </td>
            <td style="vertical-align: top; width: 80%;"><%=trackProfileBean.getDestinationStateOrProvinceCode()%><br>
            </td>
            </tr>
            <tr align="left">
            <td
            style="vertical-align: top; width: 25%; text-align: left; font-style: italic;"><span
            style="font-weight: bold;">Postal Code : </span> </td>
            <td style="vertical-align: top; width: 80%;"><%=trackProfileBean.getDestinationPostalCode()%><br>
            </td>
            </tr>
            <tr>
            <td
            style="vertical-align: top; width: 25%; text-align: left; font-style: italic;"><span
            style="font-weight: bold;">Country Code :</span><br>
            </td>
            <td
            style="vertical-align: top; text-align: left; width: 80%;"><%=trackProfileBean.getDestinationCountryCode()%><br>
            </td>
            </tr>
            </tbody>
            </table>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Delivered Date :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getDeliveredDate()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Delivered Time :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getDeliveredTime()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Signed For By :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getSignedForBy()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Delivered Location Code
            :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getDeliveredLocationCode()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Delivered Location
            Description :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getDeliveredLocationDescription()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Service :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getService()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Weight :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getWeight()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Weight Units :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getWeightUnits()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Packaging Description :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getPackagingDescription()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Package Sequence Number
            :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getPackageSequenceNumber()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Package Count :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getPackageCount()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">FedEx URL :</span><br>
            </td>
            <td style="vertical-align: top;"><a href="<%=trackProfileBean.getFedExURL()%>"><%=trackProfileBean.getFedExURL()%></a><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Scan Count :</span><br>
            </td>
            <td style="vertical-align: top;"><%=trackProfileBean.getScanCount()%><br>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top; width: 30%; text-align: right;"><span
            style="font-weight: bold; font-style: italic;">Scan :</span><br>
            </td>
            <td style="vertical-align: top;">
            <div style="text-align: center;"> </div>
            <table cellpadding="2" cellspacing="2" border="1"
            style="text-align: left; width: 100%;">
            <tbody>
            <tr>
            <td
            style="vertical-align: top; text-align: center; width: 14%;">Date<br>
            </td>
            <td
            style="vertical-align: top; text-align: center; width: 14%;">Time<br>
            </td>
            <td
            style="vertical-align: top; text-align: center; width: 14%;">Scan Type<br>
            </td>
            <td
            style="vertical-align: top; text-align: center; width: 14%;">Scan
            Description<br>
            </td>
            <td
            style="vertical-align: top; text-align: center; width: 14%;">City<br>
            </td>
            <td
            style="vertical-align: top; text-align: center; width: 14%;">State or
            Province Code<br>
            </td>
            <td
            style="vertical-align: top; text-align: center; width: 15%;">Country
            Code<br>
            </td>
            </tr>
            <%
                for (Iterator it = trackProfileBean.getScanBeans().iterator() ; it.hasNext() ; ){
                    FedexTrackReplyBean.ScanBean scanBean = (FedexTrackReplyBean.ScanBean) it.next();
            %>
            
            <tr>
            <td style="vertical-align: top; width: 14%;"><%=scanBean.getScanDate()%><br>
            </td>
            <td style="vertical-align: top; width: 14%;"><%=scanBean.getScanTime()%><br>
            </td>
            <td style="vertical-align: top; width: 14%;"><%=scanBean.getScanType()%><br>
            </td>
            <td style="vertical-align: top; width: 14%;"><%=scanBean.getScanDescription()%><br>
            </td>
            <td style="vertical-align: top; width: 14%;"><%=scanBean.getScanCity()%><br>
            </td>
            <td style="vertical-align: top; width: 14%;"><%=scanBean.getScanStateOrProvinceCode()%><br>
            </td>
            <td style="vertical-align: top; width: 15%;"><%=scanBean.getScanCountryCode()%><br>
            </td>
            </tr>
            
            <%}%>          
            
            </tbody>
            </table>
            <br>
            </td>
            </tr>
                        
            <%}%>         
            
            </tbody>
            </table>
            
            </netui:form>
            
            <%}%>

            
            <br>
            <br>        
        
        
    </netui-template:section>
</netui-template:template>
