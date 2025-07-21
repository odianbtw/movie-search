package com.odian.moviesearch.unit.dao.postgres.repository.spring;


import com.odian.moviesearch.dao.postgres.entity.CountryEntity;
import com.odian.moviesearch.dao.postgres.repository.spring.SpringDataCountryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringDataCountryRepositoryTest {

    @Autowired
    private SpringDataCountryRepository countryRepository;


    @Test
    void testFindAll () {
        var countries = countryRepository.findAll();
        assertThat(countries).isNotEmpty();
    }

    @Test
    void testSaveCountry () {
        var size = countryRepository.findAll().size();
        CountryEntity entity = new CountryEntity();
        entity.setName("Some Republic");
        CountryEntity entity2 = new CountryEntity();
        entity2.setName("Some Republica");
        countryRepository.save(entity);
        countryRepository.save(entity2);
        assertEquals(size+2, countryRepository.findAll().size());
    }
}
