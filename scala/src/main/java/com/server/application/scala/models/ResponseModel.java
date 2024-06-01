package com.server.application.scala.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseModel implements Serializable{
    private String message;
    private Integer statusCode;
    private String status;
    private String messageDetails;
    private Object data;

}
