
public class Test {
	public static void main(String[] args) {
		StringBuilder b = new StringBuilder("1");
		StringBuilder c = b.append("2");
		b.append("234");
		c.deleteCharAt(1);
		
		System.out.println("Equals? " + (b == c));
		System.out.println((b == c));
	} 
	
}
