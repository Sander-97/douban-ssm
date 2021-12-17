package xyz.bxdsander.service;


import xyz.bxdsander.bean.Msg;
import xyz.bxdsander.bean.User;

/**
 * @ClassName UserService
 * @Direction: 对于上传的要登录的用户名和密码进行处理
 * @Author: Sander
 * @Date 2021/9/27 10:40
 * @Version 1.0
 **/
public interface UserService {

    /**
     *  用户登录
     * @param userName
     * @param password
     * @return
     */
    Msg login(String userName, String password);

    /**
     *  用户注册
     * @param userName
     * @param password
     * @return
     */
    Msg register(String userName, String password);

    /**
     * 更新当前用户的个人信息
     * @param user
     * @return 返回true成功，false失败
     */
    boolean personage(User user);

    /**
     *  更新当前用户的头像信息
     * @param user
     * @return true成功，false失败
     */
    boolean portrait(User user);

    /**
     *  查询用户的信息
     * @param userName 用户名
     * @return 成功返回用户user对象，失败返回null
     */
    User UserInfo(String userName);

    /**
     *  通过用户id得到用户的所有信息
     * @param userId
     * @return 成功返回user对象，失败返回null
     */
    User userInfoByUserId(int userId);

    /**
     * 通过用户名进行逻辑判断和记录增加
     * @param userName
     * @return 成功与否
     */
    boolean findBackUser(String userName);
}
