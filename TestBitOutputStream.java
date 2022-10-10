/*
 * Mayolo Valencia
 * 6/24/2022
 * 
 * Writing in a string of 0's and 1's
 * the BitOutputStrean writes the bits into
 * a file. Then when closing makes sure the 
 * last bit is full. By shifting to the right
 * by however many spaces are left and filling
 * in with 0's.
 */

package AssignmentsDataStructures;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestBitOutputStream {
	public static void main(String[] args) throws Exception {
		BitOutputStream output = new BitOutputStream(new File("testOutput.dat"));
		output.writeBit("010000100100001001101");
		output.close();
	    System.out.println("Done");
}

public static class BitOutputStream {
    //  programs statements
	private FileOutputStream output;
	private byte currBit;
	private int bitAmount;
 
	// Constructor
	public BitOutputStream(File file) throws IOException {
		output = new FileOutputStream(file);
	}

	public void writeBit(String bitString) throws IOException {
		for (int i = 0; i < bitString.length(); ++i) {
			writeBit(bitString.charAt(i));
		}
	}

	public void writeBit(char bit) throws IOException {
		// left shift by 1
		currBit = (byte) (currBit << 1);

		 if(bit == '1') { 
			 /* yields a 1 if either bit is 1.
			  * copies bit if it exists*/
			 currBit = (byte)(currBit | 1);
			 
		 }bitAmount++;
		 
		 /* if bit amount == 8 writes into 
		  * file and resets
		  */
		 if(bitAmount == 8) {
			 output.write(currBit);
			 currBit = 0;
			 bitAmount = 0;
		 }
	}
	
	/** Writes the last byte and closes the stream.
	 *  If the last byte is not full, right-shift with zeros*/
	public void close() throws IOException {
		
		if(bitAmount > 0) {
			/* Shift by whatever amount left and
			 * and fills in with 0's  		*/
			currBit = (byte) (currBit << (8 - bitAmount));
			output.write(currBit);
		}
		
		//closes stream
		output.close();		
	}
  }
}