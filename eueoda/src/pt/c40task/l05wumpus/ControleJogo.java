package pt.c40task.l05wumpus;

import java.util.Scanner;

public class ControleJogo {
    private final Scanner keyboard = new Scanner(System.in);
    private Caverna caverna;
    private Heroi jogador;

    public ControleJogo(String[][] arquivoCaverna) {
    	System.out.print("Entre com um nome: ");
        String nome = keyboard.nextLine();
        caverna = MontadorCaverna.montaCaverna(arquivoCaverna, nome);
        if (caverna == null) System.out.println("Caverna inválida!");
        this.jogador = this.caverna.hero;
    }

    void print() {
    	// Imprime o estado atual do jogo com as frases personalizadas :)
    	
    	System.out.println(caverna);
        System.out.println("Player: " + jogador.getNome());
        System.out.println("Score: " + jogador.getScore());
    }
    
    public void run() {
        Acoes comando;
        print();
        
        // O jogo roda enquanto o jogador esta vivo e nao sai do jogo
        while (this.jogador.getVivo()) {
        	comando = leAcoes();
            if (comando == Acoes.SAI) {
            	if (this.jogador.getOuro()) this.jogador.somaScore(1000);
            	break;
            }
            this.jogador.agir(comando);
            print();
        }
        
        // Mensagens finais
        if (this.jogador.getScore() > 0 && this.jogador.getVivo() && jogador.getOuro())
    		System.out.println("Você ganhou!");
    	else 
    		System.out.println("Você perdeu :(");
    }

    private Acoes leAcoes() {
    	// Traduz o input para o enum
    	
        String input = keyboard.nextLine();
        if (input.length() > 1) return Acoes.INVALIDO;
        char c = input.charAt(0);
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
}