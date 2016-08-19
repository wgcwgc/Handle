/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		2.6
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgcwgc;
//E:\MyFiles\java\projects\HandleTest

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Handle6
{
	public static void readFileByLines(String srcFileName)throws Exception
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
	        System.out.println("\t\t�ļ������У����Ժ򣬣���\n\t" + srcFile.getAbsolutePath());
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(srcFile.getName()));
//			System.out.println(srcFile.getCanonicalPath());
			String targetPath = "E:/MyFiles/java/projects/HandleTestOut/";
			System.out.println(targetPath + srcFile);
			System.out.println(srcFile.getName().substring(0, srcFile.getName().indexOf(srcFile.getName().lastIndexOf("."))));
			File targetpath = new File(targetPath);
			if(!targetpath.exists())
				targetpath.mkdirs();
//			String target = targetPath + srcFile.getName();
//			File targ = new File(target);
//			if(!targ.exists())
//				targ.mkdir();
			System.out.println("�����ɹ�������");
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(targetPath) , "GBK");
	        String tempString = null;
			boolean bool = false;
	        while((tempString = bufferedReader.readLine()) != null)
	        {
	        	tempString = tempString.trim();
	        	String str = null;
	        	if(tempString.startsWith("//"))
	        	{
	        		continue;
	        	}
	        	else if(tempString.startsWith("/*"))
	        	{
	        		if(!bool)
	        			bool = !bool;
	        		str = tempString.substring(0, tempString.indexOf("/*"));
	        	}
	        	else if(tempString.startsWith("*/"))
	        	{
	        		bool = false;
	        		str = tempString.substring(tempString.indexOf("*/"), tempString.length());
	        	}
	        	else if(tempString.isEmpty())
	        	{
	        		continue;
	        	}
	        	else if(bool && tempString.contains("//"))
	        	{
	        		continue;
	        	}
	        	else if(!bool && (tempString.contentEquals("//") || tempString.contentEquals("/*")))
	        	{
	        		if(tempString.contentEquals("//"))
	        		{
	        			str = tempString.substring(0, tempString.lastIndexOf("//"));
	        		}
	        		else
	        		{
	        			str = tempString.substring(0, tempString.lastIndexOf("/*"));
	        		}
	        	}
	        	else if(bool)
	        	{
	        		continue;
	        	}
	        	else
	        	{
	        		str = tempString;
	        	}
//	        	bufferedWriter.write(str + "\r\n");
	        	out.write(str + "\r\n");
	        }
	        out.close();
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
				else if(fileName.endsWith("c") || fileName.endsWith("cpp") ||
						fileName.endsWith("java") || fileName.endsWith("js") || fileName.endsWith("cs"))
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
		cin.close();
		List <File> fileList = new ArrayList <File>();
		fileList = getFileList(srcPath , fileList);
		for(File item : fileList)
	    {
			try
			{
				if(item.isFile())
					readFileByLines(item.toString());
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
