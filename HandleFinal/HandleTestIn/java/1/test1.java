package wgc.lanchang;
import java.awt.List;
import java.io.File;
public class test1
{
	public static void main(String[] args)
	{
			String str = "1234567	 ";
			System.out.println(str.trim());
	}
	public static List getFileList(String strPath)
	{
		File dir = new File(strPath);
		File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
		List filelist = null;
		if (files != null)
		{
			for (int i = 0; i < files.length; i++)
			{
				String fileName = files[i].getName();
				if (files[i].isDirectory())
				{ // 判断是文件还是文件夹
					getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
				}
				else if (fileName.endsWith("avi"))
				{ // 判断文件名是否以.avi结尾
					String strFileName = files[i].getAbsolutePath();
					System.out.println("---" + strFileName);
//					filelist.add(files[i]);
				}
				else
				{
					continue;
				}
			}
		}
		return filelist;
	}
}
