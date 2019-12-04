package Ex1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

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
	public ComplexFunction (function f) 
	{
		//In this case the program gets only one function with no operator, so we need to initialize it to be left.
		left=f;
		o = Operation.None;
	}
	public ComplexFunction(function left, Operation o, function right) 
	{
		this.left=left;
		this.o=o;
		if(right!=null)
			this.right=right;
	}
	public ComplexFunction(function left, String o, function right) 
	{
		this.left=left;
		if(right!=null)
			this.right=right;
		switch(o.toUpperCase())
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
		
		//TODO 
		function check = initFromString(s);
		ComplexFunction cf = (ComplexFunction)check;
		this.left = cf.left();
		this.right = cf.right();
		this.o = cf.getOp();
		
	}
	/*******************************************************************************/
	/*****************************END OF CONSTRUCTORS*******************************/
	/*******************************************************************************/
	public boolean isZero(function fRight)//Useful function for checking if right side of function equals to the ZERO function
	{
		if(fRight==null)
			return false;
		if(fRight instanceof Polynom)
		{
			Polynom p=new Polynom();
			if(p.equals((Polynom)fRight))
					return true;
		}
		else if(fRight instanceof Monom)
		{
			Monom m=new Monom();
			if(m.equals((Monom)fRight))
					return true;
		}
		return false;
	}
	/****************Using different operators for determine the situation in the tree*****************************/
	public void plus(function f1)
	{
		o=Operation.Plus;
		//Assuming there is a function left, we need to add (plus) function right
		if ( right != null )
		{
			function fLeft =new ComplexFunction(left, o,right);
			left = fLeft;
		}
		right=f1;
	}
	public void mul(function f1)
	{
		o=Operation.Times;
		//Assuming there is a function left, we need to mul (times) function right
		if ( right != null )
		{
			function fLeft =new ComplexFunction(left, o,right);
			left = fLeft;
		}
		right=f1;
	}
	public void div(function f1) 
	{
		//TODO exception when f1 represents the ZERO function f(x)=0 (need to be checked by JUNIT tests)
		o=Operation.Divid;
		//Assuming there is a function left, we need to mul (times) function right
		if ( right != null )
		{
			function fLeft =new ComplexFunction(left, o,right);
			left = fLeft;
		}
		right=f1;
	}
	public void max(function f1) 
	{
		o=Operation.Max;
		//Assuming there is a function left, we need to mul (times) function right
		if ( right != null )
		{
			function fLeft =new ComplexFunction(left, o,right);
			left = fLeft;
		}
		right=f1;
	}
	public void min(function f1)
	{
		o=Operation.Min;
		//Assuming there is a function left, we need to mul (times) function right
		if ( right != null )
		{
			function fLeft =new ComplexFunction(left, o,right);
			left = fLeft;
		}
		right=f1;
	}
	public void comp(function f1) 
	{
		o=Operation.Comp;
		if ( right != null )
		{
			function fLeft =new ComplexFunction(left, o,right);
			left = fLeft;
		}
		right=f1;
	}
	
	/*--------------------------------------------------------------*/
	public double f(double x) {return x;}
	
	
	//*****************Matan's part*********************************
	public function initFromString(String s){return null;}
	public function copy() 
	{return null;}

	@Override
	public String toString() {
		Iterator<Node> listiter = iteretor();
		Node iter = new Node();
		String s = "";
		while(listiter.hasNext()) {
			iter = listiter.next();
			s+=iter.o;
			s+=iter.f;
			s+=iter.o;
			//cfl.remove(iter);
		}
		s = s.replaceAll("Plus","+");
		s = s.replaceAll("None","");
		s = s.replaceAll("Null", "");
		return s;
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



}
