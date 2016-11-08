package com.wf.baas.pay.integration;
import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class NotificationRESTServiceRestAssuredITCase {

	public void testNotificationsFetchSuccessful(){
		expect().
			body("type", equalTo("PAY")).
			body("count", equalTo(new Integer(10))).
		when().
			get("/notifications/PAY");
	}
}
