
public class Query {

	private String query;
	private String IP;
	private Types type;
	
	public Query(String s) {
		query = s;
		IP = "";
	}
	
	public void handleQuery() {
		String[] f = query.split("[ ]+");
		if (f[1].equals("regPeer")) {
			type = Types.PEER_REG;
			IP = f[2];
		}
		else
			type = Types.GET_LIST_OF_PEERS;
	}
	
	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	public String getIP() {
		return IP;
	}
		
}
