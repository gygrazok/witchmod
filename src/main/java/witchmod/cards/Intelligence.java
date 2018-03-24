package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawPower;

public class Intelligence extends AbstractWitchCard {
	public static final String ID = "Intelligence";
	public static final	String NAME = "Intelligence";
	public static final	String IMG = "cards/placeholder_power.png";
	public static final	String DESCRIPTION = "Draw !M! additional cards each turn.";
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;

	private static final int POWER = 1;
	private static final int UPGRADE_BONUS = 1;

	
	public Intelligence() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseMagicNumber = this.magicNumber = POWER;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DrawPower(p,this.magicNumber),this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new Intelligence();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BONUS);
			upgradedMagicNumber = true;
		}
	}
}
