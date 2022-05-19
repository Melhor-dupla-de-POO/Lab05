package pt.c40task.l05wumpus;

public class Ouro extends Componente {
	Ouro(int x, int y, Caverna cave) {
		super(x, y, 'O', cave, 1);
	}
	public String toString() {
		return "Você encontrou o Ouro\n";
	}
}
