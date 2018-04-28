package com.hym.wxtest.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * OOS文件存储的工具类
 */
@Configuration
public class OssClientUtil {
    private static Logger logger = LoggerFactory.getLogger(OssClientUtil.class);

    //存储空间名称
    private static String bucketName;
    // 文件存储目录
    private static String fileDir;
    // 存储实例
    private static OSSClient ossClient;

    @Value("${oos.bucket_name}")
    public void setBucketName(String bucketName) {
        OssClientUtil.bucketName = bucketName;
    }

    @Value("${oos.file_dir}")
    public void setFileDir(String fileDir) {
        OssClientUtil.fileDir = fileDir;
    }

    @Autowired
    @Qualifier("oosClient")
    public void setOssClient(OSSClient ossClient) {
        OssClientUtil.ossClient = ossClient;
    }

    /**
     * 上传文件
     *
     * @param url
     */
    public static void uploadFile(String url) throws Exception {
        File fileOnServer = new File(url);
        FileInputStream fin;
        try {
            fin = new FileInputStream(fileOnServer);
            String[] split = url.split("/");
            uploadFile2OSS(fin, split[split.length - 1]);
        } catch (FileNotFoundException e) {
            logger.error("uploadFile={}", e);
            throw new Exception("图片上传失败01");
        }
    }

    /**
     * 上传文件
     * @param file
     * @return
     * @throws Exception
     */
    public static String uploadFile(MultipartFile file) throws Exception {
        if (file.getSize() > 1024 * 1024) {
            logger.error("uploadFile={}", "上传图片大小不能超过1M！");
            throw new Exception("上传图片大小不能超过1M！");
        }
        // 获取文件名
        String originalFilename = file.getOriginalFilename();
        // 避免文件名重复 uuid_文件名
        originalFilename = UUID.randomUUID().toString() + "_" + originalFilename;
        // 添加文件所在目录
        String hash = Integer.toHexString(originalFilename.hashCode());
        String fileUrl = fileDir;
        for(char c : hash.toCharArray()){
            fileUrl += "/" + c;
        }
        originalFilename = fileUrl + "/" + originalFilename;
        try {
            InputStream inputStream = file.getInputStream();
            uploadFile2OSS(inputStream, originalFilename);
            // 获取路径
            return getFileUrl(originalFilename);
        } catch (Exception e) {
            logger.error("uploadFile={}", e.getMessage());
            throw new Exception("文件上传失败");
        }
    }

    /**
     * 获得图片url链接
     * @param fileName
     * @return
     */
    private static String getFileUrl(String fileName) {
        if (!StringUtils.isEmpty(fileName)) {
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
            // 生成URL
            URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
            if (url != null) {
                return url.toString();
            }
        }
        return null;
    }

    /**
     * 上传到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param inputStream 文件流
     * @param fileName 文件名称 包括后缀名
     * @return 出错返回"" ,唯一MD5数字签名
     */
    private static String uploadFile2OSS(InputStream inputStream, String fileName) {
        String ret = "";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            PutObjectResult putResult = ossClient.putObject(bucketName, fileName, inputStream, objectMetadata);
            ret = putResult.getETag();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     * @param FilenameExtension 文件后缀
     * @return String
     */
    public static String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }
}
