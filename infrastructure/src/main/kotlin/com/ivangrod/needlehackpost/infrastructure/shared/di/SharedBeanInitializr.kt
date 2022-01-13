package com.ivangrod.needlehackpost.infrastructure.shared.di

import com.ivangrod.needlehackpost.infrastructure.shared.bus.event.spring.SpringApplicationEventBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SharedBeanInitializr {

    @Bean
    fun eventBus(publisher: ApplicationEventPublisher): SpringApplicationEventBus {
        return SpringApplicationEventBus(publisher)
    }
}
