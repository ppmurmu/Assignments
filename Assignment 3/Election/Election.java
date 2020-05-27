//package col106.assignment3.Election;
import java.util.LinkedList;
import java.util.Queue;
//import assignment3.BST.BST;
public class Election //implements ElectionInterface
{
	/*
	 * Do not touch the code inside the upcoming block
	 * If anything tempered your marks will be directly cut to zero
	*/
	/**public static void main() {
		ElectionDriverCode EDC = new ElectionDriverCode();
		System.setOut(EDC.fileout());
	}**/
	/*
	 * end code
	 */

	//write your code here
	/**static class Node<T,E>
	{
		T key;
		E value;
		Node<T,E> l,r;
		public Node(T k,E v)
		{
			key=k;
			value=v;
			l=r=null;
		}
	}**/
	String[][] data;
	BST<Integer, Integer> vot;
	int r;

	public Election()
	{
		data=new String[100][7];
		//vot=new BST();
		vot = new BST<Integer, Integer>();
		r=0;

	}
  public void insert(String name, String candID, String state, String district, String constituency, String party, String votes)
	{
		 int v=Integer.parseInt(votes);
		 int c=Integer.parseInt(candID);
		 vot.insert(c,v);
		 data[r][0]=candID;
		 data[r][1]=name;
		 data[r][2]=state;
		 data[r][3]=district;
		 data[r][4]=constituency;
		 data[r][5]=party;
		 data[r][6]=votes;
		 r++;


	}
	public String getParty(String c)
	{
		String t="";
		for(int i=0;i<r;i++)
		{
			if(c.equalsIgnoreCase(data[i][0]))
			{
				t=data[i][5];
				break;
			}
		}
		return t;
	}
	public String getName(String c)
	{
		String t="";
		for(int i=0;i<r;i++)
		{
			if(c.equalsIgnoreCase(data[i][0]))
			{
				t=data[i][1];

			}
		}
		return t;
	}
	public String getState(String c)
	{
		String t="";
		for(int i=0;i<r;i++)
		{
			if(c.equalsIgnoreCase(data[i][0]))
			{
				t=data[i][2];

			}
		}
		return t;
	}
	public String getDistrict(String c)
	{
		String t="";
		for(int i=0;i<r;i++)
		{
			if(c.equalsIgnoreCase(data[i][0]))
			{
				t=data[i][3];

			}
		}
		return t;
	}
	public String getCon(String c)
	{
		String t="";
		for(int i=0;i<r;i++)
		{
			if(c.equalsIgnoreCase(data[i][0]))
			{
				t= data[i][4];

			}
		}
		return t;
	}
	public String getVotes(String c)
	{
		String t="";
		for(int i=0;i<r;i++)
		{
			if(c.equalsIgnoreCase(data[i][0]))
			{
				t=data[i][6];

			}
		}
		return t;
	}


	public void updateVote(String name, String candID, String votes)
	{
		int v=Integer.parseInt(votes);
		int c=Integer.parseInt(candID);
		vot.update(c,v);

	}
	public void topkInConstituency(String constituency, String k)
	{
		String[][] topCon=new String[100][2];
		int j=0;
		for(int i=0;i<r;i++)
		{
			if(data[i][4].equalsIgnoreCase(constituency))
			{
				topCon[j][0]=data[i][0]; //to store ID
				topCon[j][1]=data[i][6]; //to store votes
				j++;
			}
		}
		//sorting th matrix
		for(int i=0;i<j-1;i++)
		{
			for(int m=0;m<j-1-i;m++)
			{
				int t1=Integer.parseInt(topCon[m][1]);
				int t2=Integer.parseInt(topCon[m+1][1]);
				if(t2>t1)
				{
					String t3=topCon[m][0];
					String t4=topCon[m][1];
					topCon[m][0]=topCon[m+1][0];
					topCon[m][1]=topCon[m+1][0];
					topCon[m+1][0]=t3;
					topCon[m+1][1]=t4;
				}
			}
		}
		int count=0;
		int x=Integer.parseInt(k);
		for(int i=0;i<x;i++)
		{
			System.out.println(getName(topCon[i][0])+", "+topCon[i][0]+ ", "+getParty(topCon[i][0]));
		}
	}
	public void leadingPartyInState(String state)
	{
		String[][] leadParty=new String[100][2];
		int j=0;
		for(int i=0;i<r;i++)
		{
			if(data[i][2].equalsIgnoreCase(state))
			{
				leadParty[j][0]=data[i][5]; //to store party
				leadParty[j][1]=data[i][6]; //to store votes
				j++;
			}

		}
		int max=0;
		String p="";int sum=0,t=0;
		for(int i=0;i<j;i++)
		{
			sum=0;
			for(int k=0;k<j;k++)
			{
					if(leadParty[i][0].equalsIgnoreCase(leadParty[k][0]))
					{
						t=Integer.parseInt(leadParty[i][1]);
						sum=sum+t;
					}
			}
			if(sum>max)
			{
				p=leadParty[i][0];
			}
		}
		System.out.println(p);

	}
	public void cancelVoteConstituency(String constituency)
	{
		for(int i=0;i<r;i++)
		{
			if(constituency.equalsIgnoreCase(data[i][4]))
			{
				int temp=Integer.parseInt(data[i][0]);
				vot.delete(temp);
			}
		}
	}
	public void leadingPartyOverall()
	{
		int sum=0,t=0,max=0;
		String p="";
		for(int i=0;i<r;i++)
		{
			sum=0;
			for(int k=0;k<r;k++)
			{
				if(data[i][5].equalsIgnoreCase(data[k][5]))
				{
					t=Integer.parseInt(data[i][6]);
					sum=sum+t;
				}
			}
			if(sum>max)
			{
				p=data[i][5];
			}

		}



	}
	public void voteShareInState(String party,String state)
	{
		int sum=0,t=0;
		for(int i=0;i<r;i++)
		{

			if(state.equalsIgnoreCase(data[i][2]) && party.equalsIgnoreCase(data[i][5]))
			{
				t=Integer.parseInt(data[i][6]);
				sum=sum+t;
			}
		}
		int totsum=0;
		for(int i=0;i<r;i++)
		{
			if(state.equalsIgnoreCase(data[i][2]))
			{
				t=Integer.parseInt(data[i][6]);
				totsum=totsum+t;
			}
		}
		int x=(int)(sum/totsum * 1000);

		System.out.println(x/10);



	}

	public void printElectionLevelOrder()
	{
		if(vot.root==null)
		{
			return;
		}
		Queue<BST.Node> q = new LinkedList<>();
		q.add(vot.root);
		q.add(null);
		while (!q.isEmpty())
		{
			BST.Node curr = q.poll();
			if (curr == null)
			{
				if (!q.isEmpty())
				{
					q.add(null);

				}
			}
			else
			{
				if (curr.l != null)
				{
					q.add(curr.l);
				}
			// Pushing right child current node
				if (curr.r != null)
				{
					q.add(curr.r);
				}
				int temp=(int)curr.key;
				String ts=Integer.toString(temp);
				System.out.println(getName(ts)+", "+curr.key+ ", "+getState(ts) + ", "+ getDistrict(ts) + ", "+ getCon(ts)+ ", "+ getParty(ts) + ", "+ curr.value);
			}
		}

	}
}
