package GlobalVariables;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

public class SendEmail

{
	GlobalDecl GV = new GlobalDecl();

	
	public void execute() throws Exception
	{           
		String path= GV.OUTPUT_ZIP_FILE;
		String[] to={"pravin7may@gmail.com"};
		// String[] cc={"shraavan.abbanaboina@gmail.com"};
		String[] cc={} ;
		String[] bcc={};

		SendEmail.sendMail("augustusab1947@gmail.com",
				"Prison123456", // this is password.
				"smtp.gmail.com",
				"465",
				"true",
				"true",
				false,
				"javax.net.ssl.SSLSocketFactory",
				"false",
				to,
				cc,
				bcc,
				//This is Subject Line for Email.
				"Automation Test Results",

				//This is Body of the Email.
				"<p><h3><font color='Green'>This is an Auto Generated Test Results by <b> Selenium TestNG Tool </b> </font color></h3> </p>"+
				"<p><i><h4><font color='blue'>Pls DOWNLOAD or VIEW the attached html file for latest BAT Test Results.</i> </font color> </h4></p>",
				path,
			GV.OUTPUT_ZIP_FILE);
	}

	public  static boolean sendMail(String userName,
			String passWord,
			String host,
			String port,
			String starttls,
			String auth,
			boolean debug,
			String socketFactoryClass,
			String fallback,
			String[] to,
			String[] cc,
			String[] bcc,
			String subject,
			String text,
			String attachmentPath,
			String attachmentName){

		//Object Instantiation of a properties file.
		Properties props = new Properties();

		props.put("mail.smtp.user", userName);

		props.put("mail.smtp.host", host);

		if(!"".equals(port)){
			props.put("mail.smtp.port", port);
		}

		if(!"".equals(starttls)){
			props.put("mail.smtp.starttls.enable",starttls);
			props.put("mail.smtp.auth", auth);
		}

		if(debug){

			props.put("mail.smtp.debug", "true");

		}else{

			props.put("mail.smtp.debug", "false");

		}

		if(!"".equals(port)){
			props.put("mail.smtp.socketFactory.port", port);
		}
		if(!"".equals(socketFactoryClass)){
			props.put("mail.smtp.socketFactory.class",socketFactoryClass);
		}
		if(!"".equals(fallback)){
			props.put("mail.smtp.socketFactory.fallback", fallback);
		}

		try{

			Session session = Session.getDefaultInstance(props, null);

			session.setDebug(debug);

			MimeMessage msg = new MimeMessage(session);
			msg.setText(text);
			msg.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			// Fill the message
			messageBodyPart.setText(text);
			messageBodyPart.setContent(text, "text/html");


			Multipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart1 = new MimeBodyPart();
			DataSource source = new FileDataSource(attachmentPath);
			messageBodyPart1.setDataHandler(new DataHandler(source));
			messageBodyPart1.setFileName(attachmentName);
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart);


			msg.setContent(multipart);
			msg.setFrom(new InternetAddress(userName));

			for(int i=0;i<to.length;i++){
				msg.addRecipient(Message.RecipientType.TO, new
						InternetAddress(to[i]));
			}

			for(int i=0;i<cc.length;i++){
				msg.addRecipient(Message.RecipientType.CC, new
						InternetAddress(cc[i]));
			}

			for(int i=0;i<bcc.length;i++){
				msg.addRecipient(Message.RecipientType.BCC, new
						InternetAddress(bcc[i]));
			}

			msg.saveChanges();

			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, passWord);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;

		} catch (Exception mex){
			mex.printStackTrace();
			return false;
		}
	}
}