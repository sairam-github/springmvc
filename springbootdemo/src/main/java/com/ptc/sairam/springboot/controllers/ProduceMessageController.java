package com.ptc.sairam.springboot.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.RequestTelemetry;
import com.microsoft.applicationinsights.web.internal.RequestTelemetryContext;
import com.microsoft.applicationinsights.web.internal.ThreadContext;
import com.microsoft.applicationinsights.web.internal.correlation.TelemetryCorrelationUtils;

@RestController
public class ProduceMessageController {
	@Autowired
	private JmsTemplate jmstemplate;
	
	@Autowired
	private Queue queue;
	
	@Autowired
	TelemetryClient telemetry;
	
	@GetMapping("/publishmessage")
	public String PublishMessage() {
		
		RequestTelemetryContext context = ThreadContext.getRequestTelemetryContext();
		RequestTelemetry requestTelemetry = context.getHttpRequestTelemetry();
		requestTelemetry.getContext().getCloud().setRole("springbootdemo");
		try {
			requestTelemetry.getContext().getCloud().setRoleInstance(InetAddress.getLocalHost().getCanonicalHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String, String> jmsmessage = new HashMap<String, String>();
		jmsmessage.put("Id", requestTelemetry.getContext().getOperation().getId());
		jmsmessage.put("parentId", TelemetryCorrelationUtils.generateChildDependencyId());
		
		jmstemplate.convertAndSend(queue, jmsmessage);
		
		return "Message is published successfully"; 
	}
}
