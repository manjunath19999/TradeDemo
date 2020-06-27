package com.example.demo.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResponseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal minValue;

	private BigDecimal maxValue;
	
	private String symbol;

	private String message;

	public ResponseBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "ResponseBean [minValue=" + minValue + ", maxValue=" + maxValue + ", symbol=" + symbol + ", message="
				+ message + "]";
	}

	

}
