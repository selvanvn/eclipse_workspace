package day14;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Difference {
	private int[] elements;
	public int maximumDifference;

	Difference(int[] elements) {
		this.elements = elements;
	}

	public void computeDifference() {
		//maximumDifference = Integer.MIN_VALUE;
		Arrays.sort(elements);
		maximumDifference= elements[elements.length-1] - elements[0];
		/*for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements.length; j++) {
				int count = elements[i] - elements[j];
				if (count > maximumDifference) {
					maximumDifference = count;
				}
			}
		}*/
	}

}