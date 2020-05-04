Para correr esta app, debe primero tener una imagen cassandra 3.11.1 y ejecutar los siguientes pasos:


Instrucciones para instalar cassandra

Generar un archivo docker-compose.yml
cassandra:
  container_name: my-cassandra
  image: cassandra:3.11.1
  ports:
    - 9042:9042

ejecutar docker-compose up

Ahora corremos cqlsh dentro del contenedor con este comando

docker exec -it my-cassandra cqlsh

E iniciamos la construccion del schema dentro del cqlsh.

create keyspace localkeyspace with replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};

use localkeyspace;

CREATE TABLE distancia(id uuid,pais varchar,distanciamayor float, distanciamenor float,invocaciones float,PRIMARY KEY(id));

SELECT * FROM distancia; -->Deberia poder verse la tabla vacia.

Realizado esto podemos correr la aplicacion