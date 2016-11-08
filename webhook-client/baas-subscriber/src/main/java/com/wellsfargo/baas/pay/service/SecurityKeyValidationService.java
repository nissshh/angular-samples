/**
 * 
 */
package com.wf.baas.pay.service;

import org.springframework.stereotype.Service;

/**
 * 
 * Security Validations
 * 
 * @author u180362
 *
 */

@Service
public interface SecurityKeyValidationService {
	boolean validateKey(String securityKey);
}
