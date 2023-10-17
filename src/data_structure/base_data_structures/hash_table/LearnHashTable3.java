package data_structure.base_data_structures.hash_table;

import algorithms.datamodel.TreeNode;

import java.util.*;

// TODO: Hash Table如何设计和构建key键值
// 1. 找到唯一对应的合理性来作为key，作为验证(重复性，有效性)和合并 !!
public class LearnHashTable3 {

    // 将数字和它的所处位置关系，构建成一个唯一的key键值，作为判断
    // Valid Sudoku
    // Determine if a 9 x 9 Sudoku board is valid
    // Input: board => true
    //      [["5","3",".",".","7",".",".",".","."]
    //      ,["6",".",".","1","9","5",".",".","."]
    //      ,[".","9","8",".",".",".",".","6","."]
    //      ,["8",".",".",".","6",".",".",".","3"]
    //      ,["4",".",".","8",".","3",".",".","1"]
    //      ,["7",".",".",".","2",".",".",".","6"]
    //      ,[".","6",".",".",".",".","2","8","."]
    //      ,[".",".",".","4","1","9",".",".","5"]
    //      ,[".",".",".",".","8",".",".","7","9"]]
    public boolean isValidSudoku(char[][] board) {
        // Test：核心在于验证数字的重复性，如何判断一个数字，在它对应的行，列和block局域中已经存在
        //       1(5)    表示5在第一行
        //       (5)1    表示5在第一列
        //       0(5)0   表示5在第一个block的位置
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    String b = "(" + board[i][j] + ")";
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 3 + b + j / 3))
                        return false;
                }
            }
        }
        return true;
    }

    // Find Duplicate Subtrees
    // Given the root of a binary tree, return all duplicate subtrees
    // For each kind of duplicate subtrees, you only need to return the root node of any one of them
    // Two trees are duplicate if they have the same structure with the same node values
    // root = [1,2,3,4,null,2,4,null,null,4] -> [[2,4],[4]]
    //     1       本质上是要构建每个subtree的唯一key键值的表示，通过key确定子树是否已经存在 !!
    //   2   3     计算子树"序列化"的值来作为key     1(2(4),3(2(4),4)) 3(2(4),4)
    // 4    2  4   必须要考虑值在左边或右边的子树上面
    //     4       首先确定以什么方式遍历完整个树 ??  只能使用前序和后序遍历 !!
    private int curId = 1;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> serialToId = new HashMap<>();
        Map<Integer, Integer> idToCount = new HashMap<>();
        List<TreeNode> res = new LinkedList<>();
        postorder(root, serialToId, idToCount, res);
        return res;
    }

    // 用后续遍历构建出当前node的Serial序列, 满足重复序列的则提取到结果list中
    private int postorder(TreeNode root, Map<String, Integer> serialToId,
                          Map<Integer, Integer> idToCount, List<TreeNode> res) {
        if (root == null) return 0;
        int leftId = postorder(root.getLeft(), serialToId, idToCount, res);
        int rightId = postorder(root.getRight(), serialToId, idToCount, res);
        String curSerial = leftId + "," + root.getVal() + "," + rightId;

        int serialId = serialToId.getOrDefault(curSerial, curId);
        if (serialId == curId) curId++;
        serialToId.put(curSerial, serialId);

        int serialIdCount = idToCount.getOrDefault(serialId, 0);
        idToCount.put(serialId, serialIdCount + 1);
        if (idToCount.get(serialId) == 2) res.add(root);
        return serialId;
    }
}
