# QSOFT-Projeto — Quality Assessment for Service-Based Software  
Mestrado em Engenharia Informática, 2024/2025

## Contexto

Este repositório contém todos os artefactos do projeto prático da unidade curricular **QSOFT** (Qualidade de Software). O trabalho é desenvolvido em duas partes:
- **Parte 1 (P1):** Análise de um projeto Java orientado a serviços, focando-se na sua qualidade para eventual reutilização.
- **Parte 2 (P2):** Avaliação de uma aplicação baseada em JHipster, mantendo o mesmo domínio e expandindo o enfoque para atributos de qualidade adicionais.

Este README é o documento central e integra toda a documentação global e individual, conforme as regras da unidade curricular.

---

## Parte 1 — Análise de Projeto Existente

### Enquadramento

1. **Contexto de Negócio:**  
   Uma equipa de engenharia de software avalia um projeto legado, potencialmente reutilizável, mas sem documentação de qualidade disponível. O objetivo é medir atributos de qualidade essenciais e guiar a decisão de reutilização.

2. **Atributos de Qualidade a Avaliar:**
   - Correção funcional
   - Manutenibilidade (código e testes)
   - Performance (sob vários cenários)
   - Segurança (vulnerabilidades e dependências)
   - Conformidade arquitetural

### Metodologia

- **Goal-Question-Metric (GQM):**  
  Definir metas, questões e métricas para cada atributo avaliado. Justificar limiares e ferramentas usadas.
- **Divisão do Trabalho:**  
  Cada elemento do grupo avalia aspectos distintos (e.g., métricas de manutenibilidade diferentes), garantindo cobertura multidimensional.
- **Comprovação de Trabalho:**  
  Toda a evolução é feita em Markdown/LaTeX, com histórico de commits visível, e nunca em formatos binários (ex: docx).  
  Relatórios individuais e globais refletem a contribuição de cada membro.

### Artefactos e Organização

- `docs/` — Relatório global (detalha aplicação, contexto, plano de medição, trabalho de equipa)
- `individual/1240485/` — Relatórios individuais, métricas calculadas manualmente, artefactos pessoais
- `test-results/`, `scripts/`, `tool-reports/` — Resultados de ferramentas, planos de teste, outputs de scripts
- `images/` — Gráficos e diagramas (sempre referenciados no texto)
- `src/` — (Opcional) Cópia do código analisado, se permitido


---

## Parte 2 — Avaliação JHipster/Expansão de Domínio

### Enquadramento

- Avaliação de uma aplicação baseada em **JHipster** (>= 8.7.3), mantendo o mesmo domínio do P1.
- O projeto P2 (monolítico, backend+frontend) serve como base para análise de atributos de qualidade ampliados.

### Novos Atributos de Qualidade e Processos

- **Acessibilidade:**  
  Avaliação com WCAG 2.2, aplicação de WAI-ARIA, e geração de statement por domínio/endereço.
- **Compatibilidade Visual:**  
  Testes de visualização em múltiplos browsers/dispositivos, usando ferramentas automatizadas.
- **Manutenibilidade:**  
  Métricas e ferramentas alinhadas com o P1, sem cálculos manuais.
- **Performance:**  
  Avaliação baseada no modelo RAIL/Core Web Vitals e backend, usando ferramentas distintas das nativas do JHipster.
- **Segurança:**  
  Scans autenticados (backend e frontend), análise de OWASP Top 10, dependências, e vulnerabilidades client-side.

### Organização e Submissão

- Todo o trabalho é mantido neste mesmo repositório, com separação clara entre P1 e P2 nos relatórios.
- Relatórios são evoluídos incrementalmente, com commits frequentes e mensagens referenciando issues/tarefas.
- **Formato:** Markdown ou LaTeX (sem docx); PDF gerado antes da submissão Moodle.
- **Estrutura típica:**
  - `docs/README.md` ou `docs/relatorio_global.md` — Relatório global P1+P2
  - `individual/1240485/` — Relatório individual P2
  - `acessibilidade/` — Statements e evidências para cada domínio/endereço
  - `tool-reports/`, `test-results/` — Outputs de ferramentas de análise
  - `images/` — Screenshots, gráficos, diagramas

---

## Critérios de Avaliação

- **Trabalho individual e de grupo**: cada aspeto do projeto é discutido em equipa, mas os commits e relatórios individuais documentam o contributo de cada membro.
- **Auditoria e transparência**: todas as alterações e contribuições documentadas no histórico do repositório.
- **Consistência**: estrutura, convenções de nomes e formatos homogéneos em todos os artefactos.

---

## Referências Importantes

- [Como escrever commits claros](https://www.freecodecamp.org/news/writing-good-commit-messages-a-practical-guide/)
- [WAI-ARIA 1.1](https://www.w3.org/TR/wai-aria-1.1/)
- [WCAG 2.2](https://www.w3.org/TR/WCAG22/)
- [OWASP Top 10:2021](https://owasp.org/Top10/)
- [JHipster](https://www.jhipster.tech/)
- [JHipster Domain Language](https://www.jhipster.tech/jdl/intro)
- [Core Web Vitals](https://web.dev/vitals/)

---

> **QSOFT — Mestrado em Engenharia Informática, ISEP 2024/2025**  
> Projeto prático (P1+P2) — Professores: Isabel Azevedo, Catarina Oliveira, Mafalda Ferreira Landeiro  
> Todos os artefactos e documentação encontram-se centralizados neste README/repositório.
