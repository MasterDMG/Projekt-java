package pack1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.File; 
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Filmoteka {
	protected int current;
	protected ArrayList<Klient> tabK;
	protected ArrayList<Pracownik> tabP;
	protected static ArrayList<Film> tabF;
	protected ArrayList<Klient> tabKWysz;
	protected ArrayList<Pracownik> tabPWysz;
	protected static ArrayList<Film> tabFWysz;
	protected ArrayList<Film> kosz;
	protected int founded;
	private String generatePass(String s) { 
		int j=s.length();
		String encrypted="";
		for(int i=0;i<s.length();i++) {
			encrypted+=String.valueOf(s.charAt(i)+j);
			j--;
		}
		return encrypted;
	}
	
	
	public boolean checkPassK(String pass, int id) {
		if(tabK.get(id).getPass().equals(generatePass(pass)))return true;
		else return false;
	}
	public boolean checkPassP(String pass, int id) {
		if(tabP.get(id).getPass().equals(generatePass(pass)))return true;
		else return false;
	}
	public void changePassK(String pass, int id) {
		tabK.get(id).setPass(generatePass(pass));
	}
	public void changePassP(String pass, int id) {
		tabP.get(id).setPass(generatePass(pass));
	}
	
	public Filmoteka() {
		current=0;
		tabK = new ArrayList<Klient>();
		tabP = new ArrayList<Pracownik>();
		tabF = new ArrayList<Film>();
		kosz = new ArrayList<Film>();
		Pracownik newWorker = new Pracownik("Marcel","Poloczek","01251410978",0,0,500000,generatePass("root"));
		tabP.add(newWorker);
		tabP.get(0).setUpr("Admin");
		
	}
	

	public void addClient(String imie, String nazwisko, String pesel, String pass, int il) {
		Klient newKlient = new Klient(imie,nazwisko,pesel,il,generatePass(pass));
		tabK.add(newKlient);
	}
	public void addMovie(String nazwa,String gatunek,double dl,int szt,double cen, int tab[]) {
		Film newMovie = new Film(nazwa,gatunek,dl,szt,cen,tab);
		tabF.add(newMovie);
	}
	public void addWorker(String imie, String nazwisko, String pesel,int nr,double placa, String pass,int il) {
		Pracownik newWorker = new Pracownik(imie,nazwisko,pesel,il,nr,placa,generatePass(pass));
		tabP.add(newWorker);
	}
	public int getFoundedNumber() {
		return this.founded;
	}
	public void deleteK() {
		tabK.remove(current);
		this.founded--;
		
	}
	public void deleteKosz() {
		kosz.remove(current);
		
	}
	public void returnKosz() {
		tabF.add(kosz.get(current));
		deleteKosz();
	}
	public void deleteP() {
		tabP.remove(current);
		this.founded--;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	
	public void next(int place, int...id) {
		current++;
		switch(place) {
			case 1:
				if(current==tabK.size())current=0;
			break;
			case 2:
				if(current==tabP.size())current=0;
			break;
			case 3:
				if(current==tabF.size())current=0;
			break;
			case 4:
				if(current==kosz.size())current=0;
			break;
			case 5:
				if(current==tabK.get(id[0]).getIl_wyp())current=0;
			break;
		}
		
		
	}
	public void prev(int place, int...id) {
		current--;
		switch(place) {
			case 1:
				if(current==-1)current=tabK.size()-1;
			break;
			case 2:
				if(current==-1)current=tabP.size()-1;
			break;
			case 3:
				if(current==-1)current=tabF.size()-1;
			break;
			case 4:
				if(current==-1)current=kosz.size()-1;
			break;
			case 5:
				if(current==-1)current=tabK.get(id[0]).getIl_wyp()-1;
			break;
		}
		
		
	}
	public void setCurrentToZero() {
		current=0;
	}
	
	public void showCurrentB(int id) {
		System.out.println("Tytul:     "+tabK.get(id).wyp_movie(current).getNazwa());
		System.out.println("Gatunek:   "+tabK.get(id).wyp_movie(current).getGatunek());
		System.out.println("Premiera:  "+tabK.get(id).wyp_movie(current).getPremiera());
		System.out.println("Dlugosc:   "+tabK.get(id).wyp_movie(current).getDlugosc());
		System.out.println("Sztuk:     "+tabK.get(id).wyp_movie(current).getSztuk());
		System.out.println("Cena:      "+tabK.get(id).wyp_movie(current).getCena());
	}
	public boolean showCurrentF() {
	
					System.out.println("Tytul:     "+tabF.get(current).getNazwa());
					System.out.println("Gatunek:   "+tabF.get(current).getGatunek());
					System.out.println("Premiera:  "+tabF.get(current).getPremiera());
					System.out.println("Dlugosc:   "+tabF.get(current).getDlugosc());
					System.out.println("Sztuk:     "+tabF.get(current).getSztuk());
					System.out.println("Cena:      "+tabF.get(current).getCena());
					return true;
				
		
		
	}
	public boolean showCurrentP() {
		
				System.out.println("Id:            "+tabP.get(current).getId());
				System.out.println("Imie:          "+tabP.get(current).getImie());
				System.out.println("Nazwisko:      "+tabP.get(current).getNazwisko());
				System.out.println("Urodzenie:     "+tabP.get(current).getUrodzenie().getDataS());
				System.out.println("Numer Dzialu:  "+tabP.get(current).getNr_dzialu());
				System.out.println("Wypłata:       "+tabP.get(current).getWyplata());
				
				return true;
			
}
	public boolean showCurrentK() {
		
		
				System.out.println("Id:                "+tabK.get(current).getId());
				System.out.println("Imie:              "+tabK.get(current).getImie());
				System.out.println("Nazwisko:          "+tabK.get(current).getNazwisko());
				System.out.println("Urodzenie:         "+tabK.get(current).getUrodzenie().getDataS());
				System.out.println("Ilosc wyporzyczen: "+tabK.get(current).getIl_wyp());
			
				return true;
			
	
}
	public boolean showCurrentKosz() {
		
		
				System.out.println("Tytul:     "+kosz.get(current).getNazwa());
				System.out.println("Gatunek:   "+kosz.get(current).getGatunek());
				System.out.println("Premiera:  "+kosz.get(current).getPremiera());
				System.out.println("Dlugosc:   "+kosz.get(current).getDlugosc());
				System.out.println("Sztuk:     "+kosz.get(current).getSztuk());
				System.out.println("Cena:      "+kosz.get(current).getCena());
				return true;
	
	
}
	public String login(String name,String pass) {
		if(name.charAt(0)=='w') {
			for(int i=0;i<tabP.size();i++) {
				if(name.equals(tabP.get(i).getId()) && generatePass(pass).equals(tabP.get(i).getPass())) {
					return tabP.get(i).getUpr();
				}
			}
			return "NoAcc";
		}else {
			for(int i=0;i<tabK.size();i++) {
				if(name.equals(tabK.get(i).getId()) && generatePass(pass).equals(tabK.get(i).getPass())) {
					return tabK.get(i).getUpr();
				}
			}
			return "NoAcc";
		}
	}
	public String getLastId(int option) {
		switch( option) {
			case 0:
				return tabK.get(tabK.size()-1).getId();
			case 1: 
				return tabP.get(tabP.size()-1).getId();
			default: return "";
		}
	}
	public int getSizeOf(int array, int...id ) {
		switch(array) {
			case 1:return tabF.size();
			case 2:return tabK.size();
			case 3:return tabP.size();
			case 4:return kosz.size();
			case 5: return (tabK.get(id[0])).getIl_wyp();
			default: return 0;
		}
	}
	
	public boolean borrowMovie(int id) {
		
		if(tabF.get(current).getSztuk()>0) {
			tabK.get(id).addToCart(tabF.get(current));
			tabF.get(current).setSztuk(tabF.get(current).getSztuk()-1);
			tabK.get(current).setIl_wyp(tabK.get(current).getIl_wyp()+1);
			return true;
		}
		else return false;
	}
	
	public void returnMovie(int id) {
				tabK.get(id).deleteCart(tabF.get(current));
				
	}
	public void modifyK(String modifier,int place) {
		switch(place) {
			case 1:
				tabK.get(current).setImie(modifier);
			break;
			case 2:
				tabK.get(current).setNazwisko(modifier);
			break;
			case 3:
				tabK.get(current).setPesel(modifier);
			break;
			
		
		}
	}
	public void modifyP(String modifier,int place, double...digit) {
		switch(place) {
			case 1:
				tabP.get(current).setImie(modifier);
			break;
			case 2:
				tabP.get(current).setNazwisko(modifier);
			break;
			case 3:
				tabP.get(current).setPesel(modifier);
			break;
			
			case 4:
				tabP.get(current).setNr_dzialu((int)digit[0]);
			break;
			case 5:
				tabP.get(current).setWyplata(digit[0]);
			break;
		}
	}
	public void modifyF(String modifier,int place, double...digit) {
		switch(place) {
			case 1:
				tabF.get(current).setNazwa(modifier);
			break;
			case 2:
				tabF.get(current).setGatunek(modifier);
			break;
			case 3:
				tabF.get(current).setDlugosc(digit[0]);
			break;
			case 4:
				tabF.get(current).setSztuk((int)digit[0]);
			break;
			case 5:
				tabF.get(current).setCena(digit[0]);
			break;
		}
	}
	
	public void saveFile(String name) {
	    try {
	    	File path = new File(name+".txt");
		    PrintWriter save;
		    save = new PrintWriter(path);
		    if (path.createNewFile()) {
		    	save.println(getSizeOf(2));
		    	for(int i=0;i<getSizeOf(2);i++) {
		    		save.println(tabK.get(i).getImie());
		    		save.println(tabK.get(i).getNazwisko());
		    		save.println(tabK.get(i).getId());
		    		save.println(tabK.get(i).getPesel());
		    		save.println(tabK.get(i).getPass());
		    		save.println(tabK.get(i).getIl_wyp());
		    		for(int j=0;j<tabK.get(i).getIl_wyp();j++) {
		    			save.println(tabF.get(j).getNazwa());
		    			save.println(tabF.get(j).getGatunek());
		    			save.println(tabF.get(j).getDlugosc());
		    			save.println(tabF.get(j).getSztuk());
		    			save.println(tabF.get(j).getCena());
		    			save.println((tabF.get(j).getPremieraD()).getDzien());
		    			save.println((tabF.get(j).getPremieraD()).getMies());
		    			save.println((tabF.get(j).getPremieraD()).getRok());
		    			
		    		}
		    		
		    	}
		    	save.println(getSizeOf(1));
		    	for(int j=0;j<getSizeOf(1);j++) {
	    			save.println(tabF.get(j).getNazwa());
	    			save.println(tabF.get(j).getGatunek());
	    			save.println(tabF.get(j).getDlugosc());
	    			save.println(tabF.get(j).getSztuk());
	    			save.println(tabF.get(j).getCena());
	    			save.println((tabF.get(j).getPremieraD()).getDzien());
	    			save.println((tabF.get(j).getPremieraD()).getMies());
	    			save.println((tabF.get(j).getPremieraD()).getRok());
	    			
	    		}
		    	save.println(getSizeOf(3));
		    	for(int i=0;i<getSizeOf(3);i++) {
		    		save.println(tabP.get(i).getImie());
		    		save.println(tabP.get(i).getNazwisko());
		    		save.println(tabP.get(i).getPesel());
		    		save.println(tabP.get(i).getId());
		    		save.println(tabP.get(i).getNr_dzialu());
		    		save.println(tabP.get(i).getWyplata());
		    		save.println(tabP.get(i).getPass());
		    	}
		    	  
		    	  
		    	  
		    
		    } else {
		    	save.println(getSizeOf(2));
		    	for(int i=0;i<getSizeOf(2);i++) {
		    		save.println(tabK.get(i).getImie());
		    		save.println(tabK.get(i).getNazwisko());
		    		save.println(tabK.get(i).getPesel());
		    		save.println(tabK.get(i).getId());
		    		save.println(tabK.get(i).getPass());
		    		save.println(tabK.get(i).getIl_wyp());
		    		for(int j=0;j<tabK.get(i).getIl_wyp();j++) {
		    			save.println(tabF.get(j).getNazwa());
		    			save.println(tabF.get(j).getGatunek());
		    			save.println(tabF.get(j).getDlugosc());
		    			save.println(tabF.get(j).getSztuk());
		    			save.println(tabF.get(j).getCena());
		    			save.println((tabF.get(j).getPremieraD()).getDzien());
		    			save.println((tabF.get(j).getPremieraD()).getMies());
		    			save.println((tabF.get(j).getPremieraD()).getRok());
		    			
		    		}
		    		
		    	}
		    	save.println(getSizeOf(1));
		    	for(int j=0;j<getSizeOf(1);j++) {
	    			save.println(tabF.get(j).getNazwa());
	    			save.println(tabF.get(j).getGatunek());
	    			save.println(tabF.get(j).getDlugosc());
	    			save.println(tabF.get(j).getSztuk());
	    			save.println(tabF.get(j).getCena());
	    			save.println((tabF.get(j).getPremieraD()).getDzien());
	    			save.println((tabF.get(j).getPremieraD()).getMies());
	    			save.println((tabF.get(j).getPremieraD()).getRok());
	    			
	    		}
		    	save.println(getSizeOf(3));
		    	for(int i=0;i<getSizeOf(3);i++) {
		    		save.println(tabP.get(i).getImie());
		    		save.println(tabP.get(i).getNazwisko());
		    		save.println(tabP.get(i).getPesel());
		    		save.println(tabP.get(i).getId());
		    		save.println(tabP.get(i).getNr_dzialu());
		    		save.println(tabP.get(i).getWyplata());
		    		save.println(tabP.get(i).getPass());
		    	}
		    }
		    save.close();
	    } catch (IOException e) {
	    	System.out.println("Nie udało sie zapisac pliku!");
	    	e.printStackTrace();
	    }
	  }
	public void deleteAll() {
		for(int i=0;i<tabK.size();i++) {
			tabK.remove(i);
		}
		for(int i=0;i<tabP.size();i++) {
			tabP.remove(i);
		}
		for(int i=0;i<tabF.size();i++) {
			tabF.remove(i);
		}
		for(int i=0;i<kosz.size();i++) {
			kosz.remove(i);
		}
	}
	public boolean readFile(String name) {
	    try {
	    	deleteAll();
	    	Scanner read = new Scanner(new File(name+".txt"));
	    	
	    	int border= Integer.parseInt(read.nextLine());
	    	int wypBorder;
	    		for(int i=0;i<border;i++) {
	    			Klient newKlient = new Klient(read.nextLine(),read.nextLine(),read.nextLine(),Integer.parseInt((read.nextLine()).substring(2)),read.nextLine());
	    			tabK.add(newKlient);
	    			wypBorder=Integer.parseInt(read.nextLine());
	    			for(int j=0;j<wypBorder;j++) {
	    				int tab[]=new int[3];
	    				Film newWypo = new Film(read.nextLine(),read.nextLine(),Double.parseDouble(read.nextLine()),Integer.parseInt(read.nextLine()),Double.parseDouble(read.nextLine()),tab);
	    				newWypo.setPremiera(Integer.parseInt(read.nextLine()), Integer.parseInt(read.nextLine()), Integer.parseInt(read.nextLine()));
	    				tabK.get(i).addToCart(newWypo);
	    			}
	    		}
	    		border= Integer.parseInt(read.nextLine());
	    		for(int j=0;j<border;j++) {
    				int tab[]=new int[3];
    				Film newWypo = new Film(read.nextLine(),read.nextLine(),Double.parseDouble(read.nextLine()),Integer.parseInt(read.nextLine()),Double.parseDouble(read.nextLine()),tab);
    				newWypo.setPremiera(Integer.parseInt(read.nextLine()), Integer.parseInt(read.nextLine()), Integer.parseInt(read.nextLine()));
    				tabF.add(newWypo);
    			}
	    		border= Integer.parseInt(read.nextLine());
	    		for(int i=0;i<border;i++) {
	    			Pracownik newWorker = new Pracownik(read.nextLine(),read.nextLine(),read.nextLine(),Integer.parseInt((read.nextLine()).substring(2)),Integer.parseInt(read.nextLine()),Double.parseDouble(read.nextLine()),read.nextLine());
	    			tabP.add(newWorker);
	    			
	    		}
	    		
	    		
	    		read.close();
	    		tabP.get(0).setUpr("Admin");
	    		return true;
	    } catch (FileNotFoundException e) {  
	    	return false;
	    	
	    }
	    
	}
	public static void sortujPoNazwie() {
		Collections.sort(tabF, new Comparator<Film>() {
            @Override
            public int compare(Film film1, Film film2) {
                return film2.getNazwa().compareToIgnoreCase(film1.getNazwa());
            }
        });
    }
	public static void sortujPoNazwieRos() {
	Collections.sort(tabF, new Comparator<Film>() {
		@Override
        public int compare(Film film1, Film film2) {
            return film1.getNazwa().compareToIgnoreCase(film2.getNazwa());
        }
    });
	}
	
	public void sortCzasF(boolean kier) {
		if(kier) {
		Film pom=tabF.get(0);
			for(int i=0;i<tabF.size();i++) {
				for(int j=0;j<tabF.size();j++) {
					
					if(tabF.get(i).getDlugosc()>tabF.get(j).getDlugosc()) {
						pom=tabF.get(i);
						tabF.set(i, tabF.get(j));
						tabF.set(j, pom);
						
					}
				}
			}
		}else {
			Film pom=tabF.get(0);
			for(int i=0;i<tabF.size();i++) {
				for(int j=0;j<tabF.size();j++) {
					
					if(tabF.get(i).getDlugosc()<tabF.get(j).getDlugosc()) {
						pom=tabF.get(i);
						tabF.set(i, tabF.get(j));
						tabF.set(j, pom);
						
					}
				}
			}
		}
	}
	public void sortDzialP(boolean kier) {
		if(kier) {
		Pracownik pom=tabP.get(0);
			for(int i=0;i<tabP.size();i++) {
				for(int j=0;j<tabP.size();j++) {
					
					if(tabP.get(i).getNr_dzialu()>tabP.get(j).getNr_dzialu()) {
						pom=tabP.get(i);
						tabP.set(i, tabP.get(j));
						tabP.set(j, pom);
						
					}
				}
			}
		}else {
			Pracownik pom=tabP.get(0);
			for(int i=0;i<tabP.size();i++) {
				for(int j=0;j<tabP.size();j++) {
					
					if(tabP.get(i).getNr_dzialu()<tabP.get(j).getNr_dzialu()) {
						pom=tabP.get(i);
						tabP.set(i, tabP.get(j));
						tabP.set(j, pom);
						
					}
				}
			}
		}
	}
	public void sortWypK(boolean kier) {
		if(kier) {
		Klient pom=tabK.get(0);
			for(int i=0;i<tabK.size();i++) {
				for(int j=0;j<tabK.size();j++) {
					
					if(tabK.get(i).getIl_wyp()>tabK.get(j).getIl_wyp()) {
						pom=tabK.get(i);
						tabK.set(i, tabK.get(j));
						tabK.set(j, pom);
						
					}
				}
			}
		}else {
			Klient pom=tabK.get(0);
			for(int i=0;i<tabK.size();i++) {
				for(int j=0;j<tabK.size();j++) {
					
					if(tabK.get(i).getIl_wyp()<tabK.get(j).getIl_wyp()) {
						pom=tabK.get(i);
						tabK.set(i, tabK.get(j));
						tabK.set(j, pom);
						
					}
				}
			}
		}
	}
	
	public void clearFound(int place) {
		switch(place) {
		case 1:
			for(int i=0;i<tabK.size();i++) {
				tabK.get(i).setFound(true);
			}
		break;
		case 2:
			for(int i=0;i<tabP.size();i++) {
				tabP.get(i).setFound(true);
			}
		break;

		case 3:
			for(int i=0;i<tabF.size();i++) {
				tabF.get(i).setFound(true);
			}
		break;

		}
	}
	public void clearFoundF(int place) {
		switch(place) {
		case 1:
			for(int i=0;i<tabK.size();i++) {
				tabK.get(i).setFound(false);
			}
		break;
		case 2:
			for(int i=0;i<tabP.size();i++) {
				tabP.get(i).setFound(false);
			}
		break;

		case 3:
			for(int i=0;i<tabF.size();i++) {
				tabF.get(i).setFound(false);
			}
		break;

		}
	}
	//filmek
	public boolean szukajGatF(String gat) {
		tabFWysz = new ArrayList<Film>();
		boolean founded=false;
		for(int i=0;i<tabF.size();i++) {
			if(tabF.get(i).getGatunek().toLowerCase().equals(gat.toLowerCase())) {
				tabFWysz.add(tabF.get(i));
				founded = true;
			}
		}
		return founded;
		
	}
	
	public boolean szukajCenaF(double min,double max) {
		boolean founded=false;
		tabFWysz = new ArrayList<Film>();
		if(min>max) {
			double pom=min;
			min=max;
			max=pom;
		}
		for(int i=0;i<tabF.size();i++) {
			if(tabF.get(i).getCena()>=min && tabF.get(i).getCena()<=max ) {
				tabFWysz.add(tabF.get(i));
				founded=true;
			}
		}
		return founded;
		
	}
	
	//kliencik
	public boolean szukajImieK(String imie) {
		boolean founded=false;
		tabKWysz= new ArrayList<Klient>();
		for(int i=0;i<tabK.size();i++) {
			if(tabK.get(i).getImie().toLowerCase().equals(imie.toLowerCase())) {
				tabKWysz.add(tabK.get(i));
				founded=true;
			}
		}
		return founded;
		
	}
	public boolean szukajIlWypK(int min,int max) {
		if(min>max) {
			int pom=min;
			min=max;
			max=pom;
		}
		boolean founded=false;
		tabKWysz= new ArrayList<Klient>();
		for(int i=0;i<tabK.size();i++) {
			if(tabK.get(i).getIl_wyp()>=min && tabK.get(i).getIl_wyp()<=max) {
				tabKWysz.add(tabK.get(i));
			}
		}
		return founded;
		
	}
	
	
	
	public void deleteF() {
		kosz.add(tabF.get(current));
		tabF.remove(current);
		this.founded--;
		if(founded==0)clearFound(3);
	}
	//pracowniczek
	public boolean szukajWypP(double min,double max) {
		if(min>max) {
			double pom=min;
			min=max;
			max=pom;
		}
		tabPWysz = new ArrayList<Pracownik>();
		boolean founded=false;
		
		for(int i=0;i<tabP.size();i++) {
			if(tabP.get(i).getWyplata()>=min && tabP.get(i).getWyplata()<=max) {
				tabPWysz.add(tabP.get(i));
				founded=true;
			}
		}
		return founded;
		
	}
	
	
	public boolean szukajDzialP(int nr) {
		boolean founded=false;
		tabPWysz = new ArrayList<Pracownik>();
		for(int i=0;i<tabP.size();i++) {
			if(tabP.get(i).getNr_dzialu()==nr) {
				tabPWysz.add(tabP.get(i));
				founded=true;
			}
		}
		return founded;
		
	}
	
	
	public void sortImieK(boolean kier) {
		if(kier) {
		Klient pom=tabK.get(0);
			for(int i=0;i<tabK.size();i++) {
				for(int j=0;j<tabK.size();j++) {
					
					if(tabK.get(i).getImie().compareToIgnoreCase(tabK.get(j).getImie())>0) {
						pom=tabK.get(i);
						tabK.set(i, tabK.get(j));
						tabK.set(j, pom);
						
					}
				}
			}
		}else {
			
			Klient pom=tabK.get(0);
			for(int i=0;i<tabK.size();i++) {
				for(int j=0;j<tabK.size();j++) {
					
					if(tabK.get(i).getImie().compareToIgnoreCase(tabK.get(j).getImie())<0) {
						pom=tabK.get(i);
						tabK.set(i, tabK.get(j));
						tabK.set(j, pom);
						
					}
				}
			}
		}
	}
	public void sortImieP(boolean kier) {
		if(kier) {
		Pracownik pom=tabP.get(0);
			for(int i=0;i<tabP.size();i++) {
				for(int j=0;j<tabP.size();j++) {
					
					if(tabP.get(i).getImie().compareToIgnoreCase(tabP.get(j).getImie())>0) {
						pom=tabP.get(i);
						tabP.set(i, tabP.get(j));
						tabP.set(j, pom);
						
					}
				}
			}
		}else {
			
			Pracownik pom=tabP.get(0);
			for(int i=0;i<tabP.size();i++) {
				for(int j=0;j<tabP.size();j++) {
					
					if(tabP.get(i).getImie().compareToIgnoreCase(tabP.get(j).getImie())<0) {
						pom=tabP.get(i);
						tabP.set(i, tabP.get(j));
						tabP.set(j, pom);
						
					}
				}
			}
		}
	}
	
	public void returnF(int id) {
		for(int i=0;i<tabF.size();i++) {
			if(tabF.get(i).equals(tabK.get(id).wyp_movie(current))) {
				tabF.get(i).setSztuk(tabF.get(i).getSztuk()+1);
			}
		}
		tabK.get(id).returnMovie(current);
		
	
	}
	
	public void showCurrentWysz(int place) {
		switch(place) {
			case 1:
				System.out.println("Tytul:     "+tabFWysz.get(current).getNazwa());
				System.out.println("Gatunek:   "+tabFWysz.get(current).getGatunek());
				System.out.println("Premiera:  "+tabFWysz.get(current).getPremiera());
				System.out.println("Dlugosc:   "+tabFWysz.get(current).getDlugosc());
				System.out.println("Sztuk:     "+tabFWysz.get(current).getSztuk());
				System.out.println("Cena:      "+tabFWysz.get(current).getCena());
			break;
			case 2:
				System.out.println("Id:            "+tabPWysz.get(current).getId());
				System.out.println("Imie:          "+tabPWysz.get(current).getImie());
				System.out.println("Nazwisko:      "+tabPWysz.get(current).getNazwisko());
				System.out.println("Urodzenie:     "+tabPWysz.get(current).getUrodzenie().getDataS());
				System.out.println("Numer Dzialu:  "+tabPWysz.get(current).getNr_dzialu());
				System.out.println("Wypłata:       "+tabPWysz.get(current).getWyplata());
			break;
			case 3:
				System.out.println("Id:                "+tabKWysz.get(current).getId());
				System.out.println("Imie:              "+tabKWysz.get(current).getImie());
				System.out.println("Nazwisko:          "+tabKWysz.get(current).getNazwisko());
				System.out.println("Urodzenie:         "+tabKWysz.get(current).getUrodzenie().getDataS());
				System.out.println("Ilosc wyporzyczen: "+tabKWysz.get(current).getIl_wyp());
			break;
		}
	}
	public void nextWysz(int place) {
		current++;
		switch(place) {
			case 3:
				if(current==tabKWysz.size())current=0;
			break;
			case 2:
				if(current==tabPWysz.size())current=0;
			break;
			case 1:
				if(current==tabFWysz.size())current=0;
			break;
			
		}
		
		
	}
	public void prevWysz(int place) {
		current--;
		switch(place) {
			case 3:
				if(current==-1)current=tabKWysz.size()-1;
			break;
			case 2:
				if(current==-1)current=tabPWysz.size()-1;
			break;
			case 1:
				if(current==-1)current=tabFWysz.size()-1;
			break;
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
}
