package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.actions.ShuffleCardInDeckAction;

public class UnnaturalEnergy extends AbstractWitchCard {
	public static final String ID = "UnnaturalEnergy";
	public static final	String NAME = "Unnatural Energy";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Gain [B]. Recurrent.";
	public static final	String DESCRIPTION_UPGRADED = "Gain [B][B]. Recurrent.";
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 1;
	
	private static final int COST = 0;
	private static final int POWER = 1;
	private static final int UPGRADE_BONUS = 1;


	
	public UnnaturalEnergy() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseMagicNumber = POWER;
		this.retain = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ShuffleCardInDeckAction(AbstractDungeon.player, this));
	}
	
	
	public AbstractCard makeCopy() {
		return new UnnaturalEnergy();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
		}
	}
}
