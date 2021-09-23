package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommodityService;


public class ShowItemImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        /**************图片路径***************/
		String picFolder= "C:/webImage/itemImg/commodity/";
        String itemId = request.getParameter("itemId");
        String fileName = picFolder + itemId + ".jpg";
        CommodityService service = new CommodityService();
        String imgURL = service.findCommodity(Integer.parseInt(itemId)).getCommodityImageURL();
        if(imgURL==null)  //图片名
            fileName = "C:/webImage/itemImg/commodity/default.jpg";

        String mimeType = "image/jpeg";
        //设置content类型
        response.setContentType(mimeType);
        //设置大小
        File file = new File(fileName);
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
