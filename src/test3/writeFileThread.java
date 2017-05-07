package test3;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class writeFileThread extends Thread{
    
	private String filepos;
	private String filepos2;
	public writeFileThread(String pos,String pos2){
		this.filepos=pos;
		this.filepos2=pos2;
		
	}
	public void run()
	{

		
		File file=new File(filepos);
		File file2=new File(filepos2);
		try{
			
			if(!file.exists()||!file2.exists())
			{
				System.out.println("�ļ������ڣ�");
			    return;
			}
	    
			//�Ը��ļ�����
		    RandomAccessFile out = new RandomAccessFile(filepos, "rw");
		    RandomAccessFile out2 = new RandomAccessFile(filepos2, "rw");
		    
		    FileChannel fcout=out.getChannel();
		    FileLock flout=null; 
           
           while(true){  
	           try {
	                flout = fcout.tryLock();
	                break;
	           } catch (Exception e) {
	                System.out.println("�������߳����ڲ������ļ�����ǰ�߳�����1000����"); 
	                sleep(1000);
	           }
          }
           
           //д��
           sleep(10);  
           
           //�ƶ��ļ�ָ�뵽��δ
           long fileLength = out.length();
           out.seek(fileLength);
           
           long fileLength2 = out2.length();
           out2.seek(fileLength);
           
           
           StringBuffer sb=new StringBuffer();  
           sb.append("���д��ʲô");  
           
           out.write(sb.toString().getBytes("utf-8"));
           out2.write(sb.toString().getBytes("utf-8"));
           
           //System.out.println("д��   "+filepos+" "+sb.toString());
           
           
           flout.release();  
           fcout.close();  
           out.close();  
           out=null; 
           out2.close();  
           out2=null; 
           
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
