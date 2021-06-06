package com.demo.saveo.response;

import java.util.List;

import lombok.Data;

@Data
public class FindAllTransactionsByUserResponse {
	private List<Transaction> transactionList;
}
