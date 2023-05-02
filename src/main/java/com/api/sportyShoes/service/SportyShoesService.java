package com.api.sportyShoes.service;

import com.api.sportyShoes.error.ShoesException;
import com.api.sportyShoes.model.Report;
import com.api.sportyShoes.model.Shoe;
import com.api.sportyShoes.repo.ReportRepo;
import com.api.sportyShoes.repo.ShoesRepo;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SportyShoesService implements ShoesService {

  private final ReportRepo reportRepo;
  private final ShoesRepo shoesRepo;

  @PostConstruct
  public void init() {
    Shoe shoe1 = new Shoe(1, "Adidas", "Running", 1200.00);
    Shoe shoe2 = new Shoe(2, "Nike", "Basketball", 1500.50);
    Shoe shoe3 = new Shoe(3, "Columbia", "Boot", 2543);
    Shoe shoe4 = new Shoe(4, "Puma", "Football", 766);

    shoesRepo.save(shoe1);
    shoesRepo.save(shoe2);
    shoesRepo.save(shoe3);
    shoesRepo.save(shoe4);

    Date dt = new Date();
    Report report1 = new Report(100, "purchaserA", "Football", dt,
        "adidas_runner:5,nike_airmax:10");
    Report report2 = new Report(101, "purchaserB", "Classical", dt,
        "adidas_cricket:5,nike_cricket:10");
    Report report3 = new Report(102, "purchaserC", "Basketball", dt,
        "adidas_basketball:5,nike_basketball:10");

    reportRepo.save(report1);
    reportRepo.save(report2);
    reportRepo.save(report3);
  }

  public Shoe addShoe(Shoe shoe) throws ShoesException {
    int id = shoe.getId();
    Shoe oldShoe = null;
    try {
      oldShoe = shoesRepo.findById(id).get();
    } catch (NoSuchElementException e) {
      log.error(e.getMessage());
    }
    if (oldShoe != null) {
      throw new ShoesException("Shoe already exists with id: " + id);
    }
    return shoesRepo.save(shoe);
  }

  public Shoe getShoeById(int id) throws ShoesException {
    Shoe shoe;
    try {
      if (id < 1) {
        throw new ShoesException("Shoe id should be larger than 0");
      }
      shoe = shoesRepo.findById(id).get();
    } catch (NoSuchElementException e) {
      throw new ShoesException("Shoe not found with id: " + id);
    }
    return shoe;
  }

  public Shoe updateShoe(Shoe shoe) {
    return shoesRepo.save(shoe);
  }

  public void deleteShoeById(int id) throws ShoesException {
    try {
      shoesRepo.deleteById(id);
    } catch (IllegalArgumentException e) {
      throw new ShoesException("Invalid id: " + id);
    } catch (EmptyResultDataAccessException e) {
      throw new ShoesException("SHoe does not exist with id: " + id);
    }
  }

  public List<Shoe> getAllShoes() {
    return shoesRepo.findAll();
  }

  @Override
  public Report addPurchaseReport(Report pr) {
    return null;
  }

  public Report createPurchaseReport(Report pr) throws ShoesException {
    int id = pr.getId();
    Report oldPr = null;
    try {
      oldPr = reportRepo.findById(id).get();
    } catch (NoSuchElementException e) {
      log.error(e.getMessage());
    }
    if (oldPr != null) {
      throw new ShoesException("Purchase report already exists with id: " + id);
    }
    return reportRepo.save(pr);
  }

  public Report getPurchaseReportById(int id) throws ShoesException {
    Report pr;
    try {
      if (id < 1) {
        throw new ShoesException("Purchase report cannot be smaller than 1");
      }
      pr = reportRepo.findById(id).get();
    } catch (NoSuchElementException e) {
      throw new ShoesException("Purchase report not found with id: " + id);
    }
    return pr;
  }

  public Report updatePurchaseReport(Report pr) {
    return reportRepo.save(pr);
  }

  public void deletePurchaseReportById(int id) throws ShoesException {
    try {
      reportRepo.deleteById(id);
    } catch (IllegalArgumentException e) {
      throw new ShoesException("Invalid id: " + id);
    } catch (EmptyResultDataAccessException e) {
      throw new ShoesException("Purchase report doesn't exist with id: " + id);
    }
  }

  public List<Report> getAllPurchaseReports() {
    return reportRepo.findAll();
  }

  public List<Report> getAllPurchaseReportsByCategory(String category) {
    return reportRepo.findByCategory(category);
  }

  public List<Report> getAllPurchaseReportsByDOP(Date dop) {
    return reportRepo.findByPurchaseDate(dop);
  }

}
