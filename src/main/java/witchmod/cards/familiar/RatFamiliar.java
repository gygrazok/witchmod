package witchmod.cards.familiar;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

import witchmod.cards.AbstractWitchCard;
import witchmod.powers.DecrepitPower;

public class RatFamiliar extends AbstractWitchCard{
	public static final String ID = "RatFamiliar";
	public static final	String NAME = "Rat";
	public static final	String IMG = "cards/rat.png";
	public static final	String DESCRIPTION = "Ethereal. NL Apply !M! Poison and !M! Decrepit. NL Exhaust.";

	private static final CardRarity RARITY = CardRarity.SPECIAL;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 0;

	private static final int COST = 1;
	private static final int POWER = 2;
	private static final int UPGRADE_BONUS = 1;

	public RatFamiliar() {
		super(ID,IMG,COST,TYPE,RARITY,TARGET);
		this.magicNumber = this.baseMagicNumber = POWER;
		this.exhaust = true;
		this.isEthereal = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, magicNumber), magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new DecrepitPower(m, magicNumber, false), magicNumber));
	}

	public AbstractCard makeCopy() {
		return new RatFamiliar();
	}

	@Override
	public void triggerOnEndOfPlayerTurn() {
		AbstractDungeon.actionManager.addToTop(new ExhaustAllEtherealAction());
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
