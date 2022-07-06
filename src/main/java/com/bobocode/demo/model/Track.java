package com.bobocode.demo.model;

import com.bobocode.bring.annotation.Bean;
import lombok.Data;

@Bean
@Data
public class Track implements Vehicle {
    @Override
    public void go() {
        System.out.println("the TRACK IS MOVING!");
    }
}
