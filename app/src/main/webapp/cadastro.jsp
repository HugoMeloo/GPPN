<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html" charset=UTF-8>
    <link rel="stylesheet" href="style-cadastro.css">
    <title>Cadastro GPPN</title>
</head>
<body>
<div class= "bg-warning text-dark p-3">
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="register-box text-center p-4">
            <h1>GPPN</h1>
            <form action="/telaCadastro" method="post">
                <div class="form-row">
                    <div class="form-group">
                        <label for="nome">Nome:</label>
                        <input type="text" class="form-control" id="nome" name="nome" required>
                    </div>
                    <div class="form-group">
                        <label for="sobrenome">Sobrenome:</label>
                        <input type="text" class="form-control" id="sobrenome" name="sobrenome" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="endereco">Endereço:</label>
                    <input type="text" class="form-control" id="endereco" name="endereco" required>
                </div>

                <div class="form-group">
                    <label for="telefone">Telefone:</label>
                    <input type="tel" class="form-control" id="telefone" name="telefone" placeholder="(DDD) Telefone" required>
                </div>

                <div class="form-group">
                    <label for="email">E-mail:</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <label for="senha">Senha:</label>
                    <input type="password" class="form-control" id="senha" name="senha" required>
                </div>

                <div class="form-group">
                    <label for="confirmar-senha">Confirmar Senha:</label>
                    <input type="password" class="form-control" id="confirmar-senha" name="confirmar-senha" required>
                </div>

                <button type="submit" class="btn btn-primary btn-block font-weight-bold mt-3">Cadastrar-se</button>
            </form>
        </div>
    </div>
</body>
</html>