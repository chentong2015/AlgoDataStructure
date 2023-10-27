package data_structure.dfs_bfs_traverse.graph;

import java.util.*;

// TODO: 标准图问题的解决算法
// 1. Depth First Search 深度优先遍历，能够在满足前后约束条件下，遍历所有的节点
// 2. Node InDegree 根据到结点的指向数目，依次从低到高，遍历节点之后更新相关节点的计数
public class LearnGraph {

    // Course Schedule II
    // numCourses要上课的总数, prerequisites[i] = [ai, bi] 约束关系, bi必须先于ai
    // Return the (any) ordering of courses you should take to finish all courses, return empty if it's impossible
    // numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]] -> [0,2,1,3]
    // numCourses = 2, prerequisites = [[0,1],[1,0]]             -> 无解循环
    // numCourses = 3, prerequisites = []                        -> [2, 1, 0] 直接输出没有约束的课程
    // numCourses = 3, prerequisites = [[1,0]]                   -> [2, 0, 1] 先输出没有约束的课程，后输出有约束的

    private static final int WHITE = 1;
    private static final int GRAY = 2;
    private static final int BLACK = 3;

    private boolean isPossible;
    private Map<Integer, Integer> color;
    private Map<Integer, List<Integer>> adjList;
    private List<Integer> topologicalOrder;

    private void init(int numCourses) {
        this.isPossible = true;
        this.color = new HashMap<>();
        this.adjList = new HashMap<>();
        this.topologicalOrder = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            this.color.put(i, WHITE);
        }
    }

    // 递归从一个顶点遍历它的所有的相邻的顶点，直到顶点没有后续的顶点
    // 如果在遍历的过程中，出现Cycle循环的路径，则无法形成有效的通路 !!
    private void dfs(int node) {
        if (!isPossible) return;
        color.put(node, GRAY);
        for (Integer neighbor : adjList.getOrDefault(node, new ArrayList<>())) {
            if (color.get(neighbor) == WHITE) {
                dfs(neighbor);
            } else if (color.get(neighbor) == GRAY) {
                isPossible = false;
            }
        }
        color.put(node, BLACK);     // 将最终找的顶点标记成黑色
        topologicalOrder.add(node); // 将顺序依次添加到list拓扑列表，最后反向取出
    }

    // Time Complexity: O(V+E) where V represents the number of vertices and E represents the number of edges
    // Space Complexity: O(V+E) 其中O(V)用于存储顶点, O(E)是由于dfs()递归造成栈空间的额外开销的空间复杂度 !!
    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        init(numCourses);
        for (int i = 0; i < prerequisites.length; i++) {
            int src = prerequisites[i][1];
            int dest = prerequisites[i][0];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<>());
            lst.add(dest);
            adjList.put(src, lst);
        }
        for (int i = 0; i < numCourses; i++) {
            if (color.get(i) == WHITE) {
                dfs(i);
            }
        }
        if (isPossible) {
            int[] order = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                order[i] = topologicalOrder.get(numCourses - 1 - i);
            }
            return order;
        }
        return new int[0];
    }

    // 时间复杂度：O(V+E)
    // 空间复杂度：O(V+E)
    public int[] findOrderNodeInDegree(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] inDegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int src = prerequisites[i][1];
            int dest = prerequisites[i][0];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<>());
            lst.add(dest);
            adjList.put(src, lst);
            inDegree[dest] += 1; // 统计的是到达dest目的顶点的edges数目
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);   // 顶点in-degree数为0的先入队列，优先读取
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            int node = queue.remove();
            topologicalOrder[index++] = node;
            if (adjList.containsKey(node)) {
                for (Integer neighbor : adjList.get(node)) {
                    inDegree[neighbor]--;           // 修改改顶点的所有临顶点的in-degree数目
                    if (inDegree[neighbor] == 0) {  // 如果顶点的in-degree数为0，就是可以加入队列，优先读取的
                        queue.add(neighbor);
                    }
                }
            }
        }
        if (index == numCourses) { // 判断是否填满了所有的课程
            return topologicalOrder;
        }
        return new int[0];
    }
}
