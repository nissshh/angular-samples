/**
 * 
 */
package com.wf.baas.pay;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wf.baas.pay.dao.NotificationDAO;
import com.wf.baas.pay.exception.BaaSApplicationException;
import com.wf.baas.pay.model.Notification;

/**
 * @author u180362
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/baas-application-context-test.xml" })
public class NotificationsDAOTest {

	private static final String TEST_TYPE = "UNIT-TEST-TYPE";
	private static final String TEST_STATUS = "UNIT-TEST-STATUS";
	private static final String TEST_CLIENT = "UNIT-TEST-CLIENT";
	@Autowired
	NotificationDAO notificationsDAO;

	@Test
	@Rollback
	public void testInsert() throws BaaSApplicationException {
		Notification notification = getTestNotification();
		notificationsDAO.createNotification(notification);
		notification = findTestNotification(TEST_TYPE,TEST_STATUS);
		Assert.assertNotNull(notification);
		deleteTestNotification(TEST_TYPE);
		notification = findTestNotification(TEST_TYPE,TEST_STATUS);
		Assert.assertNull(notification);
	}

	private void deleteTestNotification(String testType) throws BaaSApplicationException {
		notificationsDAO.deleteNotificationByType(testType);
	}

	@Test
	@Rollback
	public void testUpdate() throws BaaSApplicationException {
		Notification notification = getTestNotification();
		notificationsDAO.createNotification(notification);
		notificationsDAO.updateNotification(notification);
		notification = findTestNotification(TEST_TYPE,TEST_STATUS);
		Assert.assertTrue((notification.getCount() == 2));
		deleteTestNotification(TEST_TYPE);
	}

	@Test
	@Rollback
	public void testGetAll() throws BaaSApplicationException {
		Notification notification = getTestNotification();
		notificationsDAO.createNotification(notification);
		List<Notification> list = notificationsDAO.findAllNotifications();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
		notificationsDAO.deleteAllNotificaiton();
	}

	
	private Notification getTestNotification() {
		Notification notification = new Notification(TEST_CLIENT,TEST_TYPE, TEST_STATUS);
		return notification;
	}

	private Notification findTestNotification(String testType, String testStatus) throws BaaSApplicationException {
		Notification notificationActual = notificationsDAO
				.findNotificationByTypeAndStatus(testType, testStatus);
		return notificationActual;
	}

}
