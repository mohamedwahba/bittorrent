package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

import javax.management.Query;

public class Peer {

    public void main(String args[]) {
            
            Socket peerSocket = null;
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            
            //Registry server will return Tracker and ports, at which point we can get rid of the following lines of code.
            //TrackerInfo trackerInfo = Registry.getTrackerInfo();
            String tracker = "localhost";
            Integer listeningPort = 19999;
                    
            //read the configuration file
            PeerInfo peerInfo = this.readConfigFile();
            
            try {
                    //create new socket
                    peerSocket = new Socket(tracker,listeningPort);
                    //connect an output stream to it to send data to the server
                    oos = new ObjectOutputStream(peerSocket.getOutputStream());
                    //connect a reader on it to receive data from the server
                    ois = new ObjectInputStream(peerSocket.getInputStream());
                    //send the PeerInfo to the server
                    oos.writeObject(peerInfo);
                    //once the client has an ACK back it's info has been successfully loaded onto the server
                    String ack = (String) ois.readObject();
                    
                    
                    // request the chunk
                    
                    /*
                    //send the server a query
                    String queryFile = "Test1.txt";
                    //Add the query to a message. ID will be returned in the ACK
                    Query query = new Query();
                    query.setQuery(queryFile);
                    query.setId(UUID.randomUUID().toString());
                    query.setType(Query.PEER_QUERY);

                    //send the Query to the server
                    oos.writeObject(query);
                    //wait for the response
                    QueryResultSet queryResultSet = (QueryResultSet) ois.readObject(); */
                    
                    
                    
            } catch(UnknownHostException uhex) {
                    //do something
            } catch(IOException ioex) {
                    //do something
            } catch(ClassNotFoundException cnfex) {
                    //do something
            } finally {
                    //close the reader, writer and socket
                    try {
                            oos.close();
                    } catch(Exception ex) {}
                    try {
                            ois.close();
                    } catch(Exception ex) {}
                    try {
                            peerSocket.close();
                    } catch(Exception ex) {}
            }
    }
    
    private PeerInfo readConfigFile() {
            
            PeerInfo info = new PeerInfo();
            
            //read the config file
            String location = System.getProperty("CONFIG_FILE_LOCATION", "C:\\Temp\\config.xml");
            ConfigFileParser parser = new ConfigFileParser(location);

            //Create the object that will be sent to the server
            info.setIpAddress(parser.getIpAddress());
            //get the list of files in the directory
            DirectoryReader reader = new DirectoryReader(parser.getSharedDirectory());
            if(reader.queryDirectory()) {
                    info.setFiles(reader.getFilesInDirectory());
            }
            //set the unique id on the peer info; it will be returned in the ACK
            info.setId(UUID.randomUUID().toString());
            
            return info;
                            
    }
}
