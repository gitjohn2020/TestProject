package com.grossery.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grossery.model.HistoryMaster;
import com.grossery.model.MailContent;
import com.grossery.model.SmsContent;
import com.grossery.service.MailService;
import com.grossery.service.OtpService;
import com.grossery.service.ReadExcelService;
import com.grossery.service.UploadService;

@RestController
public class MailController {
	private static final Logger logger = Logger.getLogger(MailController.class);

	@Autowired
	private MailService service;
	@Autowired
	private UploadService upload;
	@Autowired
	private ReadExcelService read;
	@Autowired
	HistoryMaster history;
	@Autowired
	OtpService otpservice;
	@Autowired
	MailContent content;

	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
	@ResponseBody
	public String sendEmail(@RequestBody MailContent content,
			HttpServletRequest request) {

		String result = service.sendEmail(content);
		return result;
	}

	@RequestMapping(value = "/generateOtp", method = RequestMethod.POST)
	@ResponseBody
	public String generateOtp(@RequestParam(value = "key") String key,
			@RequestParam(value = "email") String mail,
			HttpServletRequest request) {
		logger.info("key is:-" + key);
		int otp = otpservice.generateOTP(key);
		String mailbody = "Your OTP is:-" + otp;
		content.setBody(mailbody);
		content.setFrom("arnabpaul830@gmail.com");
		content.setSubject("OTP for Resistration");
		content.setPassword("Tom&jerry1107");
		List<String> to = new ArrayList<>();
		to.add(mail);
		content.setTo(to);
		String result = service.sendEmail(content);
		// It returns success as response in successful case
		return result;
	}

	@RequestMapping(value = "/validateOtp", method = RequestMethod.POST)
	@ResponseBody
	public String validateOtp(@RequestParam("otpnum") int otpnum,
			@RequestParam("key") String key) {
		logger.info("key is in validate:-" + key + "---otpnum---" + otpnum);
		final String SUCCESS = "success";
		final String FAIL = "fail";
		logger.info(" Otp Number : " + otpnum);
		// Validate the Otp
		if (otpnum >= 0) {
			int serverOtp = otpservice.getOtp(key);
			logger.info("serverOtp----" + serverOtp);
			if (serverOtp > 0) {
				logger.info("i am here 1...");
				if (otpnum == serverOtp) {
					otpservice.clearOTP(key);
					logger.info("i am here 2...");
					return ("success");
				} else {
					logger.info("i am here 3...");
					return FAIL;
				}
			} else {
				logger.info("i am here 4...");
				return FAIL;
			}
		} else {
			return FAIL;
		}
	}

	@RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
	@ResponseBody
	public String sendSMS(
			@RequestParam(value = "pathOfXL", required = true) MultipartFile uploadedFile,
			@RequestParam(value = "subject") String subject,
			@RequestParam(value = "body") String body,
			HttpServletRequest request) throws IOException {

		logger.info("the output is:=" + body + "--===" + subject + "---");
		String fileLocation = upload.uploadFile(uploadedFile);
		MailContent content = null;
		try {
			content = read.readExcel(fileLocation, subject, body);
			logger.info("total Phone numbers---" + content.getTo().size());
			// for (int i = 0; i < content.getTo().size(); i++) {
			// try {
			// Thread.sleep(0);
			// logger.info("no-"+i+" "+content.getTo().get(i));
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// }
			history = service.sendSMS(content,
					uploadedFile.getOriginalFilename());
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			upload.deleteFile(fileLocation);
		}

		return "Total msg sent:-" + content.getTo().size()
				+ ".Successful messages:-" + history.getTotal_success()
				+ ".Failed Messages:-" + history.getTotal_failed();
	}

}
