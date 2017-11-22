/*
package com.zhangli.ddshop.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class JedisTest {
    @Test
    public void testJedis01(){
        Jedis jedis = new Jedis("192.168.14.138", 6379);
        jedis.set("name", "ddm");
        System.out.println(jedis.get("name"));
        jedis.close();
    }

    @Test
    public void testJedis03(){
        //创建存放节点的集合
        Set<HostAndPort> clusterSet = new HashSet<>();
        clusterSet.add(new HostAndPort("192.168.14.138", 9001));
        clusterSet.add(new HostAndPort("192.168.14.138", 9002));
        clusterSet.add(new HostAndPort("192.168.14.138", 9003));
        clusterSet.add(new HostAndPort("192.168.14.138", 9004));
        clusterSet.add(new HostAndPort("192.168.14.138", 9005));
        clusterSet.add(new HostAndPort("192.168.14.138", 9006));
        //创建jedis集群对象进行使用
        JedisCluster jedisCluster = new JedisCluster(clusterSet);
        jedisCluster.set("name", "林志玲");
        System.out.println(jedisCluster.get("name"));
        jedisCluster.close();
    }

}
*/
