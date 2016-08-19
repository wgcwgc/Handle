package wgc.lanchang;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class ReadFileByLines
{
	public static void main(String[] args)throws Exception
	{
		@SuppressWarnings("resource")
		Scanner cin = new Scanner (System.in);
        System.out.println("\t请输入待处理文件的完整目录:");
    	String srcDir = cin.next();
		for(File item : new File(srcDir).listFiles())
	    {
			try
			{
				if(item.isFile())
					readFileByLines(item.toString() );
			}
			catch (Exception e)
			{
				throw new Exception("文件读写异常！");
			}
	    }
	}
	@SuppressWarnings("resource")
	public static void readFileByLines(String fileName1)throws Exception
	{
        File file1 = new File(fileName1);
        if(!file1.exists())
        {
            throw new Exception("待处理目录不存在或者格式输入不合法!");
        }
        BufferedReader reader = null;
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
