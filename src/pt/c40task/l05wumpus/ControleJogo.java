package pt.c40task.l05wumpus;

import java.util.Scanner;

public class ControleJogo {
    private final Scanner keyboard = new Scanner(System.in);
    private Caverna caverna;
    private Heroi jogador;

    public ControleJogo(String[][] arquivoCaverna, String nome) {
        caverna = MontadorCaverna.montaCaverna(arquivoCaverna, nome);
        if (caverna == null) System.out.println("Caverna inválida!");
        else this.jogador = this.caverna.hero;
    }

    void print() {
    	// Imprime o estado atual do jogo com as frases personalizadas :)
    	
    	System.out.println(caverna);
        System.out.println("Player: " + jogador.getNome());
        System.out.println("Score: " + jogador.getScore());
    }

    public Acoes leAcoes(char in) {
    	// Traduz o input para o enum
    	
    	char c = in;
    	if (in == '@') {
    		String input = keyboard.nextLine();
    		if (input.length() > 1) return Acoes.INVALIDO;
    		c = input.charAt(0);
    	}
        Acoes acoes = Acoes.INVALIDO;
        switch (c) {
            case 'w':
                acoes = Acoes.CIMA;
                break;
            case 's':
                acoes = Acoes.BAIXO;
                break;
            case 'a':
                acoes = Acoes.ESQ;
                break;
            case 'd':
                acoes = Acoes.DIR;
                break;
            case 'k':
                acoes = Acoes.EQUIPA;
                break;
            case 'c':
                acoes = Acoes.CAPTURA;
                break;
            case 'q':
                acoes = Acoes.SAI;
                break;
        }
        return acoes;
    }
    
    public boolean valid() {
    	return this.caverna != null;
    }
    public Heroi getJogador() {
    	return this.jogador;
    }
    public Caverna getCaverna() {
    	return this.caverna;
    }
    
}