## Microservices-With-Spring-Boot

Controller</br>
Service</br>
Repository</br>
Entity</br>
Model

Change --> application.prop -> application.yaml

(jdbc:mysql://localhost:3306/?user=root)

How to activate Discovery Server : </br>
Install Maven Deapendencies-> Create new Service, Do configuration for the server in application.yaml file

Do client setup in Services.</br>
Feign Client-> Call other service internally :</br>
Enable Feign Client and Create a class and setup the Service on which you want to maka a Internal Request.

Added Feigh decoder to decode errors.

Installed Docker -> Install OpenZipkin Image -> For request tracing
