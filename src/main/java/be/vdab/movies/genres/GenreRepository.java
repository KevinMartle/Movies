package be.vdab.movies.genres;

import be.vdab.movies.films.Film;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreRepository {
    private final JdbcClient jdbcClient;

    public GenreRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    List<Genre>findAllGesorteerdOpNaam(){
        String sql = """
                select id, naam
                from genres
                order by naam
                """;
        return jdbcClient.sql(sql)
                .query(Genre.class)
                .list();
    }



}
