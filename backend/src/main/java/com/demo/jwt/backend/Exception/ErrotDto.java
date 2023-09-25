package com.demo.jwt.backend.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ErrotDto {
    private  String message;

}
