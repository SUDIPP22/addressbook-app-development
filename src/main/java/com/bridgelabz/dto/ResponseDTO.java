package com.bridgelabz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Purpose : This class is created for generating the response of various Http request
 * which will contain a string message
 *
 * @author SUDIP PANJA
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private HttpStatus httpStatus;
    private String message;
}
