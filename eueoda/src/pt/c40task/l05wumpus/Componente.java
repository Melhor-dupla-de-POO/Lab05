package pt.c40task.l05wumpus;

// Classe base para todos os componentes do jogo
public abstract class Componente {
	private int x, y, primario;
	Caverna cave;
	private final char id;
	Componente(int x, int y, char id, Caverna cave, int primario) {
		this.x = x; this.y = y; this.id = id; this.cave = cave; this.primario = primario;
	}
	
	// Getters e Setters
	public int[] getPos() {
		int[] pos = new int[2];
		pos[0] = x; pos[1] = y;
		return pos;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setPos(int x, int y) {
		setX(x); setY(y);
	}
	public void setPos(int[] arr) {
		setPos(arr[0], arr[1]);
	}
	public int getPrimario() {
		return this.primario;
	}
	public char getId() {
		return this.id;
	}
}
