package com.example.primerParcial.controllers;

import com.example.primerParcial.dtos.DtoRequestAdn;
import com.example.primerParcial.dtos.DtoStats;
import com.example.primerParcial.services.AdnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/adn")
public class AdnController {

    @Autowired
    private AdnService adnService;

    //verificar si es mutante
    @PostMapping("/mutant")
    public ResponseEntity<?>isMutant(@RequestBody DtoRequestAdn dtoRequestAdn){
        boolean isMutant = adnService.isMutant(dtoRequestAdn.getDna()); //la matriz se pasa al service para verificar si es mutante
        if (isMutant){
            return ResponseEntity.ok("Es mutante");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No es mutante");
        }
    }

    //estadisticas de verificaciones de ADN
    @GetMapping("/stats")
    public ResponseEntity<DtoStats> obtenerEstadisticas(){
        DtoStats stats = adnService.obtenerEstadisticas();
        return ResponseEntity.ok(stats);
    }
}
