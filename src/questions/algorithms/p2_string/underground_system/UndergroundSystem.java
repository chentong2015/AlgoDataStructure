package questions.algorithms.p2_string.underground_system;

import java.util.HashMap;
import java.util.Map;

// 注意约束条件:
// 1 <= id, t <= 10^6 顾客id的数目约定在百万之内，时间相隔小于10^6
// 1 <= stationName.length, startStation.length, endStation.length <= 10 名字的长度不会很长
// All strings consist of uppercase and lowercase English letters and digits. 
// There will be at most 2 * 104 calls in total to checkIn, checkOut, and getAverageTime.
// Answers within 10^-5 of the actual value will be accepted.
public class UndergroundSystem {

    private Map<Integer, Station> setCheckIn;
    private Map<String, TimeTotal> tableTimeTotal;

    public UndergroundSystem() {
        setCheckIn = new HashMap<>();
        tableTimeTotal = new HashMap<>();
    }

    public void checkIn(int customerId, String stationName, int startTime) {
        if (setCheckIn.containsKey(customerId)) {
            // Error: 已经check in的顾客不能重复
        } else {
            Station station = new Station(stationName, startTime);
            setCheckIn.put(customerId, station);
        }
    }

    public void checkOut(int customerId, String endStationName, int endTime) {
        if (setCheckIn.containsKey(customerId)) {
            Station startStation = setCheckIn.get(customerId);
            String stationKey = startStation.getName() + ":" + endStationName;
            int timeInterval = endTime - startStation.getStartTime();
            if (tableTimeTotal.containsKey(stationKey)) {
                TimeTotal timeTotal = tableTimeTotal.get(stationKey);
                timeTotal.setSum(timeTotal.getSum() + timeInterval);
                timeTotal.setTimes(timeTotal.getTimes() + 1);
            } else {
                TimeTotal timeTotal = new TimeTotal(timeInterval, 1);
                tableTimeTotal.put(stationKey, timeTotal);
            }
            // 统计完成之后，需要移除原来check in集合中的数据
            setCheckIn.remove(customerId);
        } else {
            // Error: check out的时候，必须已经有check in的纪录
        }
    }

    // 同样两个站，正向坐和反向坐的平均时间可能不同
    // 该方法保证最低的时间复杂度 O(1)
    public double getAverageTime(String startStationName, String endStationName) {
        String stationKey = startStationName + ":" + endStationName;
        if (tableTimeTotal.containsKey(stationKey)) {
            TimeTotal timeTotal = tableTimeTotal.get(stationKey);
            return timeTotal.getSum() / timeTotal.getTimes();
        } else {
            // Error: Without any records, can not calculate the average time
            return 0d;
        }
    }
}
