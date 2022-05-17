package pt.c40task.l05wumpus;

public class Heroi extends Componente {
	private String nome;
	private int qt_flecha, score;
	private boolean vivo, equipada, ouro;
	
	Heroi(int x, int y, Caverna cave, String nome) {
		super(x, y, 'P', cave, 0);
		equipada = false; 
		qt_flecha = 1; 
		this.nome = nome; 
		this.score = 0; 
		this.ouro = false; 
		this.vivo = true;
	}
	
	public void agir(Acoes comando) {
		if (comando == Acoes.EQUIPA) {
			if(this.equipada) {
				System.out.println("A flecha já está equipada");
			}
			else if(qt_flecha == 0) {
				System.out.println("Você não possui flechas para equipar");
			}
			else {
				this.equipada = true;
				this.qt_flecha--;
				System.out.println("Você equipou a flecha!");
			}
		}
		else {
			cave.moveHeroi(comando);
			if (this.getEquipada()) {
				
				// Como a flecha eh sempre disparada na proxima sala
				// Nos desequipamos ela e atualizamos o score
				this.setEquipada(false);
				this.score -= 100;
			}
		}
	}
	
	// Getters e Setters
	public String getNome() {
		return nome;
	}
	public int getScore() {
		return score;
	}
	public void somaScore(int val) {
		score += val;
	}
	public boolean getVivo() {
		return this.vivo;
	}
	public void morre() {
		this.vivo = false;
		this.somaScore(-1000);
	}
	public boolean getOuro() {
		return this.ouro;
	}
	public void setOuro(boolean ouro) {
		this.ouro = ouro;
	}
	public boolean getEquipada() {
		return this.equipada;
	}
	public void setEquipada(boolean equipada) {
		this.equipada = equipada;
	}
}
