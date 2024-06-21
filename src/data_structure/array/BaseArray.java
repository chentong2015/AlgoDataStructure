package data_structure.array;

// 静态数组: 存放相同类型的一组数据
// Array: store multiple values with the same types
// Arrays 存放基本的静态方法(直接调用) Arrays.toString()
public class BaseArray {

    // 1. 基本Value Types类型都可构建数组 + 自定义的类型也可以创建数组
    //    TestClass[] JavaUnitTestExceptions.test = new TestClass[10];
    // 2. 使用new声明引用类型的实例, 数组具有固定的长度
    private int[] intArray = new int[10];  // par default = 0
    private String[] stringArray = new String[10];  // par default = null 
    private boolean[] boolArray = new boolean[10];  // par default = false

    // 3. 直接赋初值，同时确定数组的长度
    private double[] doubleArray = {1.0, 2.0, 3.0};
    private double[] doubleArray2 = new double[]{1.0, 2.0, 3.0};

    public BaseArray() {
        // 4. 从下标0开始
        intArray[0] = 10;
        intArray[1] = 10;
        // 5. 使用循环来初始化 ==> 这里使用的length field 设置成利于动态修改的  ===> C#中也是Length表示长度 !!!
        for (int i = 0; i < stringArray.length; i++) {
            stringArray[i] = "item " + i;
        }
    }
}

        
