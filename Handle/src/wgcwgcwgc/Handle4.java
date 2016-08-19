/**
 * 
 * ����ʦ 
 * 2016/3/23 16:59:24
 * 1. ȥ���У�����ȥǰ��ո�*********************************************(��ʵ��)
 * 2. ˫���Ż��ߵ������ڵ�ע�ͱ���*****************************************(ʵ��)
 * 3. ʹ�������з�ʽ����ʹ�ý�����ʽ****************************************(��ʵ��)
 * 4. ����һ���Ƿ���Ŀ¼�ݹ�Ĳ�����Ĭ�ϲ��ݹ�*********************************(��ʵ��)
 * 5. ����ݹ飬�ж����Ŀ¼�Ƿ�������Ŀ¼����Ŀ¼����������***********************(��ʵ��)
 * 
 */

/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		3.4
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgcwgcwgc;
//E:\MyFiles\java\projects\HandleTestIn

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

public class Handle4
{
	public static void readFileByLines(String srcFileName , String targetPath)throws Exception
	{
	    File srcFile = new File(srcFileName);
	    if(!srcFile.exists())
	    {
	        throw new Exception("������Ŀ¼�����ڻ��ʽ���Ϸ�������");
	    }
	    BufferedReader bufferedReader = null;
	    try
	    {
	    	bufferedReader = new BufferedReader(new FileReader(srcFile));
	        System.out.println(srcFile.getAbsolutePath() + "\tsucceed������");
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
			catch(Exception e)
			{
				throw new Exception("Ŀ���ļ������쳣������");
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetpath));
	        String tempString = null;
			boolean bool = false;
	        while((tempString = bufferedReader.readLine()) != null)
	        {
	        	String tempstring = tempString.trim();
	        	String str = null;
//	        	System.out.println(tempString);
	        	if(tempstring.startsWith("//"))
	        	{
	        		continue;
	        	}
	        	else if(tempstring.startsWith("/*") || tempstring.startsWith("/**"))
	        	{
	        		if(!bool)
	        			bool = true;
	        		str =tempString.substring(0, tempString.indexOf("/*"));
	        		if(str.trim().isEmpty())
	        			continue;
	        	}
	        	else if(tempstring.startsWith("*/"))
	        	{
	        		bool = false;
	        		str = tempString.substring(tempString.indexOf("*/") + 2, tempString.length());
	        		if(str.trim().isEmpty())
	        			continue;
	        	}
	        	else if(tempstring.isEmpty())
	        	{
	        		continue;
	        	}
	        	else if(bool && tempstring.contains("*/"))
	        	{
	        			bool = false;
	        			str = tempString.substring(tempString.indexOf("*/") + 2 , tempString.length());
	        			if(str.trim().isEmpty())
	        				continue;
	        	}
	        	else if(!bool && (tempstring.contains("//") || tempstring.contains("/*") || tempstring.contains("/**")))
	        	{
	        		if(tempstring.contains("//"))
	        		{
	        			str = tempString.substring(0, tempString.lastIndexOf("//"));
	        		}
	        		else if(tempstring.contains("/*"))
	        		{
	        			bool = true;
	        			str = tempString.substring(0, tempString.lastIndexOf("/*"));
	        		}
	        		else if(tempstring.contains("/**"))
	        		{
	        			bool = true;
	        			str = tempString.substring(0 , tempString.lastIndexOf("/**"));
	        		}
	        		if(str.trim().isEmpty())
	        			continue;
	        	}
	        	else if(bool && tempstring.contains("//"))
	        	{
	        		continue;
	        	}
	        	else if(bool)
	        	{
	        		continue;
	        	}
	        	else
	        	{
	        		str = tempString;
	        	}
	        	bufferedWriter.write(str + "\r\n");
	        	bufferedWriter.flush();
	        }
	        bufferedWriter.close();
	    }
	    catch(IOException e)
	    {
	    	throw new Exception("�ļ���ȡ�쳣������");
	    }
	    finally
	    {
	    	bufferedReader.close();
	    }
	}
	
	public static List <File> getFileList(String strPath , List <File> filelist , int flag)throws Exception
	{
		File dir = new File(strPath);
		File [] files = dir.listFiles();
		if(files != null)
		{
			for(int i = 0; i < files.length; i++)
			{
				String fileName = files[i].getName();
				if(files[i].isDirectory() && flag == 1)
				{
					getFileList(files[i].getAbsolutePath() , filelist , 0);
				}
				else if(fileName.endsWith(".c") || fileName.endsWith(".cpp") ||
						fileName.endsWith(".java") || fileName.endsWith(".js") ||
						fileName.endsWith(".cs"))
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
//		System.out.print("������������ļ�������Ŀ¼:");
//		String srcPath = cin.next();
		String srcPath = args[0];
//		System.out.print("�������ļ�����󱣴������Ŀ¼:");
//		String targetPath = cin.next();
		String targetPath = args[1];
		int num = 0 , sign = 0;
		while(targetPath.contains(srcPath))
		{
			num ++;
			if(num % 3 == 0)
			{
				System.out.println("�Ƿ��˳�����:(������'1' , ������'0')");
				if(1 == cin.nextInt())
				{
					System.out.println("\n\n**********************************************\n" +
				    		"��л����ʹ�ã�����\n**********************************************\n");
					System.exit(0);
				}
				else
				{
					sign = 0;
				}
			}
			else if(sign == 0)
			{
				System.out.println("�ļ������ı���Ŀ¼�Ǵ������ļ�Ŀ¼������Ŀ¼ ,Ϊ���Ϸ�Ŀ¼ ,���������봦����Ŀ¼:");
				targetPath = cin.next();
			}
		}
		System.out.println("�Ƿ���Ŀ¼��Ƕ�׵��ļ���:(������'1' , ������'0')");
		int flag = cin.nextInt();
		cin.close();
		List <File> fileList = new ArrayList <File>();
		fileList = getFileList(srcPath , fileList , flag);
		for(File item : fileList)
	    {
			try
			{
				if(item.isFile())
					readFileByLines(item.toString() , targetPath);
			}
			catch(Exception e)
			{
				throw new Exception("�ļ���д�쳣������");
			}
	    }
		System.out.println("\n\n**********************************************\n" +
    		"\t\t�ļ���������ɣ�����\n\t\t��л����ʹ�ã�����\n**********************************************\n");
	}
}

