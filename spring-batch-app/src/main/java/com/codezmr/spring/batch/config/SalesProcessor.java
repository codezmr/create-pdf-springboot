package com.codezmr.spring.batch.config;

import org.springframework.batch.item.ItemProcessor;

import com.codezmr.spring.batch.entity.Sales;

public class SalesProcessor implements ItemProcessor<Sales, Sales>{

	@Override
	public Sales process(Sales sales) throws Exception {
		
		return sales;
	}

}
