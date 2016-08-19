/**
 * 2016/3/23 16:59:24
 * 邱老师 2016/3/23 16:59:24
 * 1. 去空行，不是去前后空格*********************************************
 * 2. 双引号或者单引号内的注释保留*****************************************
 * 3. 使用命令行方式，不使用交互方式****************************************
 * 4. 增加一个是否子目录递归的参数，默认不递归*********************************(实现)
 * 5. 如果递归，判断输出目录是否是输入目录的子目录，是则不允许***********************(实现)
 */
/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		3.0
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgcwgcwgc;
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

public class Handle
{
	public static void readFileByLines(String srcFileName , String targetPath)throws Exception
	{
	    File srcFile = new File(srcFileName);
	    if(!srcFile.exists())
	    {
	        throw new Exception("待处理目录不存在或格式不合法!!!");
	    }
	    BufferedReader bufferedReader = null;
	    try
	    {
	    	bufferedReader = new BufferedReader(new FileReader(srcFile));
	        System.out.println("\t\t文件处理中，请稍候，，，\n\t" + srcFile.getAbsolutePath() + "\tsucceed!!!");
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
				throw new Exception("目标文件创建异常!!!");
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
	        		continue;
	        	}
	        	else if(tempString.startsWith("/*"))
	        	{
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
	        	}
	        	else if(tempString.isEmpty())
	        	{
	        		continue;
	        	}
	        	else if(bool && tempString.contains("//"))
	        	{
	        		continue;
	        	}
	        	else if(!bool && (tempString.contains("//") || tempString.contains("/*")))
	        	{
	        		if(tempString.contains("//"))
	        		{
	        			str = tempString.substring(0, tempString.lastIndexOf("//"));
	        		}
	        		else
	        		{
	        			str = tempString.substring(0, tempString.lastIndexOf("/*"));
	        		}
	        		if(str.trim().isEmpty())
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
	    	throw new Exception("文件读取异常!!!");
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
		System.out.print("请输入待处理文件的完整目录:");
		String srcPath = cin.next();
		System.out.print("请输入文件处理候保存的完整目录:");
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
				throw new Exception("文件读写异常!!!");
			}
	    }
    System.out.println("\n\n**********************************************\n" +
    		"\t\t文件处理已完成!!!\n**********************************************\n");
	}
}
