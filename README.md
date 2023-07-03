# sistema-ventas

Proyecto realizado con java, usando el framework spring boot y una base de datos mysql para poder automatizar la creacion de mi servicio API rest:Sistema de ventas

# Diagrama UML
![Screenshot from 2023-06-30 16-30-57](https://github.com/LeandroTombe/sistema-ventas/assets/57770761/f23ac02b-8c3f-4786-8388-f01271dc7df1)


# Componentes Spring
Nuestros componentes principales se etiquetarán con @ para que el framework Spring lo reconozca. Cada uno tiene una misión en nuestra arquitectura:
![components](https://github.com/LeandroTombe/sistema-ventas/assets/57770761/ae02dd12-6496-4c05-b321-d7955ea455e2)

Controladores
Se etiquetan como @Controller o en nuestro caso al ser una API REST como @RestController. Estos son los controladores que se encargan de recibir las peticiones de los usuarios y devolver respuestas.

Servicios
Se etiquetan como @Service. Se encargan de implementar la parte de negocio o infraestructura. En nuestro caso puede ser el sistema de ventas o parte de la seguridad y perfiles de usuario.

Repositorios
Se etiquetan como @Repository e implementan la interfaz y operaciones de persistencia de la información. En nuestro caso, puede ser una base de datos. Podemos extender de repositorios pre establecidos o diseñar el nuestro propio.

Configuración
Se etiquetan como @Configuration. Se encargan de configurar los componentes de la aplicación. Se se suelen iniciar al comienzo de nuestra aplicación.

Bean
La anotación @Bean, nos sirve para indicar que este bean será administrado por Spring Boot (Spring Container). La administración de estos beans se realiza mediante a anotaciones como @Configuration.

## Modo de ejecucion:
Debemos tener instalado docker en nuestro sistema operativo

1) primer paso es ejecutar un comando que nos permite construir, a traves de docker, un ejecutable de java y exponerlo en un puerto cualquiera, en este caso, elegi el puerto 4000 el "." del final nos buscara dentro del directo el Dockerfile, para esto sea efectivo, debemos posicionarnos en la carpeta sistemas-ventas

```shell
docker build -t sistemav2 .
```
2) Luego ejecutamos el siguiente comando, que se encarga de enlazar nuestro nuestra API REST de java spring boot con una base de datos, configurando el usuario root de mysql y tambien del servidor
```shell
docker-compose up
```

Si todo se ejecuto de forma correcta, nos tiene que salir el puerto donde se pueden hacer las peticiones

![Screenshot from 2023-06-30 16-19-51](https://github.com/LeandroTombe/sistema-ventas/assets/57770761/40324e8d-3b97-4abb-96ab-a860a0452145)



# Listado de endpoints, de cada modelo/entidad

## VENTAS
peticion: CREAR UNA VENTA
- `http://localhost:4000/api/ventas/createVenta` -
se crea una venta, con sus respecitvas lineas de ventas asociadas y con la fecha que se realizo. Por ejemplo, si solictamos un producto llamado "caramelo", debemos  tambien agregar la cantidad que necesitamos. Este endpoint creara automaticamente las lineas de ventas con el producto y cantidad respectivamente, descontando del mismo su stock
- hay que tener en cuenta que el producto debe existir y la cantidad solicitada debe ser menor al que tiene en stock
![Screenshot from 2023-06-30 16-29-05](https://github.com/LeandroTombe/sistema-ventas/assets/57770761/c04b1c70-a8bc-430d-a92f-2784b042066d)

peticion: OBTENER TODAS LAS VENTAS
- `http://localhost:4000/api/ventas/getVentas`
Se visualizan todas las ventas realizadas desde el primer dia que se inicio la aplicacion

peticion: VISUALIZAR VENTAS DE UNA SEMANA
- `http://localhost:4000/api/ventas/getVentasByWeek`
Se visualizan las ventas de hoy hasta una semana de diferencia

peticion: VISUALIZAR GANANCIAS DE UNA SEMANA
- `http://localhost:4000/api/ventas/getVentasByWeek`
Se visualizan las ventas de hoy hasta una semana de diferencia, obteniendo la cantidad vendida de cualquier producto y sus respectivas ganancias

peticion: VISUALIZAR VENTAS DE UN MES
- `http://localhost:4000/api/ventas/getVentasByMonth`
Se visualizan las ventas de hoy hasta un mes de diferencia

peticion: VISUALIZAR GANANCIAS DE UN MES
- `http://localhost:4000/api/ventas/getGananciasByMonth`
Se visualizan las ventas de hoy hasta un mes de diferencia, obteniendo la cantidad vendida de cualquier producto y sus respectivas ganancias

peticion: VISUALIZAR VENTAS DE UN Año
- `http://localhost:4000/api/ventas/getVentasByYear`
Se visualizan las ventas de hoy hasta un Año de diferencia

peticion: VISUALIZAR GANANCIAS DE UNA Año
- `http://localhost:4000/api/ventas/getGananciasByYear`
Se visualizan las ventas de hoy hasta un Año de diferencia, obteniendo la cantidad vendida de cualquier producto y sus respectivas ganancias

## COMPRAS

peticion: CREAR UNA COMPRA
- `http://localhost:4000/api/compras/createCompra` -
se crea una compra, con sus respecitvas lineas de compras asociadas y con la fecha que se realizo. Por ejemplo, si solictamos un producto llamado "chocolate", debemos tambien agregar la cantidad que necesitamos. Este endpoint creara automaticamente las lineas de ventas con el producto y cantidad respectivamente, sumando al  stock del producto solicitado
- hay que tener en cuenta que el producto debe existir

peticion: OBTENER TODAS LAS COMPRAS
- `http://localhost:4000/api/compras/getCompras`
Se visualizan todas las ventas realizadas desde el primer dia que se inicio la aplicacion


peticion: VISUALIZAR PERDIDAS(compradas a un prooveedor) DE UNA SEMANA
- `http://localhost:4000/api/compras/getGananciasByWeek`
Se visualizan las compras de hoy hasta una semana de diferencia, obteniendo la cantidad comprada de cualquier producto y sus respecivas peridas


## PRODUCTOS

peticion: OBTENER TODOS LOS PRODUCTOS
- `http://localhost:4000/api/productos/getProductos`
Se visualizan todas las compras realizadas desde el primer dia que se inicio la aplicacion


peticion: CREAR UN PRODUCTO
- `http://localhost:4000/api/productos/createProducto`
Este metodo se necesita enviar como atributo "name,precio,Actualstock" para poder realizar de forma correcta su creacion
Caso que no se envie esos atributos al body, saltara un mensaje de error 400 "bad request "solitando que ingrese cualquiera de estos campos
![image](https://github.com/LeandroTombe/sistema-ventas/assets/57770761/dc31dace-6e9f-4534-9e9a-a695eb193d1d)

peticion: ACTUALIZAR UN PRODUCTO POR ID
- `http://localhost:4000/api/productos/updateProducto/:id`
Se encarga de actualizar un producto enviando en la peticion un id de un producto existente y cualquiera de estos atributos para actualizar :"name, precio,Actualstock"
Caso que no exista el producto o se ingrese un atributo no valido, nos dara un error 400 "Bad request" 

peticion : BUSCAR UN PRODUCTO POR NOMBRE
- `http://localhost:4000/api/productos/buscarProductoNombre/:nombreProducto`
Se encarga de buscar un producto enviando en la peticion un nombre de un producto existente
Caso que no exista el producto nos dara un error 400 "Bad request"

peticion : BUSCAR UN PRODUCTO POR ID
- `http://localhost:4000/api/productos/buscarProductoId/:id`
Se encarga de buscar un producto enviando en la peticion un id de un producto existente
Caso que no exista el producto nos dara un error 400 "Bad request"

## LINEA DE VENTA

peticion: OBTENER TODAS LAS LINEAS DE VENTA
- `http://localhost:4000/api/lineaVentas/getLineaVentas`
Se visualizan todas las lineas de ventas realizadas desde el primer dia que se inicio la aplicacion


## LINEA DE COMPRA

peticion: OBTENER TODAS LAS LINEAS DE COMPRA
- `http://localhost:4000/api/Lineacompras/getLineaCompras`
Se visualizan todas las lineas de compras realizadas desde el primer dia que se inicio la aplicacion



## USUARIO

peticion: OBTENER TODOS LOS USUARIOS
- `http://localhost:4000/api/auth/getUsers`
Se visualizan todas los usuarios creados desde el primer dia que se inicio la aplicacion


peticion: CREAR UN USUARIO

- `http://localhost:4000/api/auth/createUser`
Se encargar de crear un usuario recibiendo como parametro del body: "name", "password", "email"
Este mismo se crea un rol CLIENTE de forma automatica y nos permite poder realizar diversas peticiones al servidor
![image](https://github.com/LeandroTombe/sistema-ventas/assets/57770761/4a52ed62-3e98-4939-8fc5-9b6aa2dac750)


# AUTORIZACION
Los que estan como INTERNAUTAS pueden acceder a los endpoints de:
USUARIO --> CREAR UN USUARIO

Los que estan logueado como CLIENTE pueden acceder a los endpoints de :
PRODUCTOS --> OBTENER TODOS LOS PRODUCTOS, BUSCAR UN PRODUCTO POR NOMBRE, BUSCAR UN PRODUCTO POR ID
VENTAS --> CREAR UNA VENTA,

Los que estan logueado como PROVEEDOR pueden acceder a los endpoints de :
COMPRAS --> CREAR UNA COMPRA

Los que estan como ADMIN pueden acceder a todos los endpoints
