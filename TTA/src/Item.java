
public class Item {

	public enum itemTypes {
		whiteAction,
		redAction,
		resource,
		food,
		worker,
		card,
		science,
		military,
		culture
	}
	
	public itemTypes value;
	public int count;
	
	public Item (itemTypes i, int count){
		value = i;
		this.count = count;
	}
}
