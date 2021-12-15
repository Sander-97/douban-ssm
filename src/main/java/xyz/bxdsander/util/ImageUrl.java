package xyz.bxdsander.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName ImageUrl
 * @Direction: 把得到的图片文件名，转化为图片在项目中的绝对地址 = "http://127.0.0.1/DouBanProject/image/"
 * @Author: Sander
 * @Date 2021/10/14 14:15
 * @Version 1.0
 **/
public class ImageUrl {

    private static String guide = "http://127.0.0.1/DouBanProject/image/";
    private static Map<String,String> map = new HashMap<>();

    /**
     * 把原文件的名字改为  “时间戳+随机数” + “.” + “后缀名”
     * @param fileName
     * @return lastFileName  更改后的文件名
     */
    public static String alterFileName(String fileName){
        //获取原文件的后缀名
        String suffix =fileName.substring(fileName.lastIndexOf(".") + 1);
        //修改后的文件名前缀
        String prefix = generateSuffix();

        //beginIndex: 索引值加1，意味着从 “.” 的后一位元素开始，endIndex： 没有写结束索引值意味着获取到最后一个元素为止

        /**
         * 更改之后的文件名
         */
        String lastFileName = prefix + "." + suffix;
        return lastFileName;

    }

    /**
     *  字符串拼接  加上原本的项目名实录------- "http://47.102.212.18/iDouBan" + "时间戳" + "随机数" + "." + "文件后缀名"
     * @param fileName 原本的文件名
     * @return 文件名和文件存放地址
     */
    public static HashMap<String,String> imgUrl(String fileName){

        //使用前先清空
        map.clear();
        /**
         * 调用更改原文件名的方法
         */
        String lastFileName = alterFileName(fileName);

        /**
         * 相片的绝对地址----虚拟路径，对应磁盘上文件的存储路径
         */

        String imgUrl = guide + lastFileName;

        map.put(lastFileName, imgUrl);

        return (HashMap<String, String>) map;

    }


    /**
     * 获得时间戳+随机数的文件名前缀
     * @return 文件名前缀
     */
    public static String generateSuffix(){
        //获得当前的时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        //转化为字符串
        String formatDate = format.format(new Date());

        //随机生成文件编号

        int random = new Random().nextInt(10000);

        return new StringBuffer().append(formatDate).append(random).toString();

    }
}
