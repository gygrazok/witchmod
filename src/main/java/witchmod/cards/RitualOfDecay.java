package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.RitualOfDecayPower;

public class RitualOfDecay extends AbstractWitchCard {
	public static final String ID = "RitualOfDecay";
	public static final	String NAME = "Ritual of Decay";
	public static final	String IMG = "cards/placeholder_power.png";
	public static final	String DESCRIPTION = "When you play the first card each turn, all enemies lose health equal to the cost of that card.";
	public static final	String DESCRIPTION_UPGRADED = "When you play the first card each turn, all enemies lose health equal to the cost of that card. Innate.";
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;


	
	public RitualOfDecay() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RitualOfDecayPower(p)));
	}
	
	public AbstractCard makeCopy() {
		return new RitualOfDecay();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			isInnate = true;
			rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
