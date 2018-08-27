package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.BaseModTags;
import basemod.helpers.CardTags;
import witchmod.powers.UnholyFormPower;

public class UnholyForm extends AbstractWitchCard {
	public static final String ID = "UnholyForm";
	public static final	String NAME = "Unholy Form";
	public static final	String IMG = "cards/wickedthoughts.png";
	public static final	String DESCRIPTION = "Enemy attacks won't deal more damage than !M! % of your max health. At the start of your turn add a random Curse to your draw pile.";

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;

	private static final int POOL = 1;
	private static final int COST = 3;

	private static final int POWER = 6;
	private static final int POWER_UPGRADED = 3;
	
	public UnholyForm() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
		CardTags.addTags(this, BaseModTags.FORM);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int limit = p.maxHealth/magicNumber;
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new UnholyFormPower(p,limit)));
	}

	public AbstractCard makeCopy() {
		return new UnholyForm();
	}


	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(POWER_UPGRADED);
		}
	}
}
