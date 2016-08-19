package wgc.lanchang;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Handle3
{
	@SuppressWarnings("resource")
	public static void main(String[] args)throws Exception
	{
		Scanner cin = new Scanner (System.in);
        System.out.println("\t请输入待处理文件的完整目录:");
    	String srcDir = cin.next();
		for(File item : new File(srcDir).listFiles())
	    {
			try
			{
				if(item.isFile())
					readFileByLines(item.toString());
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
            reader = new BufferedReader(new FileReader(file));
            File file2 = new File(file.getName());
            if(!file2.exists())
            {
            	file2.createNewFile();
            }
            System.out.println(file2.getName());
            FileWriter fileWriter = new FileWriter(file2.getName() , true);
			@SuppressWarnings("resource")
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			String data = " This content will append to the end of the file";
			bufferWriter.write(data);
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
            			bool = true;
            		System.out.println(tempString.substring(0, tempString.indexOf("/*")));
            	}
            	else if(tempString.startsWith("*/"))
            	{
            		bool = false;
            		System.out.println(tempString.substring(tempString.indexOf("*/"), tempString.length()));
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
            			System.out.println(tempString.substring(0, tempString.lastIndexOf("//")));
            		else
            			System.out.println(tempString.substring(0, tempString.lastIndexOf("/*")));
            	}
            	else if(bool)
            	{
            		continue;
            	}
            	else
            	{
            		System.out.println(tempString);
            	}
            }
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
