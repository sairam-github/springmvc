package com.ptc.sairam.springboot.config;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public NumberFormat usCurrencyFormat() {
		return NumberFormat.getCurrencyInstance(Locale.US);
	}
	
	@Bean
	public NumberFormat indiaCurrencyFormat() {
		return NumberFormat.getCurrencyInstance(Locale.ENGLISH);
	}
}
