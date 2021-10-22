package system_design.distributed_id_generator;

// 分布式ID: 数据的唯一标致
// 1. 全局唯一(unique)
// 2. 按照时间粗略有序(sortable by time)
// 3. 尽可能短
public class DistributedIdGenerator {

    // UUID: 索引太长，非自增
    // (数据库)自增ID: 数据库分表之后，自增的id可能会出现重复
    // 分布式ID生成系统: 高可用，高并发(高调用率)
    // 1. 数据库自增ID: 使用另外一个数据库来做自增ID > 使用集群和主从来保证高可用
    // 2.
    // 3.


    // TODO: 在分布式这个场景下，无法保证严格有序，要想高性能，只能做到粗略有序
    // 场景01：多台MySQL服务器组成高性能分布式发号器
    // 每来一个请求，由round-robin balancer随机地将请求发给8台MySQL中的任意一个，然后返回一个ID
    // 缺点是ID是不是严格递增的，只是粗略递增的
    // 场景02：Twitter Project 将64bits分段存储不同的信息
    // 1 bit: not used
    // 41 bits: 时间戳 - 按照时间粗略有序
    // 10 bits: machine id
    // 12 bits: 序列号
}
