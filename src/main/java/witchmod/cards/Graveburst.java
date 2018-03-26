package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class Graveburst extends AbstractWitchCard{
	public static final String ID = "Graveburst";
	public static final	String NAME = "Graveburst";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal damage to all enemies equal to the twice the number of Attacks in your discard pile.";
	public static final	String UPGRADE_DESCRIPTION = "Deal damage to all enemies equal to the twice the number of Attacks in your discard pile. Persistent.";
	public static final	String EXTENDED_DESCRIPTION[] = new String[] {" NL (Deals !D! damage.)"};
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 2;
	private static final int POWER = 2;

	public Graveburst() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.isMultiDamage = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AttackEffect.FIRE, true));      
	}
	
	public AbstractCard makeCopy() {
		return new Graveburst();
	}
	
    @Override
    public void applyPowers() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
        	if (c.type == CardType.ATTACK) {
        		count++;
        	}
        }
        baseDamage = count*POWER;
        super.applyPowers();
        rawDescription = upgraded?UPGRADE_DESCRIPTION:DESCRIPTION;
        rawDescription += EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }
	

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        rawDescription = upgraded?UPGRADE_DESCRIPTION:DESCRIPTION;
        rawDescription += EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }
    
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.retain = true;
		}
	}
	
	@Override
	public void atTurnStart(){
		if (upgraded) {
			this.retain = true;
		}
	}
	
}
