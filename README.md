# seisigner

Conector do Assijus para realizer assinaturas digitais em documentos do SEI.

## Variáveis de Ambiente

1. `assijus.systems`: incluir `seisigner`
2. `assijus.seisigner.url`: a url do seisigner, no estilo http//localhost:8080/seisigner/api/v1
3. `assijus.seisigner.password`: informar uma senha longa, um GUID aleatório, para proteger o serviço de uso indevido.
4. `seisigner.password`, deve ser preenchido com a mesma senha que for informada em `assijus.seisigner.password`
5. `seisigner.datasource.name` (default: java:/jboss/datasources/SeiDS)
6. `seisigner.datasource.url` (default: null)
7. `seisigner.datasource.username` (default: null)
8. `seisigner.datasource.password` (default: null)
9. `seisigner.datasource.schema` (default: sei)