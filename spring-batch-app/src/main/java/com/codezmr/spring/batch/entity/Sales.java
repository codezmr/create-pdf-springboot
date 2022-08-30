package com.codezmr.spring.batch.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="SALES_RECORDS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String region;
	private String country;
	private String itemType;
	private String salesChannel;
	private String orderPriority;
	private String orderDate;
	private String orderId;
	private String shipDate;
	private String unitsSold;
	private String unitPrice;
	private String unitCost;
	private String totalRevenue;
	private String totalCost;
	private String totalProfit;
}
