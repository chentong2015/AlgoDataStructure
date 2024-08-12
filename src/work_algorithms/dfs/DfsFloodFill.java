package work_algorithms.dfs;

public class DfsFloodFill {

    // Flood Fill
    // An image is represented by an m x n integer grid image,
    // image[i][j] represents the pixel value of the image
    // You are also given three integers sr, sc, and newColor
    // Starting pixel, plus any pixels connected 4-directionally
    // to the starting pixel of the same color as the starting pixel
    //
    // image = [1,1,1],  sr = 1, sc = 1   ->  Output: [2,2,2]
    //         [1,1,0],  newColor = 2                 [2,2,0]
    //         [1,0,1]                                [2,0,1]
    // 从指定的像素位置点出发，将所有相连的同种像素颜色的位置点，全部设置成新的颜色
    //
    // O(n*m)  最差情况所有位置都会遍历，递归调用n*m次方法
    // O(1)    不需要开辟额外的空间
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        resetColor(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    // 从指定的位置，沿着四个方向展开，形成DFS深度优先遍历
    private void resetColor(int[][] image, int row, int col, int sourceColor, int newColor) {
        if (row >= image.length || row < 0 || col >= image[0].length || col < 0) {
            return;
        }
        // TODO. 排除对修改过的颜色再次修改, 避免StackOverflow
        if (image[row][col] != sourceColor || image[row][col] == newColor) {
            return;
        }
        image[row][col] = newColor;
        resetColor(image, row - 1, col, sourceColor, newColor);
        resetColor(image, row, col - 1, sourceColor, newColor);
        resetColor(image, row + 1, col, sourceColor, newColor);
        resetColor(image, row, col + 1, sourceColor, newColor);
    }
}
