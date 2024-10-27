<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html" charset=UTF-8>
    <link rel="stylesheet" href="style-login.css">
    <title>Login GPPN</title>
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="login-box text-center p-4">
            <h1>GPPN</h1>
            <form>
                <div class="form-group">
                    <label for="email">E-mail:</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <label for="senha">Senha:</label>
                    <input type="password" class="form-control" id="senha" name="senha" required>
                </div>

                <button type="submit" class="btn btn-light btn-block font-weight-bold mt-3">Acessar</button>
            </form>
            <p class="mt-3">Não tem uma conta? <a href="#">Cadastre-se</a></p>
            <p>Problemas de acesso? <a href="#">Fale conosco</a></p>
        </div>
    </div>
</body>
</html>