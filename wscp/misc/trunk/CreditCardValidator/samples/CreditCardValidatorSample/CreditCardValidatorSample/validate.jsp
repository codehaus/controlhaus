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
    <netui-template:setAttribute value="validate" name="title"/>
    <netui-template:section name="bodySection">
        
        <h2>Credit Card Validator </h2>
        <br/>
        <br/>
        
        <netui:form action="validate">
            <table class="tablebody">
                <tr class="tablebody">
                    <td><i><b>Credit Card Number :</b></i></td>
                    <td>
                        <netui:textBox dataSource="{actionForm.number}"/>
                    </td>
                </tr>                
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr class="tablebody">
                    <td><i><b>Credit Card Expiry Date :</b></i></td>
                </tr>
                <tr>
                    <td>Expiry Day :</td>
                    <td class="prompt">                        
                        <netui:textBox dataSource="{actionForm.day}" maxlength="2" size="1"/>
                    </td>
                </tr>
                <tr>
                    <td>Expiry Month :</td>
                    <td class="prompt">                        
                        <netui:textBox dataSource="{actionForm.month}" maxlength="2" size="1"/>
                    </td>
                </tr>
                <tr>
                    <td>Expiry Year :</td>
                    <td class="prompt">                        
                        <netui:textBox dataSource="{actionForm.year}" maxlength="4" size="1"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td><i><b>Credit Card Type :</b></i></td>
                    <td class="prompt">                        
                        <netui:select dataSource="{actionForm.type}" optionsDataSource="{actionForm.cardTypes}"/>
                    </td>
                </tr>                
            </table>
            <br/>&nbsp;
            <netui:button value="Validate" type="submit"/>
        </netui:form>
    </netui-template:section>
</netui-template:template>
