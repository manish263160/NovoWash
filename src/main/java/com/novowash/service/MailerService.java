package com.novowash.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.novowash.model.Mail;
import com.novowash.utils.GenUtilities;

@Service("mailService")
public class MailerService {

	Logger logger = Logger.getLogger(MailerService.class);

	@Autowired JavaMailSender mailSender;
	
	
	public void sendEmail(final Mail mail) {

		if (GenUtilities.isValidEmail(mail.getMailTo())) {
			Thread mailThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						MimeMessage mimeMessage = mailSender.createMimeMessage();
						MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
						mimeMessageHelper.setSubject(mail.getMailSubject());
						//mimeMessageHelper.setFrom(fqcDBProperties.getProperty(0l, "mail.from.acct"));
						mimeMessageHelper.setTo(mail.getMailTo());
						mimeMessageHelper.setText(mail.getMailContent(), true);
						mimeMessageHelper.setSentDate(new Date());
						mailSender.send(mimeMessageHelper.getMimeMessage());
					} catch (MessagingException e) {
						logger.error("MailerService : Error sending Email ", e);
					}
				}
			});
			mailThread.start();

		} else {
			logger.warn("MailerService :Invalid Email " + mail.getMailTo() + "Couldn't send email.");
		}

	}

	

}
