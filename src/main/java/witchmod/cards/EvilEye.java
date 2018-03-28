package witchmod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;

import witchmod.characters.WitchCharacter;

public class EvilEye extends AbstractWitchCard{
	public static final String ID = "EvilEye";
	public static final	String NAME = "Evil Eye";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Deal !D! damage. NL Deals an additional +!M! damage for each Curse drawn this combat.";
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = 1;
	private static final int POWER = 6;
	private static final int MAGIC = 2;
	private static final int MAGIC_UPGRADE_BONUS = 1;

	public EvilEye() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
		this.magicNumber = this.baseMagicNumber = MAGIC;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.RED), 0.25f));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.25f));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		
	}
	
	@Override
	public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
		return tmp + magicNumber*((WitchCharacter)player).cursesDrawnTotal;
	}

	public AbstractCard makeCopy() {
		return new EvilEye();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(MAGIC_UPGRADE_BONUS);
		}
	}
}
