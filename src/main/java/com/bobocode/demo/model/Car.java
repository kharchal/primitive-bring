package com.bobocode.demo.model;

import com.bobocode.bring.annotation.Bean;
import com.bobocode.bring.annotation.Inject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Bean
@Data
@NoArgsConstructor
public class Car implements Vehicle {

    @Inject
    private Cont cont;

    @Override
    public void go() {
        System.out.println("the car is going...");
    }
}
