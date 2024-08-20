package be.vdab.movies.genres;

import be.vdab.movies.films.Film;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    Stream<Genre>findAllGesorteerdOpNaam(){
        return genreService.findAllGesorteerdOpNaam()
                .stream();
    }





}
