package interview;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OtherQuestions {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 5, 6, 3, 0};
        int[] nums2 = {1, 2, 5, 6, 6, 3, 0};
        int[] nums3 = {1, 9, 5, 6, 6, 3, 0};
        System.out.println(isMountainList(nums1));
        System.out.println(isMountainList(nums2));
        System.out.println(isMountainList(nums3));
    }

    // 判断数组中的数据是否形成"山"的形状: 数字规律必须(严格地)先升后降
    // [1, 2, 5, 6, 3, 0] -> true
    // [1, 2, 6, 6, 3, 0] -> false
    //
    // O(n)  O(1)
    public static boolean isMountainList(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }
        // 只需要判断是处在上升还是下降趋势即可，一次遍历循环
        boolean isUp = true;
        for (int index = 1; index < nums.length; index++) {
            if (nums[index] > nums[index - 1]) {
                if (!isUp) {
                    return false;
                }
            } else if (nums[index] == nums[index - 1]) {
                // 对于严格上升和严格下降而言，不存在连续两个相等的值
                return false;
            } else {
                if (isUp) {
                    isUp = false;
                }
            }
        }
        return true;
    }

    // Java SE APIs的测试和调用
    // 需要注意方法的初始化条件 + Java Stream API的使用
    public Optional<String> solution(List<PhoneCall> phoneCalls) {
        HashMap<String, Duration> hashMapCalls = new HashMap<>();
        for (PhoneCall call : phoneCalls) {
            String number = call.getPhoneNumber();
            if (hashMapCalls.containsKey(number)) {
                Duration oldDuration = hashMapCalls.get(number);
                hashMapCalls.put(number, oldDuration.plus(call.getDuration()));
            } else {
                hashMapCalls.put(number, call.getDuration());
            }
        }

        Duration maxDuration = Duration.ofNanos(0l);
        String resultNumber = "+33999999999";
        for (Map.Entry<String, Duration> entry : hashMapCalls.entrySet()) {
            int comparedValue = entry.getValue().compareTo(maxDuration);
            if (comparedValue > 0) {
                maxDuration = entry.getValue(); // update
                resultNumber = entry.getKey();  // save the number
            } else if (comparedValue == 0) {
                if (resultNumber.compareTo(entry.getKey()) < 0) {
                    resultNumber = entry.getKey();
                }
            }
        }
        if (resultNumber.equals("+33999999999")) {
            return Optional.empty();
        }
        return Optional.of(resultNumber);
    }

    private interface PhoneCall {
        String getPhoneNumber();

        Duration getDuration();
    }
}
