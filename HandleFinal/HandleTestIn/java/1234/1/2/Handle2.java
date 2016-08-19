package wgc.lanchang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Handle2
{
	@SuppressWarnings("resource")
	public static void main(String[] args)throws Exception
	{
		Scanner cin = new Scanner (System.in);
		
		//待处理目录输入
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
		//友好界面提示
        System.out.println("**********************************************\n" +
        		"\t\t文件处理已完成!!!\n**********************************************\n");
        
        
       
        
	}
	
	public static void readFileByLines(String fileName1)throws Exception
	{
        File file = new File(fileName1);
        //判断目录合法
        if(!file.exists())
        {
            throw new Exception("待处理目录不存在或者格式输入不合法!");
        }
        
        BufferedReader reader = null;
        try
        {
        	
        	System.out.println("\t\t文件处理中，请稍后，，，");
        	
        	 //延时3秒，便于观察输出情况
//    		try
//    		{
//    		    Thread.sleep(1000);
//    		}
//    		catch (InterruptedException e)
//    		{
//    			throw new Exception("延时程序出错!");
//    		}
    		
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
//            int line = 1;
			boolean bool = false;
            while ((tempString = reader.readLine()) != null)
            {
            	tempString = tempString.trim();
            	if(tempString.startsWith("//"))
            	{
//            		System.out.println("00000");
            		continue;
            	}
            	
            	else if(tempString.startsWith("/*"))
            	{
            		if(!bool)
            			bool = true;
//            		System.out.print("11111" + line ++ + ": ");
            		System.out.println(tempString.substring(0, tempString.indexOf("/*")));
            	}
            	else if(tempString.startsWith("*/"))
            	{
            		bool = false;
//            		System.out.print("22222" + line ++ + ": ");
            		System.out.println(tempString.substring(tempString.indexOf("*/"), tempString.length()));
            	}
            	else if(tempString.isEmpty())
            	{
//            		System.out.println("33333");
            		continue;
            	}
            	else if(tempString.contains("//") && bool)
            	{
//            		System.out.println("44444");
            		continue;
            	}
//            	else if((tempString.indexOf("//") > tempString.indexOf("\"") || tempString.indexOf("/*") > tempString.indexOf("\"")) && !bool)
//            	{
//            		String string = "";
//            		char []str = tempString.toCharArray();
//            		int i;
//            		for(i = 1;str[i - 1] != ';' && str[i] != '/' && str[i + 1] != '/' && i < tempString.length() - 2;i ++)
//            		{
//            			string += str[i - 1];
//            		}
//            		string += str[i - 1];
////            			System.out.println("66666" + line ++ + ": ");
//            		System.out.println(string);
//            	}
            	else if(!bool && (tempString.contentEquals("//") || tempString.contentEquals("/*")))
            	{
            		
//            		System.out.println("666666" + line ++ + ": ");
            		if(tempString.contentEquals("//"))
            			System.out.println(tempString.substring(0, tempString.lastIndexOf("//")));
            		else
            			System.out.println(tempString.substring(0, tempString.lastIndexOf("/*")));
            	}
            	else if(bool)
            	{
//            		System.out.println("77777");
            		continue;
            	}
            	else
//            		System.out.println("*****" + line ++ + ": ");
            		System.out.println(tempString);
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







