package com.learning.BetIPL.controller;

import com.learning.BetIPL.framework.mail.MailMessage;
import com.learning.BetIPL.framework.mail.MailUtil;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MailMessage ms=new MailMessage();
		ms.setToAddress("sagar.mailto@gmail.com");
		ms.setBody("Hello friend");
		ms.setSubject("Test");
		MailUtil.INSTANCE.sendMail(ms);
	}

}
