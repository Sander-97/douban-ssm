package xyz.bxdsander.service.impl;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.bxdsander.bean.Msg;
import xyz.bxdsander.bean.User;
import xyz.bxdsander.dao.UserMapper;
import xyz.bxdsander.service.UserService;
import xyz.bxdsander.util.ValidateUtil;

/**
 * @ClassName UserService
 * @Direction: 对于上传的要登录的用户名和密码进行处理
 * @Author: Sander
 * @Date 2021/9/27 10:40
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private User user;

    /**
     *  用户登录
     * @param userName
     * @param password
     * @return
     */
    public Msg login(String userName, String password){
        //调用判空方法
        if (ValidateUtil.isInvalidUserName(userName) == true)
            return new Msg("用户名为空",null);

        user.setUserName(userName);
        user.setPassWord(password);
        //确认该用户的用户名和密码是否都正确，并确认他的管理权限
        Msg msg = userMapper.login(user);
        return msg;
    }

    /**
     *  用户注册
     * @param userName
     * @param password
     * @return
     */
    public Msg register(String userName, String password){
        int judge = 0;
        /**
         * 判断用户名和密码是否为空
         */
        if (ValidateUtil.isInvalidUserName(userName))
            return new Msg("用户名为空",null);
        else if (ValidateUtil.isInvalidPassword(password))
            return new Msg("密码为空",null);

        user.setPassWord(password);
        user.setUserName(userName);
        //判断所注册的用户名是否重复
        if (userMapper.repetition(user) == 1)
            return new Msg("用户名重复",null);
        else {
            judge = userMapper.register(user);
            if (judge==1)
                return new Msg("注册成功",user);
            else
                return new Msg("注册失败",user);

        }

    }

    /**
     * 更新当前用户的个人信息
     * @param user
     * @return 返回true成功，false失败
     */
    public boolean personage(User user){
        int judge = 0;
        judge = userMapper.personAge(user);
        if (judge == 1)
            return true;
        else return false;
    }

    /**
     *  更新当前用户的头像信息
     * @param user
     * @return true成功，false失败
     */
    public boolean portrait(User user){
        int judge;
        judge = userMapper.portrait(user);
        if (judge == 1)
            return true;
        else return false;

    }

    /**
     *  查询用户的信息
     * @param userName 用户名
     * @return 成功返回用户user对象，失败返回null
     */
    public User UserInfo(String userName){
        user = userMapper.userInfoByUserName(userName);
        if (user != null) {
            return user;
        }else return null;
    }

    /**
     *  通过用户id得到用户的所有信息
     * @param userId
     * @return 成功返回user对象，失败返回null
     */
    public User userInfoByUserId(int userId){
        user = userMapper.userInfoByUserId(userId);
        if (user!= null)
        {
            return user;
        }else return null;
    }

    /**
     * 通过用户名进行逻辑判断和记录增加
     * @param userName
     * @return 成功与否
     */
    public boolean findBackUser(String userName){
        user.setUserName(userName);

        if (userMapper.repetition(user) == 1)
        {
            //将该用户对应的记录中的找回密码相关的字段，更新为最新情况
        }
        else
        {
            return false;
        }
        return false;

    }
}
