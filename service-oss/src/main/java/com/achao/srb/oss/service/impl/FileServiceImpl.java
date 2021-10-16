package com.achao.srb.oss.service.impl;

import com.achao.srb.oss.service.FileService;
import com.achao.srb.oss.utils.OSSProperties;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(InputStream inputStream, String module, String fileName) {


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(
                OSSProperties.ENDPOINT,
                OSSProperties.KEY_ID,
                OSSProperties.KEY_SECRET);

        if (!ossClient.doesBucketExist(OSSProperties.BUCKET_NAME)) {
            // 创建存储空间。
            ossClient.createBucket(OSSProperties.BUCKET_NAME);
            // 设置存储空间的访问权限为私有。
            ossClient.setBucketAcl(OSSProperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }

        //构建日期时间
        String folder = new DateTime().toString("/yyyy/MM/dd/");
        //文件名
        fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        //文件根路径
        String key = module + folder + fileName;

        ossClient.putObject(OSSProperties.BUCKET_NAME, key, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        return "https://" + OSSProperties.BUCKET_NAME + "." + OSSProperties.ENDPOINT + "/" + key;
    }

    @Override
    public void removeFile(String url) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(
                OSSProperties.ENDPOINT,
                OSSProperties.KEY_ID,
                OSSProperties.KEY_SECRET);

        String host = "https://" + OSSProperties.BUCKET_NAME + "." + OSSProperties.ENDPOINT + "/";
        String objectName = url.substring(host.length());
        System.out.println(objectName);
        // 删除文件或目录。如果要删除目录，目录必须为空。
        ossClient.deleteObject(OSSProperties.BUCKET_NAME, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
