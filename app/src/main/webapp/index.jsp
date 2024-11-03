<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta http-equiv="Content-Type" content="text/html" charset=UTF-8>
    <link rel="stylesheet" href="style-index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <title>GPPN - Software de Gestão para Pequenos Negócios</title>
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
                        <a class="nav-link" href="#">Empresas</a>
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
<div class ="bg-warning text-dark p-3">

    <!-- Main content -->
    <main class="container py-5">
        <!-- Intro Section -->
        <section class="text-center mb-5">
            <h1 class="display-4">Software de Gestão para Pequenos Negócios</h1>
            <h2 class="lead">Bem-vindo GPPN!</h2>
            <p class="mt-4">
                Transforme a forma como você gerencia seu negócio com nosso software de gestão intuitivo e acessível, desenvolvido
                especialmente para pequenos empreendedores. Sabemos que a rotina de um pequeno negócio é desafiadora, e estamos aqui para
                facilitar sua jornada.
            </p>
        </section>

        <!-- Soluções Section -->
        <section>
            <h3 class="text-center mb-4">Nossas Soluções</h3>
            <div class="row">
                <div class="col-md-6 mb-3">
                    <h5>Gestão de Estoque</h5>
                    <p>Mantenha o controle do seu inventário de forma simples e eficiente, evitando perdas e garantindo que você sempre tenha o que precisa em mãos.</p>
                </div>
                <div class="col-md-6 mb-3">
                    <h5>Agendamento</h5>
                    <p>Organize seus compromissos e serviços com facilidade, garantindo que seus clientes sejam atendidos com pontualidade e eficiência.</p>
                </div>
                <div class="col-md-6 mb-3">
                    <h5>Controle Financeiro</h5>
                    <p>Tenha uma visão clara das finanças do seu negócio, com relatórios detalhados que ajudam você a tomar decisões informadas.</p>
                </div>
                <div class="col-md-6 mb-3">
                    <h5>Agendamento de Serviços</h5>
                    <p>Facilite a vida dos seus clientes com um sistema de agendamento prático, que permite que eles marquem horários de forma rápida e sem complicações.</p>
                </div>
            </div>
        </section>

        <!-- Por que escolher GPPN Section -->
        <section class="mt-5">
            <h3 class="text-center mb-4">Por que escolher o GPPN?</h3>
            <p>
                Nossa missão é democratizar o acesso à tecnologia para pequenos negócios, oferecendo ferramentas que realmente fazem a
                diferença. Com um design amigável e suporte dedicado, garantimos que você tenha todo o auxílio necessário para prosperar.
            </p>
            <p>
                Dê o próximo passo para simplificar a gestão do seu negócio. Experimente o GPPN e descubra como podemos ajudar você a crescer!
            </p>
        </section>

        <!-- Call to Action Section -->
        <section class="text-center mt-5">
            <h3>Comece Agora!</h3>
            <p>Faça parte da nossa comunidade de empreendedores que já estão transformando suas empresas. <a href="cadastro.jsp">Cadastre-se</a> e aproveite uma demonstração gratuita!</p>

        </section>
    </main>
  </div>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>