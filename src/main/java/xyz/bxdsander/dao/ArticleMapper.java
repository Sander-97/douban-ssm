package xyz.bxdsander.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import xyz.bxdsander.bean.Article;

/**
 * @InterfaceName ArticleDao
 * @Direction:
 * @Author: Sander
 * @Date 2021/9/16 9:03
 * @Version 1.0
 **/
@Mapper
@Repository
public interface ArticleMapper {
    /**
     *  新增一篇文章到数据库---新增一篇文章
     * @param article article表对应的实体类
     * @return 返回刚插入数据库中的文章的id，如果失败就返回-1
     */
   int  articleInsert(Article article);

    /**
     * 查询编辑过的文章--查找文章
     * @param article article表对应的实体类
     * @return 返回所查询的数据中的文章的id----如果没有就返回-1
     */
    int articleQuery(Article article);

    /**
     *  更新一篇文章记录，在a_article表中更新
     * @param article article表对应的实体类
     * @return 返回操作是否成功
     */
    boolean articleUpdate(Article article);

    /**
     * 在a_tag表中查询是否有该条记录
     * @param tagName 用户所输入的标签名
     * @return 如果成功就返回该条记录的主键，失败就返回-1
     */
    int tagIsExist(String tagName);

    /**
     *  在a_tag表中插入一条记录
     * @param tagName 用户输入的标签tag名字
     * @return 成功就返回该条记录的主键id，失败就返回 -1
     */
    int tagInsert(String tagName);

    /**
     *  在article-to-tag表中插入一条新的记录
     * @param articleId 文章id
     * @param tagId 文章的分类标签id
     * @return 返回操作成功与否
     */
    boolean middleInsert(@Param("articleId") int articleId, @Param("tagId") int tagId);

    /**
     *  在Article-to-tag的表中删除一条符合articleId的记录
     * @param articleId 文章的id
     * @return 返回操作成功与否
     */
    boolean middleDelete(int articleId);

    /**
     *  删除---在article表中删除所有关于文章id的一条纪录
     * @param articleId 文章id
     * @return 返回操作成功与否
     */
    boolean articleDelete(int articleId);

}
