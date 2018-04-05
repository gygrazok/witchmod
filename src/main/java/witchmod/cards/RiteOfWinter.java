package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.RiteOfWinterAction;

public class RiteOfWinter extends AbstractWitchCard {
	public static final String ID = "RiteOfWinter";
	public static final	String NAME = "Rite Of Winter";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Place any number of cards in your hand on top of your draw pile and gain !B! Block for each of them.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	private static final int COST = 1;
	private static final int BLOCK = 4;
	private static final int BLOCK_UPGRADED = 3;

	
	public RiteOfWinter() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = BLOCK;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new RiteOfWinterAction(block));
	}
	
	public AbstractCard makeCopy() {
		return new RiteOfWinter();
	}
	
	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBlock(BLOCK_UPGRADED);
		}
	}
}
