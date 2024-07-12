package br.jus.trf2.sei.signer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import org.apache.commons.codec.binary.Hex;

import br.jus.trf2.assijus.system.api.AssijusSystemContext;
import br.jus.trf2.assijus.system.api.IAssijusSystem.IDocIdHashGet;

public class DocIdHashGet implements IDocIdHashGet {
	@Override
	public void run(Request req, Response resp, AssijusSystemContext ctx) throws Exception {
		Id id = new Id(req.id);
		String cpf = req.cpf;

		ContentData cd = DocIdPdfGet.retrievePdf(id, cpf);
		System.out.println("Content");
		System.out.println(Base64.getEncoder().encodeToString(cd.ab));

		// Produce response
		resp.sha1 = calcSha1(cd.ab);
		resp.sha256 = calcSha256(cd.ab);

		resp.policy = "AD-RB";
		resp.secret = cd.secret;

		byte[] decodedHex = Hex.decodeHex(cd.sha256);
//		resp.sha256 = decodedHex;
		if (!Arrays.equals(resp.sha256, decodedHex)) {
			System.out.println("MySQL: " + cd.sha256);
			System.out.println("Calcu: " + Hex.encodeHexString(resp.sha256, true));
			throw new Exception("sha256 diferente");
		}
	}

	public static byte[] calcSha1(byte[] content) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();
		md.update(content);
		byte[] output = md.digest();
		return output;
	}

	public static byte[] calcSha256(byte[] content) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.reset();
		md.update(content);
		byte[] output = md.digest();
		return output;
	}

	@Override
	public String getContext() {
		return "obter o hash";
	}
}
