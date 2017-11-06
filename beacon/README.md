Para usar eddystone-beacon:

Una vez se instale BlueZ 5+, se puede verificar si el donngle es LE compatible con "sudo hcitool lescan" (https://github.com/dburr/linux-ibeacon)

Se debe instalar bleno y seguir los prerrequisitos asegurando que se baja el servicio bluetooth
(https://github.com/sandeepmistry/bleno). Si aparece un icono en la barra de menu del SO, se debe deshabilitar (click derecho/salir)
porque este applet sube automaticamente el servicio de bluetooth otra vez

Luego se debe asegurar que el dispositivo está activado (sudo hciconfig hci0 up)

Finalmente, iniciar los ejemplos: node simple.js

Si no funciona, se debe bajar el hci1 (sudo hciconfig hci1 down)
