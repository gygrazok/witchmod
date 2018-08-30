package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import witchmod.cards.familiar.CatFamiliar;
import witchmod.cards.familiar.FamiliarCardEnum;
import witchmod.orbs.CatOrb;
import witchmod.powers.SummonFamiliarPower;

public class SummonCatFamiliarOrb extends AbstractWitchCard {
	public static final String ID = "SummonCatFamiliarOrb";
	public static final	String NAME = "Cat Familiar";
	public static final	String IMG = "cards/summoncat.png";
	public static final	String DESCRIPTION = "Summons a Cat Familiar.";

	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;

	private static final int POOL = 1;
	private static final int COST = 0;



	public SummonCatFamiliarOrb() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		cardPreviewTooltip = new CatFamiliar();
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new IncreaseMaxOrbAction(1));
		AbstractDungeon.actionManager.addToBottom(new ChannelAction(new CatOrb(upgraded));
	}

	public AbstractCard makeCopy() {
		return new SummonCatFamiliarOrb();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			initializeDescription();
			cardPreviewTooltip.upgrade();
		}
	}
}
