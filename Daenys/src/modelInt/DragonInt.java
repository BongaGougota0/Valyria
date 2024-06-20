package modelInt;

public abstract class DragonInt {
	public String dracarys() {
		return "Fire! Fire!! Fire!!! Fire!!! Fireeeeeeeee!!!! Fireeeeeeeeee! \n Dracarys!";
	};
	public abstract String roar();
	
	public abstract void attack();
	public void fly() {
		System.out.print("Giant leap to the sky! Swoosh");
		System.out.print("Spread my giant wings and fly. Vallah!");

	}
	
	public abstract DragonRegion dragonBirthPlace();
}
