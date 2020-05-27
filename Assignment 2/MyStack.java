
class MyStack<T>
{
    private T[] ar;
    private int top;
    public MyStack()
    {
        ar = (T[])new Object[1000000];
        top=-1;
    }
    public void push(T value)
    {
        ar[++top]=value;
        //top++;
    }
    public T pop() throws EmptyStackException
    {
        if(isEmpty())
        {

            System.out.println("Stack is empty");
            throw new EmptyStackException();
        }
        else
        {
            return ar[top--];
        }
    }
    public T top() throws EmptyStackException
    {
        if(isEmpty())
        {
            System.out.println("Stack is empty");
            throw new EmptyStackException();
        }
        else
        {
            return ar[top];
        }
    }
    public boolean isEmpty()
    {
        if(top==-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
