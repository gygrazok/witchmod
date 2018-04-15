package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.RiteOfSpringAction;

public class RiteOfSpring extends AbstractWitchCard {
	public static final String ID = "RiteOfSpring";
	public static final	String NAME = "Rite of Spring";
	public static final	String IMG = "cards/riteofspring.png";
	public static final	String DESCRIPTION = "Shuffle in your draw pile any number of cards in your hand and gain twice that much HP. Exhaust";

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 1;
	private static final int COST = 2;
	private static final int COST_UPGRADED = 1;



	public RiteOfSpring() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new RiteOfSpringAction());
	}

	public AbstractCard makeCopy() {
		return new RiteOfSpring();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}
