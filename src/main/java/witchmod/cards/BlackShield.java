package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BlackShield extends AbstractWitchCard{
	public static final String ID = "BlackShield";
	public static final	String NAME = "Black Shield";
	public static final	String IMG = "cards/blackshield.png";
	public static final	String DESCRIPTION = "Gain !B! Block, doubled if you have at least a Curse in hand.";
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 7;
	private static final int UPGRADE_BONUS = 2;


	public BlackShield() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int finalBlock = block;
		for (AbstractCard c : p.hand.group) {
			if (c.type == CardType.CURSE) {
				finalBlock += block;
				break;
			}
		}
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, finalBlock));
		
	}

	public AbstractCard makeCopy() {
		return new BlackShield();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BONUS);
		}
	}
}
