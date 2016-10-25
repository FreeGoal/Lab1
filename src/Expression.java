import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Expression {
	
	public int variablenumber;
	public int termnumber;
    public int[][] variablecount = new int[10][10];
	ArrayList variable = new ArrayList();
	public int[] factor = new int[10];
	


	@SuppressWarnings("rawtypes")
	public static void main(final String[] args) {
		// TODO �Զ����ɵķ������
		int flag = 0;
		Expression expr = new Expression();
		while(true)
		{
			final InputStreamReader isr = new InputStreamReader(System.in); 
			final BufferedReader bffff = new BufferedReader(isr);
			try{
				
				final String str = bffff.readLine();//input by users
				//match
				final String reg1 = "!simplify";
				final String reg2 = "!d/d";
				final Pattern pat1 = Pattern.compile(reg1);
				final Matcher mat1 = pat1.matcher(str);
				final Pattern pat2 = Pattern.compile(reg2);
				final Matcher mat2 = pat2.matcher(str);
				//deal
				if(mat1.find())
				{
					//showexpr(expr);
					if(flag == 0)
						System.out.println("Error!");
					else
					{
						expr = simplify(str,expr);
						showexpr(expr);
					}
					
				}else if(mat2.find())
				{
					if(flag == 0){
						System.out.println("Error!");}
					else{
						expr = derivative(str,expr);}
					showexpr(expr);
				}else
				{
					expr = deal(str,expr);
					flag = 1;
					showexpr(expr); 
				}
			}catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace(); 
			}		
		}
	}

	public static Expression deal(final String str,final Expression ori)
	{
		final long aaaa=System.currentTimeMillis();
		System.out.println("ʼ�ڣ�"+aaaa);
		int state = 0;//�Զ���״̬
			//System.out.println(str);
			for(int i = 0 ; i < str.length(); i ++)//������ı��ʽ���кϷ����ж�
			{
				switch(state)
				{
				case 0://��ʼ״̬�µ�ת�ƺ���
				{
					if(str.charAt(i) >= 48 && str.charAt(i) <= 57)
					{
						state = 1;
					}else if(str.charAt(i) >= 97 && str.charAt(i) <= 122)
					{
						state = 2;
					}else if(str.charAt(i) == 43 || str.charAt(i) == 42)
					{
						state = 3;
					}else
					{
						state = 3;
					}
					break;		
				}
				case 1://β��Ϊ����ʱ��ת�ƺ���
				{
					if(str.charAt(i) >= 48 && str.charAt(i) <= 57)
					{
						state = 1;
					}else if(str.charAt(i) >= 97 && str.charAt(i) <= 122)
					{
						state = 3;
					}else if(str.charAt(i) == 43 || str.charAt(i) == 42)
					{
						state = 4;
					}else
					{
						state = 3; 
					}
					break;	
				}
				case 2://β��Ϊ����ʱ��ת�ƺ���
				{
					if(str.charAt(i) >= 48 && str.charAt(i) <= 57)
					{
						state = 3;
					}else if(str.charAt(i) >= 97 && str.charAt(i) <= 122)
					{
						state = 3;
					}else if(str.charAt(i) == 43 || str.charAt(i) == 42)
					{
						state = 4;
					}else
					{
						state = 3;
					}		
					break;	
				}
				case 3://�Ƿ�״̬
				{
					break;	
				}
				case 4://β��Ϊ����ʱ��ת�ƺ���
				{
					if(str.charAt(i) >= 48 && str.charAt(i) <= 57)
					{
						state = 1;
					}else if(str.charAt(i) >= 97 && str.charAt(i) <= 122)
					{  
						state = 2;
					}else if(str.charAt(i) == 43 || str.charAt(i) == 42)
					{
						state = 3;
					}else
					{
						state = 3;
					}
					break;		
				}
				default:
					break;
				}
				//System.out.println(state);
				if(state == 3)
				{
					System.out.println("Error!");
					System.out.println("���ڣ�"+System.currentTimeMillis());
					System.out.println("ִ�к�ʱ : "+(System.currentTimeMillis()-aaaa)+" ���� ");
					return ori;
				}			
			}
			if(state == 1 || state == 2)
			{
				//System.out.println(str);
			}else if(state == 4)
			{
				System.out.println("Error!");
				System.out.println("���ڣ�"+System.currentTimeMillis());
				System.out.println("ִ�к�ʱ : "+(System.currentTimeMillis()-aaaa)+" ���� ");
				return ori;
			}
			//�γ����ݽṹ
			Expression expr = new Expression();
			for(int i = 0; i < 10; i ++)
			{
				expr.factor[i] = 1;
			}
			//System.out.println(Expr.termnumber);
			//System.out.println(Expr.variablenumber);
			state = 0;//���״̬λ
			for(int i = 0; i < str.length(); i ++)//�γ����ݽṹ���Զ���
			{
				if(str.charAt(i) >= 48 && str.charAt(i) <= 57)
				{
					if(state == 1)
					{
						expr.factor[expr.termnumber] = expr.factor[expr.termnumber] *10 + (str.charAt(i) - 48);
					}else if(state == 0)
					{
						expr.factor[expr.termnumber] = expr.factor[expr.termnumber] * (str.charAt(i) - 48);
						state = 1;
					}
				}else if(str.charAt(i) >= 97 && str.charAt(i) <= 122)
				{  
					if(expr.variable.contains(str.charAt(i)))
					{
						int index = expr.variable.indexOf(str.charAt(i));
						//System.out.println(index+"yes");
						expr.variablecount[index][expr.termnumber] ++;
					}else
					{
						expr.variable.add(str.charAt(i));
						final int index = expr.variable.indexOf(str.charAt(i));
						//System.out.println(index+"first");
						expr.variablecount[index][expr.termnumber] ++;
						expr.variablenumber ++;
					}
					state = 0;
				}else if(str.charAt(i) == 43)
				{
					expr.termnumber ++;
					state = 0;
				}else if(str.charAt(i) == 42)
				{
					state = 0;
				}
				//System.out.println(Expr.factor[Expr.termnumber]);
			}
			//System.out.println("trmn"+Expr.termnumber);
			//System.out.println("vrbn"+Expr.variablenumber);
			
			for(int i = 0; i < expr.termnumber + 1; i ++)
			{
				for(int j = 0; j < expr.variablenumber; j ++)
				{
					//System.out.println(i+"a"+j);
					//System.out.println(Expr.variablecount[j][i]);
				}
			}
			
			//showexpr(Expr);
			System.out.println("���ڣ�"+System.currentTimeMillis());
			System.out.println("ִ�к�ʱ : "+(System.currentTimeMillis()-aaaa)+" ���� ");	
		return expr;	
	}

	
	static Expression simplify(final String str,final Expression smpl)
	{
		final long tttt=System.currentTimeMillis();
		System.out.println("ʼ�ڣ�"+tttt);
		final String regex = "[a-z]=[0-9]+";
		final Pattern pat = Pattern.compile(regex);
		final Matcher mat = pat.matcher(str);
		while(mat.find())
		{
			//System.out.println(mat.group());
			final String val = mat.group();
			if(!smpl.variable.contains(val.charAt(0)))
			{
				System.out.println("no this variable");
				continue;
			}

			final int index = smpl.variable.indexOf(val.charAt(0));
			final String aaaaa = val.substring(2,val.length());
			final int value = Integer.parseInt(aaaaa);
			//System.out.println("value="+value);
			for(int i = 0; i < smpl.termnumber + 1; i ++)
			{
				if(smpl.variablecount[index][i] != 0)
				{
					final int count = smpl.variablecount[index][i];
					for(int j = 0; j < count; j ++)
					{
						smpl.factor[i] = smpl.factor[i] * value;
					}
					smpl.variablecount[index][i] = 0;
				}
			}
		}
		int flag1 = smpl.termnumber + 1;
		int flag2[] = new int[10];

		for(int i = 0; i < smpl.termnumber + 1; i ++)
		{
			for(int j = 0; j < smpl.variablenumber; j ++)
			{
				if(smpl.variablecount[j][i] != 0)
				{	flag1 --;
					flag2[i] ++;
					break;
				}	
			}
		}
		
		final int iii=1;
		if(flag1 > iii)
		{
			int sum = 0;
			for(int i = 0; i < smpl.termnumber + 1; i ++)
			{
				if(flag2[i] == 0)
				{
					sum += smpl.factor[i];
				}
			}
			final Expression rtn = new Expression();
			rtn.termnumber = smpl.termnumber - flag1 + 1;
			for(int i = 0; i < rtn.termnumber; i ++)
			{
				for(final int j = 0; i < smpl.termnumber + 1; i ++)
				{
					if(flag2[j] != 0)
					{
						rtn.factor[i] = smpl.factor[j];
						rtn.variable = smpl.variable;
						rtn.variablenumber = smpl.variablenumber;
						for(int k = 0; k < rtn.variablenumber; k ++)
							rtn.variablecount[k][i] = smpl.variablecount[k][j];
					}
				}
			}
			rtn.factor[rtn.termnumber] = sum;
			System.out.println("���ڣ�"+System.currentTimeMillis());
			System.out.println("ִ�к�ʱ : "+(System.currentTimeMillis()-tttt)+" ���� ");
			//showexpr(rtn);
			return rtn;
		}
		//showexpr(smpl);
		System.out.println("���ڣ�"+System.currentTimeMillis());
		System.out.println("ִ�к�ʱ : "+(System.currentTimeMillis()-tttt)+" ���� ");	
		return smpl; 
	}  
	
	static Expression derivative(final String str,final Expression expr)
	{

		final long tttt=System.currentTimeMillis();
		System.out.println("ʼ�ڣ�"+tttt);
		final char aaaa = str.charAt(5);
		
		if(!expr.variable.contains(aaaa))
		{
			System.out.println("no this variable");
			return expr;
		}
			
		else
		{
			final Expression der = new Expression();
			int count = 0;
			final int index = expr.variable.indexOf(aaaa);
			for(int i = 0; i < expr.termnumber + 1; i ++)
			{
				if(expr.variablecount[index][i] != 0)
				{
					der.factor[count] = expr.factor[i]*expr.variablecount[index][i];
					der.variablecount[index][count] = expr.variablecount[index][i] - 1;
					for(int j = 0; j < expr.variablenumber; j ++)
					{
						if(j != index){
							der.variablecount[j][count] = expr.variablecount[j][i];}
					}
					count ++;
				}
			}
			der.termnumber = count - 1;
			der.variable = expr.variable;
			der.variablenumber = expr.variablenumber;
			//showexpr(der);
			System.out.println("���ڣ�"+System.currentTimeMillis());
			System.out.println("ִ�к�ʱ : "+(System.currentTimeMillis()-tttt)+" ���� ");
			return der;
		}
	}
	
	static void showexpr(final Expression show){
		for(int i = 0 ; i < show.termnumber + 1; i ++)
		{
			int nmmm = 0;
			int mmmm = 0;
			if(i != 0)
			{
				System.out.print('+');
				nmmm = 0;
			}
			if(show.factor[i]!=1)
			{
				System.out.print(show.factor[i]);
				nmmm = 1;
			}
				
			for(int j = 0; j < show.variablenumber; j ++)
			{
				for(int k = 0; k < show.variablecount[j][i]; k ++)
				{
					if(mmmm != 0 || nmmm == 1)
						System.out.print('*');
					System.out.print(show.variable.get(j));
					mmmm ++;
				}
			}
		}
		System.out.println();
	}

}