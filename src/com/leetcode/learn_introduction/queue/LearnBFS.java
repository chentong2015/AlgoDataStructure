package com.leetcode.learn_introduction.queue;

public class LearnBFS {

    // Open the Lock
    // 4 circular wheels. Each wheel has 10 slots (0-9)(9-0) 数字循环，在相邻的两个数字可以自由移动
    // The lock initially starts at '0000', can not reach "deadends"
    // Given a target representing the value of the wheels that will unlock the lock,
    // return the minimum total number of turns, -1 if it's impossible

    //       "8788"    "8878"
    // "7888"      8888      "9888"
    //      "8887"        "8889"
    //         "8988"  "8898"
    // 没有通路可以到达8888

    //      0101   0102
    //     1212      2002
    //    0201        '0202'
    // "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"

    // 0001  0010  0100  1000
    //          0000           -> 每一个结点可以延展开8个位置，每一个位置又能恢复成原来的位置，造成循环 !!
    // 0009  0090  0900  9000  -> 每变化一次，作为是一层的展开，记作一步统计 !!
    public int openLock(String[] deadends, String target) {
        // 测试理解：1. 逆向推导，从目标的值恢复成0000，避开死锁的情况下最快需要几步
        //            使用BFS算法，将该字符移动一步可以达到的相关位置都入队列，然后从第二层移动到第三层，最后判断结果是否能够到0000
        

        return 0;
    }

}
