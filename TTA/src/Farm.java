import java.util.ArrayList;



public class Farm extends ResourceCard {

	private int resourcePerWorker = 0;

	public Farm(int size, int cost, String name, int scienceCost) {
		super(cost, name, scienceCost);
		resourcePerWorker = size;
	}

	public void produce(Item blueChips, Item population) {
		resources = resources + Math.min(workers, blueChips.count) * resourcePerWorker;
		blueChips.count -= Math.min(workers, blueChips.count);
	}

	private int consume(Item blueChips, Item population, int resourceCount) {
		while (resourceCount > 0 && resources > 0){
			resourceCount -= resourcePerWorker;
			blueChips.count++;
			resources--;
		}
		return resourceCount;
	}

	public static void consume(ArrayList<Farm> farms, Item blueChips, Item population) {
		int resourceCount = 0;
		if (population.count <= 0){
			System.out.println("pop -ve breakpoint");
			resourceCount = 6;
		} else if (population.count == 0){
			resourceCount = 6;
		} else if (population.count <= 4){
			resourceCount = 4;
		} else if (population.count <= 8){
			resourceCount = 3;
		} else if (population.count <= 12){
			resourceCount = 2;
		} else if (population.count <= 16){
			resourceCount = 1;
		}

		Farm a = null;
		Farm i = null;
		Farm s = null;
		Farm m = null;
		for (Farm f:farms){
			if (f.name.equals("Agriculture")) a = f;
			else if (f.name.equals("Irrigation")) i = f;
			else if (f.name.equals("Selective Breeding")) s = f;
			else if (f.name.equals("Mech Agriculture")) m = f;
		}

		if (a != null && resourceCount > 0) {
			resourceCount = a.consume(blueChips, population, resourceCount);
		}
		if (i != null && resourceCount > 0) {
			resourceCount = i.consume(blueChips, population, resourceCount);
		}
		if (s != null && resourceCount > 0) {
			resourceCount = s.consume(blueChips, population, resourceCount);
		}
		if (m != null && resourceCount > 0) {
			resourceCount = m.consume(blueChips, population, resourceCount);
		}
		if (resourceCount > 0) System.out.println("insufficient food breakpoint");
		if (resourceCount < 0) {
			// return change TODO : extend to spread out to higher levels
			a.resources -= resourceCount;
			blueChips.count += resourceCount;
		}

	}

}
