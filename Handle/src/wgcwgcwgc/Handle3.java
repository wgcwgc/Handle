/**
 * 
 * 邱老师 
 * 2016/3/23 16:59:24
 * 1. 去空行，不是去前后空格*********************************************(实现)
 * 2. 双引号或者单引号内的注释保留*****************************************
 * 3. 使用命令行方式，不使用交互方式****************************************(已实现)
 * 4. 增加一个是否子目录递归的参数，默认不递归*********************************(已实现)
 * 5. 如果递归，判断输出目录是否是输入目录的子目录，是则不允许***********************(已实现)
 * 
 */

/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		3.3
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

public class Handle3
{
	public static void readFileByLines(String srcFileName , String targetPath)throws Exception
	{
	    File srcFile = new File(srcFileName);
	    if(!srcFile.exists())
	    {
	        throw new Exception("待处理目录不存在或格式不合法！！！");
	    }
	    BufferedReader bufferedReader = null;
	    try
	    {
	    	bufferedReader = new BufferedReader(new FileReader(srcFile));
	        System.out.println(srcFile.getAbsolutePath() + "\tsucceed！！！");
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
				throw new Exception("目标文件创建异常！！！");
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetpath));
	        String tempString = null;
			boolean bool = false;
	        while((tempString = bufferedReader.readLine()) != null)
	        {
	        	String tempstring = tempString.trim();
	        	String str = null;
//	        	System.out.println(tempString);
//	        	if(tempstring.contains("\""))
//	        	{
//	        		//**********************************************************************************
//	        	}
//	        	else 
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
	        			str = tempString.substring(0, tempString.indexOf("//"));
	        		}
	        		else if(tempstring.contains("/*"))
	        		{
	        			bool = true;
	        			str = tempString.substring(0, tempString.indexOf("/*"));
	        		}
	        		else if(tempstring.contains("/**"))
	        		{
	        			bool = true;
	        			str = tempString.substring(0 , tempString.indexOf("/**"));
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
	    	throw new Exception("文件读取异常！！！");
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
//		System.out.print("请输入待处理文件的完整目录:");
//		String srcPath = cin.next();
		String srcPath = args[0];
//		System.out.print("请输入文件处理后保存的完整目录:");
//		String targetPath = cin.next();
		String targetPath = args[1];
		int num = 0 , sign = 0;
		while(targetPath.contains(srcPath))
		{
			num ++;
			if(num % 3 == 0)
			{
				System.out.println("是否退出程序:(是输入'1' , 否输入'0')");
				if(1 == cin.nextInt())
				{
					System.out.println("\n\n**********************************************\n" +
				    		"感谢您的使用！！！\n**********************************************\n");
					System.exit(0);
				}
				else
				{
					sign = 0;
				}
			}
			else if(sign == 0)
			{
				System.out.println("文件处理后的保存目录是待处理文件目录或其子目录 ,为不合法目录 ,请重新输入处理后的目录:");
				targetPath = cin.next();
			}
		}
		System.out.println("是否处理目录内嵌套的文件夹:(是输入'1' , 否输入'0')");
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
				throw new Exception("文件读写异常！！！");
			}
	    }
		System.out.println("\n\n**********************************************\n" +
    		"\t\t文件处理已完成！！！\n\t\t感谢您的使用！！！\n**********************************************\n");
	}
}

