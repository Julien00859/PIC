package connect;

import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class TwoWaySerialComm {
	 void connect( String portName ) throws Exception {
		    CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier( portName );
		    if( portIdentifier.isCurrentlyOwned() ) {
		      System.out.println( "Error: Port is currently in use" );
		    } else {
		      System.out.println("test de conception");
		      int timeout = 2000;
		      CommPort commPort = portIdentifier.open( this.getClass().getName(), timeout );
		 
		      if( commPort instanceof SerialPort ) {
		        SerialPort serialPort = ( SerialPort )commPort;
		        serialPort.setSerialPortParams( 9600,
		                                        SerialPort.DATABITS_8,
		                                        SerialPort.STOPBITS_1,
		                                        SerialPort.PARITY_NONE );
		 
		        InputStream in = (InputStream) serialPort.getInputStream();
		        OutputStream out = (OutputStream) serialPort.getOutputStream();
		 
		        new Thread( new SerialReader( in ) ).start();
		        new Thread( new SerialWriter( out ) ).start();
		 
		      } else {
		        System.out.println( "Error" );
		      }
		    }
	 }
}
