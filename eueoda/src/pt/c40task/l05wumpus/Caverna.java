package pt.c40task.l05wumpus;

public class Caverna {
	private Sala[][] salas;
	Heroi hero;
	
	Caverna() {
		salas = new Sala[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) salas[i][j] = new Sala();
		}
		salas[0][0].setVisitado(true);
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
	
	void conectaHeroi(Heroi hero) {
		int[] pos = hero.getPos();
		
		// Retornar algum erro
		if (!celulaValida(pos)) return;
		
		this.salas[pos[0]][pos[1]].adicionaHeroi(hero);
	}
	
	void invalido() {
		System.out.println("Ação invalida!!");
	}
	
	void moveHeroi(Acoes acao) {
		int[] pos1 = this.hero.getPos(), pos2 = this.hero.getPos();
		switch (acao) {
			case CIMA:
				pos2[0]--;
				break;
			case BAIXO:
				pos2[0]++;
				break;
			case DIR:
				pos2[1]++;
				break;
			case ESQ:
				pos2[1]--;
				break;
			case INVALIDO:
				invalido();
				break;
			case CAPTURA:
				this.salas[pos1[0]][pos1[1]].coleta(this.hero);
				break;
			default:
				break;
		}
		if (acao == Acoes.CIMA || acao == Acoes.BAIXO 
				|| acao == Acoes.DIR || acao == Acoes.ESQ) {
			if (!celulaValida(pos2)) {
				invalido();
			}
			else {
				this.salas[pos1[0]][pos1[1]].retira(this.hero);
				this.salas[pos2[0]][pos2[1]].setVisitado(true);
				this.salas[pos2[0]][pos2[1]].adicionaHeroi(this.hero);
				this.hero.setPos(pos2);
				this.hero.somaScore(-15);
			}
		}
	}
	
	public String toString() {
		int[] pos = this.hero.getPos();
		String ans = this.salas[pos[0]][pos[1]].frase();
		for (Sala[] linha : salas) {
			for (Sala s : linha) {
				ans += s.toString();
				ans += " ";
			}
			ans += "\n";
		}
		return ans;
	}
}
