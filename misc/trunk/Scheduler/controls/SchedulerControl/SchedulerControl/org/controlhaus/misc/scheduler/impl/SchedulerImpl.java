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


package org.controlhaus.misc.scheduler.impl;

import org.quartz.*;
import org.quartz.Scheduler;

import java.util.*;

public class SchedulerImpl implements org.controlhaus.misc.scheduler.impl.Scheduler {
    public void schedule(Date startTime, Date endTime, int repeatCount, long repeatInterval, Class jobClass, Map contextMap) throws SchedulerException {
        Contract.enforce(startTime != null, "startTime");
        Contract.enforce(jobClass != null, "jobClass");

        SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        String jobName = jobClass.toString() + System.currentTimeMillis();

        JobDetail jobDetail = new JobDetail(jobName,scheduler.DEFAULT_GROUP,jobClass);

        if (contextMap != null) {
            Set keys = contextMap.keySet();
            for (Iterator i = keys.iterator(); i.hasNext();) {
                Object key = i.next();
                Object value = contextMap.get(key);
                jobDetail.getJobDataMap().put(key, value);
            }
        }

        long startTimeMs = startTime.getTime();
        long currentTimeMs = System.currentTimeMillis();

        if(startTimeMs < currentTimeMs){
            System.out.println("Setting start time as current time");
            startTime = new Date();
        }

        if (endTime != null) {
            long endTimeMs = endTime.getTime();
            if(endTimeMs < startTimeMs){
                System.out.println("Setting end time null");
                endTime = null;
            }

            if(endTime != null){
                System.out.println("Setting repeatCount to be indefinate, as endTime is valid");
                repeatCount = SimpleTrigger.REPEAT_INDEFINITELY;
            }
        }


        String triggerName = jobName;
        SimpleTrigger trigger = new SimpleTrigger(triggerName,scheduler.DEFAULT_GROUP,startTime,endTime,repeatCount,repeatInterval);
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
