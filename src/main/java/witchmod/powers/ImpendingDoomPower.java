package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import witchmod.WitchMod;

public class ImpendingDoomPower extends AbstractWitchPower {
    private static final String POWER_ID = "ImpendingDoom";
    //public static final String NAME = "Impending Doom";
    //public static final String[] DESCRIPTIONS = new String[]{ "At the end of its next turn, loses #b"," health."};
    public static final String IMG = "powers/impendingdoom.png";
    private AbstractCreature source;

    public ImpendingDoomPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID);
        this.owner = owner;
        this.source = source;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(WitchMod.getResourcePath(IMG));
        this.isTurnBased = true;
        this.type = PowerType.DEBUFF;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }
    

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            AbstractDungeon.actionManager.addToBottom(new DamageAction(owner,new DamageInfo(source, amount, DamageType.HP_LOSS),AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, source, ID));
        }
    }
}


