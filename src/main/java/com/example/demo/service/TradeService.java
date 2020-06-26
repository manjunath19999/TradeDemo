package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.beans.ResponseBean;
import com.example.demo.model.TradeModel;

public interface TradeService {

	ResponseEntity<TradeModel> addTrade(TradeModel tradeModel);

	ResponseEntity<TradeModel> deleteAllTrade();

	ResponseEntity<List<TradeModel>> findAllTrades();

	ResponseEntity<List<TradeModel>>   findAllByStockAndTradeType(String stockSymbol, String tradeType, LocalDateTime startDate,
			LocalDateTime endDate);

	ResponseEntity<ResponseBean> findAllByStock(String stockSymbol, LocalDateTime startDate, LocalDateTime endDate);

	ResponseEntity<List<TradeModel>>  findAllByUserId(Long id);

}
