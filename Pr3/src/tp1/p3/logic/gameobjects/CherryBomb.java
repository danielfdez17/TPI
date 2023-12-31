package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.logic.actions.ExplosionAction;
import tp1.p3.view.Messages;

public class CherryBomb extends Plant {
	
	// CONSTATNS
	public static final int DAMAGE = 10;
	public static final int ENDURANCE = 2;
	public static final int COST = 50;
	public static final int EXPLOSION_DAMAGE = 10;
	
	// ATRIBUTE
	private ExplosionAction explosion;

	// BUILDERS
	public CherryBomb() {
		super();
	}
	public CherryBomb(GameWorld game, int col, int row) {
		super(game, col, row);
		this.damage = DAMAGE;
		this.endurance = ENDURANCE;
		this.cost = COST;
		this.cycles = 0;
		this.explosion = new ExplosionAction(this.col, this.row, EXPLOSION_DAMAGE, false);
	}

	// METHODS
	@Override
	protected String getSymbol() {
		String string = Messages.CHERRY_BOMB_SYMBOL;
		if (this.cycles == 2) {
			return string.toUpperCase();
		}
		else {
			return string;			
		}
	}
	@Override
	public String getDescription() {
		return Messages.plantDescription(Messages.CHERRY_BOMB_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE);
	}
	@Override
	public String getName() {
		return Messages.CHERRY_BOMB_NAME;
	}
	@Override
	public void onExit() {
		this.game.pushAction(this.explosion);
		this.endurance = 0;
	}
	@Override
	public void update() {
		if (this.isAlive()) {
			if (this.cycles > 0 && this.cycles == ENDURANCE) {
				this.onExit();
			}
			this.cycles++;
		}
		else {
			this.onExit();
		}
	}
	@Override
	public int getCost() {
		return this.cost;
	}
	@Override
	public Plant spawn(GameWorld game, int col, int row) {
		return new CherryBomb(game, col, row);
	}
}
