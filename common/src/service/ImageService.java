package service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ImageService {

    void save(MultipartFile file, String uploadFolder, String fileName);

    void save(List<MultipartFile> files, String uploadFolder);

    List<File> fileList(String path);

    List<String> fileNameList(String path);

    void delete(String path);

    void delete(String path, String fileName);

}
