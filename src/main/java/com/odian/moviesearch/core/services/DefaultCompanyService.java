package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.dao.CompanyDao;
import com.odian.moviesearch.core.dao.CountryDao;
import com.odian.moviesearch.core.dao.MediaDao;
import com.odian.moviesearch.core.exceptions.NotFoundException;
import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.core.model.Country;
import com.odian.moviesearch.core.model.Media;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class DefaultCompanyService implements CompanyService{

    private final CompanyDao companyDao;
    private final CountryDao countryDao;
    private final MediaDao mediaDao;

    @Transactional
    @Override
    public Company create(Company company) {
        Country country = countryDao.findById(company.getCountry().getId())
                .orElseThrow(() -> new NotFoundException("Country with this id not found"));
        Media media = mediaDao.create(company.getMedia());
        company.setCountry(country);
        company.setMedia(media);
        return companyDao.create(company);
    }
}
