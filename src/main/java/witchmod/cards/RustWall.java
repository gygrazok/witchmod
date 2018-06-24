package witchmod.cards;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.TooltipInfo;

public class RustWall extends AbstractWitchCard{
	public static final String ID = "RustWall";
	public static final	String NAME = "Wall of Rust";
	public static final	String IMG = "cards/rustwall.png";
	public static final	String DESCRIPTION = "Gain !B! block. Shuffle a Curse of Rust in your draw pile.";

	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;

	private static final int POOL = 1;

	private static final int COST = 0;
	private static final int POWER = 9;
	private static final int UPGRADE_BONUS = 5;


	public RustWall() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(new RustWallCurse(), 1, true, false));
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		List<TooltipInfo> out = new ArrayList<>();
		out.add(new TooltipInfo("Curse of Rust", "Halves your block at the end of the turn while in hand."));
		return out;
	}

	public AbstractCard makeCopy() {
		return new RustWall();
	}

	public void upgrade() {
		if (!upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BONUS);
		}
	}
}
