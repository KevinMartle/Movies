package be.vdab.movies.klanten;

import be.vdab.movies.genres.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(KlantRepository.class)
class KlantRepositoryTest {
    private static final String KLANTEN_TABLE = "klanten";
    private final KlantRepository klantRepository;
    private final JdbcClient jdbcClient;

    public KlantRepositoryTest(KlantRepository klantRepository, JdbcClient jdbcClient) {
        this.klantRepository = klantRepository;
        this.jdbcClient = jdbcClient;
    }



    @Test
    void findAllGenresGeeftEenGesorteerdeLijstOpNaamTerug() {
        int aantalRecords = JdbcTestUtils.countRowsInTableWhere(jdbcClient, KLANTEN_TABLE, "familienaam like '%test%'");
        assertThat(klantRepository.findByStukWoord("test"))
                .hasSize(aantalRecords)
                .extracting(Klant::getFamilienaam)
                .isSorted();
    }

}