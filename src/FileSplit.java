import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class FileSplit {
	public static void splitFile(File f) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(f));
		FileOutputStream out;
		String name = f.getName();
		int partCounter = 1;
		int sizeOfFiles = 1024 * 1024;// 1MB
		byte[] buffer = new byte[sizeOfFiles];
		int tmp = 0;
		while ((tmp = bis.read(buffer)) > 0) {
			File newFile = new File(f.getParent() + "\\" + name + "."
					+ String.format("%03d", partCounter++));
			newFile.createNewFile();
			out = new FileOutputStream(newFile);
			out.write(buffer, 0, tmp);
			out.close();
		}
	}

	public static void main(String[] args) throws IOException {
		splitFile(new File(
				"C://Users//Public//Pictures//Sample Pictures//Desert.jpg"));
	}
}
