package backAgil.example.back.servicesImpl;

import backAgil.example.back.models.Citerne;
import backAgil.example.back.models.Compartiment;
import backAgil.example.back.repositories.CiterneRepository;
import backAgil.example.back.repositories.CompartimentRepository;
import backAgil.example.back.services.CompartimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompartimentServiceImpl implements CompartimentService {

    @Autowired
    private CompartimentRepository compartimentRepository;

    @Autowired
    private CiterneRepository citerneRepository;

    @Override
    public List<Compartiment> getAllCompartiments() {
        return compartimentRepository.findAll();
    }

    @Override
    public Optional<Compartiment> getCompartimentById(Long id) {
        return compartimentRepository.findById(id);
    }

    @Override
    public List<Compartiment> getCompartimentsByCiterneId(Long citerneId) {
        Citerne citerne = citerneRepository.findById(citerneId)
                .orElseThrow(() -> new IllegalArgumentException("Citerne not found"));
        List<Compartiment> compartiments = compartimentRepository.findByCiterneId(citerneId);

        double totalCompartimentCapacity = compartiments.stream().mapToDouble(Compartiment::getCapaciteMax).sum();

        if (totalCompartimentCapacity > citerne.getCapacite()) {
            throw new IllegalArgumentException("Total compartments capacity exceeds citerne capacity");
        }

        return compartiments;
    }


    // Méthode dans le service pour ajouter un compartiment
    @Override
    public Compartiment addCompartiment(Compartiment compartiment) {
        // Vérifier si l'ID de la citerne est fourni
        if (compartiment.getCiterne() == null || compartiment.getCiterne().getId() == null) {
            throw new IllegalArgumentException("Citerne ID must be provided");
        }

        // Vérifier si la référence du compartiment est fournie
        if (compartiment.getReference() == null || compartiment.getReference().isEmpty()) {
            throw new IllegalArgumentException("Reference must be provided");
        }

        // Récupérer la citerne associée
        Citerne citerne = citerneRepository.findById(compartiment.getCiterne().getId())
                .orElseThrow(() -> new IllegalArgumentException("Citerne does not exist"));

        // Vérifier si la capacité totale des compartiments ne dépasse pas la capacité de la citerne
        double capaciteTotale = compartimentRepository.sumCapaciteByCiterneId(citerne.getId());
        if (capaciteTotale + compartiment.getCapaciteMax() > citerne.getCapacite()) {
            throw new IllegalArgumentException("La capacité du compartiment dépasse celle de la citerne.");
        }

        // Vérifier si le nombre de compartiments ne dépasse pas le nombre maximum autorisé
        long nombreCompartiments = compartimentRepository.countByCiterneId(citerne.getId());
        if (nombreCompartiments >= citerne.getNombreCompartiments()) {
            throw new IllegalArgumentException("Le nombre de compartiments dans la citerne a atteint la limite.");
        }

        // Lier la citerne au compartiment
        compartiment.setCiterne(citerne);

        // Sauvegarder le compartiment dans la base de données
        return compartimentRepository.save(compartiment);
    }



}