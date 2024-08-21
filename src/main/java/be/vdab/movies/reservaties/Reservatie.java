package be.vdab.movies.reservaties;

import java.time.LocalDateTime;

public class Reservatie {
    private final int klantId;
    private final int filmId;
    private final LocalDateTime reservatie;

    public Reservatie(int klantId, int filmId, LocalDateTime reservatie) {
        this.klantId = klantId;
        this.filmId = filmId;
        this.reservatie = reservatie;
    }

    public int getKlantId() {
        return klantId;
    }

    public int getFilmId() {
        return filmId;
    }

    public LocalDateTime getReservatie() {
        return reservatie;
    }
}
