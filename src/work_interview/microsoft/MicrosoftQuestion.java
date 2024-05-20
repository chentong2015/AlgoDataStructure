package work_interview.microsoft;

import java.util.*;

public class MicrosoftQuestion {

    // TODO. Sliding Windows 金典使用场景
    // Find the closest sum index
    // Given an array, find the left and right index, within this range the sum is closest to M
    // Return the smallest index (left, right)
    public static int findClosestSumIndex(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int sum = 0;
        int left = 0;
        int closestSum = 0;
        for (int index = 0; index < nums.length; index++) {
            sum += nums[index];
            while (sum > m) {
                sum -= nums[left];
                left++;
            }
            // 退出while循环条件：sum <= m 在小于等于m的所有数里，越大的数则越接近
            if (sum > closestSum) {
                closestSum = sum;
                // 记录坐标[left, index]
            }
        }
        return 0;
    }

    // TODO: 抽象问题的本质：循环依赖
    // Excel Formula Validation
    // Spring使用三级缓存(因为有AOP)来部分解决这个问题
    private static Set<String> finishedCells;  // 一级缓存
    private static Set<String> creatingCells;  // 二级缓存
    private static HashMap<String, String> allCells = new HashMap<>(); // 包含所有的Cells信息

    public static boolean validExcelFormulas(HashMap<String, String> cells) {
        allCells = cells;
        finishedCells = new HashSet<>();
        creatingCells = new HashSet<>();
        for (Map.Entry<String, String> cell : cells.entrySet()) {
            if (!validSingleFormula(cell.getKey(), cell.getValue())) {
                return false;
            }
        }
        return true;
    }

    // 验证一个单独的Cell是否是有效的
    private static boolean validSingleFormula(String key, String formula) {
        // 使用缓存来判断已经计算过的Cell，避免重复执行逻辑
        if (finishedCells.contains(key)) return true;
        // 添加正在被创建的Cell key
        creatingCells.add(key);
        Set<String> relatedCellKeys = parseAllRelatedCellKeys(formula);
        for (String relatedCellKey : relatedCellKeys) {
            if (relatedCellKey.length() > 0) {
                if (finishedCells.contains(relatedCellKey)) continue;
                // TODO: 如果用来计算的相关的单元格正在被创建中，则表示出现了循环依赖，无法计算
                if (creatingCells.contains(relatedCellKey)) return false;
                // 这里没有考虑Cell Formula公式的有效性
                // 成功计算之后，可以设置成Cell单元格的计算值
                boolean result = validSingleFormula(relatedCellKey, allCells.get(relatedCellKey));
                // 如果递归中有错，则整个Excel Formula无效
                if (!result) return false;
            }
        }
        // 移除掉正在创建的cell, 保存已经创建好的cell
        creatingCells.remove(key);
        finishedCells.add(key);
        return true;
    }

    private static Set<String> parseAllRelatedCellKeys(String formula) {
        Set<String> relatedCellKeys = new HashSet<>();
        if (formula.contains(",")) {
            // 将数组转成list，然后直接添加到结果Set集中
            relatedCellKeys.addAll(Arrays.asList(formula.split(",")));
        } else {
            relatedCellKeys.add(formula);
        }
        return relatedCellKeys;
    }

    // 单元测试案例
    // 1. 测试没有任何相关联 true
    private static void testCase1() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "");
        cells.put("A01", "");
        cells.put("B00", "");
        System.out.println(validExcelFormulas(cells));
    }

    // 2. 测试有简单关联 true
    private static void testCase2() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "A01");
        cells.put("A01", "");
        cells.put("B00", "");
        System.out.println(validExcelFormulas(cells));
    }

    // 3. 测试有复杂关联 true
    private static void testCase3() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "B00");
        cells.put("A01", "A00");
        cells.put("B00", "");
        System.out.println(validExcelFormulas(cells));
    }

    // 4. 测试有复杂关联 true
    private static void testCase4() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "B00,B01");
        cells.put("A01", "A00");
        cells.put("B00", "");
        cells.put("B01", "");
        System.out.println(validExcelFormulas(cells));
    }

    // 5. 测试简单循环依赖 false
    private static void testCase5() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "A01");
        cells.put("A01", "A00");
        cells.put("B00", "");
        System.out.println(validExcelFormulas(cells));
    }

    // 6. 测试复杂循环依赖 false
    private static void testCase6() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "");
        cells.put("A01", "A00,B00,A01");
        cells.put("B00", "");
        System.out.println(validExcelFormulas(cells));
    }

    // 7. 测试复杂循环依赖 false
    private static void testCase7() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "A01");
        cells.put("A01", "B00");
        cells.put("B00", "A00");
        System.out.println(validExcelFormulas(cells));
    }

    // 8. 测试复杂循环依赖 false
    private static void testCase8() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "A01");
        cells.put("A01", "B00");
        cells.put("B00", "B01");
        cells.put("B01", "A01");
        System.out.println(validExcelFormulas(cells));
    }

    // 9. 测试复杂循环依赖 false
    private static void testCase9() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "C01,A01");
        cells.put("A01", "B00");
        cells.put("B00", "B01");
        cells.put("B01", "A01");
        cells.put("C01", "");
        System.out.println(validExcelFormulas(cells));
    }

    // 10. 测试复杂循环依赖 false
    private static void testCase10() {
        HashMap<String, String> cells = new HashMap<>();
        cells.put("A00", "C01,A01");
        cells.put("A01", "B00,D02"); // 假设这里不能出现找不到key的情况
        cells.put("B00", "B01");
        cells.put("B01", "A01");
        cells.put("C01", "");
        System.out.println(validExcelFormulas(cells));
    }
}
