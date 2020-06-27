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

	@Query(value = "SELECT  *  FROM trades as t , users as u where "
			+ " u.trade_id = t.id and  created_date BETWEEN :startDate AND :endDate   and type = :type and symbol = :symbol  order by t.id asc", nativeQuery = true)
	public List<TradeModel> findByTypeAndSymbolAndCreatedOnBetweenOrderByIdAsc(Timestamp startDate, Timestamp endDate,
			String type, String symbol);

	public List<TradeModel> findBySymbol(String symbol);

	@Query(value = "SELECT  MIN(t.price) as val  FROM trades as t , users as u where  u.trade_id = t.id and  created_date BETWEEN :startDate AND :endDate  and symbol = :sym ", nativeQuery = true)
	public BigDecimal minVal(Timestamp startDate, Timestamp endDate, String sym);

	@Query(value = "SELECT  MAX(t.price) as val FROM trades as  t , users as u  where  u.trade_id  = t.id and  created_date BETWEEN :startDate AND :endDate   and symbol = :sym ", nativeQuery = true)
	public BigDecimal maxVal(Timestamp startDate, Timestamp endDate, String sym);

	public List<TradeModel> findByUserModelIdOrderByIdAsc(Long id);

}
