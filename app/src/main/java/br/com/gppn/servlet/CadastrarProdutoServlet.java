package br.com.gppn.servlet;

import dao.ProdutoDao;
import dao.TipoItemDao;
import model.Produto;
import model.TipoItem;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.fileupload.servlet.ServletFileUpload.isMultipartContent;

@WebServlet("/CadastrarProduto")


public class CadastrarProdutoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> parameters = uploadImage(req);

        String produtoId = parameters.get("idProduto");
        String nomeProduto = parameters.get("produto-name");
        double quantidade = Double.parseDouble(parameters.get("quantidade"));
        double precoUni = Double.parseDouble(parameters.get("preco"));
        String codigoItem = parameters.get("codigoItem");
        String tipoItem = parameters.get("tipoItem"); // Pode ser ID ou descrição
        String imagemProduto = parameters.get("imageProduto");
        String novoTipo = parameters.get("newTipoItem"); // Recebe a descrição do novo tipo, se aplicável

        // Log para depuração
        System.out.println(produtoId);
        System.out.println(nomeProduto);
        System.out.println(quantidade);
        System.out.println(precoUni);
        System.out.println(codigoItem);
        System.out.println("TipoItem: " + tipoItem);
        System.out.println("NovoTipo: " + novoTipo);
        System.out.println(imagemProduto);

        ProdutoDao produtoDao = new ProdutoDao();
        TipoItemDao tipoItemDao = new TipoItemDao();

        try {
            // Verifica se tipoItem é um ID ou uma descrição
            String tipoItemDescricao;

            if ("addNew".equals(tipoItem)) {
                // Caso seja um novo tipo
                if (novoTipo != null && !novoTipo.isBlank()) {
                    int novoTipoId = tipoItemDao.salvarNovoTipo(novoTipo); // Salva o novo tipo e obtém o ID
                    tipoItemDescricao = novoTipo; // Usa a descrição do novo tipo
                    tipoItem = String.valueOf(novoTipoId); // Atualiza tipoItem com o ID gerado
                } else {
                    throw new IllegalArgumentException("Descrição do novo tipo de item é inválida.");
                }
            } else {
                // Caso tipoItem seja um ID ou uma descrição
                try {
                    // Tenta interpretar tipoItem como um ID
                    tipoItemDescricao = tipoItemDao.buscarDescricaoPorId(Integer.parseInt(tipoItem));
                } catch (NumberFormatException e) {
                    // Se não for um número, assume que já é uma descrição válida
                    tipoItemDescricao = tipoItem;
                }
            }

            System.out.println("TipoItem (Descrição): " + tipoItemDescricao);

            // Cria o objeto produto com a descrição do tipo
            Produto produto = new Produto(produtoId, nomeProduto, quantidade, precoUni, codigoItem, tipoItemDescricao, imagemProduto);

            // Decide se é uma criação ou atualização
            if (produtoId == null || produtoId.isBlank()) {
                produtoDao.createProduto(produto);
            } else {
                produtoDao.updateProduto(produto);
            }

            // Redireciona após sucesso
            resp.sendRedirect("/ExibirProdutos");
        } catch (Exception e) {
            // Log de erro e resposta de erro
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Erro ao cadastrar produto: " + e.getMessage());
        }
    }




    private Map<String, String> uploadImage(HttpServletRequest httpServletRequest) {

        Map<String, String> requestParameters = new HashMap<>();

        if (isMultipartContent(httpServletRequest)) {

            try {

                DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

                List<FileItem> fileItems = new ServletFileUpload(diskFileItemFactory).parseRequest(httpServletRequest);

                for (FileItem fileItem : fileItems) {

                    checkFieldType(fileItem, requestParameters);

                }

            } catch (Exception ex) {

                requestParameters.put("imageProduto", "img/default-produto.jpg");

            }

        }

        return requestParameters;

    }

    private void checkFieldType(FileItem item, Map requestParameters) throws Exception {

        if (item.isFormField()) {

            requestParameters.put(item.getFieldName(), item.getString());

        } else {

            String fileName = processUploadedFile(item);
            requestParameters.put("imageProduto", "img/".concat(fileName));

        }

    }

    private String processUploadedFile(FileItem fileItem) throws Exception {
        Long currentTime = new Date().getTime();
        String fileName = currentTime.toString().concat("-").concat(fileItem.getName().replace(" ", ""));
        String filePath = this.getServletContext().getRealPath("img").concat(File.separator).concat(fileName);
        fileItem.write(new File(filePath));
        return fileName;
    }


}
