package com.example.Category_Service.Repository;

import com.example.Category_Service.DTO.CategoriesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoriesDTO , Long>  {
    boolean existsByNameIgnoreCase(String name);

}
