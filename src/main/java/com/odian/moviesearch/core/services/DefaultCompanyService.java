package com.odian.moviesearch.core.services;

import com.odian.moviesearch.core.services.validators.CompanyPageableValidator;
import com.odian.moviesearch.core.dao.CompanyDao;
import com.odian.moviesearch.core.dao.MediaDao;
import com.odian.moviesearch.core.exceptions.NotFoundException;
import com.odian.moviesearch.core.model.*;
import com.odian.moviesearch.core.model.utils.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class DefaultCompanyService implements CompanyService{

    private final CompanyDao companyDao;
    private final MediaDao mediaDao;
    private final CompanyPageableValidator validator;

    @Transactional
    @Override
    public Company create(Company company) {
        return companyDao.create(company);
    }

    @Override
    public Company findById(Long id) {
        return companyDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Company with this id not found"));
    }

    @Override
    public Company update(Company company) {
        return companyDao.update(company);
    }

    @Override
    public PagedResponse<Company> findAll(Pageable pageable) {
        return companyDao.findAll(pageable);
    }


}
