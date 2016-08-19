/**
 * 
 * 邱老师 
 * 2016/3/23 16:59:24
 * 1. 去空行，不是去前后空格*********************************************(已实现)
 * 2. 双引号或者单引号内的注释保留*****************************************(已实现)
 * 3. 使用命令行方式，不使用交互方式****************************************(已实现)
 * 4. 增加一个是否子目录递归的参数，默认不递归*********************************(已实现)
 * 5. 如果递归，判断输出目录是否是输入目录的子目录，是则不允许***********************(已实现)
 * 
 */

/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		3.9
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgcwgcwgc;
import java.io.*;
import java.util.*;

public class Handle9
{
	public static void readFileByLines(String srcFileName , String targetPathName)throws Exception
	{
	    File srcFile = new File(srcFileName);
	    BufferedReader bufferedReader = null;
	    try
	    {
	    	bufferedReader = new BufferedReader(new FileReader(srcFile));
	        System.out.println("\n" + srcFile.getAbsolutePath() + "\tsucceed！！！");
	        targetPathName = targetPathName + "\\"+ srcFile.getName();
			File targetpath = new File(targetPathName);
			if(!targetpath.getParentFile().exists())
				targetpath.getParentFile().mkdirs();
			try
			{
				targetpath.createNewFile();
			}
			catch(Exception e)
			{
				System.out.println("\n目标文件创建异常！！！");
				System.exit(0);
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetpath));
	        String tempString = "";
	        boolean bool = false;
	        while((tempString = bufferedReader.readLine()) != null)
	        {
	        	boolean boob = false;
	        	String tempstring = tempString;
	        	String str = "";
	        	if(tempString.trim().isEmpty())
	        		continue;
	        	else
	        	{
	        		tempstring += '$';
	       			char [] string = tempstring.toCharArray();
	       			for(int i = 0;i < string.length - 1 && string[i] != '$';i ++)
	       			{
	      				String tempstr = "";
	       				tempstr += string[i];
	       				if(tempstr.equals("\""))
	       				{
	       					if(!boob && !bool)
	       					{
	       						boob = true;
	       						str += string[i];
	       						continue;
	       					}
	       					else if(boob && !bool)
	       					{
	       						boob = false;
	       						str += string[i];
	       						continue;
	       					}
	       					else if(bool)
	       						break;
	       				}
       					tempstr += string[i + 1];
       					if(!bool  && !boob && tempstr.equals("//"))
	      					break;
	       				else if(tempstr.equals("/*"))
	   					{
	       					if(!boob && !bool)
	       					{
	       						bool = true;
	       						i ++;
	       						continue;
	       					}
	       					else if(boob && !bool)
	       					{
	       						str += string[i];
	       					}
	       					else if(bool)
	       						break;
	      				}
	       				else if(tempstr.equals("*/"))
	       				{
	       					if(!boob && bool)
	       					{
	       						bool = false;
	       						i ++;
	       						continue;
	       					}
	       					else if(boob && !bool)
	       						str += string[i];
	       					else if(boob && bool)
	       					{
	       						bool = false;
	       						i ++;
	       						continue;
	       					}
	     				}
	       				else if(!bool)
	       					str += string[i];
       				}
	        	}

	        	if(!str.trim().isEmpty())
	        		bufferedWriter.write(str + "\r\n");
	        	bufferedWriter.flush();
	        }
	        bufferedWriter.close();
	    }
	    catch(IOException e)
	    {
	    	System.out.println("\n文件读取异常！！！");
	    	System.exit(0);
	    }
	    finally
	    {
	    	bufferedReader.close();
	    }
	}
	
	public static List <File> getFileList(String srcPath , List <File> filelist , int flag)throws Exception
	{
		File [] files = new File(srcPath).listFiles();
		if(files != null)
		{
			for(int i = 0; i < files.length; i++)
			{
				String fileName = files[i].getName();
				if(files[i].isDirectory() && flag == 1)
					getFileList(files[i].getAbsolutePath() , filelist , 1);
				else if(fileName.endsWith(".c") || fileName.endsWith(".cpp") ||
						fileName.endsWith(".java") || fileName.endsWith(".js") ||
						fileName.endsWith(".cs"))
					filelist.add(files[i]);
				else
					continue;
			}
		}
		return filelist;
	}
	
	public static void main(String[] args)throws Exception
	{
		String srcPath = "";
		String targetPath = "";
		String string = "";
		int flag = 0;
		if(args.length != 0)
			string = args[0];
		if(string.isEmpty())
		{
			System.out.println("\n输入不能为空！！！");
			System.exit(0);
		}
		else if(string.equalsIgnoreCase("-h") || string.equalsIgnoreCase("-help") || string.equalsIgnoreCase("help") )
		{
			System.out.println("\n\n\t\t\tRemove Blank Lines And Comments\n");
			System.out.println("实现对.c .cpp .java .js .cs格式文件的空行消除以及对其注释符" + " // 、" + "/*...*/ 、" + "/**...*/" + " 和 ///的清除.\n\n选项：");
			System.out.println("\t-i\t" + "待处理文件的输入目录。");
			System.out.println("\t-o\t" + "处理后文件输出目录。");
			System.out.println("\t-r\t" + "是否处理其子目录。");
			System.out.println("\t-h\t" + "帮助。");
			System.out.println("\n参考输入格式：" + "-i C:\\testIn -o C:\\testOut -r");
			System.exit(0);
		}
		else
		{
			if(args.length > 5 || args.length < 4)
			{
				System.out.println("\n输入参数不正确！！！");
				System.exit(0);
			}
			for(int i = 0;i < args.length;i ++)
			{
				if(args[i].equals("-i"))
				{
					srcPath = args[i + 1];
					i ++;
				}
				else if(args[i].equals("-o"))
				{
					targetPath = args[i + 1];
					i ++;
				}
				else if(args[i].equals("-r"))
				{
					flag = 1;
				}
				else
				{
					System.out.println("\n输入格式不正确！！！");
					System.exit(0);
				}
			}
		}
		File fileIn = new File(srcPath);
		File fileOut = new File(targetPath);
		if(!fileIn.isDirectory() && !fileIn.isFile() && !fileIn.exists())
		{
			System.out.println("\n输入目录不存在或者格式不正确！！！");
			System.exit(0);
		}
		if(!fileOut.isDirectory() && !fileOut.isFile() && targetPath.contains(srcPath))
		{
			System.out.println("\n输出目录不正确！！！");
			System.exit(0);
		}
		System.out.println("\n\n");
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
				System.out.println("\n文件输出目录不正确！！！");
				System.exit(0);
			}
	    }
		System.out.println("\n\n**********************************************\n" +
				"\t\t文件处理已完成！！！\n\t\t感谢您的使用！！！\n**********************************************\n");
	}
}

