# sistema-ventas

Proyecto realizado con java, usando el framework spring boot y una base de datos mysql para poder automatizar la creacion de mi servicio API rest:Sistema de ventas

# Diagrama UML
![Screenshot from 2023-06-30 16-30-57](https://github.com/LeandroTombe/sistema-ventas/assets/57770761/f23ac02b-8c3f-4786-8388-f01271dc7df1)


## Modo de ejecucion:
Debemos tener instalado docker en nuestro sistema operativo

1) primer paso es ejecutar un comando que nos permite construir, a traves de docker, un ejecutable de java y exponerlo en un puerto cualquiera, en este caso, elegi el puerto 4000 el "." del final nos buscara dentro del directo el Dockerfile, para esto esto sea efectivo, debemos posicionarnos en la carpeta sistemas-ventas

```shell
docker build -t sistemav2 .
```
2) Luego ejecutamos el siguiente comando, que se encarga de enlazar nuestro nuestra API REST de java spring boot con una base de datos, configurando el usuario root de mysql y tambien del servidor
```shell
docker-compose up
```

Si todo se ejecuto de forma correcta, nos tiene que salir el puerto donde se pueden hacer las peticiones

![Screenshot from 2023-06-30 16-19-51](https://github.com/LeandroTombe/sistema-ventas/assets/57770761/40324e8d-3b97-4abb-96ab-a860a0452145)



##  Listado de endpoints, de cada modelo/entidad

#### VENTAS
URL: CREAR UNA VENTA
- `http://localhost:4000/api/ventas/createVenta` -
se crea una venta, con sus respecitvas lineas de ventas asociadas y con la fecha que se realizo. Por ejemplo, si solictamos un producto llamado "caramelo", debemos  tambien agregar la cantidad que necesitamos. Este endpoint creara automaticamente las lineas de ventas con el producto y cantidad respectivamente, descontando del mismo su stock
- hay que tener en cuenta que el producto debe existir y la cantidad solicitada debe ser menor al que tiene en stock
![Screenshot from 2023-06-30 16-29-05](https://github.com/LeandroTombe/sistema-ventas/assets/57770761/c04b1c70-a8bc-430d-a92f-2784b042066d)

URL: OBTENER TODAS LAS VENTAS
- `http://localhost:4000/api/ventas/getVentas`
Se visualizan todas las ventas realizadas desde el primer dia que se inicio la aplicacion
