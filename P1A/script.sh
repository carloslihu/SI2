#!/bin/bash


sudo /opt/si2/virtualip.sh eth0
#local
export J2EE_HOME=/usr/local/glassfish-4.1.1/glassfish
#MV
# export J2EE_HOME=/opt/glassfish4/glassfish

ant todo

# abrimos maquina virtual
asadmin start-domain domain1
zip -r SI2P1A_2401_2.zip P1-base P1-ws memoria-p1a.pdf
