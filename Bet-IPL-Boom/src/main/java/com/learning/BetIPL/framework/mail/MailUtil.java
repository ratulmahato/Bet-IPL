package com.learning.BetIPL.framework.mail;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.learning.BetIPL.framework.common.StringUtil;




public enum MailUtil {
    INSTANCE;

    private Properties properties = null;
    private String smtpUser = null;
    private String smtpPassword = null;

    /**
     * MailUtil
     */
    private MailUtil() {

        properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        smtpUser = "neerajkumar.c1993@gmail.com";
        smtpPassword = "9955335407";

        // properties.put("mail.smtp.host", PropertyUtil.INSTANCE.getProperty("mail.smtp.host"));
        // properties.put("mail.smtp.port", PropertyUtil.INSTANCE.getProperty("mail.smtp.port"));
        // properties.put("mail.transport.protocol", PropertyUtil.INSTANCE.getProperty("mail.transport.protocol"));
        // properties.put("mail.smtp.auth", "true");
        // properties.put("mail.smtp.starttls.enable", "true");

        // properties.put("mail.smtp.connectiontimeout", "10000");
        // properties.put("mail.smtp.timeout", "10000");
        // ----------------------------------------------------------------------------------
        // smtpUser = PropertyUtil.INSTANCE.getProperty("mail.smtp.user");
        // smtpPassword = PropertyUtil.INSTANCE.getProperty("mail.smtp.password");

    }

    /**
     * @param message
     *            - the MailMessage
     * @throws Exception
     *             - the Exception
     */
    public void sendMail(MailMessage message) throws Exception {

        String to = message.getToAddress();
        String cc = message.getCcAddress();
        String bcc = message.getBccAddress();

        String subject = message.getSubject();
        String body = message.getBody();
        String attachmentMimeType = message.getAttachmentMimeType();
        String env = message.getEnv();
        String containerName = message.getContainerName();
        String remoteFileName = message.getRemoteFileName();
        String fileNameDisplay = message.getFileNameDisplay();

        Multipart multipart = new MimeMultipart();

        addBody(body, multipart);

        InputStream is = null;
        BufferedInputStream bis = null;
        if (message.isAttached()) {

            // if (StringUtil.equals(env, "LOCAL")) {
            // is = new ByteArrayInputStream(message.getLocalFileData());
            // } else {
            // is = AzureStorageUtil.INSTANCE.getObject(containerName, remoteFileName);
            // }
            //
            // bis = new BufferedInputStream(is);
            // attachFile(bis, fileNameDisplay, attachmentMimeType, multipart);
        }

        try {
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(smtpUser, smtpPassword);
                }
            });

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(smtpUser));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            if (StringUtil.isNotEmpty(cc)) {
                msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            }
            if (StringUtil.isNotEmpty(bcc)) {
                msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
            }

            msg.setSubject(subject);
            msg.setContent(multipart);

            Transport.send(msg);
        } finally {
            close(bis);
            close(is);
        }

        return;
    }

    // --------------------------------------------------------------------------------------------------------
    /**
     * @param body
     *            - the String
     * @param multipart
     *            - the Multipart
     * @throws MessagingException
     *             - the MessagingException
     */
    private void addBody(String body, Multipart multipart) throws MessagingException {

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html");
        multipart.addBodyPart(messageBodyPart);
    }

    /**
     * @param bis
     *            - the BufferedInputStream
     * @param fileName
     *            - the String
     * @param mimeType
     *            - the String
     * @param multipart
     *            - the Multipart
     * @throws Exception
     *             - the Exception
     */
    private void attachFile(BufferedInputStream bis, String fileName, String mimeType, Multipart multipart) throws Exception {

        if (null == bis) {
            return;
        }

        if (null == fileName || fileName.isEmpty()) {
            return;
        }

        if (null == mimeType || mimeType.isEmpty()) {
            return;
        }

        DataSource dataSource = null;
        dataSource = new ByteArrayDataSource(bis, mimeType);

        MimeBodyPart attachBodyPart = new MimeBodyPart();
        attachBodyPart.setDataHandler(new DataHandler(dataSource));
        attachBodyPart.setFileName(fileName);
        multipart.addBodyPart(attachBodyPart);
    }

    /**
     * @param closeable
     *            - the Closeable
     */
    private static void close(Closeable closeable) {

        if (null == closeable) {
            return;
        }

        try {
            closeable.close();
        } catch (Exception e) {
            // Do Nothing
        }
    }
}
