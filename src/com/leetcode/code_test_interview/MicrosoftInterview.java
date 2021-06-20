package com.leetcode.code_test_interview;

import com.leetcode.top_interview_questions.base.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 和平时工作一致，明确需求
 * 白板写代码，如何思考和解决问题
 * 1. 面试的问题不一定有标准答案，有的甚至没有正确答案，把面试官当做客户 !!
 * 2. 沟通好问题是什么，问题的边界，假设条件，边缘场景，异常场景，技术架构、如何测试、技术限制等
 * 3. 动手答题前，请留两分钟思考，抽象算法和逻辑
 * 4. 如果题目特殊，可以请求一定的提示，思考后解决
 * 5. Ask the right question，make decision在多种解法中找到最优化的解法 !!
 * 6. 如果测试的题目需要写的代码量过大，则考虑换一种思路，寻找最优解 !!
 */
public class MicrosoftInterview {

    // Microsoft 实战题目
    // 1. https://codeshare.io/ 共享代码界面
    // 2. Teams 视频面试, 共享桌面，运行测试
    // http://microsoft-hire.me/pages/problems/leetcode.html
    // https://www.careercup.com/page?pid=microsoft-interview-questions
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.getVal());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        return results;
    }
}
