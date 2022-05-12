package pt.c40task.l05wumpus;

import java.util.ArrayList;
import java.util.Map;

public class Sala {
	private ArrayList<Componente> comps;
	boolean visitado;
	private static Map<Character, Integer> poder = Map.of(
			'O', 3,
			'B', 3,
			'W', 3,
			'P', 2,
			'f', 1,
			'b', 0
		);
	
	Sala() {
		comps = new ArrayList<Componente>();
		visitado = false;
	}
	
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
			Heroi.setVivo(false);
		}
		if (wumpus) {
			if (Heroi.getEquipada()) {
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
	
	public String toString() {
		String ans = new String();
		int pwr = -1;
		if (this.visitado) ans = "#";
		else ans = "-";
		for (Componente c : comps) {
			if (poder.get(c.getId()) > pwr) {
				pwr = poder.get(c.getId());
				ans = "" + c.getId();
			}
		}
		return ans;
	}
	
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
}
