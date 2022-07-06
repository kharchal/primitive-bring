package com.bobocode.demo.model;

import com.bobocode.bring.annotation.Bean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Bean
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cont {
    private String value = "def value";
}
