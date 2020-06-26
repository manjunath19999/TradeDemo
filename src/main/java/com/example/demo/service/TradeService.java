package com.example.demo.service;

import java.sql.Timestamp;

import org.json.JSONArray;
import org.springframework.http.ResponseEntity;

import com.example.demo.beans.ResponseBean;
import com.example.demo.model.TradeModel;

public interface TradeService {

	ResponseEntity<TradeModel> addTrade(TradeModel tradeModel);

	ResponseEntity<TradeModel> deleteAllTrade();

	ResponseEntity<JSONArray> findAllTrades(TradeModel tradeModel);

	ResponseEntity<JSONArray>  findAllByStockAndTradeType(String stockSymbol, String tradeType, Timestamp startDate,
			Timestamp endDate);

	ResponseEntity<ResponseBean> findAllByStock(String stockSymbol, Timestamp startDate, Timestamp endDate);

	ResponseEntity<JSONArray>  findAllByUserId(Long id);

}
