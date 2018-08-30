package witchmod.orbs;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CatOrb extends FamiliarOrb{
	public static final String ID = "CatOrb";
	public static final String NAME = "Cat";
	public static final int COST = 1;
	public static final String DESCRIPTION = "Deal !M! damage 2 times to a random enemy";
	public static final String DESCRIPTION_UPGRADED = "Deal !M! damage 3 times to a random enemy";
	public static final int POWER = 3;
	public CatOrb(boolean upgraded) {
		super(ID,upgraded,COST,NAME,DESCRIPTION,DESCRIPTION_UPGRADED);
		this.magicNumber = this.baseMagicNumber = POWER;
		updateDescription();
	}
	
	@Override
	public void onEvoke() {
		AbstractMonster m = AbstractDungeon.getRandomMonster();
		AbstractPlayer p = AbstractDungeon.player;
		calcMagicNumber(m);
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, magicNumber, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, magicNumber, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
		if (upgraded) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, magicNumber, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		}
	}

	@Override
	public AbstractOrb makeCopy() {
		return new CatOrb(upgraded);
	}


	private void calcMagicNumber(AbstractMonster monster) {
		calcMagicNumber();
		float temp = magicNumber;
		for (AbstractPower power : monster.powers) {
			temp = power.atDamageReceive(temp,DamageInfo.DamageType.NORMAL);
		}
		for (AbstractPower power : monster.powers) {
			temp = power.atDamageFinalReceive(temp,DamageInfo.DamageType.NORMAL);
		}
		magicNumber = MathUtils.floor(temp);
	}

	@Override
	public void calcMagicNumber() {
		magicNumber = baseMagicNumber;
		float temp = magicNumber;
		for (AbstractPower power : AbstractDungeon.player.powers) {
			temp = power.atDamageGive(temp,DamageInfo.DamageType.NORMAL);
		}
		for (AbstractPower power : AbstractDungeon.player.powers) {
			temp = power.atDamageFinalGive(temp,DamageInfo.DamageType.NORMAL);
		}
		magicNumber = MathUtils.floor(temp);
	}
}
