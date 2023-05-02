package com.api.sportyShoes.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Report {

	@Id
	@GeneratedValue
	private int id;

	private String orderList;

	private String purchaser;

	private String category;

	@Temporal(TemporalType.DATE)
	private Date purchaseDate;

	public Report(int id, String purchaser, String category, Date purchaseDate, String orderList) {
		super();
		this.id = id;
		this.purchaser = purchaser;
		this.category = category;
		this.purchaseDate = purchaseDate;
		this.orderList = orderList;
	}

}
