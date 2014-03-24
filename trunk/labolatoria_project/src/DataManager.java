import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

interface ISimpleData
{
	String toCSV();
	void getCSV(String data);
}

class DataManager
{
	private DataManager() {}
	static void SaveTextFile(String fileName, String text) throws IOException
	{
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8))
		{
			writer.write(text);			
		}
	}
	
	static String LoadTextFile(String fileName) throws IOException
	{
		String output = "";
		Path path = Paths.get(fileName);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8))
		{
			output = reader.readLine();
		}
		return output;
	}
	
}