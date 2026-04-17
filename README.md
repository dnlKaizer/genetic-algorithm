# Algoritmo Genético para o Problema das N-Rainhas

Projeto Java simples para resolver o problema das N-rainhas utilizando **Algoritmo Genético (AG)**.

## Objetivo

Encontrar uma disposição de N rainhas em um tabuleiro N x N de forma que o número de conflitos entre rainhas seja minimizado (idealmente, zero conflitos).

## Representação da solução

Cada indivíduo representa um tabuleiro por meio de um vetor de genes:

- Índice do vetor = coluna do tabuleiro
- Valor armazenado = linha onde a rainha está naquela coluna

Exemplo para N = 4:

- genes = {2, 0, 3, 1}

Isso significa:

- Coluna 0 -> linha 2
- Coluna 1 -> linha 0
- Coluna 2 -> linha 3
- Coluna 3 -> linha 1

## Estratégia do Algoritmo Genético

O ciclo evolutivo implementado segue estas etapas:

1. Inicialização de uma população aleatória.
2. Recombinação (crossover de 1 ponto) para gerar filhos.
3. Mutação para gerar indivíduos mutantes.
4. Junção de população atual + filhos + mutantes.
5. Elitismo para preservar os melhores indivíduos.
6. Seleção por roleta para completar a nova população.
7. Repetição por um número fixo de gerações.