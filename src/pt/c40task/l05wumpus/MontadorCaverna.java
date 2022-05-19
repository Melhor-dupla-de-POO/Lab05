package pt.c40task.l05wumpus;

public class MontadorCaverna {
	public static Caverna montaCaverna(String[][] input, String nome) {
		// O metodo retorna null caso a caverna seja invalida
		
		Caverna cave = new Caverna();
		
		// Guardam quantos componentes de cada ja processamos
		int buracos = 0, wumpus = 0, ouro = 0, heroi = 0;
		for (String[] linha : input) {
			int x = linha[0].charAt(0) - '1', y = linha[1].charAt(0) - '1';
			char c = linha[2].charAt(0);
			switch (c) {
				case 'P':
					
					// Se ja foi processado um heroi ou ele esta fora da posicao (1, 1)
					if (heroi == 1 || x != 0 || y != 0) return null;
					Heroi novoHeroi = new Heroi(x, y, cave, nome);
					novoHeroi.setPos(x, y);
					cave.hero = novoHeroi;
					cave.conectaHeroi(novoHeroi);
					heroi++;
					break;
				case 'B':
					
					// Se ja foram processados 3 buracos
					if (buracos == 3) return null;
					Buraco novoBuraco = new Buraco(x, y, cave);
					novoBuraco.setPos(x, y);
					if (!cave.conecta(novoBuraco)) return null;
					buracos++;
					break;
				case 'W':
					
					// Se ja temos um Wumpus
					if (wumpus == 1) return null;
					Wumpus novoWumpus = new Wumpus(x, y, cave);
					novoWumpus.setPos(x, y);
					if (!cave.conecta(novoWumpus)) return null;
					wumpus++;
					break;
				case 'O':
					
					// Se ja temos um ouro
					if (ouro == 1) return null;
					Ouro novoOuro = new Ouro(x, y, cave);
					novoOuro.setPos(x, y);
					if (!cave.conecta(novoOuro)) return null;
					ouro++;
					break;
				default:
					break;
			}
		}
		
		// Checagem final
		if (heroi != 1 || wumpus != 1 || ouro != 1 || buracos < 2 || buracos > 3) return null;
		return cave;
	}
}
