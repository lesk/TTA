import java.util.ArrayList;

public class Mine extends ResourceCard {
	
	public Mine(int size, int cost, String name, int scienceCost) {
		super(size, cost, name, scienceCost);
	}

	public static void produce(Mine[] mines, Item blueChips, Item population) {
		for (Mine m:mines){
			if (m != null) m.produce(blueChips, population);
		}
	}
	
	public void produce(Item blueChips, Item population) {
		resources += Math.min(workers, blueChips.count) * resourcePerWorker;
		blueChips.count -= Math.min(workers, blueChips.count);
	}

	private int consume(Item blueChips, Item population, int resourceCount) {
		while (resourceCount > 0 && resources > 0){
			resourceCount -= resourcePerWorker;
			blueChips.count++;
			resources -= resourcePerWorker;
		}
		return resourceCount;
	}

	public static void consume(Mine[] mines, Item blueChips, Item population) {
		int resourceCount = 0;
		if (blueChips.count < 0){
			System.out.println("bluechips -ve breakpoint");
			resourceCount = 6;
		} else if (blueChips.count == 0){
			resourceCount = 6;
		} else if (blueChips.count <= 4){
			resourceCount = 4;
		} else if (blueChips.count <= 8){
			resourceCount = 2;
		}

		Mine b = mines[0];
		Mine i = mines[1];
		Mine c = mines[2];
		Mine o = mines[3];
//		for (Mine m:mines){
//			if (m.name.equals("Bronze")) b = m;
//			else if (m.name.equals("Iron")) i = m;
//			else if (m.name.equals("Coal")) c = m;
//			else if (m.name.equals("Oil")) o = m;
//		}

		if (b != null && resourceCount > 0) {
			resourceCount = b.consume(blueChips, population, resourceCount);
		}
		if (i != null && resourceCount > 0) {
			resourceCount = i.consume(blueChips, population, resourceCount);
		}
		if (c != null && resourceCount > 0) {
			resourceCount = c.consume(blueChips, population, resourceCount);
		}
		if (o != null && resourceCount > 0) {
			resourceCount = o.consume(blueChips, population, resourceCount);
		}
		if (resourceCount > 0) {
			System.out.println("insufficient resources breakpoint");
		}
		if (resourceCount < 0) {
			// return change TODO : extend to spread out to higher levels
			b.resources -= resourceCount;
			blueChips.count += resourceCount;
		}
	}

	@Override
	public boolean playCard(Player player) {
		if (super.playCard(player)){
			if (name.equals("Bronze")) player.playedMines[0] = this;
			else if (name.equals("Iron")) player.playedMines[1] = this;
			else if (name.equals("Coal")) player.playedMines[2] = this;
			else if (name.equals("Oil")) player.playedMines[3] = this;
			return true;
		}
		return false;
	}
}
