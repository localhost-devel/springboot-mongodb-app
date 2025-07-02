
# 🚀 Spring Boot + MongoDB Deployment using Kubernetes + Kustomize

---
## 🧾 Overview
This guide walks you through deploying a Spring Boot application backed by MongoDB to Kubernetes using **Kustomize** for environment-specific configuration management.

By the end of this tutorial, you'll be able to:
- Package and push a Spring Boot app Docker image
- Deploy your app and MongoDB using Kubernetes manifests
- Use Kustomize to manage overlays for environments
- Monitor and debug your running application

---
## 🔍 About Kustomize
Kustomize is a configuration management tool built into `kubectl` that allows you to customize raw, template-free YAML files for multiple environments. It helps manage overlays and base configurations without modifying the original manifests.

---
## 🛠 Prerequisites
- 🐳 [Docker](https://github.com/localhost-devel/thedevroom/blob/master/TheDevRoom/docker/docker.md#-installation-guide)
- Kubernetes Cluster – You can use Minikube for local development.
    - 📘 [Minikube Setup Guide](hhttps://github.com/localhost-devel/thedevroom/blob/master/TheDevRoom/kubernetes-setup/setup-k8s/setup_minikube.md)
- `kubectl` CLI
- `kustomize` or `kubectl` with built-in Kustomize support
- Maven
- Docker Hub account

---
## 📁 Directory Structure
```bash
kustomize/
└── k8s
    ├── base
    │   ├── database
    │   │   ├── deployment.yaml
    │   │   └── service.yaml
    │   ├── foundation
    │   │   └── namespace.yaml
    │   ├── kustomization.yaml
    │   └── springapp
    │       ├── deployment.yaml
    │       └── service.yaml
    └── overlays
        └── dev
            ├── kustomization.yaml
            └── patch
                ├── database.yaml
                └── springapp.yaml
```

---
## 🔨 Step 1: Build the Spring Boot Project
```bash
mvn clean package
```
> Compiles your Spring Boot project and creates a `.jar` file inside the `target/` directory.

---
## 🐳 Step 2: Build the Docker Image
```bash
docker build -t thedevroom/spring-boot-mongo-application:1 .
```
> Builds the Docker image using your Dockerfile and tags it appropriately.

---
## 🔐 Step 3: Login to Docker Hub
```bash
docker login -u thedevroom
```
> Authenticate with your Docker Hub credentials.

---
## 📦 Step 4: Push Docker Image to Docker Hub
```bash
docker push thedevroom/spring-boot-mongo-application:1
```
> Pushes the image to your Docker Hub repository.

---
## ✏️ Step 5: Update Patch File
Update the image tag and replica count in `kustomize/k8s/overlays/dev/patch/springapp.yaml` and `database.yaml` as needed.

---
## 🚀 Step 6: Deploy to Kubernetes
```bash
kubectl apply -k kustomize/k8s/overlays/dev/
```
> Deploys MongoDB, Spring Boot app, and namespace using Kustomize overlay.

---
## 📊 Step 7: Monitor Pod Status
```bash
watch kubectl get pods -n app
```
> Continuously monitors pods under the `app` namespace.

---
## 🌐 Step 8: Access the Spring Boot Application

### Option 1: Get NodePort Manually
```bash
kubectl get svc -n app
```
> Note the NodePort for `springapp-service` and access it:
```
http://<NodeIP>:<NodePort>
```

### Option 2: Access via Minikube
```bash
minikube service springapp-service -n app
```
> Opens the app in your browser directly (Minikube only).

---
## 🔎 Step 9: Access Pod for Logs/Debugging

### Get Pod Name
```bash
kubectl get pods -n app
```

### Access Pod Shell
```bash
kubectl exec -it <pod-name> -n app -- /bin/sh
```
Example:
```bash
kubectl exec -it springapp-6f55c47d9c-abc12 -n app -- /bin/sh
```

---
## 🧹 Step 10: Cleanup Kubernetes Resources
```bash
kubectl delete -k ./kustomize/k8s/overlays/dev/
```
> Deletes the namespace and all deployed resources.

---
## ✅ Final Notes
- Ensure your Docker image is publicly accessible or configure Kubernetes to access private images
- Make use of readiness and liveness probes in production
- For production environments, consider using Secrets and ConfigMaps properly for secure configuration

---
## ✅ Summary
This guide demonstrated how to:
- Package and containerize a Spring Boot + MongoDB app
- Deploy it using Kustomize and Kubernetes
- Use environment-specific overlays
- Monitor and clean up your deployments

---
## 📚 References:
- [Kustomize Docs](https://kubectl.docs.kubernetes.io/pages/app_management/introduction.html)
- [Kubernetes Documentation](https://kubernetes.io/docs/)
- [Docker Docs](https://docs.docker.com/)
- [Spring Boot Docs](https://spring.io/projects/spring-boot)

---
## 👨‍💻 Created by: TheDevRoom
- 🌐 Website: [TheDevRoom](https://github.com/localhost-devel/localhost-devel/blob/master/README.md)
- 📞 Contact: +91 9999999999