package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.CompanyPageableValidator;
import com.odian.moviesearch.core.dao.CompanyDao;
import com.odian.moviesearch.core.dao.CountryDao;
import com.odian.moviesearch.core.dao.MediaDao;
import com.odian.moviesearch.core.exceptions.NotFoundException;
import com.odian.moviesearch.core.model.*;
import com.odian.moviesearch.core.model.enums.MediaType;
import com.odian.moviesearch.core.model.utils.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class DefaultCompanyService implements CompanyService{

    private final CompanyDao companyDao;
    private final CountryDao countryDao;
    private final MediaDao mediaDao;
    private final CompanyPageableValidator validator;

    @Transactional
    @Override
    public Company create(Company company) {
        Country country = countryDao.findById(company.getCountry().getId())
                .orElseThrow(() -> new NotFoundException("Country with this id not found"));
        Media media = company.getMedia();
        media.setMediaType(MediaType.LOGO);
        media.setName(company.getName() + "_logo");
        company.setCountry(country);
        company.setMedia(mediaDao.create(media));
        return companyDao.create(company);
    }


    public PagedResponse<Company> findAll (Pageable pageable) {
        validator.validate(pageable);
        return companyDao.findAll(pageable);
    }

    @Override
    public Company findById(Long id) {
        return companyDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Company with this id not found"));
    }

    @Transactional
    @Override
    public Company update(Company company) {
        return companyDao.update(company);
    }
}
