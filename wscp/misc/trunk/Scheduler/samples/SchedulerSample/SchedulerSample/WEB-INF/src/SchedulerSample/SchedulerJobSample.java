/*   Copyright 2004 BEA Systems, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */


package SchedulerSample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

public class SchedulerJobSample implements StatefulJob
{ 
    private static int executionNo ;
    
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        System.out.println("Time of execution : " + System.currentTimeMillis());
        synchronized(this){
            executionNo++;
        }
        
        HttpSession session = (HttpSession) jobExecutionContext.getJobDetail().getJobDataMap().get(SchedulerSampleConstants.SESSION);
        if(session == null){
            throw new RuntimeException("Sessiion object could not be found in the job, Job expects session object");
        }

        Collection sessionData = (Collection) session.getAttribute(SchedulerSampleConstants.SESSION_DATA);
        if(sessionData == null){
            sessionData = new ArrayList();
            session.setAttribute(SchedulerSampleConstants.SESSION_DATA,sessionData);
        }
        
        String contextMessage = (String)jobExecutionContext.getJobDetail().getJobDataMap().get(SchedulerSampleConstants.CONTEXT_MESSAGE);
        if(contextMessage == null){
            contextMessage = "";
        }        
        
        StringBuffer sessionEntry = new StringBuffer();
        sessionEntry.append("<b>" + "Job Executed - " + "</b>" + "<br>");
        sessionEntry.append("<i>" + "Execution No : " + "</i>" +"<b>"+ executionNo + "</b>"+" , ");
        sessionEntry.append("<i>" + "Date of Execution : " + "</i>" +"<b>"+new Date() +"</b>"+" , ");   
        sessionEntry.append("<i>" + "Message : " + "</i>" + "<b>"+contextMessage + "</b>");            
        System.out.println(sessionEntry.toString());        
        sessionData.add(sessionEntry.toString());        
    }
    
    public static void initializeJob(){
        executionNo = 0;
    }
} 
