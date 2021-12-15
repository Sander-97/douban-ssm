package xyz.bxdsander.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.bxdsander.bean.ArticleList;
import xyz.bxdsander.bean.User;
import xyz.bxdsander.dao.ArticleListMapper;
import xyz.bxdsander.service.ArticleListService;
import xyz.bxdsander.service.UserService;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName ArticleListService
 * @Direction: 对文章列表进行展示的业务逻辑处理
 * @Author: Sander
 * @Date 2021/9/27 10:38
 * @Version 1.0
 **/
public class ArticleListServiceImpl implements ArticleListService {
//    @Autowired
//   private ArticleList articleList;

    @Autowired
    private ArticleListMapper articleListMapper;

    @Autowired
    private UserService userService;


    /**
     *  查询数据的总条数
     * @return 总条数
     */
    public int getTotalCount(){
        return articleListMapper.getTotalCount();
    }

    /**
     * 分页查询所有文章
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @return 返回所有文章的数据集合
     */
    public List<ArticleList> queryArticleListByPage(int currentPage, int pageSize){
        List<ArticleList> articleLists1;
        List<ArticleList> articleLists = null;

        User user;
        //拿到文章表的信息
        articleLists1 = articleListMapper.queryArticleListByPage(currentPage, pageSize);
        for (ArticleList articleList : articleLists1 ){
            user = null;
            //拿到作者的基本信息
            user = userService.userInfoByUserId(articleList.getAuthorId());
            //把作者信息赋值
            articleList.setAuthorImg(user.getPortrait());
            articleList.setAuthorNickName(user.getNickName());

            articleLists.add(articleList);
        }

        return articleLists;
    }

    /**
     *  获取我的文章列表的数据总条数
     * @param userId 用户id
     * @return 返回数据的总数
     */
    public int getMyTotalCount(int userId){
        return articleListMapper.getMyTotalCount(userId);
    }

    /**
     *  分页查询我的所有文章
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param userId  用户id
     * @return 返回我所有文章的数据集合
     */
    public List<ArticleList> queryMyArticleListByPage(int currentPage ,int pageSize, int userId){
        List<ArticleList> articleLists1 = new ArrayList<ArticleList>();
        List<ArticleList> articleLists = new ArrayList<ArticleList>();

        User user;

        articleLists1 = articleListMapper.queryMyArticleListByPage(currentPage, pageSize, userId);

        for ( ArticleList articleList:articleLists1) {
            user = null;

            user = userService.userInfoByUserId(userId);

            articleList.setAuthorImg(user.getPortrait());
            articleList.setAuthorNickName(user.getNickName());

            articleLists.add(articleList);
        }
        return articleLists;
    }

    /**
     * 查询符合搜索条件的数据总数
     * @param searchContent 搜索内容
     * @return 返回数据总数
     */
    public int getArticleSearchContent(String searchContent){
        return articleListMapper.getArticleSearchCount(searchContent);
    }

    /**
     * 根据搜索内容进行分页查询
     * @param currentPage 当前页
     * @param PageSize 页面大小
     * @param searchContent 搜索内容
     * @return 返回数据集合
     */
    public List<ArticleList> queryArticleSearchContentByPage(int currentPage , int PageSize ,String searchContent){
        List<ArticleList> articleLists1;
        List<ArticleList> articleLists = null;

        User user = new User();
        articleLists1 = articleListMapper.queryArticleByPage(currentPage, PageSize, searchContent);
        for (ArticleList articleList: articleLists1) {
            user = null;

            user = userService.userInfoByUserId(articleList.getAuthorId());

            articleList.setAuthorNickName(user.getNickName());
            articleList.setAuthorImg(user.getPortrait());

            articleLists.add(articleList);
        }

        return articleLists;

    }

    /**
     * 查询我的符合搜索内容的记录总数
     * @param authorId 作者id
     * @param searchContent 搜索内容
     * @return 返回数据总数
     */
    public int getMyArticleSearchCount(int authorId , String searchContent){
        return articleListMapper.getMyArticleSearchCount(searchContent, authorId);
    }

    /**
     *  分页查询，根据搜索内容和我的id查询符合条件的记录
     * @param currentPage 当前页
     * @param PageSize 页面大小
     * @param searchContent 搜索内容
     * @param authorId 作者id
     * @return 返回数据记录集合
     */
    public List<ArticleList> queryMyArticleSearchContentByPage(int currentPage , int PageSize ,String searchContent,int authorId) {
        List<ArticleList> articleLists1 ;
        List<ArticleList> articleLists = null;

        User user = new User();
        articleLists1 = articleListMapper.queryMyArticleByPage(currentPage, PageSize, searchContent, authorId);
        for (ArticleList articleList : articleLists1) {
            user = null;

            user = userService.userInfoByUserId(articleList.getAuthorId());

            articleList.setAuthorNickName(user.getNickName());
            articleList.setAuthorImg(user.getPortrait());

            articleLists.add(articleList);
        }

        return articleLists;

    }

    /**
     * 获取我的收藏总数
     * @param userId 用户id
     * @return 返回数据总数
     */
    public int getMyCollectionTotalCount(int userId){
        return articleListMapper.getMyCollectionTotalCount(userId);
    }

    /**
     *  分页查询我的收藏总数
     * @param currentPage 当前页
     * @param PageSize  页面大小
     * @param authorId 作者id
     * @return 返回数据集合
     */
    public List<ArticleList> queryMyCollectionByPage(int currentPage , int PageSize ,int authorId) {
        List<ArticleList> articleLists1 ;
        List<ArticleList> articleLists = null;

        User user = new User();
        articleLists1 = articleListMapper.queryMyCollectionBytPage(currentPage, PageSize, authorId);
        for (ArticleList articleList : articleLists1) {
            user = null;

            user = userService.userInfoByUserId(articleList.getAuthorId());

            articleList.setAuthorNickName(user.getNickName());
            articleList.setAuthorImg(user.getPortrait());

            articleLists.add(articleList);
        }

        return articleLists;

    }
}
