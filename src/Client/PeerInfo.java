package Client;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public class PeerInfo implements Serializable {
	private String ipAddress;
	
	public PeerInfo() {
		
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}