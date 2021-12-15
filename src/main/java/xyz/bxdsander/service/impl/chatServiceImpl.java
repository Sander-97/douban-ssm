package xyz.bxdsander.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.bxdsander.bean.User;
import xyz.bxdsander.bean.chat;
import xyz.bxdsander.dao.UserMapper;
import xyz.bxdsander.dao.chatMapper;
import xyz.bxdsander.service.chatService;
import java.util.List;

/**
 * @ClassName chatService
 * @Direction: 处理和聊天相关的业务逻辑，负责数据的拼接
 * @Author: Sander
 * @Date 2021/9/27 10:39
 * @Version 1.0
 **/
public class chatServiceImpl implements chatService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private chatMapper chatMapper;

    /**
     * 在聊天表查询符合条件的数据总数
     * @param userId 用户id
     * @return 数据总数
     */
    public int getTotalCount(int userId){
        return chatMapper.getTotalCount(userId);
    }

    /**
     *  分页查询符合条件的聊天记录
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param userId 用户id
     * @return 聊天的chat对象的数据集合
     */
    public List<chat> queryChatByPage(int currentPage , int pageSize , int userId){
        List<chat> chats = null;
        List<chat> chats1;

        User fromUser ;

        User toUser ;

        chats1 = chatMapper.queryChatByPage(currentPage, pageSize, userId);

        for (chat chat : chats1){
            fromUser = userMapper.userInfoByUserId(chat.getFromUserId());
            toUser = userMapper.userInfoByUserId(chat.getToUserId());

            chat.setFromUserImg(fromUser.getPortrait());
            chat.setFromUserNickName(fromUser.getNickName());

            chat.setToUserImg(toUser.getPortrait());
            chat.setToUserNickName(toUser.getNickName());

            chats.add(chat);
        }

        return chats;
    }

    /**
     *  查询符合条件的数据记录总数
     * @param userId 用户id
     * @param toUserId 被当前用户选择的id
     * @return 返回数据总数
     */
    public int getShowCount(int userId ,int toUserId){
        return chatMapper.getShowTotalCount(userId,toUserId);
    }


    /**
     *  分页查询符合条件的聊天记录---聊天信息展示
     * @param currentPage 当前页
     * @param PageSize 页面大小
     * @param userId  用户id
     * @param toUserId 被选中的用户id
     * @return 返回数据集合
     */
    public List<chat> queryShowByPage(int currentPage , int PageSize ,int userId , int toUserId){
        List<chat> chats = null;
        List<chat> chats1 ;

        User fromUser ;
        User toUser;

        chats1 = chatMapper.queryShowByPage(currentPage,PageSize,userId, toUserId);

        for (chat chat : chats1){

            fromUser = userMapper.userInfoByUserId(chat.getFromUserId());
            toUser = userMapper.userInfoByUserId(chat.getToUserId());

            chat.setFromUserImg(fromUser.getPortrait());
            chat.setFromUserNickName(fromUser.getNickName());

            chat.setToUserImg(toUser.getPortrait());
            chat.setToUserNickName(toUser.getNickName());

            chats.add(chat);
        }

        return chats;

    }

    /**
     * 进行聊天的增加
     * @param chat 聊天
     * @return 操作成功与否
     */
    public boolean chatInsert(chat chat){
        return chatMapper.chatInsert(chat);
    }
}
