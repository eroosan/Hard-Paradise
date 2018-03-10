# Hard-Paradise

El proyecto Hard-Paradise consiste en una aplicación web, que consistirá en subir fotos de tu ordenador de sobremesa, para que los demás usuarios puedan observar tu trabajo a la hora de montarlo y diseñarlo, así como valorar tu gusto y la elección de piezas que has realizado para el proyecto, además podrán dejar comentarios con su opinión. 
Esta aplicación a su vez tendrá un top donde aparecerán los usuarios con mayor reputación por las valoraciones recibidas, y habrá otra sección donde salgan las últimas publicaciones en la aplicación, de esta manera todos los usuarios tendrán la posibilidad de ser vistos.

*Parte publica*: El usuario podrá únicamente ver las noticias sin necesidad de registrarse.
 
*Parte privada*: Una vez registrado el usuario podrá entrar al apartado de montajes para subir el suyo propio o ver el de los demás usuarios. Dentro de cada montaje el usuario puede valorarlo o hacer un comentario. Un usuario registrado tendrá la posibilidad de editar su perfil (Cambiar nombre de usuario, contraseña… etc).

 **Entidades**
 
* Usuario: Usuario que tiene la posibilidad de subir su montaje, editarlo o borrarlo, valorar y comentar los montajes de otros usuarios.
* Favorito: El usuario tiene la capacidad de marcar un montaje como favorito.
* Montaje: Publicación con fotos y descripción de los montajes del PC de los usuarios.
* Valoración: Valorar un montaje con una nota el montaje de un usuario.
* Comentario: Poner un comentario al montaje de un usuario.
* Seguir usuario: Un usuario puede seguir a otro para que le lleguen notificaciones de las publicaciones del usuario. 
* Noticia: Noticias sobre componentes Hardware. 

**Servicio interno**

Se llevará a cabo a través de un sistema de correos que avisará de notificaciones como la activación de la cuenta o interacciones con otros usuarios.

**Modelo entidad-relación**

![alt text](https://github.com/SexyBuggy/Hard-Paradise/blob/master/Capturas/ModeloEntidadRelacion.png)

**Modelo navegación web**

![alt text](https://github.com/SexyBuggy/Hard-Paradise/blob/master/Capturas/DiagramaNavegacion.png)

**Modelo de datos UML **

![alt text](https://github.com/SexyBuggy/Hard-Paradise/blob/master/Capturas/ModeloDatosUML.jpg)


**Capturas de pantalla**

Noticias:
Nos muestra en la misma pagina todas las noticias que se han publicado en la aplicacion, asi como permite publicar nuevas.

![alt text](https://github.com/SexyBuggy/Hard-Paradise/blob/master/Capturas/noticias.png)



Builds:
Nos muestra una vista previa de cada Montaje publicado por los usuarios, permitiendo acceder a cada uno de ellos individualmente.


![alt text](https://github.com/SexyBuggy/Hard-Paradise/blob/master/Capturas/builds.png)

Una vez accedido a un montaje, tenemos la posibilidad de leer la descripcion completa,y dar una valoracion, asi como de ver y realizar comentarios.


![alt text](https://github.com/SexyBuggy/Hard-Paradise/blob/master/Capturas/montaje.png)

Usuario:
Nos muestra nuestros datos de usuario, además de permitirnos ver los usuarios a los que seguimos, permitiéndolos modificarlos, asi como acceder a un nuevo registro e inicio de sesion.

![alt text](https://github.com/SexyBuggy/Hard-Paradise/blob/master/Capturas/perfil.png)


![alt text](https://github.com/SexyBuggy/Hard-Paradise/blob/master/Capturas/favoritos.png)

**INTRUCCIONES DE DESPLIEGUE DE APLICACIÓN**

Primero instalamos Java 8:
sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get update
sudo apt-get install openjdk-8-jre

A continuación instalamos el servicio MYSQL:
sudo apt-get update
sudo apt-get install -y mysql-server

Una vez terminado entramos en la consola de MYSQL y creamos la base de datos vacía que va a usar la aplicación web:
sudo mysql -u root -p
mysql>CREATE DATABASE test;
mysql>exit

Por último ejecutamos los jar necesarios en dos terminales. Solo hay que ir al directorio donde se encuentre el .jar y ejecutamos:
sudo java -jar hardParadise-0.0.1-SNAPSHOT-jar
sudo java -jar InternalService-0.0.1-SNAPSHOT-jar

En caso de tener un error de acceso a la base de datos al ejecutar los .jar, deberemos antes ejecutar (esto es debido a problemas con los privilegios que dependen de la versión de Ubuntu que estemos usando):
sudo mysql -u root -p
mysql>USE mysql;
mysql>SELECT User, Host, plugin FROM mysql.user;
mysql>UPDATE user SET plugin='mysql_native_password' WHERE User='root';
mysql>FLUSH PRIVILEGES;
mysql>Exit

**Miembros del equipo:**

* Nombre completo: Gabriel Eiraldi Zoff
* Correo oficial de universidad: g.eiraldi@alumnos.urjc.es
* GitHub: https://github.com/GabrielEZio

- Nombre completo: Rubén Oliver Dólera
- Correo oficial de universidad: r.oliverd@alumnos.urjc.es
- GitHub: https://github.com/ruben1314

* Nombre completo: Jorge Ruiz Molina
* Correo oficial de universidad: j.ruizmo@alumnos.urjc.es
* GitHub: https://github.com/SexyBuggy/

Trello: https://trello.com/b/j45jdP4u/hard-paradise
