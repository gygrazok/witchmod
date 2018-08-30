package witchmod.actions;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import witchmod.orbs.FamiliarOrb;

public class EvokeFamiliarAction extends AbstractGameAction {

    public FamiliarOrb familiar;

    public EvokeFamiliarAction(FamiliarOrb familiar) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.familiar = familiar;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            familiar.onEvoke();
            AbstractDungeon.player.energy.use(familiar.cost);
            familiar.used = true;
        }
        this.tickDuration();
    }
}

