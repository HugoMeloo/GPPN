 function checkAddNewOption(produtoId) {
     const selectField = document.getElementById(`tipoItem-${produtoId}`);
     const newField = document.getElementById(`newTipoItem-${produtoId}`);
     const addButton = document.getElementById(`addTipoItemButton-${produtoId}`);

     if (selectField.value === "addNew") {
         newField.style.display = "block"; // Exibe o campo de texto
         addButton.style.display = "inline-block"; // Exibe o botão
     } else {
         newField.style.display = "none"; // Esconde o campo de texto
         addButton.style.display = "none"; // Esconde o botão
     }
 }

 function addNewOption(produtoId) {
     const selectField = document.getElementById(`tipoItem-${produtoId}`);
     const newField = document.getElementById(`newTipoItem-${produtoId}`);
     const newOptionValue = newField.value.trim();

     if (newOptionValue !== "") {
         // Envia o novo tipo para o backend
         fetch('/SalvarNovoTipo', {
             method: 'POST',
             headers: {
                 'Content-Type': 'application/x-www-form-urlencoded'
             },
             body: `novoTipo=${encodeURIComponent(newOptionValue)}`
         })
         .then(response => {
             if (response.ok) {
                 // Adiciona a nova opção ao dropdown
                 const newOption = document.createElement("option");
                 newOption.value = newOptionValue; // Define o valor como o novo tipo
                 newOption.textContent = newOptionValue; // Define o texto exibido
                 selectField.appendChild(newOption);

                 // Seleciona a nova opção
                 selectField.value = newOptionValue;

                 // Esconde e limpa o campo de texto
                 newField.style.display = "none";
                 newField.value = "";
                 document.getElementById(`addTipoItemButton-${produtoId}`).style.display = "none";
             } else {
                 alert("Erro ao salvar o novo tipo.");
             }
         })
         .catch(error => console.error('Erro ao enviar novo tipo:', error));
     } else {
         alert("Por favor, insira um tipo válido.");
     }
 }

 // Carrega os tipos de itens ao carregar a página
 document.addEventListener('DOMContentLoaded', () => {
     document.querySelectorAll('[id^="tipoItem-"]').forEach(selectField => {
         const produtoId = selectField.id.split('-')[1]; // Extrai o ID dinâmico
         fetch('/ListarTipos')
             .then(response => response.json())
             .then(data => {
                 data.forEach(tipo => {
                     const option = document.createElement("option");
                     option.value = tipo.id;
                     option.textContent = tipo.descricao;
                     selectField.insertBefore(option, selectField.firstChild); // Adiciona antes da opção "Adicionar Novo"
                 });
             })
             .catch(error => console.error(`Erro ao carregar os tipos para produto ${produtoId}:`, error));
     });
 });