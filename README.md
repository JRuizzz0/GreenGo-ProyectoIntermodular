# GreenGo-ProyectoIntermodular
#SISTEMAS-INFORMÁTICOS

Lo primero que debemos hacer es iniciar la base de datos. Para ello, debemos de tener instalado **pgAdmin 4** <img width="115" height="26" alt="image" src="https://github.com/user-attachments/assets/6aaba522-65fb-4e89-aba9-e01d9400b01c" /> 
-Página para descargarse pgAdmin 4 (Windows) https://www.postgresql.org/download/windows/ (instalar una de las verisones de 2026)

-Link video tutorial youtube (por si hay dudas): https://www.youtube.com/watch?v=gFtswXFopTw

Tras tenerlo instalado, debemo seguir los siguientes pasos:

-Una vez iniciado sesión / registrados, debemos **crear una base de datos**. Hacemos click derecho en **"Databases"** y pulsamos en **Create -> Database**
<img width="219" height="31" alt="image" src="https://github.com/user-attachments/assets/485e603a-dcf7-4225-91ff-fd1be674b056" />
	
<img width="430" height="81" alt="image" src="https://github.com/user-attachments/assets/81288e29-2f5d-49c8-b1a5-73ba143ae3ed" />


-Escribimos el nombre de la DataBase **(GreenGo)** y pulsamos en el botón **"Save"** 
<img width="697" height="546" alt="image" src="https://github.com/user-attachments/assets/42cce1e6-7249-4f49-9484-c55871c555bf" />


-Tras ello, se nos habrá creado una base de datos (con nombre GreenGo). Pulsamos click derecho encima del nombre y pulsamos en **Query Tool**
<img width="344" height="25" alt="image" src="https://github.com/user-attachments/assets/699cc90e-23d6-45d4-974a-4c980f753653" />
<img width="304" height="427" alt="image" src="https://github.com/user-attachments/assets/2b47207d-ffc6-42d5-a832-050e7a70fb9d" />


-Una vez hecho esto, se nos abrirá una página al lado donde tendremos que insertar todos los Scripts del documento de base de datos (descargar el .docx dentro de la carpeta **Green-Go_Memoria_BBDD**)
<img width="518" height="420" alt="image" src="https://github.com/user-attachments/assets/908e976f-26a6-49f4-bfd1-4e46fc9b0506" />
-Copiamos todas las lineas de Scripts y de Inserts
<img width="377" height="307" alt="image" src="https://github.com/user-attachments/assets/508bfe31-9eba-400f-acbc-2bcc839033dc" />


-Una vez esté todo copiado, pulsamos la tecla F5 para ejecutarlo. Una vez ejecutado, nos debe saltar un mensaje abajo a la derecha 
<img width="315" height="35" alt="image" src="https://github.com/user-attachments/assets/99a2430b-2dc3-4a0b-b3a3-5c68b3fe8a2f" />


----------------------------------------------------------------------------------------------------------------------------


Para poder ejecutar el programa y que funcione, debemos clonar el repositorio de github en dos máquinas virtuales diferentes:

**1. IntelIJ IDEA**, para ejecutar toda la parte del back. <img width="72" height="100" alt="image" src="https://github.com/user-attachments/assets/0a845bce-8545-48cf-9052-92f57cd84651" />

-Página para instalar IntelIJ IDEA (windows) https://www.jetbrains.com/es-es/idea/download/?section=windows

-Link video tutorial youtube (por si hay dudas): https://www.youtube.com/watch?v=eNeFJOdh_-c

Para ejecutarla:

-Clonamos el repositorio de **github** <img width="395" height="78" alt="image" src="https://github.com/user-attachments/assets/1c64cd51-5556-4833-9209-df93ece2d550" /> --> 
-En el apartado de **URL** copiamos
	https://github.com/JRuizzz0/GreenGo-ProyectoIntermodular.git y pulsamos en el botón "Clone"
<img width="869" height="641" alt="image" src="https://github.com/user-attachments/assets/57dd5820-cd54-4740-9494-33c5a4f38695" />


-Una vez se haya clonado, abrimos la **terminal** y, dentro de la terminal, abrimos el **Git Bash**
<img width="953" height="348" alt="image" src="https://github.com/user-attachments/assets/e3b2dbe5-2b5a-4197-a765-e713c4690777" />


-Dentro del Git bash, copiamos el siguiente código:

	git switch develop


-Una vez estemos en la **rama develop**, abrimos la carpeta de **src** (dentro del repositorio) <img width="615" height="343" alt="image" src="https://github.com/user-attachments/assets/d7c49e13-4cd1-4b4b-8e15-1ded63911bf8" />


-Abrimos la carpeta **"config"** y entramos en la clase **"DatabaseConfig"**. Dentro de esta clase, debemos de cambiar el **USERNAME** y **PASSWORD** a nuestras credenciales de pgAdmin 4 <img width="1353" height="473" alt="image" src="https://github.com/user-attachments/assets/346dfcfb-cb4e-4f14-8cce-646766edffaa" />


-Una vez cambiado la parte de "DatabaseConfig", entramos en la clase **"Main"** <img width="595" height="241" alt="image" src="https://github.com/user-attachments/assets/1bbf7e04-8545-47ea-bbc0-633fd00181bf" />


-Una vez estemos en la clase, debemos pulsar el botón de inicio verde de arriba a la derecha  <img width="1258" height="618" alt="image" src="https://github.com/user-attachments/assets/d28caa68-5f23-4f55-814e-a7ba67727474" />


-Una vez hecho todo esto, nos debería de salir algo así:
<img width="872" height="286" alt="image" src="https://github.com/user-attachments/assets/f39fcd53-7819-47d4-a5c5-c27f2d340cb8" />


----------------------------------------------------------------------------------------------------------------------------

**2. Visual Studio Code**, para ejecutar la parte del front <img width="105" height="99" alt="image" src="https://github.com/user-attachments/assets/f7f1f8b1-9d8e-4fd4-b869-f0dac3bd9cc2" />

-Página para descargarse Visual Studio Code (Windows): https://code.visualstudio.com/

-Link video tutorial youtube (por si hay dudas) (la extension que sale al final del video no es necesaria para la instalacion): https://www.youtube.com/watch?v=6pD7_rcFrj8

-Para que funcione el programa, una vez tengamos instalado Visual Studio Code, nos instalamos la extensión de **"Live Server"**<img width="1147" height="276" alt="image" src="https://github.com/user-attachments/assets/9a062826-2a84-44b1-8914-4cb0702dfd13" />


Para ejecutarlo:

-Nos creamos una carpeta en nuestro ordenador (se puede llamar como quieras)


-Clonamos el repositorio de **github**. Para ello, pulsamos Ctrl+Shift+P o F1 y escribimos git Clone <img width="587" height="431" alt="image" src="https://github.com/user-attachments/assets/de520364-1e38-4159-a0ee-2b7a120c68c8" /> --> -Pegamos la URL <img width="593" height="74" alt="image" src="https://github.com/user-attachments/assets/e754d200-d758-4e7c-8073-a2747ab405d4" /> --> -Se nos va a abrir una ventana con nuestras carpetas. Pulsamos en la carpeta que nos hemos creado y ya tendríamos el repositorio en nuestro ordenador


-En el menú de arriba pulsamos en **Terminal** y le damos a **New Terminal** para crear una nueva terminal <img width="634" height="434" alt="image" src="https://github.com/user-attachments/assets/fe6beee0-0f5e-44bf-87c8-673b6371fc31" />


-Una vez que tengamos la nueva terminal, copiamos el siguiente código:

	git switch develop


-Al estar ya en la rama develop, abrimos la carpeta **"LOGIN"**, y dentro de ella, abrimos la clase **"login.html** <img width="162" height="344" alt="image" src="https://github.com/user-attachments/assets/05441c8d-2ba9-486d-9d05-459e1713854e" />


-Una vez estemos en la clase login.html, pulsamos en el botón **Go Live** y se nos abrirá la página web <img width="1690" height="993" alt="image" src="https://github.com/user-attachments/assets/1ab211bd-b9e0-47e4-b3a1-b63df4ed1a9a" />

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


Con todo esto, solo queda registrarse/iniciar sesión y empezar el pedido. ¡Bienvenido a la familia GreenGo!<img width="1913" height="1029" alt="image" src="https://github.com/user-attachments/assets/e7fd3226-20fd-4341-9625-91cc55c0bf47" />


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

**1. GitHub**

GitHub permite gestionar el historial de cambios del código fuente, registrando cada modificación para garantizar la trazabilidad y la posibilidad de revertir versiones cuando sea necesario. Además, facilita el trabajo en equipo mediante el uso de ramas, pull requests y revisiones de código, lo que mejora la calidad y la organización del desarrollo. Por otro lado, el repositorio remoto actúa como respaldo en la nube, asegurando que el código esté siempre accesible desde cualquier equipo.

**2. IntelliJ IDEA**

Este IDE ofrece soporte específico para el lenguaje o framework del proyecto, con análisis de código en tiempo real, autocompletado avanzado y refactorizaciones seguras. Se integra fácilmente con GitHub (commit, push, pull), con sistemas de build y con servidores de aplicaciones. Gracias a sus atajos de teclado, inspecciones de código y sugerencias inteligentes, IntelliJ IDEA reduce errores y acelera el desarrollo, aumentando significativamente la productividad.

**3. Visual Studio Code**

Visual Studio Code se utilizó como editor complementario para tareas en las que IntelliJ no resultaba tan ágil o liviano. Por ejemplo, permite la edición rápida de archivos de configuración (JSON, YAML, XML), abriéndolos de forma instantánea y con un buen resaltado de sintaxis. Además, su terminal integrada y el soporte para control de versiones facilitan la ejecución de comandos Git, npm, pip, etc., desde la misma interfaz.

**4. pgAdmin**

Se empleó pgAdmin como interfaz gráfica para administrar la base de datos PostgreSQL. Ofrece una gestión visual completa: crear, modificar y eliminar tablas, índices, vistas, secuencias y procedimientos almacenados sin necesidad de escribir SQL manualmente. Su editor de consultas incluye resaltado de sintaxis, autocompletado y explicación visual de planes de ejecución, lo que simplifica la depuración de consultas complejas. Al ser una herramienta cliente multiplataforma, permite trabajar con bases de datos PostgreSQL locales o remotas desde cualquier sistema operativo.



