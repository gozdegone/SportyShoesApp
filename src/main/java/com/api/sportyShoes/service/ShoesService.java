package com.api.sportyShoes.service;

import com.api.sportyShoes.error.ShoesException;
import com.api.sportyShoes.model.Report;
import com.api.sportyShoes.model.Shoe;
import java.util.Date;
import java.util.List;

public interface ShoesService {

  Shoe addShoe(Shoe shoe) throws ShoesException;

  Shoe getShoeById(int id) throws ShoesException;

  Shoe updateShoe(Shoe shoe);

  void deleteShoeById(int id) throws ShoesException;

  List<Shoe> getAllShoes();

  Report addPurchaseReport(Report pr) throws ShoesException;

  Report getPurchaseReportById(int id) throws ShoesException;

  Report updatePurchaseReport(Report pr);

  void deletePurchaseReportById(int id) throws ShoesException;

  List<Report> getAllPurchaseReports();

  List<Report> getAllPurchaseReportsByCategory(String category);

  List<Report> getAllPurchaseReportsByDOP(Date dop);

}
