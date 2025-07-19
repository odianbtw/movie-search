package com.odian.moviesearch.unit.dao.postgres.repository.spring;


import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataCountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SpringDataCountryRepositoryTest extends AbstractSpringDataTest {

    @Autowired
    private SpringDataCountryRepository countryRepository;

    @Test
    void testFindAll () {
        var countries = countryRepository.findAll();
        assertThat(countries).isNotEmpty();
    }
}
