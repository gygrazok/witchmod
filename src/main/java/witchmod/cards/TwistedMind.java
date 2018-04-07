package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.TwistedMindPower;

public class TwistedMind extends AbstractWitchCard {
	public static final String ID = "TwistedMind";
	public static final	String NAME = "Twisted Mind";
	public static final	String IMG = "cards/placeholder_power.png";
	public static final	String DESCRIPTION = "When you play the first card each turn, all enemies lose health equal to the cost of that card.";
	public static final	String DESCRIPTION_UPGRADED = " Innate. When you play the first card each turn, all enemies lose health equal to the cost of that card.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;
	
	private static final int POOL = 1;
	
	private static final int COST = 0;


	
	public TwistedMind() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TwistedMindPower(p,1),1));
	}
	
	public AbstractCard makeCopy() {
		return new TwistedMind();
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
