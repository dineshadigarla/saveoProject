package com.demo.saveo.response;

import java.util.List;

import lombok.Data;

@Data
public class FindAllUsersResponse {
	List<User> userList;
}
