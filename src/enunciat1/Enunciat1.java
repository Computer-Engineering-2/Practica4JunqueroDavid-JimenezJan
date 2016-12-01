package enunciat1;
/*
 * Quins�s�n�els�candidats�del�problema?
 * 
 * 		> Els Anuncis: s'ha de trobar una selecci� que maximitzi la suma de tots els seus preus finals.
 * 
 * Quina�funci�de�selecci�aplicar�el�vostre�algorisme?
 * 
 * 		> selector(ArrayList<Anunci> anuncis) :  Donada una colecci� amb tots els candidats, es trien els que tenen la millor oferta en relaci� al temps que ocupen.
 * 	 	  Es realitza aix� fins que no queda temps disponible per cap candidat.
 * 
 * 
 * La�vostra�funci�de�selecci�,�garanteix�trobar�sempre�la�millor�soluci�?��Per� qu�?
 * 
 * 		> No, encara que tingui en compte la relaci� oferta/temps, el preu total nom�s va en relaci� a la quantitat. Aquest �s un problema d'optimizaci� que requeriria seleccionar
 * 		  segons la quantiat d'oferta en relaci� a la posici� i al temps que ocupa comparat amb altres combinacions d'anuncis. Un anunci molt llarg per� amb molta oferta en la posici� 1 pot ser m�s profit�s
 * 		  que un grup de diversos anuncis amb alts r�tios oferta/temps.
 * 
 * 		  De totes formes, la nostra funci� de selecci� reordena els candidats seleccionats descendentmentn per beneficiar-se al m�xim de la bonificaci� de la posici�.
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
		
		System.out.println("Generar aleat�riament? Y/N");
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
			
			System.out.println("Col�lecci� d'Anuncis: \n");
			for(Anunci a:anuncis){
				System.out.println(a.toString());
			}
			
			ArrayList<Anunci> solution = selector(anuncis);
			Collections.sort(solution);
			
			System.out.println(" \n*************************");
			System.out.println("************************* \n");
			System.out.println("Selecci� d'anuncis: \n");
			
			int preuTotal=0;
			int pos=1;
			
			for(Anunci a:solution){
				preuTotal+= a.offer()*1000/pos + 5000;
				pos++;
				System.out.println(a.toString());
				
			}
			System.out.println("\n \n Preu Total= "+preuTotal+"�");		
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
					if(((float)a.offer()/a.length()) > ((float)millor.offer()/millor.length())) millor=a; // Si ja s'ha trobat un, s'agafa el que t� una oferta major en relaci� a la durada.
				}
			}
			if (millor!=null){ // Si s'ha trobat alg�n, es resta la seva durada (ja hem comprobat que es menor a la que queda per omplir)
				time-=millor.length();
				solution.add(millor);
				anuncis.remove(millor);
			}
		}while (time>70 || millor!=null);
	
		return solution;
	}	
}
