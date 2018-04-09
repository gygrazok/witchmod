package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.ChosenOfTheMoonPower;

public class ChosenOfTheMoon extends AbstractWitchCard {
	public static final String ID = "ChosenOfTheMoon";
	public static final	String NAME = "Chosen of theMoon";
	public static final	String IMG = "cards/chosenofthemoon.png";
	public static final	String DESCRIPTION = "Gain !M! Artifact when your turn begins if you have none.";

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;

	private static final int POOL = 1;

	private static final int COST = 1;
	private static final int POWER = 1;
	private static final int UPGRADE_BONUS = 1;



	public ChosenOfTheMoon() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseMagicNumber = this.magicNumber = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_AWAKENEDONE_1",0.3f));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ChosenOfTheMoonPower(p,magicNumber),magicNumber));
	}

	public AbstractCard makeCopy() {
		return new ChosenOfTheMoon();
	}


	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
