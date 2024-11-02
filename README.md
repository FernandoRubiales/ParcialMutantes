FUNCIONAMIENTO Y USO DE API:

1) Para poder correr nuestra API, les dejo la URL de Render donde esta subida la misma: https://parcialmutantes-ps5x.onrender.com
  
2) Para poder probar el funcionamiento de la API podemos utilizar POSTMAN, y realizar las peticiones (GET y POST en este caso).
   
3) Para el metodo "POST", la URL proporcionada es: https://parcialmutantes-ps5x.onrender.com/api/v1/adn/mutant

4) Luego en el body tenemos que mandar el arreglo de Strings (la cual indica la secuencia de ADN) en formato JSON, les dejo un ejemplo: { “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

5) En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un 403-Forbidden.

6) Para el metodo "GET", el cual nos devuelve un JSON con las estadisticas de las verificaciones de ADN, la URL proporcionada es: https://parcialmutantes-ps5x.onrender.com/api/v1/adn/stats

7) Este metodo nos devuelve la cantidad de mutantes, la cantidad de humanos y el ratio.


Resumen: Como conclusión, la API esta subida en Render, donde mediante su URL podremos ponerla en funcionamiento realizando el HTTP POST o el HTTP GET, cada metodo con
sus respectivas URL. Postman en una app donde podremos hacer las peticiones y probar el funcionamiento.

Para ver si es un mutante o no, el formato del arreglo de Strings para ingresar en el body del metodo POST en Postman, debe ser del siguiente formato:  { “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}


Si verifica que el arreglo de Strings es un mutante, nos va a mostrar que es mutante y se guardará en la base de datos con un booleano igual a TRUE (indicando que es Mutante) en caso de que no haya sido guardado antes.

Luego si por lo contrario, no es un mutante, también se guardará en la base de datos pero con un booleano igual a FALSE (indicando que no es Mutante) en caso de que no haya sido guardado antes.
