package be.vdab.movies.klanten;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
class KlantService {
    private final KlantRepository klantRepository;

    KlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    List<Klant>findByStukWoord(String woord){
        return klantRepository.findByStukWoord(woord);
    }
}
