package service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public void save(MultipartFile file, String uploadFolder, String fileName) {
        System.out.println("UPLOAD_FOLDER - " + uploadFolder);
        if(! new File(uploadFolder).exists())
        {
            new File(uploadFolder).mkdirs();
        }
        String orgName = file.getOriginalFilename();

        Pattern pattern = Pattern.compile(".(png|jpg|jpeg)$");
        Matcher matcher = pattern.matcher(orgName);

        if(matcher.find()){
            String filePath = uploadFolder + fileName + ".png";
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(List<MultipartFile> files, String uploadFolder) {
        System.out.println("UPLOAD_FOLDER - " + uploadFolder);
        if(! new File(uploadFolder).exists())
        {
            new File(uploadFolder).mkdirs();
        }
        int count = 0;
        for (MultipartFile file : files){
            count++;
            String orgName = file.getOriginalFilename();

            Pattern pattern = Pattern.compile(".(png|jpg|jpeg)$");
            Matcher matcher = pattern.matcher(orgName);

            if(matcher.find()){
                String filePath = uploadFolder + count + ".png";
                File dest = new File(filePath);
                try {
                    file.transferTo(dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
