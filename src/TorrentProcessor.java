import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class TorrentProcessor {
	public static void createTorrent(File file, File sharedFile,
			String announceURL) throws IOException {
		final int pieceLength = 1024 * 1024;
		PrintWriter pw = new PrintWriter(file);
		pw.println(announceURL);
		pw.flush();
		pw.println(sharedFile.getName());
		pw.flush();
		long N = sharedFile.length() / pieceLength;
		int parts = 1;
		for (int i = 0; i < N; i++) {
			pw.println(sharedFile.getName() + "."
					+ String.format("%03d", parts) + " " + (parts++) + " "
					+ pieceLength);
			pw.flush();
		}
		N *= pieceLength;
		pw.println(sharedFile.getName() + "." + String.format("%03d", parts)
				+ " " + (parts++) + " " + (sharedFile.length() - N));
		pw.flush();
	}

	public static void main(String[] args) throws Exception {
		createTorrent(
				new File(
						"C://Users//Public//Pictures//Sample Pictures//desert.torrent"),
				new File(
						"C://Users//Public//Pictures//Sample Pictures//Desert.jpg"),
				"http://example.com/announce");
	}
}
