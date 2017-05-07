package test1;

import java.util.Scanner;

class ListNode{
	int num;
	ListNode next;
	ListNode(int x)
	{
		num=x;
		next=null;
	}
}

public class Reverse_List {

	public static ListNode ReverseKNode(ListNode head, int k)
	{
		if (head==null || k<2) return head;
	    
		ListNode prehead =new ListNode(0);
		ListNode lasttail=prehead;
		
		ListNode curtail=head;
		ListNode curhead=head;
		ListNode posthead=null;
		
		ListNode Node=head;
		
		int num=0;
		
		while(Node!=null)
		{
			num++;
			Node=Node.next;
			if(num==k)
			{
				ListNode temp=curtail.next;
				while(temp!=Node)
				{
					posthead=curhead;
					curhead=temp;
					
					temp=temp.next;
					curhead.next=posthead;
				}
				
				lasttail.next=curhead;
				
				lasttail=curtail;
				curtail=Node;
				curhead=Node;
				
				num=0;
			}
			
		}
		
		lasttail.next=curhead;
		
		return prehead.next;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ListNode head= new ListNode(1);
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("请输入链表的长度：");
        int length=0;
        length=sc.nextInt();
        
        System.out.println("请输入k的大小：");
        int k=0;
        k=sc.nextInt();
        
        ListNode lastnode=head;
        
        for(int i=2;i<=length;i++)
        {
        	ListNode temp = new ListNode(i);
        	
        	lastnode.next=temp;
        	
        	lastnode=temp;
        	
        }
      
        
      ListNode reverselist;
      reverselist=ReverseKNode(head,k);
      
      while(reverselist!=null)
      {
    	  System.out.print(reverselist.num+" ");
    	  reverselist=reverselist.next;
      }
      return;  
	}

}
