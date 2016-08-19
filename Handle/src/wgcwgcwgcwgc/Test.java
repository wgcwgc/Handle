/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		4.0
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgcwgcwgcwgc;

import java.io.File;
import java.io.IOException;

public class Test
{
	public static void main(String[] args) throws IOException
	{
		String string = "E:\\MyFiles\\java\\projects\\SynchronousFinal\\SynchronousTestIn\\java.java";
		File file = new File(string);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getCanonicalPath());
		System.out.println(file.getPath());
	}
}
