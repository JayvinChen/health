package com.itheima.quartz;

import com.itheima.dao.PackageDao;
import com.itheima.utils.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 * @author Jayvin
 * @date 2019/9/26 19:55
 */
public class RemoveImgFromQiniu {
    @Autowired
    private PackageDao packageDao;

    /**
     * @Description: 删除七牛云的垃圾图片
     * @Param: []
     * @return: void
     */
    public void removeImgs() {
        // 上次更新时间
        Date lastUpdateTime = packageDao.getUpdateTime();
        if (null == lastUpdateTime) {
            // 如果为空，则取第一次上传图片的时间
            lastUpdateTime = QiNiuUtil.getFirstTime();
        }
        // 七牛云服务器的所有新增图片
        List<String> fileNames = QiNiuUtil.getFileNameList(lastUpdateTime);
        // 数据库的所有新增图片
        List<String> DBImgnames = packageDao.getImgnames(lastUpdateTime);
        // 两个集合相减，结果就是垃圾图片
        fileNames.removeAll(DBImgnames);
        // 调用方法删除垃圾图片
        if (fileNames.size() > 0) {
            QiNiuUtil.removeFiles(fileNames.toArray(new String[]{}));
        }

        //更新成功则添加更新时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        packageDao.updateTime(dateFormat.format(new Date()));
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring_quartz_remove.xml");
    }
}
