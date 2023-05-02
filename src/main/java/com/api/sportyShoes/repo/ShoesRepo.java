package com.api.sportyShoes.repo;


import com.api.sportyShoes.model.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesRepo extends JpaRepository<Shoe, Integer> {

}
