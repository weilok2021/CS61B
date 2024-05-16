public class IntList {
	public int first;
	public IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	/** Return the size of the list using... recursion! */
	public int size() {
		if (this.rest == null) {
			return 1;
		}
		return 1 + this.rest.size();
	}

	/** Return the size of the list using no recursion! */
	public int iterativeSize() {
		IntList p = this; // pointer to iterate over the list
		int size = 0;
		while (p != null) {
			size ++;
			p = p.rest;
		}
		return size;
	}

	/** Returns the ith value in this list.*/
	public int get(int i) {
		if (i == 0) {
			return this.first;
		}
		return this.rest.get(i - 1);
	}

	public int iterativeGet(int i) {
		IntList p = this; // need a pointer to the list, because we don't want to lost track of the reference to list
		while (i != 0) {
			p = p.rest;
			i--;
		}
		return p.first;
	}

	public static IntList incrList(IntList L, int x) {
		/* Your code here. */
		if (L.rest == null) {
			return new IntList(L.first + x, null);
		}
		else {
			return new IntList(L.first + x, incrList(L.rest, x));
		}
	}

	/** Returns an IntList identical to L, but with
	 * each element incremented by x. Not allowed to use
	 * the 'new' keyword. */
	public static IntList dincrList(IntList L, int x) {
		/* Your code here. */
		IntList p = L;
		while (p != null) {
			p.first = p.first + x;
			p = p.rest;
		}
		return L;
	}

	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10, L);
		L = new IntList(5, L);

		System.out.println(L.get(0));
		System.out.println(L.get(1));
		System.out.println(L.get(2));

		System.out.println(L.size());
	}
} 