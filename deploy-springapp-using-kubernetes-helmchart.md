# 🚀 Spring Boot + MongoDB Deployment using Helm

---
## 🧾 Overview
This guide walks you through deploying a Spring Boot application backed by MongoDB to Kubernetes using **Helm** for templated, configurable deployments.

By the end of this tutorial, you'll be able to:
- Build and push a Spring Boot app Docker image
- Deploy your app and MongoDB using Helm
- Manage environment-specific configurations using `values.yaml` files
- Dry-run and validate Helm charts before deployment
- Monitor and debug your deployed app

---
## 📁 Directory Structure
```bash
helmchart/
├── Chart.yaml
├── values-dev.yaml
├── values-prod.yaml
├── charts/
└── templates/
    ├── deployment-springpapp.yaml
    ├── service-springapp.yaml
    ├── deployment-mongodb.yaml
    ├── service-mongodb.yaml
```

---
## 🛠 Prerequisites
- Docker
- Kubernetes cluster (Minikube, KIND, or cloud-based)
- `kubectl` CLI
- `helm` CLI (v3+)
- Maven
- Docker Hub account

---
## 🔨 Step 1: Build the Spring Boot Project
```bash
mvn clean package
```

---
## 🐳 Step 2: Build Docker Image
```bash
docker build -t thedevroom/spring-boot-mongo-application:1 .
```

---
## 🔐 Step 3: Login to Docker Hub
```bash
docker login -u thedevroom
```

---
## 📦 Step 4: Push Docker Image
```bash
docker push thedevroom/spring-boot-mongo-application:1
```

---
## ✏️ Step 5: Review or Update Helm Values
Edit `helmchart/values-dev.yaml` to adjust:
- Image tag
- Replica counts
- MongoDB credentials
- Port values

---
## 🚧 Step 6: Dry-run and Template Validation

### Dry-run install
```bash
helm install springboot-mongo ./helmchart --values helmchart/values-dev.yaml --dry-run --debug
```

### Render templates
```bash
helm template springboot-mongo ./helmchart --values helmchart/values-dev.yaml
```

---
## 🚀 Step 7: Deploy Using Helm
```bash
helm install springboot-mongo ./helmchart -f ./helmchart/values-dev.yaml --namespace app --create-namespace
```

---
## 📊 Step 8: Monitor the Deployment
```bash
watch kubectl get pods -n app
```

---
## 🌐 Step 9: Access the Spring Boot Application

### Option 1: Get NodePort
```bash
kubectl get svc -n app
```

Access via:
```
http://<NodeIP>:<NodePort>
```

### Option 2: Minikube
```bash
minikube service springapp-service -n app
```

---
## 🔎 Step 10: Access Logs and Debug

### Get Pod Name
```bash
kubectl get pods -n app
```

### Access Shell
```bash
kubectl exec -it <pod-name> -n app -- /bin/sh
```

---
## 🧹 Step 11: Uninstall the Release
```bash
helm uninstall springboot-mongo -n app
```

If namespace cleanup is needed:
```bash
kubectl delete namespace app
```

---
## ✅ Final Notes
- Use Secrets and ConfigMaps in production
- Enable resource requests/limits and probes for better resilience
- Split values files for different environments (e.g., `values-prod.yaml`, `values-staging.yaml`)

---
## ✅ Summary
This guide showed you how to:
- Package and containerize a Spring Boot + MongoDB app
- Use Helm for deploying to Kubernetes
- Validate charts before deployment
- Access and monitor the app

---
## 📚 References
- [Helm Docs](https://helm.sh/docs/)
- [Kubernetes Docs](https://kubernetes.io/docs/)
- [Docker Docs](https://docs.docker.com/)
- [Spring Boot Docs](https://spring.io/projects/spring-boot)

---
## 👨‍💻 Created by: TheDevRoom
- 🌐 Website: [TheDevRoom](https://github.com/localhost-devel/localhost-devel/blob/master/README.md)
- 📞 Contact: +91 9999999999