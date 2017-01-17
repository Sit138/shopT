package service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    void save(MultipartFile file, String uploadFolder, String fileName);

    void save(List<MultipartFile> files, String uploadFolder);

}
