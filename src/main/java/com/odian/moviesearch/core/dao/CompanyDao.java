package com.odian.moviesearch.core.dao;

import com.odian.moviesearch.core.model.Company;
import com.odian.moviesearch.core.model.PagedResponse;
import com.odian.moviesearch.core.model.utils.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CompanyDao {
    Company create (Company company);

}
