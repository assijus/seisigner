package br.jus.trf2.sei.signer;

public class Id {
	long idAssinatura;
	long idDocumento;

	public Id(String id) {
		String[] split = id.split("_");
		this.idAssinatura = Long.valueOf(split[0]);
		this.idDocumento = Long.valueOf(split[1]);
	}

	public Id(long idAssinatura, long idDocumento) {
		this.idAssinatura = idAssinatura;
		this.idDocumento = idDocumento;
	}

	public String toString() {
		return "" + getIdAssinatura() + "_" + getIdDocumento();
	}

	public long getIdAssinatura() {
		return idAssinatura;
	}

	public long getIdDocumento() {
		return idDocumento;
	}
}
