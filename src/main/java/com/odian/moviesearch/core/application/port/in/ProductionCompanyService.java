package com.odian.moviesearch.core.application.port.in;

import com.odian.moviesearch.core.domain.model.ProductionCompany;

public interface ProductionCompanyService {
//    PagedResponse<ProductionCompany> findAll ()
    ProductionCompany create (ProductionCompany productionCompany);
}
