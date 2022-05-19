# Lab05

## Destaques
[Arquivos java](src/pt/c40task/l05wumpus/)

- Alunos:
  - Bernardo Panka Archegas 246970
  - Luiz Henrique Yuji Delgado Oda 247255
```java
Sala() {
	comps = new ArrayList<Componente>();
	visitado = false;
}
```
Neste recorte, temos o construtor da classe Sala, que possuí um ArrayList de Componente. Com ele, podemos adicionar facilmente outros tipos de componentes fazendo alterações mínimas no código da classe Sala. Além disso, aproveitamos o conceito de polimofismo utilizando a classe abstrata Componente como o tipo do ArrayList.

```java
void conecta(Componente nova) {
		
	int[] pos = nova.getPos();
	
	// Retornar algum erro
	if (!celulaValida(pos)) return;
	
	this.salas[pos[0]][pos[1]].adiciona(nova);
}
```
Já nesse código é retratado um método da classe Caverna, onde queremos inserir uma componente nova dentro de sua sala. Podemos ver neste trecho que a função só depende da classe abstrata Componente e de métodos declarados nela para inserir na sala correta. Com isso, podemos ver que esta função não depende do tipo da componente, facilitando a inserção de novas subclasses sem que ocorra mudança nestre trecho de código. Além disso, podemos ver que uma componente guarda dentro de si sua sala, mostrando que delegamos a cada objeto o máximo de atributos possíveis, especializando as classes ao máximo.

```java
public enum Acoes {
	CIMA, BAIXO, DIR, ESQ, EQUIPA, CAPTURA, SAI, INVALIDO; 
}
```
Por último, temos um trecho do código onde utilizamos enum para tratar as possíveis ações. Com ele, podemos processar as ações diretamente por meio das palavras-chave "CIMA", "BAIXO", "DIR", "ESQ", "EQUIPA", "CAPTURA", "SAI", "INVALIDO". Isso resulta em um aumento direto na legibilidade do código.
