package pack1;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Window {


	public static void main(String args[]) {
		int daty[]= new int[3],sztuki, logMenu,dzial, id,ilP=1,ilK=0;
		double dl,cena,placa,minD,maxD;
		String tyt="", gat="", log="   ",pass, im,naz,pes,previlages="";
		boolean quit=false,logged[],logout=false,check=true,currOut,modifyOut,szukaj=false;
		char zn;
		logged= new boolean[3];
		Filmoteka magazyn = new Filmoteka();
		Scanner scan= new Scanner(System.in);
		Pattern pesel = Pattern.compile("^[0-9]{2}[0-4]{1}\\d{1}[0-3]{1}\\d{6}");
		Matcher match;
		
		do{
			System.out.println("      [1]Zaloguj sie! \n      [2]Zarejstruj sie!\n      [0]Wyjdz");
			logMenu=(int)(scan.next()).charAt(0)-48;
			
			if(logMenu==2) {
				System.out.print("Imie: ");
				im=scan.next();
				System.out.print("Nazwisko: ");
				naz=scan.next();
				System.out.print("Pesel: ");
				check=true;
				do {
					pes=scan.next();
					match = pesel.matcher(pes);
					if(match.matches())check=false;
					else System.out.println("Zly format peselu");
				}while(check);
				System.out.print("Haslo: ");
				pass=scan.next();
				magazyn.addClient(im,naz,pes,pass,ilK);
				ilK++;
				logMenu=3;
				System.out.println("Pomyslnie Zarejestrowano twoje id to: "+magazyn.getLastId(0));
				
			}else if(logMenu==1){
				System.out.print("id: ");
				log = scan.next();
				System.out.print("Haslo: ");
				pass = scan.next();
				previlages=magazyn.login(log, pass);
				switch(previlages) {
				case "Pracownik":
					logged[0]=true;
					logged[1]=false;
					logged[2]=true;
				break;
				case "Klient":
					logged[0]=true;
					logged[1]=true;
					logged[2]=false;
				break;
				case "Admin":
					logged[0]=true;
					logged[1]=true;
					logged[2]=true;
				break;
				default:
					logged[0]=false;
					logged[1]=false;
					logged[2]=false;
					System.out.println("Nie ma takiego konta!");
				}
			}else {
				System.out.println("Czy napewno chcesz Wyjsc? y/n");
				logMenu=3;
				switch((scan.next()).charAt(0)) {
					case 'y':
						quit=true;
						
					break;
					case'n':
						System.out.println("Wracam!");
						
					break;
					default:
						System.out.println("Wybierz y/n !!!");
				}
			}
			if(!logged[0]) {
				if(logMenu!=3) {
					System.out.println("Bledne dane\n[1]Powtorz  [0]wyjdz");
					switch((scan.next()).charAt(0)) {
					case '0':
						System.out.println("Czy napewno chcesz Wyjsc? y/n");
						switch((scan.next()).charAt(0)) {
							case 'y':
								quit=true;
							break;
							case'n':
								System.out.println("Wracam!");
								
							break;
							default:
								System.out.println("Wybierz y/n !!!");
						}
					break;
					case'1':
					break;
					default:
						System.out.println("Wybierz 1/0 !!!");
					}
				}
			}else {
				
				do {
					System.out.println("Zalogowany jako: "+previlages);
					id=Integer.parseInt(log.substring(2));
					if(logged[2]) {
						System.out.println("[1] Dodaj Klienta: ");
						System.out.println("[2] Dodaj film: ");
						System.out.println("[3] Klienci: ");
						System.out.println("[4] Filmy: ");
						System.out.println("[5] Pracownicy: ");
						System.out.println("[u] Usuniete Filmy: ");
						System.out.println("[s] Szukanie");
						
					}
					if(logged[1] && !logged[2]) {
						System.out.println("[6] Wypozycz: ");
						System.out.println("[7] Wypozyczone: ");
					}
					if(logged[1] && logged[2]) {
						System.out.println("[a] Dodaj Pracownika: ");
						System.out.println("[p] Dodaj plik z bazą: ");
						System.out.println("[z] Stworz plik z bazą: ");
					}
					System.out.println("[9] Edytuj dane: ");
					System.out.println("[0] Wyloguj: ");
					
					switch((scan.next()).charAt(0)) {
					
					case 's':
						szukaj=false;
						System.out.println("[1] Szukaj filmu: ");
						System.out.println("[2] Szukaj Pracownika: ");
						System.out.println("[3] Szukaj Klienta: ");
						magazyn.setCurrent(0);
						switch(scan.next().charAt(0)) {
						case '1':
							System.out.println("Czego szukamy? \n  [1]Gatunek \n  [2]Cena\n ");
							switch(scan.next().charAt(0)) {
								case '1':
									System.out.println("Jakiego gatunku szukamy? ");
									
									szukaj=magazyn.szukajGatF(scan.next());
								break;
								case '2':
									System.out.print("Podaj Przedział w którym szukamy:  \nOD: ");
									minD=Double.parseDouble(scan.next());
									System.out.print("DO: ");
									maxD=Double.parseDouble(scan.next());
									szukaj=magazyn.szukajCenaF(minD,maxD);
								break;
							}
							if(szukaj) {
								currOut=false;
								do {
									magazyn.showCurrentWysz(1); 
									
									System.out.println("[a]PREV  [d]NEXT  [q]QUIT ");
									zn=scan.next().charAt(0);
									switch(zn) {
									case 'a':
										magazyn.prevWysz(1);
									break;
									case 'd':
										magazyn.nextWysz(1);
									break;
									case 'q':
										currOut=true;
									break;
									default:
										System.out.println("zla opcja!");
									}
								}while(!currOut);
							}else System.out.println("Brak danych!");
						break;
						case '2':
							System.out.println("Czego szukamy? \n  [1]Dzial \n  [2]Wyplata");
							switch(scan.next().charAt(0)) {
								case '1':
									System.out.println("Jakiego dzialu szukamy? ");
									szukaj=magazyn.szukajDzialP(Integer.parseInt(scan.next()));
								break;
								case '2':
									System.out.print("Podaj Przedział w którym szukamy:  \nOD: ");
									minD=Double.parseDouble(scan.next());
									System.out.print("DO: ");
									maxD=Double.parseDouble(scan.next());
									szukaj=magazyn.szukajWypP(minD,maxD);
								break;
								
							}
							if(szukaj) {
								currOut=false;
								do {
									magazyn.showCurrentWysz(2); 
									
									System.out.println("[a]PREV  [d]NEXT  [q]QUIT ");
									zn=scan.next().charAt(0);
									switch(zn) {
									case 'a':
										magazyn.prevWysz(2);
									break;
									case 'd':
										magazyn.nextWysz(2);
									break;
									case 'q':
										currOut=true;
									break;
									default:
										System.out.println("zla opcja!");
									}
								}while(!currOut);
							}else System.out.println("Brak danych!");
							
						break;
						case '3':
							System.out.println("Czego szukamy? \n  [1]Imie \n  [2]Ilosc Wyporzyczen");
							switch(scan.next().charAt(0)) {
								case '1':
									System.out.println("Jakiego gatunku szukamy? ");
									
									szukaj=magazyn.szukajImieK(scan.next());
								break;
								case '2':
									System.out.print("Podaj Przedział w którym szukamy:  \nOD: ");
									minD=Double.parseDouble(scan.next());
									System.out.print("DO: ");
									maxD=Double.parseDouble(scan.next());
									szukaj=magazyn.szukajIlWypK((int)minD,(int)maxD);
								break;
							}
							if(szukaj) {
								currOut=false;
								do {
									magazyn.showCurrentWysz(3); 
									
									System.out.println("[a]PREV  [d]NEXT  [q]QUIT ");
									zn=scan.next().charAt(0);
									switch(zn) {
									case 'a':
										magazyn.prevWysz(3);
									break;
									case 'd':
										magazyn.nextWysz(3);
									break;
									case 'q':
										currOut=true;
									break;
									default:
										System.out.println("zla opcja!");
									}
								}while(!currOut);
							}else System.out.println("Brak danych!");
						break;
						}
					break;
					case '1':
						System.out.print("Imie: ");
						im=scan.next();
						System.out.print("Nazwisko: ");
						naz=scan.next();
						System.out.print("Pesel: ");
						check=true;
						do {
							pes=scan.next();
							match = pesel.matcher(pes);
							if(match.matches())check=false;
							else System.out.println("Zly format peselu");
						}while(check);
						System.out.print("Haslo: ");
						pass=scan.next();
						magazyn.addClient(im,naz,pes,pass,ilK);
						ilK++;
						System.out.println("Pomyslnie dodano Klienta o id:  "+magazyn.getLastId(0));
						
					break;
					case '2':
						System.out.print("Tytul: ");
						tyt=scan.next();
						System.out.print("Gatunek: ");
						gat=scan.next();
						System.out.print("Dlugosc: ");
						dl=Double.parseDouble(scan.next());
						if(dl<0)dl*=-1;
						System.out.print("Sztuk: ");
						sztuki=scan.nextInt();
						if(sztuki<0)sztuki*=-1;
						System.out.print("Cena: ");
						cena=scan.nextDouble();
						if(cena<0)cena*=-1;
						System.out.println("Premiera:   ");
						do {
							check=true;
							System.out.print("Dzien: ");
							daty[0]=scan.nextInt();
							if(daty[0]<0 || daty[0]>31)check=false;
							System.out.print("Miesiac: ");
							daty[1]=scan.nextInt();
							if(daty[1]<0 || daty[1]>12)check=false;
							System.out.print("Rok:");
							daty[2]=scan.nextInt();
							if(daty[2]< 1900 || daty[2]>2100)check=false;
							if(!check)System.out.println("Podales zla date!");
						}while(!check);
						
						magazyn.addMovie(tyt, gat, dl, sztuki, cena, daty);
						
						System.out.println("Pomyslnie dodano Film!");
						
					break;
					case '3':
						currOut=false;
						if(magazyn.getSizeOf(2)>0) {
							magazyn.setCurrent(0);
							do {
								
								if(!magazyn.showCurrentK()) {
									System.out.println("Nie znaleziono nic w tym kryterium!");
									
								}
								
								System.out.println("[a]PREV  [s]SORT  [m]MODIFY  [x]DELETE  [d]NEXT  [q]QUIT ");
								zn=scan.next().charAt(0);
								switch(zn) {
								case 'a':
									magazyn.prev(1);
								break;
								case 'd':
									magazyn.next(1);
								break;
								case 'm':
									modifyOut=false;
									do {
										System.out.println("Modyfikuj:");
										System.out.println("[1]Imie  [2]Nazwisko  [3]Pesel  [q]Wroc");
										switch(scan.next().charAt(0)) {
											case '1':
												System.out.print("Imie: ");
												magazyn.modifyK(scan.next(), 1);
											break;
											case '2':
												System.out.print("Nazwisko: ");
												magazyn.modifyK(scan.next(), 2);
											break;
											case '3':
												System.out.print("Pesel: ");
												magazyn.modifyK(scan.next(), 3);
											break;
			
											case 'q':
												modifyOut=true;
											break;
											default: 
												System.out.println("Zla Opcja!: ");
												
										}
										if(!modifyOut)magazyn.showCurrentK();
									}while(!modifyOut);
								break;
								case 'x':
									System.out.println("Czy napewno chcesz ususać? y/n");
									switch(scan.next().charAt(0)) {
										case 'y':
											magazyn.deleteK();
											magazyn.prev(1);
											if(magazyn.getFoundedNumber()==0)magazyn.clearFound(1);
											if(magazyn.getSizeOf(2)==0) {
												currOut=true;
											}
											System.out.println("Usnieto!");
										break;
										case 'n':
											System.out.println("Nie Usnieto!");
										break;
										default:
											System.out.println("Zła opcja!");
									}
								break;
								case 's':
									System.out.print("Jak sortujemy? \n[1] Ilosc wypożyczen(rosnaco)\n");
									System.out.println("[2] Ilosc wypożyczen(malejaco)");
									System.out.println("[3] Imie(malejaco)");
									System.out.println("[4] Imie(rosnaco)");
									switch(scan.next().charAt(0)){
										case'1':
											magazyn.sortWypK(false);
											magazyn.setCurrent(0);
										break;
										case'2':
											magazyn.sortWypK(true);
											magazyn.setCurrent(0);
										break;
										case'3':
											magazyn.sortImieK(false);
											magazyn.setCurrent(0);
										break;
										case'4':
											magazyn.sortImieK(true);
											magazyn.setCurrent(0);
										break;
										default:
											System.out.println("Zla opcja!");
									
									}
								break;
								case 'q':
									currOut=true;
								break;
								
								default:
									System.out.println("Zla opcja!");
								}
							}while(!currOut);
						
						}else System.out.println("Nie mamy żadnych Klientow");
					break;
					
					case '4':
						currOut=false;
						if(magazyn.getSizeOf(1)>0) {
							magazyn.setCurrent(0);
							do {
								
								if(!magazyn.showCurrentF()) {
									System.out.println("Nie znaleziono nic w tym kryterium!");
									
								}
								
								System.out.println("[a]PREV  [s]SORT  [m]MODIFY  [x]DELETE  [d]NEXT  [q]QUIT ");
								zn=scan.next().charAt(0);
								switch(zn) {
								case 'a':
									magazyn.prev(3);
								break;
								case 'd':
									magazyn.next(3);
								break;
								case 'q':
									currOut=true;
								break;
								case 's':
									System.out.print("Jak sortujemy? \n[1] Nazwa(rosnaco)\n");
									System.out.println("[2] Nazwa (malejaco)");
									System.out.println("[3] Czas(rossnaco)");
									System.out.println("[4] Czas(malejaco)");
									switch(scan.next().charAt(0)){
										case'1':
											Filmoteka.sortujPoNazwieRos();
											magazyn.setCurrent(0);
										break;
										case'2':
											Filmoteka.sortujPoNazwie();
											magazyn.setCurrent(0);
										break;
										case'3':
											magazyn.sortCzasF(false);
											magazyn.setCurrent(0);
										break;
										case'4':
											magazyn.sortCzasF(true);
											magazyn.setCurrent(0);
										break;
										default:
											System.out.println("Zla opcja!");
									
									}
								break;
	
								
								case 'x':
									System.out.println("Usun Jeden element y/n Lub wszytsko a");
									switch(scan.next().charAt(0)) {
										case 'y':
											magazyn.deleteF();
											magazyn.prev(2);
											if(magazyn.getFoundedNumber()==0)magazyn.clearFound(3);
											if(magazyn.getSizeOf(3)==0) {
												currOut=true;
												
											}
											magazyn.setCurrent(0);
											System.out.println("Usnieto!");
										break;
										case 'n':
											System.out.println("Nie Usnieto!");
										break;
										case 'a':
											
											int i=0;
											int size=magazyn.getSizeOf(1);
											while(size>i) {
												magazyn.setCurrent(0);
												magazyn.deleteF();
												i++;
											}
											currOut=true;
											System.out.println("usnieto wszytsko!");
										break;
										default:
											System.out.println("Zła opcja!");
									}
								break;
								case 'm':
									modifyOut=false;
									do {
										System.out.println("Modyfikuj:");
										System.out.println("[1]Tytul  [2]Gatunek  [3]Dlugosc  [4]Ilosc  [5]Cena  [q]Wroc");
										switch(scan.next().charAt(0)) {
											case '1':
												System.out.print("Tytul: ");
												magazyn.modifyF(scan.next(), 1);
											break;
											case '2':
												System.out.print("Gatunek: ");
												magazyn.modifyP(scan.next(), 2);
											break;
											case '3':
												System.out.print("Dlugosc: ");
												magazyn.modifyP("", 3,Double.parseDouble(scan.next()));
											break;
											case '4':
												System.out.print("Haslo: ");
												magazyn.modifyP("", 4,Integer.parseInt(scan.next()));
											break;
											case '5':
												System.out.print("Numer dzialu: ");
												magazyn.modifyP("",5,Double.parseDouble(scan.next()));
											break;
											case 'q':
												modifyOut=true;
											break;
											default: 
												System.out.println("Zla Opcja!: ");
												
										}
										if(!modifyOut)magazyn.showCurrentP();
									}while(!modifyOut);
								break;
								default:
									System.out.println("Zla opcja!");
								}
							}while(!currOut);
						
						}else System.out.println("Nie mamy żadnych filmow");
					break;
					case '5':
						currOut=false;
						if(magazyn.getSizeOf(3)>0) {
							magazyn.setCurrent(0);
							do {
								
								if(!magazyn.showCurrentP()) {
									System.out.println("Nie znaleziono nic w tym kryterium!");
									magazyn.clearFound(2);
								}
								
								System.out.println("[a]PREV  [s]SORT  [m]MODIFY  [x]DELETE  [d]NEXT  [q]QUIT ");
								zn=scan.next().charAt(0);
								switch(zn) {
								case 'a':
									magazyn.prev(2);
								break;
								case 'd':
									magazyn.next(2);
								break;
								case 'm':
									modifyOut=false;
									do {
										System.out.println("Modyfikuj:");
										System.out.println("[1]Imie  [2]Nazwisko  [3]Pesel  [4]Numer Dzialu  [5]Wyplata  [q]Wroc");
										switch(scan.next().charAt(0)) {
											case '1':
												System.out.print("Imie: ");
												magazyn.modifyP(scan.next(), 1);
											break;
											case '2':
												System.out.print("Nazwisko: ");
												magazyn.modifyP(scan.next(), 2);
											break;
											case '3':
												System.out.print("Pesel: ");
												magazyn.modifyP(scan.next(), 3);
											break;
								
											case '4':
												System.out.print("Numer dzialu: ");
												magazyn.modifyP("", 5,scan.nextDouble());
											break;
											case '5':
												System.out.print("Wyplata: ");
												magazyn.modifyP("", 6,scan.nextDouble());
											break;
											case 'q':
												modifyOut=true;
											break;
											default: 
												System.out.println("Zla Opcja!: ");
												
										}
										if(!modifyOut)magazyn.showCurrentP();
									}while(!modifyOut);
								break;
								case 'x':
									System.out.println("Czy napewno chcesz ususać? y/n");
									switch(scan.next().charAt(0)) {
										case 'y':
											magazyn.deleteP();
											magazyn.prev(2);
											if(magazyn.getFoundedNumber()==0)magazyn.clearFound(2);
											if(magazyn.getSizeOf(3)==0) {
												currOut=true;
											}
											System.out.println("Usnieto!");
										break;
										case 'n':
											System.out.println("Nie Usnieto!");
										break;
										default:
											System.out.println("Zła opcja!");
									}
								break;
								case 's':
									System.out.print("Jak sortujemy? \n[1] Dzial(rosnaco)\n");
									System.out.println("[2] Dzial(malejaco)");
									System.out.println("[3] Imie(rosnaco)");
									System.out.println("[4] Imie(malejaco)");
									switch(scan.next().charAt(0)){
										case'1':
											magazyn.sortDzialP(false);
											magazyn.setCurrent(0);
										break;
										case'2':
											magazyn.sortDzialP(true);
											magazyn.setCurrent(0);
										break;
										case'3':
											magazyn.sortImieP(false);
											magazyn.setCurrent(0);
										break;
										case'4':
											magazyn.sortImieP(true);
											magazyn.setCurrent(0);
										break;
										default:
											System.out.println("Zla opcja!");
									
									}
								break;
								case 'q':
									currOut=true;
								break;
								
								default:
									System.out.println("Zla opcja!");
								}
							}while(!currOut);
						
						}else System.out.println("Nie mamy żadnych Pracownikow");
					break;
					
					case '6':
						currOut=false;
						if(magazyn.getSizeOf(1)>0) {
							magazyn.setCurrent(0);
							do {
								
								if(!magazyn.showCurrentF()) {
									System.out.println("Nie znaleziono nic w tym kryterium!");
									magazyn.clearFound(3);
								}
								Filmoteka.sortujPoNazwieRos();
								System.out.println("[a]PREV  [w]BORROW  [d]NEXT  [q]QUIT ");
								zn=scan.next().charAt(0);
								switch(zn) {
								case 'a':
									magazyn.prev(3);
								break;
								case 'd':
									magazyn.next(3);
								break;
								case 'w':
									if(magazyn.borrowMovie(id)) {
										System.out.println("Wypożyczono ten film!");
										
									}else {
										System.out.println("Niestety nie mamy aktualnie tego Filmu");
									}
									
								break;
								case 'q':
									currOut=true;
								break;
								
								default:
									System.out.println("Zla opcja!");
								}
							}while(!currOut);
						
						}else System.out.println("Nie mamy żadnych filmow");
			
						
					break;
					case '7':
						currOut=false;
						if(magazyn.getSizeOf(5,id)>0) {
							magazyn.setCurrent(0);
							do {
								
								magazyn.showCurrentB(id);
								
								
								System.out.println("[a]PREV  [w]RETURN  [d]NEXT  [q]QUIT ");
								zn=scan.next().charAt(0);
								switch(zn) {
								case 'a':
									magazyn.prev(5,id);
								break;
								case 'd':
									magazyn.next(5,id);
								break;
								case 'w':
										magazyn.returnF(id);
										System.out.println("Zwrócono ten film!");
										if(magazyn.getSizeOf(5, id)==0)currOut=true;
								break;
								case 'q':
									currOut=true;
								break;
								
								default:
									System.out.println("Zla opcja!");
								}
							}while(!currOut);
						
						}else System.out.println("Nie masz żadnych filmow");
					break;
					case'u':
						currOut=false;
						if(magazyn.getSizeOf(4)>0) {
							magazyn.setCurrent(0);
							do {
								
								if(!magazyn.showCurrentKosz()) {
									System.out.println("Nie znaleziono nic w tym kryterium!");
	
								}
								
								System.out.println("[a]PREV  [s]RETURN  [x]DELETE  [d]NEXT  [q]QUIT ");
								zn=scan.next().charAt(0);
								switch(zn) {
								case 'a':
									magazyn.prev(4);
								break;
								case 'd':
									magazyn.next(4);
								break;
								case 'q':
									currOut=true;
								break;
								case 'x':
									System.out.println("Usun Jeden element y/n Lub wszytsko a");
									switch(scan.next().charAt(0)) {
										case 'y':
											magazyn.deleteKosz();
											magazyn.prev(4);
											if(magazyn.getSizeOf(4)==0) {
												currOut=true;
											}
											System.out.println("Usnieto!");
										break;
										case 'n':
											System.out.println("Nie Usnieto!");
										break;
										case 'a':
											
											int i=0;
											int size=magazyn.getSizeOf(1);
											while(size>i) {
												magazyn.setCurrent(0);
												magazyn.deleteKosz();
												i++;
												
											}
											currOut=true;
											System.out.println("Usnieto wszytsko!");
										break;
										default:
											System.out.println("Zła opcja!");
									}
								break;
								case 's':
									System.out.println("Przywrocic jeden film y/n Lub wszytsko a");
									switch(scan.next().charAt(0)) {
										case 'y':
											magazyn.returnKosz();
											magazyn.prev(4);
											if(magazyn.getSizeOf(4)==0) {
												currOut=true;
											}
											System.out.println("Przywrocono!");
										break;
										case 'n':
											System.out.println("Nie przywrocono!");
										break;
										case 'a':
											int i=0;
											int size=magazyn.getSizeOf(4);
											while(size>i) {
												magazyn.setCurrent(0);
												magazyn.returnKosz();
												i++;
					
											}
											currOut=true;
											System.out.println("Przywrocono Wszystko");
										break;
										default:
											System.out.println("Zła opcja!");
									}
								break;
								
								default:
									System.out.println("Zla opcja!");
								}
							}while(!currOut);
						
						}else System.out.println("Kosz jest pusty");
					break;
					case 'z':
						System.out.println("Czy napewno chcesz zapisac? y/n");
						switch((scan.next()).charAt(0)) {
							case 'y':
								System.out.println("Podaj Nazwe pod którą chcesz zapisać !(UWAGA MOZESZ NADPISAC ISTNIEJACY PLIK!)");
								magazyn.saveFile(scan.next());
							break;
							case'n':
								System.out.println("Wracam!");
							break;
							default:
								System.out.println("Wybierz y/n !!!");
						}
					break;
					case 'p':
						System.out.println("Czy napewno chcesz Wczytać? y/n (UWAGA UTRACISZ AKTUALNE DANE i SESJE !)");
						switch((scan.next()).charAt(0)) {
							case 'y':
								System.out.println("Podaj Nazwe pliku który chcesz wczytac");
								magazyn.readFile(scan.next());
								logout=true;
								ilK=magazyn.getSizeOf(1)+1;
								ilP=magazyn.getSizeOf(2)-1;
							break;
							case'n':
								System.out.println("Wracam!");
							break;
							default:
								System.out.println("Wybierz y/n !!!");
						}
					break;
					case 'a':
						System.out.print("Imie: ");
						im=scan.next();
						System.out.print("Nazwisko: ");
						naz=scan.next();
						System.out.print("Pesel: ");
						check=true;
						do {
							pes=scan.next();
							match = pesel.matcher(pes);
							if(match.matches())check=false;
							else System.out.println("Zly format peselu");
							scan.nextLine();
						}while(check);
						System.out.print("Numer dzialu: ");
						dzial=scan.nextInt();
						System.out.print("Wyplata: ");
						placa=scan.nextDouble();
						System.out.print("Haslo: ");
						pass=scan.next();
						magazyn.addWorker(im,naz,pes,dzial,placa,pass,ilP);
						ilP++;
						System.out.println("Pomyslnie dodano Pracownika o id:  "+magazyn.getLastId(1));
						
					break;
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					case '9':
						currOut=false;
						do {
							System.out.println("ZMIANA DANYCH KONTA");
							System.out.println("   [1]IMIE ");
							System.out.println("   [2]NAZWISKO ");
							System.out.println("   [3]PESEL ");
							System.out.println("   [4]HASLO ");
							System.out.println("   [w]POWROT ");
							if(previlages=="Klient") {
								switch(scan.next().charAt(0)) {
								
								case '1':
									System.out.print("Imie: ");
									magazyn.modifyK(scan.next(), 1);
								break;
								case '2':
									System.out.print("Nazwisko: ");
									magazyn.modifyK(scan.next(), 2);
								break;
								case '3':
									System.out.print("Pesel: ");
									magazyn.modifyK(scan.next(), 3);
								break;
								case'4':
									System.out.print("Podaj Stare Hasło: ");
									
										if(magazyn.checkPassK(scan.next(),id)) {
											System.out.print("Podaj Nowe Hasło: ");
											magazyn.changePassK(scan.next(),id);
										}else System.out.println("podano zle hasło!");
									
									
								break;
								case'w':
									currOut=true;
								break;
								}
							}else {
								switch(scan.next().charAt(0)) {
								
								case '1':
									System.out.print("Imie: ");
									magazyn.modifyP(scan.next(), 1);
								break;
								case '2':
									System.out.print("Nazwisko: ");
									magazyn.modifyP(scan.next(), 2);
								break;
								case '3':
									System.out.print("Pesel: ");
									magazyn.modifyP(scan.next(), 3);
								break;
								case'4':
									System.out.print("Podaj Stare Hasło: ");
									
										if(magazyn.checkPassP(scan.next(),id)) {
											System.out.print("Podaj Nowe Hasło: ");
											magazyn.changePassP(scan.next(),id);
										}else System.out.println("podano zle hasło!");
									
									
								break;
								case'w':
									currOut=true;
								break;
								}
							}
						}while(!currOut);
						currOut=false;
					break;
					case '0':
						logout=true;
						
					break;
					}
				}while(!logout);
				logout=false;
				logged[0]=false;
				System.out.println("WYLOGOWANO!");
			}
			logMenu=10;
		}while(!quit);
		
		System.out.println("Żegnaj");
		scan.close();
	}
}
