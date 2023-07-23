package algorithms.others_algo.Kruskal;

import java.util.HashSet;

public class MinCostConnection {

    // TODO: Kruskal算法，寻找最小生成树 Kruskal's Algorithm for Minimum Spanning Trees
    // Min Cost to Connect All Points
    // 1 <= points.length <= 1000
    // -10^6 <= xi, yi <= 10^6
    // All pairs (xi, yi) are distinct.
    //
    // 测试理解：是否连接的点最接近会导致全部连接后的距离最短 ?
    // 1. 依次查找每个点的最相邻的点，进行连接，并且保存连接纪录(HashSet)
    // 2. 如果发现最小的线路已经被连接了，则不再连接
    // 3. 直到最后，所有的点都至少被连接过一次
    //    points = [[0,0],[2,2],[3,10],[5,2],[7,0]] 4+3+9+0+4=20
    //    points = [[3,12],[-2,5],[-4,1]]           5+7+2+4=18
    //    points = [[0,0],[1,1],[1,0],[-1,1]]       1+1+2=4
    //    points = [[2,-3],[-17,-8],[13,8],[-17,-15]]
    //             0 --- 2: 22
    //             1 --- 3: 7
    //             特殊情况无法计算...
    //             如何让两个分开连接的部分，再次合成在一起，构成联通的整体 ??
    // 复杂度: O(n^2) O(n) 需要存储联通的线路
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length < 1) return 0;
        int sumDistance = 0;
        HashSet<String> connectedPoints = new HashSet<>();
        for (int i = 0; i < points.length; i++) {
            int j = (i == points.length - 1) ? 0 : i + 1;
            int minDistance = Integer.MAX_VALUE;
            int minIndex = i;
            // 遍历其余所有的连接点，找出其中最短的两点作为连接
            while (j != i) {
                int tempDistance = getDistance(points[i], points[j]);
                if (tempDistance < minDistance) {
                    minDistance = tempDistance;
                    minIndex = j;
                }
                j++;
                if (j == points.length) j = 0;
            }
            String connectedFlag = generateConnectedFlag(i, minIndex);
            // 此处的判断是为了包含只有一个点的特殊情况
            if (minIndex != i) {
                if (!connectedPoints.contains(connectedFlag)) {
                    System.out.println(i + " --- " + minIndex + ": " + minDistance);
                    sumDistance += minDistance;
                    connectedPoints.add(connectedFlag);
                }
            }
        }
        return sumDistance;
    }

    private int getDistance(int[] point1, int[] point2) {
        int distanceX = Math.abs(point1[0] - point2[0]);
        int distanceY = Math.abs(point1[1] - point2[1]);
        return distanceX + distanceY;
    }

    // 将两点的连线做特殊的标记
    private String generateConnectedFlag(int i, int minIndex) {
        if (i < minIndex) {
            return i + ":" + minIndex;
        }
        return minIndex + ":" + i;
    }
}
