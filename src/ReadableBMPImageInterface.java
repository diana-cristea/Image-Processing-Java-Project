import java.io.File;
// interfata ce va fi implementata de Readabe BMPImage
public interface ReadableBMPImageInterface {
	
	byte[] readImage(int offset, int size,File f); //metoda de citire
}
