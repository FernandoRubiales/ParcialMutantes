package com.example.primerParcial.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DtoStats {

    private long count_Mutant_Dna;
    private long count_Human_Dna;
    private double ratio;

}
