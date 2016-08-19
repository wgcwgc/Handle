/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		2.0
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgcwgc;

import java.io.*;
import java.util.*;

public class Handle
{
	public static List <File> getFileList(String strPath , List <File> filelist)
	{
		File dir = new File(strPath);
		File [] files = dir.listFiles();
		if(files != null)
		{
//			System.out.println(filelist.size());
			for(int i = 0; i < files.length; i++)
			{
				String fileName = files[i].getName();
				if(files[i].isDirectory())
				{
					if(!files[i].exists())
						new File(files[i].getName()).mkdir();
//					filelist.add(files[i]);
					getFileList(files[i].getAbsolutePath() , filelist);
				}
				else if(fileName.endsWith("c") ||
						fileName.endsWith("cpp") ||
						fileName.endsWith("java") ||
						fileName.endsWith("js") ||
						fileName.endsWith("cs") ||
						fileName.endsWith("in") ||
						fileName.endsWith("txt"))
				{
//					String strFileName = files[i].getAbsolutePath();
					System.out.println("\t\t" + files[i].getAbsolutePath() + "，，，");
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
        System.out.println("\t请输入待处理文件的完整目录:");
    	String srcDir = cin.next();
    	cin.close();
		List <File> fileList = new ArrayList<File>();
		fileList = getFileList(srcDir , fileList);
		if(fileList == null || fileList.size() < 1)
			System.out.println("文件异常！！！");
		else
			for(File item : fileList)
		    {
				try
				{
					if(item.isFile())
						readFileByLines(item.toString());
				}
				catch (Exception e)
				{
					throw new Exception("文件读写异常！");
				}
		    }
        System.out.println("**********************************************\n" +
        		"\t\t文件处理已完成!!!\n**********************************************\n");
	}
	public static void readFileByLines(String fileName1)throws Exception
	{
        File file = new File(fileName1);
        if(!file.exists())
        {
            throw new Exception("待处理目录不存在或者格式输入不合法!");
        }
        BufferedReader reader = null;
        try
        {
        	System.out.println("\t\t文件处理中，请稍后，，，");
    		try
    		{
    		    Thread.sleep(1000);
    		}
    		catch (InterruptedException e)
    		{
    			throw new Exception("延时程序出错!");
    		}
            reader = new BufferedReader(new FileReader(file));
            File file2 = new File(file.getName());
            if(!file2.exists())
            {
            	file2.createNewFile();
            }
            System.out.println(file2.getName());
//            FileWriter fileWriter = new FileWriter(file2.getName() , true);
            FileWriter fileWriter = new FileWriter(file2.getName());
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            String tempString = null;
//            int line = 1;
			boolean bool = false;
            while ((tempString = reader.readLine()) != null)
            {
            	tempString = tempString.trim();
            	String str = null;
            	if(tempString.startsWith("//"))
            	{
//            		System.out.println("00000" + line ++ + ": ");
            		continue;
            	}
            	else if(tempString.startsWith("/*"))
            	{
            		if(!bool)
            			bool = !bool;
//            		System.out.print("11111" + line ++ + ": ");
            		str = tempString.substring(0, tempString.indexOf("/*"));
//            		System.out.println(str);
            		str += "\r\n";
//            		bufferWriter.write(str);
            	}
            	else if(tempString.startsWith("*/"))
            	{
            		bool = false;
//            		System.out.print("22222" + line ++ + ": ");
            		str = tempString.substring(tempString.indexOf("*/"), tempString.length());
//            		System.out.println(tempString.substring(tempString.indexOf("*/"), tempString.length()));
            		str += "\r\n";
//            		bufferWriter.write(str);
            	}
            	else if(tempString.isEmpty())
            	{
//            		System.out.println("33333" + line ++ + ": ");
            		continue;
            	}
            	else if(tempString.contains("//") && bool)
            	{
//            		System.out.println("44444" + line ++ + ": ");
            		continue;
            	}
            	else if(!bool && (tempString.contentEquals("//") || tempString.contentEquals("/*")))
            	{
//            		System.out.println("55555" + line ++ + ": ");
            		if(tempString.contentEquals("//"))
            		{
            			str = tempString.substring(0, tempString.lastIndexOf("//"));
//            			System.out.println(tempString.substring(0, tempString.lastIndexOf("//")));
            			str += "\r\n";
//            			bufferWriter.write(str);
            		}
            		else
            		{
            			str = tempString.substring(0, tempString.lastIndexOf("/*"));
//            			System.out.println(tempString.substring(0, tempString.lastIndexOf("/*")));
            			str += "\r\n";
//            			bufferWriter.write(str);
            		}
            	}
            	else if(bool)
            	{
//            		System.out.println("666666" + line ++ + ": ");
            		continue;
            	}
            	else
            	{
//            		System.out.println("77777" + line ++ + ": ");
            		str = tempString;
//            		System.out.println(str);
            		str += "\r\n";
//            		bufferWriter.write(str);
            	}
            	bufferWriter.write(str);
            }
            bufferWriter.close();
        }
        catch (IOException e)
        {
        	throw new Exception("文件读取异常！");
        }
        finally
        {
            try
            {
				reader.close();
			}
            catch (IOException e)
            {
            	throw new Exception("文件关闭异常！");
			}
        }
    }
}
