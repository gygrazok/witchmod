package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.IntelligencePower;

public class Intelligence extends AbstractWitchCard {
	public static final String ID = "Intelligence";
	public static final	String NAME = "Intelligence";
	public static final	String IMG = "cards/intelligence.png";
	public static final	String DESCRIPTION = "At the start of your turn, draw !M! random card from your discard pile.";
	public static final	String DESCRIPTION_PLURAL = "At the start of your turn, draw !M! random cards from your discard pile";

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;

	private static final int POOL = 1;

	private static final int COST = 1;

	private static final int POWER = 1;
	private static final int UPGRADE_BONUS = 1;


	public Intelligence() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseMagicNumber = this.magicNumber = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		System.out.println("Magic = "+magicNumber+" "+baseMagicNumber);
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntelligencePower(p,magicNumber),magicNumber));
	}

	public AbstractCard makeCopy() {
		return new Intelligence();
	}

	@Override
	protected void upgradeMagicNumber(int amount) {
		super.upgradeMagicNumber(amount);
		if (magicNumber == 1) {
			rawDescription = DESCRIPTION;
		} else {
			rawDescription = DESCRIPTION_PLURAL;
		}
		initializeDescription();
	}


	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
