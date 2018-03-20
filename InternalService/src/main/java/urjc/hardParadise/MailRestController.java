package urjc.hardParadise;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sun.mail.smtp.SMTPTransport;
@RestController
public class MailRestController {
	
	
	@Value("${password}")
	private String password;
	
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/envioCorreo", method = RequestMethod.GET) 
	public ResponseEntity<Boolean> sendMail(@RequestParam String correo, @RequestParam String nombre) {
		
		
		
		try {

			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

			// Get a Properties object
			Properties props = System.getProperties();
			props.setProperty("mail.smtps.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.port", "465");
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.setProperty("mail.smtps.auth", "true");

			props.put("mail.smtps.quitwait", "false");

			Session session = Session.getInstance(props, null);

			final MimeMessage msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress("hardparadise.welcome@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));

			msg.setSubject("Bienvenido a HardParadise");
			msg.setText(
					"Hola " + nombre
							+ "\n\nTe damos la bienvenida a Hard paradise. Ya puedes comenzar a disfrutar de este paraíso para amantes del hardware y montajes customizados.\n\nUn cordial saludo del equipo de desarrollo.\n\nJulio Cabesetti, Jefe de departamento de redes sociales Hard-Paradise.",
					"utf-8");
			msg.setSentDate(new Date());

			SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

			t.connect("smtp.gmail.com", "hardparadise.welcome@gmail.com", password);
			t.sendMessage(msg,	 msg.getAllRecipients());
			t.close();

		} catch (MessagingException ex) {
			System.out.println(ex);
}		
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
}
}
