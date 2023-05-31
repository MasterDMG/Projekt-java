package pack1;

public class Copy {
	protected Film tab[];
	protected int rozmiarf;
	
	public Copy(){
		tab = new Film[0];
		
	}
	public void copyF(int rozmiar, Film tablica[]) {
		rozmiarf=rozmiar;
		tab = new Film[rozmiar+1];
		for(int i=0;i<rozmiar;i++) {
			tab[i].setCena(tablica[i].getCena());
			tab[i].setDlugosc(tablica[i].getDlugosc());
			tab[i].setNazwa(tablica[i].getNazwa());
			tab[i].setSztuk(tablica[i].getSztuk());
			tab[i].setGatunek(tablica[i].getGatunek());
		}
		
		
	}
	public void fCopy( Film tablica[]) {
		for(int i=0;i<rozmiarf;i++) {
			tablica[i].setCena(tab[i].getCena());
			tablica[i].setDlugosc(tab[i].getDlugosc());
			tablica[i].setNazwa(tab[i].getNazwa());
			tablica[i].setSztuk(tab[i].getSztuk());
			tablica[i].setGatunek(tab[i].getGatunek());
		}
	}
}
