
package br.jus.trf2.sei.assinador;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "assinadorPortType", targetNamespace = "assinadorns")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AssinadorPortType {


    /**
     * Recupera os documentos associados com o agrupador
     * 
     * @param agrupador
     * @param versao
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "assinadorns/obterDocumentosAgrupador")
    @WebResult(name = "documentos", partName = "documentos")
    public String obterDocumentosAgrupador(
        @WebParam(name = "Versao", partName = "Versao")
        String versao,
        @WebParam(name = "Agrupador", partName = "Agrupador")
        String agrupador);

    /**
     * Obtencao do hash de documento para assinatura
     * 
     * @param idDocumento
     * @param idAssinatura
     * @param versao
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "assinadorns/obterDocumentoAssinatura")
    @WebResult(name = "base64DocumentoAssinatura", partName = "base64DocumentoAssinatura")
    public String obterDocumentoAssinatura(
        @WebParam(name = "Versao", partName = "Versao")
        String versao,
        @WebParam(name = "IdAssinatura", partName = "IdAssinatura")
        String idAssinatura,
        @WebParam(name = "IdDocumento", partName = "IdDocumento")
        String idDocumento);

    /**
     * Recebimento de assinaturas
     * 
     * @param hashPacoteAssinaturas
     * @param arrIdsDocumentosAssinados
     * @param arrIdsAssinaturas
     * @param versao
     * @param arrTamanhosAssinaturas
     * @param base64PacoteAssinaturas
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "assinadorns/enviarAssinaturasDocumentos")
    @WebResult(name = "retorno", partName = "retorno")
    public String enviarAssinaturasDocumentos(
        @WebParam(name = "Versao", partName = "Versao")
        String versao,
        @WebParam(name = "arrIdsDocumentosAssinados", partName = "arrIdsDocumentosAssinados")
        ArrayOfIdDocumentoAssinado arrIdsDocumentosAssinados,
        @WebParam(name = "Base64PacoteAssinaturas", partName = "Base64PacoteAssinaturas")
        String base64PacoteAssinaturas,
        @WebParam(name = "HashPacoteAssinaturas", partName = "HashPacoteAssinaturas")
        String hashPacoteAssinaturas,
        @WebParam(name = "arrTamanhosAssinaturas", partName = "arrTamanhosAssinaturas")
        ArrayOfTamanhoAssinatura arrTamanhosAssinaturas,
        @WebParam(name = "arrIdsAssinaturas", partName = "arrIdsAssinaturas")
        String arrIdsAssinaturas);

}
