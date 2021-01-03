package witchmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.ReflectionHacks;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import javassist.expr.MethodCall;
import witchmod.relics.WalkingCane;

import java.util.ArrayList;

@SpirePatch(cls="com.megacrit.cardcrawl.powers.VulnerablePower", method="atEndOfRound")
public class VulnerablePowerPatch {

	@SpireInsertPatch(locator=Locator.class)
	public static SpireReturn<Void> Insert(VulnerablePower __instance) {
		if (__instance.owner != AbstractDungeon.player && AbstractDungeon.player.hasRelic(WalkingCane.ID)) {
			AbstractDungeon.player.getRelic(WalkingCane.ID).flash();
			return SpireReturn.Return(null);
		}
		return SpireReturn.Continue();
	}

	private static class Locator extends SpireInsertLocator {
		public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
			Matcher amountCheckMatcher = new Matcher.FieldAccessMatcher(VulnerablePower.class, "amount");
			return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), amountCheckMatcher);
		}
	}
}
