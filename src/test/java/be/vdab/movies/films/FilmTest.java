package be.vdab.movies.films;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FilmTest {

    @Test
    public void maakReservatieVerhoogtGereserveerdMet1(){
        Film film = new Film (0,0, "test", 1, 0, BigDecimal.ONE);
        film.maakReservatie();
        assertThat(film.getGereserveerd()).isOne();

    }

    @Test
    public void maakReservatieThrowsGeenVoorraadMeerExceptionAlsVoorraadGelijkIsAanGereserveerd(){
        Film film = new Film (0, 0, "test", 1, 1, BigDecimal.ONE);
        assertThatExceptionOfType(GeenVoorraadMeerException.class).isThrownBy(()->film.maakReservatie());

    }

}