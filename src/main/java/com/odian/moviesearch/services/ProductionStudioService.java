package com.odian.moviesearch.services;

import com.odian.moviesearch.dto.ProductionStudioDTO;
import com.odian.moviesearch.model.ProductionStudio;
import com.odian.moviesearch.repositories.ProductionStudioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductionStudioService {
    private ProductionStudioRepository productionStudioRepository;

    public List<ProductionStudioDTO> findAll () {
        return productionStudioRepository.findAll().stream()
                .map(ProductionStudioService::wrapper)
                .toList();
    }

    protected static ProductionStudioDTO wrapper (ProductionStudio studio) {
        return new ProductionStudioDTO(
                studio.getId(),
                studio.getName()
        );
    }
}
