package br.com.gppn.servlet;

import dao.EmpresaDao;
import model.Empresa;
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

@WebServlet("/CadastrarEmpresa")

public class CadastrarEmpresaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> parameters = uploadImage(req);

        String empresaId = parameters.get("id");
        String nomeEmpresa = parameters.get("empresa-name");
        String endereco = parameters.get("endereco-empresa");
        String telefone = parameters.get("telefone-empresa");
        String servico = parameters.get("servico-empresa");
        String email = parameters.get("email-empresa");
        String imagemEmpresa = parameters.get("image-empresa");

        System.out.println(empresaId);
        System.out.println(nomeEmpresa);
        System.out.println(endereco);
        System.out.println(telefone);
        System.out.println(servico);
        System.out.println(email);
        System.out.println(imagemEmpresa);



        EmpresaDao empresaDao = new EmpresaDao();
        Empresa empresa = new Empresa(empresaId, nomeEmpresa, endereco, telefone, servico, email, imagemEmpresa);
        if (empresaId == null || empresaId.isBlank()) {
            empresaDao.createEmpresa(empresa);
        } else {
            empresaDao.updateEmpresa(empresa);
        }
        resp.sendRedirect("/ExibirEmpresas");


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

                requestParameters.put("image-empresa", "img/default-empresa.jpg");

            }

        }

        return requestParameters;

    }

    private void checkFieldType(FileItem item, Map requestParameters) throws Exception {

        if (item.isFormField()) {

            requestParameters.put(item.getFieldName(), item.getString());

        } else {

            String fileName = processUploadedFile(item);
            requestParameters.put("image-empresa", "img/".concat(fileName));

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
