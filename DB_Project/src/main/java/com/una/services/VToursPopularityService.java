package com.una.services;
import com.una.models.VToursPopularity;
import com.una.repositories.VToursPopularityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VToursPopularityService {

    private final VToursPopularityRepository repository;

    public VToursPopularityService(VToursPopularityRepository repository){
        this.repository = repository;
    }

    public List<VToursPopularity> findAll(){
        return repository.findAll();
    }
}
