package org.daikaixian.jspider.controller;

import org.daikaixian.jspider.spider.JSpider;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by kaishui on 15-9-19.
 */
@Controller
@RequestMapping("/")
public class UserController {

    //通过schedulerFactory获取一个调度器

    SchedulerFactory schedulerfactory = new StdSchedulerFactory();
    Scheduler scheduler = null;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(){
        //System.out.println("fuck");
        return "index";
    }

    @RequestMapping(value = "execute", method = RequestMethod.POST)
    @ResponseBody
    public void execute(@RequestParam(value = "username", defaultValue = "")String username,
                          @RequestParam(value = "pswd", defaultValue = "")String pswd,
                          @RequestParam(value = "email", defaultValue = "")String email) throws SchedulerException {

//        if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(pswd) && !StringUtils.isEmpty(email)){
//            JSpider.USER_NAME = username;
//            JSpider.PSWD = pswd;
//            JSpider.TARGET_EMAIL = email;
//        }

        scheduler=schedulerfactory.getScheduler();
//       创建jobDetail实例，绑定Job实现类
//       指明job的名称，所在组的名称，以及绑定job类
        HashMap map = new HashMap();
        map.put("USER_NAME", username);
        map.put("PSWD", pswd);
        map.put("TARGET_EMAIL", email);
        JobDataMap dataMap = new JobDataMap(map);

        JobDetail job= JobBuilder.newJob(JSpider.class).withIdentity("job1", "jgroup1").setJobData(dataMap).build();

//       定义调度触发规则
//      使用simpleTrigger规则
//        Trigger trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
//                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withRepeatCount(8))
//                        .startNow().build();
//      使用cornTrigger规则  每分钟执行一次
        Trigger trigger=TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ? *"))//设置调度时间规则,当前为每分钟一次.
                .startNow().build();

//       把作业和触发器注册到任务调度中
        scheduler.scheduleJob(job, trigger);

//       启动调度
        scheduler.start();

        //return "execute";
    }

    @RequestMapping(value = "stop", method = RequestMethod.GET)
    @ResponseBody
    public void stop() throws SchedulerException {

        scheduler.shutdown();
        System.out.println("stop");
//        return /"stop";
    }

}
