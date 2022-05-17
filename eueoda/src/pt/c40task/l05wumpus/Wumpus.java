package pt.c40task.l05wumpus;

public class Wumpus extends Componente {
	Wumpus(int x, int y, Caverna cave) {
		super(x, y, 'W', cave, 1);
		this.criaSecundario();
	}
	
	public void criaSecundario() {
		// Cria fedor nas celulas adjacentes
		
		int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
		int[] cur = this.getPos();
		for(int i = 0; i < 4; i++) {
			int x = cur[0] + dx[i], y = cur[1] + dy[i];
			Fedor s = new Fedor(x, y, cave);
			cave.conecta(s);
		}
	}
	
	public String toString() {
		return new String();
	}
}
