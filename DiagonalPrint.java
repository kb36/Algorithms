
public class DiagonalPrint {
	public static void main(String[] args) {
		DiagonalPrint d = new DiagonalPrint();
		int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
		d.printDiagonal(arr);
	}
	
	public void printDiagonal(int[] input) {
		if(input == null || input.length == 0)
			return;
		
		int hStep = 0;
		int begin = 0;
		int line = 0;
		while(begin < input.length) {
			int current = begin;
			hStep = line;
			while(current < input.length) {
				System.out.print(input[current]+" ");
				hStep = hStep+1;
				current = current + hStep;
			}
			line++;
			System.out.println();
			begin = begin+line+1;
		}
	}
}
