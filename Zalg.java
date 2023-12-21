package HW1;

public class Zalg{

	public static void main(String[] args) {
		String P = "LIGHT";
		int lenP = P.length();
		String T = "darknessdarknessLIGHTNESSdarknessdarkness";
		String S = P+"$"+T;
		
		System.out.println("Pattern: " +P);
		System.out.println("Text: " + T);
		int[] zVals = new int[S.length()];
		zVals = calculateZVals(S);
		System.out.print("zVals = ");
		for(int i=0; i<S.length(); i++) {
			System.out.print(zVals[i]);
		}
		exactMatches(zVals, lenP);
	}
	
	public static int[] calculateZVals(String S) {
		//INITIALIZING
		//I know I can do this with fewer variables, but this is more readable to me.
		int len = S.length();
		int[] zVals = new int[len];
		int r=0, l=0, j=0, Zi=0, kPrime=0, beta = 0;
		int k = 1;
		
		//CALCULATE Z VALUE FOR EACH INDEX, except first char.
		for(int i=1; i<len; i++) {
			k = i;
			j = 0;
			Zi = 0;
			
			//IF NOT IN A ZBOX, EXPLICIT COMPARISON
			if(k>r) {
				while((k<len)&&(S.charAt(k)==S.charAt(j))){
					Zi++;//count the match
					j++;//move left finger forward
					k++;//move right finger forward
				}
				//If we found a new rightmost zBox(even if it ends in the same place), reset l & r
				if (k-1>=r) {
					r = k-1;
					l = i;
				}
			}
			else if (k<=r){// else WE ARE IN A ZBOX. FIND K'
				kPrime = k -l;
				beta = r-k+1;
				if(zVals[kPrime]<beta) {
					Zi = zVals[kPrime];
					//hmmm, I need to do some explicit comparison beyond beta (case 2b)
				}
				else {Zi = beta;}
				//This takes care of the tail of the string
				if(len-k < Zi) {
					Zi = len - k;
				}
			}
			zVals[i]=Zi;
		}	
		return zVals;
	}

	public static void exactMatches(int[] zVals, int lenP) {
		int lenS = zVals.length;
		int lenT = zVals.length - lenP -1; //-1 for the "$"
		System.out.println("\nexact match at indexes: (of T)(one-indexed)");
		for(int i = lenP+1; i < lenS; i++) {
			if(zVals[i]>= lenP) {System.out.print(i-lenP + ", ");}	
		}
	}
}
