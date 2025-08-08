package com.odian.moviesearch.core.application.port.out;

import java.util.Optional;

public interface ProductionCompanyRepository {
    ProductionCompany create (ProductionCompany productionCompany);
    Optional<ProductionCompany> findById (Long id);
    ProductionCompany update (ProductionCompany company);
}
