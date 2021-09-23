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
                //items�д����˱��еĸ�������
                upload.setSizeMax(1024 * 1024 * 5);//
                List<FileItem> items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();

                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    String name = item.getFieldName();
                    if (item.isFormField()) {//�������ͨ�����ݣ��ı���
                        if (name.equals("itemName")) {
                            itemName = item.getString("UTF-8");
                            
                        }else if (name.equals("desc")) {
                            desc = item.getString("UTF-8");
                            int length = desc.length();
                            if (length > 300) {
                                out.flush();//��ջ���
                                out.println("<script>");//���script��ǩ
                                out.println("alert('�������ݹ���');");//js��䣺���alert���
                                out.println("history.back();");//js��䣺�����ҳ�������
                                out.println("</script>");//���script��β��ǩ
                            }
                         
                        } else if(name.equals("type")){
                            type = item.getString("UTF-8");
                        } else if(name.equals("price")){
                        	price = item.getString("UTF-8");
                        }
                    } else {//����ͨ�����ݣ��˴�ΪͼƬ��
                    	int itemId = service.findNewCommodityId() + 1;
                        filename = itemId + ".jpg";
                        System.out.println(request.getContextPath());
                        File f = new File("C:/webImage/itemImg/commodity");
                        if (!f.exists()) {
                            f.mkdir();
                        }
                        imgsrc = f + "/" + filename;

                        // �����ļ�
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
                    out.flush();//��ջ���
                    out.println("<script>");//���script��ǩ
                    out.println("alert('�ϴ��ɹ�');");//js��䣺���alert���
                    out.println("window.location='UserInfo';");//js��䣺�����ҳ�������
                    out.println("</script>");//���script��β��ǩ
            	}
            	else{
            		session.removeAttribute("cmdt");
                    out.flush();//��ջ���
                    out.println("<script>");//���script��ǩ
                    out.println("alert('���ϴ�����Ʒ�Ƿ�');");//js��䣺���alert���
                    out.println("window.location='UserInfo';");//js��䣺�����ҳ�������
                    out.println("</script>");//���script��β��ǩ
            	}
                
                
            }
        } catch (FileUploadBase.SizeLimitExceededException e) {
            out.flush();//��ջ���
            out.println("<script>");//��ջ���
            out.println("alert('ͼƬ��С���5M');");//js��䣺���alert���
            out.println("history.back();");//js��䣺�����ҳ�������
            out.println("</script>");//���script��β��ǩ
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
