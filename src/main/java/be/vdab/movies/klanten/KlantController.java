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

    private record KlantZonderIdEnNamenAanElkaar(String voorEnAchternaam, String straatNummer, String postcode, String gemeente){
        KlantZonderIdEnNamenAanElkaar(Klant klant){
            this(klant.getFamilienaam() + " " + klant.getVoornaam(), klant.getStraatNummer(), klant.getPostcode(), klant.getGemeente());
        }
    }

    @GetMapping(params = "stukWoord")
    Stream<KlantZonderIdEnNamenAanElkaar>findByStukWoord(String stukWoord){
        return klantService.findByStukWoord(stukWoord)
                .stream()
                .map(KlantZonderIdEnNamenAanElkaar::new);
    }
}
