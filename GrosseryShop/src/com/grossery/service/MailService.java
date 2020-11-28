package com.grossery.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.grossery.controller.MailController;
import com.grossery.dao.HistoryDao;
import com.grossery.model.HistoryMaster;
import com.grossery.model.MailContent;
import com.grossery.util.MailConfig;
import com.grossery.util.MailSenderFactory;

@Service
@Transactional
public class MailService {
	
	private static final Logger logger = Logger.getLogger(MailService.class);
	@Autowired
    JavaMailSender mailSender;
	@Autowired
	HistoryMaster history;
	@Autowired
	HistoryDao historydao;
	@Autowired
	ReadExcelService excelservice;
	
	
	
    public String sendEmail(MailContent content) {
 
    	
    	//MailContent content=(MailContent)object;
    	
        MimeMessagePreparator preparator = getMessagePreparator(content);
 
        try {
        	
            this.mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
            return "success";
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
            return "fail";
        }
    }
    
    public HistoryMaster sendSMS(MailContent content,String upload_filename){
    	logger.info("in service--------");
    	HashMap<String, String> sms_status_success= new HashMap<>();
    	HashMap<String, String> sms_status_fail= new HashMap<>();
    	for (int i = 0; i < content.getTo().size(); i++) {
			try {
				Thread.sleep(0);
				logger.info("no-"+i+" "+content.getTo().get(i));
				if(Long.parseLong(content.getTo().get(i))%3==0){
					sms_status_success.put(content.getTo().get(i), "success");
				}else{
					sms_status_fail.put(content.getTo().get(i), "Failed due to connectivity");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HHmmss");  
    	    Date date = new Date();  
    	    System.out.println();
    	    String error_file="UnsendSMS_"+formatter.format(date)+".xlsx";
    	history.setSent_date(date);
    	history.setTotal_failed(sms_status_fail.size());
    	history.setTotal_success(sms_status_success.size());
    	history.setError_file(error_file);
    	history.setSource_file(upload_filename);
    	try {
			historydao.addHistory(history);
			excelservice.writeFailedNoInExcel(error_file, sms_status_fail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e);
		}
    	return history;
    }
    
    private MimeMessagePreparator getMessagePreparator(final MailContent content) {
    	 
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
               // mimeMessage.setFrom(content.getFrom());
               
                for (int i = 0; i < content.getTo().size(); i++) {
                	System.out.println("address is: --"+content.getTo().get(i));
                	mimeMessage.addRecipient(Message.RecipientType.TO,  new InternetAddress(content.getTo().get(i)));	
				}
                mimeMessage.setText(content.getBody());
                mimeMessage.setSubject(content.getSubject());
            }
        };
        return preparator;
    }

}
