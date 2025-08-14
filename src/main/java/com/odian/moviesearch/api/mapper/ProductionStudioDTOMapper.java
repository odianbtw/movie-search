package com.odian.moviesearch.api.mapper;

import com.odian.moviesearch.api.model.StudioDTO;
import com.odian.moviesearch.core.domain.model.ProductionStudio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductionStudioDTOMapper {

    StudioDTO domainToDto (ProductionStudio productionStudio);


}
