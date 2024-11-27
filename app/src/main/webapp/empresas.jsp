<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="style-index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">

    <title>GPPN - Software de Gestão para Pequenos Negócios</title>
     <style>
            /* Garante que a página ocupa toda a altura da janela */
            body {
                display: flex;
                flex-direction: column;
                min-height: 100vh;
                margin: 0;
            }
            main {
                flex: 1; /* Faz com que o conteúdo principal ocupe o espaço disponível */
            }
            footer {
                background-color: #ffc107; /* Cor de fundo amarela */
                color: #000; /* Texto preto */
                padding: 50px 0;
            }
        </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">GPPN</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="index.jsp">Produtos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/Empresas">Empresas</a>
                    </li>
                    <form class="d-flex ms-3">
                        <a class="btn btn-outline-light" href="/login" role="button">
                            <i class="bi bi-person"></i> Login
                        </a>
                    </form>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
<main>
  <div class="bg-warning text-dark vh-100 d-flex flex-column justify-content-center align-items-center">
      <div class="container">
          <h1 class="text-center">Empresas</h1>
          <div id="imageCarousel" class="carousel slide" data-bs-ride="carousel">
              <div class="carousel-inner">
                  <c:forEach var="empresa" items="${empresas}" varStatus="status">
                      <div class="carousel-item ${status.first ? 'active' : ''}">
                          <img src="${empresa.image}" class="d-block mx-auto img-thumbnail" style="width: 250px; height: auto;" alt="${empresa.servico}">
                          <div class="text-center mt-2">
                              <h5>${empresa.nome}</h5>
                              <p>${empresa.servico}</p>
                          </div>
                      </div>
                  </c:forEach>
              </div>
              <button class="carousel-control-prev" type="button" data-bs-target="#imageCarousel" data-bs-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Anterior</span>
              </button>
              <button class="carousel-control-next" type="button" data-bs-target="#imageCarousel" data-bs-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="visually-hidden">Próximo</span>
              </button>
          </div>
      </div>

      <!-- Call to Action Section -->
      <footer class="mt-5">
          <section class="text-center">
              <h3>Comece Agora!</h3>
              <p>Faça parte da nossa comunidade de empreendedores que já estão transformando suas empresas.
                  <a href="cadastro.jsp">Cadastre-se</a> e aproveite uma demonstração gratuita!</p>
          </section>
      </footer>
  </div>



    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
