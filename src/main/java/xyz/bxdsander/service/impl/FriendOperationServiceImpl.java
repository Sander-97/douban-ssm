package xyz.bxdsander.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.bxdsander.dao.friendOperationMapper;
import xyz.bxdsander.service.FriendOperationService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName FriendOperationService
 * @Direction: 好友关系的业务逻辑
 * @Author: Sander
 * @Date 2021/9/27 10:40
 * @Version 1.0
 **/
public class FriendOperationServiceImpl implements FriendOperationService {
    @Autowired
    private friendOperationMapper friendOperationMapper;

    /**
     * 删除好友
     * @param fromUserId 进行操作的当前用户id
     * @param toUserId 被选中的用户id
     * @return 返回操作成功与否
     */
    public boolean friendDelete(int fromUserId , int toUserId){
        boolean judge = false;

        if (friendOperationMapper.friendDelete(fromUserId, toUserId))
            judge = true;

        return judge;
    }

    /**
     * 当前好友状态关系更新
     * @param fromUserId 进行操作的当前用户id
     * @param toUserId 被选中的用户id
     * @param status 状态关系
     * @return 操作成功与否
     */
    public boolean friendUpdate(int fromUserId , int toUserId, int status){
        boolean judge = false;

        if (friendOperationMapper.friendUpdate(fromUserId, toUserId, status))
            judge = true;

        return judge;
    }

    /**
     *  好友关系状态的查询
     * @param fromUserId 进行操作的用户id
     * @param toUserId 被选中的用户id
     * @return 查询到的双方关系
     */
    public int friendQuery(int fromUserId , int toUserId){
        return friendOperationMapper.friendQuery(fromUserId, toUserId);
    }

    /**
     *   增加一个好友相关记录
     * @param fromUserId 进行操作的用户id
     * @param toUserId 被选中的用户id
     * @param groupName 分组名
     * @return 返回操作成功与否
     */
    public boolean friendInsert(int fromUserId, int toUserId ,String groupName){
        boolean judge = false;
        //新增一条好友关系记录到friend表
        if (friendOperationMapper.friendInsert(fromUserId, toUserId)){
            if (!friendOperationMapper.friendGroupIsExist(groupName))
                friendOperationMapper.friendGroupInsert(groupName);

            int groupId = friendOperationMapper.getGroupIdByGroupName(groupName);

            friendOperationMapper.friendGroupUpdate(fromUserId, toUserId,1,groupId);

            judge = true;

        }

        return judge;
    }

    /**
     * 得到该用户对应的好友分组名
     * @param userId 用户id
     * @return 返回用户名集合
     */
    public List<String> friendGroupNameQuery(int userId){
        List<String> groupNames ;

        groupNames = friendOperationMapper.friendGroupNameQuery(userId);
        return groupNames;
    }

    /**
     *  在好友分组中新增一条记录---status默认1
     * @param fromUserId 进行操作的用户id
     * @param toUserId 被操作的用户id
     * @param groupName 分组名
     * @return 返回操作成功与否
     */
    public boolean friendGroupAlter(int fromUserId , int toUserId , String groupName){
        boolean judge = false;
        //判断好友分组名是否存在
        if (!friendOperationMapper.friendGroupIsExist(groupName))
            //如果不存在就新增一个好友分组
            friendOperationMapper.friendGroupInsert(groupName);
        //获得好友分组的id
        int groupId = friendOperationMapper.getGroupIdByGroupName(groupName);
        //更新好友分组的详细信息
         judge = friendOperationMapper.friendGroupUpdate(fromUserId, toUserId, 1, groupId);

        return judge;
    }

}
