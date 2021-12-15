package xyz.bxdsander.service;

import java.util.List;

/**
 * @ClassName FriendOperationService
 * @Direction: 好友关系的业务逻辑
 * @Author: Sander
 * @Date 2021/9/27 10:40
 * @Version 1.0
 **/
public interface FriendOperationService {

    /**
     * 删除好友
     * @param fromUserId 进行操作的当前用户id
     * @param toUserId 被选中的用户id
     * @return 返回操作成功与否
     */
    boolean friendDelete(int fromUserId , int toUserId);

    /**
     * 当前好友状态关系更新
     * @param fromUserId 进行操作的当前用户id
     * @param toUserId 被选中的用户id
     * @param status 状态关系
     * @return 操作成功与否
     */
    boolean friendUpdate(int fromUserId , int toUserId, int status);

    /**
     *  好友关系状态的查询
     * @param fromUserId 进行操作的用户id
     * @param toUserId 被选中的用户id
     * @return 查询到的双方关系
     */
    int friendQuery(int fromUserId , int toUserId);
    /**
     *   增加一个好友相关记录
     * @param fromUserId 进行操作的用户id
     * @param toUserId 被选中的用户id
     * @param groupName 分组名
     * @return 返回操作成功与否
     */
    boolean friendInsert(int fromUserId, int toUserId ,String groupName);

    /**
     * 得到该用户对应的好友分组名
     * @param userId 用户id
     * @return 返回用户名集合
     */
    List<String> friendGroupNameQuery(int userId);

    /**
     *  在好友分组中新增一条记录---status默认1
     * @param fromUserId 进行操作的用户id
     * @param toUserId 被操作的用户id
     * @param groupName 分组名
     * @return 返回操作成功与否
     */
    boolean friendGroupAlter(int fromUserId , int toUserId , String groupName);

}
