/**
 * 
 * ����ʦ 
 * 2016/3/23 16:59:24
 * 1. ȥ���У�����ȥǰ��ո�*********************************************(��ʵ��)
 * 2. ˫���Ż��ߵ������ڵ�ע�ͱ���*****************************************(��ʵ��)
 * 3. ʹ�������з�ʽ����ʹ�ý�����ʽ****************************************(��ʵ��)
 * 4. ����һ���Ƿ���Ŀ¼�ݹ�Ĳ�����Ĭ�ϲ��ݹ�*********************************(��ʵ��)
 * 5. ����ݹ飬�ж����Ŀ¼�Ƿ�������Ŀ¼����Ŀ¼����������***********************(��ʵ��)
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
	        throw new Exception("������Ŀ¼�����ڻ��ʽ���Ϸ�������");
	    }
	    BufferedReader bufferedReader = null;
	    try
	    {
	    	bufferedReader = new BufferedReader(new FileReader(srcFile));
	        System.out.println("\n" + srcFile.getAbsolutePath() + "\tsucceed������");
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
				System.out.println("�ù�����Ҫ����ʵ�ֶ�.c .cpp .java .js .cs��ʽ���ļ������Ż�.");
				System.out.println("�������Կ��е���������ע�ͷ�" + "// ��" + "/*...*/ ��" + "/**...*/" + " �� ///�����.");
				System.out.println("�������빲������������\n\t\t��һ�������Ǵ������ļ�������Ŀ¼(-i)\n"
						+ "\t\t�ڶ��������Ǵ�����ļ������Ŀ¼(-o)\n\t\t�����������ǿ�ѡ����(-r)\n"
						+ "����������Ϊ�Ƿ�ݹ鴦������Ŀ¼,����ݹ�������\"Y\",���Ŀ¼����������Ŀ¼����Ŀ¼,������Ĭ��Ϊ������,��������һ��������ɡ�");
				System.out.println("\n�й�ĳ���������ϸ��Ϣ�������  -h ������");
				System.out.println("-i\t\t\t" + "�������ļ�������Ŀ¼��");
				System.out.println("-o\t\t\t" + "������ļ������Ŀ¼��");
				System.out.println("-r\t\t\t" + "�Ƿ�ݹ鴦������Ŀ¼��");
				System.out.println("-h\t\t\t" + "������");
				System.out.println("\n�ο������ʽ��" + "-i C:\\testIn -o C:\\testOut\\ -r Y.");
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
				throw new Exception("�ļ���д�쳣������");
			}
	    }
		System.out.println("\n\n**********************************************\n" +
				"\t\t�ļ���������ɣ�����\n\t\t��л����ʹ�ã�����\n**********************************************\n");
		Thread.sleep(3000);
	}
}

