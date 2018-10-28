class AVLNode<X extends Comparable<X>>
{
	X data;
	int height;
	AVLNode<X> left;
	AVLNode<X> right;
	AVLNode<X> parent;
	AVLNode()
	{
		data=null;
		height=1;
		left=null;
		right=null;
		parent=null;
	}
	AVLNode(X temp)
	{
		data=temp;
		height=1;
		left=null;
		right=null;
		parent=null;
	}
}

public class AVLTree<X extends Comparable<X>>
{
	AVLNode<X> root;
	int calculateHeight(AVLNode<X> r)
	{
		int p;
		if(r.left==null && r.right==null)
        {
        	p=1;
        }
        else if(r.left==null && r.right!=null)
        {
        	p=1+r.right.height;
        }
        else if(r.left!=null && r.right==null)
        {
        	p=1+r.left.height;
        }
        else
        {
        	p = 1 + (int)Math.max(r.left.height,r.right.height);
        }
        return p;
	}
	AVLNode insertNodeInTree(AVLNode<X> r,X tmp) 
	{ 
    	if (r==null)
    	{
        	AVLNode<X> p=new AVLNode<X>(tmp);
        	return p;
    	}
  		
  		AVLNode<X> tmp1;
    	if (r.data.compareTo(tmp)>0)
    	{
    		tmp1=insertNodeInTree(r.left, tmp);
    		tmp1.parent=r;
        	r.left  = tmp1; 
    	}
    	else if (r.data.compareTo(tmp)<0)
    	{
    		tmp1=insertNodeInTree(r.right,tmp);
    		tmp1.parent=r;
        	r.right = tmp1; 
    	}
    	else 
        	return r; 

        r.height=calculateHeight(r);
        int diff=0;
        if(r.left==null && r.right==null)
        {
        	diff=0;
        }
        else if(r.left==null && r.right!=null)
        {
        	diff=-r.right.height;
        }
        else if(r.left!=null && r.right==null)
        {
        	diff=r.left.height;
        }
        else
        {
        	diff=r.left.height-r.right.height;
        } 
  
    // Left Left Case 
    	if (diff > 1 && (r.left.data.compareTo(tmp)>0)) 
    	{
        	return rightRotate(r); 
    	}
  	//Right Right Case
  		if (diff < -1 && (r.right.data.compareTo(tmp)<0))
    	{
        	return leftRotate(r); 
    	}
    // Left Right Case 
    	if (diff > 1 && (r.left.data.compareTo(tmp)<0))
    	{ 
        	r.left =  leftRotate(r.left); 
        	return rightRotate(r); 
    	} 
    // Right Left Case 
    	if (diff < -1 && (r.right.data.compareTo(tmp)>0)) 
    	{	 
        	r.right = rightRotate(r.right); 
        	return leftRotate(r); 
    	} 	
    	return r; 
	} 

	AVLNode rightRotate(AVLNode<X> z) 
	{ 
    	AVLNode<X> y = z.left; 
    	AVLNode<X> T3 = y.right;
    	y.right = z;
    	z.left = T3;
    	y.parent=z.parent;
    	z.parent=y;
    	if(T3!=null)
    		T3.parent=z;
 	    z.height = calculateHeight(z); 
    	y.height = calculateHeight(y); 
    	return y; 
	} 

	AVLNode leftRotate(AVLNode<X> z) 
	{ 
    	AVLNode<X> y = z.right; 
    	AVLNode<X> T2 = y.left; 
    	z.right = T2; 
    	y.left = z; 
    	y.parent=z.parent;
    	z.parent=y;
    	if(T2!=null)
    		T2.parent=z;
 	    y.height = calculateHeight(y);
    	z.height = calculateHeight(z);
    	return y; 
	} 

	AVLNode successorNode(AVLNode<X> o)
	{
		AVLNode<X> suc=null;
		if(o.right!=null)
		{
			suc=o.right;
			while(suc.left!=null)
			{
				suc=suc.left;
			}
			return suc;
		}
		else
		{
			suc=o;
			o=o.parent;
			while(o!=null && o.left!=suc)
			{
				suc=o;
				o=o.parent;
			}
			return o;
		}
	}

	AVLNode predecessorNode(AVLNode<X> o)
	{
		AVLNode<X> pred=null;
		if(o.left!=null)
		{
			pred=o.left;
			while(pred.right!=null)
			{
				pred=pred.right;
			}
			return pred;
		}
		else
		{
			pred=o;
			o=o.parent;
			while(o!=null && o.right!=pred)
			{
				pred=o;
				o=o.parent;
			}
			return o;
		}
	}

	AVLNode findPosition(X o)
	{
		AVLNode<X> k=root;
		while(k.data.compareTo(o)!=0)
		{
		if(k.data.compareTo(o)>0)
			k=k.left;
		else if(k.data.compareTo(o)<0)
			k=k.right;

		if(k==null)
			break;
		}
		return k;
	}

	void printTree(AVLNode<X> o)
	{
		if(o.left==null && o.right==null)
		{
			System.out.print(o.data+", ");
		}
		else if(o.left!=null && o.right==null)
		{
			printTree(o.left);
			System.out.print(o.data+", ");
		}
		else if(o.left==null && o.right!=null)
		{
			System.out.print(o.data+", ");
			printTree(o.right);
		}
		else
		{
			printTree(o.left);
			if(o.parent==null)
				System.out.print(o.data+", ");
			else
				System.out.print(o.data+", ");
			printTree(o.right);
		}
	}

	/*public static void main(String args[])
	{
		AVLTree<Integer> t=new AVLTree<Integer>();
		int arr[]={10,20,30,40,50,25};
		int i=0;
		t.root=t.insertNodeInTree(t.root,arr[0]);
		for(i=1;i<6;i++)
		{
			t.root=t.insertNodeInTree(t.root,arr[i]);
		}
		t.printTree(t.root);	
	}*/
}
