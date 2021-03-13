import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public abstract class BMPImage {
	
	//Acest obiect va contine continutul imaginii

	//Numele (au calea completa) a fisierului ce contine imaginea initiala
	private String inputFileName;
	
	////Numele (au calea completa) a fisierului ce va contine imaginea finala
	private String outputFileName;
	
	//vector cu elemente de tip byte in care se va citi imaginea din fisier
	protected byte[] data;
	
	//intreg ce va retine dimensiunea fisierului cu imaginea
	private int size;
	
	//constructor fara parametri
	public BMPImage() {
		super(); //apelarea constructorului clasei pe care o mosteneste
		this.setData(null); //seteaza vectorul ca fiind null
		this.inputFileName = null; //seteaza numele fisierului de intrare ca null
		this.outputFileName = null;//seteaza numele fisierului de iesire ca null
	}
	
	//constructor cu 2 parametri -> numele fisierului de intrare si de iesire
	public BMPImage(String inputFileName, String outputFileName) { 
		super(); //apelarea constructorului clasei pe care o mosteneste
		this.setData(null); //seteaza continutul vectorului de date null
		this.inputFileName = inputFileName; //seteaza numele fisierului de intrare ca cel primit ca parametru
		this.outputFileName = outputFileName; //seteaza numele fisierului de iesire ca cel primit ca parametru
	}
	
	//constructor cu 3 parametri -> numele fisierului de intrare si de iesire si vector de date
	public BMPImage(byte[] data, String inputFileName, String outputFileName) {
		super();//apelarea constructorului clasei pe care o mosteneste 
		this.setData(data); //seteaza vectorul de date ca cel primit ca parametru
		this.inputFileName = inputFileName;//seteaza numele fisierului de intrare ca cel primit ca parametru
		this.outputFileName = outputFileName;//seteaza numele fisierului de iesire ca cel primit ca parametru
	}

	//metoda ce returneaza numele fisierului de intrare
	public String getInputFileName() {
		return inputFileName;
	}
	
	//metoda ce seteaza numele fisierului de intrare
	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}
	
	//metoda ce returneaza numele fisierului de iesire
	public String getOutputFileName() {
		return outputFileName;
	}
	
	//metoda ce seteaza numele fisierului de iesire
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	
	//metoda ce returneaza vectorul de date al imaginii
	public byte[] getData() {
		return data;
	}
	
    //metoda ce seteaza vectorul de date ca fiind egal cu cel primit ca parametru
	public void setData(byte[] data) {
		this.data = data;
	}
	
	//metoda in care se seteaza vectorul de date pe portiuni, pe masura ce datele sunt citite
	//primeste ca prim parametru cate 1/4 din imaginea citita in threadul Producer
	//al doilea parametru reprezinta offset-ul de care trebuie tinut cont cand adaugam o noua portiune de vector in vectorul principal
	public void setData(byte[] data1,int offset) {
		        if(offset==0) this.data=new byte[size]; //daca suntem la prima adaugare, alocam un spatiu corespunzator pentru vectorul de date
			    int j=0; //initializam contorul ce parcurge subvectorul cu 0 
			    for(int k=offset;k<offset+data1.length;k++){ //pozitionam cursorul pentru adaugarea noului subvector in functie de offset
	                if(k<this.size) //verificam ca nu am ajuns la ultima pozitie
	                	this.data[k]=data1[j]; //se insereaza subvectorul in vectorul principal
			        j++; //se incrementeaza contorul corespunzator subvectorului
			    }   
	}
	
	//metoda ce returneaza marimea fisierului ce contine imaginea
	public int getSize() {
		return size;
	}
	
	//metoda ce seteaza marimea fisierului ce contine imaginea
	public void setSize(int size) {
		this.size = size;
	}
}
