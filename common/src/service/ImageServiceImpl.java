package service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    @Override
    public List<File> fileList(String path) {
        List<File> files = Collections.emptyList();
        try {
            files = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    @Override
    public List<String> fileNameList(String path) {
        List<String> nameList = Collections.emptyList();
        try {
            nameList = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .map(File::getName)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameList;
    }

    @Override
    public void delete(String path) {
        List<File> files = fileList(path);
        if (files != null){
            files.stream().filter(File::isFile).forEach(File::delete);
        }
        new File(path).delete();
    }

    @Override
    public void delete(String path, String fileName) {
        List<File> files = fileList(path);
        if(files != null){
            files.stream()
                    .filter(File::isFile)
                    .filter(f -> f.getName().equals(fileName))
                    .forEach(File::delete);
        }
        List<File> newFiles = fileList(path);
        if (newFiles != null){
            int count = 1;
            for(File file : newFiles){
                if (file.exists()){
                    file.renameTo(new File(path + count + ".png"));
                    count++;
                }
            }
        }
    }
}
