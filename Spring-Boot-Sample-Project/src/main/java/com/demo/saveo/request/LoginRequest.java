package com.demo.saveo.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginRequest extends BaseRequest {

	private String username;
	private String password;

}
