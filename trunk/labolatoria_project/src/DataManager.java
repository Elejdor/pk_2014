import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

interface ISimpleData
{
	String toCSV();
	void getCSV(String data);
}

class DataManager
{
	static Charset encoding = StandardCharsets.UTF_8;
	
	private DataManager() {}
	
	static void SaveTextFile(String fileName, String text) throws IOException
	{
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName), encoding);
		writer.write(text);
	}
	
	static String LoadTextFile(String fileName) throws IOException
	{
		BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), encoding);
		return reader.readLine();
	}
	
}