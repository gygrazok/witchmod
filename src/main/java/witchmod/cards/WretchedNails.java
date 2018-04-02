package witchmod.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.CollectorStakeEffect;

import witchmod.characters.WitchCharacter;

public class WretchedNails extends AbstractWitchCard{
	public static final String ID = "WretchedNails";
	public static final	String NAME = "Wretched Nails";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "When you draw this, draw a card. Deal damage equal to double the number of cards you have drawn this turn.";
	public static final	String DESCRIPTION_UPGRADED = "When you draw this, draw !M! cards. Deal damage equal to double the number of cards you have drawn this turn.";
	public static final String EXTENDED_DESCRIPTION = " NL (Current damage !D!)";
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 2;
	private static final int MAGIC = 1;
	private static final int MAGIC_UPGRADE_BONUS = 1;

	public WretchedNails() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseMagicNumber = this.magicNumber = MAGIC;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int cardsDrawn = ((WitchCharacter)AbstractDungeon.player).cardsDrawnThisTurn;
		for (int i = 0; i < cardsDrawn; i++) {
			AbstractDungeon.effectsQueue.add(new CollectorStakeEffect(m.hb.cX + MathUtils.random(-50.0f, 50.0f) * Settings.scale, m.hb.cY + MathUtils.random(-60.0f, 60.0f) * Settings.scale));
		}
		AbstractDungeon.actionManager.addToBottom(new WaitAction(1.0f));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.SMASH));
	}

	public AbstractCard makeCopy() {
		return new WretchedNails();
	}
	
	@Override
	public void triggerWhenDrawn() {
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, magicNumber));
		flash();
	}
	
    @Override
    public void applyPowers() {
    	calcBaseDamage();
        super.applyPowers();
        rawDescription = upgraded?DESCRIPTION_UPGRADED:DESCRIPTION;
        rawDescription = rawDescription + EXTENDED_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = upgraded?DESCRIPTION_UPGRADED:DESCRIPTION;
        initializeDescription();
    }
	
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		calcBaseDamage();
		super.calculateCardDamage(mo);	
        rawDescription = upgraded?DESCRIPTION_UPGRADED:DESCRIPTION;
        rawDescription = rawDescription + EXTENDED_DESCRIPTION;
        initializeDescription();
	}
	
	private void calcBaseDamage() {
		baseDamage = ((WitchCharacter)AbstractDungeon.player).cardsDrawnThisTurn*2;
	}
	

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			rawDescription = DESCRIPTION_UPGRADED;
			upgradeMagicNumber(MAGIC_UPGRADE_BONUS);
			initializeDescription();
		}
	}
}
