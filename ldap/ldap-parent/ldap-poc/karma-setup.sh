# Establecer proxy, si se requiere
sudo npm config set proxy http://192.168.0.6:3128

# Instalar karma para invocarlo desde cualquier sitio con el comando 'karma'
sudo npm install -g karma-cli

# Cambiar al directorio del proyecto
cd git/ldap-poc/

# Instalar en el proyecto, karma y todas las dependencias
npm install karma --save-dev
npm install karma-jasmine karma-chrome-launcher jasmine-core --save-dev

# Verificar la instalación
karma --version

# Después de revisar la configuración y establecer browser/files, iniciar karma
karma start
