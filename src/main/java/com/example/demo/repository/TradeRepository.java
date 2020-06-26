package com.example.demo.repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TradeModel;

@Repository
public interface TradeRepository extends CrudRepository<TradeModel, Long> {

	public Optional<TradeModel> findById(Long tradeId);

	public List<TradeModel> findAllByOrderByIdAsc();

	public List<TradeModel> findByTypeAndSymbolAndCreatedOnBetweenOrderByIdAsc(String type, String symbol,
			Timestamp startDate, Timestamp endDate);

	public List<TradeModel> findBySymbol(String symbol);

	@Query(value = "SELECT COALESCE(min(price),0)  FROM trades where  createdOn BETWEEN :startDate AND :endDate  and symbol = :sym ")
	public BigDecimal minVal(String sym, Timestamp startDate, Timestamp endDate);

	@Query(value = "SELECT COALESCE(max(price),0) FROM trades where  createdOn BETWEEN :startDate AND :endDate   and symbol = :sym ")
	public BigDecimal maxVal(String sym, Timestamp startDate, Timestamp endDate);
	
	public List<TradeModel> findByUserDetailsIdOrderByIdAsc(Long id);

}
