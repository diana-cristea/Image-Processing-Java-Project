import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		String x = new StringBuffer().append("a").append(1).append("b").toString();
		System.out.println(x);
		long startTime = System.nanoTime(); //retine timpul de incepere al programului
		String inputFileName = ""; //initializeaza numele fisierului de intrare
		String outputFileName = ""; //initializeaza numele fisierului de iesire
		int processType = 0; //initializeaza tipul de procesare. Aceste e 0 pentru transformarea identitate si 1 pentru cea negativa

		if(args.length == 3) { //daca sunt introduse toate argumentele 
	        try {
	        	inputFileName = args[0]; //numele fisierului de intrare este dat de primul argument
	        	outputFileName = args[1];//numele fisierului de iesire este dat de al doilea argument
	        	processType = Integer.parseInt(args[2]); //tipul de procesare este dat de al treilea argument
	        	//afiseaza in consola argumentele introduse
	            System.out.println(inputFileName + "  " + outputFileName + "  " +  processType); 
	        } catch (NumberFormatException e) { //exceptie privind ultimul argument care trebuie sa fie un intreg(0 sau 1)
	            System.err.println("Invalid Input: Please enter numbers.");
	        }
	      } else { //daca nu au fost introduse 3 argumente apare mesaj de atentionare 
	         System.err.println("Input Format: inputFileName outputFileName processType");
	      }
        //creez un obiect de tip ReadableBMPImage folsind constructorul cu 2 parametrii: fisierul de intrare si cel de iesire
		ReadableBMPImage initialImage = new ReadableBMPImage(inputFileName, outputFileName);
		
		//creez un obiect de tip Buffer pentru cele 2 threaduri
		Buffer buffer = new Buffer();
		
		//creez thread-ul Producer si ii dau ca parametrii buffer-ul si imaginea initiala
	    Producer ProducerThread = new Producer(buffer,initialImage);
	    
	    //creez thread-ul Consumer si ii dau ca parametrii buffer-ul si imaginea initiala
	    Consumer ConsumerThread = new Consumer(buffer,initialImage);
	    
	    //pornesc threadul Producer
	    ProducerThread.start();
	    
	    //pornesc threadul Consumer
	    ConsumerThread.start();
	    
	    //Folosesc metoda join a threadurilor pentru a astepta terminarea acestora inainte de a trece la procesare
	    ProducerThread.join();
	    ConsumerThread.join();
	    
		long readTime = System.nanoTime(); //retin momentul in care s-a terminat citirea
		System.out.println("Read Execution Time: " + ((readTime - startTime) / 1000000) + "ms"); //afisez durata de citire a imaginii cu threaduri
		
		//creez un obiect de tip ProcessableBMPImage si ii transmit ca parametru imaginea citita
		ProcessableBMPImage finalImage = new ProcessableBMPImage(initialImage);
		
		//daca tipul de procesare dorit este transformarea negativa, atunci aplicam metoda de procesare
		//altfel, continuam cu scrierea imaginii in fisierul de iesire
		if(processType==1) 
			finalImage.processImage();
		
		long processTime = System.nanoTime(); //retinem timpul dupa ce s-a terminat procesarea
		
		//calculam si afisam timpul de procesare
		System.out.println("Process Execution Time: " + ((processTime - readTime) / 1000000) + "ms");
		
		//scriem imaginea in noul fisier
		finalImage.writeImage();
		
		//retinem momentul in care s-a finalizat scrierea
		long writeTime = System.nanoTime();
		
		//calculam si afisam timpul de scriere
		System.out.println("Write Execution Time: " + ((writeTime - processTime) / 1000000) + "ms");
		
		//calculam si afisam timpul total de executie
		System.out.println("Total Execution Time: " + ((writeTime - startTime) / 1000000) + "ms");
	}
	}

