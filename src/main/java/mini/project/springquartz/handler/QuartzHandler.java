package mini.project.springquartz.handler;

import lombok.RequiredArgsConstructor;
import mini.project.springquartz.jobs.FirstJob;
import mini.project.springquartz.jobs.SecondJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class QuartzHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Scheduler scheduler;

    public void schedulingJobWithCronTrigger(JobDataMap jobDataMap, Long id) {

        JobDetail jobDetail = JobBuilder.newJob(FirstJob.class)
                .withIdentity("cron-" + id, "cron-job")
                .setJobData(jobDataMap)
                .build();

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("cron-" + id, "cron-job")
                .withSchedule(CronScheduleBuilder.cronSchedule("5 * * * * ?"))
                .build();
        try {
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("error", e);
        }

    }

    public void schedulingJobWithSimpleTrigger(JobDataMap jobDataMap, Long id) {
        JobDetail jobDetail = JobBuilder.newJob(SecondJob.class)
                .withIdentity("simple-" + id, "simple-job")
                .setJobData(jobDataMap)
                .build();

        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("simple-" + id, "simple-job")
                .startAt(new Date())
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(5, 10))
                .build();

        try {
            scheduler.scheduleJob(jobDetail, simpleTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("error", e);
        }
    }

}
