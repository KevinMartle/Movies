package be.vdab.movies.reservaties;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
class ReservatieRepository {
    private final JdbcClient jdbcClient;

    ReservatieRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    void createReservatie(Reservatie reservatie){
        String sql = """
                insert into reservaties(klantId, filmId, reservatie)
                values(?,?,?)
                """;
        jdbcClient.sql(sql)
                .params(reservatie.getKlantId(), reservatie.getFilmId(), reservatie.getReservatie())
                .update();
    }
}
