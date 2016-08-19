/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		1.0
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgc;

import java.io.*;
import java.util.*;

public class Handle
{
    public static void main(String argc[])throws Exception
    {
    	//存储目录输入
    	System.out.println("\t请输入文件处理后存储的完整目录,记得后面加反斜杠'\\':");
    	@SuppressWarnings("resource")
		Scanner cin = new Scanner (System.in);
        String destDir = cin.next();
        //待处理目录输入
        System.out.println("\t请输入待处理文件的完整目录:");
    	String srcDir = cin.next();
        File fileSrc = new File(srcDir);
        //判断目录合法
        if(!fileSrc.exists())
        {
            throw new Exception("待处理目录不存在或者格式输入不合法!");
        }
        for(File item : fileSrc.listFiles())
        {
           handleFile(item, destDir);
        }
        //延时3秒，便于观察输出情况
		try
		{
		    Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
		    e.printStackTrace();
		}
		//友好界面提示
        System.out.println("\t\t恭喜!已完成!!!");
    }
    //文件复制
    static void handleFile(File src, String dest) throws Exception
    {
    	//创建文件输入输出流对象
        //FileInputStream fin = new FileInputStream(src);
        //FileOutputStream fou = new FileOutputStream(src.getName());
        //byte [] data = new byte[2048];
        
		//int r;
        //String str = null;
        
//        while((str = fin.read()) != null)
//        {
//            fou.write(data);
//        }
//        
        
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
				//fou.write(str);
			}
        
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
        //关闭文件流
        fin.close();
        fou.close();
		}
        //延时3秒，便于观察输出情况
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

