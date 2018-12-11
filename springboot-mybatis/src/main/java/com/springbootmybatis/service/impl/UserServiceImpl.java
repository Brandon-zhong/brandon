package com.springbootmybatis.service.impl;

import com.spirngboot.utils.*;
import com.springbootmybatis.dao.UserMapper;
import com.springbootmybatis.entity.User;
import com.springbootmybatis.service.UserService;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author brandon
 * Created on 2018-12-01.
 * desc: 用户管理逻辑实现类
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private RedisUtil redisUtil;
//    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;


    /**
     * 获取所有的用户信息
     *
     * @return 返回所有的用户的list形式
     */

    @Override
    public List<User> getAllUser() {
//        List<User> userInfo = null;
//        List<User> userInfo = (List<User>) redisTemplate.opsForValue().get("all_user_info");
        List<User> userInfo = (List<User>) redisUtil.getValue("all_user_info");
//        System.out.println("redis data --> " + userInfo.toString());
        if (null != userInfo && userInfo.size() != 0) {
//            System.out.println("这个数据是从redis中拿到的");
            log.info("这个数据是从redis中拿到的");
            return userInfo;
        }

        userInfo = userMapper.selectAllUser();
        if (null == userInfo || userInfo.size() == 0) {
            return null;
        }

        for (User user : userInfo) {
            user.setPassword("");
        }

        redisUtil.setValue("all_user_info", userInfo);

        return userInfo;
    }


    /**
     * 用户登录逻辑处理
     *
     * @param username 用户登录的用户名
     * @param password 用户登录的密码
     * @return 登录成功, 则返回该用户对象, 登录失败则返回null
     */

    @Override
    public User login(String username, String password) {

        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return null;
        }

        User user = userMapper.selectByUserName(username);
        if (null == user) {
            return null;
        }

        if (!EncryptUtil.encodeSHA256(password).equals(user.getPassword())) {
            return null;
        }
        //验证密码成功后,更新最近登录时间
        user.setLastUse(TimeUtil.getTimeSecend());
        userMapper.updateByPrimaryKeySelective(user);

//        System.out.println("登录成功 --> " + username);
        log.info("登录成功 --> " + username);


        //将密码置空,并返回
        user.setPassword("");

        return user;
    }


    /**
     * 注册用户操作
     *
     * @param userName 注册的用户名
     * @param password 注册的用户密码
     * @param age      注册的用户年龄
     * @return 返回注册操作影响的行数
     */

    @Override
    public int register(String userName, String password, int age) {

        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
            return 0;
        }

        User user = new User();
        user.setName(userName);
        user.setPassword(EncryptUtil.encodeSHA256(password));
        user.setAge(age);
        user.setLastUse(TimeUtil.getTimeSecend());

        return userMapper.insert(user);
    }


    /**
     * 修改用户信息操作
     *
     * @param user 要修改的用户信息(能修改的用户信息有:密码,年龄)
     * @return 返回影响的航行数
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int modifyUser(User user) {

        //用户信息的合法性校验(id和用户名至少有一个是有效的)
        boolean bool = null == user || (null == user.getId() && StringUtil.isEmpty(user.getName()));
        if (bool) {
            return 0;
        }
        //根据用户id或者用户名查找用户信息
        User selectUser = null;
        if (null != user.getId()) {
            selectUser = userMapper.selectByPrimaryKey(user.getId());
        } else if (!StringUtil.isEmpty(user.getName())) {
            selectUser = userMapper.selectByUserName(user.getName());
        } else {
            return 0;
        }

        //验证用户年龄和密码的合法信息,如果合法则设置并更新
        if (0 != user.getAge()) {
            selectUser.setAge(user.getAge());
        }
        if (!StringUtil.isEmpty(user.getPassword())) {//密码更新的时候要进行加密操作
            selectUser.setPassword(EncryptUtil.encodeSHA256(user.getPassword()));
        }
        return userMapper.updateByPrimaryKeySelective(selectUser);
    }


    private static void threadPoolTest() throws ExecutionException, InterruptedException {

//         BasicThreadFactory.Builder()
        System.out.println("UserServiceImpl.threadPoolTest");

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("").daemon(true).build());

        scheduledThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("这是通过 ScheduledThreadPoolExecutor 线程池创建的线程");
            }
        });

        ThreadPoolUtil.executorTask(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println("通过线程池 工具类 执行线程任务 --> " + i);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ThreadPoolUtil.executorTask(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println("asfsfasf调用线程 --> " + i);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

//        Future submitTask = ThreadPoolUtil.submitTask(() -> {
//            int total = 0;
//            try {
//                for (int i = 0; i < 100; i++) {
//                    System.out.println("submitTask调用线程 --> " + i);
//                    total++;
//                    Thread.sleep(1000);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return total;
//        });
//        System.out.println("submitTask --> " + submitTask.get());

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        threadPoolTest();
    }


}
