package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MercurialRod extends AbstractWitchCard{
	public static final String ID = "MercurialRod";
	public static final	String NAME = "Mercurial Rod";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "If you have 0 block gain !B! block, otherwise deal !D! damage.";
	public static final	String[] EXTENDED_DESCRIPTION = new String[] {" NL Will gain !B! block"," NL Will deal !D! damage"};
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF_AND_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int DAMAGE = 6;
	private static final int BLOCK = 6;
	private static final int UPGRADE_BONUS = 2;

	public MercurialRod() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = DAMAGE;
		this.baseBlock = BLOCK;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.currentBlock == 0) {
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
		} else {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		}
	}

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = DESCRIPTION;
        if (AbstractDungeon.player.currentBlock == 0) {
        	rawDescription += EXTENDED_DESCRIPTION[0];
        	target = CardTarget.SELF;
        } else {
        	rawDescription += EXTENDED_DESCRIPTION[1];
        	target = CardTarget.ENEMY;
        }
        this.initializeDescription();
    }
    
    @Override
    public void onMoveToDiscard() {
        this.rawDescription = DESCRIPTION;
        this.initializeDescription();
    }
	
	public AbstractCard makeCopy() {
		return new MercurialRod();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
			upgradeBlock(UPGRADE_BONUS);
		}
	}
}