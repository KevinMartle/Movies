package be.vdab.movies.reservaties;

import org.springframework.web.bind.annotation.*;

@RestController

public class ReservatieController {
    private final ReservatieService reservatieService;

    public ReservatieController(ReservatieService reservatieService) {
        this.reservatieService = reservatieService;
    }

    @PostMapping("films/reservaties")
    void create(@RequestBody NieuweReservatie nieuweReservatie){
        reservatieService.createReservatieEnUpdateFilmGereserveerd(nieuweReservatie);
    }



}
