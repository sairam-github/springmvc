package com.ptc.sairam.springboot.listeners;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.Duration;
import com.microsoft.applicationinsights.telemetry.RemoteDependencyTelemetry;
import com.microsoft.applicationinsights.telemetry.RequestTelemetry;
import com.microsoft.applicationinsights.web.internal.RequestTelemetryContext;
import com.microsoft.applicationinsights.web.internal.ThreadContext;
import com.microsoft.applicationinsights.web.internal.correlation.TelemetryCorrelationUtils;

@Component
public class MessageListener {
	@Autowired
	TelemetryClient telemetryClient;

	@JmsListener(destination = "ptc.webapps")
	public void GetMessage(HashMap<String, String> message) {
		//System.out.println("Received message: " + message);
		
		//String parentId = message.substring(14);
		// System.out.println("ParentId - " + parentId);
		
		// System.out.println("Child dependency id - " + TelemetryCorrelationUtils.generateChildDependencyId());
		
//		RequestTelemetryContext context = ThreadContext.getRequestTelemetryContext();
//		if (context == null) {
//			System.out.println("context is null");
//		}
		// RequestTelemetry requestTelemetry = context.getHttpRequestTelemetry();
	    // requestTelemetry.getContext().getOperation().setParentId(parentId);
		
		// System.out.println("Id: " + requestTelemetry.getId());
		// System.out.println("RootId: " + requestTelemetry.getContext().getOperation().getId());
		// System.out.println("ParentId: " + requestTelemetry.getContext().getOperation().getParentId());
		
		// RemoteDependencyTelemetry dependencyTelemetry = new RemoteDependencyTelemetry("JMS Message", "myCall", new Duration(delta), true);
		RemoteDependencyTelemetry dependencyTelemetry = new RemoteDependencyTelemetry("JMS Message", "JMS", new Duration(200), true);
		dependencyTelemetry.getContext().getOperation().setId(message.get("Id"));
		dependencyTelemetry.getContext().getOperation().setParentId(message.get("parentId"));
		// dependencyTelemetry.setId(parentId);
		
		dependencyTelemetry.setTimestamp(new Date());
		
		telemetryClient.trackDependency(dependencyTelemetry);
		
		telemetryClient.trackEvent("Message is received");
	}
}
