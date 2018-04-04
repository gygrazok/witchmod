package witchmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MagicFang extends AbstractWitchCard{
	public static final String ID = "MagicFang";
	public static final	String NAME = "Magic Fang";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! Damage two times. Gain !B! Block.";
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int DAMAGE = 3;
	private static final int DAMAGE_UPGRADED_BONUS = 2;
	private static final int BLOCK = 3;
	private static final int BLOCK_UPGRADED_BONUS = 2;

	public MagicFang() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = DAMAGE;
		this.baseBlock = BLOCK;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.SLASH_VERTICAL));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.SLASH_HORIZONTAL));
	}

	public AbstractCard makeCopy() {
		return new MagicFang();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(DAMAGE_UPGRADED_BONUS);
			upgradeBlock(BLOCK_UPGRADED_BONUS);
	
		}
	}
}
