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
    <netui-template:setAttribute value="Index" name="title"/>
    <netui-template:section name="bodySection">
    
        <p class="pagehead">
            &nbsp;Page Flow: Fedex Tracking JpfSample
        </p>
        
        <netui:form action="track">
        
        <table width="100%" cellpadding="0" class="tablebody" cellspacing="0">
            <tr>
                <td valign="top">
                    <table width="100%" class="tablebody">
                        <tr class="tablebody">
                            <td style="width: 30%; text-align: right;"><i><b>Transaction Id :</b></i></td>
                            <td>
                                <netui:textBox dataSource="{actionForm.transactionId}"/>
                            </td>
                        </tr>                
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr class="tablebody">
                            <td style="width: 30%; text-align: right;"><i><b>Account Number :</b></i></td>
                            <td>
                                <netui:textBox dataSource="{actionForm.accountNumber}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr class="tablebody">
                            <td style="width: 30%; text-align: right;"><i><b>Meter Number :</b></i></td>
                            <td>
                                <netui:textBox dataSource="{actionForm.meterNumber}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr class="tablebody">
                            <td style="width: 30%; text-align: right;"><i><b>Carrier Code :</b></i></td>
                            <td>
                                <netui:textBox dataSource="{actionForm.carrierCode}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr class="tablebody">
                            <td style="width: 30%; text-align: right;"><i><b>Package Identifier :</b></i></td>
                            <td>
                                <netui:textBox dataSource="{actionForm.packageIdentifier}" size="20"/>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr class="tablebody">
                            <td style="width: 30%; text-align: right;"><i><b>Package Identifier Type :</b></i></td>
                            <td>
                                <netui:textBox dataSource="{actionForm.packageIdentifierType}" size="40"/>
                            </td>
                        </tr>                                                                                                                 
                    </table>
                </td>                
            </tr>            
        </table>        
        <br/>&nbsp;
            <netui:button value="Track this package" type="submit"/>
        </netui:form>        
    </netui-template:section>
</netui-template:template>
