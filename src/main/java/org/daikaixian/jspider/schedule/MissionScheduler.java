package org.daikaixian.jspider.schedule;



import org.daikaixian.jspider.spider.JSpider;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * Created by kaishui on 15-8-30.
 */
public class MissionScheduler {

    public static void main(String[] args) {

        //通过schedulerFactory获取一个调度器
        SchedulerFactory schedulerfactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try{

            scheduler=schedulerfactory.getScheduler();
//       创建jobDetail实例，绑定Job实现类
//       指明job的名称，所在组的名称，以及绑定job类
            JobDetail job= JobBuilder.newJob(JSpider.class).withIdentity("job1", "jgroup1").build();

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

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
