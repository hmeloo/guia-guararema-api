package br.com.guararema.api.controller;

import br.com.guararema.api.dto.PlaceRequest;
import br.com.guararema.api.entity.Place;
import br.com.guararema.api.service.PlaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<Place>> listAll() {
        return ResponseEntity.ok(placeService.findAll());
    }

    @PostMapping
    public ResponseEntity<Place> create(@Valid @RequestBody PlaceRequest request) {
        Place place = Place.builder()
                .name(request.name())
                .category(request.category())
                .description(request.description())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(placeService.create(place));
    }
}
