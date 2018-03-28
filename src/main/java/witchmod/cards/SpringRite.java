package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.SpringRiteAction;

public class SpringRite extends AbstractWitchCard {
	public static final String ID = "SpringRite";
	public static final	String NAME = "Spring Rite";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Shuffle in your draw pile any number of cards in your hand and gain that much HP. Exhaust";
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	private static final int COST = 2;
	private static final int COST_UPGRADED = 1;


	
	public SpringRite() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new SpringRiteAction());
	}
	
	public AbstractCard makeCopy() {
		return new SpringRite();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}
