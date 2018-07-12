package project3;

public class client {

	public static void main(String[] args) {
		SortedIntList list = new SortedIntList();
		list.setUnique(false);
		for (int i = -1; i < 4; i++) {
			list.add(i);
		}
		list.add(9);
		for (int i = 0; i < 3; i++) {
			list.add(2);
		}
		System.out.println("List: " + list);
		System.out.println("Count 2: " + list.count(2));
		list.setUnique(true);
		System.out.println("List: " + list);
		System.out.println("Empty? " + list.isEmpty());
		System.out.println("Min value: " + list.min());
		System.out.println("Unique: " + list.getUnique());

		System.out.println();

		System.out.println("List 2 start from here.");

		// set capacity.
		SortedIntList list2 = new SortedIntList(30);
		list2.setUnique(false);
		for (int i = -4; i < 7; i = i + 2) {
			list2.add(i);
		}
		list2.add(0);
		list2.add(0);
		list2.add(1);
		System.out.println("List: " + list2);
		System.out.println("Max value: " + list2.max());
		System.out.println("size: " + list2.size());
		list2.setUnique(true);
		System.out.println("List: " + list2);
		System.out.println("Index of 9: " + list2.indexOf(9));
		
		System.out.println();

		System.out.println("List 3 start from here.");
		
		// set capacity & set unique.
		SortedIntList list3 = new SortedIntList(true, 6);
		for (int i =0; i< 6; i++) {
			list3.add(1);
		}
		System.out.println("List: "+list3);
		System.out.println("Index of 3: " + list3.indexOf(3));
		for (int i =0; i< 6; i++) {
			list3.add(i);
		}
		System.out.println("List: "+list3);
		System.out.println("Count -3: " + list3.count(-3));
		
		
	}

}
