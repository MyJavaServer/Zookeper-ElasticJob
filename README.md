Zookeeper
     
     开源的分布式协调服务
     ZooKeeper 的设计目标是将那些复杂且容易出错的分布式一致性服务封装起来，构成一个高效可靠的原语集，并以一系列简单易用的接口提供给用户使用。
     功能：
     
     ZooKeeper 是一个典型的分布式数据一致性解决方案，分布式应用程序可以基于 ZooKeeper 实现诸如
            数据发布/订阅、负载均衡、命名服务、分布式协调/通知、集群管理、Master 选举、分布式锁和分布式队列等功能。
     
     ZooKeeper 一个最常用的使用场景就是用于担任服务 生产者和服务消费者的 "注册中心"。
            服务生产者将自己提供的服务注册到 ZooKeeper 中心，服务的消费者在进行服务调用的时候先到 ZooKeeper 中查找服务，获取到服务生产者的详细信息之后，再去调用服务生产者的内容与数据。
        
重要概念总结
   
     ZooKeeper 本身就是一个分布式程序（只要半数以上节点存活，ZooKeeper 就能正常服务）。
            为了保证高可用，最好是以集群形态来部署 ZooKeeper，这样只要集群中大部分机器是可用的（能够容忍一定的机器故障），那么 ZooKeeper 本身仍然是可用的。
    
     ZooKeeper 将数据保存在内存中，这也就保证了 高吞吐量和低延迟（但是内存限制了能够存储的容量不太大，此限制也是保持 Znode 中存储的数据量较小的进一步原因）。

     ZooKeeper 底层其实只提供了两个功能：①管理（存储、读取）用户程序提交的数据；②为用户程序提交数据节点监听服务。

     ZooKeeper 是高性能的。在“读”多于“写”的应用程序中尤其地高性能，因为“写”会导致所有的服务器间同步状态。（“读”多于“写”是协调服务的典型场景。）

要确认一个定时任务需要 一个cron表达式+jobDetail；

现在要让实现定时任务的协调，用zookeeper，
简单说就是需要3要素，zk对象+cron+jobDetail；





































框架组合使用场景：(大多是扮演服务的注册中心或管理中心的角色)
    
    Dubbo + zookeeper
    使用 ZooKeeper 作为 Dubbo 的注册中心(Dubbo 官方推荐使用 ZooKeeper 注册中心)。   
    
    Solr + zookeeper
    另外在搭建 Solr 集群的时候，我使用 ZooKeeper 作为 Solr 集群的管理工具。
        这时，ZooKeeper 主要提供下面几个功能：
        集群管理：容错、负载均衡。
        配置文件的集中管理。
        集群的入口。
