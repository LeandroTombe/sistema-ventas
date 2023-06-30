# sistema-ventas

Proyecto realizado con java usando el framework spring boot para poder automatizar la creacion de mi servicio API rest:Sistema de ventas

## Modo de ejecucion:
Debemos tener instalado docker en nuestro sistema operativo

1) primer paso es ejecutar un comando que nos permite construir, a traves de docker, un ejecutable de java y exponerlo en un puerto cualquiera, en este caso, elegi el 4000
elegimos usar "." como busqueda del Dockerfile, para esto esto sea efectivo, debemos posicionarnos en la carpeta sistemas-ventas

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
