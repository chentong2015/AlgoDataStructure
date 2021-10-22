package system_design.distributed_id_generator;

// 雪花算法: 保证每台机器每个毫秒内生成的ID不同
// 分布式ID: long 64bits分段存储不同的信息
//    1 bit: not used 符号位
//    41 bits: 时间戳，表示某一个毫秒，可以用约70年
//    10 bits: 机器号，这里可以细分成数据区+机器号
//    12 bits: 序列号，一毫秒中可以生成2^12次方个分布式id
public class TwitterSnowflake {

    private long datacenterId;
    private long machineId;
    private long sequence = 0L; // 序列号
    private long lastTimeStmp = -1L;// 上一次时间戳

    // 起始的时间戳，表示一个毫秒: 时间的起始点
    private final static long START_STMP = 1480166465631L;
    private final static long DATACENTER_BIT = 5;// 数据中心占用的位数
    private final static long MACHINE_BIT = 5;   // 机器标识占用的位数
    private final static long SEQUENCE_BIT = 12; // 序列号占用的位数
    // 每个部分左移的位数
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    // TODO: 每一部分最大值, 只保留低位指定长度bit位值1
    // -1的二进制为：........ 1111 1111 1111 1111 1111 1111 1111 1111
    // << 12       ........ 1111 1111 1111 1111 1111 0000 0000 0000
    // ~           ........ 0000 0000 0000 0000 0000 1111 1111 1111
    private final static long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    // 需要根据配置的数据中心和机器id来使用，如果机器过多，则会带来很多大的工作量 ==> 如何自动生成 ?
    public TwitterSnowflake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("数据中心数目范围出错");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("机器id数目出错");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    // TODO: 时钟回拨问题 => 直接抛错
    // 如果系统时间回拨，到时获取到的时间戳比之前生成时间戳更小
    // 导致分布式id不以时间递增，再继续生成则可能导致生成到相同的分布式ID
    public synchronized long nextId() {
        long currStmp = System.currentTimeMillis();
        if (currStmp < lastTimeStmp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }
        if (currStmp == lastTimeStmp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L)
                // 同一毫秒的序列数已经达到最大，等下一个毫秒的到来
                currStmp = getNextMill();
        } else {
            // 不同毫秒内，序列号重置为0
            sequence = 0L;
        }
        lastTimeStmp = currStmp;
        return buildNextId(currStmp);
    }

    // TODO: 自旋等，知道等待到下一个毫秒
    private long getNextMill() {
        long mill = System.currentTimeMillis();
        while (mill <= lastTimeStmp) {
            mill = System.currentTimeMillis();
        }
        return mill;
    }

    // "或运算": 将低位算出来的数值左移定，拼接4个部分值成64位分布式id结果
    private long buildNextId(long currStmp) {
        long timeStmp = (currStmp - START_STMP) << TIMESTMP_LEFT;
        long dataCenter = datacenterId << DATACENTER_LEFT;
        long machine = machineId << MACHINE_LEFT;
        long sequenceNumber = sequence;
        return timeStmp | dataCenter | machine | sequenceNumber;
    }
}
