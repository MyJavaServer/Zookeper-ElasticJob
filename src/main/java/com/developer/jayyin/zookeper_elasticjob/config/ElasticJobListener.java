package com.developer.jayyin.zookeper_elasticjob.config;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;
import org.springframework.stereotype.Component;

/**
 * 自定义 任务执行过程 监听接口
 */
public class ElasticJobListener extends AbstractDistributeOnceElasticJobListener {



    public ElasticJobListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
        super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
    }

    @Override
    public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
        System.out.println(shardingContexts.getJobName());
        System.out.println(shardingContexts.getTaskId());
        System.out.println(shardingContexts.getJobParameter());
        System.out.println("任务开始执行");
    }

    @Override
    public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
        System.out.println(shardingContexts.getJobName());
        System.out.println(shardingContexts.getTaskId());
        System.out.println(shardingContexts.getJobParameter());
        System.out.println("任务执行完成");
//        JobTask jobTask = taskRepository.findOne(Long.valueOf(shardingContexts.getJobParameter()));
//        jobTask.setStatus(1);
//        taskRepository.save(jobTask);
    }
}