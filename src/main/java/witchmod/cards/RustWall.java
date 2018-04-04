package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RustWall extends AbstractWitchCard{
	public static final String ID = "RustWall";
	public static final	String NAME = "Rust Wall";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Gain !B! block. Shuffle a Curse of Rust card in your draw pile.";
	
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
		AbstractDungeon.actionManager.addToBottom(new MakeTempCardInDrawPileAction(AbstractDungeon.player, AbstractDungeon.player, new RustWallCurse(), 1, true, false));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
		
	}

	public AbstractCard makeCopy() {
		return new RustWall();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BONUS);
		}
	}
}
