package system_design.distributed_id_generator;

// 分布式ID: 数据的唯一标致
// 1. 全局唯一(unique)
// 2. 按照时间粗略有序(sortable by time)
// 3. 尽可能短
public class DistributedIdGenerator {

    // TODO: 在分布式这个场景下，无法保证严格有序，要想高性能，只能做到粗略有序 ?
    // 失败方案:
    // 1. UUID: 索引太长，非自增
    // 2. (数据库)自增ID: 数据库分表之后，自增的id可能会出现重复
    //
    // 分布式ID生成系统: 高可用，高并发(高调用率)
    // 1. 数据库自增ID: 使用另外一个数据库来做自增ID
    // 2. 数据库多主模式: 使用集群和主从来保证高可用
    // 3. 号段模式: 请求一次数据库，批量获取分布式ID，可能会导致一段号段的失效
    //
    // 成熟的企业级分布式ID生成器
    // 1. DiDi TinyID: Spring + JdbcTemplate + DB数据库 > 基于自增
    // 2. Twitter Snowflake: 雪花算法
    //    2.1 Meituan leaf 提供号段和雪花两种模式
    //    2.2 Baidu uid-generator 不推荐使用 !
    //        作为雪花算法的一个扩展，在使用的时候能够自动生成对应的机器id
    //        > 只能连一个数据库
    //        >  没有做到对应机器部署的区分(docker或实体机)

}
