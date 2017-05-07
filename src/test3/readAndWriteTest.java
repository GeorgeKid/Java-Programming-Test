package test3;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;


public class readAndWriteTest {
    
	public static void main(String[] args) {
		//启动10个写的线程
		for(int i=0;i<10;i++)
		{
			int num=(int)Math.floor(5*Math.random());
			String filepos=selectfile(num);
			String filepos2=selectfile2(num);
			writeFileThread write= new writeFileThread(filepos,filepos2);
			write.start();
		}
		
		//启动2个读的线程
		for(int j=0;j<2;j++)
		{
			int num2=(int)Math.floor(5*Math.random());
			String filepos=selectfile(num2);
			String filepos2=selectfile2(num2);
			readFileThread read=new readFileThread(filepos,filepos2);
			read.start();
		}
        
	}

	//自行在这里添加5个文件文置
	public static String selectfile(int n)
	{
		String filepos=null;
		if(n==0) { filepos="G:/JAVA刷题编程/1.txt";}
		else if (n==1) {filepos="G:/JAVA刷题编程/2.txt";}
		else if (n==2) {filepos="G:/JAVA刷题编程/3.txt";}
		else if (n==3) {filepos="G:/JAVA刷题编程/4.txt";}
		else if (n==4) {filepos="G:/JAVA刷题编程/5.txt";}
		else{
			System.out.println("文件数只有5个！");
		}
	
		
		return filepos;
	}
	
	//准备文件的备份，以便比较真实内容
	public static String selectfile2(int n)
	{
		String filepos=null;
		if(n==0) { filepos="G:/JAVA刷题编程/1bak.txt";}
		else if (n==1) {filepos="G:/JAVA刷题编程/2bak.txt";}
		else if (n==2) {filepos="G:/JAVA刷题编程/3bak.txt";}
		else if (n==3) {filepos="G:/JAVA刷题编程/4bak.txt";}
		else if (n==4) {filepos="G:/JAVA刷题编程/5bak.txt";}
		else{
			System.out.println("文件数只有5个！");
		}
	
		
		return filepos;
	}
	
}







