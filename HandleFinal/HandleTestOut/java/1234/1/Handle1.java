package wgc.lanchang;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Handle1
{
	@SuppressWarnings("resource")
	public static void main(String[] args)throws Exception
	{
		Scanner cin = new Scanner (System.in);
        System.out.println("\t������������ļ�������Ŀ¼:");
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
				throw new Exception("�ļ���д�쳣��");
			}
	    }
        System.out.println("**********************************************\n" +
        		"\t\t�ļ����������!!!\n**********************************************\n");
	}
	public static void readFileByLines(String fileName1)throws Exception
	{
        File file = new File(fileName1);
        if(!file.exists())
        {
            throw new Exception("������Ŀ¼�����ڻ��߸�ʽ���벻�Ϸ�!");
        }
        BufferedReader reader = null;
        try
        {
        	System.out.println("\t\t�ļ������У����Ժ󣬣���");
    		try
    		{
    		    Thread.sleep(1000);
    		}
    		catch (InterruptedException e)
    		{
    			throw new Exception("��ʱ�������!");
    		}
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
            int line = 1;
			boolean bool = false;
            while ((tempString = reader.readLine()) != null)
            {
            	tempString = tempString.trim();
            	if(tempString.startsWith("//"))
            	{
            		System.out.println("00000");
            		continue;
            	}
            	else if(tempString.startsWith("/*"))
            	{
            		if(!bool)
            			bool = true;
            		System.out.print("11111" + line + ":");
            		System.out.println(tempString.substring(0, tempString.indexOf("/*")));
            	}
            	else if(tempString.startsWith("*/"))
            	{
            		bool = false;
            		System.out.print("22222" + line + ":");
            		System.out.println(tempString.substring(tempString.indexOf("*/"), tempString.length()));
            	}
            	else if(tempString.isEmpty())
            	{
            		System.out.println("33333");
            		continue;
            	}
            	else if(tempString.contains("//") && bool)
            	{
            		System.out.println("44444");
            		continue;
            	}
            	else if((tempString.indexOf("//") > tempString.indexOf("\"") || tempString.indexOf("