package be.vdab.movies.films;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class FilmRepository {
    private final JdbcClient jdbcClient;

    public FilmRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    List<Integer> findByGenreId(int genreId){
        String sql = """
                select id
                from films
                where genreId = ?
                order by titel
                """;
        return jdbcClient.sql(sql)
                .param(genreId)
                .query(Integer.class)
                .list();
    }

    Optional<Film> findById(int id){
        String sql = """
                select id, genreId, titel, voorraad, gereserveerd, prijs
                from films
                where id = ?
                """;
        return jdbcClient.sql(sql)
                .param(id)
                .query(Film.class)
                .optional();
    }

    public Optional<Film> findAndLockById(int id){
        String sql = """
                select id, genreId, titel, voorraad, gereserveerd, prijs
                from films
                where id = ?
                for update
                """;
        return jdbcClient.sql(sql)
                .param(id)
                .query(Film.class)
                .optional();
    }


   public void updateFilmGereserveerd(int id, int gereserveerd){
        String sql = """
                update films
                set gereserveerd = ?
                where id = ?
                """;
        jdbcClient.sql(sql)
                .params(gereserveerd, id)
                .update();
    }
}
