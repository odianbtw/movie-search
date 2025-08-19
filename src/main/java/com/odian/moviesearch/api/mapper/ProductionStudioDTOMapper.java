package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.StudioDTO;
import com.odian.moviesearch.core.domain.model.ProductionStudio;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductionStudioDTOMapper {

    StudioDTO domainToDto (ProductionStudio productionStudio);

    ProductionStudio idToDomain (UUID id);
}
