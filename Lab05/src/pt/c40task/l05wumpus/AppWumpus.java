package pt.c40task.l05wumpus;

import java.util.Scanner;

public class AppWumpus {

  public static void main(String[] args) {
     AppWumpus.executaJogo(
           (args.length > 0) ? args[0] : null,
           (args.length > 1) ? args[1] : null,
           (args.length > 2) ? args[2] : null);
  }
  
  public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                 String arquivoMovimentos) {
     Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
     
     String[][] cave = tk.retrieveCave();
     String movements = tk.retrieveMovements();
     String nome = "Alcebiades";
     if (movements.length() == 0) {
    	 Scanner keyboard = new Scanner(System.in);
    	 System.out.print("Entre com um nome: ");
         nome = keyboard.nextLine(); 
     }
     
     // Monta a caverna e se ela for valida, roda o jogo
     ControleJogo jogo = new ControleJogo(cave, nome);
     if (jogo.valid()) {
    	 Acoes comando;
    	 tk.writeBoard(jogo.getCaverna().apresenta(), jogo.getJogador().getScore(), 'x');
    	 if (movements.length() == 0) {
    		 jogo.print();
            
    		 // O jogo roda enquanto o jogador esta vivo e nao sai do jogo
    		 while (jogo.getJogador().getVivo() && !jogo.getJogador().getTerminou()) {
    			 comando = jogo.leAcoes('@');
    			 if (comando == Acoes.SAI) {
    				 break;
    			 }
    			 jogo.getJogador().agir(comando);
    			 jogo.print();
    			 tk.writeBoard(jogo.getCaverna().apresenta(), jogo.getJogador().getScore(), 'x');
    		 }
    	 }
    	 else {
    		 int pos = 0, tot = movements.length();
    		 
    		 jogo.print();
            
    		 // O jogo roda enquanto o jogador esta vivo e nao sai do jogo
    		 while (jogo.getJogador().getVivo() && pos < tot && !jogo.getJogador().getTerminou()) {
    			 comando = jogo.leAcoes(movements.charAt(pos++));
    			 if (comando == Acoes.SAI) {
    				 break;
    			 }
    			 jogo.getJogador().agir(comando);
    			 jogo.print();
    			 tk.writeBoard(jogo.getCaverna().apresenta(), jogo.getJogador().getScore(), 'x');
    		 }
    	 }
		 
		 // Mensagens finais
    	 if (jogo.getJogador().getTerminou()) {
    		 jogo.getJogador().somaScore(1000);
    		 System.out.println("Volte sempre!");
    	 }
		 if (jogo.getJogador().getScore() > 0 && jogo.getJogador().getVivo() && jogo.getJogador().getOuro()) {
			 jogo.print();
			 System.out.println("Você ganhou!");
			 tk.writeBoard(jogo.getCaverna().apresenta(), jogo.getJogador().getScore(), 'w');
		 }
		 else {
			 jogo.print();
			 System.out.println("Você perdeu :(");
			 tk.writeBoard(jogo.getCaverna().apresenta(), jogo.getJogador().getScore(), 'n');
		 }
     
    }
    tk.stop();
  }
}