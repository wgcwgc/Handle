/**
 * 
 * @author		Administrator
 * @author		wgc
 * @version		3.0
 * @user		Eliminate the annotation and blank lines		
 * 
 */

package wgcwgcwgc;

public class Test
{

	public static void main(String[] args)
	{
//		String str1 = "1234567890";
//		String str2 = "1234567890";
//		System.out.println("1\t" + str1.substring(0, str1.lastIndexOf("78")));
//		System.out.println("2\t" + str1.indexOf("78"));
//		System.out.println(str1.contains(str2));
		
		
		
//		String str3 = "E:\\MyFiles\\java\\projects\\HandleTest";
//		String str4 = "E:\\MyFiles\\java\\projects\\HandleTest\\handle";
//		System.out.println(str4.contains(str3));
		
		
		
//		System.out.println(args.length);
//		for(int i = 0;i < args.length;i ++)
//		System.out.println(args[i].toString());
		
		
		
//		System.out.println("\"");
		
		String tempString = "1234567";
		System.out.println(dispose(tempString));
	}

	public static String dispose(String tempString)
	{
		String str = null;
		boolean bool = false;
		String tempstring = tempString.trim();
//		if(tempstring.isEmpty() || (!bool && tempstring.startsWith("//")) )//空行 和 "//"在前面并且其前没有可见字符
//		{
//			return null;
//		}
//		else 
		if(!bool && tempstring.contains("//") && !tempstring.contains("/*") && !tempstring.contains("*/"))//单行注释中只有"//" 000 和 100
		{
			bool = true;
			if(tempstring.contains("\""))
			{
				if(tempstring.indexOf("//") < tempstring.indexOf("\"") || tempstring.indexOf("//") > tempstring.lastIndexOf(")") ||
						tempstring.indexOf("//") > tempstring.indexOf(";"))
				{
					str = tempString.substring(0, tempString.indexOf("//"));
				}
				else if((tempstring.indexOf("//") < tempstring.indexOf(";") && tempstring.indexOf("//") > tempstring.indexOf("\"") )
						|| ( (tempstring.indexOf("//") < tempstring.lastIndexOf(")")) && tempstring.indexOf("//") > tempstring.indexOf("\"")) )
					if(!tempstring.contains(";"))
					{
						str = tempString.substring(0, tempString.lastIndexOf(")"));
					}
					else
						str = tempString.substring(0, tempString.indexOf(";"));
				else
				{
					if(!tempstring.contains(";"))
					{
						str = tempString.substring(0, tempString.lastIndexOf(")"));
					}
					else
						str = tempString.substring(0, tempString.indexOf(";"));
				}
			}
			else
				str = tempString.substring(0, tempString.indexOf("//"));
		}
		else if(!bool && tempstring.contains("/*") && !tempstring.contains("*/") && !tempstring.contains("//"))//多行注释开始时有"/*" 没"*/" 没 "//" 010 
		{
//			bool = true;
			if(tempstring.contains("\""))
			{
				if((tempstring.indexOf("/*") < tempstring.indexOf(";") && tempstring.indexOf("/*") > tempstring.indexOf("\"") )
						|| ( (tempstring.indexOf("/*") < tempstring.lastIndexOf(")")) && tempstring.indexOf("/*") > tempstring.indexOf("\"")) )
				{
					if(!tempstring.contains(";"))
					{
						str = tempString.substring(0, tempString.lastIndexOf(")"));
					}
					else
						str = tempString.substring(0, tempString.indexOf(";"));
				}
				
				else
				{
					bool = true;
					str = tempString.substring(0, tempString.indexOf("/*"));
				}
			}
			else
			{
				bool = true;
				str = tempString.substring(0, tempString.indexOf("/*"));
			}
		}
		else if(!bool && tempstring.contains("/*") && tempstring.contains("*/") && !tempstring.contains("//"))//多行注释开始时有"/*"和"*/"但是没"//" 011
		{
//			bool = true;
			if(tempstring.contains("\""))
			{
				if(tempstring.indexOf("/*") > tempstring.indexOf("\"") &&
						( tempstring.indexOf("*/") < tempstring.indexOf(")") || tempstring.indexOf("*/") < tempstring.indexOf(";")) )
				{
					if(!tempstring.contains(";"))
					{
						str = tempString.substring(0, tempString.lastIndexOf(")"));
					}
					else
						str = tempString.substring(0, tempString.indexOf(";"));
				}
				else if(tempstring.indexOf("/*") < tempstring.indexOf("\"") && tempstring.indexOf("*/") > tempstring.indexOf("\"") &&
						( tempstring.indexOf("*/") < tempstring.indexOf(";") || tempstring.indexOf("*/") < tempstring.lastIndexOf(")")) )
				{
					bool = true;
					str = tempString.substring(0, tempString.indexOf("/*"));
				}
				else
				{
					if(!tempstring.contains(";"))
					{
						str = tempString.substring(0, tempString.lastIndexOf(")"));
					}
					else
						str = tempString.substring(0, tempString.indexOf(";"));
				}
			}
			else
				str = tempString.substring(0, tempString.indexOf("/*")) + tempString.substring(tempString.indexOf("*/") + 2, tempString.length());
		}
		else if(!bool && tempstring.contains("/*") && tempstring.contains("*/") && tempstring.contains("//"))//多行注释开始时有"/*" "*/"和"//" 111
		{
//			bool = true;
			int index1 = tempstring.indexOf("//");
			int index2 = tempstring.indexOf("/*");
			int index3 = tempstring.indexOf("*/");
			if(tempstring.contains("\""))
			{
				int flag;
				if(!tempstring.contains(";"))
				{
					flag = 0;
					str = tempString.substring(0, tempString.lastIndexOf(")"));
				}
				else
				{
					flag = 1;
					str = tempString.substring(0, tempString.indexOf(";"));
				}
				if(flag == 1)
				{
					if(index1 < index2)
					{
						if(index1 < tempstring.indexOf("\"") || index1 > tempstring.indexOf(";"))
							str = tempString.substring(0 , tempString.indexOf("//"));
						else if(index2 > tempstring.indexOf(";"))
						{
							str = tempString.substring(0, tempString.indexOf("/*"));
						}
						else
							str = tempString.substring(0, tempString.indexOf(";"));
					}
					else if(index1 > index3)
					{
						if(index1 < tempstring.indexOf("\"") || (index2 < tempstring.indexOf("\"") && index3 > tempstring.indexOf(";")) )
						{
							str = tempString.substring(0, tempString.indexOf("/*")) +
									tempString.substring(tempString.indexOf("*/") + 2 , tempString.indexOf("//"));
						}
						else if(index3 > tempstring.indexOf("\"") && index3 < tempstring.indexOf(";") && index2 < tempstring.indexOf("\""))
						{
							bool = true;
							str = tempString.substring(0, tempString.indexOf("/*"));
						}
						else if(index3 < tempstring.indexOf("\"") && index1 > tempstring.indexOf("\"") && index1 < tempstring.indexOf(";"))
						{
							str = tempString.substring(0, tempString.indexOf("/*")) +
									tempString.substring(tempString.indexOf("*/"), tempString.indexOf(";"));
						}
						else if(index2 > tempstring.indexOf("\"") && index2 < tempstring.indexOf(";"))
						{
							if(index1 < tempstring.indexOf(";"))
								str = tempString.substring(0, tempString.indexOf(";"));
						}
						else
						{
							//***************************************************************************
						}
					}
					else
					{
						str = tempString.substring(0, tempString.indexOf("/*")) +
								tempString.substring(tempString.indexOf("*/") + 2, tempString.length());
					}
				}
				else
				{
					
				}
			}
			else
			{
				if(index1 < index2)
				{
					str = tempString.substring(0 , tempString.indexOf("//"));
				}
				else if(index1 > index3)
				{
					str = tempString.substring(0, tempString.indexOf("/*")) +
							tempString.substring(tempString.indexOf("*/") + 2 , tempString.indexOf("//"));
				}
				else
				{
					str = tempString.substring(0, tempString.indexOf("/*")) + tempString.substring(tempString.indexOf("*/") + 2, tempString.length());
				}
			}
		}
		else if(!bool && tempstring.contains("//") && tempstring.contains("/*") && !tempstring.contains("*/"))//多行注释开始时有"//" "/*" 但是没"*/" 110
		{
			bool = true;
			if(tempstring.indexOf("//") < tempstring.indexOf("/*"))
				str = tempString.substring(0, tempString.indexOf("//"));
			else
				str = tempString.substring(0 , tempString.indexOf("/*"));
		}
		else if(bool && !tempstring.contains("/*") && tempstring.contains("//") && tempstring.contains("*/"))//多行注释结束时有"//" "*/" 但是没"/*" 101
		{
			bool = false;
			if(tempstring.indexOf("//") < tempstring.indexOf("*/"))
				str = tempString.substring(0, tempString.indexOf("//"));
			else
			{
				str = tempString.substring(tempString.indexOf("*/") + 2, tempString.indexOf("//"));
			}
		}
		else if(bool && !tempstring.contains("/*") && !tempstring.contains("//") && tempstring.contains("*/"))//多行注释结束时有"*/" 但是没"//" "/*" 001
		{
			bool = false;
			str = tempString.substring(tempString.indexOf("*/") + 2 , 0);
		}
		
		return str;
	}

}


