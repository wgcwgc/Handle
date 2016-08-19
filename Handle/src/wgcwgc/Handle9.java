/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		2.9
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgcwgc;
//E:\MyFiles\java\projects\HandleTest

//E:\MyFiles\java\projects\HandleTestOut

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Handle9
{
	public static void readFileByLines(String srcFileName , String targetPath)throws Exception
	{
	    File srcFile = new File(srcFileName);
	    if(!srcFile.exists())
	    {
	        throw new Exception("������Ŀ¼�����ڻ��ʽ���Ϸ�!!!");
	    }
	    BufferedReader bufferedReader = null;
	    try
	    {
	    	bufferedReader = new BufferedReader(new FileReader(srcFile));
	        System.out.println("\t\t�ļ������У����Ժ򣬣���\n\t" + srcFile.getAbsolutePath() + "\tsucceed!!!");
			targetPath = targetPath + "\\"+ srcFile.getName();
			File targetpath = new File(targetPath);
			if(!targetpath.getParentFile().exists())
			{
				targetpath.getParentFile().mkdirs();
			}
			try
			{
				targetpath.createNewFile();
			}
			catch (Exception e)
			{
				throw new Exception("Ŀ���ļ������쳣!!!");
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetpath));
	        String tempString = null;
			boolean bool = false;
	        while((tempString = bufferedReader.readLine()) != null)
	        {
	        	tempString = tempString.trim();
	        	String str = null;
	        	if(tempString.startsWith("//"))
	        	{
	        		System.out.println("1" + str);
	        		continue;
	        	}
	        	else if(tempString.startsWith("/*"))
	        	{
	        		System.out.println("2" + str);
	        		if(!bool)
	        			bool = !bool;
	        		str =tempString.substring(0, tempString.indexOf("/*"));
	        		if(str.trim().isEmpty())
	        			continue;
	        	}
	        	else if(tempString.startsWith("*/"))
	        	{
	        		bool = !bool;
	        		str = tempString.substring(tempString.indexOf("*/") + 2, tempString.length());
	        		if(str.trim().isEmpty())
	        			continue;
	        		System.out.println("3" + str);
	        	}
	        	else if(tempString.isEmpty())
	        	{
	        		System.out.println("4" + str);
	        		continue;
	        	}
	        	else if(bool && tempString.contains("//"))
	        	{
	        		System.out.println("5" + str);
	        		continue;
	        	}
	        	else if(!bool && (tempString.contains("//") || tempString.contains("/*")))
	        	{
	        		System.out.println("6" + str);
	        		if(tempString.contains("//"))
	        		{
	        			str = tempString.substring(0, tempString.lastIndexOf("//") - 2);
	        		}
	        		else
	        		{
	        			str = tempString.substring(0, tempString.lastIndexOf("/*") - 2);
	        		}
	        		if(str.trim().isEmpty())
	        			continue;
	        	}
	        	else if(bool)
	        	{
	        		System.out.println("7" + str);
	        		continue;
	        	}
	        	else
	        	{
	        		System.out.println("8" + str);
	        		str = tempString;
	        	}
	        	bufferedWriter.write(str + "\r\n");
	        	bufferedWriter.flush();
	        }
	        bufferedWriter.close();
	    }
	    catch(IOException e)
	    {
	    	throw new Exception("�ļ���ȡ�쳣!!!");
	    }
	    finally
	    {
	    	bufferedReader.close();
	    }
	}
	
	public static List <File> getFileList(String strPath , List <File> filelist)throws Exception
	{
		File dir = new File(strPath);
		File [] files = dir.listFiles();
		if(files != null)
		{
			for(int i = 0; i < files.length; i++)
			{
				String fileName = files[i].getName();
				if(files[i].isDirectory())
				{
					getFileList(files[i].getAbsolutePath() , filelist);
				}
				else if(fileName.endsWith(".c") || fileName.endsWith(".cpp") ||
						fileName.endsWith(".java") || fileName.endsWith(".js") ||
						fileName.endsWith(".cs") || fileName.endsWith(".txt"))
				{
					filelist.add(files[i]);
				}
				else
				{
					continue;
				}
			}
		}
		return filelist;
	}
	
	public static void main(String[] args)throws Exception
	{
		Scanner cin = new Scanner (System.in);
		System.out.print("������������ļ�������Ŀ¼:");
		String srcPath = cin.next();
		System.out.print("�������ļ�����򱣴������Ŀ¼:");
		String targetPath = cin.next();
		cin.close();
		List <File> fileList = new ArrayList <File>();
		fileList = getFileList(srcPath , fileList);
		for(File item : fileList)
	    {
			try
			{
				if(item.isFile())
					readFileByLines(item.toString() , targetPath);
			}
			catch(Exception e)
			{
				throw new Exception("�ļ���д�쳣!!!");
			}
	    }
    System.out.println("\n\n**********************************************\n" +
    		"\t\t�ļ����������!!!\n**********************************************\n");
	}
}
