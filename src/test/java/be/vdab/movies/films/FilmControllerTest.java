package be.vdab.movies.films;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@Sql("/films.sql")
@AutoConfigureMockMvc
class FilmControllerTest {
    private final static String FILMS_TABLE = "films";
    private final MockMvc mockMvc;
    private final JdbcClient jdbcClient;

    public FilmControllerTest(MockMvc mockMvc, JdbcClient jdbcClient) {
        this.mockMvc = mockMvc;
        this.jdbcClient = jdbcClient;
    }

    private int genreIdVanEenTestFilm(){
        return jdbcClient.sql("select genreId from films where titel = 'test1'")
                .query(Integer.class)
                .single();
    }
    private int idVanEenTestFilm(){
        return jdbcClient.sql("select id from films where titel = 'test1'")
                .query(Integer.class)
                .single();
    }

    @Test
    void findByGenreIdVindtAlleFilmsPerGenre() throws Exception{
        int genreId = genreIdVanEenTestFilm();
        mockMvc.perform(get("/films/{genreId}", genreId))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("length()")
                                .value(JdbcTestUtils.countRowsInTableWhere(jdbcClient, FILMS_TABLE, "genreId = " + genreId))

                );
    }

    @Test
    void findByIdVindtEenFilmMetEenBestaandId()throws Exception{
        int id = idVanEenTestFilm();
        mockMvc.perform(get("/films/film/{id}", id))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("id").value(id),
                        jsonPath("titel").value("test1")
                );

    }

    @Test
    void findByIdThrowsFilmNotFoundExceptionMetOnbestaandeId() throws Exception{
        mockMvc.perform(get("/films/film/{id}", Integer.MAX_VALUE))
                .andExpect(status().isNotFound());
    }
}