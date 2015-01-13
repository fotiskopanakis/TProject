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
		try 
		{
			FileOutputStream out = new FileOutputStream(output);
			FileInputStream fin = new FileInputStream(input);
			
			byte[] curr = new byte[1];
			byte[] prev = new byte[1];
			
			while( fin.read(curr) != -1 )
            {
				if(Arrays.equals(curr, prev))
				{
					prev=curr.clone();
					if(fin.read(curr) != -1)
					{
						int times=Character.getNumericValue(curr[0]);
						for(int i=0;i<times+1;i++)
						{
							out.write(prev); 
						}
					}
					prev=new byte[1];
					continue;
				}
				else
				{
					out.write(curr);
					prev=curr.clone();
					continue;
				}
            }
			
			fin.close();
			out.close();
		}
		catch (Exception e) {e.printStackTrace();} 
	}
	
	public static void write2output(int runLength,FileOutputStream out,byte[] currbyte, int noOfBytes)
	{
		try 
		{
			if(runLength==1)
			{
				out.write(currbyte,0,noOfBytes);
			}
			else if(runLength>1)
			{
				char[] ch = String.valueOf(runLength-2).toCharArray();
				byte[] number = new String(ch).getBytes();
				
				out.write(currbyte,0,noOfBytes);
				out.write(currbyte,0,noOfBytes);
				out.write(number,0,number.length);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
