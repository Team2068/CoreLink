package first.robot;

import org.wpilib.hardware.bus.SerialPort;

public class CoreLink {
  public final SerialPort coreLinkUSB;

  public CoreLink() {
    // The SerialPort class has not been updated for SystemCore yet so the Port will need to be updated in the future. 
    coreLinkUSB = new SerialPort(115200, SerialPort.Port.USB);
    // enables termination using char "\n"
    coreLinkUSB.enableTermination();
    coreLinkUSB.setTimeout(0.01);
  }

  // attempts to read up to 256 bytes. Returns null if there is no response
  public String read(){
    String response = coreLinkUSB.readString(256);
    return response.isEmpty() ? null : response.stripTrailing();
  }
  
  public void write(String message){
    coreLinkUSB.writeString(message + '\n');
  }

  // returns the number of bytes currently avaliable to read from the serial port
  public int bytesReceived(){
    return coreLinkUSB.getBytesReceived();
  }
}
