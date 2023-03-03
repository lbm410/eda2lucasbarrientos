package divideyvenceras;

public class tuberias {
	public static void main(String[] args) {
		int [] a = {};
		buscarFallos(a);
	}
	public static void buscarFallos(int [] a) {
		if (a.length == 0) {
			throw new RuntimeException("Que dise su polla, si no hay sensores, que pollas vas a buscar vieo");
		}
		if (a[0] == a[a.length-1]) {
			System.out.println("No hay fugas.");
		} else {
			buscarFallos(a, 0, a.length - 1);
		}
	}
	public static void buscarFallos(int [] a, int i, int j) {
			if (a[i] != a[j]) {
				if (i+1 == j) {
					System.out.println("Fallo entre " + i + "-" + j);
				} else {
					int mitad = (i+j)/2;
					buscarFallos(a, i, mitad);
					buscarFallos(a, mitad, j);
				}
		}
	}
}
