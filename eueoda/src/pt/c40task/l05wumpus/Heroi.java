package pt.c40task.l05wumpus;

public class Heroi extends Componente {
	static private String nome;
	static private int qtflecha, score;
	static private boolean vivo, equipada, ouro;
	Heroi(int x, int y, Caverna cave, String nome) {
		equipada = false; qt_flecha = 1; Heroi.nome = nome; Heroi.score = 0; Heroi.ouro = false; Heroi.vivo = true;
		super(x, y, 'P', cave, 0);
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
	public void somaScore(int val) score += val;
	public int getScore() return score;
	public void setVivo(boolean vivo) this.vivo = vivo;
	public boolean getVivo() return this.vivo;
}
