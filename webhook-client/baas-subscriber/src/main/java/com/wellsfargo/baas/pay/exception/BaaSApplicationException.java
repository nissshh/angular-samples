/**
 * 
 */
package com.wf.baas.pay.exception;

import javax.ws.rs.core.Response.Status;

/**
 * @author u180362
 *
 */
public class BaaSApplicationException extends Exception {

	public BaaSApplicationException(Exception e) {
		super(e);
	}

	public Status getStatus() {
		return Status.INTERNAL_SERVER_ERROR;
	}

}
