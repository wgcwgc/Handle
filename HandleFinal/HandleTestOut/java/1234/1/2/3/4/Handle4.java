package wgc.lanchang;
import java.io.*;
import java.util.*;
public class Handle4
{
	public static void main(String[] args)throws Exception
	{
		Scanner cin = new Scanner (System.in);
        System.out.println("\t请输入待处理文件的完整目录:");
    	String srcDir = cin.next();
    	cin.close();
		for(File item : new File(srcDir).listFiles())
	    {
			try
			{
				if(item.isFile())
					readFileByLines(item.toString());
				else if(item.isDirectory())
				{
					File dir = new File(item.getName());
					if(!dir.exists())
						dir.mkdir();
				}
			}
			catch (Exception e)
			{
				throw new Exception("文件读写异常！");
			}
	    }
        System.out.println("**********************************************\n" +
        		"\t\t文件处理已完成!!!\n**********************************************\n");
	}
	public static void readFileByLines(String fileName1)throws Exception
	{
        File file = new File(fileName1);
        if(!file.exists())
        {
            throw new Exception("待处理目录不存在或者格式输入不合法!");
        }
        BufferedReader reader = null;
        try
        {
        	System.out.println("\t\t文件处理中，请稍后，，，");
    		try
    		{
    		    Thread.sleep(1000);
    		}
    		catch (InterruptedException e)
    		{
    			throw new Exception("延时程序出错!");
    		}
            reader = new BufferedReader(new FileReader(file));
            File file2 = new File(file.getName());
            if(!file2.exists())
            {
            	file2.createNewFile();
            }
            System.out.println(file2.getName());
            FileWriter fileWriter = new FileWriter(file2.getName());
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            String tempString = null;
			boolean bool = false;
            while ((tempString = reader.readLine()) != null)
            {
            	tempString = tempString.trim();
            	if(tempString.startsWith("//"))
            	{
            		continue;
            	}
            	else if(tempString.startsWith("/*"))
            	{
            		if(!bool)
            			bool = !bool;
            		String str = tempString.substring(0, tempString.indexOf("/*"));
            		System.out.println(str);
            		str += "\r\n";
            		bufferWriter.write(str);
            	}
            	else if(tempString.startsWith("*/"))
            	{
            		bool = false;
            		String str = tempString.substring(tempString.indexOf("*/"), tempString.length());
            		System.out.println(tempString.substring(tempString.indexOf("*/"), tempString.length()));
            		str += "\r\n";
            		bufferWriter.write(str);
            	}
            	else if(tempString.isEmpty())
            	{
            		continue;
            	}
            	else if(tempString.contains("//") && bool)
            	{
            		continue;
            	}
            	else if(!bool && (tempString.contentEquals("//") || tempString.contentEquals("/*")))
            	{
            		if(tempString.contentEquals("//"))
            		{
            			String str = tempString.substring(0, tempString.lastIndexOf("//"));
            			System.out.println(tempString.substring(0, tempString.lastIndexOf("//")));
            			str += "\r\n";
            			bufferWriter.write(str);
            		}
            		else
            		{
            			String str = tempString.substring(0, tempString.lastIndexOf("/*"));
            			System.out.println(tempString.substring(0, tempString.lastIndexOf("/*")));
            			str += "\r\n";
            			bufferWriter.write(str);
            		}
            	}
            	else if(bool)
            	{
            		continue;
            	}
            	else
            	{
            		String str = tempString;
            		System.out.println(str);
            		str += "\r\n";
            		bufferWriter.write(str);
            	}
            }
            bufferWriter.close();
        }
        catch (IOException e)
        {
        	throw new Exception("文件读取异常！");
        }
        finally
        {
            try
            {
				reader.close();
			}
            catch (IOException e)
            {
            	throw new Exception("文件关闭异常！");
			}
        }
    }
}
