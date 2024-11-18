<!DOCTYPE html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="UTF-8" />
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <link href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="http://localhost:8080/webjars/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/dashboard.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <title>GPPN | Financeiro</title>
</head>
<body>
    <header class="navbar navbar-dark sticky-top bg-warning text-dark flex-md-nowrap p-0 shadow">
        <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6 bg-warning text-dark fw-bold" href="#">GPPN</a>
        <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <input class="form-control form-control-dark w-100 rounded-0 border-0" type="text" placeholder="Search" aria-label="Search">
        <div class="navbar-nav">
            <div class="nav-item text-nowrap">
                <a class="nav-link px-3" href="/logout">Sign out</a>
            </div>
        </div>
    </header>

    <div class="container-fluid">
        <div class="row">
            <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-warning text-dark sidebar collapse">
                           <div class="position-sticky pt-3 sidebar-sticky">
                               <ul class="nav flex-column">
                                   <li class="nav-item">
                                       <a class="nav-link" aria-current="page" href="#">
                                           <span data-feather="user" class="align-text-bottom"></span>
                                           <c:if test="${sessionScope.loggedUser != null}">
                                               <span>${sessionScope.loggedUser}</span>
                                           </c:if>
                                       </a>
                                   </li>
                               </ul>
                               <hr>
                               <ul class="nav flex-column">
                                   <li class="nav-item">
                                       <a class="nav-link" aria-current="page" href="/ExibirEmpresas">
                                           <span data-feather="home" class="align-text-bottom"></span> Empresas
                                       </a>
                                   </li>
                                   <li class="nav-item">
                                       <a class="nav-link" href="/calendario.jsp">
                                           <span data-feather="calendar" class="align-text-bottom"></span> Agenda
                                       </a>
                                   </li>
                                   <li class="nav-item">
                                       <a class="nav-link" href="/ExibirProdutos">
                                           <span data-feather="box" class="align-text-bottom"></span> Estoque
                                       </a>
                                   </li>
                                   <li class="nav-item">
                                       <a class="nav-link" href="/financeiro.jsp">
                                           <span data-feather="dollar-sign" class="align-text-bottom"></span> Financeiro
                                       </a>
                                   </li>
                               </ul>
                           </div>
                       </nav>

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <h2>Financeiro</h2>


                <div class="position-fixed bottom-0 end-0 m-3">
                    <div class="dropdown">
                        <button class="btn bg-warning text-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Ações
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#cadastrarProdutoModal">Financeiro</a></li>
                        </ul>
                    </div>
                </div>

                  <canvas id="myChart" width="400" height="250"></canvas>

                  <script>
                      fetch('GetChartData')
                          .then(response => response.json())
                          .then(data => {
                              const labels = data.map(item => item.label);
                              const values = data.map(item => item.value);
                              const colors = data.map(item => item.color); // Extraindo cores do JSON

                              const ctx = document.getElementById('myChart').getContext('2d');
                              new Chart(ctx, {
                                  type: 'bar',
                                  data: {
                                      labels: labels,
                                      datasets: [{
                                          label: 'Valor Total (Preço x Quantidade)',
                                          data: values,
                                          backgroundColor: colors, // Aplicando cores diferentes
                                          borderColor: colors.map(color => color.replace('0.2', '1')), // Borda mais opaca
                                          borderWidth: 1
                                      }]
                                  },
                                  options: {
                                      scales: {
                                          y: {
                                              beginAtZero: true
                                          }
                                      }
                                  }
                              });
                          })
                          .catch(error => console.error('Erro ao carregar os dados:', error));
                  </script>


    <script src="http://localhost:8080/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="../js/feather.min.js"></script>
    <script src="../js/dashboard.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
