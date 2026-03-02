package br.eti.kge.airports.controllers;

import br.eti.kge.airports.DTO.AirportMinDTO;
import br.eti.kge.airports.entities.Airport;
import br.eti.kge.airports.service.AirportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirportController {

    @Autowired
    private AirportService airportService;

    /**
     * Retorna TODOS os aeroportos.
     *
     * @return
     */
    @GetMapping("/airport")

    public ResponseEntity<List<Airport>> findAll() {
        List<Airport> result = airportService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/city/{cityName}")
    public ResponseEntity<List<AirportMinDTO>> findByCityIgnoreCase(@PathVariable String cityName) {

        List<AirportMinDTO> result = airportService.findByCountry(cityName);

        if (result.isEmpty()) {
            // Ops... lista vazia...
            // notFound devolve 404
            return ResponseEntity.notFound().build();
        } else {
            // Eba! Tem dados!
            // ok devolve 200
            return ResponseEntity.ok(result);

        }

    }

    /**
     * Retorna aeroportos por país.
     *
     * @param countryName
     * @return
     */
    @GetMapping("/country/{countryName}")
    public ResponseEntity<List<AirportMinDTO>> findByCountryIgnoreCase(@PathVariable String countryName) {

        List<AirportMinDTO> result = airportService.findByCountry(countryName);

        if (result.isEmpty()) {
            // Ops... lista vazia...
            // notFound devolve 404
            return ResponseEntity.notFound().build();
        } else {
            // Eba! Tem dados!
            // ok devolve 200
            return ResponseEntity.ok(result);
        }
    }
}
