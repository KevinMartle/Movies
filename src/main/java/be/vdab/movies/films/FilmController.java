package be.vdab.movies.films;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.stream.Stream;

@RestController
@RequestMapping("films")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("{genreId}")
    Stream<Integer> findByGenreId(@PathVariable int genreId){
        return filmService.findByGenreId(genreId)
                .stream();
    }


    private record FilmZonderGenreId(int id, String titel, int voorraad, int gereserveerd, BigDecimal prijs){
        FilmZonderGenreId(Film film){
            this(film.getId(), film.getTitel(), film.getVoorraad(), film.getGereserveerd(), film.getPrijs());
        }
    }
    @GetMapping("film/{id}")
    FilmZonderGenreId findById(@PathVariable int id){
        return filmService.findById(id).map(film-> new FilmZonderGenreId(film)).orElseThrow(()-> new FilmNietGevondenException());
        }

}
