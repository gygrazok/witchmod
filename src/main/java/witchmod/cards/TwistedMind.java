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
	public static final	String IMG = "cards/twistedmind.png";
	public static final	String DESCRIPTION = "Whenever you play a card that costs 2 or more, ALL enemies lose !M! health.";

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;

	private static final int POOL = 1;

	private static final int COST = 1;
	private static final int POWER = 3;
	private static final int UPGRADED_BONUS = 2;


	public TwistedMind() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseMagicNumber = this.magicNumber = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new TwistedMindPower(p,magicNumber),magicNumber));
	}

	public AbstractCard makeCopy() {
		return new TwistedMind();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADED_BONUS);
		}
	}
}
