package pack1;

import java.util.ArrayList;

public class Klient extends Person {
	protected String id;
	protected int il_wyp;
	protected ArrayList<Film> wyp;
	
	public void returnMovie(int index) {
		wyp.remove(index);
	}
	
	public Film wyp_movie(int index) {
		return wyp.get(index);
	}
	
	
	public void addToCart(Film wypo) {
		wyp.add(wypo);
		this.il_wyp++;
	}
	
	public void deleteCart(Film wypo) {
		for(int i=0;i<wyp.size();i++) {
			if(wypo.equals(wyp.get(i))) {
				wyp.remove(i);
				il_wyp--;
			}
		}
	}
	public String getId() {
		return id;
	}
	public void setId(int id) {
		this.id ="cl"+String.valueOf(id);;
	}
	public int getIl_wyp() {
		return wyp.size();
	}
	public void setIl_wyp(int il_wyp) {
		this.il_wyp = il_wyp;
	}
	public Klient(String imie, String nazwisko, String pesel, int id,String pass) {
		wyp = new ArrayList<Film>();
		il_wyp=0;
		this.imie=imie;
		this.nazwisko=nazwisko;
		this.pesel=pesel;
		this.id="cl"+String.valueOf(id);;
		int daty[]= new int[3];
		
		if((int)pesel.charAt(2)-48>=2) {
			daty[2]=2000+(((int)pesel.charAt(0)-48)*10)+(int)pesel.charAt(1)-48;
			daty[1]=(((int)pesel.charAt(2)-50)*10)+(int)pesel.charAt(3)-48;
		}else {
			daty[2]=1900+(((int)pesel.charAt(0)-48)*10)+(int)pesel.charAt(1)-48;
			daty[1]=(((int)pesel.charAt(2)-48)*10)+(int)pesel.charAt(3)-48;
		}
		daty[0]=(((int)pesel.charAt(4)-48)*10)+(int)pesel.charAt(5)-48;
		this.urodzenie= new Data(daty[0],daty[1],daty[2]);
		this.upr="Klient";
		this.pass=pass;
		this.found=true;
	}
}
