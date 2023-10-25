package com.easy.car_Rent.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.easy.car_Rent.controller","com.easy.car_Rent.advisor"})
public class WebAppConfig {
}
