package com.developer.jayyin.zookeper_elasticjob.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *  zk注册中心配置
 *  创建一个bean，用来配置zk
 */
@Configuration
//开启条件
@ConditionalOnExpression("'${regCenter.serverList}'.length() > 0")
public class ZkRegistryCenterConfig {

    @Value("${regCenter.serverList}")
    private String serverList;
    @Value("${regCenter.namespace}")
    private String namespace;


    /**
     * 注册配置
     * @return
     */
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(){
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }




}
