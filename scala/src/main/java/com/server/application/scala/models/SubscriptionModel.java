package com.server.application.scala.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionModel implements Serializable {
    public String UserName;
    public Boolean status;
    public int count;
    public String uniqueCode;
    public String Events;
}
