package be.vdab.movies.klanten;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class KlantRepository {
    private final JdbcClient jdbcClient;

    KlantRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    List<Klant>findByStukWoord(String woord){
        String sql = """
                select id, familienaam, voornaam, straatNummer, postcode, gemeente
                from klanten
                where familienaam like ?
                order by familienaam
                """;
        return jdbcClient.sql(sql)
                .params("%" + woord + "%")
                .query(Klant.class)
                .list();
    }
}
