package be.vdab.movies.films;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(FilmRepository.class)
@Sql("/films.sql")
class FilmRepositoryTest {
    private final static String FILMS_TABLE = "films";
    private final FilmRepository filmRepository;
    private final JdbcClient jdbcClient;

    public FilmRepositoryTest(FilmRepository filmRepository, JdbcClient jdbcClient) {
        this.filmRepository = filmRepository;
        this.jdbcClient = jdbcClient;
    }

    private int idVanEenTestFilm(){
        return jdbcClient.sql("select id from films where titel = 'test1'")
                .query(Integer.class)
                .single();
    }
    private int gereserveerdVanEenTestFilm(){
        return jdbcClient.sql("select gereserveerd from films where titel = 'test1'")
                .query(Integer.class)
                .single();
    }
    @Test
    void updateFilmGereserveerdUpdateEenFilm(){
        int id = idVanEenTestFilm();
        filmRepository.updateFilmGereserveerd(id, 5);
        int aantalRecords = JdbcTestUtils.countRowsInTableWhere(jdbcClient, FILMS_TABLE, "id = " + id + " and gereserveerd = 5");
        assertThat(aantalRecords).isOne();
    }

}