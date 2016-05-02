package net.brainage.nest.data.model;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ms29.seo on 2016-05-03.
 */
@Configuration
public class ModelMapperTestContextConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
