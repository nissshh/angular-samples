/**
 * 
 */
package com.wf.baas.pay.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.wf.baas.pay.exception.BaaSApplicationException;
import com.wf.baas.pay.mappers.NotificationMapper;
import com.wf.baas.pay.model.Notification;

/**
 * @author u180362
 *
 */

@Repository
@Component
public class NotificationDAO {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	NotificationMapper mapper;

	public List<Notification> findAllNotifications()
			throws BaaSApplicationException {
		List<Notification> notifications = null;
		try {
			notifications = mapper.getAllNotifications();
		} catch (Exception e) {
			throw new BaaSApplicationException(e);
		}
		return notifications;
	}
	
	public List<Notification> findNotificationsByClient(String client)
			throws BaaSApplicationException {
		List<Notification> notifications = null;
		try {
			notifications = mapper.findNotificationsByClient(client);
		} catch (Exception e) {
			throw new BaaSApplicationException(e);
		}
		return notifications;
	}

	public int createNotification(Notification notification)
			throws BaaSApplicationException {
		try {
			return mapper.createNotification(notification);
		} catch (Exception e) {
			throw new BaaSApplicationException(e);
		}
	}

	public Notification findNotification(Notification notification)
			throws BaaSApplicationException {
		try {
			return mapper.findNotification(notification);
		} catch (Exception e) {
			throw new BaaSApplicationException(e);
		}

	}

	public Notification findNotificationByTypeAndStatus(String type,
			String status) throws BaaSApplicationException {
		try {
			return mapper.findNotificationByTypeAndStatus(type, status);
		} catch (Exception e) {
			throw new BaaSApplicationException(e);
		}
	}

	public int updateNotification(Notification notification)
			throws BaaSApplicationException {
		try {
			return mapper.updateNotification(notification);
		} catch (Exception e) {
			throw new BaaSApplicationException(e);
		}

	}
	

	public void deleteAllNotificaiton() throws BaaSApplicationException {
		try {
			mapper.deleteAllNotification();
		} catch (Exception e) {
			throw new BaaSApplicationException(e);
		}
	}

	public void deleteNotificationByType(String testType)
			throws BaaSApplicationException {
		try {
			mapper.deleteNotificationByType(testType);
		} catch (Exception e) {
			throw new BaaSApplicationException(e);
		}

	}

	/** Service Status Quality methods ***/
	@PostConstruct
	public void init() {
		logger.debug("Initialized service {}", this.getClass());
	}

	@PreDestroy
	public void destroy() {
		logger.debug("Destroying service {}", this.getClass());
	}
}
