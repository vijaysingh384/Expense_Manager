package com.example.Category_Service.Service;

import com.example.Category_Service.DTO.CategoriesDTO;
import com.example.Category_Service.Exception.BusinessException;
import com.example.Category_Service.Exception.ResourceNotFoundException;
import com.example.Category_Service.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoriesDTO Create(CategoriesDTO categoriesDTO) {
        if(categoryRepository.existsByNameIgnoreCase(categoriesDTO.getName())){
            throw new BusinessException("Category name already exist");
        }

        return categoryRepository.save(categoriesDTO);
    }


    public CategoriesDTO GetById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found for this ID: " + id));
    }

    public List<CategoriesDTO> GetAllCategory() {
        return categoryRepository.findAll();
    }


    public CategoriesDTO updateCategory(Long id , CategoriesDTO categoriesDTO) {
        CategoriesDTO dto = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found for this ID: " + id));
        dto.setName(categoriesDTO.getName());
        return categoryRepository.save(dto);
    }


    public CategoriesDTO deleteCategory(Long id) {
        CategoriesDTO dto = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found for this ID: " + id));
        if (dto!=null){
            categoryRepository.delete(dto);
            return  dto;
        }
        return null;
    }
}
