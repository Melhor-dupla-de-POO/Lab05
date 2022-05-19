package pt.c40task.l05wumpus;

public class Caverna {
	private Sala[][] salas;
	Heroi hero;
	
	Caverna() {
		salas = new Sala[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) salas[i][j] = new Sala();
		}
		
		// A sala inicial comeca visitada
		salas[0][0].setVisitado(true);
	}
	
	public boolean celulaValida(int[] pos) {
		boolean ok = true;
		for (int i = 0; i < 2; i++)
			ok &= pos[i] >= 0 && pos[i] < 4;
		return ok;
	}
	
	boolean conecta(Componente nova) {
		
		int[] pos = nova.getPos();
		
		if (!celulaValida(pos)) return false;
		
		return this.salas[pos[0]][pos[1]].adiciona(nova);
	}
	
	void conectaHeroi(Heroi hero) {
		int[] pos = hero.getPos();
		
		if (!celulaValida(pos)) return;
		
		this.salas[pos[0]][pos[1]].adicionaHeroi(hero);
	}
	
	void invalido() {
		System.out.println("Ação invalida!");
	}
	
	void moveHeroi(Acoes acao) {
		int[] pos_inicial = this.hero.getPos(), pos_final = this.hero.getPos();
		switch (acao) {
			case CIMA:
				pos_final[0]--;
				break;
			case BAIXO:
				pos_final[0]++;
				break;
			case DIR:
				pos_final[1]++;
				break;
			case ESQ:
				pos_final[1]--;
				break;
			case INVALIDO:
				invalido();
				break;
			case CAPTURA:
				
				// Coleta o ouro
				this.salas[pos_inicial[0]][pos_inicial[1]].coleta(this.hero);
				break;
			default:
				break;
		}
		if (acao == Acoes.CIMA || acao == Acoes.BAIXO 
				|| acao == Acoes.DIR || acao == Acoes.ESQ) {
			if (!celulaValida(pos_final)) {
				invalido();
			}
			else {
				
				// Atualiza as salas, o heroi e o score
				this.salas[pos_inicial[0]][pos_inicial[1]].retira(this.hero);
				this.salas[pos_final[0]][pos_final[1]].setVisitado(true);
				this.salas[pos_final[0]][pos_final[1]].adicionaHeroi(this.hero);
				this.hero.setPos(pos_final);
				this.hero.somaScore(-15);
			}
		}
	}
	
	public String toString() {
		
		// Cada componente tem uma frase associada
		// Esse metodo devolve a frase associada ao componente 
		// mais importante na sala do heroi
		// Tambem devolve a matriz da caverna
		int[] pos = this.hero.getPos();
		String ans = this.salas[pos[0]][pos[1]].frase();
		for (Sala[] linha : this.salas) {
			for (Sala s : linha) {
				ans += s.toString();
				ans += " ";
			}
			ans += "\n";
		}
		return ans;
	}
	
	public char[][] apresenta() {
		char[][] mat = new char[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				mat[i][j] = this.salas[i][j].toString().charAt(0);
			}
		}
		return mat;
	}
}
