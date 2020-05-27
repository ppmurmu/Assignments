class MyCalculator extends MyStack
{
    private MyStack<Integer> numbers;
    private MyStack<Character> operators;
  public MyCalculator()
  {

  }

  public int calculate(String expression) throws EmptyStackException
  {
    numbers= new MyStack<Integer>();
    operators=new MyStack<Character>();
    for(int i=0;i<expression.length();i++)
    {
      char ch=expression.charAt(i);
      if(ch<='9' && ch>='0')
      {
        String t="";
        while(i<expression.length() && ch<='9' && ch>='0')
        {
          t=t+ch;
          i++;
          if(i<expression.length())
          {
              ch=expression.charAt(i);
          }
        }
        i--;
        int a=Integer.parseInt(t);
        t="";
        numbers.push(a);
      }
      else if(ch=='(')
      {
        operators.push(ch);
      }
      else if(ch=='+' || ch=='-' || ch=='*')
      {
        while(!operators.isEmpty() && precedence(operators.top())>=precedence(ch))
        {
          int a=result(operators.pop(),numbers.pop(),numbers.pop());
          numbers.push(a);
        }
        operators.push(ch);
      }
      else if(ch==')')
      {
        while(operators.top()!='(')
        {
          int a=result(operators.pop(),numbers.pop(),numbers.pop());
          numbers.push(a);
        }
        operators.pop();
      }

    }
    while(!operators.isEmpty())
      {
          int a=result(operators.pop(),numbers.pop(),numbers.pop());
          numbers.push(a);
      }
    int x=numbers.pop();
    return x;
  }

  public int precedence(char op)
    {
        if(op == '+' || op == '-')
        {
            return 1;
        }
        else if (op=='*')
        {
            return 2;
        }
        else
        {
            return 0;
        }
    }

  public int result(char ch,int a, int b)
  {
    if(ch=='+')
    {
      return b+a;
    }
    else if(ch=='-')
    {
      return b-a;
    }
    else if(ch=='*')
    {
      return b*a;
    }
    else
    {
        return 0;
    }
  }

}
