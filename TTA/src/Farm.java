

public class Farm extends ResourceCard {

	private int resourcePerWorker = 0;

	public Farm(int size, int cost) {
		super(cost);
		resourcePerWorker = size;
	}

	public void produce(Item blueChips, Item population) {
		
		resources = resources + Math.min(workers, blueChips.count) * resourcePerWorker;
		
		blueChips.count -= Math.min(workers, blueChips.count);
		
		// Can do here if only one farm, need to change when extra farm types defined
		if (population.count <= 0){
			blueChips.count += 6;
			resources -= 6;
		} else if (population.count <= 4){
			blueChips.count += 4;
			resources -= 4;
		} else if (population.count <= 8){
			blueChips.count += 3;
			resources -= 3;
		} else if (population.count <= 12){
			blueChips.count += 2;
			resources -= 2;
		} else if (population.count <= 16){
			blueChips.count += 1;
			resources -= 1;
		}
		System.out.println("farm resource total = " + resources);
		if (resources < 0){
			System.out.println("food -ve resource breakpoint");
		}
	}

}
