/*
 * Decompiled with CFR.
 */
package witchmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import witchmod.WitchMod;

public class ImpendingDoomPower extends AbstractPower {
    public static final String POWER_ID = "ImpendingDoom";
    public static final String NAME = "Impending Doom";
    public static final String[] DESCRIPTIONS = new String[]{ "At the end of its next turn, loses #b"};
    public static final String IMG = "powers/athamesoffering.png";
    private AbstractCreature source;
    public ImpendingDoomPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
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
        this.description = DESCRIPTIONS[0]+this.amount;
    }
    

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();
            AbstractDungeon.actionManager.addToBottom(new DamageAction(owner,new DamageInfo(source, amount, DamageType.HP_LOSS),AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.source, "ImpendingDoom"));
        }
    }
}


