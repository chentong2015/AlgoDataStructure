package com.base_system_design.leader_follower;

// 多线程场景：
// 多个消费者线程从阻塞队列中取出任务，只有一个线程能够成为leader成功出队
// 其他的消费者线程被阻塞，作为follower，之后其中一个消费者可能被选成新的leader
public class LeaderFollower {

}
