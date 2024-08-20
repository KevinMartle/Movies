package be.vdab.movies.films;

import java.math.BigDecimal;

public class Film {
    private final int id;
    private final int genreId;
    private final String titel;
    private final int voorraad;
    private int gereserveerd;
    private final BigDecimal prijs;

    public Film(int gereserveerd, int id, int genreId, String titel, int voorraad, BigDecimal prijs) {
        this.gereserveerd = gereserveerd;
        this.id = id;
        this.genreId = genreId;
        this.titel = titel;
        this.voorraad = voorraad;
        this.prijs = prijs;
    }

    public int getId() {
        return id;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getTitel() {
        return titel;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public int getGereserveerd() {
        return gereserveerd;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    
}