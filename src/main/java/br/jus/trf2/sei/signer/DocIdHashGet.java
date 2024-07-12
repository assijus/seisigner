package br.jus.trf2.sei.signer;

import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;

import br.jus.trf2.assijus.system.api.AssijusSystemContext;
import br.jus.trf2.assijus.system.api.IAssijusSystem.IDocIdHashGet;

public class DocIdHashGet implements IDocIdHashGet {
	@Override
	public void run(Request req, Response resp, AssijusSystemContext ctx) throws Exception {
		Id id = new Id(req.id);
		String cpf = req.cpf;

		ContentData cd = DocIdPdfGet.retrievePdf(id, cpf);
//		System.out.println("Content");
//		System.out.println(Base64.getEncoder().encodeToString(cd.ab));

		// Produce response
		resp.sha1 = Utils.calcSha1(cd.ab);
		resp.sha256 = Utils.calcSha256(cd.ab);

		resp.policy = "AD-RB";
		resp.secret = cd.secret;

		byte[] decodedHex = Hex.decodeHex(cd.sha256);
//		resp.sha256 = decodedHex;
		if (!Arrays.equals(resp.sha256, decodedHex)) {
			System.out.println("MySQL: " + cd.sha256);
			System.out.println("Calc:  " + Hex.encodeHexString(resp.sha256, true));
			throw new Exception("sha256 diferente");
		}
	}


	@Override
	public String getContext() {
		return "obter o hash";
	}
}
