package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.cards.familiar.FamiliarCardEnum;
import witchmod.powers.SummonFamiliarPower;

public class SummonToadFamiliar extends AbstractWitchCard {
	public static final String ID = "SummonToadFamiliar";
	public static final	String NAME = "Toad Familiar";
	public static final	String IMG = "cards/summonfrog.png";
	public static final	String DESCRIPTION = "At the start of your turn, add a Toad to your hand. NL Removes other Familiar powers.";
	public static final	String DESCRIPTION_UPGRADED = "At the start of your turn, add an upgraded Toad to your hand. Removes other Familiar powers.";

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;

	private static final int POOL = 1;
	private static final int COST = 0;



	public SummonToadFamiliar() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower(SummonFamiliarPower.POWER_ID)) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, SummonFamiliarPower.POWER_ID));
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SummonFamiliarPower(p, FamiliarCardEnum.TOAD, upgraded)));
	}

	public AbstractCard makeCopy() {
		return new SummonToadFamiliar();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
