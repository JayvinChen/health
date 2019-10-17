package com.itheima.utils;

import com.google.gson.Gson;
import com.itheima.constant.MessageConstant;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QiNiuUtil {
    private static final String ACCESSKEY = "eyyIOXMfdvyXN_D0mftFybvlI7oWUpIR3HfLTqkJ";
    private static final String SECRETKEY = "-sHtH1K0pTcHCYZvcUpEyWFzbSSrBWbUzW82XAbX";
    private static final String BUCKET = "mycloud1225";
    public static final String DOMAIN = "pybrpbjv2.bkt.clouddn.com";

    /**
     * 批量删除
     * @param filenames 需要删除的文件名列表
     * @return 删除成功的文件名列表
     */
    public static List<String> removeFiles(String... filenames){
        // 删除成功的文件名列表
        List<String> removeSuccessList = new ArrayList<String>();
        if(filenames.length > 0){
            // 创建仓库管理器
            BucketManager bucketManager = getBucketManager();
            // 创建批处理器
            BucketManager.Batch batch = new BucketManager.Batch();
            // 批量删除多个文件
            batch.delete(BUCKET,filenames);
            try {
                // 获取服务器的响应
                Response res = bucketManager.batch(batch);
                // 获得批处理的状态
                BatchStatus[] batchStatuses = res.jsonToObject(BatchStatus[].class);
                for (int i = 0; i < filenames.length; i++) {
                    BatchStatus status = batchStatuses[i];
                    String key = filenames[i];
                    System.out.print(key + "\t");
                    if (status.code == 200) {
                        removeSuccessList.add(key);
                        System.out.println("delete success");
                    } else {
                        System.out.println("delete failure");
                    }
                }
            } catch (QiniuException e) {
                e.printStackTrace();
                throw new RuntimeException(MessageConstant.PIC_UPLOAD_FAIL);
            }
        }
        return removeSuccessList;
    }

    /**
     * @param localFilePath 本地文件的路径
     * @param savedFilename 保存到服务器上的文件名称
     */
    public static void uploadFile(String localFilePath, String savedFilename){
        // 上传的管理器
        UploadManager uploadManager = getUploadManager();
        String upToken = getToken();
        try {
            Response response = uploadManager.put(localFilePath, savedFilename, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(String.format("key=%s, hash=%s",putRet.key, putRet.hash));
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            throw new RuntimeException(MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    /**
     * 通过字节流的方式上传文件
     * @param bytes  文件的字节流
     * @param savedFilename 保存到服务器上的文件名称
     */
    public static void uploadViaByte(byte[] bytes, String savedFilename){
        UploadManager uploadManager = getUploadManager();
        String upToken = getToken();
        try {
            Response response = uploadManager.put(bytes, savedFilename, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            throw new RuntimeException(MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    private static String getToken(){
        // 创建授权
        Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
        // 获得认证后的令牌
        String upToken = auth.uploadToken(BUCKET);
        return upToken;
    }

    private static UploadManager getUploadManager(){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //构建上传管理器
        return new UploadManager(cfg);
    }

    private static BucketManager getBucketManager(){
        // 创建授权信息
        Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
        // 创建操作某个仓库的管理器
        return new BucketManager(auth, new Configuration(Zone.zone2()));
    }

    //以下是自行修改的内容
    /**
     * @Description: 上传图片后的路径
     * @Param: [fileName]
     * @return: java.lang.String
     */
    public static String getPicURL(String fileName) {
        return "http://" + DOMAIN + "/" + fileName;
    }

    /**
     * @Description: 获取七牛云的图片列表
     * @Param: [lastUpdateTime]
     * @return: java.util.List<java.lang.String>
     */
    public static List<String> getFileNameList(Date lastUpdateTime) {
        BucketManager bucketManager = QiNiuUtil.getBucketManager();
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(BUCKET, "");
        List<String> fileNames = new ArrayList();
        while (fileListIterator.hasNext()) {
            FileInfo[] fileInfos = fileListIterator.next();
            for (FileInfo fileInfo : fileInfos) {
                // 七牛云图片的更新时间，毫秒值时间精度是17位(多出4位)，要把后面4位剪掉
                long putTime = Long.parseLong(String.valueOf(fileInfo.putTime).substring(0,13));
                // new Date(putTime)传入毫秒值参数，就是相应的日期时间
                if (new Date(putTime).compareTo(lastUpdateTime)>=0) {
                    // 更新时间在数据库时间之后
                    fileNames.add(fileInfo.key); //key就是文件名
                }
            }
        }
        return fileNames;
    }
    
    /** 
     * @Description: 查询第一次上传图片的时间
     * @Param: [] 
     * @return: java.util.Date 
     */ 
    public static Date getFirstTime() {
        BucketManager bucketManager = QiNiuUtil.getBucketManager();
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(BUCKET, "");

        Long firstTime = 111111111111111L;
        while (fileListIterator.hasNext()) {
            FileInfo[] fileInfos = fileListIterator.next();
            for (FileInfo fileInfo : fileInfos) {
                Long putTime = Long.parseLong(String.valueOf(fileInfo.putTime).substring(0,13));
                firstTime = Math.min(firstTime,putTime);
            }
        }

        return new Date(firstTime);
    }

    public static void main(String[] args) throws ParseException {
        // System.out.println(getFirstTime());
        Integer i1 = 127;
        Integer i2 = 127;
        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }
}
