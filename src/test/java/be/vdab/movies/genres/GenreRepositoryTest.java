package be.vdab.movies.genres;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(GenreRepository.class)
class GenreRepositoryTest {
    private final static String GENRES_TABLE = "genres";
    private final GenreRepository genreRepository;
    private final JdbcClient jdbcClient;

    public GenreRepositoryTest(GenreRepository genreRepository, JdbcClient jdbcClient) {
        this.genreRepository = genreRepository;
        this.jdbcClient = jdbcClient;
    }

    @Test
    void findAllGenresGeeftEenGesorteerdeLijstOpNaamTerug() {
        int aantalRecords = JdbcTestUtils.countRowsInTable(jdbcClient, GENRES_TABLE);
        assertThat(genreRepository.findAllGesorteerdOpNaam())
                .hasSize(aantalRecords)
                .extracting(Genre::getNaam)
                .isSorted();
    }
}