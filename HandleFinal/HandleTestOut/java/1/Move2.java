package wgc.lanchang;
import java.io.*;
import java.util.*;
public class Move2
{
    public static void main(String argc[])throws Exception
    {
    	System.out.println("\t请输入文件处理后存储的完整目录,记得后面加反斜杠'\\':");
    	@SuppressWarnings("resource")
		Scanner cin = new Scanner (System.in);
        String destDir = cin.next();
        System.out.println("\t请输入待处理文件的完整目录:");
    	String srcDir = cin.next();
        File fileSrc = new File(srcDir);
        if(!fileSrc.exists())
        {
            throw new Exception("待处理目录不存在或者格式输入不合法!");
        }
        for(File item : fileSrc.listFiles())
        {
           handleFile(item, destDir);
        }
		try
		{
		    Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
		    e.printStackTrace();
		}
        System.out.println("\t\t恭喜!已完成!!!");
    }
    static void handleFile(File src, String dest) throws Exception
    {
        BufferedWriter fou = null;
        fou = new BufferedWriter(null);
		BufferedReader fin = null;
		String str = null;
		try
		{
			fin = new BufferedReader(new FileReader(src));
			while((str = fin.readLine()) != null)
			{
				System.out.println(str + "\r\n");
			}
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
        fin.close();
        fou.close();
		}
        try
		{
		    Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
		    e.printStackTrace();
		}
        System.out.println("\t\t数据处理中,请等待... ...\n");
    }
}
