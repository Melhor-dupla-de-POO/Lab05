package pt.c40task.l05wumpus;

public class Fedor extends Componente {
	Fedor(int x, int y, Caverna cave) {
		super(x, y, 'f', cave, 0);
	}
	public String ToString() {
		return "Você sente um fedor insuportável";
	}
}
