package com.jcfc.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//轮询，但每个机器被调用5次
public class FiveTimeRoundRobinRule extends AbstractLoadBalancerRule {

    //总共被调用的次数，当total=5，指针向下走
    private volatile Integer total = 0;
    //当前提供服务的机器指针
    private volatile Integer currentServerIndex = 0;;

    private static Logger log = LoggerFactory.getLogger(RoundRobinRule.class);

    public FiveTimeRoundRobinRule() {
    }

    public FiveTimeRoundRobinRule(ILoadBalancer lb) {
        this();
        setLoadBalancer(lb);
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            int nextServerIndex = incrementAndGetModulo(serverCount);
            server = allServers.get(nextServerIndex);

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
    }

    /**
         //获取总次数预期值
         int currentTotal = total.get();
         //获取指针预期值(即当前线程现在的值)
         int currentServer = currentServerIndex.get();


         if (currentTotal < 5) {
         int nextTotal = currentTotal + 1;
         if (total.compareAndSet(currentTotal, nextTotal))
         return currentServer;
         } else {
         int nextTotal = 0;
         int nextServer = (currentServer + 1) % modulo;
         if (currentServerIndex.compareAndSet(currentServer, nextServer))
         return nextServer;
         }
     */
    //CAS 多线程安全更新
    private synchronized int incrementAndGetModulo(int modulo) {
        if (total < 5) {
            total++;
        } else {
            total = 0;
            currentServerIndex ++;
        }
        return currentServerIndex % modulo;
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
