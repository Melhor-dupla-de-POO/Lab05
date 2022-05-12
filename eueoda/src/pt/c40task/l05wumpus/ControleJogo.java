package pt.c40task.l05wumpus;

import java.util.Scanner;

public class ControleJogo {
    private final Scanner keyboard = new Scanner(System.in);
    private Caverna caverna;
    private Heroi jogador;

    public ControleJogo(String[][] arquivoCaverna){
    	System.out.print("Entre com um nome: ");
        String nome = keyboard.nextLine();
        caverna = MontadorCaverna.montaCaverna(arquivoCaverna, nome);
        if (caverna == null) System.out.println("Caverna inválida!");
    }

    public void run() {
        Acoes comando = Acoes.INVALIDO;
        caverna.conecta(jogador);
        System.out.println(caverna);
        System.out.println("Player: " + jogador.getNome());
        while (Heroi.getVivo()) {
            comando = leAcoes();
            if (comando == Acoes.SAI) {
            	System.out.println("Volte sempre!");
            	break;
            }
            jogador.agir(comando);

            System.out.println(caverna);
            System.out.println("Player: " + jogador.getNome());
            // System.out.println(caverna.getSala(jogador.getPos()));
            System.out.println("Score: " + jogador.getScore());
        }
    }

    private Acoes leAcoes() {
        String input = keyboard.nextLine();
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