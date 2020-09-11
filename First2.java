import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.lang.*;
public class First2 extends Frame {
	Label res=new Label("");
Button b1=new Button("1");
Button b2=new Button("2");
Button b3=new Button("3");
Button b4=new Button("4");
Button b5=new Button("5");
Button b6=new Button("6");
Button b7=new Button("7");
Button b8=new Button("8");
Button b9=new Button("9");
Button b0=new Button("0");
Button add=new Button("+");
Button neg=new Button("-");
Button mul=new Button("*");
Button div=new Button("/");
Button rem=new Button("%");
Button eq=new Button("=");
Label x=new Label("");
Button pow=new Button("a^b");
Button deg=new Button(")");
Button uroot=new Button("sqrt");
Button fact=new Button("x!");
Button obx=new Button("(");
Button pi=new Button("PI");
Button sini=new Button("sin-1");
Button tani=new Button("tan-1");
Button cosi=new Button("cos-1");
Button sin=new Button("sin");
Button cos=new Button("cos");
Button tan=new Button("tan");
Button log=new Button("lg");
Button c=new Button("C");
Vector <String> out=new Vector<String>(100);
int check=0;
Stack <Float> value=new Stack<Float>();
Stack <String>  ops=new Stack<String>();


public int presidency(String t)
{
	switch(t)
	{
		case "+":
		return 1;
		case "-":
		return 1;
		case "*":
		return 2;
		case "/":
		return 2;
		case "%":
		return 2;
		case "^":
		return 3;
		case "sin":
		return 4;
		case "cos":
		return 4;
		case "tan":
		return 4;
		case "sini":
		return 4;
		case "cosi":
		return 4;
		case "tani":
		return 4;
		case "lg":
		return 4;
		case "root":
		return 4;
		case "!":
		return 4;
	}
	return 0;

}
public float factnow(float n)
{
	if(n==0)
		return 1;
	return n*factnow(n-1);
}
public float doops(String c,float ...n)
{
	switch(c)
	{
		case "+":
		return n[0]+n[1];
		case "-":
		return n[0]-n[1];
		case "*":
		return n[0]*n[1];
		case "/":
		return n[0]/n[1];
		case "%":
		return n[0]%n[1];
		case "^":
		return (float)Math.pow(n[0],n[1]);
		case "sin":
		return (float)Math.sin(Math.toRadians(n[0]));
		case "cos":
		return (float)Math.cos(Math.toRadians(n[0]));
		case "tan":
		return (float)Math.tan(Math.toRadians(n[0]));
		case "sini":
		return (float)Math.asin(Math.toRadians(n[0]));
		case "cosi":
		return (float)Math.acos(Math.toRadians(n[0]));
		case "tani":
		return (float)Math.atan(Math.toRadians(n[0]));
		case "log":
		return (float)Math.log(n[0]);
		case "root":
		return (float)Math.sqrt(n[0]);
		case "!":
		return factnow(n[0]);
	}
	return 0;
}
public void calculate(Vector t)
{
	int i=0;
	for( i=0;i<t.size();i++)
	{
		System.out.println(t.get(i));
		if((t.get(i).toString().charAt(0)>='0' && t.get(i).toString().charAt(0)<='9') || t.get(i).toString().equals("pi"))
		{
			System.out.println("entered value section"+t.get(i));
			if(t.get(i).toString().equals("pi"))
				value.push((float)Math.PI);
			else
			{
			String tem=new String("");
			while(i<t.size() && (t.get(i).toString().charAt(0)>='0' && t.get(i).toString().charAt(0)<='9'))
			{
				tem+=t.get(i).toString();
				i++;
			}
			i--;
			System.out.println("value"+tem);
			value.push(Float.parseFloat(tem));
		}
		}
		else if(t.get(i).toString().charAt(0)=='(')
		{
			ops.push(t.get(i).toString());
		}
		else if(t.get(i).toString().charAt(0)==')')
		{
			while(!ops.peek().equals("("))
			{
				if(ops.peek().equals("+") || ops.peek().equals("-") || ops.peek().equals("*") || ops.peek().equals("/") || ops.peek().equals("%") || ops.peek().equals("^") )
				{
					float val1=value.pop();
					if(value.empty())
						break;
					value.push(doops(ops.pop(),value.pop(),val1));
				}
				else if(ops.peek().equals("sin") || ops.peek().equals("cos") || ops.peek().equals("tan") || ops.peek().equals("sini") || ops.peek().equals("cosi") ||  ops.peek().equals("tani")|| ops.peek().equals("log")|| ops.peek().equals("!") || ops.peek().equals("root"))
				{
					value.push(doops(ops.pop(),value.pop(),(float)0));
				}
			}
			ops.pop();
		}
		else
		{
			
			while(!ops.empty() && presidency(ops.peek())>presidency(t.get(i).toString()))
			{
				if(ops.peek().equals("+") || ops.peek().equals("-") || ops.peek().equals("*") || ops.peek().equals("/") || ops.peek().equals("%") || ops.peek().equals("^") )
				{
					float val1=value.pop();
					if(value.empty())
						break;
					float val2=value.pop();
					value.push(doops(ops.pop(),val2,val1));
				} 
				else if(ops.peek().equals("sin") || ops.peek().equals("cos") || ops.peek().equals("tan") || ops.peek().equals("sini") || ops.peek().equals("cosi") || ops.peek().equals("tani") || ops.peek().equals("tani")|| ops.peek().equals("log") || ops.peek().equals("root"))
				{
					value.push(doops(ops.pop(),value.pop(),(float)0));
				}
				System.out.println("peek"+value.peek());

			}
			System.out.println("sign"+t.get(i).toString());
			ops.push(t.get(i).toString());
		}
	}
	while(!ops.empty())
			{
				System.out.println("entered");
				if(ops.peek().equals("+") || ops.peek().equals("-") || ops.peek().equals("*") || ops.peek().equals("/") || ops.peek().equals("%") || ops.peek().equals("^") )
				{
					float val1=value.pop();
					if(value.empty())
						break;
					float val2=value.pop();
					value.push(doops(ops.pop(),val2,val1));
				} 
				else if(ops.peek().equals("sin") || ops.peek().equals("cos") || ops.peek().equals("tan") || ops.peek().equals("sini") || ops.peek().equals("cosi") || ops.peek().equals("tani") || ops.peek().equals("!") || ops.peek().equals("log")|| ops.peek().equals("root"))
				{
					value.push(doops(ops.pop(),value.pop(),(float)0));
				}
				System.out.println("res"+value.peek());

			}
			if(!ops.empty() || value.size()>1)
			{
				System.out.println("Syntax error");
				res.setText("Syntax error");
			}
			else
			{
				System.out.println(value.peek());
				res.setText(String.valueOf(value.peek()));
			}

			
}
public First2(){
	x.setBounds(200,100,500,100);
	res.setBounds(200,200,500,100);
	pow.setBounds(200,300,100,100);
	sini.setBounds(300,300,100,100);
	cosi.setBounds(400,300,100,100);
	tani.setBounds(500,300,100,100);
	c.setBounds(600,300,100,100);
	deg.setBounds(200,400,100,100);
	sin.setBounds(300,400,100,100);
	cos.setBounds(400,400,100,100);
	tan.setBounds(500,400,100,100);
	add.setBounds(600,400,100,100);
	uroot.setBounds(200,500,100,100);
	b1.setBounds(300,500,100,100);
	b2.setBounds(400,500,100,100);
	b3.setBounds(500,500,100,100);
	neg.setBounds(600,500,100,100);
	fact.setBounds(200,600,100,100);
	b4.setBounds(300,600,100,100);
	b5.setBounds(400,600,100,100);
	b6.setBounds(500,600,100,100);
	mul.setBounds(600,600,100,100);
	pi.setBounds(200,700,100,100);
	b7.setBounds(300,700,100,100);
	b8.setBounds(400,700,100,100);
	b9.setBounds(500,700,100,100);
	div.setBounds(600,700,100,100);
	log.setBounds(200,800,100,100);
	obx.setBounds(300,800,100,100);
	b0.setBounds(400,800,100,100);
	eq.setBounds(500,800,100,100);
	rem.setBounds(600,800,100,100);
	deg.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
	out.add(")");
		check=0;
	x.setText(x.getText()+")");
}
  });
	log.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
		out.add("log");
		check=0;
	x.setText(x.getText()+"lg");
}
  });
	c.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
	
	x.setText("");
	res.setText("");
	out.clear();
	value.clear();
	ops.clear();
}
  });
	tan.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
		out.add("tan");
		check=0;
	x.setText(x.getText()+"tan");
}
  });
	cos.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
		out.add("cos");
		check=0;
	x.setText(x.getText()+"cos");
}
  });
	sin.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
		out.add("sin");
		check=0;
	x.setText(x.getText()+"sin");
}
  });
	tani.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
		out.add("tani");
		check=0;
	x.setText(x.getText()+"tan-1");
}
  });
	cosi.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
		out.add("cosi");
		check=0;
	x.setText(x.getText()+"cos-1");
}
  });
	sini.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
		out.add("sini");
		check=0;
	x.setText(x.getText()+"sin-1");
}
  });
	pi.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
		out.add("pi");
		check=0;
	x.setText(x.getText()+"PI");
}
  });
	obx.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
		out.add("(");
		check=1;
	x.setText(x.getText()+"(");
}
  });
	fact.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
		out.add("!");
		check=0;
	x.setText(x.getText()+"!");
}
  });
	uroot.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
		out.add("root");
		check=0;
	x.setText(x.getText()+"root");
}
  });
	pow.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
	
		out.add("^");
		check=0;
	x.setText(x.getText()+"^");
}
  });
   add.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1)
{
	if(check==0)
	{
		out.add("+");
		check=1;
	}
	else if(check==1)
	{
		out.removeElementAt(out.size()-1);
		out.add("+");
	}
	x.setText(x.getText()+"+");
}
  });
  div.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	if(check==0)
	{
		out.add("/");
		check=1;
	}
	else if(check==1)
	{
		out.removeElementAt(out.size()-1);
		out.add("/");
	}
	x.setText(x.getText()+"/");
}
  });
   neg.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	if(check==0)
	{
		out.add("-");
		check=1;
	}
	else if(check==1)
	{
		out.removeElementAt(out.size()-1);
		out.add("-");
	}
	x.setText(x.getText()+"-");
}
  });
  rem.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	if(check==0)
	{
		out.add("%");
		check=1;
	}
	else if(check==1)
	{
		out.removeElementAt(out.size()-1);
		out.add("%");
	}
	x.setText(x.getText()+"%");
}
  });
  mul.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	if(check==0)
	{
		out.add("*");
		check=1;
	}
	else if(check==1)
	{
		out.removeElementAt(out.size()-1);
		out.add("*");
	}
	x.setText(x.getText()+"*");
}
  });
  eq.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	calculate(out);
	String t=new String("");
	for(int i=0;i<out.size();i++)
	{
		t+=out.get(i);
	}
	System.out.println(t+" "+out.size());
	
}
  });
  b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	check=0;
	out.add("1");
	x.setText(x.getText()+"1");
}
  });
  b2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	check=0;
	out.add("2");
	x.setText(x.getText()+"2");
}
  });
   b3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	check=0;
	out.add("3");
	x.setText(x.getText()+"3");
}
  });
  b4.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	check=0;
	out.add("4");
	x.setText(x.getText()+"4");
}
  });
  b5.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	check=0;	
	out.add("5");
	x.setText(x.getText()+"5");
}
  });
  b6.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	check=0;
	out.add("6");
	x.setText(x.getText()+"6");
}
  });
   b7.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	check=0;
	out.add("7");
	x.setText(x.getText()+"7");
}
  });
  b8.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	check=0;
	out.add("8");
	x.setText(x.getText()+"8");
}
  });
   b9.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	check=0;
	out.add("9");
	x.setText(x.getText()+"9");
}
  });
  b0.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e1){
	check=0;
	out.add("0");
	x.setText(x.getText()+"0");
}
  });
  add(c);add(eq);add(add);add(mul);add(neg);add(div);add(rem);add(b0);add(b9);add(b8);add(b7);add(b6);add(b5);add(b4);add(b3);add(b2);
add(b1);add(res); add(x);add(pow);add(deg);add(uroot);add(fact);add(obx);add(pi);add(sini);add(cosi);add(tani);add(sin);add(cos);add(tan);add(log);
  // public float equate(float n1,float n2,char s){}

setSize(1000,1000);
setLayout(null);
setVisible(true);
}
public static void main(String args[]){

First2 f=new First2();

}
}