package test3;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;


public class readAndWriteTest {
    
	public static void main(String[] args) {
		//����10��д���߳�
		for(int i=0;i<10;i++)
		{
			int num=(int)Math.floor(5*Math.random());
			String filepos=selectfile(num);
			String filepos2=selectfile2(num);
			writeFileThread write= new writeFileThread(filepos,filepos2);
			write.start();
		}
		
		//����2�������߳�
		for(int j=0;j<2;j++)
		{
			int num2=(int)Math.floor(5*Math.random());
			String filepos=selectfile(num2);
			String filepos2=selectfile2(num2);
			readFileThread read=new readFileThread(filepos,filepos2);
			read.start();
		}
        
	}

	//�������������5���ļ�����
	public static String selectfile(int n)
	{
		String filepos=null;
		if(n==0) { filepos="G:/JAVAˢ����/1.txt";}
		else if (n==1) {filepos="G:/JAVAˢ����/2.txt";}
		else if (n==2) {filepos="G:/JAVAˢ����/3.txt";}
		else if (n==3) {filepos="G:/JAVAˢ����/4.txt";}
		else if (n==4) {filepos="G:/JAVAˢ����/5.txt";}
		else{
			System.out.println("�ļ���ֻ��5����");
		}
	
		
		return filepos;
	}
	
	//׼���ļ��ı��ݣ��Ա�Ƚ���ʵ����
	public static String selectfile2(int n)
	{
		String filepos=null;
		if(n==0) { filepos="G:/JAVAˢ����/1bak.txt";}
		else if (n==1) {filepos="G:/JAVAˢ����/2bak.txt";}
		else if (n==2) {filepos="G:/JAVAˢ����/3bak.txt";}
		else if (n==3) {filepos="G:/JAVAˢ����/4bak.txt";}
		else if (n==4) {filepos="G:/JAVAˢ����/5bak.txt";}
		else{
			System.out.println("�ļ���ֻ��5����");
		}
	
		
		return filepos;
	}
	
}







