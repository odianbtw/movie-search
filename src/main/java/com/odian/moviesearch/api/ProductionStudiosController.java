package com.odian.moviesearch.api;


import com.odian.moviesearch.services.ProductionStudioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//todo
@RestController
@RequestMapping("/production_studios")
@AllArgsConstructor
public class ProductionStudiosController {
    private final ProductionStudioService productionStudioService;
}
