package com.ptc.sairam.springboot.listeners;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
	public void GetMessage(HashMap<String, String> message) throws Exception {
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
		dependencyTelemetry.getContext().getCloud().setRole("springbootdemo");
		try {
			dependencyTelemetry.getContext().getCloud().setRoleInstance(InetAddress.getLocalHost().getCanonicalHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// dependencyTelemetry.setId(parentId);
		
		dependencyTelemetry.setTimestamp(new Date());
		
		telemetryClient.trackDependency(dependencyTelemetry);
		
		telemetryClient.trackEvent("Message is received");
		
		TrackMethod();
		
		// Call Joke service
		// Get joke
		String url = "https://api.icndb.com/jokes/random?limitTo=[nerdy]";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			System.out.println(entity.getContent().toString());
		} finally {
			response.close();
		}
		
		System.out.println("Joke service is called.");
	}
	
	private void TrackMethod() throws Exception {
		System.out.println("TrackMethod is called.");
		Thread.sleep(5000);
	}
}
