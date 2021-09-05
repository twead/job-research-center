package com.szakdolgozat.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.szakdolgozat.entity.User;
import com.szakdolgozat.exception.ApiRequestException;

@Service
public class EmailService {

	@Value("${url}")
	private String Url;
	@Value("${spring.mail.username}")
	private String mailUsername;

	private JavaMailSender javaMailSender;

	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendEmailVerificationToUserOrEmployer(User user, Boolean isEmployer) {
		String subject = "Email aktiválás!";
		String senderName = "Szakdolgozat";

		String mailContent = "<h1 text-align='center'>Kedves " + user.getName() + "!</h1>";
		mailContent += " Köszönjük, hogy regisztráltál az oldalunkra!<br>";
		mailContent += " Kérlek kattints a linkre profilod aktiválásához: " + Url + "#/activation/"
				+ user.getActivation();
		if (isEmployer)
			mailContent += "<br>Munkatársaink hamarosan felveszik veled a kapcsolatot a megadott elérhetőségeid alapján "
					+ ", hogy ellenőrizzük az adataidat. Álláshirdetéseket csak az ellenőrzés után tudsz majd közzétenni! ";
		mailContent += "<br>Üdvözlettel: <strong>Job Research Center</strong>";

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setFrom(mailUsername, senderName);
			helper.setTo(user.getEmail());
			helper.setSubject(subject);
			helper.setText(mailContent, true);
		} catch (UnsupportedEncodingException e) {
			throw new ApiRequestException("Az email küldés nem sikerült");
		} catch (MessagingException e) {
			throw new ApiRequestException("Az email küldés nem sikerült");
		}

		javaMailSender.send(message);

	}

	public void sendForgotPasswordEmail(User user) {
		String subject = "Elfelejtett jelszó!";
		String senderName = "Szakdolgozat";
		String mailContent = "<h1 text-align='center'>Kedves " + user.getName() + "!</h1>";
		mailContent += " Jelszavadat az alábbi linken tudod megváltoztatni:" + Url + "#/reset-password/"
				+ user.getResetPasswordCode() + "<br>";
		mailContent += "Amennyiben nem te használtad az elfelejtett jelszó funkciót.......!!!!";
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setFrom(mailUsername, senderName);
			helper.setTo(user.getEmail());
			helper.setSubject(subject);
			helper.setText(mailContent, true);
		} catch (UnsupportedEncodingException e) {
			throw new ApiRequestException("Az email küldés nem sikerült");
		} catch (MessagingException e) {
			throw new ApiRequestException("Az email küldés nem sikerült");
		}

		javaMailSender.send(message);

	}

}
