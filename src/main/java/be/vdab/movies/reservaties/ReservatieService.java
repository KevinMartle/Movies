package be.vdab.movies.reservaties;

import be.vdab.movies.films.Film;
import be.vdab.movies.films.FilmNietGevondenException;
import be.vdab.movies.films.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
class ReservatieService {
    private final ReservatieRepository reservatieRepository;
    private final FilmRepository filmRepository;

    ReservatieService(ReservatieRepository reservatieRepository, FilmRepository filmRepository) {
        this.reservatieRepository = reservatieRepository;
        this.filmRepository = filmRepository;
    }

    @Transactional(readOnly = false)
    void createReservatieEnUpdateFilmGereserveerd(NieuweReservatie nieuweReservatie){
        Film film = filmRepository.findAndLockById(nieuweReservatie.filmId()).orElseThrow(FilmNietGevondenException::new);
        film.maakReservatie();
        filmRepository.updateFilmGereserveerd(film.getId(), film.getGereserveerd());
        Reservatie reservatie = new Reservatie(nieuweReservatie.klantId(),film.getId(), LocalDateTime.now());
        reservatieRepository.createReservatie(reservatie);
    }
}
