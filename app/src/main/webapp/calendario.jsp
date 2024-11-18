<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <link href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
  <link href="http://localhost:8080/webjars/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
  <!-- arquivos style -->
  <link href="./css/styles.css" rel="stylesheet">
  <link href="./css/dark-mode.css" rel="stylesheet">
  <link href="./css/dashboard.css" rel="stylesheet">

  <title>GPPN | Agenda</title>
</head>
<body>

    <!-- Navbar -->
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
            <!-- Sidebar -->
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
                            <a class="nav-link" href="/ExibirEmpresas">
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
<div id= "teste">
            <!-- Main Content -->
            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <!-- Dark Mode Toggle -->
                <div class="toggle my-3">
                    <input id="switch" type="checkbox" name="theme">
                    <label for="switch">Toggle Dark Mode</label>
                </div>

                <!-- Calendar Container -->
                <div id="container">
                    <div id="header">
                        <div id="monthDisplay"></div>
                        <div>
                            <button id="backButton">Voltar</button>
                            <button id="nextButton">Próximo</button>
                        </div>
                    </div>

                    <div id="weekdays">
                        <div>Domingo</div>
                        <div>Segunda-feira</div>
                        <div>Terça-feira</div>
                        <div>Quarta-feira</div>
                        <div>Quinta-feira</div>
                        <div>Sexta-feira</div>
                        <div>Sábado</div>
                    </div>

                    <!-- Dynamic Calendar -->
                    <div id="calendar"></div>
                </div>

                <!-- New Event Modal -->
               <div id="newEventModal">
                   <h2>Novo Evento</h2>
                   <input id="eventTitleInput" placeholder="Título do Evento"/>
                   <input id="eventTimeInput" type="time" placeholder="Hora do Evento"/>
                   <button id="saveButton">Salvar</button>
                   <button id="cancelButton">Cancelar</button>
               </div>

                <!-- Delete Event Modal -->
                <div id="deleteEventModal">
                    <h2>Evento</h2>
                    <div id="eventText"></div><br>
                    <button id="deleteButton">Deletar</button>
                    <button id="closeButton">Fechar</button>
                </div>

                <div id="modalBackDrop"></div>
            </main>
        </div>
    </div>
</div>
    <script src="http://localhost:8080/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="./js/darkMode.js"></script>
    <script src="./js/script.js"></script>
    <script src="../js/feather.min.js"></script>
    <script src="../js/dashboard.js"></script>
</body>
</html>
</html>