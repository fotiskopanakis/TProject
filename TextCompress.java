import java.io.File;

public class TextCompress 
{
	public static void compress(File input,File output) 
	{
	}
	
	public static void decompress(File input,File output)
	{
	}
	
	public static void write2output(int runLength,Writer out,int currbyte)
	{
		try 
		{
			if(runLength==1)
			{
				out.write(currbyte);
			}
			else if(runLength>1)
			{
				out.write(currbyte);
				out.write(currbyte);
				out.write(String.valueOf(runLength-2));
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
