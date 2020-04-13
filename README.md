2TF-APOSTAR
===============================
2Tf-apostar project it`s a interface to consume the services exposes by Apostar in diferents modules. Aditionally, the project expose the consume in a API REST.

Modules
----------
![alt text](img.png)
* BetPlay
* Recargas
* Recaudos
* Sorteos
* Venta pines

How configuration
----------
The project contain a configuration file. it's name is application.yml.
2tf-apostar/src/main/resources/

```yml
operaciones:
  api:
    urlBase: http://172.17.254.17/web-services-seta-apostar/api
    recargas:
      urlBase: /recarga
      urlRecargar: /recargar
      urlConsultarSubproducto: /consultar-subproductos
      urlConsultarParametros: /consultar-parametros
      urlConsultarTopes: /consultar-topes
      urlConsultarParametrosPorSubproducto: /consultar-parametros-por-subproducto
    betplay:
      urlBase: /betplay
      urlConsultaSubproductos: /consultar-subproductos
      urlSolicitarPin: /solicitar-pin
      urlRealizarRetiro: /realizar-retiro
      urlRealizarRecaudo: /realizar-recaudo
    recaudos:
      urlBase: /recaudo
      urlConsultarDepartamentos: /consultar-departamentos
      urlConsultarRecaudos: /consultar-recaudos
      urlConsultarValor: /consultar-valor
      urlGuardar: /guardar
      urlConsultar: /consulta
    sorteos:
      urlBase: /resultado-sorteos
      urlConsultarResultados: /consultar-resultado-sorteos
      urlConsultarResultadosSemanal: /consultar-resultado-sorteos-semanal
distribuidor:
  identificador: 8000015206
  usuario-id: 8000015206
  cliente-id: 8000015207
  equipo-cliente-id: 1406
  llave: KEY
  clave: Qwerty1

server:
  servlet:
    context-path: /dostf-apostar
  port: 5000
```
* urlBase
    ```yml
    operaciones:
      api:
        urlBase: http://172.17.254.17/web-services-seta-apostar/api
    ```
    It`s a url to change in some enviroment (QA, TEST, Production), by default is test.
* distribuidor
``` xyml
distribuidor:
  identificador: 8000015206
  usuario-id: 8000015206
  cliente-id: 8000015207
  equipo-cliente-id: 1406
  llave: KEY
  clave: Qwerty1
```
these properties are supplied by apostar, is the security of consume the services exposed by them.

End points
----------
The endpoints are supplied by swagger. the url is *%SERVER%:%PORT%:/dostf-apostar/swagger-ui.html*
