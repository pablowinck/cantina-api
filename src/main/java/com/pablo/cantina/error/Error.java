package com.pablo.cantina.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Error {
    private String title;
    private String message;
    private int code;
}
