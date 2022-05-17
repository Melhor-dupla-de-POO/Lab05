package pt.c40task.l05wumpus;

public class Buraco extends Componente {
	Buraco(int x, int y, Caverna cave) {
		super(x, y, 'B', cave, 1);
		this.criaSecundario();
	}
	public void criaSecundario() {
		int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
		int[] cur = this.getPos();
		for(int i = 0; i < 4; i++) {
			int x = cur[0] + dx[i], y = cur[1] + dy[i];
			Brisa s = new Brisa(x, y, cave);
			cave.conecta(s);
		}
	}
}
