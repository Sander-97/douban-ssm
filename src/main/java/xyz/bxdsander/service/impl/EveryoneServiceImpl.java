package xyz.bxdsander.service.impl;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.bxdsander.bean.User;
import xyz.bxdsander.dao.friendMapper;
import xyz.bxdsander.service.EveryoneService;

import java.util.List;

/**
 * @ClassName EveryoneService
 * @Direction: 负责调用和用户好友功能相关的DAO操作
 * @Author: Sander
 * @Date 2021/9/27 10:39
 * @Version 1.0
 **/
public class EveryoneServiceImpl implements EveryoneService {
    @Autowired
    private friendMapper friendMapper;

    /**
     *  查询数据的总数
     * @return 返回查询到的数据
     */
    public int getTotalCount(){
        return friendMapper.getTotalCount();
    }

    /**
     * 分页查询所有用户信息
     * @param currentPage 当前页
     * @param pageSize  页面大小
     * @return 用户集合
     */
    public List<User> queryEveryoneByPage(@Param("offset") int currentPage , @Param("limit") int pageSize){
        return friendMapper.queryEveryoneByPage(currentPage, pageSize);
    }

    /**
     * 查询符合条件数据的总条数
     * @param searchContent 模糊搜索的内容
     * @return 返回搜索到的数据条数
     */
    public int getSearchCount(@Param("searchContent")String searchContent){
        return friendMapper.getSearchCount(searchContent);

    }

    /**
     * 通过模糊搜索的内容进行页面查询
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param searchContent 搜索内容
     * @return 返回查询到的用户集合
     */
    public List<User> queryEveryoneByPageAndSearchContent(@Param("offset") int currentPage , @Param("limit") int pageSize ,@Param("searchContent") String searchContent){
        return friendMapper.queryEveryoneByPage(currentPage, pageSize, searchContent);
    }

    /**
     * 根据本人的用户id和好友关系状态获取好友的总数
     * @param userId 用户id
     * @param status 好友关系状态 1--关注 2---好友
     * @return 返回查询到的数据总数
     */
    public int getFriendTotalCount(@Param("userId") int userId , @Param("status")int status){
        return friendMapper.getFriendTotalCount(userId, status);
    }

    /**
     * 通过用户本人id和好友关系状态进行分页查询
     * @param userId 用户id
     * @param status 好友关系状态 1--关注 2---好友
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @return 查询到的用户集合
     */
    public List<User> queryFriendByPage(@Param("userId")int userId , @Param("status")int status ,@Param("offset")int currentPage ,@Param("limit")int pageSize){
        return friendMapper.queryFriendByPage(userId, status, currentPage, pageSize);
    }

    /**
     *  通过用户本人id和好友关系状态以及搜索的内容进行分页查询
     * @param searchContent 搜索的内容
     * @param userId 用户本人id
     * @param status 好友关系状态 1--关注 2---好友
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @return 返回用户集合
     */
    public List<User> queryFriendByPageAndSearchContent(@Param("searchContent")String searchContent ,@Param("userId")int userId ,@Param("status")int status ,@Param("offset")int currentPage ,@Param("limit")int pageSize){
        return friendMapper.queryFriendByPage(searchContent, userId, status, currentPage, pageSize);
    }

    /**
     *  查询关于搜索内容的好友数据总数
     * @param userId 用户本人id
     * @param status 好友关系状态 1--关注 2---好友
     * @param searchContent 搜索的内容
     * @return 返回搜索的数据大小
     */
    public int getFriendTotalCount(@Param("userId")int userId ,@Param("status")int status ,@Param("searchContent")String searchContent){
        return friendMapper.getFriendGroupCount(searchContent, userId, status);
    }

    /**
     *  查询所有用户的相关信息
     * @param searchContent 搜索的内容
     * @param userId 当前用户id
     * @param status 好友关系状态 1--关注 2---好友
     * @param currentPage 当前页
     * @param pageSize  页面大小
     * @return 返回查询到的用户 集合
     */
    public List<User> queryFriendGroupByPage(@Param("searchContent")String searchContent ,@Param("userId")int userId ,@Param("status")int status ,@Param("offset")int currentPage ,@Param("limit")int pageSize){
        return friendMapper.queryFriendGroupByPage(searchContent,userId,status, currentPage, pageSize);
    }

}
