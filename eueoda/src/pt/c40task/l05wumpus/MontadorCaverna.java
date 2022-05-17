package pt.c40task.l05wumpus;

public class MontadorCaverna {
	public static Caverna montaCaverna(String[][] input, String nome) {
		Caverna cave = new Caverna();
		if (input[0][2].charAt(0) != 'P') return null;
		
		int buracos = 0, wumpus = 0, ouro = 0, heroi = 0;
		for (String[] linha : input) {
			int x = linha[0].charAt(0) - '1', y = linha[1].charAt(0) - '1';
			char c = linha[2].charAt(0);
			switch (c) {
				case 'P':
					if (heroi == 1 || x != 0 || y != 0) return null;
					Heroi novoHeroi = new Heroi(x, y, cave, nome);
					novoHeroi.setPos(x, y);
					cave.hero = novoHeroi;
					cave.conecta(novoHeroi);
					heroi++;
					break;
				case 'B':
					if (buracos == 3) return null;
					Buraco novoBuraco = new Buraco(x, y, cave);
					novoBuraco.setPos(x, y);
					cave.conecta(novoBuraco);
					buracos++;
					break;
				case 'W':
					if (wumpus == 1) return null;
					Wumpus novoWumpus = new Wumpus(x, y, cave);
					novoWumpus.setPos(x, y);
					cave.conecta(novoWumpus);
					wumpus++;
					break;
				case 'O':
					if (ouro == 1) return null;
					Ouro novoOuro = new Ouro(x, y, cave);
					novoOuro.setPos(x, y);
					cave.conecta(novoOuro);
					ouro++;
					break;
				default:
					break;
			}
		}
		if (heroi != 1 || wumpus != 1 || ouro != 1 || buracos < 2 || buracos > 3) return null;
		return cave;
	}
}
