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
<%@ page import="SchedulerSample.SchedulerSampleConstants"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<netui-template:template templatePage="/resources/jsp/template.jsp">
    <netui-template:setAttribute value="schedule" name="title"/>
    <netui-template:section name="bodySection">    
    
    <h2>Scheduler</h2>
    <br/>   
    
    This sample application contains a simple job, which adds a string containing (execution no, date of execution and message) into the session.    
    To visually understand the scheduling, the application provides a simple jsp to read the session contents and displays on the screen after every 2000 ms.    
    <br/>
    <br/>                
    
    
    <netui:form action="schedule">
        <table class="tablebody">
            <tr>
                <td><i><b>Message for Job (ContextMap):</b></i></td>
                <td>
                <netui:textBox dataSource="{actionForm.contextMessage}"/>
                </td>
            </tr>
            <tr>
                <td>Context for the Job</td>
                <td>&nbsp;</td>                
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr class="tablebody">
                <td><i><b>Start Time (ms since January 1, 1970, 00:00:00 GMT) :</b></i></td>
                <td>
                Year : <netui:textBox dataSource="{actionForm.startYear}" maxlength="4" size="1"/>
                Month : <netui:textBox dataSource="{actionForm.startMonth}" maxlength="2" size="1"/>
                Day : <netui:textBox dataSource="{actionForm.startDay}" maxlength="2" size="1"/>
                Hour : <netui:textBox dataSource="{actionForm.startHour}" maxlength="2" size="1"/>
                Minute : <netui:textBox dataSource="{actionForm.startMin}" maxlength="2" size="1"/>
                </td>
            </tr>
            <tr>
                <td>Start time of the Job (in ms)</td>                
                <td>&nbsp;</td>                
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr class="tablebody">
                <td><i><b>End Time (ms since January 1, 1970, 00:00:00 GMT) :</b></i></td>                
                <td>
                Year : <netui:textBox dataSource="{actionForm.endYear}" maxlength="4" size="1"/>
                Month : <netui:textBox dataSource="{actionForm.endMonth}" maxlength="2" size="1"/>
                Day : <netui:textBox dataSource="{actionForm.endDay}" maxlength="2" size="1"/>
                Hour : <netui:textBox dataSource="{actionForm.endHour}" maxlength="2" size="1"/>
                Minute : <netui:textBox dataSource="{actionForm.endMin}" maxlength="2" size="1"/>
                </td>
            </tr>
            <tr>
                <td>End time of the Job (in ms) (Not Mandatory). Valid value overrides 'repeatCount'.</td>                
                <td></td>                
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr class="tablebody">
                <td><i><b>Repeat Count:</b></i></td>
                <td>
                <netui:textBox dataSource="{actionForm.repeatCount}"/>
                </td>
            </tr>
            <tr>
                <td>No of executions of the Job, if endTime is not provided.</td>                
                <td>&nbsp;</td>                
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr class="tablebody">
                <td><i><b>Repeat Interval (in ms):</b></i></td>
                <td>
                <netui:textBox dataSource="{actionForm.repeatInterval}"/>
                </td>
            </tr>
            <tr>
                <td>Time interval between execution of Job</td>                
                <td>&nbsp;</td>                
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>            
        </table>
        <br/>
        &nbsp;
        <netui:button value="Schedule Job" type="submit"/>
    </netui:form>
    </netui-template:section>
</netui-template:template>
