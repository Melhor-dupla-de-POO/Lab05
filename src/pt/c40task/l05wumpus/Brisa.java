package pt.c40task.l05wumpus;

public class Brisa extends Componente {
	Brisa(int x, int y, Caverna cave) {
		super(x, y, 'b', cave, 0);
	}
	public String toString() {
		return "Você sente uma leve brisa\n";
	}
}
