package pack1;

public class Pracownik extends Person{
	private String id;
	private int nr_dzialu;
	private double wyplata;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(int id) {
		this.id = "wo"+String.valueOf(id);
	}
	public int getNr_dzialu() {
		return nr_dzialu;
	}
	public void setNr_dzialu(int nr_dzialu) {
		this.nr_dzialu = nr_dzialu;
	}
	public double getWyplata() {
		return wyplata;
	}
	public void setWyplata(double wyplata) {
		this.wyplata = wyplata;
	}
	public Pracownik(String imie, String nazwisko, String pesel, int id,int nr,double placa,String pass) {
		this.imie=imie;
		this.nazwisko=nazwisko;
		this.pesel=pesel;
		this.id="wo"+String.valueOf(id);
		this.nr_dzialu=nr;
		this.wyplata=placa;
		int daty[]= new int[3];
		
		if((int)pesel.charAt(2)-48>=2) {
			daty[2]=2000+(((int)pesel.charAt(0)-48)*10)+(int)pesel.charAt(1)-48;
			daty[1]=(((int)pesel.charAt(2)-49)*10)+(int)pesel.charAt(3)-48;
		}else {
			daty[2]=1900+(((int)pesel.charAt(0)-48)*10)+(int)pesel.charAt(1)-48;
			daty[1]=(((int)pesel.charAt(2)-48)*10)+(int)pesel.charAt(3)-48;
		}
		daty[0]=(((int)pesel.charAt(4)-48)*10)+(int)pesel.charAt(5)-48;
		this.urodzenie= new Data(daty[0],daty[1],daty[2]);
		this.upr="Pracownik";
		this.pass=pass;
		this.found=true;
	}
	
	
	
}
