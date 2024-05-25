# Microservizi OpenLiberty con OpenShift

## Descrizione dei Microservizi

### Primo Microservizio
Il primo microservizio fornisce due API REST:
- `GET /api/primo/uno`: ritorna un JSON con `{ "message": "Sono il microservizio primo /uno" }`
- `GET api/primo/due`: ritorna un JSON con `{ "message": "Sono il microservizio primo /due" }`

### Secondo Microservizio
Il secondo microservizio fornisce due API REST che ritornano un messaggio casuale:
- `GET /api/primo/tre`: ritorna un JSON con  `{ "message": "Sono il microservizio secondo /tre" }`
- `GET /api/primo/quattro`: ritorna un JSON con  `{ "message": "Sono il microservizio primo /quattro" }`

### Terzo Microservizio
Il terzo microservizio fornisce una pagina HTML alla rotta di default `/` dove è possibile vedere i risultati delle chiamate alle quattro API REST fornite dai primi due microservizi.

## Prerequisiti

- Docker o Podman
- Maven
- OpenShift CLI (`oc`)
- Accesso a una registry Docker (utilizziamo `quay.io` in questo esempio)

## Costruzione dei Microservizi

### Clonare il Repository
Clonare il repository contenente tutti i microservizi:
```sh
git clone <repository-url>
cd <repository-directory>


```
### Buildare con Maven Navigare nella directory di ciascun microservizio e buildare l'applicazione:

```sh
cd primo-microservizio
mvn clean package
cd ../secondo-microservizio
mvn clean package
cd ../terzo-microservizio
mvn clean package
```

### Buildare e Pushare le Immagini Docker con Docker o Podman 
#### Primo Microservizio

**Con Docker:**
```sh
cd primo-microservizio
docker build -t quay.io/victor_carrilho_ibm/primo-microservizio:latest -f Dockerfile .
docker push quay.io/victor_carrilho_ibm/primo-microservizio:latest
cd ..
```

**Con Podman:**
```sh
cd primo-microservizio
podman build -t quay.io/victor_carrilho_ibm/primo-microservizio:latest -f Dockerfile .
podman push quay.io/victor_carrilho_ibm/primo-microservizio:latest
cd ..
```

### Buildare e Pushare le Immagini Docker con Docker o Podman
#### Secondo Microservizio

**Con Docker:**
```sh
cd secondo-microservizio
docker build -t quay.io/victor_carrilho_ibm/secondo-microservizio:latest -f Dockerfile .
docker push quay.io/victor_carrilho_ibm/secondo-microservizio:latest
cd ..
```

**Con Podman:**
```sh
cd secondo-microservizio
podman build -t quay.io/victor_carrilho_ibm/secondo-microservizio:latest -f Dockerfile .
podman push quay.io/victor_carrilho_ibm/secondo-microservizio:latest
cd ..
```

### Buildare e Pushare le Immagini Docker con Docker o Podman
#### Frontend

**Con Docker:**
```sh
cd frontend
docker build -t quay.io/victor_carrilho_ibm/frontend:latest -f Dockerfile .
docker push quay.io/victor_carrilho_ibm/frontend:latest
cd ..
```

**Con Podman:**
```sh
cd frontend
podman build -t quay.io/victor_carrilho_ibm/frontend:latest -f Dockerfile .
podman push quay.io/victor_carrilho_ibm/frontend:latest
cd ..
```


Deployment su OpenShift 
-----------------------
### Installazione dell'Operatore Open Liberty 
1. Accedere alla console web di OpenShift. 
2. Navigare verso **OperatorHub**. 
3. Cercare **Open Liberty Operator**. 
4. Installare l'operatore nel namespace desiderato.

### Deploy dei Microservizi 
1. Applicare il file di deployment per il primo microservizio:

```sh
cd primo-microservizio
oc apply -f deploy.yaml
```
2. Applicare il file di deployment per il secondo microservizio:

```sh
cd secondo-microservizio
oc apply -f deploy.yaml
```

3. Applicare il file di deployment per il frontend:

```sh
cd frontend
oc apply -f deploy.yaml
```

### Verifica del Deployment 1. Controllare che i pod siano in esecuzione:

```sh
  oc get pods
```
```sh
  oc get services
```
```sh
  oc get routes
```

## Come creare una gestione delle App Liberty con Argocd 
### Per prima cosa bisgna fare apply del [**bootstrap-argocd.yaml**]()

```sh
oc apply -f bootstrap-argocd.yaml
```

## Nota per problemi con Argocd 

### potrebbe esserci un problema con il service account di argocd che non ha i permessi di fare apply dei file di configurazioni delle applicazioni , in tal caso bisogna aggiungere i permessi al corretto service Role con questo comando:

```sh
oc adm policy add-role-to-user admin system:serviceaccount:openshift-gitops:openshift-gitops-otp-argocd-application-controller -n libertydemowithargo
```

