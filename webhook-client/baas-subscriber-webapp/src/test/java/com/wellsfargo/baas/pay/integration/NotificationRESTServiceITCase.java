package com.wf.baas.pay.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wf.baas.pay.model.Notification;
import com.wf.baas.pay.rest.NotificationsConstant;
import com.wf.baas.pay.rest.controller.NotificationController;

public class NotificationRESTServiceITCase {

	private static final String TEST_TYPE = "TEST_TYPE_IT_TYPE";
	private static final String TEST_STATUS= "TEST_TYPE_IT_STATUS";
	private static final String TEST_CLIENT = "TEST_CLIENT_IT";

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void testGetNotification() throws JsonGenerationException,
			JsonMappingException, IOException {
		WebTarget webTarget = getWebTarget(getBaseURI());

		Builder request = webTarget.path("notifications?type=Payment&status=Recieved").request(MediaType.APPLICATION_JSON); 

		Response response = request.get(); 
		Assert.assertTrue(response.getStatus() == 200);
		
		
		Notification notifications = response.readEntity(new GenericType<Notification>(){});
		
		ObjectMapper mapper = new ObjectMapper();
		logger.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notifications));

		Assert.assertTrue("Notification is present", notifications != null);
	}
	
	@Test
	public void testGetNotifications() throws JsonGenerationException,
			JsonMappingException, IOException {

		WebTarget webTarget = getWebTarget(getBaseURI());

		Builder request = webTarget.path("notifications").request(MediaType.APPLICATION_JSON); 

		Response response = request.get(); 
		Assert.assertTrue(response.getStatus() == 200);

		List<Notification> notifications = response.readEntity(new GenericType<List<Notification>>() {});

		ObjectMapper mapper = new ObjectMapper();
		logger.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notifications));

		Assert.assertTrue("At least one notification is present",notifications.size() > 0);
	}

	private String getBaseURI() {
		return "http://localhost:8080/baas-subscriber-webapp/rest";
	}

	@Test
	public void testPutNotifitcation() throws JsonGenerationException,
			JsonMappingException, IOException {

		WebTarget webTarget = getWebTarget(getBaseURI()).path("notifications");

		Builder request = webTarget
				.request(MediaType.APPLICATION_JSON)
				.header(NotificationsConstant.HTTP_HEADER_NOTIFICATION_STATUS, TEST_STATUS)
				.header(NotificationsConstant.HTTP_HEADER_NOTIFICATION_TYPE, TEST_TYPE);
		Notification  notification = new Notification(TEST_CLIENT,TEST_TYPE, TEST_STATUS);
		Response response = request.put(Entity.entity(notification,MediaType.APPLICATION_JSON_TYPE));
		Assert.assertTrue(response.getStatus() == 200);
		Notification notificationResponse = response.readEntity(Notification.class);
		Assert.assertTrue(notificationResponse.getType().equalsIgnoreCase(TEST_TYPE));
		Assert.assertTrue(notificationResponse.getStatus().equalsIgnoreCase(TEST_STATUS));
		Assert.assertTrue(notificationResponse.getClient().equalsIgnoreCase(TEST_CLIENT));
		ObjectMapper mapper = new ObjectMapper();
		logger.info("Received notification from database *************************** "+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationResponse));

	}
	
	
	@Test
	public void testPostNotifitcation() throws JsonGenerationException,
			JsonMappingException, IOException {

		WebTarget webTarget = getWebTarget(getBaseURI()).path("notifications");

		Builder request = webTarget.request(MediaType.APPLICATION_JSON)
				.header(NotificationsConstant.HTTP_HEADER_NOTIFICATION_STATUS, TEST_STATUS)
				.header(NotificationsConstant.HTTP_HEADER_NOTIFICATION_TYPE, TEST_TYPE);
		Notification  notification = new Notification(TEST_CLIENT,TEST_TYPE, TEST_STATUS);
		Response response = request.post(Entity.entity(notification,MediaType.APPLICATION_JSON_TYPE));
		Assert.assertTrue(response.getStatus() == 201);
		Notification notificationResponse = response.readEntity(Notification.class);
		Assert.assertTrue(notificationResponse.getType().equalsIgnoreCase(TEST_TYPE));
		Assert.assertTrue(notificationResponse.getStatus().equalsIgnoreCase(TEST_STATUS));
		Assert.assertTrue(notificationResponse.getClient().equalsIgnoreCase(TEST_CLIENT));
		
		ObjectMapper mapper = new ObjectMapper();
		logger.info("Received notification from database *************************** "+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationResponse));

	}

	/**
	 * REturns the Web target identified by a base URI
	 * @return
	 */
	private WebTarget getWebTarget(String baseURI) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(JacksonFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);

		WebTarget webTarget = client.target(baseURI);
		return webTarget;
	}
}
