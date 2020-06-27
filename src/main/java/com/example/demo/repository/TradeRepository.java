package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TradeModel;

@Repository
public interface TradeRepository extends CrudRepository<TradeModel, Long> {

	public Optional<TradeModel> findById(Long tradeId);

	public List<TradeModel> findAllByOrderByIdAsc();

	public List<TradeModel> findByTypeAndSymbolAndCreatedOnBetweenOrderByIdAsc(String type, String symbol,
			LocalDateTime startDate, LocalDateTime endDate);

	public List<TradeModel> findBySymbol(String symbol);

	@Query(value = "SELECT  MIN(t.price) as val  FROM trades as t , users as u where  u.trade_id  = t.id and "
			+ " created_on >= :startDate AND created_on <= :endDate and symbol = :sym ", nativeQuery = true)
	public String minVal(@Param("startDate") LocalDateTime startDate1,@Param("endDate") LocalDateTime endDate1 ,@Param("sym") String sym1);

	@Query(value = "SELECT  MAX(t.price) as val FROM trades as  t , users as u  where  u.trade_id  = t.id and "
			+ " created_on >= :startDate AND created_on <= :endDate   and symbol = :sym ", nativeQuery = true)
	public String maxVal(@Param("startDate")LocalDateTime startDate1,@Param("endDate") LocalDateTime endDate1 ,@Param("sym") String sym1);

	public List<TradeModel> findByUserModelIdOrderByIdAsc(Long id);

}
