# 1ra ENTREGA
1. Realizar un modelamiento de los distintos datos que debe guardar el sistema. Este modelamiento 
debe ser capturado en términos de (sub-dominios), apuntando a un diseño/implementación con 
microservicios. Una vez validado el modelo, generas las entidades y relaciones correspondientes, y 
mapearlo a una base de datos SQL. En ciertos casos (es decir, para ciertos microservicios), puede 
decidirse utilizar otro tipo de base de datos (por ej., MongoDB)

3. Diseñar un backend básico de (micro-)servicios que permita realizar el ABM de las entidades (para 
así poblar y gestionar la(s) base(s) de datos) y dar soporte a las principales funcionalidades antes 
mencionadas. En este diseño, considerar que cada microservicio contará (preferentemente) con una 
base de datos separada.

4. Implementar los siguientes servicios/reportes:
   
a. Como encargado de mantenimiento quiero poder generar un reporte de uso de monopatines por 
kilómetros para establecer si un monopatín requiere de mantenimiento. Este reporte debe poder 
configurarse para incluir (o no) los tiempos de pausa.

b. Como administrador quiero poder anular cuentas para inhabilitar el uso momentáneo de la 
misma.

c. Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.

d. Como administrador quiero consultar el total facturado en un rango de meses de cierto año.

e. Como administrador quiero consultar la cantidad de monopatines actualmente en operación, 
versus la cantidad de monopatines actualmente en mantenimiento.

f. Como administrador quiero hacer un ajuste de precios, y que a partir de cierta fecha el sistema 
habilite los nuevos precios.

g. Como usuario quiero lun listado de los monopatines cercanos a mi zona, para poder encontrar 
un monopatín cerca de mi ubicación

## Endpoints para testeo
