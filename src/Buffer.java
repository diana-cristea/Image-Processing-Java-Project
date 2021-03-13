
//clasa folosita pentru sincronizarea celor 2 thread-uri
public class Buffer {
	
	private byte[] d; //vector de bytes ce contine o portiune de imagine
	private int size;  //marimea vectorului ce contine o portiune de imagine
	private boolean available = false; //folosit pe post de flag 
	
	//metoda folosita de threadul Consumer pentru a lua portiunea de imagine citita
	public synchronized byte[] get () {
		while (!available) { //daca datele nu sunt disponibile
			try {
				wait (); // Asteapta producatorul sa puna o valoare
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		}
		this.available = false ; //se schimba valoarea flag-ului
		notifyAll(); //notifica threadurile
		return this.d; //returneaza subvectorul cu datele din imagine
	}
	
	//metoda folosita de threadul Producer pentru pune portiunea de imagine citita
	//are ca si parametri subvectorul citit in threadul Producer
	public synchronized void put (byte [] data, int size ) {
	while (available) { //daca datele nu au fost inca preluate de Consumer
	try {
	wait();
	// Asteapta consumatorul sa preia valoarea
	} catch (InterruptedException e) { 
	e.printStackTrace ();
	}
	}
	this.d = data ; //vectorul data preia valorile vectorului trimis ca parametru
	this.size=size; //se seteaza dimensiunea
	this.available = true; //se schimba valoarea flag-ului
	notifyAll(); //sunt notificate threadurile
	}
	
	//metoda ce returneaza valoarea lui size
	public int getSize() {
		return this.size;
	}
	
	//metoda pentru setarea lui size
	public void setSize(int size) {
		this.size = size;
	}
	}


