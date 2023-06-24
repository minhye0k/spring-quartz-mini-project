package mini.project.springquartz.service;

import lombok.RequiredArgsConstructor;
import mini.project.springquartz.handler.QuartzHandler;
import org.quartz.JobDataMap;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuartzService {
    private final QuartzHandler quartzHandler;

    public void postQuartzJob(Long seq) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("seq", seq);
        quartzHandler.schedulingJobWithCronTrigger(jobDataMap, seq);
        quartzHandler.schedulingJobWithSimpleTrigger(jobDataMap, seq);
    }
}
