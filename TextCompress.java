import java.io.File;

public class TextCompress 
{
	public static void compress(File input,File output) 
	{
	}
	
	public static void decompress(File input,File output)
	{
		try 
		{
			CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
			
			FileOutputStream fos = new FileOutputStream(output);
			Writer out = new OutputStreamWriter(fos, encoder);
			
			FileInputStream fis = new FileInputStream(input);
	        InputStreamReader isr = new InputStreamReader(fis, encoder.charset());
	        Reader fin = new BufferedReader(isr);
			
			int curr=0;
			int prev=-1;
			
			while( (curr = fin.read()) != -1 )
            {
				if(curr==prev)
				{
					prev=curr;
					if((curr = fin.read()) != -1 )
					{
						int times=Character.getNumericValue(curr);
						for(int i=0;i<times+1;i++)
						{
							out.write(prev); 
						}
					}
					prev=-1;
					continue;
				}
				else
				{
					out.write(curr);
					prev=curr;
					continue;
				}
            }
			
			fin.close();
			out.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		} 
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
