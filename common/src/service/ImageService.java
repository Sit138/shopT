package service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void save(MultipartFile file, String uploadFolder, String fileName);

}
