package com.alexgim.sharing.common;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class S3Component {
    @Value("${bucket}")
    private String bucket;

    private final AmazonS3 s3;


    public String saveFile(MultipartFile file) {
        String filename = "testS3/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        File convertFile = null;
        try {
            convertFile = convert(file);
            s3.putObject(bucket, filename, convertFile);
            convertFile.delete();                          // 로컬에 저장된 파일 지우기
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return s3.getUrl(bucket, filename).toString();
        }
    }


    public byte[] downloadFile(String filename) {
        S3Object object = s3.getObject(bucket, filename);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String deleteFile(String filename) {
        s3.deleteObject(bucket, filename);
        return "file deleted";
    }


    public List<String> listAllFile() {
        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucket);
        return listObjectsV2Result.getObjectSummaries().stream().map(o -> o.getKey()).collect(Collectors.toList());
    }

    private File convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertFile);    // fileOutputStream 기능이 뭐지?
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }
}
