package interview;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OtherQuestions {

    // Ekino
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

    interface PhoneCall {
        String getPhoneNumber();

        Duration getDuration();
    }
}
