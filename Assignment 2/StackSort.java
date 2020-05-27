class StackSort extends MyStack
{
    public StackSort()
    {
    }
    public String[] sort(int[] nums) throws EmptyStackException
    {
        MyStack<Integer> ar= new MyStack<Integer>();
        int x=0,y=0;
        String[] s=new String[nums.length*2];
        ar.push(nums[0]);
        int t=-214748364;
        s[x]="PUSH";
        x++;
        int z=0;
        int c=0;
        for(int i=1;i<nums.length;i++)
        {
           if(nums[i]>ar.top())
           {
               while(nums[i]>ar.top())
               {
               t=ar.top();
               y=ar.pop();
               nums[z]=y;
               z++;
               s[x]="POP";
               x++;
               if(ar.isEmpty())
               {
                  break;
               }


              }
              ar.push(nums[i]);
               s[x]="PUSH";
               x++;
            }
           else if(nums[i]<=ar.top() && nums[i]>=t)
           {
               ar.push(nums[i]);
               s[x]="PUSH";
               x++;
           }
           else if(nums[i]<t)
           {
               c++;
               break;
            }
        }
        while(!ar.isEmpty())
        {
            y=ar.pop();
            nums[z]=y;
            z++;
            s[x]="POP";
            x++;
        }

        if(c==0)
        {

            return s;
        }
        else
        {
            String[] b={"NOTPOSSIBLE"};
            return b;
        }
    }
    public String[][] kSort(int[] nums) throws EmptyStackException
    {
        int x=0,y=0,z=0,j=0,k=0,c=0;
        MyStack<Integer> b= new MyStack<Integer>();
        String[][] smat=new String[200][nums.length*2];

        int t=-214748364;
        String[] temp=sort(nums);
        for(int f=0;f<temp.length;f++)
        {
            smat[0][f]=temp[f];
        }

        while(temp[0].equals("NOTPOSSIBLE"))
        {
            k=0;
            z=0;
            b.push(nums[0]);
            smat[j][k]="PUSH";
            k++;
            for(int i=1;i<nums.length;i++)
            {
                if(nums[i]>b.top())
                {
                    while(nums[i]>b.top())
                   {
                       t=b.top();
                       y=b.pop();
                       nums[z]=y;
                       z++;
                       smat[j][k]="POP";
                       k++;
                       if(b.isEmpty())
                       {
                           break;
                        }
                    }
                    b.push(nums[i]);
                    smat[j][k]="PUSH";
                    k++;
                }
                else
                {
                    b.push(nums[i]);
                    smat[j][k]="PUSH";
                    k++;
                }
            }
            while(!b.isEmpty())
            {
                y=b.pop();
                nums[z]=y;
                z++;
                smat[j][k]="POP";
                k++;
            }
            j++;
            temp=sort(nums);
        }
        for(int f=0;f<nums.length*2;f++)
        {
            smat[j][f]=temp[f];
        }

        String[][] s=new String[j+1][nums.length*2];
        for(int f=0;f<=j;f++)
        {
            for(int g=0;g<nums.length*2;g++)
            {
                s[f][g]=smat[f][g];
            }
        }
        return s;
    }

}
