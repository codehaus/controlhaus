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
    <netui-template:setAttribute value="instantMessage" name="title"/>
    <netui-template:section name="bodySection">
    
        <h2>Jabber Instant Messenger</h2>        
        
        Please provide the details below (or use default). 
        <br/>        
        <br/>
        <br/>
        
        <netui:form action="instantMessage">
            <i><B>Account Details</B></i> : Your Jabber Account details.<br/><br/>
            <table class="tablebody">                                
                <tr class="tablebody">
                    <td>Userid:</td>
                    <td>
                        <netui:textBox dataSource="{actionForm.userid}"/>
                    </td>
                </tr>
                <tr class="tablebody">
                    <td>Password:</td>
                    <td>
                        <netui:textBox dataSource="{actionForm.password}"/>
                    </td>
                </tr>                                
                <tr class="tablebody">
                    <td>ServerName:</td>
                    <td>
                        <netui:textBox dataSource="{actionForm.serverName}"/>
                    </td>
                </tr>                
            </table>
            
            <br/><br/><br/>
            
            <i><B>Reciever and Message Details</B></i> : Details regarding reciever of the message and the message itself.<br/><br/>
            
            <table class="tablebody">                
                <tr class="tablebody">
                    <td>ToUser:</td>
                    <td>
                        <netui:textBox dataSource="{actionForm.toUser}"/>
                    </td>
                </tr>
                <tr class="tablebody">
                    <td>Message:</td>
                    <td>
                        <netui:textBox dataSource="{actionForm.message}" size="40"/>
                    </td>
                </tr>
            </table>
            <br/>&nbsp;
            <netui:button value="Submit" type="submit"/>
        </netui:form>
    </netui-template:section>
</netui-template:template>
