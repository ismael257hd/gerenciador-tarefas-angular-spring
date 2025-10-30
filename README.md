# Projeto Gerenciador de Tarefas — Angular 20 + Spring Boot
	Aplicação Fullstack para gerenciamento de tarefas, com frontend em Angular 20 e backend em Spring Boot.  
	Permite criar, editar, listar e excluir tarefas de forma simples.

# Estrutura do Projeto
	projeto/
	├── frontend/project-gerenciador-tarefas # Aplicação Angular
	└── backend/gerenciador-tarefas # Aplicação Spring Boot

# Como o projeto foi criado
	# Backend
		- Criado com [Spring Initializr](https://start.spring.io/)  
		  - Java 21  
		  - Maven  
		  - Spring Boot 3.5.7  
		  - Artefato JAR  
		  - Banco de dados H2 (para testes em memória)
		- [Lombok](https://projectlombok.org/download) adicionado para reduzir boilerplate.

	# Frontend
		- Criado com Angular 20:
		``bash
		  ng new project-gerenciador-tarefas
		  cd project-gerenciador-tarefas
		  ng serve

# Bibliotecas e ferramentas instaladas:
	npm install bootstrap
	ng add @angular/material
	npm install @angular/animations@^20
	npm install -g @angular/cli
	npm install -g json-server
	json-server --watch db.json --port 3000
	json-server foi utilizado inicialmente para testar o frontend consumindo dados do arquivo db.json na raiz do projeto.

# Ambientes de Desenvolvimento
	Backend: Eclipse IDE for Enterprise Java and Web Developers Version: 2025-09 (4.37.0)
	Frontend: Visual Studio Code

# Tecnologias Utilizadas
	Camada	Tecnologias
	Frontend	Angular 20, TypeScript, HTML, SCSS, @angular/material@20.2.10
	Backend	Java 21, Spring Boot, Maven 3.8.3
	Ferramentas	Node 22.21.0, npm 10.9.4, Angular CLI 20.3.7, Windows 10/11

# Como Executar o Projeto
  # Backend (Spring Boot)
	cd backend/gerenciador-tarefas
	mvn spring-boot:run ou pelo Eclipse executando a classe GerenciadorTarefasApplication.java
	ou mvn clean spring-boot:run
	
	A aplicação iniciará em: http://localhost:8080
	
	Obs: para executar pelo mvn spring-boot:run deve ser configurada as variaveis de ambiente do sistema:
		JAVA_HOME=C:\Program Files\Java\jdk-21
		%JAVA_HOME%\bin
		
		MAVEN_HOME=C:\Program Files\Apache\apache-maven-3.8.3  
		%MAVEN_HOME%\bin
		
  # Como Executar o Projeto	
	mvn -Dtest=TaskControllerTest test ou pelo Eclipse executando a classe TaskControllerTest.java
    mvn -Dtest=TaskServiceImplTest test ou pelo Eclipse executando a classe TaskServiceImplTest.java
  # Frontend (Angular 20)
	cd frontend/project-gerenciador-tarefas
	npm install
	ng serve
	A aplicação estará disponível em: http://localhost:4200

# Observações
	Certifique-se de ter Java 21, Node 22+ e npm 10+ instalados.
