/*
 * (#) net.brainage.nest.ServletInitializer.java
 * Created on 2016-02-15
 *
 * Copyright 2015 brainage.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.brainage.nest;

import net.brainage.nest.core.time.CurrentDateTimeService;
import net.brainage.nest.core.time.DateTimeService;
import net.brainage.nest.data.auditing.AuditingDateTimeProvider;
import net.brainage.nuri.security.crypto.PBKDF2PasswordEncryptor;
import net.brainage.nuri.security.crypto.PasswordEncryptor;
import net.brainage.nuri.security.crypto.RandomNumberGenerator;
import net.brainage.nuri.security.crypto.SecureRandomNumberGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Random;

/**
 * @author <a href="mailto:ms29.seo@gmail.com">ms29.seo</a>
 */
@SpringBootApplication
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
public class NestApplication extends WebMvcConfigurerAdapter {

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(10);
        configurer.setTaskExecutor(asyncTaskExecutor());
    }

    @Bean
    public AsyncTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(200);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.setThreadNamePrefix("async-task-executor-");
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PasswordEncryptor passwordEncryptor() {
        return new PBKDF2PasswordEncryptor();
    }

    @Bean
    public RandomNumberGenerator passwordSaltGenerator() {
        return new SecureRandomNumberGenerator();
    }

    @Bean
    public DateTimeService dateTimeService() {
        return new CurrentDateTimeService();
    }

    @Bean
    public DateTimeProvider dateTimeProvider(DateTimeService dateTimeService) {
        return new AuditingDateTimeProvider(dateTimeService);
    }

    public static void main(String[] args) {
        SpringApplication.run(NestApplication.class, args);
    }
}
