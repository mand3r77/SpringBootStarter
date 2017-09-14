package com.uscs.sampleapp.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleAppStatsController {
	public static final Date UP_SINCE = new Date() {
		private static final long serialVersionUID = 1556544090789313892L;
		{ 
			super.setTime(new Date().getTime());
		}

	    @Override public void setYear(int year) { throw new UnsupportedOperationException();}
	    @Override public void setMonth(int month) {throw new UnsupportedOperationException();}
	    @Override public void setDate(int date) {throw new UnsupportedOperationException();}
	    @Override public void setHours(int hours) {throw new UnsupportedOperationException();}
	    @Override public void setMinutes(int minutes) {throw new UnsupportedOperationException();}
	    @Override public void setSeconds(int seconds) {throw new UnsupportedOperationException();}
	    @Override public void setTime(long time) {throw new UnsupportedOperationException();}
	};
	
	private static Logger logger = LoggerFactory
			.getLogger(SampleAppStatsController.class);

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("upSince", UP_SINCE);
		return "home";
	}
}