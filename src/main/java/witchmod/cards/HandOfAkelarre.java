package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import witchmod.characters.WitchCharacter;

public class HandOfAkelarre extends AbstractWitchCard{
	public static final String ID = "HandOfAkelarre";
	public static final	String NAME = "Hand of Akelarre";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal damage equal to double the number of cards you drew this turn.";
	public static final	String DESCRIPTION_UPGRADED = "Draw !M! card, then deal damage equal to double the number of cards you drew this turn.";
	public static final String EXTENDED_DESCRIPTION = " NL (Current damage !D!)";
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 2;
	private static final int MAGIC = 1;

	public HandOfAkelarre() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (upgraded) {
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
			//the bonus damage from drawing this card is already included in the calcBaseDamage function
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.SMASH));
	}

	public AbstractCard makeCopy() {
		return new HandOfAkelarre();
	}
	
    @Override
    public void applyPowers() {
    	calcBaseDamage();
        super.applyPowers();
        this.rawDescription = upgraded?DESCRIPTION_UPGRADED:DESCRIPTION;
        this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = upgraded?DESCRIPTION_UPGRADED:DESCRIPTION;
        this.initializeDescription();
    }
	
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		calcBaseDamage();
		super.calculateCardDamage(mo);	
        this.rawDescription = upgraded?DESCRIPTION_UPGRADED:DESCRIPTION;
        this.rawDescription = this.rawDescription + EXTENDED_DESCRIPTION;
        this.initializeDescription();
	}
	
	private void calcBaseDamage() {
		baseDamage = ((WitchCharacter)AbstractDungeon.player).cardsDrawnThisTurn;
		if (upgraded) {
			baseDamage++;
		}
		baseDamage = baseDamage*2;
	}
	

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
		}
	}
}
