package data_structure.binary_search;

public class LearnBinarySearch3 {

    // Peak Index in a Mountain Array
    // arr.length >= 3
    // An array arr is a mountain if the following properties hold
    // arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
    // return the index i in O(log(arr.length)) time complexity.
    //
    // Input: arr = [0,10,5,2] -> 1
    public int peakIndexInMountainArray(int[] arr) {
        int left = 1;
        int right = arr.length - 2;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (arr[middle] > arr[middle-1] && arr[middle] > arr[middle+1]) {
                return middle;
            } else if (arr[middle] > arr[middle-1]) {
                left = middle + 1; // 这里一定要往后移动一位，否则会造成无限循环，middle的值不会改变
            } else {
                right = middle;
            }
        }
        return left;
    }
}
