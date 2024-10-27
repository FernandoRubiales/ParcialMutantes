package com.example.primerParcial.services;

import com.example.primerParcial.dtos.DtoStats;
import com.example.primerParcial.entities.Adn;
import com.example.primerParcial.repositories.AdnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdnService {
    @Autowired
    private AdnRepository adnRepository;

    private static final int tamañoSecuencia = 4;


    //Metodo de verificacion si es mutante o no
    public boolean isMutant(String[] dna){

        //Validamos primero el array, si este cumple los requisitos, buscamos las secuencias validas

        if (!condicionArray(dna)){
            return false; //si la validacion del array de adn no se cumple, entonces retorna false
        }

        int longArray = dna.length;
        char[][] matriz = armarMatriz(dna); //llamamos al metodo que arma la matriz de caracteres
        int cantSecuencia = 0;

        //RECORREMOS LA MATRIZ
        for (int i=0; i<longArray; i++){
            for (int j=0; j<longArray; j++){
                if(comprobacionHorizontal(matriz, i, j, longArray)){
                    cantSecuencia++; //si se encontro una secuencia en una comprobacion horizontal, se suma en el contador de secuencia total
                }
                if (comprobacionVertical(matriz, i, j, longArray)){
                    cantSecuencia++;
                }
                if (comprobacionDiagonalIzquierda(matriz, i, j, longArray)){
                    cantSecuencia++;
                }
                if(comprobacionDiagonalDerecha(matriz, i, j, longArray)){
                    cantSecuencia++;
                }

                if(comprobacionDiagonalAbajoArribaIzquierda(matriz, i, j, longArray)){
                    cantSecuencia++;
                }

                if(comprobacionDiagonalAbajoArribaDerecha(matriz, i,j , longArray)){
                    cantSecuencia++;
                }

                if (cantSecuencia > 1){
                    //llamamos al metodo que guarda el adn en la base de datos
                    guardarAdn(dna, true);
                    return true;
                }
            }
        }
        guardarAdn(dna,false); //como no hay secuencias, no es mutante, y se guarda en la BD
        return false;
    }

    private boolean condicionArray (String[] dna){

        int n = dna.length; //tamaño del arreglo
        if(dna == null || dna.length == 0){
            System.out.println("El arreglo no puede ser vacio");
            return false;
        }
        //cada cadena debe tener la misma longitud que el numero "n" que seria la cantidad de cadenas en el arreglo
        for(String cadena : dna){
            if(cadena.length() != n || cadena.length() < tamañoSecuencia){
                System.out.println("La longitud de las cadenas no es igual a la cantidad de cadenas, no se puede formar matriz NxN");
                return false;
            }
            //Comprobar que las letras de la cadena solo sean A,T,C o G
            for(char car : cadena.toCharArray()){
                if(car != 'A' && car != 'T' && car != 'C' && car != 'G'){ //si el caracter no es igual a ninguno de estos caracteres, entonces sera invalido
                    System.out.println("Caracter invalido para la cadena");
                    return false;
                }
            }
        }
        return true;

    }

    //Armamos la matriz
    public char[][] armarMatriz(String[] dna){
        int n = dna.length;
        char [][] matriz = new char[n][n];
        for (int i=0;i<n; i++){
            matriz[i] = dna[i].toCharArray(); //convertimos la cadena en una array de caracteres y se asgina a la fila de la matriz
        }

        return matriz;
    }


    // Comprobación horizontal
    private boolean comprobacionHorizontal(char[][] matriz, int fila, int col, int longArray) {
        if (col + tamañoSecuencia > longArray) return false;

        if (matriz[fila][col] != matriz[fila][col + tamañoSecuencia - 1]) return false;

        char letraInicial = matriz[fila][col];
        for (int i = 1; i < tamañoSecuencia - 1; i++) {
            if (matriz[fila][col + i] != letraInicial) return false;
        }
        return true;
    }

    // Comprobación vertical
    private boolean comprobacionVertical(char[][] matriz, int fila, int col, int longArray) {
        if (fila + tamañoSecuencia > longArray) return false;

        if (matriz[fila][col] != matriz[fila + tamañoSecuencia - 1][col]) return false;

        char letraInicial = matriz[fila][col];
        for (int i = 1; i < tamañoSecuencia - 1; i++) {
            if (matriz[fila + i][col] != letraInicial) return false;
        }
        return true;
    }

    // Comprobación diagonal de arriba a abajo (izquierda-derecha)
    private boolean comprobacionDiagonalIzquierda(char[][] matriz, int fila, int col, int longArray) {
        if (fila + tamañoSecuencia > longArray || col + tamañoSecuencia > longArray) return false;

        if (matriz[fila][col] != matriz[fila + tamañoSecuencia - 1][col + tamañoSecuencia - 1]) return false;

        char letraInicial = matriz[fila][col];
        for (int i = 1; i < tamañoSecuencia - 1; i++) {
            if (matriz[fila + i][col + i] != letraInicial) return false;
        }
        return true;
    }

    // Comprobación diagonal de arriba a abajo (derecha-izquierda)
    private boolean comprobacionDiagonalDerecha(char[][] matriz, int fila, int col, int longArray) {
        if (fila + tamañoSecuencia > longArray || col - tamañoSecuencia < -1) return false;

        if (matriz[fila][col] != matriz[fila + tamañoSecuencia - 1][col - tamañoSecuencia + 1]) return false;

        char letraInicial = matriz[fila][col];
        for (int i = 1; i < tamañoSecuencia - 1; i++) {
            if (matriz[fila + i][col - i] != letraInicial) return false;
        }
        return true;
    }

    // Comprobación diagonal de abajo a arriba (izquierda-derecha) con optimización
    private boolean comprobacionDiagonalAbajoArribaIzquierda(char[][] matriz, int fila, int col, int longArray) {
        if (fila - tamañoSecuencia < -1 || col + tamañoSecuencia > longArray) return false;

        if (matriz[fila][col] != matriz[fila - tamañoSecuencia + 1][col + tamañoSecuencia - 1]) return false;

        char letraInicial = matriz[fila][col];
        for (int i = 1; i < tamañoSecuencia - 1; i++) {
            if (matriz[fila - i][col + i] != letraInicial) return false;
        }
        return true;
    }

    // Comprobación diagonal de abajo a arriba (derecha-izquierda) con optimización
    private boolean comprobacionDiagonalAbajoArribaDerecha(char[][] matriz, int fila, int col, int longArray) {
        if (fila - tamañoSecuencia < -1 || col - tamañoSecuencia < -1) return false;

        if (matriz[fila][col] != matriz[fila - tamañoSecuencia + 1][col - tamañoSecuencia + 1]) return false;

        char letraInicial = matriz[fila][col];
        for (int i = 1; i < tamañoSecuencia - 1; i++) {
            if (matriz[fila - i][col - i] != letraInicial) return false;
        }
        return true;
    }


    //Guardamos el adn verificado en la base de datos
    private void guardarAdn(String[] dna, boolean isMutant){
        //convertimos el arreglo en un String
        String dnaString = String.join(",", dna);

        Optional<Adn> existeDna = adnRepository.findByDna(dnaString);

        if(existeDna.isPresent()){
            System.out.println("El ADN ya esta registrado");
            return;
        }
        //Si no fue registrado entonces creamos la instancia y la guardamos

        //se crea la instancia
        Adn adn = new Adn();
        adn.setDna(dnaString);
        adn.setMutant(isMutant);
        //lo persistimos en la base de datos
        adnRepository.save(adn);
        System.out.println("El ADN ha sido guardado");

    }

    //Calculo de las estadisticas de mutantes y humanos

    public DtoStats obtenerEstadisticas(){

        //Contamos los ADN mutantes y humanos
        long countMutantDna = adnRepository.cuentaMutantes();
        long countHumanDna = adnRepository.cuentaHumanos();

        //se calcula el ratio
        double ratio = 0.0;
        if (countHumanDna + countMutantDna > 0){
            ratio = (double) countMutantDna / (countHumanDna + countMutantDna);
        }

        DtoStats stats = new DtoStats(countMutantDna,countHumanDna,ratio);
        return stats;
    }

}
