<br />
<div align="center">

  <h3 align="center">apl-back-fase1</h3>

  <p align="center">
    Application created for the challenge 1 in software architeture graduation.
    <br />
    <br />
    <a href="https://github.com/SOAT2G58/apl-back-fase1/issues">Report Bug</a>
    ·
    <a href="https://github.com/SOAT2G58/apl-back-fase1/issues">Request Feature</a>
  </p>
</div>


<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#local-execution">Local Execution</a>
      <ul>
        <li><a href="#setup">Set up</a></li>
        <li><a href="#run-locally">Run locally</a></li>
      </ul>
    </li>
  </ol>
</details>


## About The Project

This application was created by:
- Rafael Hans Sandalo, rm350051, rafael.hans@gmail.com
- Gabriel Almeida dos Santos, rm430120, gabrielalmeidads@gmail.com
- Silvia Mara Rodrigues Florido, rm349730, sflorido@gmail.com
- Paulo Lobo Neto, rm430057, pauloloboneto@gmail.com 
- Matheus Patusco Bascur,  rm350519, m.patusco13@gmail.com 

Using Hexagonal architecture concepts and DDD concepts, where can get access in this link
https://miro.com/app/board/uXjVMC27TvQ=/?share_link_id=505879927156


<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Built With

<div align="center"> 

[![Docker][Docker]][Docker-url]

</div> 

<div align="center"> 

[![Java][Java]][Java-url]

</div> 

<div align="center"> 

[![postgres][postgres]][postgres-url]

</div> 


<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Set up

To run this application locally, follow these steps:

1. Add this information in the archive .env:
  - POSTGRES_ROOT_USER=postgres
  - POSTGRES_ROOT_PASSWORD=postgres
  - POSTGRES_DATABASE=postgres
  - POSTGRES_LOCAL_PORT=5432
  - POSTGRES_DOCKER_PORT=5432
  - SPRING_LOCAL_PORT=9090
  - SPRING_DOCKER_PORT=9090
  - PGADMIN_ROOT_EMAIL=admin@domain.com
  - PGADMIN_ROOT_PASSWORD=admin


### Run locally

1. Execute the docker compose
2. And can use this postman collection to make requests:
  https://documenter.getpostman.com/view/14258182/2s93zGzyQk

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
[Java]: https://img.shields.io/badge/Java-0769AD?style=for-the-badge&logo=java&logoColor=white
[Java-url]: https://www.java.com/pt-BR/

[Docker]: https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white
[Docker-url]: https://www.docker.com/

[postgres]: https://img.shields.io/badge/postgress-00000F?style=for-the-badge&logo=postgress&logoColor=white
[postgres-url]: https://www.postgresql.org/docs/

