package com.zeecoder.ktutorials.configuration

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ApplicationConfig {

    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder) : RestTemplate = restTemplateBuilder.build()
}