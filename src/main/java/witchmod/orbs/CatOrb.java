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
import sun.net.www.protocol.https.AbstractDelegateHttpsURLConnection;
import witchmod.cards.familiar.CatFamiliar;

public class CatOrb extends FamiliarOrb{
	public static final String ID = "CatOrb";
	public static final String NAME = "Cat";
	public static final int COST = 1;
	public static final String DESCRIPTION = "Deal !M! damage 2 times to a random enemy";
	public static final String DESCRIPTION_UPGRADED = "Deal !M! damage 3 times to a random enemy";
	public static final int POWER = 3;
	public CatOrb(boolean upgraded) {
		super(ID,upgraded,COST,NAME,DESCRIPTION,DESCRIPTION_UPGRADED);
		this.magicNumber = POWER;
	}
	
	@Override
	public void onEvoke() {
		AbstractMonster m = AbstractDungeon.getRandomMonster();
		AbstractPlayer p = AbstractDungeon.player;
		int damage = calcDamage(m,magicNumber);
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
		if (upgraded) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		}
	}

	@Override
	public AbstractOrb makeCopy() {
		return new CatOrb(upgraded);
	}


	private int calcDamage(AbstractMonster monster, int baseDamage) {
		float out = baseDamage;
		for (AbstractPower power : AbstractDungeon.player.powers) {
			out += power.atDamageGive(out,DamageInfo.DamageType.NORMAL);
		}
		for (AbstractPower power : AbstractDungeon.player.powers) {
			out += power.atDamageFinalGive(out,DamageInfo.DamageType.NORMAL);
		}
		for (AbstractPower power : monster.powers) {
			out += power.atDamageReceive(out,DamageInfo.DamageType.NORMAL);
		}
		for (AbstractPower power : monster.powers) {
			out += power.atDamageFinalReceive(out,DamageInfo.DamageType.NORMAL);
		}
		return MathUtils.floor(out);
	}
}
