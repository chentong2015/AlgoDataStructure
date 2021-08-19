package com.base_system_design.time_scheduler;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// 表示过期的任务，通过Delayed元素来体现
public class DelayedTask implements Delayed {

    private String name;
    private long startTime; // milliseconds

    public DelayedTask(String name, long delay) {
        this.name = name;
        this.startTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask task = (DelayedTask) o;
        return (int) (this.startTime - task.startTime);
    }

    @Override
    public String toString() {
        return "DelayedTask{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
