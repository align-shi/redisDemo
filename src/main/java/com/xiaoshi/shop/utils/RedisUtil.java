package com.xiaoshi.shop.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 操作redis的工具类
 * @author xiaoshi
 */
@SuppressWarnings("all")
@Component
public class RedisUtil {
    private static Logger log = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 设置某个Key的过期时间
     * @param key
     * @param time
     * @return 设置成功返回true
     */
    public boolean expire(String key, long time) {
        try {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取key的过期时间
     * @param key 不能为空
     * @return 返回0表示永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除一个或者多个Key
     * @param key
     * @return
     */
    public boolean delKey(String...key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                return redisTemplate.delete(Arrays.asList(key)) > 0 ? true : false;
            }
        }
        return false;
    }

    /**
     * 获取普通的key值
     * @param key
     * @return 返回对应的value
     */
    public Object get(String key) {
        try {
            return key == null ? null : redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 设置普通的key-value键值对
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置普通的key键值对并设置过期时间
     * @param key
     * @param value
     * @param time 过期的时间(秒)，如果值小于0，则设置为永不过期
     * @return
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key,value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置递增的key
     * @param key
     * @param delta 递增因子要大于0
     * @return 递增后的值
     */
    public long incr(String key, long delta) {
        if (delta > 0) {
            throw new RuntimeException("递增因子必须大于0");
        } else {
            return redisTemplate.opsForValue().increment(key, delta);
        }
    }

    /**
     * 设置递减的key
     * @param key
     * @param delta 递减因子需要大于0
     * @return 递增后的值
     */
    public long decr(String key, long delta) {
        if (delta > 0) {
            throw new RuntimeException("递增因子必须大于0");
        } else {
            return redisTemplate.opsForValue().decrement(key, delta);
        }
    }

    /**
     * *******************HASh 类型******************
     */

    /**
     * 获得hash类型的值
     * @param key 哈希表的名称
     * @param itemm 哈希表具体键
     * @return 具体键对应的值
     */
    public Object hget(String key, String itemm) {
        try {
            return redisTemplate.opsForHash().get(key, itemm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取一个哈希表内所有的键值对
     * @param key
     * @return 返回所有的键值对map
     */
    public Map<Object, Object> hmget(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 存储hash类型的数据
     * @param key hash表的key
     * @param value 哈希表的内部键值对
     * @return
     */
    public boolean hmset(String key, Map<String, Object> value) {
        try {
            redisTemplate.opsForHash().putAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *设置hash类型 并设置过期时间
     * @param key 哈希表的名称
     * @param value 哈希表内部的键值对
     * @param time 过期时间 如果小于等于0 这位永不过期或跟随全局的过期时间
     * @return
     */
    public boolean hmset(String key, Map<String, Object> value, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, value);
            //调用自己封装的设置key过期时间的方法
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中插入一条键值对数据，如果表不存在将会创建表
     * @param key 哈希表名称
     * @param item
     * @param value
     * @return
     */
    public boolean hset(String key, Object item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中插入一条键值对数据，如果表不存在将会创建表并设置过期时间
     * @param key
     * @param item
     * @param value
     * @param time 过期时间 如果小于等于0 这位永不过期或跟随全局的过期时间
     * @return
     */
    public boolean hset(String key, Object item, Object value,long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从Hash表中删除一个或多个键值对
     * @param key
     * @param value
     * @return
     */
    public boolean hdel(String key, Object... value) {
        try {
            redisTemplate.opsForHash().delete(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断hash表中是否存在某个值
     * @param key
     * @param item
     * @return
     */
    public boolean hHaskey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 设置hash表的key的递增，如果不存在将会创建
     * @param key
     * @param item
     * @param incrBy 递增因子 double类型
     * @return 返回递增后的数
     */
    public double hincr(String key, String item, double incrBy) {
        return redisTemplate.opsForHash().increment(key, item, incrBy);
    }

    /**
     * 设置hash表的key的递减，如果不存在将会创建
     * @param key
     * @param item
     * @param descBy 递减因子 double类型
     * @return 返回递增后的数
     */
    public double hdesc(String key, String item, double descBy) {
        return redisTemplate.opsForHash().increment(key, item, -descBy);
    }

    /**
     * *******************set 类型*********************
     */

    /**
     * 获取set中所有的值
     * @param key
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断一个set集合中是否存在某个值
     * @param key
     * @param value
     * @return
     */
    private boolean sHaskey(String key, String value) {
        try {
            redisTemplate.opsForSet().isMember(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 新增一个set集合的缓存
     * @param key
     * @param value 可以是多个值
     * @return
     */
    public long sSet(String key, Object... value) {
        try {
            return redisTemplate.opsForSet().add(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 设置有过期时间的set
     * @param key
     * @param time
     * @param value
     * @return
     */
    public long sSet(String key, long time, Object... value) {
        try {
            Long result = redisTemplate.opsForSet().add(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获得set集合的长度
     * @param key
     * @return 长度
     */
    public long sGetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 从set中移除一个或者多个值
     * @param key
     * @param value
     * @return
     */
    public long sRemove(String key, Object... value) {
        try {
            return redisTemplate.opsForSet().remove(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * ******************************list类型***************************
     */

    /**
     * 获取list列表的值
     * @param key
     * @param start 起始点 从0开始
     * @param end 结束点 若区间为0 ~ -1 则返回所有的值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回List的长度
     * @param key
     * @return
     */
    public long lGetSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引获取对应的值
     * @param key
     * @param index 索引可以为负数
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将单个的值放入list缓存，若list不存在将创建
      * @param key
     * @param value
     * @return
     */
    public boolean lSet(String key ,Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置带有缓存时间的list key
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将List类型的数据放入缓存,若time为0则不设置过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean lSet(String key, List<Object> value,long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新list中某个索引的值
     * @param key
     * @param index
     * @param value
     * @return
     */
    public boolean lUpdate(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从list中删除指定个数的value值
     * @param key
     * @param count 删除的个数
     * @param value 具体的值
     * @return
     */
    public boolean lRemove(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value) > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
