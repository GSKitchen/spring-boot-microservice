package com.example.currencyexchangeservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExchangeValue {
	
	@Id
	private Long id;
	
	@Column(name = "from_val")
	private String from;
	
	@Column(name = "to_val")
	private String to;
	
	private BigDecimal convMultiple;
	private int port;
	
	public ExchangeValue() {
		super();
	}
	public ExchangeValue(Long id, String from, String to, BigDecimal convMultiple) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.convMultiple = convMultiple;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigDecimal getConvMultiple() {
		return convMultiple;
	}
	public void setConvMultiple(BigDecimal convMultiple) {
		this.convMultiple = convMultiple;
	}
	
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	@Override
	public String toString() {
		return "ExchangeValue [id=" + id + ", from=" + from + ", to=" + to + ", convMultiple=" + convMultiple
				+ ", port=" + port + "]";
	}
	
	
}
