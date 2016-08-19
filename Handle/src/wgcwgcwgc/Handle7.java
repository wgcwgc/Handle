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
 * @version		3.7
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgcwgcwgc;

import java.io.*;
import java.util.*;

public class Handle7
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
	        System.out.println("\n" + srcFile.getAbsolutePath() + "\tsucceed！！！");
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
	       					{
	       						break;
	       					}
	       				}
       					tempstr += string[i + 1];
       					if(!bool  && !boob && tempstr.equals("//"))
	      				{
	      					break;
	      				}
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
	       					{
	       						break;
	       					}
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
	       					{
	       						str += string[i];
	       					}
	       					else if(boob && bool)
	       					{
	       						bool = false;
	       						i ++;
	       						continue;
	       					}
	     				}
	       				else if(!bool)
	       				{
	       					str += string[i];
	       				}
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
		boolean isCin = true;
		String srcPath = "";
		String targetPath = "";
		String recursion = "";
		int flag = 0;
		while(isCin)
		{
			String string = cin.nextLine();
			if(string.equalsIgnoreCase("-h") || string.equalsIgnoreCase("-help") || string.equalsIgnoreCase("help") )
			{
				System.out.println("\n\n********************* W E L C O M E *************************\n");
				System.out.println("\t\t\tRemove Blank Lines And Comments\n");
				System.out.println("该工具主要用于实现对.c .cpp .java .js .cs格式的文件处理优化.");
				System.out.println("包括：对空行的消除、对注释符" + "// 、" + "/*...*/ 、" + "/**...*/" + " 和 ///的清除.");
				System.out.println("程序输入共有三个参数：\n\t\t第一个参数是待处理文件的完整目录(-i)\n"
						+ "\t\t第二个参数是处理后文件输出的目录(-o)\n\t\t第三个参数是可选参数(-r)\n"
						+ "第三个参数为是否递归处理其子目录,如果递归请输入\"Y\",输出目录不能是输入目录的子目录,不输入默认为不处理,参数请在一行输入完成。");
				System.out.println("\n有关某个命令的详细信息，请键入  -h 命令名");
				System.out.println("-i\t\t\t" + "待处理文件的完整目录。");
				System.out.println("-o\t\t\t" + "处理后文件输出的目录。");
				System.out.println("-r\t\t\t" + "是否递归处理其子目录。");
				System.out.println("-h\t\t\t" + "帮助。");
				System.out.println("\n参考输入格式：" + "-i C:\\testIn -o C:\\testOut\\ -r Y.");
				System.out.println("\n\n********************* W E L C O M E *************************\n");
//				System.exit(0);
			}
			else if(string.isEmpty())
			{
				continue;
			}
			else
			{
				srcPath = string.substring(string.indexOf("-i") + 3 , string.indexOf("-o") - 1);
				if(string.contains("-r"))
				{
					targetPath = string.substring(string.indexOf("-o") + 3 , string.indexOf("-r") - 1);
					recursion = string.substring(string.indexOf("-r") + 3 , string.length());
					if(recursion.equalsIgnoreCase("yes") || recursion.equalsIgnoreCase("y"))
						flag = 1;
					else
						flag = 0;
				}
				else
				{
					targetPath = string.substring(string.indexOf("-o") + 3 , string.length());
				}
				isCin = false;
			}
		}
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
		Thread.sleep(3000);
	}
}

