package xyz.bxdsander.dao;


import xyz.bxdsander.bean.Article;
import xyz.bxdsander.bean.ArticleComment;
import xyz.bxdsander.bean.ArticleReply;

import java.util.List;

/**
 * @InterfaceName ArticleShowDao
 * @Direction: 与文章的显示相关的所有数据库操作
 * @Author: Sander
 * @Date 2021/9/16 9:02
 * @Version 1.0
 **/
public interface ArticleShowMapper {
    /**
     *  通过articleId查询Article
     * @param articleId 文章id
     * @return 返回article对象
     */
    Article queryArticleByArticleId(int articleId);

    /**
     *  插入一条评论
     * @param articleComment 文章评论（ArticleComment）对象
     * @return 返回操作成功与否
     */
    boolean commentInsert(ArticleComment articleComment);

    /**
     *  通过articleId获取文章评论的信息
     * @param articleId 文章id
     * @return 返回文章评论的ArticleComment对象
     */
    ArticleComment getArticleCommentInfo(int articleId);

    /**
     *  通过文章id查询获取评论总数---a_comment
     * @param articleId 文章id
     * @return 记录总数
     */
    int getCommentTotalCount(int articleId);

    /**
     * 通过用户定义的页面数和页面大小，文章id进行分页查询--找到符合条件的评论
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param articleId 文章id
     * @return 文章评论对象的数据集合
     */
    List<ArticleComment> queryCommentByPage(int currentPage, int pageSize, int articleId);

    /**
     * 得到接收评论用户的id，
     * @param articleReply 文章评论
     * @return 用户id
     */
    int getUserReplyToId(ArticleReply articleReply);

    /**
     *  插入一条回复
     * @param articleReply 文章回复对象
     * @return 操作成功与否
     */
    boolean replyInsert(ArticleReply articleReply);

    /**
     *  获取满足条件的articleReply对象
     * @param articleComment 评论对象
     * @return 返回articleReply对象
     */
    ArticleReply getReplyInfo(ArticleComment articleComment);

    /**
     *  通过articleComment中的comment_id进行筛选
     * @param articleComment 评论对象
     * @return articleReply对象集合
     */
    List<ArticleReply> queryReplyByComment(ArticleComment articleComment);

}
