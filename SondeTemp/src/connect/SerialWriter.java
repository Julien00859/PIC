package connect;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;

public class SerialWriter implements Runnable{
	OutputStream out;
	 
    public SerialWriter( OutputStream out ) {
      this.out = out;
    }
 
    public void run() {
      try {
        int c = 0;
        while( ( c = System.in.read() ) > -1 ) {
          this.out.write( c );
        }
      } catch( IOException e ) {
        e.printStackTrace();
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
      ( new TwoWaySerialComm() ).connect( "/dev/COM3" );
    } catch( Exception e ) {
      e.printStackTrace();
    }
  }
}
