package Ex1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

public class ComplexFunction implements complex_function  {
	
	class Node{
		Operation o;
		function f;
		
		public Node() 
	    { 
	        this.o = Operation.None;
	        this.f = null;
	    } 
		
		public Node(function f1 , Operation o) 
	    { 
	        this.o = o;
	        this.f = f1;
	    } 
	}
	
	
	
	private LinkedList<Node> cfl = new LinkedList<>();
	
	public ComplexFunction(function f1, Operation o, function f2) {
		Node cf = new Node(f1, o);
		cfl.add(cf);
		Node add = new Node(f2,Operation.None);
		cfl.add(add);
	}
	
	public ComplexFunction(function f1) {
		if(f1 instanceof ComplexFunction) {
			Node left = new Node(((ComplexFunction) f1).left(),((ComplexFunction) f1).getOp());
			Node right = new Node(((ComplexFunction) f1).right(),((ComplexFunction) f1).getOp());
			cfl.add(left);
			cfl.add(right);
		}
		else if(f1 instanceof Polynom) {
			Node n = new Node (f1,Operation.None);
			cfl.add(n);
		}
		else if(f1 instanceof Monom) {
			Node n = new Node (f1,Operation.None);
			cfl.add(n);
		}
			
	}
	

	private Iterator<Node> iteretor() {
		return this.cfl.iterator();
	}
	
	public void plus(function f1) {
		ComplexFunction cf= new ComplexFunction(f1);
		
		//Node cf = new Node(f1,Operation.Plus);
////		cf.left = this;
////		cf.o = Operation.Plus;
////		cf.right = f1;
////		cfl.clear();
		//cfl.add(cf);
////		o.o = Operation.Plus;
////		Node_f2 right = new Node_f2();
////		//Object iter = new Node_f2();
////		right.f2 = f1;
//////		Iterator<Object> listiter = iteretor();
//////		while(cfl.iterator().hasNext()) {
//////			iter = listiter.next();
//////		}
////		cfl.add(o);
////		cfl.add(right);
//		Node n = new Node();
//		Node Pointer = new Node();
//		n.f = f1;
//		Iterator<Node> functionIter = iteretor();
//		while (this.cfl.iterator().hasNext()){
//			Pointer=functionIter .next();
//		}
//		Pointer.o=Operation.Plus;
//		this.cfl.add(n);
			
	}
	public void mul(function f1) {;}
	public void div(function f1) {;}
	public void max(function f1) {;}
	public void min(function f1) {;}
	public void comp(function f1) {;}
	public function left() {return null;}
	public function right(){return cfl.getLast().f;}
	public Operation getOp(){
		Node iter = new Node();
		Iterator<Node> liter = iteretor();
		while (iteretor().next().o != Operation.None){
			iter = liter.next();
		}
		return iter.o;
	}
	public double f(double x) {return x;}
	public function initFromString(String s){return null;}
	public function copy() {return null;}
	
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
	

}
