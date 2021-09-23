package controller;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Commodity;
import entity.User;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.CommodityService;


public class CommodityPostServlet extends HttpServlet {
    public static String filename = null;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                String itemName = null, type = null, desc = null, price = null, imageURL = null;
                String imgsrc = null;
                User user = (User) session.getAttribute("user");
                String uId = user.getId();
                CommodityService service = new CommodityService();
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                //items中储存了表单中的各种数据
                upload.setSizeMax(1024 * 1024 * 5);//
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();

                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    String name = item.getFieldName();
                    if (item.isFormField()) {//如果是普通表单内容（文本）
                        if (name.equals("itemName")) {
                            itemName = item.getString("UTF-8");
                            
                        }else if (name.equals("desc")) {
                            desc = item.getString("UTF-8");
                            int length = desc.length();
                            if (length > 300) {
                                out.flush();//清空缓存
                                out.println("<script>");//输出script标签
                                out.println("alert('文字内容过长');");//js语句：输出alert语句
                                out.println("history.back();");//js语句：输出网页回退语句
                                out.println("</script>");//输出script结尾标签
                            }
                         
                        } else if(name.equals("type")){
                            type = item.getString("UTF-8");
                        } else if(name.equals("price")){
                        	price = item.getString("UTF-8");
                        }
                    } else {//非普通表单内容（此处为图片）
                    	int itemId = service.findNewCommodityId() + 1;
                        filename = itemId + ".jpg";
                        System.out.println(request.getContextPath());
                        File f = new File("C:/webImage/itemImg/commodity");
                        if (!f.exists()) {
                            f.mkdir();
                        }
                        imgsrc = f + "/" + filename;

                        // 复制文件
                        InputStream is = item.getInputStream();
                        FileOutputStream fos = new FileOutputStream(imgsrc);
                        byte b[] = new byte[1024 * 1024];
                        int length = 0;
                        while (-1 != (length = is.read(b))) {
                            fos.write(b, 0, length);
                        }
                        fos.flush();
                        fos.close();
                    }
                }
                
            	Commodity cmdt = new Commodity(itemName, type, 0, Double.valueOf(price.toString()), uId, desc, imgsrc);
           	
            	if(service.addCommodity(cmdt)){
            		session.removeAttribute("cmdt");
                    out.flush();//清空缓存
                    out.println("<script>");//输出script标签
                    out.println("alert('上传成功');");//js语句：输出alert语句
                    out.println("window.location='UserInfo';");//js语句：输出网页回退语句
                    out.println("</script>");//输出script结尾标签
            	}
            	else{
            		session.removeAttribute("cmdt");
                    out.flush();//清空缓存
                    out.println("<script>");//输出script标签
                    out.println("alert('您上传的商品非法');");//js语句：输出alert语句
                    out.println("window.location='UserInfo';");//js语句：输出网页回退语句
                    out.println("</script>");//输出script结尾标签
            	}
                
                
            }
        } catch (FileUploadBase.SizeLimitExceededException e) {
            out.flush();//清空缓存
            out.println("<script>");//清空缓存
            out.println("alert('图片大小最大5M');");//js语句：输出alert语句
            out.println("history.back();");//js语句：输出网页回退语句
            out.println("</script>");//输出script结尾标签
        } catch (Exception e) {
            e.printStackTrace();
        }
		out.flush();
        out.close();
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
