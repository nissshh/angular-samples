package com.wf.baas.pay.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wf.baas.pay.model.Notification;

public interface NotificationMapper {

	List<Notification> getAllNotifications() throws Exception;

	List<Notification> findNotificationsByClient(String client);

	int createNotification(Notification notification) throws Exception;
	
	int incrementNotification(@Param(value = "type") String type,@Param(value = "status") String status) throws Exception;
	
	int updateNotification(Notification notification) throws Exception;
	
	void deleteAllNotification() throws Exception;

	Notification findNotification(Notification notification) throws Exception;
	
	Notification findNotificationByType(String type) throws Exception;

	Notification findNotificationByTypeAndStatus(@Param(value = "type") String type,@Param(value = "status") String status) throws Exception;
	
	void deleteNotificationByType(String testType) throws Exception;

}
