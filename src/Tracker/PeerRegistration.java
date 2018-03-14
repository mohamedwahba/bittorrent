package Tracker;

import java.util.ArrayList;
import java.util.Iterator;

import Client.PeerInfo;

public class PeerRegistration {

	ArrayList<PeerInfo> peerSet;

	public PeerRegistration() {
		peerSet = new ArrayList<PeerInfo>();
	}

	public ArrayList<PeerInfo> getPeerSet() {
		return peerSet;
	}

	public void setPeerSet(ArrayList<PeerInfo> peerSet) {
		this.peerSet = peerSet;
	}

	public void addPeer(PeerInfo peerInfo) {
		// need to remove current data if it's already in the set
		String peerName = peerInfo.getIpAddress();
		Iterator<PeerInfo> i = this.peerSet.iterator();
		PeerInfo info = new PeerInfo();
		while (i.hasNext()) {
			info = (PeerInfo) i.next();
			// need to check the IP/port combination so that
			// more than one Peer can run on the same server
			String ip = info.getIpAddress();
			if (ip.equals(peerName)) {
				this.peerSet.remove(info);
				break;
			}
		}
		this.peerSet.add(peerInfo);
	}

	public void removePeer(PeerInfo peerInfo) {
		peerSet.remove(peerInfo);
	}

	public Iterator<PeerInfo> iterator() {
		return this.peerSet.iterator();
	}
}