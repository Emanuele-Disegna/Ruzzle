package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	
	public List<Pos> trovaParola(String parola, Board board) {
		/*
		 * Facciamo partire la ricorsione solo se
		 * troviamo la prima lettera, altrimenti no
		 */
		
		for(Pos p : board.getPositions()/*Ritorna le posizioni della board*/) {
			//Se la lettera in pos è == alla prima lettera
			if(board.getCellValueProperty(p).get().charAt(0)
					/*Ritorna il valore della posizione*/== parola.charAt(0)) {
				
				
				//Facciamo partire la ricorsione
				List<Pos> parziale = new ArrayList<Pos>();
				parziale.add(p);
				if(cerca(parola, parziale, 1, board)) {
					return parziale;
				}
				
			}
			
			
		}
		
		return null;
	}
	
	private boolean cerca(String parola, List<Pos> percorso, int livello, Board board) {
		//Caso terminale
		if(livello == parola.length()) {
			return true;
		}
		
		Pos ultima = percorso.get(percorso.size()-1);
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		
		for(Pos p : adiacenti) {
			if(board.getCellValueProperty(p).get().charAt(0)==parola.charAt(livello)) {
				percorso.add(p);
				if(cerca(parola, percorso, livello+1, board)) {
					return true;
				}
				percorso.remove(percorso.size()-1);
			}
		}
		
		return false;
	}
}
