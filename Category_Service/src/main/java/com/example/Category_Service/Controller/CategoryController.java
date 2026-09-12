package com.example.Category_Service.Controller;

import com.example.Category_Service.DTO.CategoriesDTO;
import com.example.Category_Service.DTO.ResponseDTO;
import com.example.Category_Service.Repository.CategoryRepository;
import com.example.Category_Service.Service.CategoryService;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(
        name ="Category APIs",
        description = "APIs for managing categories"
)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


   ResponseDTO responseDTO = new ResponseDTO();


    @PostMapping("/categories")
    @Operation(summary = "create categories")
    public ResponseEntity<ResponseDTO> Create(@Valid @RequestBody CategoriesDTO categoriesDTO){
        CategoriesDTO category = categoryService.Create(categoriesDTO);
        responseDTO.setStatuscode(HttpStatus.CREATED.value());
        responseDTO.setError(false);
        responseDTO.setMessage("Category created successfully");
        ArrayList<CategoriesDTO> categoriesDTOS = new ArrayList<>();
        categoriesDTOS.add(category);
        responseDTO.setDtos(categoriesDTOS);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/categories/{id}")
    @Operation(summary = "Get  categories by ID ")
    public ResponseEntity<CategoriesDTO> GetById(@PathVariable Long id){
        CategoriesDTO category = categoryService.GetById(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @GetMapping("/categories")
    @Operation(summary = "Get all categories created")
    public ResponseEntity<ResponseDTO> GetAllCategory(){
        List<CategoriesDTO> categoriesDTOS = categoryService.GetAllCategory();
        responseDTO.setStatuscode(HttpStatus.OK.value());
        responseDTO.setError(false);
        responseDTO.setMessage("All Categories fetch successfully");
        responseDTO.setDtos(categoriesDTOS);
         return new ResponseEntity<>(responseDTO , HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    @Operation(summary = "Update category ")
    public ResponseEntity<ResponseDTO> updateCategory(@PathVariable Long id , @RequestBody CategoriesDTO categoriesDTO){
        CategoriesDTO update = categoryService.updateCategory(id ,categoriesDTO);
        responseDTO.setStatuscode(HttpStatus.OK.value());
        responseDTO.setError(false);
        responseDTO.setMessage("Category updated successfully");
        ArrayList<CategoriesDTO> dtos = new ArrayList<>();
        dtos.add(update);
        responseDTO.setDtos(dtos);
        return new ResponseEntity<>(responseDTO , HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    @Operation(summary = "Delete category")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable Long id){
        CategoriesDTO delete = categoryService.deleteCategory(id);
        responseDTO.setStatuscode(HttpStatus.OK.value());
        responseDTO.setError(false);
        responseDTO.setMessage("Category deleted successfully");
        ArrayList<CategoriesDTO> dtos = new ArrayList<>();
        dtos.add(delete);
        responseDTO.setDtos(dtos);
        return new ResponseEntity<>(responseDTO , HttpStatus.OK);
    }
}
