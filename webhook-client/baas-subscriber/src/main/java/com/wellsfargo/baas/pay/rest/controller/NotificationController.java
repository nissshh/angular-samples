/**
 * 
 */
package com.wf.baas.pay.rest.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.wf.baas.pay.dao.NotificationDAO;
import com.wf.baas.pay.exception.BaaSApplicationException;
import com.wf.baas.pay.model.Notification;
import com.wf.baas.pay.rest.NotificationsConstant;
/**
 * 
 * 
 * @author u180362
 *
 */
@Controller
@Aspect
@Path(NotificationsConstant.NOTIFICATIONS_BASE_PATH)
public class NotificationController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	NotificationDAO notificationsDAO;
	
	private String clientName;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getNotification() throws BaaSApplicationException {
		logger.debug("Recieved GET notifications for client {}" );
		List<Notification> notifications = notificationsDAO.findNotificationsByClient(clientName);
		return Response.ok(notifications).build();
	}

	@GET
	@Path("{type}/{status}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response getNotification(@PathParam("type") String notificationType,@PathParam("status") String paymentStatus)
			throws BaaSApplicationException {
		
		logger.debug("Recieved GET notification Type as {} status as {}",notificationType, paymentStatus);
		Notification notification = notificationsDAO.findNotificationByTypeAndStatus(notificationType, paymentStatus);
		return Response.ok(notification).build();
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateNotification(@Context HttpHeaders httpHeaders)
			throws BaaSApplicationException {
		
		String paymentStatus = httpHeaders.getHeaderString(NotificationsConstant.HTTP_HEADER_NOTIFICATION_STATUS);
		String notificationType  = httpHeaders.getHeaderString(NotificationsConstant.HTTP_HEADER_NOTIFICATION_TYPE);
		
		logger.debug("Recieved PUT/UPDATE notification Type as {} status as {}",notificationType, paymentStatus);
		
		Notification notification = new Notification(clientName,notificationType, paymentStatus);
		notificationsDAO.updateNotification(notification);
		return Response.ok(notification).build();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response postNotification(@Context HttpHeaders httpHeaders)
			throws BaaSApplicationException {
		String paymentStatus = httpHeaders.getHeaderString(NotificationsConstant.HTTP_HEADER_NOTIFICATION_STATUS);
		String notificationType  = httpHeaders.getHeaderString(NotificationsConstant.HTTP_HEADER_NOTIFICATION_TYPE);
		
		logger.debug("Recieved POST/NEW  notification Type as {} status as {}",notificationType, paymentStatus);
		
		Notification notification = new Notification(clientName,notificationType, paymentStatus);
		notificationsDAO.createNotification(notification);
		return Response.created(null).entity(notification).build();
	}

	public void delete() {

	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	
}
