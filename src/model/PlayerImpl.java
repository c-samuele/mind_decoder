package model;

public class PlayerImpl implements Player {
	
	private String name;
	
	public PlayerImpl(String name){
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getCurrentLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		
	}

}
