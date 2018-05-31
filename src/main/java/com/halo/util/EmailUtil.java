package com.halo.util;

import com.halo.config.properties.Email;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

/**
 * email发送工具
 *
 * @author MelloChan
 * @date 2018/5/31
 */
public class EmailUtil {

    private EmailUtil() {
    }

    private static Session config() throws GeneralSecurityException {
        Properties props = new Properties();
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.163.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        //基本的参数协议
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        //基于SSL的邮箱安全协议 163邮箱似乎不用？
        return Session.getInstance(props);
    }

    public static void sendEmail(Email config, String message, String destination) throws GeneralSecurityException, MessagingException {
        Session session = config();
        Message msg = new MimeMessage(session);
        msg.setSubject(new Date() + " 用户中心安全服务");
        msg.setText(message);
        msg.setFrom(new InternetAddress(config.getEmail()));
        Transport transport = session.getTransport();
        transport.connect(config.getStmp(), config.getEmail(), config.getPassword());
        transport.sendMessage(msg, new Address[]{new InternetAddress(destination)});
        transport.close();
    }

}
