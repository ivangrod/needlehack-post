package com.ivangrod.needlehackpost.infrastructure

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ComponentScan
@Configuration
@EnableJpaRepositories(basePackages = ["com.ivangrod.needlehackpost.infrastructure.post.persistence.repository"])
class Config {

}
