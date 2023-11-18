package com.rating.service.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ApiResponse {
    private boolean success ;
    private String  message ;
    private HttpStatus code;
}
