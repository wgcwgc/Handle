/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		1.2
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Handle2
{
	public static void main(String[] args)throws Exception
	{
		@SuppressWarnings("resource")
		Scanner cin = new Scanner (System.in);
		
		//待处理目录输入
        System.out.println("\t请输入待处理文件的完整目录:");
    	String srcDir = cin.next();
        
        //存储目录输入
//    	System.out.println("\t请输入文件处理后存储的完整目录:");
//		String destDir = cin.next();

		for(File item : new File(srcDir).listFiles())
	    {
			try
			{
				if(item.isFile())
					readFileByLines(item.toString() );//, destDir);
			}
			catch (Exception e)
			{
				throw new Exception("文件读写异常！");
			}
	    }
	}
	
	@SuppressWarnings("resource")
	//public static void readFileByLines(String fileName1 , String fileName2)throws Exception
	public static void readFileByLines(String fileName1)throws Exception

	{
        File file1 = new File(fileName1);
//        @SuppressWarnings("unused")
//		File file2 = new File(fileName2);
        //判断目录合法
        if(!file1.exists())
        {
            throw new Exception("待处理目录不存在或者格式输入不合法!");
        }
        
        BufferedReader reader = null;
       // BufferedWriter writer = null;
        try
        {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file1));
            FileWriter fileWriter = new FileWriter(file1.getName() , true);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null)
            {
               System.out.println(line + ": " + tempString);
               bufferWriter.write(tempString);
//               bufferWriter.write(tempString, 0, 1);
               line ++;
            }
        }
        catch (IOException e)
        {
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

