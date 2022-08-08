package com.Motorcycle.MotorcycleStar.dtos;

import lombok.Data;

@Data
public class RestResponse<T> { // T type D data E entity boleh
    private final T data;
    private final String message;
    private final int status;


}
