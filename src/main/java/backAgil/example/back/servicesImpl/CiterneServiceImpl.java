package backAgil.example.back.servicesImpl;

import backAgil.example.back.models.Citerne;
import backAgil.example.back.repositories.CiterneRepository;
import backAgil.example.back.services.CiterneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiterneServiceImpl implements CiterneService {

    @Autowired
    private CiterneRepository citerneRepository;

    @Override
    public List<Citerne> getAllCiternes() {
        return citerneRepository.findAll();
    }

    @Override
    public Optional<Citerne> getCiterneById(Long id) {
        return citerneRepository.findById(id);
    }

    @Override
    public Citerne addCiterne(Citerne citerne) {
        return citerneRepository.save(citerne);
    }

    @Override
    public Citerne updateCiterne(Long id, Citerne newCiterne) {
        return citerneRepository.findById(id).map(citerne -> {
            citerne.setReference(newCiterne.getReference());
            citerne.setCapacite(newCiterne.getCapacite());
            return citerneRepository.save(citerne);
        }).orElse(null);
    }

    @Override
    public void deleteCiterne(Long id) {
        citerneRepository.deleteById(id);
    }
}