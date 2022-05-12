package pt.c40task.l05wumpus;

import java.util.ArrayList;

public class Sala {
	private ArrayList<Componente> comps;
	boolean visitado;
	
	public void adiciona(Componente nova) {
		// Só podemos ter um componente entre Ouro, Wumpus e Buraco
		int qtd = nova.getPrimario();
		for (Componente c : comps) {
			qtd += c.getPrimario();
		}
		if (qtd <= 1)
			this.comps.add(nova);
		else {
			// Dar algum erro
		}
	}
	
	public void adicionaHeroi(Heroi hero) {
		comps.add(hero);
		boolean buraco = false, wumpus = false;
		for (Componente c : comps) {
			buraco |= (c.getId() == 'B');
			wumpus |= (c.getId() == 'W');
		}
		
		// nao sei se a gnt printa aqui ou qd printar o bagulho da sala
		if (buraco) {
			hero.setVivo(false);
		}
		if (wumpus) {
			if (hero.getEquipada()) {
				// to do random
				System.out.println("Você entrou na sala e matou o Wumpus!");
			}
			else {
				Heroi.setVivo(false);
				System.out.println("Vocẽ entrou na sala e o Wumpus te matou");
			}
		}
	}
	
	public void coleta() {
		boolean ouro = false;
		for (Componente c : comps) {
			ouro |= (c.getId() == 'O');
		}
		if (ouro) {
			Heroi.setOuro(true);
			System.out.println("Você capturou o ouro!");
		}
		else {
			System.out.println("Não tem ouro nessa sala :(");
		}
	}
	
	public void retira(Componente velha) {
		this.comps.remove(velha);
	}
}
