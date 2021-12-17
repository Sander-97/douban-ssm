package xyz.bxdsander.service;

import xyz.bxdsander.bean.Article;
import xyz.bxdsander.bean.ArticleComment;
import xyz.bxdsander.bean.ArticleReply;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ArticleShowService
 * @Direction: 文章显示的业务逻辑处理
 * @Author: Sander
 * @Date 2021/9/27 10:39
 * @Version 1.0
 **/
public interface ArticleShowService {


    /**
     *  获取文章的信息
     * @param articleId 作者id
     * @return  返回文章Article对象
     */
    Article getArticleInfo(int articleId);

    /**
     *  增加一条评论
     * @param articleComment 文章评论
     * @return 返回操作是否成功
     */
    boolean commentInsert(ArticleComment articleComment);

    /**
     *  查询评论数据的总条数
     * @param articleId 文章id
     * @return 返回查询到的数据总数
     */
    int getCommentTotalCount(int articleId);

    /**
     * 分页查询评论
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param userId 用户id
     * @return 返回数据集合
     */
    List<ArticleComment> queryCommentByPage(int currentPage , int pageSize , int userId);

    /**
     * 增加一条回复
     * @param articleReply 文章回复
     * @return 返回操作成功与否
     */
    boolean replyInsert(ArticleReply articleReply);

    /**
     * 对于回复的回复的增加
     * @param articleReply  文章的回复
     * @return 操作成功与否
     */
    boolean reply_replyInsert(ArticleReply articleReply);

    /**
     * 组装返回回复对象的数据集合
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param articleId 文章id
     * @return articleReply--数据集合
     */
    List<ArticleReply> queryReplyByComment(int currentPage , int pageSize ,int articleId);
}
