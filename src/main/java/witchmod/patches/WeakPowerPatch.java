package witchmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.WeakPower;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import witchmod.relics.WalkingCane;

import java.util.ArrayList;

@SpirePatch(cls="com.megacrit.cardcrawl.powers.WeakPower", method="atEndOfRound")
public class WeakPowerPatch {

	@SpireInsertPatch(locator=Locator.class)
	public static SpireReturn<Void> Insert(WeakPower __instance) {
		if (__instance.owner != AbstractDungeon.player && AbstractDungeon.player.hasRelic(WalkingCane.ID)) {
			AbstractDungeon.player.getRelic(WalkingCane.ID).flash();
			return SpireReturn.Return(null);
		}
		return SpireReturn.Continue();
	}

	private static class Locator extends SpireInsertLocator {
		public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
			Matcher amountCheckMatcher = new Matcher.FieldAccessMatcher(WeakPower.class, "amount");
			return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), amountCheckMatcher);
		}
	}
}