package com.example.primerParcial.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DtoRequestAdn {

    //Arreglo que enviaremos en el body
    private String [] dna;

}
