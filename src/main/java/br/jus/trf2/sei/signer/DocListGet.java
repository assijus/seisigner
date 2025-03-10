package br.jus.trf2.sei.signer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.jus.trf2.assijus.system.api.AssijusSystemContext;
import br.jus.trf2.assijus.system.api.IAssijusSystem.Document;
import br.jus.trf2.assijus.system.api.IAssijusSystem.IDocListGet;

public class DocListGet implements IDocListGet {

	@Override
	public void run(Request req, Response resp, AssijusSystemContext ctx) throws Exception {

		// Parse request
		String cpf = req.cpf;

		// Setup json array
		List<Document> list = new ArrayList<>();

		// Get documents from Oracle
		//
		// Detalhes Parâmetros:
		// NUM_CPF : Número do CPF sem caracteres especiais.
		//
		// Detalhes dos campos:
		// IdSecao : Identificador único da Seção.
		// IdTextoWeb : Identificador único do Documento.
		// CodDocumento : Número do processo a qual o documento está
		// vinculado.
		// ArquivoCompactado: Vetor de bytes do arquivo PDF compactado.
		// TipoDeTexto : Tipo do texto. (Relatório, Voto, etc.)
		// IncidenteDocumento: Descrição do incidente que se refere o
		// documento.
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			conn = Utils.getConnection();
			pstmt = conn.prepareStatement(Utils.getSQL("list"));
			pstmt.setString(1, cpf);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Document doc = new Document();

				Id id = new Id(rset.getLong("id_assinatura"), rset.getLong("id_documento"));
				doc.id = id.toString();
				doc.code = rset.getString("id_documento");
				doc.descr = rset.getString("nome_arvore") != null ? rset.getString("nome_arvore") : "";
				doc.kind = rset.getString("descr_serie");
				doc.origin = "SEI";
				doc.secret = rset.getString("segredo");
				list.add(doc);

				// Acrescenta essa informação na tabela para permitir a
				// posterior visualização.
				Utils.store(cpf + "-" + id.toString(), new byte[] { 1 });
			}
		} finally {
			if (rset != null)
				rset.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		resp.list = list;
	}

	@Override
	public String getContext() {
		return "listar documentos";
	}

}
