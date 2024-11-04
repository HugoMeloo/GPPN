<!DOCTYPE html>
<html lang="en">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="UTF-8" />
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <link href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
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
                            <a class="nav-link" href="/estoque.jsp">
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
    <div class="d-flex gap-2">

    <!-- Formulário de exclusão -->
    <form action="/DeletarEmpresa" method="post">
        <input type="hidden" id="id" name="id" value="${empresa.id}">
        <button class="btn btn-danger" type="submit">Delete</button>
    </form>

    <!-- Botão para abrir o modal de atualização -->
    <a class="btn btn-primary" href="#" data-bs-toggle="modal" data-bs-target="#updateEmpresaModal-${empresa.id}">Update</a>

    <!-- Modal de atualização -->
    <div class="modal fade" id="updateEmpresaModal-${empresa.id}" tabindex="-1" aria-labelledby="updateEmpresaModalLabel-${empresa.id}" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content p-4">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateEmpresaModalLabel-${empresa.id}">Atualizar Empresa</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Formulário de atualização -->
                    <form action="/CadastrarEmpresa" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" id="id" value="${empresa.id}">
                        <div class="form-group">
                            <label for="empresa-name-${empresa.id}">Nome:</label>
                            <input type="text" class="form-control" id="empresa-name-${empresa.id}" name="empresa-name" value="${empresa.nome}" required>
                        </div>
                        <div class="form-group">
                            <label for="endereco-empresa-${empresa.id}">Endereço:</label>
                            <input type="text" class="form-control" id="endereco-empresa-${empresa.id}" name="endereco-empresa" value="${empresa.endereco}" required>
                        </div>
                        <div class="form-group">
                            <label for="telefone-empresa-${empresa.id}">Telefone:</label>
                            <input type="tel" class="form-control" id="telefone-empresa-${empresa.id}" name="telefone-empresa" value="${empresa.telefone}" required>
                        </div>
                        <div class="form-group">
                            <label for="servico-empresa-${empresa.id}">Tipo de Serviço:</label>
                            <input type="text" class="form-control" id="servico-empresa-${empresa.id}" name="servico-empresa" value="${empresa.servico}" required>
                        </div>
                        <div class="form-group">
                            <label for="email-empresa-${empresa.id}">E-mail:</label>
                            <input type="email" class="form-control" id="email-empresa-${empresa.id}" name="email-empresa" value="${empresa.email}" required>
                        </div>
                        <div class="form-group">
                            <label for="image-empresa-${empresa.id}">Escolha uma nova foto da empresa (opcional)</label>
                            <input type="file" class="form-control" name="image-empresa" id="image-empresa-${empresa.id}">
                        </div>
                        <button type="submit" class="btn btn-primary mt-3">Atualizar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="position-fixed bottom-0 end-0 m-3">
                    <div class="dropdown">
                        <button class="btn bg-warning text-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Ações
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#cadastrarEmpresaModal">Cadastrar Empresa</a></li>
                        </ul>
                    </div>
                </div>

                <div class="modal fade" id="cadastrarEmpresaModal" tabindex="-1" aria-labelledby="cadastrarEmpresaModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content p-4">
                            <div class="modal-header">
                                <h5 class="modal-title" id="cadastrarEmpresaModalLabel">Cadastrar Empresa</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form action="/CadastrarEmpresa" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="id" id="id" value="${param.id}">
                                    <div class="form-group">
                                        <label for="empresa-name">Nome:</label>
                                        <input type="text" class="form-control" id="empresa-name" name="empresa-name" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="endereco-empresa">Endereço:</label>
                                        <input type="text" class="form-control" id="endereco-empresa" name="endereco-empresa" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="telefone-empresa">Telefone:</label>
                                        <input type="tel" class="form-control" id="telefone-empresa" name="telefone-empresa" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="servico-empresa">Tipo de Serviço:</label>
                                        <input type="text" class="form-control" id="servico-empresa" name="servico-empresa" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="email-empresa">E-mail:</label>
                                        <input type="email" class="form-control" id="email-empresa" name="email-empresa" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="image-empresa">Escolha uma nova foto da empresa (opcional)</label>
                                        <input type="file" class="form-control" name="image-empresa" id="image-empresa">
                                    </div>
                                    <button type="submit" class="btn btn-primary mt-3">Cadastrar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>
    <script src="http://localhost:8080/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script src="../js/feather.min.js"></script>
    <script src="../js/dashboard.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
