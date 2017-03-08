package phase_1;

public class Node{
	public String name;
	public int cost;
	public int value;
	public double ratio;

	public Node(String name, int cost, int value){
		this.name = name;
		this.cost = cost;
		this.value = value;
		this.ratio = (double)cost/value;
	}
}