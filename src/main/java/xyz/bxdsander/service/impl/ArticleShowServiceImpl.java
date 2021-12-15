package xyz.bxdsander.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import xyz.bxdsander.bean.Article;
import xyz.bxdsander.bean.ArticleComment;
import xyz.bxdsander.bean.ArticleReply;
import xyz.bxdsander.bean.User;
import xyz.bxdsander.dao.ArticleInteractionMapper;
import xyz.bxdsander.dao.ArticleShowMapper;
import xyz.bxdsander.dao.UserMapper;
import xyz.bxdsander.service.ArticleInteractionService;
import xyz.bxdsander.service.ArticleShowService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ArticleShowService
 * @Direction: 文章显示的业务逻辑处理
 * @Author: Sander
 * @Date 2021/9/27 10:39
 * @Version 1.0
 **/
public class ArticleShowServiceImpl implements ArticleShowService {
    @Autowired
    Article article;
    @Autowired
    ArticleShowMapper articleShowMapper;
    @Autowired
    ArticleInteractionMapper articleInteractionMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleInteractionService articleInteractionService;

    /**
     *  获取文章的信息
     * @param articleId 作者id
     * @return  返回文章Article对象
     */
    public Article getArticleInfo(int articleId){
        Article articleInfo;

        User user ;

        articleInfo = articleShowMapper.queryArticleByArticleId(articleId);

        user = null;

        user = userMapper.userInfoByUserId(articleInfo.getAuthorId());

        articleInfo.setAuthorImg(user.getPortrait());
        articleInfo.setAuthorNickName(user.getNickName());

        return articleInfo;
    }

    /**
     *  增加一条评论
     * @param articleComment 文章评论
     * @return 返回操作是否成功
     */
    public boolean commentInsert(ArticleComment articleComment){
        //插入一条评论
        articleShowMapper.commentInsert(articleComment);
        //记录下该评论的的文章下的评论总数
        int commentNum = articleInteractionMapper.commentNumQuery(articleComment.getArticleId());
        //更新该文章的评论总数
        return articleInteractionMapper.commentNumUpdate(commentNum, articleComment.getArticleId());
    }

    /**
     *  查询评论数据的总条数
     * @param articleId 文章id
     * @return 返回查询到的数据总数
     */
    public int getCommentTotalCount(int articleId){
        return articleInteractionMapper.commentNumQuery(articleId);
    }

    /**
     * 分页查询评论
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param userId 用户id
     * @return 返回数据集合
     */
    public List<ArticleComment> queryCommentByPage(int currentPage , int pageSize , int userId){
        List<ArticleComment> articleComments1;
        List<ArticleComment> articleComments = new ArrayList<ArticleComment>();

        User user ;

        articleComments1 = articleShowMapper.queryCommentByPage(currentPage, pageSize, userId);

        for(ArticleComment articleComment : articleComments1){
            user = null;

            user = userMapper.userInfoByUserId(userId);

            articleComment.setUserCommentImg(user.getPortrait());
            articleComment.setUserCommentNickName(user.getNickName());
            //记录当前用户对应评论的的点赞互动情况
            articleComment.setStarStatus(articleInteractionService.starIsExist(2, articleComment.getCommentId(), userId));

            articleComments.add(articleComment);
        }

        return articleComments;
    }

    /**
     * 增加一条回复
     * @param articleReply 文章回复
     * @return 返回操作成功与否
     */
    public boolean replyInsert(ArticleReply articleReply){
        //找到要评论的用户的id
        articleReply.setUserReplyToId(articleShowMapper.getUserReplyToId(articleReply));
        //插入一条评论
        return articleShowMapper.replyInsert(articleReply);
    }

    /**
     * 对于回复的回复的增加
     * @param articleReply  文章的回复
     * @return 操作成功与否
     */
    public boolean reply_replyInsert(ArticleReply articleReply){
        return articleShowMapper.replyInsert(articleReply);
    }

    /**
     * 组装返回回复对象的数据集合
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param articleId 文章id
     * @return articleReply--数据集合
     */
    public List<ArticleReply> queryReplyByComment(int currentPage , int pageSize ,int articleId){
        List<ArticleComment> articleComments ;
        //查询数据库的返回结果
        List <ArticleReply> articleReplysBack ;

        //拿到回复的数据集合
        List <ArticleReply> articleReplys = new ArrayList<ArticleReply>() ;
        //通过评论的id进行数据集合的筛选--过滤到只剩符合评论id的回复对象的数据集合
        //先拿到评论对象的信息
        articleComments = articleShowMapper.queryCommentByPage(currentPage, pageSize ,articleId);
        //进行数据的拼接--两个for循环--先在articleComments集合中进行循环
        for( ArticleComment articleComment : articleComments )
        {
            //在回复表中找到符合条件的评论id--返回数据库中的查询结果
            articleReplysBack = articleShowMapper.queryReplyByComment(articleComment) ;
            if ( articleReplysBack != null )
            {
                //拿到相对应的用户昵称
                for( ArticleReply articleReply : articleReplysBack )
                {
                    //拿到一个过滤之后的装有对应的comment_id的articleComment对象集合
                    if( articleReply.getCommentId()==articleComment.getCommentId() )
                    {
                        //先通过articleReply的user_id找到相对应的用户对象，再通过对象.属性得到值，最后放到articleReply对象中
                        articleReply.setUserReplyFromNickName( userMapper.userInfoByUserId( articleReply.getUserReplyFromId() ).getNickName()  ); ;
                        articleReply.setUserReplyToNickName( userMapper.userInfoByUserId( articleReply.getUserReplyToId()).getNickName() );
                        //得到发出回复的人的头像
                        articleReply.setUserReplyImg( userMapper.userInfoByUserId( articleReply.getUserReplyFromId() ).getPortrait() );
                        //将符合条件的数据放在articleReply中
                        articleReplys.add( articleReply ) ;
                    }
                }
            }

        }
        return articleReplys;
    }
}
