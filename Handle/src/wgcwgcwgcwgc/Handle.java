/**
 *  2016/3/28 16:23:34
 *  邱老师 
 *  1. 输出目录如果不存在，应该自动创建
 *  2. 当命令行参数个数为0时，就默认打印帮助信息
 *  3. 当使用-r参数时，没有正确判断输出目录是否属于源目录的子目录，还是把输出目录当成了源码目录进行了处理
 *  4. 当使用-r参数时，输出目录下也要对应创建子目录，如源目录\temp\22对应输出输出目录\out\2
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
                File sou = new File("E:\\MyFiles\\java\\projects\\HandleFinal\\HandleTestIn");  //源目录
                File des = new File("E:\\MyFiles\\java\\projects\\HandleFinal\\HandleTestOut");  //目的目录
                if(!des.exists())
                {
                        des. mkdirs();
                }
                ArrayList<File> al = new ArrayList<File>();
                fileToList(sou, al);
                for(File file:al)  //有一个文件就复制一次
                {
                        writeToFile( des,  file);
                }
                
        }
        public static void fileToList(File sou, ArrayList<File> al )  //源目录的文件名写到集合中
        {
                File[] files = sou.listFiles();  //把文件列到数组中，方便遍历
                for(File file:files)
                {
                        if(file.isDirectory()) //如果是文件夹
                        {
                                fileToList(file,al);  //递归调用，取出子目录里的文件
                        }                 
                                if(file.getName().endsWith(".java"))
                                        al.add(file);                 
                }
        }
        public static void writeToFile( File des,File file) throws IOException  //把集合中文件写到目的目录中
        {
                BufferedReader bufr = new BufferedReader(new FileReader(new File(file.getAbsolutePath())));   
                //创建文件的新方法 File file = new File(File 路径,String 文件名)
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
