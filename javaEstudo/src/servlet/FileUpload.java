package servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import dao.DaoUser;
import entidades.Usuario;


@WebServlet("/pages/fileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private DaoUser daoUser = new DaoUser();
	
    public FileUpload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String acao = request.getParameter("acao");
			
			if(acao.equalsIgnoreCase("carregar")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("upload.jsp");
				request.setAttribute("listaUserImagem", daoUser.getUsuarios());
				dispatcher.forward(request, response);
				
			}else if(acao.equalsIgnoreCase("download")) {
				String iduser = request.getParameter("iduser");
				Usuario imagem = daoUser.buscaImagem(iduser);
				if(imagem != null) {
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo."+ imagem.getTipoFile());
					
					//Pegando apenas a imagem pura
					String imagemPura = imagem.getImagem().split(",")[1];
					
					//Convertendo base64 em bytes
					byte [] imageBytes = new Base64().decodeBase64(imagemPura);
					
					//Coloca os bytes em um objeto de entrada para processar
					InputStream is = new ByteArrayInputStream(imageBytes);
					
					//Inicio  - Escrever dados da resposta
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					
					while( (read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					
					os.flush();
					os.close();
					
					//Final  - Escrever dados da resposta		
					
				}
			}
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// usar variavel fileUpload pra salvar no banco de dados
			String fileUpload = request.getParameter("fileUpload");
			// neste momento faz o insert no banco de dados

			daoUser.gravarImagem(fileUpload);

			response.getWriter().write("Upload realizado com sucesso");
	
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Erro fatal ao realizar upload");
		}
		
	
	}

}
