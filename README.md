# 🛠️ Rede Âncora Challenge — FIAP

## Sistema de Cotações e Pedidos para Mecânicos

Este projeto foi desenvolvido como parte de um **desafio prático da FIAP em parceria com a empresa Rede Âncora**, utilizando os conceitos de **MVC (Model-View-Controller)** e banco de dados **Oracle**.

---

### 📌 Objetivo

O sistema foi criado para solucionar uma **dor real da Rede Âncora**, que desejava **melhorar o relacionamento com mecânicos e oficinas** conectadas à sua rede de mais de 800 lojas franqueadas no Brasil.

O objetivo era criar uma **ferramenta web simples**, que:

- Aproximasse os mecânicos das lojas da Rede Âncora
- Agilizasse o processo de **busca, cotação e compra** de peças
- Facilitasse a visualização de **promoções** e o histórico de pedidos

---

### 💡 Solução Proposta

Um aplicativo (desktop) construído com **Java puro (sem frameworks)**, utilizando:

- **Swing (JFrame)** para interface gráfica (View)
- **POJOs** para modelagem de entidades (Model)
- **Controllers e DAOs** para a lógica e persistência (Controller)
- **Banco de dados Oracle**, com SQL e JDBC

---

### 📀 Arquitetura MVC

```
src/
├── model/         → Entidades do sistema (User, Product, Quote, Order, etc.)
├── dao/           → Acesso a dados e conexão Oracle via JDBC
├── controller/    → Lógica de negócio e fluxo da aplicação
└── view/          → Interfaces gráficas feitas com JFrame
```

---

### 🔧 Funcionalidades Implementadas

#### 👨‍💼 Perfil Mecânico:

- Buscar peças por nome ou código do veículo
- Ver detalhes e promoções ativas
- Criar cotação de peças
- Ver cotações anteriores
- Finalizar cotação e gerar pedido
- Criar pedido direto

#### 🧑‍💼 Perfil Cliente (administrador):

- Cadastrar, editar e excluir peças
- Criar promoções
- Listar todas as promoções cadastradas

---

### 📃 Tabelas no Oracle

```sql
usuario (id, nome, email, senha, role)
produto (id, nome, marca, aplicacao, codigo_veiculo, preco)
promocao (id, id_produto, descricao, desconto_percentual, data_inicio, data_fim)
cotacao (id, id_mecanico, data, status)
item_cotacao (id_cotacao, id_produto, quantidade, preco_unitario)
pedido (id, id_mecanico, data, status)
item_pedido (id_pedido, id_produto, quantidade, preco_unitario)
```

---

### 🚀 Tecnologias Utilizadas

- Java 11+
- Swing (JFrame)
- JDBC
- Oracle Database
- SQL padrão Oracle
- UUID para geração de IDs

---

### 📈 Próximas Melhorias (sugestões)

- Implementar filtros avançados na busca de produtos
- Adicionar login seguro com hashing de senha
- Melhorar responsividade da UI
- Gerar PDF de cotação e pedido
- Dashboard com estatísticas e relatórios

---

### 👥 Equipe

Projeto desenvolvido por alunos da **FIAP** com orientação técnica e parceria com a empresa **Rede Âncora**, como parte de um **Challenge Corporativo**.

