# Protocolo Calculadora:

## Para el Servidor y el Cliente (Sin IU): 

### 1. Conexión Inicial:
* El servidor está a la espera de conexiones en un puerto específico.
* El cliente se conecta al servidor mediante la dirección IP y puerto del servidor.
 
### 2. Comunicación de Operandos y Operación:
* El cliente solicita al usuario ingresar el primer operando, el segundo operando y el tipo de operación (suma, resta, multiplicación, división). De la siguiente forma:
    - 1er. Mensaje como cadena de texto por consola para solicitar al usuario el primer operando de tipo Double.
    - 2do. Mensaje como cadena de texto por consola para solicitar al usuario el segundo operando de tipo Double.
    - 3er. Mensaje como cadena de texto por consola para solicitar el usuario el tipo de operación de tipo String.
 * El cliente envía estos datos uno por uno al servidor a través de la conexión.

### 3. Procesamiento en el Servidor:
* El servidor recibe los datos del cliente, incluyendo los operandos de tipo Double y la operación de tipo String, uno a uno según los mensajes enviados por el cliente.
* Realiza la operación matemática correspondiente (suma, resta, multiplicación, división), mientras el cliente queda a la espera de la respuesta del resultado.

### 4. Envío del Resultado al Cliente:
* El servidor envía el resultado de tipo Double de la operación de vuelta al cliente mediante la conexión.

### 5. Cierre de la Conexión:
* El servidor cierra la conexión con el cliente después de completar una operación.
* El cliente también cierra su conexión con el servidor después de recibir el resultado.