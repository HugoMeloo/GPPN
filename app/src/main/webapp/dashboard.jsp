<!DOCTYPE html>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="UTF-8">
    <link href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="http://localhost:8080/webjars/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/dashboard.css" rel="stylesheet">
    <title>GPPN | Empresas</title>
</head>
<body>
<header class="navbar navbar-dark sticky-top bg-warning text-dark flex-md-nowrap p-0 shadow">
<a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6 bg-warning text-dark fw-bold" href="#">GPPN</a>
        <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <input class="form-control form-control-dark w-100 rounded-0 border-0" type="text" placeholder="Search" aria-label="Search">
        <div class="navbar-nav" >
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
                            <a class="nav-link" href="/">
                                <span data-feather="calendar" class="align-text-bottom"></span> Agenda
                            </a>
                        </li>
                        <li class="nav-item">
                             <a class="nav-link" href="/">
                                 <span data-feather="box" class="align-text-bottom"></span> Estoque
                             </a>
                        </li>
                         <li class="nav-item">
                            <a class="nav-link" href="/">
                            <span data-feather="dollar-sign" class="align-text-bottom"></span> Financeiro
                            </a>
                         </li>
                    </ul>
                </div>
            </nav>

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <h2>Empresas</h2>
                <div class="table-responsive">
                   <table class="table table-striped table-sm">
                       <tr>
                           <th>ID</th>
                           <th>Empresa</th>
                           <th>Endereço</th>
                           <th>Telefone</th>
                           <th>Serviço</th>
                           <th>E-mail</th>

                           <c:if test="${sessionScope.loggedUser != null}">
                               <th>Actions</th>
                           </c:if>
                       </tr>
                       <c:forEach var="empresa" items="${empresas}">
                           <tr>
                               <td>${empresa.id}</td>
                               <td>${empresa.nome}</td>
                               <td>${empresa.endereco}</td>
                               <td>${empresa.telefone}</td>
                               <td>${empresa.servico}</td>
                               <td>${empresa.email}</td>

                               <c:if test="${sessionScope.loggedUser != null}">
                                   <td>
                                       <form action="/DeletarEmpresa" method="post">
                                           <input type="hidden" id="id" name="id" value="${empresa.id}">
                                           <button class="btn btn-primary" type="submit">Delete</button>
                                           <a class="btn btn-secondary" href="empresas.jsp?id=${empresa.id}&nome=${empresa.nome}&endereco=${empresa.endereco}&telefone=${empresa.telefone}&servico=${empresa.servico}&email=${empresa.email}&image=${empresa.image}">Update</a>
                                       </form>
                                   </td>
                               </c:if>
                           </tr>
                       </c:forEach>
                   </table>
                </div>
            </main>
        </div>
    </div>

    <script src="http://localhost:8080/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="../js/feather.min.js"></script>
    <script src="../js/dashboard.js"></script>
</body>
</html>