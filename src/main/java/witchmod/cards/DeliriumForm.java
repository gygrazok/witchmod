package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.DeliriumFormPower;


public class DeliriumForm extends AbstractWitchCard {
	public static final String ID = "DeliriumForm";
	public static final	String NAME = "Delirium Form";
	public static final	String IMG = "cards/deliriumform.png";
	public static final	String DESCRIPTION = "Whenever you play a non-zero Cost card, draw 1 card.";

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;

	private static final int POOL = 1;

	private static final int COST = 3;
	private static final int COST_UPGRADED = 2;
	private static final int POWER = 1;

	public DeliriumForm() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DeliriumFormPower(magicNumber),magicNumber));
	}

	public AbstractCard makeCopy() {
		return new DeliriumForm();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}