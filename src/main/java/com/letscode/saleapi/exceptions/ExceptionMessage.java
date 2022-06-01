package com.letscode.saleapi.exceptions;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionMessage {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
}
