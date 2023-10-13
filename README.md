![](https://drive.google.com/file/d/1STE-tW1ah4VGdBFUWnGiFO7Yk9uo12Sf/view?usp=drive_link)

# Aplicativo de lista de tarefas

## Resumo
Aplicação simples de lista de tarefas, desenvolvida com o propósito de aprendizagem e conhecimento de uma das frameworks web mais utilizadas no momento com stack [Java](https://www.java.com/), [Spring Boot](ttps://spring.io/)


## Stack tecnológica
<a href="https://www.java.com/" title="Java"><img src="https://github.com/get-icon/geticon/raw/master/icons/java.svg" alt="Java" width="21px" height="21px"></a>
<a href="https://spring.io/" title="Spring"><img src="https://github.com/get-icon/geticon/raw/master/icons/spring.svg" alt="Spring" width="21px" height="21px"></a>
<a href="https://www.h2database.com/html/main.html" title="Spring"><img src="https://user-images.githubusercontent.com/140953/31317739-9791938a-ac46-11e7-86f3-d5e3e1e701b0.png" alt="H2 Database" width="21px" height="21px"></a>
<a href="https://git-scm.com/" title="Git"><img src="https://github.com/get-icon/geticon/raw/master/icons/git-icon.svg" alt="Git" width="21px" height="21px"></a>
<a href="https://www.docker.com/" title="docker"><img src="https://github.com/get-icon/geticon/raw/master/icons/docker-icon.svg" alt="docker" width="21px" height="21px"></a>

**Editor de texto/IDE**: <a href="https://code.visualstudio.com/" title="Visual Studio Code"><img src="https://github.com/get-icon/geticon/raw/master/icons/visual-studio-code.svg" alt="Visual Studio Code" width="21px" height="21px"></a>

### Pontos Funcionais
- [x] - Cadastro de usuário/login
  - [x] - Criação de usuário/login
- [x] - Cadastro de tarefa
  - [x] - Criação
  - [x] - Atualização
  - [x] - Atualização parcial
  - [x] - Consulta geral
- [x] - Autenticação básica

### Experimente

Deploy: [render.com](https://render.com/docs)
Aplicação implantada utilizando serviço free e runtime docker, utilizando serviço da render.

URL base aplicação: https://todolist-java-ger0.onrender.com<br>

#### **Endpoints**:

----
- **USER/Usuário:**
----
\[POST\]: https://todolist-java-ger0.onrender.com/user/

Request Body:
```json
{
  "name": "{nome do usuário}",
  "userName": "{login}",
  "password": "{senha}"
}
```

----
- **TASK/Tarefa:**
----
\[GET\]: https://todolist-java-ger0.onrender.com/task/

Response Body:
```json
[
  {
    "id": "f19d079a-1d04-4bff-8b80-0f09148cd701",
    "title": "Task 1",
    "description": "Minha task",
    "idUser": "fde7736a-16f8-4284-9c10-62fbc538c8c7",
    "priority": "Alta",
    "startAt": "2023-10-13T22:00:00",
    "endAt": "2023-10-14T23:00:00",
    "createdAt": "2023-10-13T16:20:51.834262"
  },
  {
    "id": "1ceb7667-143c-40f6-9700-ae807d751bbf",
    "title": "Task 2 1",
    "description": "Minha task 1",
    "idUser": "fde7736a-16f8-4284-9c10-62fbc538c8c7",
    "priority": "Baixa",
    "startAt": "2023-10-14T22:00:00",
    "endAt": "2023-10-15T23:00:00",
    "createdAt": "2023-10-13T16:21:04.529755"
  }
]
```

\[POST\]: https://todolist-java-ger0.onrender.com/task/

Request Body:
```json
{
  "description": "",
  "title": "",
  "priority": "Alta",
  "startAt": "yyyy-mm-ddTh:MM:ss",
  "endAt": "yyyy-mm-ddTh:MM:ss",
  "idUser": "{{idUser}}"
}
```

\[PUT\]: https://todolist-java-ger0.onrender.com/task/{idTask}

Request Body:
```json
{
  "description": "",
  "title": "",
  "priority": "Alta",
  "startAt": "yyyy-mm-ddTh:MM:ss",
  "endAt": "yyyy-mm-ddTh:MM:ss",
  "idUser": "{{idUser}}"
}
```

\[PATCH\]: https://todolist-java-ger0.onrender.com/task/{idTask}

Observação: Escolha uma ou N propriedades que deseja atualizar e envie a request somente com ela

Request Body:
```json
{
  // "description": "",
  // "title": "",
  // "priority": "Alta",
  // "startAt": "yyyy-mm-ddTh:MM:ss",
  // "endAt": "yyyy-mm-ddTh:MM:ss" 
}
```
