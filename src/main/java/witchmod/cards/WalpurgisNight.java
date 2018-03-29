package witchmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.WalpurgisNightAction;

public class WalpurgisNight extends AbstractWitchCard {
	public static final String ID = "WalpurgisNight";
	public static final	String NAME = "Walpurgis Night";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Shuffle ALL your Exhausted non-Status cards into your draw pile. NL Exhaust";
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.NONE;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 3;
	private static final int COST_UPGRADED = 2;

	
	public WalpurgisNight() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new WalpurgisNightAction());
	}
	
	public AbstractCard makeCopy() {
		return new WalpurgisNight();
	}
	

	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}
