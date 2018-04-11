package witchmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.ReflectionHacks;
import witchmod.relics.WalkingCane;

@SpirePatch(cls="com.megacrit.cardcrawl.powers.WeakPower", method="atEndOfRound")
public class WeakPowerPatch {

	public static void Replace(WeakPower __instance) {
		boolean justApplied = (boolean) ReflectionHacks.getPrivate(__instance, WeakPower.class, "justApplied");
		if (justApplied) {
			ReflectionHacks.setPrivate(__instance, WeakPower.class, "justApplied", false);
			return;
		}
		if (AbstractDungeon.player.hasRelic(WalkingCane.ID)) {
			AbstractDungeon.player.getRelic(WalkingCane.ID).flash();
			return;
		}
		if (__instance.amount == 0) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(__instance.owner, __instance.owner, WeakPower.POWER_ID));
		} else {
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(__instance.owner, __instance.owner, WeakPower.POWER_ID, 1));
		}
	}
}
