package com.odian.moviesearch.dao.postgres.repository.implementations;

import com.odian.moviesearch.core.dao.CompanyDao;
import com.odian.moviesearch.core.exceptions.DaoException;
import com.odian.moviesearch.core.exceptions.ValidationException;
import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.core.model.utils.PageableResponse;
import com.odian.moviesearch.core.model.utils.SearchCriteria;
import com.odian.moviesearch.dao.postgres.mapper.CompanyEntityMapper;
import com.odian.moviesearch.dao.postgres.model.CompanyEntity;
import com.odian.moviesearch.dao.postgres.repository.implementations.utils.CompanySpecification;
import com.odian.moviesearch.dao.postgres.repository.interfaces.spring.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class DefaultCompanyDao implements CompanyDao {

    private final CompanyRepository repository;
    private final CompanyEntityMapper mapper;

    @Override
    public Company create(Company company) {
        try {
            return mapper.to(
                    repository.save(mapper.to(company))
            );
        } catch (DataIntegrityViolationException e) {
            throw new ValidationException("The same company already exist");
        } catch (Exception e) {
            throw new DaoException("Unexpected error occurred during connection to the database");
        }
    }

    @Override
    public Optional<Company> findById(Long id) {
        try {
            var company = repository.findById(id).orElse(null);
            return Optional.ofNullable(mapper.to(company));
        } catch (Exception e) {
            throw new DaoException("Unexpected error occurred during connection to the database");
        }
    }

    @Override
    public PageableResponse<Company> findAll(SearchCriteria criteria) {
        int page = criteria.getPageCriteria().getPage();
        int size = criteria.getPageCriteria().getSize();
        String sortBy = criteria.getSortCriteria().sortBy();
        String direction = criteria.getSortCriteria().getSortDirection().name();
        var sortable = Sort.by(Sort.Direction.fromString(direction), sortBy);
        var pageable = PageRequest.of(page, size, sortable);
        Specification<CompanyEntity> specification = CompanySpecification.fromFilter(criteria.getFilterCriteria());
        var list = repository.findAll(specification, pageable);
        return new PageableResponse<>(mapper.to(list.get().toList()),page,size,list.getTotalPages(), list.getTotalPages());
    }
}
