package core_algo.learn_all_problems.p2_string.underground_system;

public class Station {

    private String name;
    private int startTime;

    public Station(String name, int startTime) {
        this.name = name;
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public int getStartTime() {
        return startTime;
    }
}
