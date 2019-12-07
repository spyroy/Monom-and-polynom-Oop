package Ex1;


public class ComplexFunction implements complex_function  
{
	
	private function left,right;
	private Operation o;

	/***********************************Getters*******************************/
	public function getLeft() 
	{
		return left;
	}
	
	public function getRight() 
	{
		return right;
	}
	public Operation getOp() 
	{
		return o;
	}
	/***********************************Setters*******************************/
	public void setLeft(function left) 
	{
		this.left=left;
	}
	
	public void setRight(function right) 
	{
		this.right=right;
	}
	
	public void setOP(Operation o) 
	{
		this.o=o;
	}
	/*--------------------------------------------------------------*/
	
	/*****************************Constructors*******************************/
	
	// default constructor
	public ComplexFunction () 
	{
		left = new Polynom();
		o = Operation.None;
		right = null;
	}
	
	public ComplexFunction (function f) 
	{
		//In this case the program gets only one function with no operator, so we need to initialize it to be left.
		left=f;
		right=null;
		o = Operation.None;
	}
	
	public ComplexFunction(function left, Operation o, function right) 
	{
		if(o == Operation.None)
			throw new RuntimeException("Operation cannot be None between two functions");
		this.left=left;
		this.o=o;
		if(right!=null)
			this.right=right;
		
	}
	
	public ComplexFunction(function left, String o, function right) 
	{
		o = o.toUpperCase();
		if(o.equals("NONE"))
			throw new RuntimeException("Operation cannot be None between two functions");
		this.left=left;
		if(right!=null)
			this.right=right;
		switch(o)
		{
		case "PLUS"  :this.o = Operation.Plus; 	break;
		case "TIMES" :
		case "MUL":
		case "MULT":
			this.o = Operation.Times; break;
		case "DIVID" :this.o = Operation.Divid; break;
		case "MAX"   :this.o = Operation.Max; 	break;
		case "MIN"   :this.o = Operation.Min; 	break;
		case "COMP"  :this.o = Operation.Comp; 	break;
		case "NONE"  :this.o = Operation.None; 	break;
		default      :this.o = Operation.Error;	break;
		}
	}

	public ComplexFunction(String s)
	{	//Assuming the given string is valid
		
		ComplexFunction cf=new ComplexFunction();
		cf=(ComplexFunction) initFromString(s);
		left=cf.left;
		o=cf.o;
		right=cf.right;
	}
	
	/*******************************************************************************/
	/*****************************END OF CONSTRUCTORS*******************************/
	/*******************************************************************************/
	
	/****************Using different operators for determine the situation in the "tree"*****************************/
	public void plus(function f1)
	{
		if (f1 == this) 
		{
			ComplexFunction cf = (ComplexFunction) f1.copy();
			this.plus(cf);
		}
		else 
		{
			Operation op = o;
			if (right != null) {
				function fLeft = new ComplexFunction(left, o, right);
				left = fLeft;
				function fRight = new ComplexFunction(f1, op, null);
				right = fRight;
			} else
				right = f1;
			o = Operation.Plus;
		}
	}
	
	public void mul(function f1)
	{
		if (f1 == this) 
		{
			ComplexFunction cf = (ComplexFunction) f1.copy();
			this.mul(cf);
		} else {
			Operation op = o;
			if (right != null) 
			{
				function fLeft = new ComplexFunction(left, o, right);
				left = fLeft;
				function fRight = new ComplexFunction(f1, op, null);
				right = fRight;
			} else
				right = f1;
			o = Operation.Times;
		}
	}
	
	public void div(function f1) 
	{
		if (f1 == this) 
		{
			ComplexFunction cf = (ComplexFunction) f1.copy();
			this.div(cf);
		} 
		else 
		{
			// exception when f1 represents the ZERO function f(x)=0 (need to be checked by
			// JUNIT tests)
			if (f1 instanceof Polynom) 
			{
				Polynom p = new Polynom();
				if (f1.equals(p)) 
				{
					throw new RuntimeException("Cannot divide by ZERO");
				}
			}
			if (f1 instanceof Monom) 
			{
				Monom m = new Monom();
				if (f1.equals(m)) 
				{
					throw new RuntimeException("Cannot divide by ZERO");
				}
			}
			// Assuming there is a function left, we need to mul (times) function right
			Operation op = o;
			if (right != null) 
			{
				function fLeft = new ComplexFunction(left, o, right);
				left = fLeft;
				function fRight = new ComplexFunction(f1, op, null);
				right = fRight;
			} else
				right = f1;
			o = Operation.Divid;
		}
	}
	
	public void max(function f1) 
	{
		if (f1 == this) 
		{
			ComplexFunction cf = (ComplexFunction) f1.copy();
			this.max(cf);
		}
		else 
		{
		Operation op = o;
		if ( right != null )
		{
			function fLeft =new ComplexFunction(left, o,right);
			left = fLeft;
			function fRight =new ComplexFunction(f1, op,null);
			right=fRight;
		}
		else
			right=f1;
		o=Operation.Max;
		}
	}
	
	public void min(function f1)
	{
		if (f1 == this) 
		{
			ComplexFunction cf = (ComplexFunction) f1.copy();
			this.min(cf);
		} 
		else 
		{
			Operation op = o;
			if (right != null) 
			{
				function fLeft = new ComplexFunction(left, o, right);
				left = fLeft;
				function fRight = new ComplexFunction(f1, op, null);
				right = fRight;
			}
			else
				right = f1;
			o = Operation.Min;
		}
	}
	
	public void comp(function f1) 
	{
		if (f1 == this) 
		{
			ComplexFunction cf = (ComplexFunction) f1.copy();
			this.comp(cf);
		} else 
		{
			Operation op = o;
			if (right != null) 
			{
				function fLeft = new ComplexFunction(left, o, right);
				left = fLeft;
				function fRight = new ComplexFunction(f1, op, null);
				right = fRight;
			} else
				right = f1;
			o = Operation.Comp;
		}
	}
	//*****************Matan's part*********************************
	/*--------------------------------------------------------------*/
	// if the left/right is Monom/Polynom/ComplexFunction we use f(Monom/Polynom/ComplexFunction)
	public double f(double x) 
	{
		while (right != null) 
		{
			switch (o) 
			{
			case Plus:
				return left.f(x) + right.f(x);
			case Times:
				return left.f(x) * right.f(x);
			case Divid:
				if (right.f(x) != 0)
					return left.f(x) / right.f(x);
				System.err.println("cannot divide by zero");
			case Max:
				double res = left.f(x) - right.f(x);
				if (res < 0)
					return right.f(x);
				return left.f(x);
			case Min:
				double Res = left.f(x) - right.f(x);
				if (Res < 0)
					return left.f(x);
				return right.f(x);
			case Comp:
				return left.f(right.f(x));
			case None:
				return left.f(x);
			default:
				throw new RuntimeException("not a good operation");
			}
		}
		return left.f(x);
	}
	
	
	
	// String of form: "Operation"("Polynom","Polynom") , (recursive)
	public function initFromString(String s) {

		try 
		{
			if (!(s.contains("(") && s.contains(")"))) 
			{
				function f = new ComplexFunction(new Polynom(s));

				return f;
			}
			int bracket = s.indexOf('(');
			String o = s.substring(0, bracket);
			String cut = s.substring(bracket + 1, s.length() - 1);
			int open = 0;
			int close = 0;
			int c = 0;

			for (int i = 0; i < cut.length(); i++) {
				if (cut.charAt(i) == '(') {
					open++;
				} else if (cut.charAt(i) == ')') {
					close++;
				}
				if (open == close && cut.charAt(i) == ',') { 
					c = i;
					i = cut.length();
				}
			}

			function left = initFromString(cut.substring(0, c));
			function right = initFromString(cut.substring(c + 1, cut.length()));

			ComplexFunction cf = new ComplexFunction(left, o, right);

			return cf;

		} catch (Exception e) {
			throw new RuntimeException("not a good string");
		}
	}


	
	public function copy() 
	{
	ComplexFunction  cf = new ComplexFunction(left,o, right);
	return cf;
	}
	
	//  to do , this function will work only in specific range
	public boolean equals(Object obj) 
	{
		if(obj instanceof Monom) 
		{
			Monom tmp=new Monom(obj.toString());
			for (double i = -1000; i < 1000; i+=0.1) {
				if(this.f(i)!=tmp.f(i))
					return false;
			}
			return true;
		}
		if(obj instanceof Polynom) 
		{
			Polynom tmp=new Polynom(obj.toString());
			for (double i = -1000; i < 1000; i+=0.1) {
				if(this.f(i)!=tmp.f(i))
					return false;
			}
			return true;
		}
		if(obj instanceof ComplexFunction) 
		{
			ComplexFunction tmp=(ComplexFunction)new ComplexFunction().initFromString(obj.toString());
			for (double i = -1000; i < 1000; i+=0.1) {
				if(this.f(i)!=tmp.f(i))
					return false;
			}
			return true;
		}
		return false;
		
	}

	@Override
	public String toString() {
		while (right != null) {
			switch (o) {
			case Plus:
				return "Plus(" + this.left.toString() + "," + this.right.toString() + ")"; 
			case Times:
				return "Times(" + this.left.toString() + "," + this.right.toString()+ ")"; 
			case Divid:
				return "Divid(" + this.left.toString() + "," + this.right.toString()+ ")"; 
			case Max:
				return "Max(" + this.left.toString() + "," + this.right.toString()+ ")"; 
			case Min:
				return "Min(" + this.left.toString() + "," + this.right.toString()+ ")"; 
			case Comp:
				return"Comp(" + this.left.toString() + "," + this.right.toString()+ ")"; 
			case None:
				return "None(" + this.left.toString() + "," + this.right.toString()+ ")"; 
			default:
				throw new RuntimeException("not a good operation");
			
		}}
		return this.left.toString();
		
		}
	


	
	

	@Override
	public function left() 
	{
		return left;
	}

	@Override
	public function right()
	{
		return right;
	}
	
	public static void main(String[] args) {
		Polynom p = new Polynom ("x^5");
		Polynom m = new Polynom ("x^7");
		ComplexFunction n = new ComplexFunction(m);
		ComplexFunction cf = new ComplexFunction(p,"Plus",m);
		System.out.println(cf.f(1));
		Polynom s = new Polynom ("x^9");
		cf.plus(cf);
		cf.div(s);
		cf.div(new Monom(5,8));
		System.out.println(cf.initFromString("plus(mul(x^3,x^4),plus(x^2,x^3))"));
		System.out.println(cf.initFromString("min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)"
				+ ",plus(divid(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0))"
				+ ",divid(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1))"
				+ ",-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)"));
		ComplexFunction cf1 = new ComplexFunction(p,"Plus",m);
		ComplexFunction cf2 = new ComplexFunction(p,"Plus",m);
		boolean flag = cf1.equals(cf);
		System.out.println(flag);
		
	}



}