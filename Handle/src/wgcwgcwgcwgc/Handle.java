/**
 *  2016/3/28 16:23:34
 *  ����ʦ 
 *  1. ���Ŀ¼��������ڣ�Ӧ���Զ�����
 *  2. �������в�������Ϊ0ʱ����Ĭ�ϴ�ӡ������Ϣ
 *  3. ��ʹ��-r����ʱ��û����ȷ�ж����Ŀ¼�Ƿ�����ԴĿ¼����Ŀ¼�����ǰ����Ŀ¼������Դ��Ŀ¼�����˴���
 *  4. ��ʹ��-r����ʱ�����Ŀ¼��ҲҪ��Ӧ������Ŀ¼����ԴĿ¼\temp\22��Ӧ������Ŀ¼\out\2
 * 
 */

/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		4.0
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgcwgcwgcwgc;
import java.io.*;
import java.util.*;
public class Handle{
        public static void main(String[] args) throws IOException {
                File sou = new File("E:\\MyFiles\\java\\projects\\HandleFinal\\HandleTestIn");  //ԴĿ¼
                File des = new File("E:\\MyFiles\\java\\projects\\HandleFinal\\HandleTestOut");  //Ŀ��Ŀ¼
                if(!des.exists())
                {
                        des. mkdirs();
                }
                ArrayList<File> al = new ArrayList<File>();
                fileToList(sou, al);
                for(File file:al)  //��һ���ļ��͸���һ��
                {
                        writeToFile( des,  file);
                }
                
        }
        public static void fileToList(File sou, ArrayList<File> al )  //ԴĿ¼���ļ���д��������
        {
                File[] files = sou.listFiles();  //���ļ��е������У��������
                for(File file:files)
                {
                        if(file.isDirectory()) //������ļ���
                        {
                                fileToList(file,al);  //�ݹ���ã�ȡ����Ŀ¼����ļ�
                        }                 
                                if(file.getName().endsWith(".java"))
                                        al.add(file);                 
                }
        }
        public static void writeToFile( File des,File file) throws IOException  //�Ѽ������ļ�д��Ŀ��Ŀ¼��
        {
                BufferedReader bufr = new BufferedReader(new FileReader(new File(file.getAbsolutePath())));   
                //�����ļ����·��� File file = new File(File ·��,String �ļ���)
                BufferedWriter bufw = new BufferedWriter(new FileWriter(new File(des,file.getName().replace(".java", ".txt"))));
                String line = null;
                while((line = bufr.readLine())!=null)
                {
                        bufw.write(line);
                        bufw.newLine();
                        bufw.flush();
                }
                bufw.close();
                bufr.close();                
        }

}
