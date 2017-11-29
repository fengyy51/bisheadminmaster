package com.binwang.util;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.binwang.bean.config.OssBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by owen on 17/5/8.
 */
public class OssUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(OssUtil.class);


    // 简单上传
    public static void objectUp(OssBean oss, MultipartFile file, String name) throws IOException {
        String accessKeyId = oss.getAccessKeyId();
        String accessKeySecret = oss.getAccessKeySecret();
        String endPoint = oss.getEndPoint();
        String bucketName = oss.getBucketName();

        // 初始化OSSClient
        OSSClient client = new OSSClient(endPoint, accessKeyId, accessKeySecret);

        // 获取指定文件的输入流
        InputStream content = file.getInputStream();

        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();

        // 必须设置ContentLength
        meta.setContentLength(file.getSize());

        // 上传Object.
        PutObjectResult result = client.putObject(bucketName, name, content, meta);

        client.shutdown();

    }

    // 断点上传
    public static void multiUp(OssBean oss, MultipartFile file, String name) throws IOException {
        String accessKeyId = oss.getAccessKeyId();
        String accessKeySecret = oss.getAccessKeySecret();
        String endPoint = oss.getEndPoint();
        String bucketName = oss.getBucketName();

        OSSClient client = new OSSClient(endPoint, accessKeyId, accessKeySecret);

        InitiateMultipartUploadRequest initiateMultipartUploadRequest =
                new InitiateMultipartUploadRequest(bucketName, name);

        InitiateMultipartUploadResult initiateMultipartUploadResult =
                client.initiateMultipartUpload(initiateMultipartUploadRequest);

        final int partSize = 1024 * 1024 * 5;

        int partCount = (int) (file.getSize() / partSize);
        if (file.getSize() % partSize != 0) {
            partCount++;
        }

        List<PartETag> partETags = new ArrayList<PartETag>();

        for (int i = 0; i < partCount; i++) {
            // 获取文件流
            FileInputStream fis = (FileInputStream) file.getInputStream();

            // 跳到每个分块的开头
            long skipBytes = partSize * i;
            fis.skip(skipBytes);

            // 计算每个分块的大小
            long size = partSize < file.getSize() - skipBytes ?
                    partSize : file.getSize() - skipBytes;

            // 创建UploadPartRequest，上传分块
            UploadPartRequest uploadPartRequest = new UploadPartRequest();
            uploadPartRequest.setBucketName(bucketName);
            uploadPartRequest.setKey(name);
            uploadPartRequest.setUploadId(initiateMultipartUploadResult.getUploadId());
            uploadPartRequest.setInputStream(fis);
            uploadPartRequest.setPartSize(size);
            uploadPartRequest.setPartNumber(i + 1);
            UploadPartResult uploadPartResult = client.uploadPart(uploadPartRequest);

            partETags.add(uploadPartResult.getPartETag());

            fis.close();
        }

        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, name, initiateMultipartUploadResult.getUploadId(), partETags);

        CompleteMultipartUploadResult completeMultipartUploadResult =
                client.completeMultipartUpload(completeMultipartUploadRequest);

        client.shutdown();
    }

    //删除
    public static void deleteObject(OssBean oss, String name) {
        String accessKeyId = oss.getAccessKeyId();
        String accessKeySecret = oss.getAccessKeySecret();
        String endPoint = oss.getEndPoint();
        String bucketName = oss.getBucketName();

        OSSClient client = new OSSClient(endPoint, accessKeyId, accessKeySecret);

        client.deleteObject(bucketName, name);

        client.shutdown();
    }


    // xml上传
    public static void XMLUp(OssBean oss, BufferedInputStream content, int length, String name) throws IOException {
        String accessKeyId = oss.getAccessKeyId();
        String accessKeySecret = oss.getAccessKeySecret();
        String endPoint = oss.getEndPoint();
        String bucketName = oss.getBucketName();

        // 初始化OSSClient
        OSSClient client = new OSSClient(endPoint, accessKeyId, accessKeySecret);


        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();

        // 必须设置ContentLength
        meta.setContentLength(length);

        meta.setContentType("text/xml");

        // 上传Object.
        PutObjectResult result = client.putObject(bucketName, name, content, meta);

        client.shutdown();

    }

    // html上传
    public static void HtmlUp(OssBean oss, InputStream content, int length, String name) throws IOException {
        String accessKeyId = oss.getAccessKeyId();
        String accessKeySecret = oss.getAccessKeySecret();
        String endPoint = oss.getEndPoint();
        String bucketName = oss.getBucketName();
        // 初始化OSSClient
        OSSClient client = new OSSClient(endPoint, accessKeyId, accessKeySecret);

        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();

        // 必须设置ContentLength
        meta.setContentLength(length);

        meta.setContentType("text/html");

        // 上传Object.
        PutObjectResult result = client.putObject(bucketName, name, content, meta);

        client.shutdown();

    }

    //后台生成的二维码上传
    public static void qrUp(OssBean oss, ByteArrayOutputStream out, String name) throws IOException {
        String accessKeyId = oss.getAccessKeyId();
        String accessKeySecret = oss.getAccessKeySecret();
        String endPoint = oss.getEndPoint();
        String bucketName = oss.getBucketName();
        // 初始化OSSClient
        OSSClient client = new OSSClient(endPoint, accessKeyId, accessKeySecret);

        InputStream content = new ByteArrayInputStream(out.toByteArray());

        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();

        // 必须设置ContentLength
        meta.setContentLength(out.size());
        meta.setContentType("application/x-png");
        meta.setCacheControl("no-cache");

        // 上传Object.
        PutObjectResult result = client.putObject(bucketName, name, content, meta);

        client.shutdown();

    }

    //阿里云文件复制
    public static void copyFile(OssBean oss, String fromUrl, String toUrl) {
        String accessKeyId = oss.getAccessKeyId();
        String accessKeySecret = oss.getAccessKeySecret();
        String endPoint = oss.getEndPoint();
        String bucketName = oss.getBucketName();
        // 初始化OSSClient
        OSSClient client = new OSSClient(endPoint, accessKeyId, accessKeySecret);

        // 拷贝Object
        CopyObjectResult result = client.copyObject(bucketName, fromUrl, bucketName, toUrl);

        client.shutdown();
    }
}
