package xyz.bxdsander.service;


/**
 * @ClassName ArticleInteractionService
 * @Direction: 文章点赞收藏转发评论的逻辑处理
 * @Author: Sander
 * @Date 2021/9/27 10:38
 * @Version 1.0
 **/
public interface ArticleInteractionService {
    /**
     * 查询点赞记录是否存在
     * @param type 操作类型
     * @param typeId 操作的主键id
     * @param userId 用户id
     * @return 存在返回1，不存在返回0
     */
    int starIsExist(int type,int typeId,int userId);

    /**
     * 新增一条点赞记录
     * @param type 操作类型
     * @param typeId 操作的主键id
     * @param userId 用户id
     * @return 返回操作成功与否
     */
    boolean starInsert(int type,int typeId,int userId);

    /**
     *  删除一条点赞记录
     * @param typeId
     * @param type
     * @param userId
     * @return
     */
    boolean starDelete(int typeId,int type,int userId);

    /**
     * 查询是否存在该收藏记录
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回是否存在
     */
    boolean collectionIsExist(int articleId,int userId);

    /**
     *  新增一条收藏记录
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回操作成功与否
     */
    boolean collectionInsert(int articleId,int userId);

    /**
     * 删除一条收藏记录
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回操作是否成功
     */
    boolean collectionDelete(int articleId,int userId);
    /**
     *  查询分享是否存在
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回是否存在的布尔值
     */
    boolean shareIsExist(int articleId, int userId);

    boolean shareInsert(int articleId, int userId);
    /**
     * 删除一条转发记录
     * @param articleId 文章id
     * @param userId 用户id
     * @return 操作是否成功
     */
    boolean shareDelete(int articleId,int userId);


}
