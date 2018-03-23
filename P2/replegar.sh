#!/bin/bash
for i in P1-ws P1-ejb-servidor-remoto P1-ejb-cliente-remoto; do
	echo "###########################################################"
	echo $i
	echo "###########################################################"
	cd $i
	ant replegar; #limpiar-todo
	cd -
done
cd P1-base
cd -
