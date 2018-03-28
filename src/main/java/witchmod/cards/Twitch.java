package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.TwitchAction;

public class Twitch extends AbstractWitchCard{
	public static final String ID = "Twitch";
	public static final	String NAME = "Twitch";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "When drawn reduce by 1 the cost of all Skills in your hand for this turn. NL Gain !B! block.";
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 2;
	private static final int POWER = 14;
	private static final int UPGRADE_BONUS = 4;
	
	private static final int MAGIC = 1;

	public Twitch() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = POWER;
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
	}

	public AbstractCard makeCopy() {
		return new Twitch();
	}
	
	@Override
	public void triggerWhenDrawn() {
		super.triggerWhenDrawn();
        AbstractDungeon.actionManager.addToBottom(new TwitchAction());
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BONUS);
		}
	}
}
