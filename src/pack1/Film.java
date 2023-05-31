package pack1;

public class Film {
	private String nazwa;
	private String gatunek;
	private Data premiera;
	private double dlugosc;
	private int sztuk;
	private double cena;
	private boolean found;
	
	public Film(String nazwa,String gatunek,double dl,int szt,double cen,int tab[]) {
		this.nazwa=nazwa;
		this.gatunek=gatunek;
		this.dlugosc=dl;
		this.sztuk=szt;
		this.cena=cen;
		this.premiera= new Data(tab[0],tab[1],tab[2]);
		this.found=true;
	}
	
	
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getGatunek() {
		return gatunek;
	}
	public void setGatunek(String gatunek) {
		this.gatunek = gatunek;
	}
	public String getPremiera() {
		return premiera.getDataS();
	}
	public Data getPremieraD() {
		return premiera;
	}
	public void setPremiera(int d,int m, int r) {
		this.premiera = new Data(d,m,r);
	}
	public double getDlugosc() {
		return dlugosc;
	}
	public void setDlugosc(double dlugosc) {
		this.dlugosc = dlugosc;
	}
	public int getSztuk() {
		return sztuk;
	}
	public void setSztuk(int sztuk) {
		this.sztuk = sztuk;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}


	public boolean isFound() {
		return found;
	}


	public void setFound(boolean found) {
		this.found = found;
	}
}
