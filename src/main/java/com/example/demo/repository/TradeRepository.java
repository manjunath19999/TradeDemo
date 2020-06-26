package com.example.demo.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
			LocalDateTime startDate, LocalDateTime endDate);

	public List<TradeModel> findBySymbol(String symbol);

	@Query(value = "SELECT  MIN(t.price ) as val  FROM trades as t , users as u where  u.trade_id  = t.id and  createdOn BETWEEN :startDate AND :endDate  and symbol = :sym ", nativeQuery = true)
	public BigDecimal minVal(String sym, LocalDateTime startDate, LocalDateTime endDate);

	@Query(value = "SELECT  MAX(t.price ) as val FROM trades as  t , users as u  where  u.trade_id  = t.id and  createdOn BETWEEN :startDate AND :endDate   and symbol = :sym ", nativeQuery = true)
	public BigDecimal maxVal(String sym, LocalDateTime startDate, LocalDateTime endDate);

	public List<TradeModel> findByUserModelIdOrderByIdAsc(Long id);

}
