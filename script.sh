#!/bin/bash
# abrimos maquina virtual

sudo /opt/si2/virtualip.sh eth0
#local
export J2EE_HOME=/usr/local/glassfish-4.1.1/glassfish
#MV
# export J2EE_HOME=/opt/glassfish4/glassfish

ant todo
