package com.odian.moviesearch.services;

import com.odian.moviesearch.dto.CountryDTO;
import com.odian.moviesearch.dto.GenreDTO;
import com.odian.moviesearch.dto.MovieDTO;
import com.odian.moviesearch.dto.ProductionStudioDTO;
import com.odian.moviesearch.exceptions.NotFoundException;
import com.odian.moviesearch.model.*;
import com.odian.moviesearch.model.enums.MediaType;
import com.odian.moviesearch.repositories.CountryRepository;
import com.odian.moviesearch.repositories.GenreRepository;
import com.odian.moviesearch.repositories.MovieRepository;
import com.odian.moviesearch.repositories.ProductionStudioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;
    private final ProductionStudioRepository productionStudioRepository;

    public MovieDTO findById (Long id) {
        var movie = movieRepository.findByIdWithDetails(id).orElseThrow(() -> new NotFoundException("Movie with this id not found"));
        return wrapper(movie);
    }

    @Transactional
    public MovieDTO save (MovieDTO movieDTO) {
        List<Genre> genres = genreRepository.findByNameIn(
                movieDTO.genres().stream().map(GenreDTO::name).toList()
        );
        List<Country> countries = countryRepository.findByNameIn(
                movieDTO.filmedAt().stream().map(CountryDTO::name).toList()
        );
        List<ProductionStudio> productionStudios = productionStudioRepository.findByNameIn(
                movieDTO.productionStudios().stream().map(ProductionStudioDTO::name).toList()
        );
        Movie movie = new Movie();
        movie.setName(movie.getName());
        movie.setSlogan(movieDTO.slogan());
        movie.setDescription(movieDTO.description());
        movie.setGenres(genres);
        movie.setMovieType(movieDTO.type());
        movie.setReleaseDate(movieDTO.releaseDate());
        movie.setDurationTime(movieDTO.durationTime());
        movie.setBudget(movieDTO.budget());
        movie.setRevenue(movieDTO.revenue());
        movie.setRating(movieDTO.rating());
        movie = movieRepository.save(movie);
        return wrapper(movie);
    }

    protected static MovieDTO wrapper (Movie movie) {
        return new MovieDTO(
                movie.getId(),
                movie.getName(),
                movie.getSlogan(),
                movie.getDescription(),
                movie.getGenres().stream().map(GenreService::wrapper).toList(),
                movie.getFilmedAt().stream().map(CountryService::wrapper).toList(),
                movie.getMovieScore().getScore(),
                movie.getMedias().stream()
                        .filter(media -> MediaType.POSTER.equals(media.getMediaType()))
                        .findFirst().map(Media::getUrl).orElse(null),
                movie.getProductionStudios().stream().map(ProductionStudioService::wrapper).toList(),
                movie.getMovieType(),
                movie.getReleaseDate(),
                movie.getDurationTime(),
                movie.getBudget(),
                movie.getRevenue(),
                movie.getRating()
        );
    }
}
