/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		2.2
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgcwgc;
//E:\MyFiles\java\MyProjects\handleTest\
import java.io.*;
import java.util.*;

public class Handle2
{
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
        	System.out.println("\t\t文件处理中，请稍候，，，");
//    		try
//    		{
//    		    Thread.sleep(100);
//    		}
//    		catch (InterruptedException e)
//    		{
//    			throw new Exception("延时程序出错!");
//    		}
            reader = new BufferedReader(new FileReader(file));
            File file2 = new File(file.getName());
            if(!file2.exists())
            {
            	file2.createNewFile();
            }
            System.out.println(file.getAbsolutePath());
//            FileWriter fileWriter = new FileWriter(file2.getName() , true);
            FileWriter fileWriter = new FileWriter(file2.getName());
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            String tempString = null;
			boolean bool = false;
            while ((tempString = reader.readLine()) != null)
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
            		str += "\r\n";
            	}
            	else if(tempString.startsWith("*/"))
            	{
            		bool = false;
            		str = tempString.substring(tempString.indexOf("*/"), tempString.length());
            		str += "\r\n";
            	}
            	else if(tempString.isEmpty())
            	{
            		continue;
            	}
            	else if(tempString.contains("//") && bool)
            	{
            		continue;
            	}
            	else if(!bool && (tempString.contentEquals("//") || tempString.contentEquals("/*")))
            	{
            		if(tempString.contentEquals("//"))
            		{
            			str = tempString.substring(0, tempString.lastIndexOf("//"));
            			str += "\r\n";
            		}
            		else
            		{
            			str = tempString.substring(0, tempString.lastIndexOf("/*"));
            			str += "\r\n";
            		}
            	}
            	else if(bool)
            	{
            		continue;
            	}
            	else
            	{
            		str = tempString;
            		str += "\r\n";
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
//					if(!files[i].exists())
//						new File(files[i].getName()).mkdir();
//					new File(files[i].getName()).mkdirs();
					getFileList(files[i].getAbsolutePath() , filelist);
				}
				else if(
						fileName.endsWith("c") ||
						fileName.endsWith("cpp") ||
						fileName.endsWith("java") ||
						fileName.endsWith("js") ||
						fileName.endsWith("cs") ||
						fileName.endsWith("in") ||
						fileName.endsWith("class") ||
						fileName.endsWith("exe") ||
						fileName.endsWith("o") ||
						fileName.endsWith("out") ||
						fileName.endsWith("txt")
						)
				{
//					try
//		    		{
//		    		    Thread.sleep(100);
//		    		}
//		    		catch (InterruptedException e)
//		    		{
//		    			throw new Exception("延时程序出错!");
//		    		}
//					System.out.println(files[i].getAbsolutePath() + "\n\t\t" + "文件处理中，请稍候，，，");
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
        System.out.println("\n\n**********************************************\n" +
        		"\t\t文件处理已完成!!!\n**********************************************\n");
	}

}
