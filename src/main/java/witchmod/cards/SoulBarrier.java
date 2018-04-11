package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SoulBarrier extends AbstractWitchCard{
	public static final String ID = "SoulBarrier";
	public static final	String NAME = "Soul Barrier";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "When drawn gain !M! Block. NL Gain !B! Block. NL Exhaust.";

	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 1;

	private static final int COST = 0;
	private static final int POWER = 6;
	private static final int UPGRADE_BONUS = 2;

	private static final int MAGIC = 3;
	private static final int MAGIC_UPGRADE_BONUS = 1;

	public SoulBarrier() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = POWER;
		this.baseMagicNumber = this.magicNumber = MAGIC;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
	}

	public AbstractCard makeCopy() {
		return new SoulBarrier();
	}

	@Override
	public void triggerWhenDrawn() {
		super.triggerWhenDrawn();
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber));
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BONUS);
			upgradeMagicNumber(MAGIC_UPGRADE_BONUS);
		}
	}
}
