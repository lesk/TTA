

public class Mine extends ResourceCard {

	private int resourcePerWorker = 0;

	public Mine(int size, int cost) {
		super(cost);
		resourcePerWorker = size;
	}

	public void produce(Item blueChips) {
		
		resources = resources + Math.min(workers, blueChips.count) * resourcePerWorker;
		
		blueChips.count -= Math.min(workers, blueChips.count);
		
		// Can do here if only one mine, need to change when extra mine types defined
		if (blueChips.count <= 0){
			blueChips.count = 6;
			resources -= 6;
		} else if (blueChips.count <= 4){
			blueChips.count += 4;
			resources -= 4;
		} else if (blueChips.count <= 8){
			blueChips.count += 2;
			resources -= 2;
		}
		System.out.println("mine resource total = " + resources);
		if (resources < 0){
			System.out.println("mine -ve resource breakpoint");
		}
	}

}
