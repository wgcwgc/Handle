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
    	//�洢Ŀ¼����
    	System.out.println("\t�������ļ�������洢������Ŀ¼,�ǵú���ӷ�б��'\\':");
    	@SuppressWarnings("resource")
		Scanner cin = new Scanner (System.in);
        String destDir = cin.next();
        //������Ŀ¼����
        System.out.println("\t������������ļ�������Ŀ¼:");
    	String srcDir = cin.next();
        File fileSrc = new File(srcDir);
        //�ж�Ŀ¼�Ϸ�
        if(!fileSrc.exists())
        {
            throw new Exception("������Ŀ¼�����ڻ��߸�ʽ���벻�Ϸ�!");
        }
        for(File item : fileSrc.listFiles())
        {
           handleFile(item, destDir);
        }
        //��ʱ3�룬���ڹ۲�������
		try
		{
		    Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
		    e.printStackTrace();
		}
		//�Ѻý�����ʾ
        System.out.println("\t\t��ϲ!�����!!!");
    }
    //�ļ�����
    static void handleFile(File src, String dest) throws Exception
    {
    	//�����ļ��������������
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
        //�ر��ļ���
        fin.close();
        fou.close();
		}
        //��ʱ3�룬���ڹ۲�������
        try
		{
		    Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
		    e.printStackTrace();
		}
        System.out.println("\t\t���ݴ�����,��ȴ�... ...\n");
        
    }
    
}
