package com.cydeo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class ResponseWrapper { // data yı özelleştirmek için bu sınıfı kullanırız!

    private boolean success;
    private String message;
    private Integer code;
    private Object data;

    public ResponseWrapper(String message, Object data){
        this.message = message;
        this.data = data;
        this.code = HttpStatus.OK.value();
        this.success = true;
    }

    public ResponseWrapper(String message){ // for delete no need for data
        this.message = message;
        this.code = HttpStatus.OK.value();
        this.success = true;
    }
}


