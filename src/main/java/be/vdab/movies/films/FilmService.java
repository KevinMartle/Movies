package be.vdab.movies.films;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    List<Integer> findByGenreId(int genreId){
        return filmRepository.findByGenreId(genreId);
    }
    Optional<Film>findById(int id){
        return filmRepository.findById(id);
    }
}
