package service;

import org.springframework.web.multipart.MultipartFile;
import util.PropertyApp;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageServiceImpl implements ImageService {

    private PropertyApp propertyApp = new PropertyApp();

    @Override
    public void save(MultipartFile file, String uploadFolder, String fileName) {
        uploadFolder = propertyApp.getPathStorage() + uploadFolder;
        System.out.println("UPLOAD_FOLDER - " + uploadFolder);
        if(! new File(uploadFolder).exists())
        {
            new File(uploadFolder).mkdir();
        }
        String orgName = file.getOriginalFilename();

        Pattern pattern = Pattern.compile(".(png|jpg|jpeg)$");
        Matcher matcher = pattern.matcher(orgName);

        if(matcher.find()){
            String typeFile = matcher.group(0);
            String filePath = uploadFolder + fileName + ".png";
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
