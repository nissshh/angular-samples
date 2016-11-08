/**
 * 
 */
package com.wf.baas.pay.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * A Returned Payment notification type
 * 
 * @author u180362
 *
 */
@XmlRootElement
public class ReturnedPaymentNotification {

	/**
	 * Sample Request Body
	{"returned_payment_notification" : {
	  "ach_file_id" : "yregwGRHAGFHKASGF",
	  "ach_company_id" : "hasfbhksfhsfhsfks",
	  "date_time_returned" : "vfsvfhjsfhksfhk",
	  "debit_credit_indicator" : "",
	  "routing_number" : "54782982",
	  "account_number" : "236487907914942048920489",
	  "amount" : "6743.52",
	  "individual_name" : "Wells",
	  "individual_id" : "U6784",
	  "original_trace_number": "8643792218",
	  "return_reason_code" : "r12",
	  "return_reason_description" : "",
	  "entry_description" : "",
	  "company_name" : "Google",
	  "original_effective_Entry_date" : "",
	  "ach_file_key" : "",
	  "sec_code" : "",
	  "redeposit_occurance" : "",
	  "found_not_found_occurance" : "",
	  "company_descriptive_date" : "",
	  "free_form_addeda_text" : ""
	}}

	 */
}
