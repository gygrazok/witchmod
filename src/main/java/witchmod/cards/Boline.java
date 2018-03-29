package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Boline extends AbstractWitchCard{
	public static final String ID = "Boline";
	public static final	String NAME = "Boline";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! damage. If this kills an enemy, obtain a random potion and Exhaust this card.";
	public static final	String DESCRIPTION_UPGRADED = "Deal !D! damage. If this kills an enemy, obtain a random potion and Exhaust this card. NL Persistent.";
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 6;
	private static final int POWER_UPGRADED_BONUS = 3;

	public Boline() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if ((damage >= m.currentHealth+m.currentBlock && damageTypeForTurn == DamageType.NORMAL)
			|| (damage >= m.currentHealth && damageTypeForTurn == DamageType.HP_LOSS)) {
			//enemy will die due to this attack
			exhaust = true;
			AbstractDungeon.actionManager.addToBottom(new ObtainPotionAction(PotionHelper.getRandomPotion()));
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.SLASH_VERTICAL));
	}

	public AbstractCard makeCopy() {
		return new Boline();
	}
	
	@Override
	public void atTurnStart(){
		if (upgraded) {
			this.retain = true;
		}
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(POWER_UPGRADED_BONUS);
			this.rawDescription = DESCRIPTION_UPGRADED;
			initializeDescription();
			this.retain = true;
		}
	}
}
