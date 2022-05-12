package pt.c40task.l05wumpus;

public class Caverna {
	private Sala[][] salas;
	Heroi hero;
	
	Caverna() {
		salas = new Sala[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) salas[i][j] = new Sala();
		}
	}
	
	public boolean celulaValida(int[] pos) {
		boolean ok = true;
		for (int i = 0; i < 2; i++)
			ok &= pos[i] >= 0 && pos[i] < 4;
		return ok;
	}
	
	void conecta(Componente nova) {
		
		int[] pos = nova.getPos();
		
		// Retornar algum erro
		if (!celulaValida(pos)) return;
		
		this.salas[pos[0]][pos[1]].adiciona(nova);
	}
	
	void invalido() {
		System.out.println("Ação invalida!!");
	}
	
	void moveHeroi(Acoes acao) {
		int[] pos = hero.getPos();
		switch (acao) {
			case CIMA:
				pos[0]--;
				if (!celulaValida(pos)) {
					invalido();
					break;
				}
				this.salas[pos[0] + 1][pos[1]].retira(hero);
				this.salas[pos[0]][pos[1]].adicionaHeroi(hero);
				break;
			case BAIXO:
				pos[0]++;
				if (!celulaValida(pos)) {
					invalido();
					break;
				}
				this.salas[pos[0] - 1][pos[1]].retira(hero);
				this.salas[pos[0]][pos[1]].adicionaHeroi(hero);
				break;
			case DIR:
				pos[1]++;
				if (!celulaValida(pos)) {
					invalido();
					break;
				}
				this.salas[pos[0]][pos[1] - 1].retira(hero);
				this.salas[pos[0]][pos[1]].adicionaHeroi(hero);
				break;
			case ESQ:
				pos[1]--;
				if (!celulaValida(pos)) {
					invalido();
					break;
				}
				this.salas[pos[0]][pos[1] + 1].retira(hero);
				this.salas[pos[0]][pos[1]].adicionaHeroi(hero);
				break;
			case INVALIDO:
				invalido();
				break;
			case CAPTURA:
				this.salas[pos[0]][pos[1]].coleta();
				break;
			default:
				break;
		}
	}
}
