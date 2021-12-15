package xyz.bxdsander.service;

import xyz.bxdsander.bean.Article;

/**
 * @ClassName ArticleEditService
 * @Direction: 实现文章的储存和展示的逻辑处理
 * @Author: Sander
 * @Date 2021/9/27 9:38
 * @Version 1.0
 **/
public interface ArticleEditService {

    /**
     * 新增一篇文章
     * @param article
     * @return 返回操作是否成功
     */
    public int articleInsert(Article article);

    /**
     *  如果tagName不存在，就新增一条记录到a_tag表，然后新增一条记录到中间表
     * @param tagName
     * @param articleId
     * @return 操作成功与否
     */
    public boolean articleNewTag(String tagName, int articleId);

    /**
     *  更新文章信息，同时删除中间表的记录
     * @param article
     * @return 返回操作是否成功
     */
    public boolean articleModify(Article article);

    /**
     *  删除文章
     * @param articleId
     * @return 返回操作是否成功
     */
    public boolean articleDelete(int articleId);
}
