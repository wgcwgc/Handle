package wgc.lanchang;
import java.io.*;
import java.util.*;
public class Move2
{
    public static void main(String argc[])throws Exception
    {
    	System.out.println("\t�������ļ������洢������Ŀ¼,�ǵú���ӷ�б��'\\':");
    	@SuppressWarnings("resource")
		Scanner cin = new Scanner (System.in);
        String destDir = cin.next();
        System.out.println("\t������������ļ�������Ŀ¼:");
    	String srcDir = cin.next();
        File fileSrc = new File(srcDir);
        if(!fileSrc.exists())
        {
            throw new Exception("������Ŀ¼�����ڻ��߸�ʽ���벻�Ϸ�!");
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
        System.out.println("\t\t��ϲ!�����!!!");
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
        System.out.println("\t\t���ݴ�����,��ȴ�... ...\n");
    }
}
