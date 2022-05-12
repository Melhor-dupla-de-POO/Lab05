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
		int[] pos1 = hero.getPos(), pos2 = pos1;
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
				this.salas[pos1[0]][pos1[1]].coleta();
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
				this.salas[pos1[0]][pos1[1]].retira(hero);
				this.salas[pos1[0]][pos1[1]].setVisitado(true);
				this.salas[pos2[0]][pos2[1]].adicionaHeroi(hero);
				hero.setPos(pos2);
			}
		}
	}
	
	public String toString() {
		String ans = new String();
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
