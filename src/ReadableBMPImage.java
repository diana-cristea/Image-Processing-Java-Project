
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

 // Clasa din care fac parte obiectele ce pot fi citite dintr-un fisier
public class ReadableBMPImage extends BMPImage implements ReadableBMPImageInterface {
	public ReadableBMPImage() { //constructor fara parametrii
		super();
	}
	//costructor cu 2 parametri: fisierul de intrare si cel de iesire
	public ReadableBMPImage(String inputFileName, String outputFileName) {
		super(inputFileName, outputFileName); //apeleaza constructorul superclasei
	}
	//costructor cu 3 parametri: vectorul ce contine imaginea, fisierul de intrare si cel de iesire
	public ReadableBMPImage(byte[] image, String inputFileName, String outputFileName) {
		super(image, inputFileName, outputFileName); //apeleaza constructorul superclasei
	}

	@Override
	public byte[] readImage(int offset, int size,File f) { //implementarea metodei de citire din interfata
		byte []data0=new byte[size]; //se aloca un spatiu egal cu lungimea transmita ca parametru pentru subvectorul ce va contine o portiune de imagine
	    setSize((int)f.length()); //setez dimensiunea totala a vectorului de date
        try(FileInputStream fs=new FileInputStream(f);){ //se creeaza o variabila de tip FileInputStream folosind fisierului primit ca parametru
        	     fs.skip(offset); //se sare peste datele marcate prin offset, adica peste cele care au fost deja citite in alta etapa
        	                      // offset specifica unde incep datele ce trebuie citite in etapa curenta
        	     fs.read(data0,0,size); //se citeste portiunea specificata de size din fisier in subvector
         }catch (FileNotFoundException e){ //tratarea exceptiilor
        	  e.printStackTrace();
         }catch(IOException e){
    	      System.out.println(e);
    	 }
		 return data0; //returneaza subvectorul din etapa curenta	      
	    
	}

}
