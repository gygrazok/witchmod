package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RoilingBarrier extends AbstractWitchCard{
	public static final String ID = "RoilingBarrier";
	public static final	String NAME = "Roiling Barrier";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Gain !B! Block. This value is increased by !M! everytime you draw this card. NL Recurrent.";
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 6;
	
	private static final int MAGIC = 1;
	private static final int MAGIC_UPGRADE_BONUS = 1;

	public RoilingBarrier() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = POWER;
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		reshuffleOnUse = true;
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
	}

	@Override
	public void triggerWhenDrawn() {
		super.triggerWhenDrawn();
		baseBlock += magicNumber;
		isBlockModified = true;
	}
	
	public AbstractCard makeCopy() {
		return new RoilingBarrier();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeMagicNumber(MAGIC_UPGRADE_BONUS);
		}
	}
}
