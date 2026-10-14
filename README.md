# VitalSênior — Monitor de Saúde Acessível

Um aplicativo simples, acessível e intuitivo projetado para idosos realizarem o monitoramento diário de saúde de forma autônoma e segura,
desenvolvido com foco na experiência do usuário idoso (UX/UI inclusiva), oferecendo uma interface limpa, de fácil navegação e adaptada para facilitar a rotina de cuidados.
O sistema permite o registro diário de Glicose e Pressão Arterial (dividido em duas medições diárias: manhã e noite). Além do registro, o sistema conta com um motor de regras inteligente que:
Dispara alertas visuais instantâneos no dashboard caso os valores estejam fora dos limites saudáveis.
Envia notificações automáticas (via Spring Mail) para cuidadores ou médicos responsáveis em caso de valores críticos ou esquecimento do horário da medição.
Arquitetura e Stack Tecnológica:
O projeto foi construído seguindo as melhores práticas de mercado e padrões de arquitetura moderna (RESTful API e SPA).
Backend:
Linguagem & Framework: Java 17+, Spring Boot 3
Segurança & Autenticação: Spring Security (JWT)
Persistência de Dados: Spring Data JPA, Hibernate
Validação: Spring Validation
Banco de Dados: MSQL
Documentação da API: OpenAPI / Swagger
Frontend: 
Framework: Angular 17+
Arquitetura & Reatividade: Standalone Components, Angular Signals
Estilização & Acessibilidade: Angular Material / Tailwind CSS (com suporte a alto contraste e fontes grandes para maior legibilidade)
Node.js e npm (para o Angular)
Maven 
