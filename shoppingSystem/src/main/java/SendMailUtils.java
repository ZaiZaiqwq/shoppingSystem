import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

//发送qq邮箱工具类
public class SendMailUtils {
    private static final String host="pop.qq.com";//邮箱服务器
    private static final String sendUser="1410911645@qq.com";//发件人邮箱
    private static final String sendPassWord="qlokorogyynpghii";//发件人授权码

    //发送邮件函数
    //输入：toUser-收件人邮箱，content-邮件的内容
    public static void sendEmail(String toUser,String content) {
        try {
            //发送邮件时连接邮件服务器的参数配置
            Properties props = new Properties();

            //创建参数配置
            props.setProperty("mail.smtp.auth", "true");//发送服务器需要身份验证
            props.setProperty("mail.host", host);//设置邮件服务器主机名
            props.setProperty("mail.transport.protocol", "smtp");//发送邮件协议名称

            // 设置环境信息
            Session session= Session.getInstance(props);//根据参数配置创建会话对象
            Message msg = new MimeMessage(session);//创建邮件对象

            //设置邮件标题
            msg.setSubject("Gene Knee Tie May Shopping System Reset Password");
            //设置邮件内容
            msg.setText("Your newly generated random password: "+content);
            //设置发送时间
            msg.setSentDate(new Date());
            //设置发件人
            msg.setFrom(new InternetAddress(sendUser));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toUser));//设置收件人地址
            msg.saveChanges();//保存设置
            Transport transport = session.getTransport("smtp");

            //连接邮件服务器
            transport.connect(host,sendUser,sendPassWord);
            transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));//发送邮件
            transport.close();//关闭连接
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

