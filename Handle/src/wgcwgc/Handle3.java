/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		2.3
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgcwgc;
//E:\MyFiles\java\MyProjects\handleTest\

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
	public static void readFileByLines(String srcFileName)throws Exception
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
            System.out.println("\t\t文件处理中，请稍候，，，\n\t" + srcFile.getAbsolutePath());
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(srcFile.getName()));
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
            		str = tempString.substring(0, tempString.indexOf("/*")) + "\r\n";
            	}
            	else if(tempString.startsWith("*/"))
            	{
            		bool = false;
            		str = tempString.substring(tempString.indexOf("*/"), tempString.length()) + "\r\n";
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
            			str = tempString.substring(0, tempString.lastIndexOf("//")) + "\r\n";
            		}
            		else
            		{
            			str = tempString.substring(0, tempString.lastIndexOf("/*")) + "\r\n";
            		}
            	}
            	else if(bool)
            	{
            		continue;
            	}
            	else
            	{
            		str = tempString + "\r\n";
            	}
            	bufferedWriter.write(str);
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
				else if(fileName.endsWith("c") || fileName.endsWith("cpp") ||
						fileName.endsWith("java") || fileName.endsWith("js") ||
						fileName.endsWith("cs") || fileName.endsWith("in") ||
						fileName.endsWith("class") || fileName.endsWith("exe") ||
						fileName.endsWith("o") || fileName.endsWith("out") ||
						fileName.endsWith("txt"))
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
    	cin.close();
		List <File> fileList = new ArrayList<File>();
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
				throw new Exception("文件读写异常!!!");
			}
	    }
        System.out.println("\n\n**********************************************\n" +
        		"\t\t文件处理已完成!!!\n**********************************************\n");
	}
}
