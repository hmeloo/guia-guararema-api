package br.com.guararema.api.service;

import br.com.guararema.api.entity.Place;
import br.com.guararema.api.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    public Place create(Place place) {
        return placeRepository.save(place);
    }
}
