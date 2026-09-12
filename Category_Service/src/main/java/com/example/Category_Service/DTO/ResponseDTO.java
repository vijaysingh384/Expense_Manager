package com.example.Category_Service.DTO;

import jdk.jfr.Category;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ResponseDTO {
    private String message;
    private boolean error;
    private int Statuscode;
    private List<CategoriesDTO> dtos;
}
