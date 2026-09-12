package com.example.Category_Service.DTO;

import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDetailsDTO {
    private int status;
    private String msg;
    private LocalDateTime dateTime;
    private String path;


}
