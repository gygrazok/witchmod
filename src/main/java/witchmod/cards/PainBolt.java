package witchmod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.PaperFrog;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;

public class PainBolt extends AbstractWitchCard{
	public static final String ID = "PainBolt";
	public static final	String NAME = "Pain Bolt";
	public static final	String IMG = "cards/placeholder_attack.png";
	public static final	String DESCRIPTION = "Applies X vulnerable. Deal !D! damage";
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	
	private static final int POOL = 1;
	
	private static final int COST = -1;
	private static final int POWER = 4;
	private static final int UPGRADE_BONUS = 2;

	public PainBolt() {
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,RARITY,TARGET,POOL);
		this.baseDamage = POWER;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int effect = EnergyPanel.getCurrentEnergy();
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new BorderFlashEffect(Color.SCARLET)));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY), 0.3f));
        if (effect > 0) {
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, effect, false), effect));
        	if (!m.hasPower(VulnerablePower.POWER_ID)) {
        		//since the damage is calculated BEFORE the applying of vulnerable,
        		//we have to manually account for the vulnerable we just applied if the target doesn't already have it
        		this.damage *= p.hasRelic(PaperFrog.ID)?1.75:1.5;
        	}
    		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn),AbstractGameAction.AttackEffect.NONE));
        }
		p.energy.use(effect);
	}
	
	@Override
	public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
		int effect = EnergyPanel.getCurrentEnergy();
		return tmp*effect;
	}
	
    @Override
    public void onMoveToDiscard() {
        this.rawDescription = DESCRIPTION;
        this.initializeDescription();
    }

	public AbstractCard makeCopy() {
		return new PainBolt();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_BONUS);
		}
	}
}
