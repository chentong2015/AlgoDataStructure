package com.leetcode.basic_theory_introduction.hash_table.concurrent_hash_map;

import java.util.concurrent.ConcurrentHashMap;

// JDK 1.7版本：ConcurrentHashMap使用"分段锁"来解决冲突，实现高并发
public class BaseConcurrentHashMap {

    // 1. 背后存储的数据结构:
    //    Segment数组: segment对象(其中包含一个小型的HashMap, 也会挂链表) ...

    // 2. Segment对象
    //    对象中包含一个属性HashEntry<K,V>[] table; 内部的数组
    //    Segment<K,V>继承ReentrantLock, 通过调用.lock()方法来加锁  ===> 不同再新创建一个ReentrantLock
    //    static class Segment<K,V> extends ReentrantLock implements Serializable {
    //        private static final long serialVersionUID = 2249069246763182397L;
    //        final float loadFactor;
    //        Segment(float lf) { this.loadFactor = lf; }
    //    }

    // 3. 运作模式: 早期版本计算hashcode的算法
    //    <key,value>  ->  hashcode (h & segments.length-1) -> index Segment数组下标(找到在Segment数组的指定位置)
    //                                                      -> 调用Segment.lock()方法来加锁
    //                                                      -> hashcode (h & entry.length-1)
    //                                                      -> index 计算出segment内部Entry数组的index位置
    //                                                      -> 将生成Entry<K,V>对象添加数组对应的位置，或者挂链表

    // 4. 多线线程实现原理: 也用了CAS方法
    //    如果运算到不同的Segment Index时，使用不同的Segment对象来加锁，不会造成冲突(可以同时进行，提高性能)
    //    <key1, value> -> Segment Index -> .lock() -> Add Entry<key, value>
    //    <key2, value> -> Segment Index -> .lock() -> 同一时刻只有一个线程能够添加到内部的数组中
    public void testMain() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(16, 0.75f, 8);
        map.put("test", "123");
        System.out.println(map);
    }

    // 5. 构造方法源码
    //     new ConcurrentHashMap<>() -> this(initialCapacity, LOAD_FACTOR, 1);
    //           initialCapacity  初始的16表示总的内部的Entry<key,value>数组的容量    ===> 与HashMap保持一致
    //           LOAD_FACTOR      负载因子，到达总容量的多少时，需要进行内部Entry<>扩容  ===> 可不影响其他的Segment对象
    //           concurrencyLevel 最多支持多少个线程同时操作Segments数组(指定数组长度的定值，不会改变)
    //    约束条件: segment数组的长度必须是大于等于concurrencyLevel, 2^n数值 !!                       <- 因为都需要通过hash计算数组index下标位置
    //            segment数组每个位置Segment对象内部的Entry<k,v>数组的长度最小值2, 同时必须满足2^n数组 !! <- 根据初始构造出来cap的值判断
    // 在初始化的时候，构造Segments[]数组
    // 然后构造出Segments[0]位置的对象并添加，之后在put添加数组新位置过程中直接使用之前已经计算过的参数

    // 6. put()方法源码
    //    public V put(K key, V value) {
    //       int hash = hash(key);  使用key计算出hash值
    //       int segmentIndex = (hash >>> segmentShift) & segmentMask; 通过hash值计算出对应的index位置
    //       判断对应的位置是否有Segment的对象，如果没有则会创建s.ensureSegment(segmentIndex);
    //       s.put(key, hash, value, false); 在指定的Segment中添加Entry<key, value>对象
    //    }

    //    Segment<K,V> s.ensureSegment(segmentIndex) { 方法只需要返回一个Segment<K,V>对象即可
    //       // 过程中由两次取对象的判断，考虑在并发情况下，对象可能由别的线程所已经创建
    //       if null (can not get instance) ==> getObjectVolatile(ss,u) 多次if判断能够最大限度的减少要执行的代码, 提高性能 !!
    //           Segment<K,V> proto = segments[0];
    //           HashEntry<K,V> tab = (HashEntry<K,V>) new HashEntry[cap];
    //           it null (can not get instance)
    //                TODO: 解决线程并发的问题, CAS方法(指令, 性能比锁更加优化)保证只有一个线程能够成功设置 !!
    //                Segment<K,V> seg = new Segment<K,V>(lf, threshold, tab);
    //                while(seg ... null) 类似于自旋
    //                   ss数组的第u个位置为null的时候才会执行seg=s;成功
    //                   if(UNSAFE.compareAndSwapObject(ss,u,null,seg=s) break; 将新创建的Segment对象添加到Segments数组指定位置
    //    }

    //    s.put(key, hash, value, false) { 调用Segment对象的.put方法，在内部添加Entry<Key,Value>对象
    //       segment.lock 多个线程同时进来，首先需要加锁
    //             tryLock()  --> 非阻塞的，尝试去加锁，可能成功加锁，也可能加锁失败
    //                scanAndLockForPut(key, hash, value); 等锁失败时会执行
    //             lock()     --> 阻塞，直到加到锁之后才会执行完成，
    //                        --> 等效于while(!tryLock()) {}自旋  ==> 可以在等待锁的过程中执行一定的操作 !!
    //       ...类似于HashMap的做法添加
    //   }

    //   HashEntry<K,V> scanAndLockForPut(key, hash, value) { 阻塞加锁: 线程在等待锁的过程中，能够做些什么事情 ?
    //       最终一定会拿到索，在等待的过程中:
    //       1. 通过key计算出index，遍历指定Entry数组的index位置的值，或所挂的链表，或红黑树)，如果有相同的key，则修改这个Entry的value值
    //                            如何遍历 ?
    //       2. 如果没有找到相同的key，则可以先生成Entry<K,V>，以便之后直接使用
    //       while(!tryLock()) {
    //
    //       }
    //   }

}
