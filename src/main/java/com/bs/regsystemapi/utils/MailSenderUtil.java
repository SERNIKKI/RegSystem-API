package com.bs.regsystemapi.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.bs.regsystemapi.entity.Department;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.service.impl.FileService;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 发送邮件工具类
 */
public class MailSenderUtil {

    // 存在本地时的临时文件夹
    private static final String uploadFilePath =
            System.getProperty("user.dir").replaceAll("\\\\","/")
                    + "/src/main/resources/file";
    // 邮件发送协议
    private final static String PROTOCOL = "smtp";
    // SMTP邮件服务器
    private final static String HOST = "smtp.qq.com";
    // SMTP邮件服务器默认端口
    private final static String PORT = "465";
    // 是否要求身份认证
    private final static String IS_AUTH = "true";
    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
    private final static String IS_ENABLED_DEBUG_MOD = "true";
    // 账号
    private final static String fromEmilAddress = "badremilia@foxmail.com";
    // 授权码
    private final static String fromEmilAddressPassWord = "bwyxasmrzrbifbic";

    /**
     * 邮件主题
     */
    private final String subject;
    /**
     * 发件地址
     */
    private final String toEmilAddress;
    /**
     * 邮件内容
     */
    private String content;

    public MailSenderUtil(String subject, String toEmilAddress, String content){
        this.subject = subject;
        this.toEmilAddress = toEmilAddress;
        this.content = content;
    }


    /**
     * 功能描述: 发送邮件的基本配置     *
     *
     *                       授权码代替密码（更安全） 授权码的获取：进入个人邮箱，点击设置–>账户， SMTP服务选项 默认情况下这个选项是不开启的。
     *                      点击开启腾讯会进行身份验证，身份验证通过以后，会收到一个用于使用SMTP的16位口令，
     *                      验证身份的过程中把收到的口令保存下来，因为后面要使用SMTP功能必须要用到这个口令。
     *  subject       邮件主题
     *  content       邮件内容
     *  toEmilAddress 收件人地址
     * @throws Exception
     * @Title: sendEmail
     * @Description: 发送邮件工具类方法
     * @return: void
     * @since: 1.0.0 2018/11/18 19:04
     */
    public boolean sendEmail() throws MessagingException {

        if (this.toEmilAddress != null) {
            //设置基本属性
            Properties properties = new Properties();
            //设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
            properties.put("mail.smtp.host", HOST);
            properties.put("mail.transport.protocol", PROTOCOL);
            //需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
            properties.put("mail.smtp.auth", IS_AUTH);
            properties.put("mail.smtp.starttls.enable", true);
            properties.put("mail.smtp.port", PORT);
            // 设置接收超时时间
            properties.put("mail.smtp.connectiontimeout", "25000");
            // 设置读取超时时间
            properties.put("mail.smtp.timeout", "25000");
            // 设置写入超时时间
            properties.put("mail.smtp.writetimeout", "25000");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            // 使用java发送邮件的步骤如下
            //1、创建session
//            Session session = Session.getInstance(properties);
            Session session = Session.getDefaultInstance(properties, new Authenticator()
            {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmilAddress,fromEmilAddressPassWord);
                }
            });

            //开启session的调试模式，可以查看当前邮件发送状态
            session.setDebug(true);
            //创建邮件对象
            MimeMessage message = new MimeMessage(session);
            try {
                //设置发件人
                message.setFrom(new InternetAddress(fromEmilAddress));
                //设置收件人地址，以逗号隔开
                InternetAddress[] sendTo = InternetAddress.parse(this.toEmilAddress);
                message.setRecipients(MimeMessage.RecipientType.TO, sendTo);

                //加载标题
                message.setSubject(subject);
                // 设置发送时间
                message.setSentDate(new Date());
                // 设置邮件的文本内容
                // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
                Multipart multipart = new MimeMultipart();
                BodyPart contentPart = new MimeBodyPart();

                content = "亲爱的医疗云用户您好：您正在" + subject + "，您的邮箱验证码如下，" + content + "，该验证码将在一分钟之后失效，请及时使用";

                contentPart.setContent(content, "text/html;charset=utf-8");
                multipart.addBodyPart(contentPart);
                message.setContent(multipart);
                message.saveChanges(); //保存邮件
                //2、通过session获取Transport对象（发送邮件的核心API）
                Transport transport = session.getTransport(PROTOCOL);
                // 3、通过邮件用户名密码链接
                transport.connect(HOST, fromEmilAddress, fromEmilAddressPassWord);
                //5、发送电子邮件
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();//关闭连接.
                System.out.println("send success!");
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
//                return false;
                throw e;
            }
        } else {
            return false;
        }
    }

    public static boolean sendEmail(String subject, String toEmilAddress, UserEntity user, Department departmentInfo) throws MessagingException {

        if (toEmilAddress != null) {
            //设置基本属性
            Properties properties = new Properties();
            //设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
            properties.put("mail.smtp.host", HOST);
            properties.put("mail.transport.protocol", PROTOCOL);
            //需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
            properties.put("mail.smtp.auth", IS_AUTH);
            properties.put("mail.smtp.starttls.enable", true);
            properties.put("mail.smtp.port", PORT);
            // 设置接收超时时间
            properties.put("mail.smtp.connectiontimeout", "25000");
            // 设置读取超时时间
            properties.put("mail.smtp.timeout", "25000");
            // 设置写入超时时间
            properties.put("mail.smtp.writetimeout", "25000");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            // 使用java发送邮件的步骤如下
            //1、创建session
//            Session session = Session.getInstance(properties);
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmilAddress, fromEmilAddressPassWord);
                }
            });

            //开启session的调试模式，可以查看当前邮件发送状态
            session.setDebug(true);
            //创建邮件对象
            MimeMessage message = new MimeMessage(session);
            SAXReader reader = new SAXReader();
            Document document = null;
            try {
                //设置发件人
                message.setFrom(new InternetAddress(fromEmilAddress));
                //设置收件人地址，以逗号隔开
                InternetAddress[] sendTo = InternetAddress.parse(toEmilAddress);
                message.setRecipients(MimeMessage.RecipientType.TO, sendTo);

                //加载标题
                message.setSubject(subject);
                // 设置发送时间
                message.setSentDate(new Date());
                // 设置邮件的文本内容
                // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
                Multipart multipart = new MimeMultipart();
                BodyPart contentPart = new MimeBodyPart();

                //获取模板html文档
                document = reader.read(MailSenderUtil.class.getClassLoader().getResource("html/emailTemplet.html").getPath());
                Element root = document.getRootElement();
                Element name = getNodes(root,"id","name");
                Element department = getNodes(root,"id","department");
                Element secondDepartment = getNodes(root,"id","secondDepartment");
                Element userName = getNodes(root,"id","userName");
                Element password = getNodes(root,"id","password");
                Element time = getNodes(root,"id","time");
                Calendar calendar = Calendar.getInstance();
                time.setText(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
                name.setText(user.getUserRealName());
                department.setText(departmentInfo.getDepartment());
                secondDepartment.setText(departmentInfo.getSecondDepartment());
                userName.setText(user.getUserName());
                password.setText(user.getUserPassword());
                //保存到临时文件
                FileWriter fwriter = new FileWriter(uploadFilePath + "/temp.html");
                XMLWriter writer = new XMLWriter(fwriter);
                writer.write(document);
                writer.flush();
                //读取临时文件，并把html数据写入到字符串str中，通过邮箱工具发送
                FileReader in = new FileReader(uploadFilePath + "/temp.html");
                char[] buff = new char[1024*10];
                int read = in.read(buff);
                String str = new String(buff);

                contentPart.setContent(str.toString(), "text/html;charset=utf-8");
                multipart.addBodyPart(contentPart);
                message.setContent(multipart);
                message.saveChanges(); //保存邮件
                //2、通过session获取Transport对象（发送邮件的核心API）
                Transport transport = session.getTransport(PROTOCOL);
                // 3、通过邮件用户名密码链接
                transport.connect(HOST, fromEmilAddress, fromEmilAddressPassWord);
                //5、发送电子邮件
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();//关闭连接.
                System.out.println("send success!");
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
//                return false;
                throw e;
            } catch (DocumentException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 方法描述：递归遍历子节点，根据属性名和属性值，找到对应属性名和属性值的那个子孙节点。
     * @param node 要进行子节点遍历的节点
     * @param attrName 属性名
     * @param attrValue 属性值
     * @return 返回对应的节点或null
     */
    public static Element getNodes(Element node, String attrName, String attrValue) {
        final List<Attribute> listAttr = node.attributes();// 当前节点的所有属性
        for (final Attribute attr : listAttr) {// 遍历当前节点的所有属性
            final String name = attr.getName();// 属性名称
            final String value = attr.getValue();// 属性的值
            System.out.println("属性名称：" + name + "---->属性值：" + value);
            if(attrName.equals(name) && attrValue.equals(value)){
                return node;
            }
        }
        // 递归遍历当前节点所有的子节点
        final List<Element> listElement = node.elements();// 所有一级子节点的list
        for (Element e : listElement) {// 遍历所有一级子节点
            Element temp = getNodes(e,attrName,attrValue);
            // 递归
            if(temp != null){
                return temp;
            };
        }
        return null;
    }

}
