package br.jus.trf2.sei.signer;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crivano.swaggerservlet.SwaggerServlet;

import br.jus.trf2.assijus.system.api.AssijusSystemContext;
import br.jus.trf2.assijus.system.api.IAssijusSystem.IDocIdPdfGet;

public class DocIdPdfGet implements IDocIdPdfGet {

	@Override
	public void run(Request req, Response resp, AssijusSystemContext ctx) throws Exception {
		Id id = new Id(req.id);
		String cpf = req.cpf;

		ContentData cd = retrievePdf(id, cpf);

		resp.inputstream = new ByteArrayInputStream(cd.ab);
		SwaggerServlet.getHttpServletResponse().addHeader("Doc-Secret", cd.secret);
	}

	protected static ContentData retrievePdf(Id id, String cpf) throws Exception, SQLException {
		ContentData cd = new ContentData();

		// Chama a procedure que recupera os dados do PDF para viabilizar a
		// assinatura
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			conn = Utils.getConnection();
			pstmt = conn.prepareStatement(Utils.getSQL("content"));
			pstmt.setLong(1, id.getIdDocumento());
			rset = pstmt.executeQuery();

			if (!rset.next())
				throw new Exception("Conteúdo não encontrado");

			cd.ab = rset.getString("conteudo") != null ? rset.getString("conteudo").getBytes(StandardCharsets.ISO_8859_1)
					: null;
			cd.contentType = "text/html; charset=utf-8";
			cd.sha256 = rset.getString("sha256");
			cd.secret = rset.getString("segredo");
		} finally {
			if (rset != null)
				rset.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		if (cd.ab == null)
			throw new Exception("Não foi possível obter o conteúdo do documento.");

		return cd;
	}

	@Override
	public String getContext() {
		return "visualizar documento";
	}
}
