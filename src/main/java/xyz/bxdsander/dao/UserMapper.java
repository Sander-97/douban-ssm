package xyz.bxdsander.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.bxdsander.bean.Msg;
import xyz.bxdsander.bean.User;

/**
 * @InterfaceName UserDao
 * @Direction: 用户登录相关的操作
 * @Author: Sander
 * @Date 2021/9/16 8:55
 * @Version 1.0
 **/
@Mapper
@Repository
public interface UserMapper {

    /**
     *   用户登录
     * @param user 用户对象
     * @return 返回Msg对象，信息会被储存在对象中用来传输
     */
    Msg login(User user);

    /**
     *  用户注册
     * @param user 用户对象
     * @return 返回1表示注册成功
     *             0表示注册失败
     */
     int register(User user);

    /**
     *  查看用户名是否重复
     * @param user 用户对象
     * @return 返回1表示重复
     *             0表示没有重复
     */
    int repetition(User user);

    /**
     * 更新user的个人信息
     * @param user 通过user对象的用户名来查询更新
     * @return  返回1表示更新成功
     *              0表示更新失败
     */
    int personAge(User user);

    /**
     * 更新数据库中用户头像地址
     * @param user 用户对象
     * @return 返回1表示更新成功
     *             0表示更新失败
     */
    int portrait(User user);

    /**
     * 通过用户的名字来查询用户信息
     * @param userName 用户名
     * @return 返回用户对象
     */
    User userInfoByUserName(String userName);

    /**
     *  根据用户id来查询用户信息
     * @param userId 用户的id
     * @return 返回用户对象
     */
    User userInfoByUserId(int userId);

    /**
     * 通过用户名来更新code和outTime
     * @param user 用户对象
     * @return 布尔值
     */
    boolean userUpdate(User user);



}
