package be.vdab.movies.reservaties;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Sql({"/films.sql", "/klanten.sql"})
class ReservatieServiceTest {
    private static final String FILMS_TABLE  = "films";
    private static final String RESERVATIES_TABLE = "reservaties";
    private final MockMvc mockMvc;
    private final JdbcClient jdbcClient;

    public ReservatieServiceTest(MockMvc mockMvc, JdbcClient jdbcClient) {
        this.mockMvc = mockMvc;
        this.jdbcClient = jdbcClient;
    }

    private int idVanEenTestFilm(){
        return jdbcClient.sql("select id from films where titel = 'test1'")
                .query(Integer.class)
                .single();
    }

    private int idVanEenTestKlant(){
        return jdbcClient.sql("select id from klanten where familienaam = 'testFam'")
                .query(Integer.class)
                .single();
    }
    @Test
    void createVoegtEenReservatieToeMetBestaandKlantEnFilmId()throws Exception{
        int klantId = idVanEenTestKlant();
        int filmId = idVanEenTestFilm();
        NieuweReservatie nieuweReservatie = new NieuweReservatie(klantId, filmId);
        ObjectMapper om = new ObjectMapper();
                mockMvc.perform(post("/films/reservaties")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(nieuweReservatie)))
                        .andExpect(status().isOk());
                assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcClient, FILMS_TABLE, "id = "+ filmId + " and gereserveerd = 3")).isOne();
                assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcClient, RESERVATIES_TABLE, "klantId = " + klantId + " and filmId = " + filmId)).isOne();
    }
}