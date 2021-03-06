import java.util.*;
import java.text.DecimalFormat;
import java.io.*;
import java.lang.*;
class TwoDBlockMatrix
{
    public float[][] mat;
    public int row;
    public int col;
    TwoDBlockMatrix(float[][] array)
    {
        row=array.length;
        col=array[0].length;
        mat=new float[row][col];
        for(int i=0;i<array.length;i++)
        {
            for(int j=0;j<array[0].length;j++)
            {
                mat[i][j]=array[i][j];
            }
        }
        
    }   
    
    public static TwoDBlockMatrix buildTwoDBlockMatrix(java.io.InputStream in)
    {
        Scanner sc=new Scanner(in);
        List<String> at=new ArrayList<String>();
        String t="";
        while(sc.hasNextLine())
        {
            t=sc.nextLine();
            at.add(t);
        }
        List<String> a=new ArrayList<String>();
        a.add("#");
        for(int i=0;i<at.size();i++)
        {
            a.add(at.get(i));
        }
        int asiz=a.size();
        int c=0;
        t=a.get(1);
        t.trim();
        t=t.substring(0,t.indexOf(" "));
        int b=Integer.valueOf(t);
        t=a.get(2);
        c=0;int d=0; int cv;
        int rowm=b;
        for(int i=0;i<asiz-1;i++)
        {
            if(a.get(i).equals("#"))
            {
                t=a.get(i+1);
                t=t.substring(0,t.indexOf(" "));
                b=Integer.parseInt(t);
                c=0;
                d=i+2;
                t=a.get(d);
                while(!t.equals("#"))
                {
                    c++;
                    d++;
                    t=a.get(d);
                }
                cv=b+c-1;
                if(cv>rowm)
                {
                    rowm=cv;
                }
            }
        }
        
               
        
        
        String t1="";
        b=0; int r=0;
        t=a.get(1);
        t=t.trim();        
        t=t.substring(t.indexOf(" ")+1,t.length());
        b=Integer.valueOf(t);
        t=a.get(2);
        t=t+" ";
        c=0;
        for(int j=0;j<t.length();j++)
        {
            char ch=t.charAt(j);
            if(ch==' ')
                {
                    c++;
                 }
        }            
        int max=b+c-1;        
        for(int i=1;i<asiz-1;i++)
        {
            if(a.get(i).equals("#"))
            {
                t=a.get(i+1);                
                t=t.substring(t.indexOf(" ")+1,t.length());                
                b=Integer.parseInt(t);
                t1=a.get(i+2);
                t1=t1+" ";
                c=0;
                for(int j=0;j<t1.length();j++)
                {
                    char ch=t1.charAt(j);
                    if(ch==' ')
                    {
                        c++;
                    }
                }
                r=b+c-1;
                if(r>max)
                {
                    max=r;
                }
            }
        }
        float[][] mat=new float[rowm][max];
        List<String> revarray=new ArrayList<String>();
        revarray.add("#");
        for(int i=0;i<a.size();i++)
        {
            revarray.add(a.get(i));
        }
        //we have created a floating point 2D array with the dimension
        t="";
        t=a.get(0);        
        t1="";
        int j=0,k=0;
        d=0;
        int x;
        for(int i=0;i<a.size()-1;i++)
        {
            if(a.get(i).equals("#"))
            {                
                t=a.get(i+1);
                String[] ar=t.split(" ");
                int nj=Integer.parseInt(ar[0].trim());
                int nk=Integer.parseInt(ar[1].trim());
                j=nj-1;
                k=nk-1;                                
                t1=a.get(i+2);
                t1=t1.substring(0,t1.length()-1);
                t1=t1+" ";
                d=i+2;                
                while(!t1.equals("#"))
                {
                    String t2="";
                    for(x=0;x<t1.length();x++)
                    {
                        float t3;
                        char ch=t1.charAt(x);
                        
                        if(ch!=' ')
                        {
                            t2=t2+ch;
                            
                            
                        }
                        else
                        {
                            t2=t2.trim();
                            t3=Float.parseFloat(t2);
                            
                            mat[j][k]=t3;
                            if(ch==' ')
                            {
                                k++;
                            }
                            
                            t2="";
                        }                                                
                    }
                    k=nk-1;
                    j++;
                 d++;                 
                 t1=a.get(d);
                 if(!t1.equals("#"))
                 {
                     t1=t1.substring(0,t1.length()-1);
                     t1=t1+" ";
                    }                                                                                                                       
                }
                t1="";
            }
        }
        for(int i=0;i<mat.length;i++)
        {
            for(j=0;j<mat[0].length;j++)
            {
                DecimalFormat format2dec = new DecimalFormat("0.00");
                String axe=format2dec.format(mat[i][j]);
                mat[i][j]=Float.parseFloat(axe);
                
                }
        }
        TwoDBlockMatrix mati=new TwoDBlockMatrix(mat);
        return mati;
    }
    public TwoDBlockMatrix transpose()
    {
        float[][] a=new float[col][row];
        int k=0,l=0;
        for(int i=0;i<a[0].length;i++)
        {
            for(int j=0;j<a.length;j++)
            {
                a[j][i]=mat[k][l];
                l++;
            }
            k++;
            l=0;
        }
        TwoDBlockMatrix mati=new TwoDBlockMatrix(a);
        return mati;
    }
    public TwoDBlockMatrix multiply(TwoDBlockMatrix other) throws IncompatibleDimensionException
    {        
        float prod[][]=new float[row][other.col];
        float sum=0;
        if(other.row!= col)
        {
            throw new IncompatibleDimensionException();
        }
            
        
        
            for(int i=0;i<prod.length;i++)
            {
                for(int j=0;j<prod[0].length;j++)
                {
                    sum=0;
                    for(int k=0;k<col;k++)
                    {
                        sum+=this.mat[i][k]*other.mat[k][j];
                    }
                    prod[i][j]=sum;
                    sum=0;
                }
            }
        for(int i=0;i<prod.length;i++)
        {
            for(int j=0;j<prod[0].length;j++)
            {
                DecimalFormat format2dec = new DecimalFormat("0.00");
                String axe=format2dec.format(prod[i][j]);
                prod[i][j]=Float.parseFloat(axe);
                
                }
        }    
        
        TwoDBlockMatrix mati=new TwoDBlockMatrix(prod);
        return mati;
    }
    public TwoDBlockMatrix getSubBlock(int row_start, int col_start, int row_end, int col_end ) throws SubBlockNotFoundException
    {
        
        float sb[][]=new float[row_end-row_start + 1][col_end - col_start + 1];        
        if(col_end-1>mat[0].length || row_end-1>mat.length || col_end==col_start || row_end == row_start )
        {
            throw new SubBlockNotFound               Exception();
        }
        
        
            int k=0,l=0;
            for(int i=row_start-1;i<=row_end-2;i++)
            {
                for(int j=col_start-1;j<=col_end-2;j++)
                {
                    sb[k][l]=mat[i][j];
                    l++;
                }
                l=0;
                k++;
            }
        
        TwoDBlockMatrix mati=new TwoDBlockMatrix(sb);
        return mati;
    }
    public String toString()
    {
        TwoDBlockMatrix ar=new TwoDBlockMatrix(mat);
        int c=0;
        int fr=0,fc=0,d;
        int cons=ar.col -1;
        String o="";
        String p="";
        int t1,t2;
        
        ArrayList<String> rep = new ArrayList<String>();
        for(int i=0;i<ar.row;i++)
        {
            c=0;
            for(int j=0;j<ar.col;j++)
            {                                    
                if(ar.mat[i][j]!=0)
                {
                    c++;
                    if(c==1)
                    {
                        
                        p=(i+1)+" " +(j+1);
                        
                        rep.add(p);
                        p="";
                        fr=i;
                        fc=j;
                    }
                }
                else
                {
                    for(int x=0;x<ar.row;x++)                    
                    {
                        d=0;
                        for(int y=fc;y<ar.col;y++)
                        {
                                                  
                            if(ar.mat[x][y]!=0 && y<fc+c)
                            {
                                d++;
                                
                            }
                            else
                            {
                                break;
                            }
                        }
                        if(c==d)
                        {
                            for(int z=fc;z<fc+c;z++)
                            {
                                o=o+ar.mat[x][z]+" ";
                                ar.mat[x][z]=0;
                            }
                            o=o.trim();
                            if(!o.equals(""))
                            {
                                o=o+";";
                                rep.add(o);
                                o="";
                                
                            }
                        }
                        
                        
                    }
                    if(c!=0)
                    {
                        rep.add("#");
                    }
                    c=0;
                }
                if(j==(ar.col-1))
                {
                    for(int x=0;x<ar.row;x++)
                    {
                        d=0;
                        for(int y=fc;y<ar.col;y++)
                        {
                            
                            if(ar.mat[x][y]!=0 && y<fc+c)
                            {
                                d++;
                            }
                            else
                            {
                                break;
                            }
                        }
                        if(c==d)
                        {
                            for(int z=fc;z<fc+c;z++)
                            {
                                o=o+ar.mat[x][z]+" ";
                                ar.mat[x][z]=0;
                            }
                            o=o.trim();
                            
                            if(!o.equals(""))
                            {
                                o=o+";";
                                rep.add(o);
                                o="";
                                
                            }
                        }
                        
                    }
                    if(c!=0)
                    {
                        rep.add("#");
                    }
                    c=0;
                }
                
            }
        }
        String ret="";
        for(int i=0;i<rep.size();i++)
        {
            ret=ret + rep.get(i)+"\n";
        }
        return ret;
    }
    public TwoDBlockMatrix inverse() throws InverseDoesNotExistException
    {
       
    float det = determinant(mat, mat.length);
    if(det==0)
    {
        throw new InverseDoesNotExistException();
    }
    
    float [][]adj = new float[mat.length][mat.length];
    float [][]inv = new float[mat.length][mat.length];
    float [][]t = new float[mat.length][mat.length];   
    /**if (mat.length == 1) 
    { 
        adj[0][0] = 1; 
        return; 
    } **/
    int s= 1; 
    
    det = determinant(mat, mat.length);
    for (int i = 0; i < mat.length; i++) 
    { 
        for (int j = 0; j < mat.length; j++) 
        {  
            coFactor(mat, t, i, j, mat.length); 
            if((i + j) % 2 == 0)
            {
                s=1;
            }
            else
            {
                s=-1;
            }
            
            adj[j][i] = (s)*(determinant(t, mat.length-1)); 
        } 
    } 
    for (int i = 0; i < mat.length; i++)
    {
        for (int j = 0; j < mat.length; j++)
        {
            inv[i][j] = adj[i][j]/(float)det; 
        }
    }
    
    
    TwoDBlockMatrix mati=new TwoDBlockMatrix(inv);
        return mati;
        
    }
   
static float determinant(float ar[][], int as) 
{ 
    float d= 0;
    int n=ar.length; 
    if (n == 1)
    {
        return ar[0][0]; 
    }
    float [][]t = new float[n][n];
    int s= 1; 
    for (int f = 0; f < as; f++) 
    { 
        t=coFactor(ar, t, 0, f, as); 
        d=d+ s*ar[0][f] * determinant(t, as - 1); 
        s = -s; 
    } 
    return d; 
} 
static float[][] coFactor(float ar[][], float t[][], int k, int l, int n) 
{ 
    t=new float[n][n];
    int i = 0, j = 0; 
    for (int r= 0; r < n; r++) 
    { 
        for (int c = 0; c< n; c++) 
        { 
            if (r != k && c != l) 
            { 
                j++;
                t[i][j] = ar[r][c]; 
                if (j == n - 1) 
                { 
                    j = 0; 
                    i++; 
                } 
            } 
        } 
    }
    return t;
} 

}

    
    
    
    
    
    
        

        
        
        

        
                
                
            
        
            
            
        

        
            
            
        
        