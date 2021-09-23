package controller;

import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "ShowUserImageServlet")
public class ShowUserImageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        /**************图片路径***************/
        String picFolder = "C:/webImage/photo/";
        String fileName = "default.jpg";
        if(((User)session.getAttribute("user")).getImageURL()!=null)  //图片名
            fileName = ((User)session.getAttribute("user")).getId()+".jpg";

        String mimeType = "image/jpeg";
        //设置content类型
        response.setContentType(mimeType);
        //设置大小
        File file = new File(picFolder + fileName);
        response.setContentLength((int) file.length());
        //打开文件并输出
        FileInputStream inputStream = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

        //把文件复制到输出流
        byte[] data = new byte[1024];
        int count = 0;
        while ((count = inputStream.read(data)) >= 0) {
            out.write(data, 0, count);
        }
        inputStream.close();
        out.close();
    }
}
