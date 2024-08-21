package be.vdab.movies.klanten;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("klanten")
public class KlantController {
    private final KlantService klantService;

    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    private record KlantMetNamenAanElkaar(int id, String voorEnAchternaam, String straatNummer, String postcode, String gemeente){
        KlantMetNamenAanElkaar(Klant klant){
            this(klant.getId(), klant.getFamilienaam() + " " + klant.getVoornaam(), klant.getStraatNummer(), klant.getPostcode(), klant.getGemeente());
        }
    }

    @GetMapping(params = "stukWoord")
    Stream<KlantMetNamenAanElkaar>findByStukWoord(String stukWoord){
        return klantService.findByStukWoord(stukWoord)
                .stream()
                .map(KlantMetNamenAanElkaar::new);
    }
}
