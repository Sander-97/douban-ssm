package xyz.bxdsander.service;

import xyz.bxdsander.bean.chat;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName chatService
 * @Direction: 处理和聊天相关的业务逻辑，负责数据的拼接
 * @Author: Sander
 * @Date 2021/9/27 10:39
 * @Version 1.0
 **/
public interface chatService {
    /**
     * 在聊天表查询符合条件的数据总数
     * @param userId 用户id
     * @return 数据总数
     */
    public int getTotalCount(int userId);
    /**
     *  分页查询符合条件的聊天记录
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param userId 用户id
     * @return 聊天的chat对象的数据集合
     */
    public List<chat> queryChatByPage(int currentPage , int pageSize , int userId);

    /**
     *  查询符合条件的数据记录总数
     * @param userId 用户id
     * @param toUserId 被当前用户选择的id
     * @return 返回数据总数
     */
    public int getShowCount(int userId ,int toUserId);


    /**
     *  分页查询符合条件的聊天记录---聊天信息展示
     * @param currentPage 当前页
     * @param PageSize 页面大小
     * @param userId  用户id
     * @param toUserId 被选中的用户id
     * @return 返回数据集合
     */
    public List<chat> queryShowByPage(int currentPage , int PageSize ,int userId , int toUserId);

    /**
     * 进行聊天的增加
     * @param chat 聊天
     * @return 操作成功与否
     */
    public boolean chatInsert(chat chat);
}
