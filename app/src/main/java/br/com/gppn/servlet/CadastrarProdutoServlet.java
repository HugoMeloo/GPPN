package br.com.gppn.servlet;

import dao.ProdutoDao;
import model.Produto;
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

        String produtoId = parameters.get("id-produto");
        String nomeProduto = parameters.get("produto-name");
        double quantidade = Double.parseDouble(parameters.get("quantidade"));
        double precoUni = Double.parseDouble(parameters.get("preco"));
        String codigoItem = parameters.get("codigoItem");
        int tipoItem = Integer.parseInt(parameters.get("tipoItem"));
        String imagemProduto = parameters.get("image-produto");

        System.out.println(nomeProduto);

        ProdutoDao produtoDao = new ProdutoDao();
        Produto produto = new Produto(produtoId, nomeProduto, quantidade, precoUni, codigoItem, tipoItem, imagemProduto);
        if (produtoId.isBlank()) {
            produtoDao.createProduto(produto);
        } else {
            produtoDao.updateProduto(produto);
        }
        resp.sendRedirect("/ExibirProdutos");


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

                requestParameters.put("image", "img/default-produto.jpg");

            }

        }

        return requestParameters;

    }

    private void checkFieldType(FileItem item, Map requestParameters) throws Exception {

        if (item.isFormField()) {

            requestParameters.put(item.getFieldName(), item.getString());

        } else {

            String fileName = processUploadedFile(item);
            requestParameters.put("image", "img/".concat(fileName));

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
