package com.pp.healthy.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledService {


//    @Scheduled(cron = "0/5 * * * * *")
//    public void scheduled(){
//        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
//    }
//    @Scheduled(fixedRate = 5000)
//    public void scheduled1() {
//        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
//    }
//    @Scheduled(fixedDelay = 5000)
//    public void scheduled2() {
//        log.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
//    }

    /**
     * 每天0点同步分类数据
     */
//    @Scheduled(cron = "0 0 0 * * *")
//    public void syncCate(){
//        log.warn("分类同步数据,没有数据同步");
//    }



}
