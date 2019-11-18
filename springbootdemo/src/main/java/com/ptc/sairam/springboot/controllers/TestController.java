package com.ptc.sairam.springboot.controllers;

import com.microsoft.applicationinsights.TelemetryClient;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microsoft.applicationinsights.telemetry.Duration;
import com.microsoft.applicationinsights.telemetry.RemoteDependencyTelemetry;

@RestController
@RequestMapping("/sample")
public class TestController {

	@Autowired
	TelemetryClient telemetryClient;

	@GetMapping("/hello")
	public String hello() throws IOException {

		// track a custom event
		// telemetryClient.trackEvent("Sending a custom event...");

		// trace a custom trace
		// telemetryClient.trackTrace("Sending a custom trace....");

		// track a custom metric
		// telemetryClient.trackMetric("custom metric", 1.0);

		// track a custom dependency
		// telemetryClient.trackDependency("SQL", "Insert", new Duration(0, 0, 1, 1, 1),
		// true);

		// External call
		// Get joke
		long startTime = new Date().getTime();

		String url = "https://api.icndb.com/jokes/random?limitTo=[nerdy]";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = null;

		try {
			response = httpclient.execute(httpget);
			System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			response.close();
			long endTime = new Date().getTime();
			long delta = endTime - startTime;
			RemoteDependencyTelemetry dependencyTelemetry = new RemoteDependencyTelemetry("My Dependency", "myCall",
					new Duration(delta), true);
			dependencyTelemetry.setTimestamp(new Date(startTime));
			// telemetryClient.trackDependency(dependencyTelemetry);
		}

		return "hello";
	}
}
