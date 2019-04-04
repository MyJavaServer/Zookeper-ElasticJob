package com.developer.jayyin.zookeper_elasticjob.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 任务属性
 * 连接三个部分（job任务、zk组件、cron定时配置）
 */
@Configuration
public class ZkStockJobConfig {

    @Value("${stockJob.cron}")  //定时表达式
    private String cron;

    @Value("${stockJob.shardingTotalCount}")
    private int shardingTotalCount; //总分片数

    @Value("${stockJob.shardingItemParameters}")
    private String shardingItemParameters;  //任务分片携带的参数

    @Value("${stockJob.jobDescription}")
    private String jobDescription;

    @Value("${stockJob.jobParameter}")
    private String jobParameter;

    @Autowired
    private StockSimpleJob mStockSimpleJob;     //任务job

    @Autowired
    private ZookeeperRegistryCenter mZkRegistryCenter;

    private ElasticJobListener mJobListener = new ElasticJobListener(100, 100);

//    public ZkStockJobConfig() {
//
//    }

//    @Bean
//    public SimpleJob stockJob() {
//        return new StockSimpleJob();
//    }

    /**
     * 加入任务并 通过 "init()" 方法启动任务，可以在监控（listener）中查看任务的执行状态， 在执行方法中去处理实际业务；
     *
     *
     * @return
     */
    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler() {

        return new SpringJobScheduler(
                mStockSimpleJob,
                mZkRegistryCenter,
                getLiteJobConfiguration(StockSimpleJob.class, cron, shardingTotalCount, shardingItemParameters, jobParameter),
                mJobListener);
    }


    /**
     * 任务配置
     *
     * @param jobClass
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     * @return
     */
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters,
                                                         final String jobParameter) {
        JobCoreConfiguration.Builder confBuilder = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount);
        JobCoreConfiguration coreConf = confBuilder
                .shardingItemParameters(shardingItemParameters)
                .jobParameter(jobParameter)
                .build();

        SimpleJobConfiguration jobConfiguration = new SimpleJobConfiguration(coreConf, jobClass.getCanonicalName());

        return LiteJobConfiguration
                .newBuilder(jobConfiguration)
                .overwrite(true)
                .build();
    }


}
