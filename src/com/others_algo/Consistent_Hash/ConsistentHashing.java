package com.others_algo.Consistent_Hash;

// 一致性哈希算法: 负载均衡方式
// 业务场景：使用n台缓存服务器时，一种常用的负载均衡方式是，对资源o的请求使用hash(o)= o mod n来映射到某一台缓存服务器
// 问题需求：当增加或减少一台缓存服务器时，这种方式可能会改变所有资源对应的hash值，也就是所有的缓存都失效了
//
// 解决方案：增加一台缓存服务器时，新的服务器尽量分担存储其他所有服务器的缓存资源
//           减少一台缓存服务器时，其他所有服务器也可以尽量分担存储它的缓存资源
// 具体实现: 将每个对象映射到圆环边上的一个点，系统再将可用的节点机器映射到圆环的不同位置
//           用一致哈希算法计算得到对象对应圆环边上位置，沿着圆环边上查找直到遇到某个节点机器(对象保存位置)
//           当增删结点机器时，机器周边的对象做重新的转移和适配到相应的机器上，不对其他机器区间造成影响
public class ConsistentHashing {
	
}
