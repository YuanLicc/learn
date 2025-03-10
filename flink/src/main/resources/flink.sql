create table if not exists log (
    id BIGINT,
    text STRING,
    ts TIMESTAMP,
    event_time as cast(current_timestamp as timestamp(3)),
    watermark for ts as ts - interval 3 second
)
WITH (
    'connector' = 'datagen',
    'fields.id.kind' = 'sequence',
    'fields.id.start' = 1,
    'fields.id.end' = 10000,
    'fields.text.kind' = 'random',
    'fields.text.length' = '50',
    'fields.ts.kind' = 'sequence',
    'rows-per-second' = '10'
);

// 滚动窗口
select
    text,
    tumble_start(ts, interval '10' second) as start_time,
    tumble_end(ts, interval '10' second) as end_time,
    count(distinct id)
from log
group by text, tumble(ts, interval '10' second);

// 滑动窗口
select
    text,
    hop_start(ts, interval '2' second, interval '10' second) as start_time,
    hop_end(ts, interval '2' second, interval '10' second) as end_time,
    count(distinct id) as cnt
from log
group by text, hop(ts, interval '2' second, interval '10' second)

// session 窗口
select
    text,
    session_start(ts, interval '7' second) as session_start,
    session_end(ts, interval '7' second) as session_end,
    count(distinct id) as cnt
from log
group by text, session(ts, interval '7' second)


// tvf 写法
// 滑动窗口中，要求窗口长度为滑动步长的整数倍
select
    text,
    window_start,
    window_end,
    sum(id) as idSum
from table(
    hop(table log, descriptor(ts), interval '2' second, interval '8' second)
)
group by text, window_start, window_end;

// 累计窗口，相同的起始时间，但是结束时间不同
select text, window_start, window_end, sum(id) as sumId
from table(cumulate(table log, descriptor(ts), interval '2' second, interval '8' second))
group by text, window_start, window_end;

// lookup join 关联维表
select log.*, wei.*
from log
join weibiao for system_time as of log.ts as wei
on wei.order_id = log.id;

// window 开窗写法

Window w as (
    partition by text order by ts range between interval '10' second and current row
)

