/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		2.0
 * @user		Eliminate the annotation and blank lines		
 * 
 */
package wgcwgc;

public class Test
{

	public static void main(String[] args)
	{
		String str = "asdf//";
		System.out.println("1\t" + str.lastIndexOf("//"));
		System.out.println("2\t" + str.startsWith("//"));
		System.out.println("3\t" + str.endsWith("//"));
		System.out.println("4\t" + str.equals("asdf//"));
		System.out.println("5\t" + str.contains("//"));
		System.out.println("6\t" + str.contentEquals("//"));
	}

}
