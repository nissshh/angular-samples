/**
 * 
 */
package com.wf.baas.pay.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author u180362
 *
 */
@XmlRootElement
public class Notification {

	private String type;

	private long count;

	private String status;

	private String client;

	public Notification() {
		// TODO Auto-generated constructor stub
	}

	public Notification(String clientName, String notificationType,
			String paymentStatus) {
		this.client = clientName;
		this.type = notificationType;
		this.status = paymentStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notification [type=" + type + ", count=" + count + ", status="
				+ status + "]";
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

}
