package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.RotPower;

public class PlagueSpreader extends AbstractWitchCard{
	public static final String ID = "PlagueSpreader";
	public static final	String NAME = "Plague Spreader";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Apply !M! Rot to ALL enemies. NL Exhaust.";
	public static final	String DESCRIPTION_UPGRADED = "Apply !M! Rot to ALL enemies. NL Exhaust. NL Innate.";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int MAGIC = 1;

	public PlagueSpreader() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.magicNumber = this.baseMagicNumber = MAGIC;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new RotPower(mo,p, magicNumber, false), magicNumber, true));
		}
	}

	public AbstractCard makeCopy() {
		return new PlagueSpreader();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			isInnate = true;
		}
	}
}
