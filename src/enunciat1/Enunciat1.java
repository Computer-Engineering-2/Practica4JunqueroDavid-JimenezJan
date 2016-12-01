package enunciat1;
/*
 * Quins són els candidats del problema?
 * 
 * 		> Els Anuncis: s'ha de trobar una selecció que maximitzi la suma de tots els seus preus finals.
 * 
 * Quina funció de selecció aplicarà el vostre algorisme?
 * 
 * 		> selector(ArrayList<Anunci> anuncis) :  Donada una colecció amb tots els candidats, es trien els que tenen la millor oferta en relació al temps que ocupen.
 * 	 	  Es realitza això fins que no queda temps disponible per cap candidat.
 * 
 * 
 * La vostra funció de selecció, garanteix trobar sempre la millor solució?  Per  què?
 * 
 * 		> No, encara que tingui en compte la relació oferta/temps, el preu total només va en relació a la quantitat. Aquest és un problema d'optimizació que requeriria seleccionar
 * 		  segons la quantiat d'oferta en relació a la posició i al temps que ocupa comparat amb altres combinacions d'anuncis. Un anunci molt llarg però amb molta oferta en la posició 1 pot ser més profitòs
 * 		  que un grup de diversos anuncis amb alts ràtios oferta/temps.
 * 
 * 		  De totes formes, la nostra funció de selecció reordena els candidats seleccionats descendentmentn per beneficiar-se al màxim de la bonificació de la posició.
 * 
 */

import java.util.ArrayList;
import java.util.Collections;

import Keyboard.*;

public class Enunciat1 {

	public static void main(String[] args){
		char input;
		ArrayList<Anunci> anuncis=new ArrayList<Anunci>();
		
		// Crear Anuncis
		
		System.out.println("Generar aleatòriament? Y/N");
		do{
			input=Character.toUpperCase(Keyboard.readChar());
		}while(input!='N' && input!='Y');
			if(input=='N'){
				int a,b;
				int id=1;
				do{	
				System.out.println("Entra la durada d'un nou anunci o entra un nombre negatiu (-1) per acabar.");
				a=Keyboard.readInt();
				if(a<0) break;
				System.out.println("Entra la oferta del nou anunci o entra un nombre negatiu (-1) per acabar");
				b=Keyboard.readInt();
				if(b<0) break;
				anuncis.add(new Anunci(id,a,b));
				id++;
				}while(true);
			}
			else{
				int qAnuncis=(int) Math.floor((Math.random() * 100)+1);
					for(int i=1;i<qAnuncis;i++){
					anuncis.add(new Anunci(i,(int) Math.floor((Math.random() * 66)+5),(int) Math.floor(Math.random() * 9)+1));
				}
			}
			
			System.out.println("Col·lecció d'Anuncis: \n");
			for(Anunci a:anuncis){
				System.out.println(a.toString());
			}
			
			ArrayList<Anunci> solution = selector(anuncis);
			Collections.sort(solution);
			
			System.out.println(" \n*************************");
			System.out.println("************************* \n");
			System.out.println("Selecció d'anuncis: \n");
			
			int preuTotal=0;
			int pos=1;
			
			for(Anunci a:solution){
				preuTotal+= a.offer()*1000/pos + 5000;
				pos++;
				System.out.println(a.toString());
				
			}
			System.out.println("\n \n Preu Total= "+preuTotal+"€");		
	}

	private static ArrayList<Anunci> selector(ArrayList<Anunci> anuncis){
		ArrayList<Anunci> solution = new ArrayList<Anunci>();
		int time=70;
		Anunci millor;
		do{
			millor=null;
			for(Anunci a:anuncis){
				if(a.length()<=time){ // Primer es busquen anuncis que tinguin una durada menor a la que queda per omplir.
					if (millor==null) millor=a;
					if(((float)a.offer()/a.length()) > ((float)millor.offer()/millor.length())) millor=a; // Si ja s'ha trobat un, s'agafa el que té una oferta major en relació a la durada.
				}
			}
			if (millor!=null){ // Si s'ha trobat algún, es resta la seva durada (ja hem comprobat que es menor a la que queda per omplir)
				time-=millor.length();
				solution.add(millor);
				anuncis.remove(millor);
			}
		}while (time>70 || millor!=null);
	
		return solution;
	}	
}
