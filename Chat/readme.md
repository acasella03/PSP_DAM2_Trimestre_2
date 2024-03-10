# Protocolo del Chat
El intercambio de mensajes en este chat se realiza a través de un protocolo cliente-servidor utilizando sockets en Java. A continuación, se detalla el protocolo paso a paso, para el servidor (ChatServer) y el cliente (ChatClient).

## Servidor (ChatServer)
* Inicialización del servidor:
    - El servidor solicita al usuario que ingrese el puerto en el que se ejecutará.
    - Se crea un ServerSocket para escuchar conexiones entrantes en el puerto especificado.
* Aceptación de conexiones:
    - El servidor espera conexiones de clientes.
    - Cuando un cliente se conecta, se crea una instancia de ClientHandler para manejar la comunicación con ese cliente específico.
* Gestión de clientes:
    - Se mantiene una lista de ClientHandler para todos los clientes conectados.
    - Si el número de clientes conectados alcanza el máximo permitido (MAX_CLIENTS), se rechaza la nueva conexión.
* Comunicación:
    - Cada ClientHandler maneja la comunicación con su cliente correspondiente.
    - Al iniciar, el ClientHandler solicita al cliente que ingrese un apodo.
    - El servidor informa a todos los clientes conectados sobre la nueva conexión.
    - Los mensajes enviados por un cliente se reenvían a todos los demás clientes conectados.
* Desconexión:
    - Si un cliente envía el mensaje "bye", se le informa que ha dejado el chat.
    - Se cierra la conexión con el cliente y se elimina de la lista de clientes conectados.

## Cliente (ChatClient)
* Inicialización del cliente:
    - El cliente solicita al usuario que ingrese la dirección IP y el puerto del servidor al que se conectará.
    - Se crea un Socket para establecer la conexión con el servidor.
* Conectarse al servidor:
    - El cliente se conecta al servidor utilizando la dirección IP y el puerto proporcionados.
* Interacción:
    - El cliente solicita al usuario que ingrese su apodo.
    - Se inician dos hilos: uno para recibir mensajes del servidor y otro para enviar mensajes al servidor.
* Envío de mensajes:
    - El usuario puede enviar mensajes al servidor escribiendo en la consola.
    - Si el usuario escribe "bye", se envía este mensaje al servidor y se cierra la conexión.
* Recibir mensajes:
    - Los mensajes enviados por el servidor o por otros clientes se muestran en la consola del cliente.

## Protocolo de mensajes
* Conexión y desconexión:
    - El servidor y el cliente se comunican mediante el intercambio de mensajes de texto a través de DataOutputStream y DataInputStream.
    - El apodo del cliente se envía primero al servidor para identificarlo.
    - El servidor y los clientes pueden enviar mensajes de texto. El servidor reenvía todos los mensajes recibidos a todos los clientes conectados.

Este protocolo permite la comunicación en tiempo real entre múltiples clientes a través de un chat basado en texto.