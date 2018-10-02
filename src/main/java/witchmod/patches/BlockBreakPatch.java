package witchmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import witchmod.powers.AbstractWitchPower;

@SpirePatch(
		cls="com.megacrit.cardcrawl.core.AbstractCreature",
		method="brokeBlock",
		paramtypez={}
)
public class BlockBreakPatch {
	public static void Prefix(AbstractCreature __instance) {
        for (AbstractPower p : __instance.powers) {
        	if (p instanceof AbstractWitchPower) {
        		((AbstractWitchPower) p).onBlockBreak();
        	}
        }
	}
}
