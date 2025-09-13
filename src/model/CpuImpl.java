package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CpuImpl implements Cpu {

	
	private Map<Color,Integer[]> matrix;
	private final int lengthCode;
	private final List<Color> availableColors;
	private List<Code> attemptsStory; 
	
	private List<Color> newAttempt;
	
	public CpuImpl(int length,List<Color> availableColors){
		this.lengthCode = length;
		this.availableColors = availableColors;
		matrix = new LinkedHashMap<>();
		attemptsStory = new ArrayList<>();
	
		newAttempt = new ArrayList<>(Collections.nCopies(lengthCode, null));
		
		
		
		// Initialization matrix
		initMatrix();
	}
	
	@Override
	public void initMatrix() {
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
		attemptsStory.add(attemptCode);
		
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
						arrValue[j] = arrValue[j] + indexCorrect; 
				}
			}
				
		}
	}
	
	@Override
	public Code chooseAttempt() {
	    List<Color> attempt = new ArrayList<>();
	    List<Color> available = new ArrayList<>(matrix.keySet());
	    Random rand = new Random();
	    
	    System.out.println("\n\nAVAILABLE COLORS: " + available);
	    System.out.println("ATTEMPT: " + attempt);
	    

	    // Calculate the possible indices for each color
	    Map<Color, List<Integer>> indexsAvailable = new HashMap<>();

	    for (Map.Entry<Color, Integer[]> entry : matrix.entrySet()) {
	        Color c = entry.getKey();
	        Integer[] values = entry.getValue();

	        List<Integer> possibleIndexes = new ArrayList<>();
	        for (int i = 0; i < values.length; i++) {
	            if (values[i] != null) {
	                possibleIndexes.add(i);
	            }
	        }

	        if (!possibleIndexes.isEmpty()) {
	            indexsAvailable.put(c, possibleIndexes);
	        }
	    }
	    
	    for(Map.Entry<Color, Integer[]> entry : matrix.entrySet()) { 
	    	System.out.println("Color:["+entry.getKey()+"]PossibleIndex:"+Arrays.toString(entry.getValue()));
	    
	    	// Check if there are unique colors for the index
	    	if (hasSingleValue(entry.getValue())) {
	    	    // save the index 
	    	    int index = IntStream.range(0, lengthCode)
	    	                         .filter(i -> entry.getValue()[i] != null)
	    	                         .findFirst()
	    	                         .orElse(-1);
	    	    
	    	    // Insert the certain color into the sequence
	    	    if (index != -1) {
	    	        newAttempt.set(index, entry.getKey());
	    	        
	    	        // Remove the certain color from the available colors
	    	        available.remove(entry.getKey());
	    	    }
	    	}

	    }
	
	    System.out.println("BEFORE SELECT REMAINING COLORS"+newAttempt);
	    System.out.println("\n\nAVAILABLE COLORS: " + available+"\n\n");
	    
	    selectRamainingColors(newAttempt,available);
	    
	    System.out.println("AFTER SELECT REMAINING COLORS"+newAttempt);
	    System.out.println("\n\nAVAILABLE COLORS: " + available+"\n\n");
	    return new CodeImpl(attempt);
	}
	
	public void selectRamainingColors(List<Color> partialAttempt, List<Color> available) {
	    for (int i = 0; i < lengthCode; i++) {
	        if (partialAttempt.get(i) == null) {
	            Color bestColor = null;
	            int maxValue = Integer.MIN_VALUE;

	            for (Map.Entry<Color, Integer[]> entry : matrix.entrySet()) {
	                Integer[] values = entry.getValue();
	                if (values[i] != null && values[i] > maxValue && available.contains(entry.getKey())) {
	                    maxValue = values[i];
	                    bestColor = entry.getKey();
	                }
	            }

	            if (bestColor != null) {
	                partialAttempt.set(i, bestColor);
	                available.remove(bestColor); 
	            }
	        }
	    }
	}

	
	
	public Code makeUniqueRandomAttempt() {
		List<Color> attempt; 
		
		    do {
		        List<Color> shuffled = new ArrayList<>(availableColors);
		        Collections.shuffle(shuffled);
		        attempt = shuffled.subList(0, availableColors.size()); 
	       
		    // DEBUG
	        if(ifContain(attempt))
	        	System.out.println("SEQUENZA DUPLICATA  ####################### ####################### ####################### #######################");
	        
	    } while (ifContain(attempt));

		return new CodeImpl(attempt);
	}
	
	
	
	// Check if the attempt has already been made
	public boolean ifContain(List<Color> att){
		  return attemptsStory.stream()
		            .anyMatch(c -> c.getColor().equals(att));
	}
	

	// Checks if there is only one color left in an index
	public static boolean hasSingleValue(Integer[] values) {
	    int count = 0;
	    
	    for (Integer v : values) {
	        if (v != null) 
	            count++;
	        
	            if (count > 1) 
	            	return false;
	        }
	    return count == 1;
	}

	
	
	
	
	
	
}