package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.RemembranceAction;

public class Remembrance extends AbstractWitchCard{
	public static final String ID = "Remembrance";
	public static final	String NAME = "Remembrance";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Shuffle a random card of each different type from your discard pile into your draw pile.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.NONE;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int UPGRADED_COST = 0;

	public Remembrance() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new RemembranceAction());      
	}
	
	public AbstractCard makeCopy() {
		return new Remembrance();
	}
	

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(UPGRADED_COST);
		}
	}
	
}
