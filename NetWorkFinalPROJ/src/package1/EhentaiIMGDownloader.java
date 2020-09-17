package package1;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//???????
public class EhentaiIMGDownloader extends Thread 
{
	private InputStream in = null;
	
	private String FileName = null;
	
	private String folderDst = null;
	
	EhentaiIMGDownloader(InputStream input,String filename,String folderDst)
	{
		this.in = input;
		
		this.FileName = filename;
		
		this.folderDst = folderDst;
	}
	
	@Override
	public void run(){
		
		byte[] buffer = new byte[4096];
		
		int len =0;
		
		OutputStream out = null;
		
		try {
			
		    out = new FileOutputStream(folderDst+"//"+FileName);
			
			while((len = in.read(buffer))!=-1)
			{
				out.write(buffer, 0, len);
			}
			
			out.close();
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		
		
	}
}
