package com.example.demo.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.beans.ResponseBean;
import com.example.demo.model.TradeModel;
import com.example.demo.repository.TradeRepository;
import com.example.demo.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeRepository tradeRepository;

	@Override
	public ResponseEntity<TradeModel> addTrade(TradeModel tradeModel) {
		boolean trademodel;
		Long tradeId = tradeModel.getId();
		trademodel = tradeRepository.existsById(tradeId);

		if (trademodel) {
			return new ResponseEntity<TradeModel>(HttpStatus.BAD_REQUEST);
		} else {
			tradeRepository.save(tradeModel);
			return new ResponseEntity<TradeModel>(HttpStatus.CREATED);
		}

	}

	@Override
	public ResponseEntity<TradeModel> deleteAllTrade() {
		tradeRepository.deleteAll();
		return new ResponseEntity<TradeModel>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TradeModel>> findAllTrades() {

		List<TradeModel> resList = tradeRepository.findAllByOrderByIdAsc();
		return new ResponseEntity<List<TradeModel>>(resList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TradeModel>> findAllByStockAndTradeType(String stockSymbol, String tradeType,
			LocalDateTime startDate, LocalDateTime endDate) {

		List<TradeModel> list = tradeRepository.findBySymbol(stockSymbol);
		if (list.isEmpty())
			return new ResponseEntity<List<TradeModel>>(HttpStatus.NOT_FOUND);

		List<TradeModel> tradeList = tradeRepository.findByTypeAndSymbolAndCreatedOnBetweenOrderByIdAsc(tradeType,
				stockSymbol, startDate, endDate);
		return new ResponseEntity<List<TradeModel>>(tradeList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseBean> findAllByStock(String stockSymbol, LocalDateTime startDate,
			LocalDateTime endDate) {

		ResponseBean bean = new ResponseBean();
		BigDecimal defaultValue = new BigDecimal(0);
		List<TradeModel> list = tradeRepository.findBySymbol(stockSymbol);
		if (list.isEmpty())
			return new ResponseEntity<ResponseBean>(HttpStatus.NOT_FOUND);

		BigDecimal minValue = tradeRepository.minVal(stockSymbol, startDate, endDate);
		BigDecimal maxValue = tradeRepository.maxVal(stockSymbol, startDate, endDate);

		if (minValue.equals(defaultValue) && maxValue.equals(defaultValue)) {
			bean.setMessage("There are no trades in the given date range");
			return new ResponseEntity<ResponseBean>(bean, HttpStatus.OK);
		}
		bean.setMinValue(minValue);
		bean.setMaxValue(maxValue);
		bean.setSymbol(stockSymbol);
		return new ResponseEntity<ResponseBean>(bean, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<TradeModel>> findAllByUserId(Long id) {

		List<TradeModel> resList = tradeRepository.findByUserModelIdOrderByIdAsc(id);
		if (resList.isEmpty())
			return new ResponseEntity<List<TradeModel>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<TradeModel>>(resList, HttpStatus.OK);
	}

}
