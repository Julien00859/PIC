package julien2;

public class Main {
	public static void main(String[] args) {
		
		Temperature temp = new Temperature();
		PIC pic = new PIC(temp);
		Thread th = new Thread(pic);
		th.start();
		
		GUI gui = new GUI(temp,pic);
		gui.setVisible(true);
	}

}
