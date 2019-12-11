package Ex1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Functions_GUI implements functions{
	
	private ArrayList<function> functions;
	
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};
	
	
	public Functions_GUI() {
		functions = new ArrayList<function>();
	}
	
	@Override
	public boolean add(function arg0) {
		return functions.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return functions.addAll(arg0);
	}


	@Override
	public void clear() {
		functions.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return functions.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return functions.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return functions.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return functions.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return functions.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return functions.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return functions.retainAll(arg0);
	}

	@Override
	public int size() {
		return functions.size();
	}

	@Override
	public Object[] toArray() {
		return functions.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) { 
		return functions.toArray(arg0);
	}
	
	
	
	
	@Override
	public void initFromFile(String file) throws IOException {
		
		String s="";
		
		try {
			
			FileReader new_file = new FileReader(file);
			BufferedReader read = new BufferedReader(new_file);			
			while((s = read.readLine())!= null) {
				String f = s.substring(0, s.length());
				function tmp = new ComplexFunction(f);
				functions.add(tmp);
			}
			read.close();
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	
	
	@Override
	public void saveToFile(String file) throws IOException {
		
		Iterator<function> f_iter = iterator();
		StringBuilder s = new StringBuilder();
		while(f_iter.hasNext()) {
			function n = f_iter.next();
			s.append(n.toString());
			if(f_iter.hasNext()) {
				s.append("\n");
			}	
		}
		
		try{
			
			File new_file = new File(file);
			PrintWriter write = new PrintWriter(new_file);
			write.write(s.toString());
			write.close();
		} 
		
		catch (FileNotFoundException e){
			e.printStackTrace();
			return;
		}
	}
	
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		if (width<=0) 
			throw new IllegalArgumentException("width cannot be negative");
		
		if (height<=0)
			throw new IllegalArgumentException("hight cannot be negative");
		
		if(rx.get_min() >= rx.get_max())
			throw new IllegalArgumentException("min must be smaller then max");

		if(ry.get_min() >= ry.get_max())
			throw new IllegalArgumentException("min must be smaller then max");
		
		if(resolution<=0)
			throw new IllegalArgumentException("resolution cannot be negative");
		
		StdDraw.setCanvasSize(width, height);
		double[] x = new double[resolution+1];
		double[][] yy = new double[functions.size()][resolution+1];
		double x_step = (rx.get_max()-rx.get_min())/resolution;
		double x0 = rx.get_min();
		for (int i=0; i<=resolution; i++) {
			x[i] = x0;
			for(int a=0;a<functions.size();a++) {
				yy[a][i] = functions.get(a).f(x[i]);
			}
			x0+=x_step;
		}
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		
		double min=0, max=0;
		min = Math.min(rx.get_min(), ry.get_min());
		max = Math.max(rx.get_max(),ry.get_max() );
		
		// drawing background lines and numbers
		StdDraw.setPenRadius(0.001);
		StdDraw.setPenColor(Color.gray);
		for (double i = min;i < max; i++) {
			Integer y_axis = (int)i;
			StdDraw.line(min,y_axis , max, y_axis);
			StdDraw.text(-0.5, i, y_axis.toString());
		}
		
		for (double i = min; i < max; i++) {
			Integer x_axis = (int)i;
			StdDraw.line(x_axis, min , x_axis ,max);
			StdDraw.text(i, -0.5, x_axis.toString());
		}
		
		
		//drawing main axis
		StdDraw.setPenRadius(0.008);
		StdDraw.setPenColor(Color.black);
		StdDraw.line(rx.get_min(), 0.0, rx.get_max(), 0.0); 
		StdDraw.line(0.0, ry.get_min(), 0.0, ry.get_max()); 
		
		
		
		StdDraw.setPenRadius(0.005);
		
		for(int a=0;a<functions.size();a++) {
			int c = a%Colors.length;
			StdDraw.setPenColor(Colors[c]);
			
			
			System.out.println(a+") "+Colors[a%7]+"  f(x)= "+((ArrayList<function>) functions).get(a));
			for (int i = 0; i < resolution; i++) {
				StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
			}
		}	
	}
	
	@Override
	public void drawFunctions(String json_file) {
		JSONParser parser = new JSONParser();

		try {
			
			FileReader new_file = new FileReader(json_file);
			Object read = parser.parse(new_file);
			JSONObject j_obj = (JSONObject) read;
			long width = (long) j_obj.get("Width");
			long height = (long) j_obj.get("Height");
			long resolution = (long) j_obj.get("Resolution");
			JSONArray j_array_x = (JSONArray) j_obj.get("Range_X");
			JSONArray j_array_y = (JSONArray) j_obj.get("Range_Y");
			String m_x = j_array_x.get(0).toString();
			String m_y = j_array_y.get(0).toString();
			String M_x = j_array_x.get(1).toString();
			String M_y = j_array_y.get(1).toString();
			Range rx = new Range(Integer.parseInt(m_x), Integer.parseInt(M_x));
			Range ry = new Range(Integer.parseInt(m_y), Integer.parseInt(M_y));
			drawFunctions((int)width, (int)height, rx, ry, (int)resolution);

		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
