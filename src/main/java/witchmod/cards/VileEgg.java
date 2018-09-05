package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.SummonFamiliarPower;

public class VileEgg extends AbstractWitchCard{
	public static final String ID = "VileEgg";
	public static final	String NAME = "Vile Egg";
	public static final	String IMG = "cards/vileegg.png";
	public static final	String DESCRIPTION = "Heal !M! HP. NL Add a random Familiar to your hand. It costs 0 this turn. NL Exhaust";
	public static final	String DESCRIPTION_UPGRADED = "Heal !M! HP. NL Add a random upgraded Familiar to your hand. It costs 0 this turn. Exhaust";

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 1;

	private static final int COST = 0;
	private static final int POWER = 3;
	private static final int UPGRADE_BONUS = 2;

	public VileEgg() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseMagicNumber = this.magicNumber = POWER;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, magicNumber));
		AbstractCard toCreate = SummonFamiliarPower.getRandomFamiliarCard();
		if (upgraded) {
			toCreate.upgrade();
		}
		toCreate.costForTurn = 0;
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(toCreate,1));
	}

	public AbstractCard makeCopy() {
		return new VileEgg();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
			rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
