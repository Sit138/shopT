package servlet;

import util.PropertyApp;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");
        String uploadsDir = PropertyApp.PATH_AVATAR_UPLOAD;
        if(request.getParameter("avatar") != null){
            String path = uploadsDir + request.getParameter("avatar") +  "/avatar.png";
            try {
                write(path, response);
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
