package servlet;

import model.enums.ImageType;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");
        Properties properties = new Properties();
        String uploadsDir = null;
        InputStream inputStream = null;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream("setting.properties");
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + "' not found in the classpath");
            }

            String imageType = request.getParameter("type");
            if(imageType != null){
                if(imageType.equalsIgnoreCase(String.valueOf(ImageType.AVATAR))){
                    uploadsDir = properties.getProperty("PATH_AVATAR_UPLOADS");
                    String path = uploadsDir + request.getParameter("id") + "/" + "avatar.png";
                    try {
                        write(path, response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e){
            System.out.println("Exception: " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void write(String path, HttpServletResponse response) throws IOException {
        byte[] buffer = new byte[1024];
        int ch;
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(path));
        BufferedOutputStream bout = new BufferedOutputStream(response.getOutputStream());
        try {
            while ((ch = bin.read(buffer)) > 0)
                bout.write(buffer, 0, ch);
        } finally {
            bin.close();
            bout.close();
        }
    }

}
