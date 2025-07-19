package com.odian.moviesearch.core.application.port.out;

import com.odian.moviesearch.core.domain.model.ProductionCompany;

public interface ProductionCompanyRepository {
    ProductionCompany create (ProductionCompany productionCompany);
}
