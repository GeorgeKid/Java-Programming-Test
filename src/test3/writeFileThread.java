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
				System.out.println("文件不存在！");
			    return;
			}
	    
			//对该文件加锁
		    RandomAccessFile out = new RandomAccessFile(filepos, "rw");
		    RandomAccessFile out2 = new RandomAccessFile(filepos2, "rw");
		    
		    FileChannel fcout=out.getChannel();
		    FileLock flout=null; 
           
           while(true){  
	           try {
	                flout = fcout.tryLock();
	                break;
	           } catch (Exception e) {
	                System.out.println("有其他线程正在操作该文件，当前线程休眠1000毫秒"); 
	                sleep(1000);
	           }
          }
           
           //写入
           sleep(10);  
           
           //移动文件指针到文未
           long fileLength = out.length();
           out.seek(fileLength);
           
           long fileLength2 = out2.length();
           out2.seek(fileLength);
           
           
           StringBuffer sb=new StringBuffer();  
           sb.append("随便写点什么");  
           
           out.write(sb.toString().getBytes("utf-8"));
           out2.write(sb.toString().getBytes("utf-8"));
           
           //System.out.println("写入   "+filepos+" "+sb.toString());
           
           
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
