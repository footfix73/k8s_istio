# INSTALAR MINIKUBE
------------------

## 1. COMO ACCEDER AL CLUSTER KUBERNETES
----------------------------------------------------------------------------
    - kubectl get po -A
	- minikube kubectl -- get po -A
					
## 2. DASHBOARD MINIKUBE
----------------------------------------------------------------------------
    - minikube dashboard
## 3. CREAR UN DESPLIEGUE DE MUESTRA Y EXPONERLO EN EL PUERTO 8080 (NO NECESARIO)
----------------------------------------------------------------------------
    - kubectl create deployment hello-minikube --image=kicbase/echo-server:1.0
    - kubectl expose deployment hello-minikube --type=NodePort --port=8080

Esto puede tardar un momento, pero tu despliegue aparecerá pronto cuando lances el siguiente comando

    - minikube service hello-minikube

Alternativamente se puede utilizar kubectl para reenviar el puerto

    - kubectl port-forward service/hello-minikube 7080:8080

Y la solicitud estaría disponible en http://localhost:7080/.

## 4. COMANDOS PARA MANEJAR KUBERNETES
----------------------------------------------------------------------------
    - Pausar kubernetes:              minikube pause
    - Quitar pausa:                   minikube unpause
    - Parar kubernetes:               minikube stop
    - Cambiar la memoria por defecto: minikube config set memory 9001
    - Catalogo:                       minikube addons list
    - Crear un segundo cluster:       minikube start -p aged 
    - Borrar todos los cluster:       minikube delete --all
    - Borrar deployment:              kubectl delete deployment <deployment>


## 5. KUBERNETES + ISTIO
----------------------------------------------------------------------------

    5.1 CONFIGURAR NUMERO DE CPUS (OPCIONAL)

      - minikube config set cpus 4

    5.2 CONFIGURAR MEMORIA (OPCIONAL)

      - minikube config set memory 6144

    5.3 INICIAR KUBERNETES 
      
      - minikube start ( minikube dashboard opcional )

    5.4 INSTALAR ISTIO UNA VEZ DESCARGADO (VER OPCIONES DE DESCARGA) 
    
      - istioctl install

    5.5 OBTENER INFORMACION SOBRE LOS RECURSOS DESPLEGADOS
    
      - kubectl get nodes

    5.6 OBTENER INFORMACION SOBRE LOS ESPACIOS DE NOMBRES
    
      - kubectl get ns

    5.7 OBTENER INFORMACION SOBRE LOS PODS EJECUTADOS EN UN NAMESPACE
    
      - kubectl get po -n istio-system
      - kubectl get pods -n {namespace1}

    5.8 CONFIGURAR LOS PODS DE UN NAMESPACE PARA INYECTAR ISTIO
      
      - kubectl label ns {namespace1} istio-injection=enabled

    5.9 MOSTRAR LAS ETIQUETAS

      - kubectl get ns {namespace1} --show-labels

    5.10 COMPROBAR ISTIO INYECTADO BIEN BORRAR TODOS LOS MICROS DE ESE     NAMESPACE O REINICIANDOLOS
          
	  - kubectl rollout restart deployment -n {namespace}

    5.11 COMPROBAR MICROSERVIIOS ANTES DE ISTIO TIENEN UNA REPLICA DESPUES TIENEN DOS POR HABERLE INTECTADO ISTIO CREA UNA REPLICA MAS CON SU DEFINICION
    
      - kubectl get pods -n {namespace}
      - kubectl describe pod microserviceXXX -n {namespace}

    
## 6. AÑADIR LOS ADDONS NECESARIOS Añadir los addons
----------------------------------------------------------------------------
    Ir al directorio donde se instalo istio y realizar lo siguiente
	
    - kubectl apply -f .
	  - get pod -n istio-system

## 7. REALIZAR UN PORT-FORWARD O SERVICIO NODEPORT PARA VER LOS ADDONS CREADOS POR EJEMPLO KIALI
----------------------------------------------------------------------------
	- kubectl get svc -n istio-system
	- kubectl port-forward svc/kiali -n istio-system 8000:20001
	- kubectl port-forward svc/grafana -n istio-system 3000:3000
	
## 8. DEPLOY SPRINGBOOT KUBERNETES E ISTIO (https://arupmishra91.medium.com/basic-example-of-spring-boot-microservice-using-istio-as-service-mesh-673073dcab07)
----------------------------------------------------------------------------
    
    8.1 UTILIZAR SERVICIO "SERVICE MESH" DE ISTIO PARA APLICAR MANIFIESTO PARA ESTABLECER CONFIGURACION DEL PERFIL DE ISTIO EN "DEMO" AL DESPLEGAR APPLICACION

      - istioctl manifest apply --set profile=demo

    8.2 INYECTAR ISTIO EN EL NAMESPACE ELEGIDO
    
      - kubectl label namespace demo istio-injection=enabled

    8.3 EN EL DIRECTORIO KUBE DEL PROYECTO EJECUTAR EL SIGUIENTE COMOANDO POR EL TERMINAL Y DESPLEGAR LOS MICROS EN KUBERNETES
	
	    - eval $(minikube docker-env)
	  
	    - docker build --no-cache . -t demo-1-app:1.0.0 (opcional --> docker tag demo-1-app:1.0.0 arup91/demo-1-app-svc:1.0.0 /// docker push arup91/demo-1-app-svc:1.0.0)
      - docker build --no-cache . -t demo-2-app:1.0.0 (opcional --> docker tag demo-2-app:1.0.0 arup91/demo-2-app-svc:1.0.0 /// docker push arup91/demo-2-app-svc:1.0.0)
      
      - kubectl apply -f ./kube
      - kubectl get pods

    8.4 CONFIGURAR EL PUERTO PARA COMPROBAR LOS ENDPOINTS CONFIGURADOS (exponer hacia fuera los servicios)
    
      - kubectl port-forward service/istio-ingressgateway 8002:80 -n istio-system

    8.5 ENDPOINTS PARA PROBAR
      - http://localhost:8002/service-1 
      - http://localhost:8002/service-2

    8.6 NOTAS IMPORTANTES
      1.- En el dashboard de Docker no debe uno entrar con login
      2.- En el dashboard de Docker no debe aparecer la imagen de docker, ya que solo debe generarse en local
      3.- Si el punto 2 aparece poner la siguiente comando, si es linux o semejante eval $(minikube docker-env) y si es windows en el cmd sin el eval
      4.- Si no funciona la url tenemos que hacer el port-forwarding

9. OTROS COMANDOS
----------------------------------------------------------------------------

    - docker login --username 
    - minikube.exe service demo-1-app --url
	
    - kubectl get services -n my-namespace

    - kubectl describe service my-service -n my-namespace
    
    - kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.status.loadBalancer.ingress[0].ip}:{.spec.ports[?(@.name=="http2")].port}'
    
**10.- IMPORTANTE**

    Tras la instalacion de minikube en local tanto en windows y/o mac se detecto varios incovenientes estos son los pasos definitivos
    
    - minikube start --memory=8192 --cpus=4 --addons=ingress

    - kubectl rollout restart deployment -n istio-system (si te da un error de TLS/HANDSHAKE)

    - kubectl port-forward service/istio-ingressgateway 8002:80 -n istio-system (Necesario para probar gateway de istio con MINIKUBE)

    - Tener en cuenta que al lanzar los deploy se queden en local y no se suban a dockerHUB dara errores de permisos, para  ello en la misma terminal(console/cmd/powershell) lanzar el siguiente comando **eval $(minikube docker-env)** o minikube docker-env

    - Asegurarse que las imagenes docker no se despliegan en el dockerHUB
    
    - Lanzar los deployment que vienen en la carpeta y deberian hacer el pull de la imagen sin problemas

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax

| Plugin | README |
| ------ | ------ |
| Instalación y Configuración de ISTIO en KUBERNETES | [https://www.youtube.com/watch?v=nRxWnBE0wzE][PlDb] |
| Basic Example of Spring Boot Microservice using Istio as Service Mesh | [https://arupmishra91.medium.com/basic-example-of-spring-boot-microservice-using-istio-as-service-mesh-673073dcab07][PlGh] |


License
MIT

Free Software, Hell Yeah!