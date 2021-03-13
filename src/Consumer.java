import java.io.FileWriter;
import java.io.IOException;

public class Consumer extends Thread {
	private Buffer buffer; //atribut de tip Buffer pentru sincronizare
	private ReadableBMPImage image ; //atribut de tip imagine care poate fi citita din fisier
	
	//constructor cu parametrii pentru initializarea atributelor
	public Consumer( Buffer b,ReadableBMPImage image) {
	this.buffer = b; //se seteaza valorile atributelor
	this.image=image;
	}
	
	//metoda pentru rularea thread-ului
	public void run () {
	
		//Consumer preia vectorul pus de Producer in buffer
		byte[] s=buffer.get();
	    
		//daca au fost preluate date
		if(s!=null) {this.image.setData(s,0); //se seteaza primul sfert al vectorului de date 
		
		}
		System.out.println("Consumatorul a primit partea :\t"+1); //se afiseaza mesajul corespunzator primirii primei parti
		try {      //threadul intra in sleep 
			sleep(1000);
			} catch (InterruptedException e) { }
		
		//se preia a doua parte de fisier
		s=buffer.get();
		this.image.setData(s, s.length); //se seteaza al doilea sfert de vector cu valori
		System.out.println("Consumatorul a primit partea :\t"+2);  //se afiseaza mesajul corespunzator primirii partii a doua
		try {
			sleep(1000); //threadul intra in sleep 
			} catch (InterruptedException e) { }
		
		//se preia a treia parte de fisier
		s=buffer.get();
		this.image.setData(s,2*s.length ); //se seteaza al treilea sfert de vector cu valori
	
		System.out.println("Consumatorul a primit partea :\t"+3); //se afiseaza mesajul corespunzator primirii partii a treia
		try {
			sleep(1000); //threadul intra in sleep 
			} catch (InterruptedException e) { }
		
		//se preia a patra parte de fisier
		s=buffer.get();
		this.image.setData(s, 3*s.length); //se seteaza ultimul sfert de vector cu valori
		System.out.println("Consumatorul a primit partea :\t"+4); //se afiseaza mesajul corespunzator primirii ultimei partii
		
		try {
			sleep(1000); //threadul intra in sleep 
			} catch (InterruptedException e) { }
	}	
	}
	
	
	

