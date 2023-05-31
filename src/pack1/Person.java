package pack1;
	
public class Person {
	protected String imie;
	protected String nazwisko;
	protected String pesel;
	protected Data urodzenie;
	protected String upr;
	protected String pass;
	protected boolean found;


	public String getImie() {
		return imie;
	}


	public void setImie(String imie) {
		this.imie = imie;
	}


	public String getNazwisko() {
		return nazwisko;
	}


	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}


	public String getPesel() {
		return pesel;
	}


	public void setPesel(String pesel) {
		this.pesel = pesel;
		int daty[]= new int[3];
		
		if((int)pesel.charAt(2)-48>=2) {
			daty[2]=2000+(((int)pesel.charAt(0)-48)*10)+(int)pesel.charAt(1)-48;
			daty[1]=(((int)pesel.charAt(2)-50)*10)+(int)pesel.charAt(3)-48;
		}else {
			daty[2]=1900+(((int)pesel.charAt(0)-48)*10)+(int)pesel.charAt(1)-48;
			daty[1]=(((int)pesel.charAt(2)-48)*10)+(int)pesel.charAt(3)-48;
		}
		daty[0]=daty[1]=(((int)pesel.charAt(4)-48)*10)+(int)pesel.charAt(5)-48;
		this.urodzenie= new Data(daty[0],daty[1],daty[2]);
	}


	public Data getUrodzenie() {
		return urodzenie;
	}


	public void setUrodzenie(int dzien,int mies,int rok) {
		this.urodzenie = new Data(dzien,mies,rok);
	}


	public String getUpr() {
		return upr;
	}


	public void setUpr(String upr) {
		this.upr = upr;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public boolean isFound() {
		return found;
	}


	public void setFound(boolean found) {
		this.found = found;
	}
}
