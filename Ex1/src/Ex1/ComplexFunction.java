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
	
//	public boolean isZero(function fRight)//Useful function for checking if right side of function equals to the ZERO function
//	{
//		if(fRight==null)
//			return false;
//		if(fRight instanceof Polynom)
//		{
//			Polynom p=new Polynom();
//			if(p.equals((Polynom)fRight))
//					return true;
//		}
//		else if(fRight instanceof Monom)
//		{
//			Monom m=new Monom();
//			if(m.equals((Monom)fRight))
//					return true;
//		}
//		return false;
//	}
//	
	/****************Using different operators for determine the situation in the tree*****************************/
	// need to fix plus to himself (cf.plus(cf)) do to all operations
	public void plus(function f1)
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
		o=Operation.Plus;
	}
	
	public void mul(function f1)
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
		o=Operation.Times;
	}
	
	public void div(function f1) 
	{
		//exception when f1 represents the ZERO function f(x)=0 (need to be checked by JUNIT tests)
		if(f1 instanceof Polynom)
		{
			Polynom p=new Polynom();
			if(f1.equals(p))
			{
				throw new RuntimeException("Cannot divide by ZERO");
			}
		}
		if(f1 instanceof Monom)
		{
			Monom m = new Monom();
			if(f1.equals(m))
			{
				throw new RuntimeException("Cannot divide by ZERO");
			}
		}
		//Assuming there is a function left, we need to mul (times) function right
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
		o=Operation.Divid;
	}
	
	public void max(function f1) 
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
	
	public void min(function f1)
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
		o=Operation.Min;
	}
	
	public void comp(function f1) 
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
		o=Operation.Comp;
	}
	//*****************Matan's part*********************************
	/*--------------------------------------------------------------*/
	// if the left/right is Monom/Polynom/ComplexFunction we use f(Monom/Polynom/ComplexFunction)
	public double f(double x) 
	{
		switch(o)
		{
		case Plus  :return left.f(x) + right.f(x);
		case Times : return left.f(x) * right.f(x);
		case Divid :
			if(right.f(x) != 0)
				return left.f(x) / right.f(x);
			System.err.println("cannot divide by zero");
		case Max   :
			double res = left.f(x) - right.f(x);
			if(res<0)
				return right.f(x);
			return left.f(x);
		case Min   :
			double Res = left.f(x) - right.f(x);
			if(Res<0)
				return left.f(x);
			return right.f(x);
		case Comp  :return left.f(right.f(x));
		case None  :return left.f(x);
		default      :throw new RuntimeException("not a good operation");
		}
	}
	
	
	
	
	// String of form: "Operation"("Polynom","Polynom") , (recursive)
	// fix!!
	public function initFromString(String s) {
		if (!s.contains("(") && !s.contains(")")) {
			this.left= new Polynom(s);
			this.right=null;
			this.o= Operation.None;
			return this.left.initFromString(s);
		}
		s = s.toUpperCase();
		s = s.replaceAll("X", "x");
		String op = "";
		if (s.charAt(0) == 'P' || s.charAt(0) == 'T' || s.charAt(0) == 'M' || s.charAt(0) == 'D' || s.charAt(0) == 'C'
				|| s.charAt(0) == 'N')
			op = new String(s.substring(0, s.indexOf('(')));
		s = s.substring(s.indexOf('(') + 1);
		s = s.substring(0, s.length()-1);
		Operation o = Operation.None;
		switch (op) 
		{
		case "PLUS":
			o = Operation.Plus;
			break;
		case "TIMES":
		case "MUL":
		case "MULT":
			this.o = Operation.Times;
			break;
		case "DIVID":
			o = Operation.Divid;
			break;
		case "MAX":
			o = Operation.Max;
			break;
		case "MIN":
			o = Operation.Min;
			break;
		case "COMP":
			o = Operation.Comp;
			break;
		case "NONE":
			o = Operation.None;
			break;
		default:
			o = Operation.Error;
			break;
		}

//		if (o == Operation.Error) {
//			if (s.contains(",")) {
//				s = s.replaceAll(",", "");
//				Polynom polL = new Polynom(s);
//				this.left = polL;
//				return this.left.initFromString(s);
//			} else {
//				Polynom polR = new Polynom(s);
//				this.right = polR;
//				return this.right.initFromString(s);
//			}
//		}

		function nleft = initFromString(s.substring(0, s.indexOf(',') + 1));
		function nright = initFromString(s.substring(s.indexOf(',') + 1));
		function f = new ComplexFunction(nleft, o, nright);

		return f;

	}
	public function copy() 
	{
	ComplexFunction  cf = new ComplexFunction(left,o, right);
	return cf;
	}
	
	//  to do , this function will work only in specific range
	public boolean equals(Object obj) 
	{
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
		cf.plus(n);
		cf.div(s);
		cf.div(new Monom(0,8));
		//cf = cf.initFromString("x^3");
		System.out.println(cf.toString());
	}



}