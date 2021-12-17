package xyz.bxdsander.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName ArticleInteractionDao
 * @Direction: 文章的用户互动--点赞，收藏，转发 评论数，回复数
 * @Author: Sander
 * @Date 2021/9/16 9:04
 * @Version 1.0
 **/
@Mapper
@Repository
public interface ArticleInteractionMapper {
    /** 查询评论的数量，在a_comment表中对符合articleId的记录数量进行查询
     * @param articleId 文章id
     * @return 返回查询到的记录数量
     */
    int commentNumQuery(int articleId);

    /**
     *  在增加评论的同时，更新a_article表中的评论数
     * @param commentNum 评论数量
     * @param articleId 文章id
     * @return 返回是否操作成功
     */
    boolean commentNumUpdate(int commentNum, int articleId);

    /**
     * 查询收藏是否存在
     * @param typeId 类型对应的主键id
     * @param type 所属类型
     * @param userId 用户id
     * @return 返回操作是否成功
     */
    boolean starIsExist(int typeId, int type, int userId);

    /**
     *  增加点赞的记录--------文章，评论，回复共用
     * @param typeId 类型的主键id
     * @param type 所属类型
     * @param userId 用户id
     * @return 返回操作是否成功
     */
    boolean starInsert(int typeId, int type, int userId);

    /**
     * 删除点赞记录-------文章，评论，回复共用
     * @param typeId 类型主键id
     * @param type 所属类型
     * @param userId 用户id
     * @return  返回操作是否成功
     */
    boolean starDelete(int typeId, int type, int userId);

    /**
     *  点赞数查询，在a_article表中对符合articleId的记录进行查询 -----共用
     * @param typeId 文章id
     * @param type 所属类型
     * @return 返回查询到的记录数
     */
    int starNumQuery(int typeId, int type);

    /**
     * 新增点赞的同时，更新a_article表中的点赞数
     * @param starNum 点赞数
     * @param articleId 文章id
     * @return 返回操作是否成功
     */
    boolean starNumUpdate(int starNum, int articleId);

    /**
     *  评论的点赞数-----在增加点赞的同时，更新a_article表中的点赞数
     * @param starNum 评论的点赞数
     * @param commentId 评论的id
     * @return 返回操作是否成功
     */
    boolean commentStarNumUpdate(int starNum, int commentId);

    /**
     *  回复的点赞数-----在增加点赞的同时，更新a_article表中的点赞数
     * @param starNum 回复的点赞数
     * @param replyId 回复的id
     * @return 返回操作是否成功
     */
    boolean replyStarNumUpdate(int starNum, int replyId);

    /**
     * 查询收藏是否存在，在a_article表中查询是否有该条记录
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回操作是否成功
     */
    boolean collectionIsExist(int articleId, int userId);

    /**
     * 新增收藏的同时，更新a_article表中的收藏数
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回操作是否成功
     */
    boolean collectionInsert(int articleId, int userId);

    /**
     * 删除收藏文章记录
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回操作是否成功
     */
    boolean collectionDelete(int articleId, int userId);

    /**
     * 收藏数量查询，在a_collection表中查询符合articleId的记录数量
     * @param articleId 文章id
     * @return 返回查询到的记录数量
     */
    int collectionNumQuery(int articleId);

    /**
     *  进行文章的收藏(包括收藏和取消收藏)操作，同时更新a_article表中的收藏数
     * @param articleId 文章id
     * @param collectionNum 收藏数
     * @return  返回操作是否成功
     */
    boolean collectionNumUpdate(int articleId, int collectionNum);

    /**
     * 在a_share表中查询是否存在符合该条件的记录数
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回操作是否成功
     */
    boolean shareIsExist(int articleId, int userId);

    /**
     * 新增转发文章的记录
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回操作是否成功
     */
    boolean shareInsert(int articleId, int userId);

    /**
     * 删除转发文章的记录
     * @param articleId 对应的文章id
     * @param userId 进行转发的用户id
     * @return 返回操作是否成功
     */
    boolean shareDelete(int articleId, int userId);

    /**
     * 转发数量查询，在a_share表中查询符合articleId的记录数
     * @param articleId 对应的文章id
     * @return 返回文章的转发总数
     */
    int shareNumQuery(int articleId);

    /**
     * 新增一条转发的同时，在a_article表中更新转发数
     * @param articleId 文章id
     * @param shareNum 分享数
     * @return 返回操作是否成功
     */
    boolean shareNumUpdate(int articleId, int shareNum);


}

