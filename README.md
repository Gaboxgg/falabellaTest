# falabellaTest

Estructura del proyecto:

-adapters
-data
-fragments
-interfaces
-utils
-viewmodels
MainActivity

También estan los test unitarios en la carpeta correspondiente a los test unitarios (test).

El usuario de acceso tiene como nombre de usuario "prueba" y la contraseña es "123123".

En el root del proyecto van encontrar dos carpetas importantes, una llamada key, donde genere una llave
para firmar los apks a la hora de generarlos, la clave es también "123123" y el nombre del key es "key0" 
y la otra carpeta llamada apk donde deje un apk listo para ser ejecutado con la versión actual.

Consideraciones generales:

- Al ingresar a la app, existe un validador que guarda en preferencias el logeo exitoso, así no pregunta siempre el login al entrar.
- Al dar Salir este validador se borra y la app vuelve a preguntar siempre por el login hasta que este sea exitoso.
- El buscador de códigos funciona con un like es decir va buscar toda palabra que contenga la llave deseada y no solo la llave exacta.
- El buscador de códigos va generar búsquedas así no se haya terminado de escribir el código completo.
- El detalle de cada muestra tiene el mismo orden en la cual aparece en el api.
