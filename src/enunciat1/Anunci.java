package enunciat1;

public class Anunci implements Comparable<Anunci>{

	private int id;
	private int length;
	private int offer;
	
	public Anunci(int id,int length, int offer){
		this.id=id;
		this.length=length;
		this.offer=offer;
	}
	public int length(){return this.length;}
	public int offer(){return this.offer;}
	public String toString(){return "Anunci "+id+" ("+length+"s | "+offer+"K€)";}
	public int getId(){return this.id;}
	public boolean equals(Object o){
		if(!(o instanceof Anunci)) return false;
		return this.id == ((Anunci)o).getId();
	}
	public int compareTo(Anunci a){
		return -(this.offer - a.offer()); // L'ordre natural el retorna invertit per tal de que el TreeSet l'ordeni descendentment.
	}
}
