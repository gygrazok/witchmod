package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GnarledBody extends AbstractWitchCard{
	public static final String ID = "GnarledBody";
	public static final	String NAME = "Gnarled Body";
	public static final	String IMG = "cards/gnarledbody.png";
	public static final	String DESCRIPTION = "Costs 1 less for each card in hand. NL Gain !B! Block.";

	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 1;

	private static final int COST = 7;
	private static final int POWER = 10;
	private static final int UPGRADE_BONUS = 5;

	public GnarledBody() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
	}

	public AbstractCard makeCopy() {
		return new GnarledBody();
	}

	@Override
	public void applyPowers() {
		super.applyPowers();
		setCostForTurn(Math.max(0, COST - AbstractDungeon.player.hand.group.size()));
	}

	@Override
	public void triggerOnOtherCardPlayed(AbstractCard c) {
		setCostForTurn(Math.max(0, COST - AbstractDungeon.player.hand.group.size()));
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BONUS);
		}
	}
}
