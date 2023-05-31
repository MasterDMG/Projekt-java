package pack1;

public class Data {
	protected String data;
	private int dzien;
	private int mies;
	private int rok;
	
	public Data(int dzien, int mies,int rok) {
		data=dzien+"."+mies+"."+rok;
		this.dzien=dzien;
		this.mies=mies;
		this.rok=rok;
		
	}
	public String getDataS() {
		return this.data;
	}
	public int getDzien() {
		return dzien;
	}
	public void setDzien(int dzien) {
		this.dzien = dzien;
	}
	public int getMies() {
		return mies;
	}
	public void setMies(int mies) {
		this.mies = mies;
	}
	public int getRok() {
		return rok;
	}
	public void setRok(int rok) {
		this.rok = rok;
	}
}
