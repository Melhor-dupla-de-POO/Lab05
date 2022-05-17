package pt.c40task.l05wumpus;

import java.util.ArrayList;
import java.util.random.*;
import java.util.Map;
import java.util.Random;

public class Sala {
	private ArrayList<Componente> comps;
	private boolean visitado;
	
	// Map para calcular qual a componente mais relevante de cada sala
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
	
	public boolean adiciona(Componente nova) {
		// Adiciona a nova componente e verifica se eh uma sala valida
		
		// Só podemos ter um componente entre Ouro, Wumpus e Buraco
		int qtd = nova.getPrimario();
		for (Componente c : comps) {
			qtd += c.getPrimario();
		}
		if (qtd <= 1) {
			this.comps.add(nova);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void adicionaHeroi(Heroi hero) {
		
		// Coloca o heroi em this e verifica se tem um buraco ou wumpus
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
			
			// Se ele é sortudo e tem a flecha equipada, o wumpus morre
			if (hero.getEquipada() && rand.nextBoolean()) {
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
		// Coleta o ouro e atualiza a sala e o score
		// Ou indica que nao tem ouro
		
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
		// Devolve o caracter correspondente ao componente mais relevante da sala
		// Se a sala ainda nao foi visitada, devolve '-'
		
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
		// Devolve a frase associada ao componente mais relevante da sala
		
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
