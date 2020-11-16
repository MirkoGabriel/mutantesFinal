# mutantesFinal
-Use mysql como gestor de base de datos

-JDBC para la conectividad con base de datos en java.

-Cada dna que cumple con las condiciones (A,C,G,T) es evaluado para designarlo como "mutante" o "humano".

-En cualquiera de los casos lo inserta en la base de datos con un identificador.

-El metodo get stats retorna el objeto con la cantidad de cada uno y el radio de mutantes sobre el total.

-En el package Conexion esta la clase que conecta a la BBDD que la misma esta en un host(freesqldata).

-En el package model esta los modelos dna y resultado del stat.

-En el package rest esta los servicios de la api.

-La BBDD esta con

host y la bbdd :sql10.freesqldatabase.com/sql10376928

user:sql10376928

pass:BzBmU4rr3C

El nivel 3 esta deployada en:

https://mutantes-ultimo.herokuapp.com

Servicio /stats:

https://mutantes-ultimo.herokuapp.com/stats
