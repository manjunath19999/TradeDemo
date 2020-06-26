package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.ResponseBean;
import com.example.demo.model.TradeModel;
import com.example.demo.service.TradeService;

@RestController
@CrossOrigin
@RequestMapping("service/trade")
public class TradeController {

	@Autowired
	private TradeService tradeService;

	@PostMapping("/addTrade")
	public ResponseEntity<TradeModel> addTrade(@RequestBody TradeModel tradeModel) {
		return tradeService.addTrade(tradeModel);

	}

	@DeleteMapping("/deleteTrades")
	public ResponseEntity<TradeModel> deleteTrade() {
		return tradeService.deleteAllTrade();
	}

	@GetMapping("/findAllTrades")
	public ResponseEntity<List<TradeModel>> findAllTrades() {
		return tradeService.findAllTrades();
	}

	@GetMapping("/{stockSymbol}/{tradeType}/{startDate}/{endDate}")
	public ResponseEntity<List<TradeModel>> findAllByStockAndTradeType(@PathVariable("stockSymbol") String stockSymbol,
			@PathVariable("tradeType") String tradeType, @PathVariable("startDate") LocalDateTime startDate,
			@PathVariable("endDate") LocalDateTime endDate) {
		return tradeService.findAllByStockAndTradeType(stockSymbol, tradeType, startDate, endDate);
	}

	@GetMapping("/{stockSymbol}/{startDate}/{endDate}")
	public ResponseEntity<ResponseBean> findAllByStock(@PathVariable("stockSymbol") String stockSymbol,
			@PathVariable("startDate") LocalDateTime startDate, @PathVariable("endDate") LocalDateTime endDate) {
		return tradeService.findAllByStock(stockSymbol, startDate, endDate);
	}
	
	@GetMapping("/users/{userID}")
	public ResponseEntity<List<TradeModel>	> findAllByStock(@PathVariable("userID") Long id) {
		return tradeService.findAllByUserId(id);
	}

}
