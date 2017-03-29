
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Scanner;

import gnu.io.CommPortIdentifier;

public class SerialWriter implements Runnable{
	OutputStream out;
	public Scanner sc;
	 
    public SerialWriter( OutputStream out ) {
      this.out = out;
      sc = new Scanner(System.in);
    }
 
    public void run() {
	    while(true){
	    	int val = sc.nextInt();
	      try {
			this.out.write(val);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    }
    }
  
  public static void main( String[] args ) {
    try {
    	Enumeration ports = CommPortIdentifier.getPortIdentifiers();
    	while (ports.hasMoreElements()) {
    	CommPortIdentifier port = (CommPortIdentifier)ports.nextElement();
    	String type;
    	switch (port.getPortType()) {
    	case CommPortIdentifier.PORT_PARALLEL:
    	type = "Parallel"; 
    	break;
    	case CommPortIdentifier.PORT_SERIAL:
    	type = "Serial"; 
    	break;
    	default: /// Shouldn't happen
    	type = "Unknown"; 
    	break;
    	}
    	System.out.println(port.getName() + ": " + type);
    	}
    	
    	System.out.println("test de librairie");
      ( new TwoWaySerialComm() ).connect( "COM4" );
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }
}