import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
<<<<<<< HEAD
import java.util.*;

public class Expression
{

    int VariableNumber;
    int TermNumber;
    int[][] VariableCount = new int[10][10];
<<<<<<< HEAD
    ArrayList Variable = new ArrayList();
    int[] Factor = new int[10];

    @SuppressWarnings("rawtypes")
    public static void main(String[] args)
    {
        // TODO 自动生成的方法存根
        int flag = 0;
        Expression expr = new Expression();
        while (true)
        {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader bf = new BufferedReader(isr);
            try
            {

                String str = bf.readLine();// input by users
                // match
                String reg1 = "!simplify";
                String reg2 = "!d/d";
                Pattern pat1 = Pattern.compile(reg1);
                Matcher mat1 = pat1.matcher(str);
                Pattern pat2 = Pattern.compile(reg2);
                Matcher mat2 = pat2.matcher(str);
                // deal
                if (mat1.find())
                {
                    // showexpr(expr);
                    if (flag == 0)
                        System.out.println("Error!");
                    else
                    {
                        expr = simplify(str, expr);
                        showexpr(expr);
                    }

                }
                else if (mat2.find())
                {
                    if (flag == 0)
                        System.out.println("Error!");
                    else
                        expr = derivative(str, expr);
                    showexpr(expr);
                }
                else
                {
                    expr = deal(str, expr);
                    flag = 1;
                    showexpr(expr);
                }
            }
            catch (IOException e)
            {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }

    public static Expression deal(String str, Expression ori)
    {
        long a = System.currentTimeMillis();
        System.out.println("始于：" + a);
        int state = 0;// 自动机状态
        // System.out.println(str);
        for (int i = 0; i < str.length(); i++)// 对输入的表达式进行合法性判断
        {
            switch (state) {
                case 0:// 初始状态下的转移函数
                {
                    if (str.charAt(i) >= 48 && str.charAt(i) <= 57)
                    {
                        state = 1;
                    }
                    else if (str.charAt(i) >= 97 && str.charAt(i) <= 122)
                    {
                        state = 2;
                    }
                    else if (str.charAt(i) == 43 || str.charAt(i) == 42)
                    {
                        state = 3;
                    }
                    else
                    {
                        state = 3;
                    }
                    break;
                }
                case 1:// 尾数为数字时的转移函数
                {
                    if (str.charAt(i) >= 48 && str.charAt(i) <= 57)
                    {
                        state = 1;
                    }
                    else if (str.charAt(i) >= 97 && str.charAt(i) <= 122)
                    {
                        state = 3;
                    }
                    else if (str.charAt(i) == 43 || str.charAt(i) == 42)
                    {
                        state = 4;
                    }
                    else
                    {
                        state = 3;
                    }
                    break;
                }
                case 2:// 尾数为变量时的转移函数
                {
                    if (str.charAt(i) >= 48 && str.charAt(i) <= 57)
                    {
                        state = 3;
                    }
                    else if (str.charAt(i) >= 97 && str.charAt(i) <= 122)
                    {
                        state = 3;
                    }
                    else if (str.charAt(i) == 43 || str.charAt(i) == 42)
                    {
                        state = 4;
                    }
                    else
                    {
                        state = 3;
                    }
                    break;
                }
                case 3:// 非法状态
                {
                    break;
                }
                case 4:// 尾数为符号时的转移函数
                {
                    if (str.charAt(i) >= 48 && str.charAt(i) <= 57)
                    {
                        state = 1;
                    }
                    else if (str.charAt(i) >= 97 && str.charAt(i) <= 122)
                    {
                        state = 2;
                    }
                    else if (str.charAt(i) == 43 || str.charAt(i) == 42)
                    {
                        state = 3;
                    }
                    else
                    {
                        state = 3;
                    }
                    break;
                }
                default:
                    break;
            }
            // System.out.println(state);
            if (state == 3)
            {
                System.out.println("Error!");
                System.out.println("终于：" + System.currentTimeMillis());
                System.out.println("执行耗时 : " + (System.currentTimeMillis() - a) + " 毫秒 ");
                return ori;
            }
        }
        if (state == 1 || state == 2)
        {
            // System.out.println(str);
        }
        else if (state == 4)
        {
            System.out.println("Error!");
            System.out.println("终于：" + System.currentTimeMillis());
            System.out.println("执行耗时 : " + (System.currentTimeMillis() - a) + " 毫秒 ");
            return ori;
        }
        // 形成数据结构
        Expression Expr = new Expression();
        for (int i = 0; i < 10; i++)
        {
            Expr.Factor[i] = 1;
        }
        // System.out.println(Expr.TermNumber);
        // System.out.println(Expr.VariableNumber);
        state = 0;// 清空状态位
        for (int i = 0; i < str.length(); i++)// 形成数据结构的自动机
        {
            if (str.charAt(i) >= 48 && str.charAt(i) <= 57)
            {
                if (state == 1)
                {
                    Expr.Factor[Expr.TermNumber] = Expr.Factor[Expr.TermNumber] * 10 + (str.charAt(i) - 48);
                }
                else if (state == 0)
                {
                    Expr.Factor[Expr.TermNumber] = Expr.Factor[Expr.TermNumber] * (str.charAt(i) - 48);
                    state = 1;
                }
            }
            else if (str.charAt(i) >= 97 && str.charAt(i) <= 122)
            {
                if (Expr.Variable.contains(str.charAt(i)))
                {
                    int index = Expr.Variable.indexOf(str.charAt(i));
                    // System.out.println(index+"yes");
                    Expr.VariableCount[index][Expr.TermNumber]++;
                }
                else
                {
                    Expr.Variable.add(str.charAt(i));
                    int index = Expr.Variable.indexOf(str.charAt(i));
                    // System.out.println(index+"first");
                    Expr.VariableCount[index][Expr.TermNumber]++;
                    Expr.VariableNumber++;
                }
                state = 0;
            }
            else if (str.charAt(i) == 43)
            {
                Expr.TermNumber++;
                state = 0;
            }
            else if (str.charAt(i) == 42)
            {
                state = 0;
            }
            // System.out.println(Expr.Factor[Expr.TermNumber]);
        }
        // System.out.println("trmn"+Expr.TermNumber);
        // System.out.println("vrbn"+Expr.VariableNumber);

        for (int i = 0; i < Expr.TermNumber + 1; i++)
        {
            for (int j = 0; j < Expr.VariableNumber; j++)
            {
                // System.out.println(i+"a"+j);
                // System.out.println(Expr.VariableCount[j][i]);
            }
        }

        // showexpr(Expr);
        System.out.println("终于：" + System.currentTimeMillis());
        System.out.println("执行耗时 : " + (System.currentTimeMillis() - a) + " 毫秒 ");
        return Expr;
    }

    static Expression simplify(String str, Expression smpl)
    {
        long t = System.currentTimeMillis();
        System.out.println("始于：" + t);
        String regex = "[a-z]=[0-9]+";
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(str);
        while (mat.find())
        {
            // System.out.println(mat.group());
            String val = mat.group();
            if (!smpl.Variable.contains(val.charAt(0)))
            {
                System.out.println("no this variable");
                continue;
            }

            int index = smpl.Variable.indexOf(val.charAt(0));
            String a = val.substring(2, val.length());
            int value = Integer.parseInt(a);
            // System.out.println("value="+value);
            for (int i = 0; i < smpl.TermNumber + 1; i++)
            {
                if (smpl.VariableCount[index][i] != 0)
                {
                    int count = smpl.VariableCount[index][i];
                    for (int j = 0; j < count; j++)
                    {
                        smpl.Factor[i] = smpl.Factor[i] * value;
                    }
                    smpl.VariableCount[index][i] = 0;
                }
            }
        }
        int flag1 = smpl.TermNumber + 1;
        int flag2[] = new int[10];

        for (int i = 0; i < smpl.TermNumber + 1; i++)
        {
            for (int j = 0; j < smpl.VariableNumber; j++)
            {
                if (smpl.VariableCount[j][i] != 0)
                {
                    flag1--;
                    flag2[i]++;
                    break;
                }
            }
        }
        if (flag1 > 1)
        {
            int sum = 0;
            for (int i = 0; i < smpl.TermNumber + 1; i++)
            {
                if (flag2[i] == 0)
                {
                    sum += smpl.Factor[i];
                }
            }
            Expression rtn = new Expression();
            rtn.TermNumber = smpl.TermNumber - flag1 + 1;
            for (int i = 0; i < rtn.TermNumber; i++)
            {
                for (int j = 0; i < smpl.TermNumber + 1; i++)
                {
                    if (flag2[j] != 0)
                    {
                        rtn.Factor[i] = smpl.Factor[j];
                        rtn.Variable = smpl.Variable;
                        rtn.VariableNumber = smpl.VariableNumber;
                        for (int k = 0; k < rtn.VariableNumber; k++)
                            rtn.VariableCount[k][i] = smpl.VariableCount[k][j];
                    }
                }
            }
            rtn.Factor[rtn.TermNumber] = sum;
            System.out.println("终于：" + System.currentTimeMillis());
            System.out.println("执行耗时 : " + (System.currentTimeMillis() - t) + " 毫秒 ");
            // showexpr(rtn);
            return rtn;
        }
        // showexpr(smpl);
        System.out.println("终于：" + System.currentTimeMillis());
        System.out.println("执行耗时 : " + (System.currentTimeMillis() - t) + " 毫秒 ");
        return smpl;
    }

    static Expression derivative(String str, Expression expr)
    {

        long t = System.currentTimeMillis();
        System.out.println("始于：" + t);
        char a = str.charAt(5);

        if (!expr.Variable.contains(a))
        {
            System.out.println("no this variable");
            return expr;
        }

        else
        {
            Expression der = new Expression();
            int count = 0;
            int index = expr.Variable.indexOf(a);
            for (int i = 0; i < expr.TermNumber + 1; i++)
            {
                if (expr.VariableCount[index][i] != 0)
                {
                    der.Factor[count] = expr.Factor[i] * expr.VariableCount[index][i];
                    der.VariableCount[index][count] = expr.VariableCount[index][i] - 1;
                    for (int j = 0; j < expr.VariableNumber; j++)
                    {
                        if (j != index)
                            der.VariableCount[j][count] = expr.VariableCount[j][i];
                    }
                    count++;
                }
            }
            der.TermNumber = count - 1;
            der.Variable = expr.Variable;
            der.VariableNumber = expr.VariableNumber;
            // showexpr(der);
            System.out.println("终于：" + System.currentTimeMillis());
            System.out.println("执行耗时 : " + (System.currentTimeMillis() - t) + " 毫秒 ");
            return der;
        }

    }
=======
	ArrayList Variable = new ArrayList();
	int[] Factor = new int[10];
	
=======
public class Expression {
	
	public int variablenumber;
	public int termnumber;
    public int[][] variablecount = new int[10][10];
	ArrayList variable = new ArrayList();
	public int[] factor = new int[10];
>>>>>>> refs/remotes/origin/master
	


	@SuppressWarnings("rawtypes")
<<<<<<< HEAD
	public static void main(String[] args) {
		
=======
	public static void main(final String[] args) {
		// TODO 自动生成的方法存根
>>>>>>> refs/remotes/origin/master
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
				// TODO 自动生成的 catch 块
				e.printStackTrace(); 
			}		
		}
	}
>>>>>>> lab4

<<<<<<< HEAD
    static void showexpr(Expression show)
    {
        for (int i = 0; i < show.TermNumber + 1; i++)
        {
            int n = 0;
            int m = 0;
            if (i != 0)
            {
                System.out.print('+');
                n = 0;
            }
            if (show.Factor[i] != 1)
            {
                System.out.print(show.Factor[i]);
                n = 1;
            }

            for (int j = 0; j < show.VariableNumber; j++)
            {
                for (int k = 0; k < show.VariableCount[j][i]; k++)
                {
                    if (m != 0 || n == 1)
                        System.out.print('*');
                    System.out.print(show.Variable.get(j));
                    m++;
                }
            }
        }
        System.out.println();
    }
=======
	public static Expression deal(final String str,final Expression ori)
	{
		final long aaaa=System.currentTimeMillis();
		System.out.println("始于："+aaaa);
		int state = 0;//自动机状态
			//System.out.println(str);
			for(int i = 0 ; i < str.length(); i ++)//对输入的表达式进行合法性判断
			{
				switch(state)
				{
				case 0://初始状态下的转移函数
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
				case 1://尾数为数字时的转移函数
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
				case 2://尾数为变量时的转移函数
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
				case 3://非法状态
				{
					break;	
				}
				case 4://尾数为符号时的转移函数
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
					System.out.println("终于："+System.currentTimeMillis());
					System.out.println("执行耗时 : "+(System.currentTimeMillis()-aaaa)+" 毫秒 ");
					return ori;
				}			
			}
			if(state == 1 || state == 2)
			{
				//System.out.println(str);
			}else if(state == 4)
			{
				System.out.println("Error!");
				System.out.println("终于："+System.currentTimeMillis());
				System.out.println("执行耗时 : "+(System.currentTimeMillis()-aaaa)+" 毫秒 ");
				return ori;
			}
			//形成数据结构
			Expression expr = new Expression();
			for(int i = 0; i < 10; i ++)
			{
				expr.factor[i] = 1;
			}
			//System.out.println(Expr.termnumber);
			//System.out.println(Expr.variablenumber);
			state = 0;//清空状态位
			for(int i = 0; i < str.length(); i ++)//形成数据结构的自动机
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
			System.out.println("终于："+System.currentTimeMillis());
			System.out.println("执行耗时 : "+(System.currentTimeMillis()-aaaa)+" 毫秒 ");	
		return expr;	
	}

	
	static Expression simplify(final String str,final Expression smpl)
	{
		final long tttt=System.currentTimeMillis();
		System.out.println("始于："+tttt);
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
			System.out.println("终于："+System.currentTimeMillis());
			System.out.println("执行耗时 : "+(System.currentTimeMillis()-tttt)+" 毫秒 ");
			//showexpr(rtn);
			return rtn;
		}
		//showexpr(smpl);
		System.out.println("终于："+System.currentTimeMillis());
		System.out.println("执行耗时 : "+(System.currentTimeMillis()-tttt)+" 毫秒 ");	
		return smpl; 
	}  
	
	static Expression derivative(final String str,final Expression expr)
	{

		final long tttt=System.currentTimeMillis();
		System.out.println("始于："+tttt);
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
			System.out.println("终于："+System.currentTimeMillis());
			System.out.println("执行耗时 : "+(System.currentTimeMillis()-tttt)+" 毫秒 ");
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
>>>>>>> refs/remotes/origin/master

}