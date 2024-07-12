SELECT 
	ass.id_assinatura,
    doc.id_documento,
    doc.nome_arvore,
    s.nome descr_serie,
    dc.conteudo_assinatura,
    SHA2(dc.conteudo_assinatura, 224) segredo
FROM
    sei.assinatura ass
        LEFT JOIN
    sei.documento doc  ON ass.id_documento = doc.id_documento
        LEFT JOIN
    sei.documento_conteudo dc ON dc.id_documento = doc.id_documento
        LEFT JOIN
    sei.serie s ON s.id_serie = doc.id_serie
WHERE
    ass.cpf = ?
    AND ass.sin_ativo = 'N'