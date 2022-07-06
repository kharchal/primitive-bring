package com.bobocode.demo.model;

import com.bobocode.bring.annotation.Bean;
import lombok.Data;

@Bean("bike")
@Data
public class Bicycle implements Vehicle {
    @Override
    public void go() {
        System.out.println("the bike is slowly moving");
    }
}
