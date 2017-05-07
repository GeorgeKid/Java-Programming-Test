package test3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class readFileThread extends Thread{
	
	
	private String filepos;
	private String filepos2;
	
	public readFileThread(String pos,String pos2)
	{
		this.filepos=pos;
		this.filepos2=pos2;
		
	}
	
	public boolean verify(String act)
	{
		boolean result=true;
		
		try {
			File file=new File(filepos2);
			RandomAccessFile fis = new RandomAccessFile(file, "rw"); 
			
			//读出
			byte[] buf = new byte[1024];    
			StringBuffer sb=new StringBuffer();    
			while((fis.read(buf))!=-1){                    
			    sb.append(new String(buf,"utf-8"));        
			    buf = new byte[1024];    
			} 
			
			System.out.println("Expected Content:"+sb.toString());
			
			System.out.println("Actual Content:"+act);
			
			if(sb.toString().equals(act)){
				result=true;
			}else{
				result=false;
			}
		
			fis.close();    
			fis=null;
			
			return result;
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
		
	}
	
	public void run()
	{
		
		try {
			File file=new File(filepos);
			//加锁
			RandomAccessFile fis = new RandomAccessFile(file, "rw");   
            FileChannel fcin=fis.getChannel();    
            FileLock flin=null;    
            while(true){    
                try {  
                    flin = fcin.tryLock();  
                    break;  
                } catch (Exception e) {  
                     System.out.println("有其他线程正在操作该文件，当前线程休眠1000毫秒");   
                     sleep(1000);    
                }  
                  
            }    
            
            //读出
            byte[] buf = new byte[1024];    
            StringBuffer sb=new StringBuffer();    
            while((fis.read(buf))!=-1){                    
                sb.append(new String(buf,"utf-8"));        
                buf = new byte[1024];    
            }    
                
            System.out.println("File Name:"+filepos+" "+sb.toString());    
            
            if(this.verify(sb.toString())){
            	System.out.println("PASS");
            }else{
            	System.out.println("Fail");
            }
                
            flin.release();    
            fcin.close();    
            fis.close();    
            fis=null;    
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
      
}

