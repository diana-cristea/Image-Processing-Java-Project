

public class ProcessableBMPImage extends WritableBMPImage implements ProcessableBMPImageInterface {

	public ProcessableBMPImage() { //constructor fara parametrii
		super(); //apeleaza constructorul superclasei
	}
	
	//constructor ce primeste ca parametru un obiect de tip ReadableBMPImage
	public ProcessableBMPImage(ReadableBMPImage readableImage) {
		
		//apeleaza constructorul superclasei
		super(readableImage.getData(), readableImage.getInputFileName(), readableImage.getOutputFileName());
		
	}
	//constructor ce are ca parametrii numele fisierelor de intrare si iesire
	public ProcessableBMPImage(String inputFileName, String outputFileName) {
		
		//apeleaza constructorul superclasei
		super(inputFileName, outputFileName);
	}
	
	@Override
	public void processImage() { //implementarea metodei de procesare din interfata
		byte[] img = this.getData(); //se salveaza o copie a vectorului de date	
		if (img == null) { //daca vectorul nu contine nicio informatie
			return; //se intoarce in programul principal
		}
		for(int i=54;i<img.length;i++){ //pentru fiecare byte, cu exceptia primilor 54 care contin headerul imaginii
            img[i]=(byte)(255-(img[i]& 0xff)); //fiecare valoare devine 255 - valoarea initiala unsigned, aceasta diferenta este convertita apoi in tipul byte
                                               // img[i]&0xff face din signed byte, unsigned
		}
	    this.setData(img); //vectorul de date ia valorile vectorului procesat
	}

}
