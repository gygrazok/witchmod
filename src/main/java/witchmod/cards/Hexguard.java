package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.HexguardPower;

public class Hexguard extends AbstractWitchCard {
	public static final String ID = "Hexguard";
	public static final	String NAME = "Hexguard";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Gain !B! block and !M! Artifact for 1 turn.";
	
	private static final CardRarity RARITY = CardRarity.BASIC;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 2;
	private static final int POWER = 12;
	private static final int UPGRADE_BONUS = 4;
	private static final int MAGIC = 1;


	
	public Hexguard() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseBlock = POWER;
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HexguardPower(p, magicNumber), magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new Hexguard();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_BONUS);
		}
	}
}
