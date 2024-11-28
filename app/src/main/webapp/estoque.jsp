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
    <title>GPPN | Estoque</title>
</head>
<body>
    <header class="navbar navbar-dark sticky-top bg-warning text-dark flex-md-nowrap p-0 shadow">
        <a class="navbar-brand col-md-3 col-lg-2 me-0 px-3 fs-6 bg-warning text-dark fw-bold" href="/index.jsp">GPPN</a>
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
                <h2>Estoque</h2>

                <div class="table-responsive">
                    <table class="table table-striped table-sm">
                        <tr>
                            <th>ID</th>
                            <th>Produto</th>
                            <th>Quantidade</th>
                            <th>Preço Unitário</th>
                            <th>Código do Item</th>
                            <th>Tipo de Item</th>
                            <th>Imagem</th>
                            <c:if test="${sessionScope.loggedUser != null}">
                                <th>Ações</th>
                            </c:if>
                        </tr>
                        <c:forEach var="produto" items="${produtos}">
                            <tr>
                                <td>${produto.idProduto}</td>
                                <td>${produto.nomeProduto}</td>
                                <td>${produto.quantidade}</td>
                                <td>${produto.precoUni}</td>
                                <td>${produto.codigoItem}</td>
                                <td>${produto.tipoItem}</td>
                                <td><img src="${produto.imagemProduto}" alt="Imagem do Produto" width="50" height="50" style="border-radius: 50%;"></td>
                                <c:if test="${sessionScope.loggedUser != null}">
                                    <td>
                                        <div class="d-flex gap-2">
                                            <!-- Formulário de exclusão -->
                                            <form action="/DeletarProduto" method="post">
                                                <input type="hidden" id="idProduto" name="idProduto" value="${produto.idProduto}">
                                                <button class="btn btn-danger" type="submit">Delete</button>
                                            </form>

                                            <!-- Botão para abrir o modal de atualização -->
                                            <a class="btn btn-primary" href="#" data-bs-toggle="modal" data-bs-target="#updateProdutoModal-${produto.idProduto}">Update</a>

                                            <!-- Modal de atualização -->
                                            <div class="modal fade" id="updateProdutoModal-${produto.idProduto}" tabindex="-1" aria-labelledby="updateProdutoModalLabel-${produto.idProduto}" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content p-4">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="updateProdutoModalLabel-${produto.idProduto}">Atualizar Produto</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <!-- Formulário de atualização -->
                                                            <form action="/CadastrarProduto" method="post" enctype="multipart/form-data">
                                                                <input type="hidden" name="idProduto" id="idProduto" value="${produto.idProduto}">
                                                                <div class="form-group">
                                                                    <label for="produto-name-${produto.idProduto}">Nome do Produto:</label>
                                                                    <input type="text" class="form-control" id="produto-name-${produto.idProduto}" name="produto-name" value="${produto.nomeProduto}" required>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="quantidade-${produto.idProduto}">Quantidade:</label>
                                                                    <input type="number" class="form-control" id="quantidade-${produto.idProduto}" name="quantidade" value="${produto.quantidade}" required>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="preco-${produto.idProduto}">Preço Unitário (R$):</label>
                                                                    <input type="text" class="form-control" id="preco-${produto.idProduto}" name="preco" value="${produto.precoUni}" required>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="codigoItem-${produto.idProduto}">Código do Item:</label>
                                                                    <input type="text" class="form-control" id="codigoItem-${produto.idProduto}" name="codigoItem" value="${produto.codigoItem}" required>
                                                                </div>
                                                                      <div class="form-group">
                                                                          <label for="tipoItem-${produto.idProduto}">Tipo de Item:</label>
                                                                          <select class="form-control" id="tipoItem-${produto.idProduto}" name="tipoItem" onchange="checkAddNewOption('${produto.idProduto}')" required>
                                                                              <option value="Electronic" ${produto.tipoItem == 'Electronic' ? 'selected' : ''}>Electronic</option>
                                                                              <option value="Mechanic" ${produto.tipoItem == 'Mechanic' ? 'selected' : ''}>Mechanic</option>
                                                                              <option value="Accessory" ${produto.tipoItem == 'Accessory' ? 'selected' : ''}>Accessory</option>
                                                                              <option value="addNew">Adicionar Novo</option>
                                                                          </select>
                                                                          <!-- Campo de entrada que será exibido quando "Adicionar Novo" for selecionado -->
                                                                          <input type="text" class="form-control mt-2" id="newTipoItem-${produto.idProduto}" placeholder="Especifique o novo tipo" style="display: none;">
                                                                          <button type="button" class="btn btn-secondary mt-2" id="addTipoItemButton-${produto.idProduto}" style="display: none;" onclick="addNewOption('${produto.idProduto}')">Adicionar</button>
                                                                      </div>
                                                                <div class="form-group">
                                                                    <label for="image-produto-${produto.idProduto}">Escolha uma nova foto do produto (opcional):</label>
                                                                    <input type="file" class="form-control" name="image-produto" id="image-produto-${produto.idProduto}" value="${produto.imagemProduto}">
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
                            <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#cadastrarProdutoModal">Cadastrar Produto</a></li>
                        </ul>
                    </div>
                </div>

                <!-- Modal de cadastro de produto -->
                <div class="modal fade" id="cadastrarProdutoModal" tabindex="-1" aria-labelledby="cadastrarProdutoModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content p-4">
                            <div class="modal-header">
                                <h5 class="modal-title" id="cadastrarProdutoModalLabel">Cadastrar Produto</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form action="/CadastrarProduto" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="idProduto" id="idProduto" value="${param.idProduto}">

                                    <div class="form-group">
                                        <label for="produto-name">Nome do Produto:</label>
                                        <input type="text" class="form-control" id="produto-name" name="produto-name" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="quantidade">Quantidade:</label>
                                        <input type="number" class="form-control" id="quantidade" name="quantidade" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="preco">Preço Unitário:</label>
                                        <input type="number" class="form-control" id="preco" name="preco" step="0.01" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="codigoItem">Código do Item:</label>
                                        <input type="text" class="form-control" id="codigoItem" name="codigoItem" required>
                                    </div>
                                  <div class="form-group">
                                   <label for="tipoItem-${produto.idProduto}">Tipo de Item:</label>
                                   <select class="form-control" id="tipoItem-${produto.idProduto}" name="tipoItem" onchange="checkAddNewOption('${produto.idProduto}')" required>
                                       <option value="Electronic" ${produto.tipoItem == 'Electronic' ? 'selected' : ''}>Electronic</option>
                                       <option value="Mechanic" ${produto.tipoItem == 'Mechanic' ? 'selected' : ''}>Mechanic</option>
                                       <option value="Accessory" ${produto.tipoItem == 'Accessory' ? 'selected' : ''}>Accessory</option>
                                       <option value="addNew">Adicionar Novo</option>
                                   </select>
                                   <!-- Campo de entrada que será exibido quando "Adicionar Novo" for selecionado -->
                                   <input type="text" class="form-control mt-2" id="newTipoItem-${produto.idProduto}" placeholder="Especifique o novo tipo" style="display: none;">
                                   <button type="button" class="btn btn-secondary mt-2" id="addTipoItemButton-${produto.idProduto}" style="display: none;" onclick="addNewOption('${produto.idProduto}')">Adicionar</button>
                                   </div>
                                    <div class="form-group">
                                        <label for="imageProduto">Escolha uma nova foto do produto (opcional)</label>
                                        <input type="file" class="form-control" name="imageProduto" id="imageProduto">
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
    <script src="../js/drop.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
