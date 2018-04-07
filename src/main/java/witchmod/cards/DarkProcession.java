package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.powers.DarkProcessionPower;

public class DarkProcession extends AbstractWitchCard {
	public static final String ID = "DarkProcession";
	public static final	String NAME = "Dark Procession";
	public static final	String IMG = "cards/darkprocession.png";
	public static final	String DESCRIPTION = "At the start of your turn add a copy of the last played card to your hand.";
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;
	
	private static final int POOL = 1;
	
	private static final int COST = 3;
	private static final int COST_UPGRADED = 2;

	
	public DarkProcession() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DarkProcessionPower()));
	}
	
	public AbstractCard makeCopy() {
		return new DarkProcession();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}
