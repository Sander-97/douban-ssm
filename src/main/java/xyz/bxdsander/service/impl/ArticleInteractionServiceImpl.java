package xyz.bxdsander.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.bxdsander.dao.ArticleInteractionMapper;
import xyz.bxdsander.service.ArticleInteractionService;


/**
 * @ClassName ArticleInteractionService
 * @Direction: 文章点赞收藏转发评论的逻辑处理
 * @Author: Sander
 * @Date 2021/9/27 10:38
 * @Version 1.0
 **/
public class ArticleInteractionServiceImpl implements ArticleInteractionService {

    @Autowired
    private ArticleInteractionMapper articleInteractionMapper;

    /**
     * 查询点赞记录是否存在
     * @param type 操作类型
     * @param typeId 操作的主键id
     * @param userId 用户id
     * @return 存在返回1，不存在返回0
     */
    public int starIsExist(int type,int typeId,int userId){
        if (articleInteractionMapper.starIsExist(typeId, type, userId))
            return 1;
        else return 0;

    }

    /**
     * 新增一条点赞记录
     * @param type 操作类型
     * @param typeId 操作的主键id
     * @param userId 用户id
     * @return 返回操作成功与否
     */
    public boolean starInsert(int type,int typeId,int userId){
        //如果已经存在该点赞记录，就返回结果，不执行下一步
        if (articleInteractionMapper.starIsExist(typeId, type, userId))
            return true;

        articleInteractionMapper.starInsert(typeId,type,userId);
        int starNum = articleInteractionMapper.starNumQuery(typeId,type);

        switch (type){
            case 1://点赞类型为文章
            {
                return articleInteractionMapper.starNumUpdate(starNum, typeId);
            }
            case 2://点赞类型为评论
            {
                return articleInteractionMapper.commentStarNumUpdate(starNum, typeId);
            }
            case 3://点赞类型是回复
            {
                return articleInteractionMapper.replyStarNumUpdate(starNum, typeId);
            }

        }
        return false;

    }

    /**
     *  删除一条点赞记录
     * @param typeId
     * @param type
     * @param userId
     * @return
     */
    public boolean starDelete(int typeId,int type,int userId){
        //先在点赞表中删除一条点赞
        articleInteractionMapper.starDelete(typeId, type,userId);

        int starNum = articleInteractionMapper.starNumQuery(typeId, type);

        //把点赞数更新到相应类型的记录上
        switch (type){
            case 1://点赞类型为文章
            {
                return articleInteractionMapper.starNumUpdate(starNum, typeId);
            }
            case 2://点赞类型为评论
            {
                return articleInteractionMapper.commentStarNumUpdate(starNum, typeId);
            }
            case 3://点赞类型是回复
            {
                return articleInteractionMapper.replyStarNumUpdate(starNum, typeId);
            }

        }
        return false;
    }

    /**
     * 查询是否存在该收藏记录
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回是否存在
     */
    public boolean collectionIsExist(int articleId,int userId){

        return articleInteractionMapper.collectionIsExist(articleId, userId);
    }

    /**
     *  新增一条收藏记录
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回操作成功与否
     */
    public boolean collectionInsert(int articleId,int userId){
        //如果已经存在该收藏，就直接返回结果
        if (articleInteractionMapper.collectionIsExist(articleId, userId))
            return  true;
        //先加入收藏
        articleInteractionMapper.collectionInsert(articleId, userId);

        int collectionNum = articleInteractionMapper.collectionNumQuery(articleId);
        //把记录的收藏数更新到文章表的收藏数上
        return articleInteractionMapper.collectionNumUpdate(articleId, collectionNum);
    }

    /**
     * 删除一条收藏记录
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回操作是否成功
     */
    public boolean collectionDelete(int articleId,int userId){
        articleInteractionMapper.collectionDelete(articleId, userId);

        int collectionNum = articleInteractionMapper.collectionNumQuery(articleId);

        return articleInteractionMapper.collectionNumUpdate(articleId, collectionNum);
    }

    /**
     *  查询分享是否存在
     * @param articleId 文章id
     * @param userId 用户id
     * @return 返回是否存在的布尔值
     */
    public boolean shareIsExist(int articleId, int userId){
        return articleInteractionMapper.shareIsExist(articleId, userId);
    }

    public boolean shareInsert(int articleId, int userId){
        if (articleInteractionMapper.shareIsExist(articleId, userId))
            return true;

        articleInteractionMapper.shareInsert(articleId, userId);

        int shareNum = articleInteractionMapper.shareNumQuery(articleId);

        return articleInteractionMapper.shareNumUpdate(articleId, shareNum);
    }

    /**
     * 删除一条转发记录
     * @param articleId 文章id
     * @param userId 用户id
     * @return 操作是否成功
     */
    public boolean shareDelete(int articleId,int userId){
        articleInteractionMapper.shareDelete(articleId, userId);

        int shareNum = articleInteractionMapper.shareNumQuery(articleId);

        return articleInteractionMapper.shareNumUpdate(articleId, shareNum);

    }


}
