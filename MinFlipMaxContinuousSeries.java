/**
 * You are given with an array of 1s and 0s. And you are given with an integer m, which signifies number of flips allowed.
 * find the position of zeros which when flipped will produce maximum continuous series of 1s.
 * e.g.
 * input:
 * arr={1 1 0 1 1 0 0 1 1 1 } m=1
 * output={1 1 1 1 1 0 0 1 1 1} position=2
 * arr={1 1 0 1 1 0 0 1 1 1 } m=2
 * output={1 1 0 1 1 1 1 1 1 1} position=5,6
 *
 */
public class MinFlipMaxContinuousSeries {
	
	public static void main(String[] args) {
		int[] arr = {1, 1, 0, 1, 1, 0, 0, 1, 1, 1 };
		MinFlipMaxContinuousSeries m = new MinFlipMaxContinuousSeries();
		m.findMinFlipTwoZeroes(arr);
	}

	public void findMinFlipTwoZeroes(int[] arr) {
		if(arr == null || arr.length == 0)
			return;
		
		int cBegin = 0, cEnd = 0;
		int mBegin = 0, mEnd = 0;
		int wZeroCount = 0;

		while(cEnd < arr.length) {
			if(arr[cEnd] == 0)
				wZeroCount++;
			if(wZeroCount > 2) {
				while(arr[cBegin] != 0) {
					cBegin++;
				}
				cBegin++;
				wZeroCount--;
			}
			
			if(cEnd-cBegin > mEnd-mBegin) {
				mEnd = cEnd;
				mBegin = cBegin;
			}
			cEnd++;
		}
			
		
		for(int i = mBegin; i <= mEnd; i++) {
			if(arr[i] == 0)
				System.out.println("position: "+ i);
		}
	}
}
