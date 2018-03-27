package witchmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class UnluckySevenCleansed extends AbstractWitchCard {
	public static final String ID = "UnluckySevenCleansed";
	public static final	String NAME = "Lucky Seven";
	public static final	String IMG = "cards/placeholder_skill.png";
	public static final	String DESCRIPTION = "Gain !M! [B]. NL Draw !M! cards. NL Exhaust.";
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	
	private static final int POOL = 0;
	
	private static final int COST = 0;
	private static final int POWER = 7;


	
	public UnluckySevenCleansed() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseMagicNumber = this.magicNumber = POWER;
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new UnluckySevenCleansed();
	}
	
	@Override
	public boolean canUpgrade(){
		return false;
	}

	@Override
	public void upgrade() {
		
	}
}
