import java.util.*;
/**
 * Constructor and handler for Tree data structure, and parsing variables for hashmap
 *
 * @author Bryce Baker
 * @version 2018/11/22
 */

public class Tree<T extends Comparable<T>> 
{
    //Branch subclass constructor for tree
    Stack<branch> tcs = new Stack<branch>();
    HashMap<String, Boolean> map = new HashMap<String, Boolean>();
 class branch implements Comparable<branch>
    {
      private T data;
      private branch left; 
      private branch right;   
      //Constructor for branch
            public branch(T d) 
      {
         setLeft(null);
         setRight(null);
         setData(d);
      }
      //Return Data
            public T getData() { return data; }
            //Set branch data
      public void setData( T d) { data = d; }
//Setters and getters for branch's left and right
      public void setLeft(branch l) { left = l;}
      public void setRight(branch r) { right = r;}
      public branch getLeft()  { return left;}
      public branch getRight()  { return right;}
   
//Compare 2 branches
      public int compareTo(branch o) 
      {
         return this.getData().compareTo( o.getData());
      }
    }
    //Initial constructor for root
       private branch root;
   private int size; 
//Initial constuctor for tree
      public Tree()
   {
      root = null;
      size = 0;
   }
   


    //Write generic s operands to branch  
   
   public void treeCon(T s)
   {
       branch op = new branch(s);
       if(tcs.size() < 2)
       {
         System.out.println("Error: insufficient branches in stack for operator");
         System.out.println(tcs.size());
         System.out.println(s);
         
        }
        else
        {
            branch tmpr = tcs.pop();
            branch tmpl = tcs.pop();
            
            op.setRight(tmpr);
            op.setLeft(tmpl);
            tcs.push(op);
            
        }
       
       
       

    }
    //Add primitives to stack
    public void treeAdd(T x)
    {
        Scanner scan = new Scanner(System.in);
        String check = x.toString();
        if(check.replaceAll("[^a-zA-Z]", "").length() > 0)
        {
            boolean control = false;
            while(control == false)
            {
            System.out.println("Enter value for variable " + check);
            String in = scan.next();
            if(in.replaceAll("[^0-1]","").length() != 1)
            {
            System.out.println("Bad value");
            }
            else
            {
            if(in.equals("1"))
            {
                map.put(check, new Boolean(true));
                
              
            }
            else
            {
             map.put(check, new Boolean(false));
            }
            control = true;
            }
        }
        }
    branch b = new branch(x);
    tcs.push(b);
    
    }
    //Set root after construction of tree
    public void setRoot()
    {
     root = tcs.peek();
     
    }
       // The different traversal types. 
   public static final int INORDER = 0;
   public static final int PREORDER = 1;
   public static final int POSTORDER = 2;
   public static final int LEVELORDER = 3;
       /* Traverse the tree.  travtype determines the type of 
      traversal to perform. */
   private void traverse(branch r, int travType) 
   {
      if ( r != null) 
      {
         switch ( travType) 
         {
            case INORDER: 
            if(r.getLeft() != null)
                    {
                        traverse(r.getLeft(), 0);
                    }
                    visit(r);
                             if(r.getRight() != null)
                    {
                     traverse(r.getRight(), 0);   
                    }
                     break;
            case PREORDER: 
                 visit(r);
                 if(r.getLeft() != null)
                 {
                     traverse(r.getLeft(), 1);
                    }
                    if(r.getRight() != null)
                    {
                     traverse(r.getRight(), 1);   
                    }
                     break;
            case POSTORDER: 
                     if(r.getLeft() != null)
                     {
                         traverse(r.getLeft(), 2);   
                        }
                            if(r.getRight() != null)
                    {
                     traverse(r.getRight(), 2);   
                    }
                    visit(r);
                     break;

         }
      }
   }
   //Caller for traversal types
   public void inOrder() 
   {
      traverse( root, INORDER);
   }

   public void postOrder() 
   {
      traverse( root, POSTORDER);
   }

   public void preOrder() 
   {
      traverse( root, PREORDER);
   }


   //Visitor for branch
      private void visit(branch r) 
   {
       
       if ( r != null) System.out.print( r.getData());
   }
   //Validate tree
   public boolean validate(branch b)
   {
       boolean r = false;
       String s = b.getData().toString();
 if(root.getLeft() == null && root.getRight() == null)
 {
    
    if(s.equals("0"))
    {}
    else if(s.equals("1"))
    {
    
    r = true;
    }
    else
    {
    r = map.get(s);
    
    }
    }
    else
    {
   
    if(s.equals("^"))
    {
        boolean lb;
        boolean rb;
    String ls = b.getLeft().getData().toString();
    String rs = b.getRight().getData().toString();
    if(ls.replaceAll("[^a-zA-Z]","").length() > 0)
    {
    lb = map.get(ls);
    
    }
    else if(ls.equals("0"))
    {lb = false;}
    else
    {lb = true;}
       if(rs.replaceAll("[^a-zA-Z]","").length() > 0)
    {
    rb = map.get(rs);
    
    }
    else if(rs.equals("0"))
    {rb = false;}
    else
    {rb = true;}
    r = lb^rb;
    
    }
    
    if(s.equals("&"))
    {
        boolean lb;
        boolean rb;
    String ls = b.getLeft().getData().toString();
    String rs = b.getRight().getData().toString();
    if(ls.replaceAll("[^a-zA-Z]","").length() > 0)
    {
    lb = map.get(ls);
    
    }
    else if(ls.equals("0"))
    {lb = false;}
    else
    {lb = true;}
       if(rs.replaceAll("[^a-zA-Z]","").length() > 0)
    {
    rb = map.get(rs);
    
    }
    else if(rs.equals("0"))
    {rb = false;}
    else
    {rb = true;}
    r = lb&rb;
    
    }
    if(s.equals("|"))
    {
        boolean lb;
        boolean rb;
    String ls = b.getLeft().getData().toString();
    String rs = b.getRight().getData().toString();
    if(ls.replaceAll("[^a-zA-Z]","").length() > 0)
    {
    lb = map.get(ls);
    
    }
    else if(ls.equals("0"))
    {lb = false;}
    else
    {lb = true;}
       if(rs.replaceAll("[^a-zA-Z]","").length() > 0)
    {
    rb = map.get(rs);
    
    }
    else if(rs.equals("0"))
    {rb = false;}
    else
    {rb = true;}
    r = lb|rb;
    
    }
    else if(s.equals("!"))
    {
    if(b.getLeft() != null)
    {
    String nl = b.getLeft().toString();
    boolean n;
    if(nl.replaceAll("[^a-zA-Z]","").length() > 0)
    {
    n = map.get(nl);
    r = !n;
    
    }
    else
    {
    if(nl.equals("0"))
    {
    r = true;
    }
    else
    {r = false;}
    }
    }
    else if(b.getRight() != null)
    {
    String nl = b.getRight().toString();
    boolean n;
    if(nl.replaceAll("[^a-zA-Z]","").length() > 0)
    {
    n = map.get(nl);
    r = !n;
    
    }
    else
    {
    if(nl.equals("0"))
    {
    r = true;
    }
    else
    {r = false;}
    }
    }
    
    }
    }
 return r;
    }
    //Caller for validate
    public boolean validator()
    {
        boolean rez = false;
    if(root == null)
    {}
    else
    {
    rez = validate(root);
    }
    return rez;
    }
}

