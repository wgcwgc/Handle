package wgc.lanchang;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class Test {
	static String fromFile = "C:\\1.txt";
	static String fromFileCharset = "UTF-8";
	static String toFileCharset = "UTF-8";
	static String fromFilePathWithOutFile = fromFile.substring(0,fromFile.lastIndexOf("\\")+1);
	static String fromFileWithOutFilePath = fromFile.substring(fromFile.lastIndexOf("\\")+1,fromFile.length());
	public static void main(String args[]){
		Test test = new Test();
		test.excute(fromFile);
		System.out.println(fromFileWithOutFilePath);
	}
	public void excute(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			System.out.println("---文件存在正在处理---");
			String tempString = null; 
			String writeString = null;
			InputStreamReader isReader = null;
			FileInputStream fiStream = null;
			BufferedReader bReader = null;
			try {
				fiStream = new FileInputStream(filePath);
				isReader = new InputStreamReader(fiStream,fromFileCharset);
				bReader = new BufferedReader(isReader);
				String toFilePathWithFileName = fromFilePathWithOutFile
					+ "New_"
					+ fromFileWithOutFilePath;
				File wFile = new File(toFilePathWithFileName);
				wFile.createNewFile();
				OutputStreamWriter os = null;
				FileOutputStream fos = null;
				fos = new FileOutputStream(toFilePathWithFileName);
				os = new OutputStreamWriter(fos,toFileCharset);
				while((tempString = bReader.readLine()) != null){
					writeString = tempString;
					writeString = this.delKongHang(writeString);
					writeString = this.delHangHouZhuShi(writeString);
					writeString = this.replaceShuangyinhaoToDanyinhao(writeString);
					writeString = this.replaceDanyinhaoToShuangyinhao(writeString);
					if(!writeString.equals("")){
						os.write(writeString);
						os.write("\n");
					}
				}
				if (os != null) {
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					os = null;
				}
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					fos = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
			}
		}else{
			System.out.println("---文件不存在！");
		}
	}
	public String delKongHang(String str){
		if(str.trim()!= null && !str.trim().equals("")){
			return str;
		}else{
			return "";
		}
	}
	public String delHangHouZhuShi(String str){
		if(str.contains("//")){
			return str.substring(0,str.indexOf("//"));
		}else if(str.contains("/*"))
		{
			return str.substring(0,str.indexOf("/*"));
		}else if(str.contains("<!--"))
		{
			return str.substring(0,str.indexOf("<!--"));
		}
		else{
			return str;
		}
	}
	public String replaceShuangyinhaoToDanyinhao(String str){
		return str.replaceAll("\"", "\'");
	}
	public String replaceDanyinhaoToShuangyinhao(String str){
		return str.replaceAll("\'", "\"");
	}
}
