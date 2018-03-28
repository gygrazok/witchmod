package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Implosion extends AbstractWitchCard{
	public static final String ID = "Implosion";
	public static final	String NAME = "Implosion";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! damage.";
	public static final	String DESCRIPTION_EXTENDED[] = new String[] {"If this kills an enemy, other enemies are dealt ", "half", " damage"};
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 3;
	private static final int POWER = 24;
	private static final int UPGRADED_BONUS = 6;


	public Implosion() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.rawDescription = DESCRIPTION + " "+DESCRIPTION_EXTENDED[0]+DESCRIPTION_EXTENDED[1]+DESCRIPTION_EXTENDED[2];
		this.initializeDescription();
		this.isMultiDamage = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if ((damage > m.currentHealth+m.currentBlock && damageTypeForTurn == DamageType.NORMAL)
				|| (damage > m.currentHealth && damageTypeForTurn == DamageType.HP_LOSS)) {
			//enemy will die due to this attack
			AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5f));
			AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AttackEffect.FIRE));
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.BLUNT_HEAVY));
	}

	public AbstractCard makeCopy() {
		return new Implosion();
	}

    @Override
    public void applyPowers() {
        super.applyPowers();
        calcCardDamage();
    }
	

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
		calcCardDamage();
    }
    
    private void calcCardDamage(){
        int[] halfMultidamage = new int[multiDamage.length];
        for (int i = 0; i < multiDamage.length; i++) {
        	halfMultidamage[i] = Math.floorDiv(multiDamage[i],2);
        }
        multiDamage = halfMultidamage;
		rawDescription = DESCRIPTION + " "+DESCRIPTION_EXTENDED[0]+Math.floorDiv(damage,2)+DESCRIPTION_EXTENDED[2];
		initializeDescription();
    }
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADED_BONUS);
		}
	}
}
