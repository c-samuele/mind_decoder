package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CpuImpl implements Cpu {

	
	private Map<Color,Integer[]> matrix;
	private int lengthCode;
	
	public CpuImpl(int length,Color[] availableColors){
		this.lengthCode = length;
		matrix = new HashMap<>();
	
		// Initialization matrix
		initMatrix(availableColors);
	}
	
	@Override
	public void initMatrix(Color[] availableColors) {
		for(Color c : availableColors) {
			matrix.put(c,new Integer[lengthCode]);
			Arrays.fill(matrix.get(c), 0);
		}
	}

	@Override
	public void printMatrix() {
		// index
	    System.out.print("INDEX\t");
	    for (int i = 0; i < lengthCode; i++) {
	        System.out.print(i + "\t");
	    }
	    System.out.print("\n------");
	    System.out.print("--------".repeat(lengthCode));
	    System.out.print("\n");
		// color and value
	    for (Map.Entry<Color, Integer[]> entry : matrix.entrySet()) {
	        Color c = entry.getKey();
	        Integer[] row = entry.getValue();
	        System.out.print(c + "\t");
	        for (int i = 0; i < row.length; i++) {
	            System.out.print(row[i] + "\t");
	        }
	        System.out.print("\n");
	    }   
	}
	

	

	

}
