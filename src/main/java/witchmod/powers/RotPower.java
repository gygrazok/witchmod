package witchmod.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import witchmod.WitchMod;
import witchmod.actions.RotLoseHPAction;

public class RotPower extends AbstractWitchPower implements HealthBarRenderPower {
    private static final String POWER_ID = "Rot";
    public static final String POWER_ID_FULL = "WitchMod:Rot";
    //public static final String NAME = "Rot";
    /*public static final String[] DESCRIPTIONS = new String[]{ "At the start of its turn, loses #b",
													    		" HP, then Rot increases by 1.",
													    		" NL When it dies, spread to another monster.",
													    		"Contagious"};*/
    public static final String IMG = "powers/rot.png";
    private AbstractCreature source;
    public boolean contagious;
    public RotPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this(owner,source,amount,false);
    }
    
    public RotPower(AbstractCreature owner, AbstractCreature source, int amount, boolean contagious) {
        super(POWER_ID);
        this.owner = owner;
        this.source = source;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture(WitchMod.getResourcePath(IMG));
        this.isTurnBased = true;
        this.type = PowerType.DEBUFF;
        this.contagious = contagious;
        if (this.contagious) {
        	this.name = DESCRIPTIONS[3]+" "+this.name;
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
        if (contagious) {
        	description += DESCRIPTIONS[2];
        }
    }
    

    @Override
    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flashWithoutSound();
            AbstractDungeon.actionManager.addToBottom(new RotLoseHPAction(owner, source, amount, contagious, AbstractGameAction.AttackEffect.POISON));
        }
    }

    @Override
    public int getHealthBarAmount() {
        return amount;
    }

    @Override
    public Color getColor() {
        return Color.DARK_GRAY;
    }
}


