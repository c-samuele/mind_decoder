package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CpuImpl implements Cpu {

	
	private Map<Color,Integer[]> matrix;
	private int lengthCode;
	
	public CpuImpl(int length,List<Color> availableColors){
		this.lengthCode = length;
		matrix = new LinkedHashMap<>();
	
		// Initialization matrix
		initMatrix(availableColors);
	}
	
	@Override
	public void initMatrix(List<Color> availableColors) {
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
	
	@Override
	public void addAttempt(Code attemptCode,Hint hint) {

		List<Color> colors = attemptCode.getColor();		// Current colors of attempt
		int indexCorrect = hint.getIndexCorrect();			// Current number of index correct
		
		System.out.println(colors);
		System.out.println(indexCorrect);
		
		// Case index = 0
		if(indexCorrect == 0) {
			for(int i = 0; i < lengthCode;i++) {
				Color c = colors.get(i);
				
				if(matrix.containsKey(c)) {
					Integer[] arrValue = matrix.get(c); 
					arrValue[i] = null;
				}
					
			}
		}
		// Case index > 0
		if(indexCorrect > 0) {
			for(int j = 0; j < lengthCode;j++) {
				Color c = colors.get(j);
				
				if(matrix.containsKey(c)) {
					Integer[] arrValue = matrix.get(c);
					
					if(arrValue[j] != null)
						arrValue[j] = arrValue[j] + indexCorrect; // oppure + 1
				}
			}
				
		}
	
		
	}
	
	
}