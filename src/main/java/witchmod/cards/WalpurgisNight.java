package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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
	public static final	String DESCRIPTION_UPGRADED = "Shuffle ALL your Exhausted non-Status cards into your draw pile, then draw !M! cards. NL Exhaust";
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.NONE;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 0;
	private static final int POWER = 2;
	
	public WalpurgisNight() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new WalpurgisNightAction());
		if (upgraded) {
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
		}
	}
	
	public AbstractCard makeCopy() {
		return new WalpurgisNight();
	}
	

	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
