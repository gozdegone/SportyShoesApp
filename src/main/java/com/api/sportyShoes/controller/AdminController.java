package com.api.sportyShoes.controller;

import com.api.sportyShoes.error.ShoesException;
import com.api.sportyShoes.model.Report;
import com.api.sportyShoes.model.Shoe;
import com.api.sportyShoes.service.ShoesService;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

  private final ShoesService service;

  private MultiValueMap<String, String> errorMap;

  @GetMapping("/adm/shoes/{id}")
  public ResponseEntity<Shoe> getShoeById(@PathVariable int id) {
    try {
      return new ResponseEntity<>(service.getShoeById(id), HttpStatus.OK);
    } catch (ShoesException e) {
      errorMap = new LinkedMultiValueMap<>();
      errorMap.add("error:", e.getMessage());
      return new ResponseEntity<>(null, errorMap, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/adm/shoes")
  public ResponseEntity<Shoe> addShoe(@RequestBody Shoe shoe) {
    try {
      return new ResponseEntity<>(service.addShoe(shoe), HttpStatus.OK);
    } catch (ShoesException e) {
      errorMap = new LinkedMultiValueMap<>();
      errorMap.add("error:", e.getMessage());
      return new ResponseEntity<>(null, errorMap, HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/adm/shoes")
  public ResponseEntity<Shoe> updateShoe(@RequestBody Shoe shoe) {
    return new ResponseEntity<>(service.updateShoe(shoe), HttpStatus.OK);
  }

  @DeleteMapping("/adm/shoes/{id}")
  public ResponseEntity<String> deleteShoeById(@PathVariable int id) {
    try {
      service.deleteShoeById(id);
      return new ResponseEntity<>("Successfully deleted shoe with id: " + id, HttpStatus.OK);
    } catch (ShoesException e) {
      errorMap = new LinkedMultiValueMap<>();
      errorMap.add("error:", e.getMessage());
      return new ResponseEntity<>(e.getMessage(), errorMap, HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/adm/purchase-reports")
  public ResponseEntity<Report> addPurchaseReport(@RequestBody Report pr) {
    try {
      return new ResponseEntity<>(service.addPurchaseReport(pr), HttpStatus.OK);
    } catch (ShoesException e) {
      errorMap = new LinkedMultiValueMap<>();
      errorMap.add("error:", e.getMessage());
      return new ResponseEntity<>(null, errorMap, HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/adm/purchase-reports")
  public ResponseEntity<Report> updatePurchaseReport(@RequestBody Report pr) {
    return new ResponseEntity<>(service.updatePurchaseReport(pr), HttpStatus.OK);
  }

  @GetMapping("/adm/purchase-reports/{id}")
  public ResponseEntity<Report> getPurchaseReportById(@PathVariable int id) {
    try {
      return new ResponseEntity<>(service.getPurchaseReportById(id), HttpStatus.OK);
    } catch (ShoesException e) {
      errorMap = new LinkedMultiValueMap<>();
      errorMap.add("error:", e.getMessage());
      return new ResponseEntity<>(null, errorMap, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/adm/purchase-reports/{id}")
  public ResponseEntity<String> deletePurchaseReportById(@PathVariable int id) {
    try {
      service.deletePurchaseReportById(id);
      return new ResponseEntity<>("Successfully deleted Purchase Report with id: " + id,
          HttpStatus.OK);
    } catch (ShoesException e) {
      errorMap = new LinkedMultiValueMap<>();
      errorMap.add("error:", e.getMessage());
      return new ResponseEntity<>(null, errorMap, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/adm/shoe/all")
  public ResponseEntity<List<Shoe>> getAllShoes() {
    return new ResponseEntity<>(service.getAllShoes(), HttpStatus.OK);
  }

  @GetMapping("/adm/purchaseReport/category/{category}")
  public ResponseEntity<List<Report>> getAllPurchaseReportsByCategory(
      @PathVariable String category) {
    return new ResponseEntity<>(
        service.getAllPurchaseReportsByCategory(category), HttpStatus.OK);
  }

  @GetMapping("/adm/purchaseReport/date/{dateInMs}")
  public ResponseEntity<List<Report>> getAllPurchaseReportsByDop(
      @PathVariable Long dateInMs) {
    Date dop = new Date(dateInMs);
    return new ResponseEntity<>(service.getAllPurchaseReportsByDOP(dop), HttpStatus.OK);
  }

  @GetMapping("/adm/purchaseReport/all")
  public ResponseEntity<List<Report>> getAllPurchaseReport() {
    return new ResponseEntity<>(service.getAllPurchaseReports(), HttpStatus.OK);
  }
}