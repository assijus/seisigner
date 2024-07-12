package br.jus.trf2.sei;

import static org.junit.Assert.assertEquals;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Hex;
import org.junit.BeforeClass;
import org.junit.Test;

import br.jus.trf2.sei.assinador.ArrayOfIdDocumentoAssinado;
import br.jus.trf2.sei.assinador.ArrayOfTamanhoAssinatura;
import br.jus.trf2.sei.assinador.AssinadorPortType;
import br.jus.trf2.sei.assinador.AssinadorService;
import br.jus.trf2.sei.signer.Utils;

/**
 * Unit test for simple App.
 */
public class AssinadorTest {
	static AssinadorPortType assinadorService;
	static final String VERSAO = "1.1";
	static final String ID_ASSINATURA = "971";
	static final String ID_DOCUMENTO = "3229";
	static final byte[] ASSINATURA_FAKE = "TESTE".getBytes();
	static final byte[] CONTENT = "TESTE".getBytes();

	@BeforeClass
	public static void setup() {
		AssinadorService service = new AssinadorService();
		assinadorService = service.getAssinadorPortService();
	}

	@Test
	public void obterDocumentoAssinatura() throws NoSuchAlgorithmException {
		assertEquals(Base64.getEncoder().encodeToString(Utils.calcSha256(CONTENT)),
				assinadorService.obterDocumentoAssinatura(VERSAO, ID_ASSINATURA, ID_DOCUMENTO));
	}

	@Test
	public void enviarAssinaturasDocumentos() throws Exception {
		ArrayOfIdDocumentoAssinado arrIdsDocumentosAssinados = new ArrayOfIdDocumentoAssinado();
		arrIdsDocumentosAssinados.getIdDocumentoAssinado().add(ID_DOCUMENTO);

		byte[] buffer = new byte[4096];
		ByteArrayOutputStream destino = new ByteArrayOutputStream();
		ZipOutputStream saida = new ZipOutputStream(new BufferedOutputStream(destino));
		ByteArrayInputStream in = new ByteArrayInputStream(ASSINATURA_FAKE);
		saida.putNextEntry(new ZipEntry(ID_DOCUMENTO));
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
		arrTamanhosAssinaturas.getTamanhoAssinatura().add(ASSINATURA_FAKE.length);

		String arrIdsAssinaturas = ID_ASSINATURA;

		assertEquals("nm_estu", assinadorService.enviarAssinaturasDocumentos(VERSAO, arrIdsDocumentosAssinados,
				base64PacoteAssinaturas, hashPacoteAssinaturas, arrTamanhosAssinaturas, arrIdsAssinaturas));
	}

}
