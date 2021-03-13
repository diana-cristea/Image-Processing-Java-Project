import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

	//clasa ce descrie obiectele de tip imagine a caror continut poate fi scris in fisier
	public class WritableBMPImage extends ReadableBMPImage implements WritableBMPImageInterface {
	
		public WritableBMPImage() { //constructor fara parametrii
			super(); //apeleaza constructorul superclasei
		}
		
		public WritableBMPImage(String inputFileName, String outputFileName) {
			super(inputFileName, outputFileName); //apeleaza constructorul superclasei
		}
		
		public WritableBMPImage(byte[] image, String inputFileName, String outputFileName) {
			super(image, inputFileName, outputFileName); //apeleaza constructorul superclasei
		}
	
	//Metoda ce creeaza fisierul de iesire cu numele specificat si scrie continutul imaginii in acesta
		@Override
		public void writeImage() {
			
			if (this.getData() == null) { //daca nu sunt date in vector, se intoarce in main
				return;
			}
			try{
				//creeaza un fisier de iesire cu numele dat de utilizator si creeaza o variabila de tip FileOutputStream asociata
				FileOutputStream fs1=new FileOutputStream(new File(this.getOutputFileName())); 
				
				//scrie in fisier continutul vectorului procesat
		        fs1.write(this.getData());
		        
		        //inchide fisierul
		        fs1.close();
			}catch(IOException e){ //trateaza exceptiile
				System.out.println(e);
			}
		}
	}
