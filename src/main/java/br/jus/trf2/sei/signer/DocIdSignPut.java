package br.jus.trf2.sei.signer;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Hex;

import br.jus.trf2.assijus.system.api.AssijusSystemContext;
import br.jus.trf2.assijus.system.api.IAssijusSystem.IDocIdSignPut;
import br.jus.trf2.sei.assinador.ArrayOfIdDocumentoAssinado;
import br.jus.trf2.sei.assinador.ArrayOfTamanhoAssinatura;
import br.jus.trf2.sei.assinador.AssinadorPortType;
import br.jus.trf2.sei.assinador.AssinadorService;

public class DocIdSignPut implements IDocIdSignPut {

	@Override
	public void run(Request req, Response resp, AssijusSystemContext ctx) throws Exception {
		Id id = new Id(req.id);
		enviarAssinatura(Long.toString(id.getIdAssinatura()), Long.toString(id.getIdDocumento()), req.envelope);
		resp.status = "OK";
	}

	@Override
	public String getContext() {
		return "salvar assinatura";
	}

	public static final String VERSAO = "1.1";

	public void enviarAssinatura(String idAssinatura, String idDocumento, byte[] envelope) throws Exception {
		AssinadorService service = new AssinadorService();
		AssinadorPortType assinadorService = service.getAssinadorPortService();

		ArrayOfIdDocumentoAssinado arrIdsDocumentosAssinados = new ArrayOfIdDocumentoAssinado();
		arrIdsDocumentosAssinados.getIdDocumentoAssinado().add(idDocumento);

		byte[] buffer = new byte[4096];
		ByteArrayOutputStream destino = new ByteArrayOutputStream();
		ZipOutputStream saida = new ZipOutputStream(new BufferedOutputStream(destino));
		ByteArrayInputStream in = new ByteArrayInputStream(envelope);
		saida.putNextEntry(new ZipEntry(idDocumento));
		int bytesLidos = 0;
		while ((bytesLidos = in.read(buffer)) != -1) {
			saida.write(buffer, 0, bytesLidos);
		}
		saida.closeEntry();
		in.close();
		in = null;
		saida.close();
		byte[] pacoteAssinaturas = destino.toByteArray();

		String base64PacoteAssinaturas = Base64.getEncoder().encodeToString(pacoteAssinaturas);

		String hashPacoteAssinaturas = Hex.encodeHexString(Utils.calcSha256(pacoteAssinaturas));

		ArrayOfTamanhoAssinatura arrTamanhosAssinaturas = new ArrayOfTamanhoAssinatura();
		arrTamanhosAssinaturas.getTamanhoAssinatura().add(envelope.length);

		String arrIdsAssinaturas = idAssinatura;

		assinadorService.enviarAssinaturasDocumentos(VERSAO, arrIdsDocumentosAssinados, base64PacoteAssinaturas,
				hashPacoteAssinaturas, arrTamanhosAssinaturas, arrIdsAssinaturas);
	}

}
