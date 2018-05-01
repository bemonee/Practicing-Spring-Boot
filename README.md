# TP-4-Monitoreo - Ramiro Pereyra
# Maven

## Goals:
 - **mvn clean:** limpia todas las clases compiladas del proyecto
 - **mvn compile:** compila el proyecto entero
 - **mvn package:** empaqueta el proyecto compilado a un formato distribuible. JAR, WAR, etc.
 - **mvn install:** instala el/los artefactos en el repositorio local.

## Scopes:
 - **compile**: opcion por defecto si no especificamos el scope. Indica que la dependencia es requerida para compilar el proyecto. La dependencia de este tipo se propaga en los proyectos dependientes del actual.
 - **provided**: idéntica a la compile pero se espera que el contenedor ya cuente con esa librería, ya que es de uso común. Un claro ejemplo es cuando desplegamos en un servidor de aplicaciones, que por defecto, tiene bastantes librerías que utilizaremos en el proyecto, así que no necesitamos desplegar la dependencia.
 - **runtime**: La dependencia es necesaria en tiempo de ejecución pero no es necesaria para compilar.
 - **test**: La dependencia es solo para testing que es una de las fases de compilación con maven. JUnit es un claro ejemplo de esto.
 - **system**: Es como provided pero hay que incluir la dependencia explicitamente en donde estará ubicada a través de un path.
 - **import**: scope que solo se usa en la sección dependencyManagement para el tipo de dependencia pom. Indica que dependencia se debe reemplazar en el listado de dependencias de la seccion dependencyManagement del pom.

## Que es un archetype?
Un archetype es un patrón o modelo original sobre el que pueden desarrollar todas aquellas cosas que son de un mismo tipo. Puede decirse que son plantillas, parametrizadas o configuradas para utilizar determinadas tecnologías, que los programadores utilizan como base para escribir y organizar el código de la aplicación.
Los arquetipos nos evitan el tener que configurar las bases de un proyecto (librerías, dependencias, configuraciones) cuando se basan en tecnologías comunes o están orientados a un determinado entorno de despliegue

## Estructura base de un proyecto Maven
Tiene cuatro carpetas fuente por defecto:
-   **src/main/java**  : donde guardaremos nuestras clases que vayamos creando para el proyecto. Debajo de esta carpeta situaremos nuestras clases en distintos paquetes.
-   **src/main/resources**  : acá almacenaremos los recursos que puedan necesitar las clases de nuestro proyecto. Acá tienen que ir los ficheros de configuración de Spring, Hibernate, archivos properties, etc.
-   **src/test/java**  : acá se guardan las clases de test que se encargarán de probar el correcto funcionamiento de nuestra aplicación. Por ejemplo: nuestros test unitarios de JUnit.

## Diferencia entre Archetype y Artifact:
Un artifact es algo producido o usado por un proyecto Maven. Es el recurso resultante de la generacion de un proyecto Maven, cada proyecto tiene un y solo un artifact que puede estar usualmete en formato JAR o WAR. Los artifact estan identificados por un group id, un artifact id y una versión.
En cambio, un archetype es una plantilla que nos permite fácilmente iniciar un proyecto con las dependencias necesarias para ese tipo de proyecto. Es un esqueleto base para los proyectos.

## Spring Stereotypes

**@Component:**  Es el estereotipo general y permite anotar un bean para que Spring lo considere uno de sus objetos.

![Relacion component](https://www.arquitecturajava.com/wp-content/uploads/SpringStereotypes.png)

**@Repository:**  Es el estereotipo que se encarga de dar de alta un bean para que implemente el patrón repositorio que es el encargado de almacenar datos en una base de datos o repositorio de información que se necesite.

![Repository](https://www.arquitecturajava.com/wp-content/uploads/SpringStereotypesRepository.png)

**@Service :** Este estereotipo se encarga de gestionar las operaciones de negocio más importantes a nivel de la aplicación y realiza llamados a varios repositorios de forma simultánea

![Service](https://www.arquitecturajava.com/wp-content/uploads/SpringStereotypesService.png)

**@Controller :** Realiza las tareas de controlador, interceptando las peticiones externas a la aplicación y procesando o dando respuesta a través del uso de los servicios. También se encarga de transformar la información procesada en el formato que se le requiera y normalmente hace uso de un motor de template. Por ejemplo JSON, HTML, XML, etc. 

![Controller](https://www.arquitecturajava.com/wp-content/uploads/SpringStereotypesController.png)

## Verbos REST


**GET**

Se usa para obtener información del servidor, puede ser algún archivo HTML, una imagen, un archivo de texto, un XML, un JSON, etc. Este método solo debe usarse para **obtener información del servidor** de acuerdo a los estándares de HTTP. El método **GET no debe cambiar el estado del servidor**. Es idempotente.

**HEAD**

Se usa para **obtener la cabecera de respuesta que devuelve el servidor** al hacer una petición sobre éste. Es similar al GET pero este no devuelve una respuesta con contenido, sino que solo los metadatos.

**POST**

Es el encargado de **crear un nuevo recurso** y, por consiguiente, **modificar el estado del servidor**.  Es usado de enviar información al servidor y que este la procese. No es idempotente.

**PUT**

Es el encargado de **modificar un recurso existente** y, por consiguiente, **modificar el estado del servidor**. Es usado de enviar información al servidor y que este la procese. Es idempotente.

**DELETE**

El método DELETE  **es usado para borrar un recurso del servidor**

**OPTIONS**

Este método se usa para saber que otros métodos HTTP están disponibles, para determinado recurso, en el servidor.

**PATCH**

Parecido al PUT pero este unicamente efectua modificaciones parciales sobre el recurso. Es no idempotente.
Solo se debe enviar la informacion a modificar.
