package com.api.sportyShoes.repo;

import com.api.sportyShoes.model.Report;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo extends JpaRepository<Report, Integer> {

  List<Report> findByCategory(String category);

  List<Report> findByPurchaseDate(Date purchaseDate);


}
