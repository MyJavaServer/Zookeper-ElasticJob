package com.developer.jayyin.zookeper_elasticjob.config;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.stereotype.Component;


/**
 * job（任务）明细
 */
@Component
public class StockSimpleJob implements SimpleJob {


    /**
     * 任务执行的方法
     * @param shardingContext
     */
    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(String.format(
                "------Thread ID: %s, 任务总片数: %s, " +
                        "当前分片项: %s.当前参数: %s,"+
                        "当前任务名称: %s.当前任务参数: %s"
                ,
                Thread.currentThread().getId(),
                shardingContext.getShardingTotalCount(),
                shardingContext.getShardingItem(),
                shardingContext.getShardingParameter(),
                shardingContext.getJobName(),
                shardingContext.getJobParameter()
        ));

        // TODO: 2019/4/4 任务执行，通过任务参数区分实际任务。可以在此执行自己的自定义任务明细，处理实际业务逻辑；


    }
}
