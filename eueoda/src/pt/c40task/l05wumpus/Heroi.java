package pt.c40task.l05wumpus;

public class Heroi extends Componente {
	static private String nome;
	static private int qt_flecha, score;
	static private boolean vivo, equipada, ouro;
	Heroi(int x, int y, Caverna cave, String nome) {
		super(x, y, 'P', cave, 0);
		equipada = false; 
		qt_flecha = 1; 
		Heroi.nome = nome; 
		Heroi.score = 0; 
		Heroi.ouro = false; 
		Heroi.vivo = true;
	}
	public String getNome() {
		return nome;
	}
	public void agir(Acoes comando) {
		if(comando == Acoes.EQUIPA) {
			if(equipada) {
				System.out.println("A flecha já está equipada");
			}
			else if(qt_flecha == 0) {
				System.out.println("Você não possui flechas para equipar");
			}
			else {
				score -= 100;
				equipada = true;
				qt_flecha--;
			}
		}
		else cave.moveHeroi(comando);
	}
	public void somaScore(int val) {
		score += val;
	}
	public int getScore() {
		return score;
	}
	public static void setVivo(boolean vivo) {
		Heroi.vivo = vivo;
	}
	public static boolean getVivo() {
		return Heroi.vivo;
	}
	public static boolean getOuro() {
		return Heroi.ouro;
	}
	public static void setOuro(boolean ouro) {
		Heroi.ouro = ouro;
	}
	public static boolean getEquipada() {
		return Heroi.equipada;
	}
	public static void setEquipada(boolean equipada) {
		Heroi.equipada = equipada;
	}
}
