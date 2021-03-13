import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Producer extends Thread {
	private Buffer buffer; //atribut de tip Buffer pentru sincronizare
	private ReadableBMPImage image; //atribut de tip imagine care poate fi citita din fisier
	
	//constructor cu parametrii pentru initializarea atributelor
	public Producer( Buffer b,ReadableBMPImage image) {
	this.buffer = b;
	this.image=image;
	}
	
	//metoda pentru rularea thread-ului
	public void run () {
		File f=new File(this.image.getInputFileName()); //se creeaza un File asociat fisierului sursa
		int size=(int)f.length(); //se retine dimensiunea fisierului
		byte[] aux=this.image.readImage(0,size/4,f); //se citeste primul sfert de fisier intr-un vector auxiliar
		buffer.put(aux,this.image.getSize()); //Producer pune in buffer prima parte a fisierului
		
		System.out.println("Producatorul a pus partea:\t" + 1); //se afiseaza mesajul care confirma adaugarea primei parti
	
		try {
			sleep (1000); //threadul intra in sleep 
		} catch (InterruptedException e) { }
		aux=this.image.readImage(size/4,size/4,f); //se citeste al doilea sfert de fisier in vectorul auxiliar
		buffer.put(aux,this.image.getSize()); //Producer pune in buffer a doua parte a fisierului
		System.out.println("Producatorul a pus partea:\t" + 2 ); //se afiseaza mesajul care confirma adaugarea partii a doua
	
		try {
			sleep (1000); //threadul intra in sleep 
		} catch (InterruptedException e) { }
		
		aux=this.image.readImage(2*(size/4),size/4,f); //se citeste al treilea sfert de fisier in vectorul auxiliar
		buffer.put(aux,this.image.getSize());  //Producer pune in buffer a treia parte a fisierului
		System.out.println("Producatorul a pus partea:\t" + 3); //se afiseaza mesajul care confirma adaugarea partii a treia
		
		try {
			sleep (1000); //threadul intra in sleep 
		} catch (InterruptedException e) { }
		aux=this.image.readImage(3*(size/4),size-3*(size/4),f); //se citeste ultimul sfert de fisier in vectorul auxiliar
		buffer.put(aux,this.image.getSize()); //Producer pune in buffer ultima parte a fisierului
		
		System.out.println("Producatorul a pus partea:\t" + 4); //se afiseaza mesajul care confirma adaugarea ultimei parti
		
		try {
			sleep (1000); //threadul intra in sleep 
		} catch (InterruptedException e) { }
	}
	}




