#!/bin/bash

#local comun
sudo /opt/si2/virtualip.sh eth0
#local PC1
export J2EE_HOME=/usr/local/glassfish-4.1.1/glassfish
export PATH=/usr/local/glassfish-4.1.1/glassfish/bin/:$PATH
#MV
# export J2EE_HOME=/opt/glassfish4/glassfish
export AS_ADMIN_USER=admin
export AS_ADMIN_PASSWORDFILE=/opt/SI2/passwordfile

ant todo

# abrimos maquina virtual
asadmin start-domain domain1
#VM2
sudo service postgresql-8.4 stop

#colas jms
/usr/local/glassfish-4.1.1/glassfish/bin/appclient -targetserver 10.1.2.2 -client dist/clientjms/P1-jms-clientjms.jar idAutorizacion
/usr/local/glassfish-4.1.1/glassfish/bin/appclient -targetserver 10.1.2.2 -client dist/clientjms/P1-jms-clientjms.jar -browse





#PRACTICA 2
export LC_ALL=C
export LANG=C
locale

#jmeter
apache-jmeter-4.0/bin/jmeter.sh &


En el primero (PC1) ejecutaremos:
o JMeter
o Máquina virtual VMWare con: 1 CPU y 768 MB de RAM
o Dentro de la máquina virtual del PC1 (PC1VM) tendremos asignada la dirección IP 10.X.Y.1
e iniciados tanto el GlassFish como el PostgreSQL 

En el segundo (PC2) ejecutaremos:
o Máquina virtual VMWare con: 1 CPU y 768 MB de RAM
o Dentro de la máquina virtual del PC2 (PC2VM) tendremos asignada la dirección IP 10.X.Y.2
e iniciado el GlassFish. El PostgreSQL estará detenido. Para detenerlo habrá que ejecutar
en la máquina virtual el comando sudo /etc/init.d/postgresql-8.4 stop. También se puede
usar el siguiente comando: sudo service postgresql-8.4 stop

Prepararemos el script JMX para que acceda desde PC1 a PC2VM a través de su dirección IP
10.X.Y.2 (es decir, accede a la máquina virtual del otro PC)
