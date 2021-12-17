package xyz.bxdsander.service;


import xyz.bxdsander.bean.ArticleList;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName ArticleListService
 * @Direction: 对文章列表进行展示的业务逻辑处理
 * @Author: Sander
 * @Date 2021/9/27 10:38
 * @Version 1.0
 **/
public interface ArticleListService {


    /**
     *  查询数据的总条数
     * @return 总条数
     */
    int getTotalCount();

    /**
     * 分页查询所有文章
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @return 返回所有文章的数据集合
     */
    List<ArticleList> queryArticleListByPage(int currentPage, int pageSize);

    /**
     *  获取我的文章列表的数据总条数
     * @param userId 用户id
     * @return 返回数据的总数
     */
    int getMyTotalCount(int userId);
    /**
     *  分页查询我的所有文章
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param userId  用户id
     * @return 返回我所有文章的数据集合
     */
    List<ArticleList> queryMyArticleListByPage(int currentPage ,int pageSize, int userId);

    /**
     * 查询符合搜索条件的数据总数
     * @param searchContent 搜索内容
     * @return 返回数据总数
     */
    int getArticleSearchContent(String searchContent);
    /**
     * 根据搜索内容进行分页查询
     * @param currentPage 当前页
     * @param PageSize 页面大小
     * @param searchContent 搜索内容
     * @return 返回数据集合
     */
    List<ArticleList> queryArticleSearchContentByPage(int currentPage , int PageSize ,String searchContent);
    /**
     * 查询我的符合搜索内容的记录总数
     * @param authorId 作者id
     * @param searchContent 搜索内容
     * @return 返回数据总数
     */
    int getMyArticleSearchCount(int authorId , String searchContent);

    /**
     *  分页查询，根据搜索内容和我的id查询符合条件的记录
     * @param currentPage 当前页
     * @param PageSize 页面大小
     * @param searchContent 搜索内容
     * @param authorId 作者id
     * @return 返回数据记录集合
     */
    List<ArticleList> queryMyArticleSearchContentByPage(int currentPage , int PageSize ,String searchContent,int authorId);

    /**
     * 获取我的收藏总数
     * @param userId 用户id
     * @return 返回数据总数
     */
    int getMyCollectionTotalCount(int userId);

    /**
     *  分页查询我的收藏总数
     * @param currentPage 当前页
     * @param PageSize  页面大小
     * @param authorId 作者id
     * @return 返回数据集合
     */
    List<ArticleList> queryMyCollectionByPage(int currentPage , int PageSize ,int authorId);
}
