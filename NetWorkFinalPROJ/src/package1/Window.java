package package1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;

import javax.swing.JTextArea;


public class Window {

	private JFrame frame;
	private JTextField textField;
	JTextArea textArea;
	Runnable runnable;
	String msg;
	int line = 0,pro =0,n;
	Image im = null;

    final static int WaittingTime = 0;
	
	static String folderDst = null;
	
	static List<String> imgURL = new ArrayList<>();
	
	static List<String> imgPage = new ArrayList<>();
	
	static List<String> imgRealURL = new ArrayList<>();
	
	static List<String> imgFileName = new ArrayList<>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		runnable = new Runnable(){

			@Override
			public void run() {
				
				if(line<10)
				{
					textArea.append(msg);
					line++;
				}
				else if(line>=10)
				{
					textArea.setText("");
					textArea.append(msg);
					line = 0;
				}		
			}
			
		};
		
		im = Toolkit.getDefaultToolkit().createImage(
	    		  this.getClass().getResource("ok.png"));
			
		frame = new JFrame("EHentaiDownloader");
		frame.setBounds(100, 100, 780, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(105, 40, 479, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Go");
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnNewButton.setBounds(624, 38, 99, 27);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("URL\r\n");
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(34, 44, 57, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("State\r\n");
		lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(14, 114, 57, 19);
		frame.getContentPane().add(lblNewLabel_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("�L�n������", Font.PLAIN, 17));
		textArea.setBounds(14, 146, 734, 247);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		frame.getContentPane().add(textArea);
		
		JButton button = new JButton("\u770B\u672C\u672C");
		button.setFont(new Font("�L�n������", Font.PLAIN, 15));
		button.setBounds(624, 106, 99, 27);
		frame.getContentPane().add(button);
		
		button.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
						
				File dir = new File("C://EHentaiImage");
				
				if(!dir.exists())
				{
					dir.mkdir();
				}
				
				try { 
					Runtime r1 = Runtime.getRuntime ();
					Process proc = r1.exec ("cmd.exe /c"+ "start C:\\EhentaiImage");
					//int e1 = proc.exitValue (); 
					} catch (Exception e) {
					e.printStackTrace ();
					} 
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO �۰ʲ��ͪ���k Stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO �۰ʲ��ͪ���k Stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO �۰ʲ��ͪ���k Stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO �۰ʲ��ͪ���k Stub
				
			}
			
		});
			
		btnNewButton.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				n = 0;
				
				String GalleryURL = textField.getText().toString();
				
				Pattern pat = Pattern.compile("https://e-hentai.org/g/[0-9]+/[a-zA-Z0-9]+/");
				
				Matcher mat = pat.matcher(GalleryURL);
				
				if(!mat.find()) {
					
					JOptionPane.showMessageDialog(frame,"�п�J���T��EHentai�e�Y���}",
                            "���~", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Thread t = new Thread(new Runnable(){

						@Override
						public void run() {
							
							textField.setEnabled(false);
							btnNewButton.setEnabled(false);	
							textArea.setText("");
							line = 0;
							
							try {
						Download(GalleryURL);
						 JOptionPane.showMessageDialog(frame,"�@�U��"+n+"�i�Ϥ�",
                                 "�U������", JOptionPane.PLAIN_MESSAGE,
                                 new ImageIcon(im));
					} catch (Exception e) {
						updateUI("�s�u����\r\n");

					}finally {
						textField.setEnabled(true);
						btnNewButton.setEnabled(true);	
					}
						}});
					
				
					t.start();
				}
			
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO �۰ʲ��ͪ���k Stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO �۰ʲ��ͪ���k Stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO �۰ʲ��ͪ���k Stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO �۰ʲ��ͪ���k Stub
				
			}});
	}
	void init()
	{
		folderDst = null;
		
		imgURL.clear();
		
		imgPage.clear();
		
	    imgRealURL.clear();
		
		imgFileName.clear();
		
	}
	void updateUI(String str)
	{
		msg = str;
		
		javax.swing.SwingUtilities.invokeLater(runnable);
	}
	public void Download(String GalleryURL) throws IOException, InterruptedException
	{
		init();
		
		updateUI("���b�s���e�Y...\r\n");
		
		BufferedReader buf = ConnectHTML(GalleryURL);
		
		//updateUI("�w���\�s�W�e�Y!!\r\n");
		
		//�q�Ĥ@����������l�X���X�᭱���X���ç�᭱���ƪ����}�[�JimgPage�̭�
		updateUI("���b����e�Y��T(�e�Y�`�@���X��)...\r\n");
		
		addPage(buf,GalleryURL);
		
		System.out.println(imgPage.size());
		
		//updateUI("�������!!\r\n");
		
		//updateUI("���b�гy��Ƨ�...\r\n");
		
		makeFolder();
		
		//updateUI("��Ƨ��гy����!!\r\n");
		
		int count=1;
		
		//��o�Ҧ��Ϥ������}1(�ݭn�b�Ϥ����}1����Ϥ����}2)		
		for(int h=0;h<imgPage.size();h++)
		{
			updateUI("���b�����"+count+"���Ϥ�����T...\r\n");
			
			addOnePageImg(ConnectHTML(imgPage.get(h)));
			
			updateUI("���b�����"+count+"���Ϥ����Ҧ��Ϥ����}...\r\n");
			
			getIMG(imgURL);	
			
		    updateUI("���b�U����"+count+"���Ҧ����Ϥ�...\r\n");
			
			DownloadIMG(imgRealURL,imgFileName);
			
			imgURL.clear();
			
			imgRealURL.clear();
			
			imgFileName.clear();
			
			count++;
		}
		
		updateUI("�U������!!\r\n");
	}
	void makeFolder()
	{
		File dir = new File("C://EHentaiImage");
		
		int num = 0;
		
		if(!dir.exists())
		{
			dir.mkdir();
		}

		File subdir = new File("C://EHentaiImage//Folder"+num);
		
		while(subdir.exists())
		{
			num = num + 1;
			
			subdir = new File("C://EHentaiImage//Folder"+num);
		}
		
		subdir.mkdir();
		
		folderDst = subdir.getPath();
	}
	void DownloadIMG(List<String> imgs,List<String> imgsName) throws IOException, InterruptedException
	{	
		URL url = null;
		
		URLConnection con = null;
		
		int pr = 100/imgs.size();
		
		int temp = 0;
		
		pro = 0;
		
		for(int f=0;f<imgs.size();f++)
		{			
			
			
			url = new URL(imgs.get(f));
			
			try {
				
				con = url.openConnection();
				
				EhentaiIMGDownloader ehen = new EhentaiIMGDownloader(con.getInputStream(),imgsName.get(f),folderDst);
				
				Thread.sleep(WaittingTime);
				
				ehen.start();	
				
				n++;
			}
			catch(Exception e) {
				System.out.println("Time out!!");
			}
			
			if(f<imgs.size()-1)
			{
				temp = temp + pr;
			}
			else if(f==imgs.size()-1)
			{
				temp = 100;
			}		

		}
	}

		void getIMG(List<String> imgs) throws IOException
		{
			BufferedReader buf = null;
			
			String str = null;
			
			Pattern pat = Pattern.compile("src=\"(http://.+/[a-z]+/.+/keystamp=.+;fileindex=.+;xres=.+/(.+[j|p|g|n]+))\" style");
			
			Matcher mat = null;
			
			for(int i=0;i<imgs.size();i++)
			{
				URL url = new URL(imgURL.get(i));
				
				URLConnection con = (HttpURLConnection)url.openConnection();
				
				con.setRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
				
				InputStream in = con.getInputStream();
				
				InputStreamReader read = new InputStreamReader(in);
				
				buf = new BufferedReader(read);
				
				while((str=buf.readLine())!=null)
				{
					mat = pat.matcher(str);
					while(mat.find())
					{
						imgRealURL.add(mat.group(1));
						
						imgFileName.add(mat.group(2));
					}
				}
			}
			
			/*for(int g=0;g<imgRealURL.size();g++)
			{
				updateUI(imgRealURL.get(g)+"\r\n");
			}*/
		}
	BufferedReader ConnectHTML(String str) throws IOException
	{
		URL url = new URL(str);
		
		URLConnection con = (HttpURLConnection)url.openConnection();
		
		con.setRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
		
		InputStream in = con.getInputStream();
		
		InputStreamReader read = new InputStreamReader(in);
		
		BufferedReader buf = new BufferedReader(read);
		
		return buf;
	}
	void addPage(BufferedReader buf,String GalleryURL) throws IOException
    {
		String str = null;
	
		Pattern pat = Pattern.compile("<a href=\"(https://e-hentai.org/g/[0-9]+/[0-9a-z]+/.{1}p=[0-9]+)\" onclick=\"return false\">");
		
		Matcher mat = null;
		
		///�q�Ĥ@�����e�Y��o�C�@������T
		while((str = buf.readLine())!=null)
		{
			mat = pat.matcher(str);
			
			while(mat.find())
			imgPage.add(mat.group(1));
		}
		
	//�d�ݬO�_�e�Y�u���@��
	if(imgPage.size()!=0){	
		//�L�o�����ƪ�����
		String temp = null;
		
		for(int i=0;i<imgPage.size();i++)
		{
			temp = imgPage.get(i);
			for(int j=i+1;j<imgPage.size();j++)
			{
				if(imgPage.get(j).equals(temp))
				{
					imgPage.remove(j);
				}
			}
		}
		
		if(imgPage.get(imgPage.size()-1).equals(imgPage.get(0)))
		{
			imgPage.remove(imgPage.size()-1);
		}

		Pattern patt = Pattern.compile("https://e-hentai.org/g/[0-9]+/[0-9a-z]+/.{1}p=([0-9]+)");
		
		Matcher match = null;
		
		List<String> tep = new ArrayList<>(); 
		
		for(int g=0;g<imgPage.size();g++)
		{
			match = patt.matcher(imgPage.get(g));
			
			while(match.find())
			{
				tep.add(match.group(1));
			}
		}
		
		patt = Pattern.compile("https://e-hentai.org/g/([0-9]+)/([0-9a-z]+)/.{1}p=[0-9]+");
		
		match = patt.matcher(imgPage.get(0));
		
		String t1=null,t2=null;
		
		while(match.find()){
		t1 = match.group(1);
		
		t2 = match.group(2);
		}
		
	if(tep.size()>1)
	{
		if(Integer.valueOf(tep.get(tep.size()-1))-Integer.valueOf(tep.get(tep.size()-2))>1)
		{
			
			for(int s=tep.size();s<Integer.valueOf(tep.get(tep.size()-1));s++)
			{
				imgPage.add(s-1,"https://e-hentai.org/g/"+t1+"/"+t2+"/?p="+s);
			}
		}
	}	
		imgPage.add(0, GalleryURL);
	}	
	else
	{
		imgPage.add(GalleryURL);
	}
	}
	void addOnePageImg(BufferedReader buf) throws IOException
	{
		String str = null;
		
		Pattern pat = Pattern.compile("<a href=\"(https://e-hentai.org/[a-z]{1}/[a-z0-9]+/[0-9]+-[0-9]+)\"");
		
		Matcher mat = null;
		
		while((str=buf.readLine())!=null)
		{
			mat = pat.matcher(str);
			
			while(mat.find())
			{
				imgURL.add(mat.group(1));
			}
		}
		
		/*for(int i=0;i<imgURL.size();i++)
		{
			updateUI(imgURL.get(i)+"\r\n");
		}*/
	}
}
