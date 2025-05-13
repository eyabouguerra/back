package backAgil.example.back.services;



import backAgil.example.back.models.Compartiment;
import java.util.List;
import java.util.Optional;


public interface CompartimentService {
    List<Compartiment> getAllCompartiments();
    Optional<Compartiment> getCompartimentById(Long id);
    List<Compartiment> getCompartimentsByCiterneId(Long citerneId);
    Compartiment addCompartiment(Compartiment compartiment);
    Compartiment updateCompartiment(Long id, Compartiment newCompartiment);
    void deleteCompartiment(Long id);
}