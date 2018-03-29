package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EternalThirstCleansed extends AbstractWitchCard{
	public static final String ID = "EternalThirstCleansed";
	public static final	String NAME = "Eternal Thirst";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! damage, then heal for the unblocked damage dealt.";
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 0;
	
	private static final int COST = 1;
	private static final int POWER = 10;

	public EternalThirstCleansed() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new VampireDamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.SLASH_DIAGONAL));      
	}
	
	public AbstractCard makeCopy() {
		return new EternalThirstCleansed();
	}

	public boolean canUpgrade(){
		return false;
	}

	@Override
	public void upgrade() {
		
	}
}
