import java.io.File;

public class RawCompress 
{
	public static void compress(File input,File output) 
	{
		try 
		{
			FileOutputStream out = new FileOutputStream(output);
			FileInputStream fin = new FileInputStream(input);
			
			byte[] curr = new byte[1];
			byte[] prev = new byte[1];
			int noOfBytes = 0;
			boolean firstTime=true;
			int runLength = 1;
			
			while( (noOfBytes = fin.read(curr)) != -1 )
            {
				if(firstTime)
				{
					prev=curr.clone();
					firstTime=false;
					continue;
				}
				
				if(Arrays.equals(curr, prev))
				{
					if(runLength<9)
					{
						runLength++;
						prev=curr.clone();
					}
					else if(runLength==9)
					{
						write2output(runLength,out,prev,noOfBytes);
						
						prev=curr.clone();
						runLength=1;
					}
					continue;
				}
				else
				{
					write2output(runLength,out,prev,noOfBytes);
					
					prev=curr.clone();
					runLength=1;
					continue;
				}
            }
			
			if(curr.length>0)
			{
				write2output(runLength,out,curr,curr.length);
			}
			
			fin.close();
			out.close();
			
			Main.ratio(input,output);
		} 
		catch (IOException e) {e.printStackTrace();} 
	}
	
	public static void decompress(File input,File output)
	{
	}
}
