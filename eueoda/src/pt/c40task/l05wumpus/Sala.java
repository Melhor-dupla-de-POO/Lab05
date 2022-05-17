package pt.c40task.l05wumpus;

import java.util.ArrayList;
import java.util.random.*;
import java.util.Map;
import java.util.Random;

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
		Componente w = null;
		for (Componente c : comps) {
			buraco |= (c.getId() == 'B');
			if (c.getId() == 'W') {
				wumpus = true;
				w = c;
			}
		}
		
		Random rand = new Random();
		
		if (buraco) {
			hero.morre();
		}
		if (wumpus) {
			if (hero.getEquipada() && rand.nextBoolean()) {
				// to do random
				System.out.println("Você entrou na sala e matou o Wumpus!");
				hero.somaScore(500);
				this.comps.remove(w);
			}
			else {
				hero.morre();
				System.out.println("Vocẽ entrou na sala e o Wumpus te matou");
			}
		}
	}
	
	public void coleta(Heroi hero) {
		boolean ouro = false;
		Componente o = null;
		for (Componente c : comps) {
			if (c.getId() == 'O') {
				ouro = true;
				o = c;
			}
		}
		this.comps.remove(o);
		if (ouro) {
			hero.somaScore(1000);
			hero.setOuro(true);
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
		if (!this.visitado) return "-";
		String ans = "#";
		int pwr = -1;
		for (Componente c : comps) {
			if (poder.get(c.getId()) > pwr) {
				pwr = poder.get(c.getId());
				ans = "" + c.getId();
			}
		}
		return ans;
	}
	
	public String frase() {
		String ans = "A sala está vazia\n";
		int pwr = -1;
		for (Componente c : comps) {
			int p = poder.get(c.getId());
			if (p > pwr && p != 2) {
				pwr = p;
				ans = c.toString();
			}
		}
		return ans;
	}
	
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
}
