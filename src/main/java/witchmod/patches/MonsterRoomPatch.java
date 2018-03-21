package witchmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


@SpirePatch(cls="com.megacrit.cardcrawl.rooms.MonsterRoom", method="getCardRarity")
public class MonsterRoomPatch {
	@SpireInsertPatch(loc=43, localvars={"rareRate"})
	public static void Insert(Object __obj_instance, int rareRate) {
		if (AbstractDungeon.player.hasPower("AthamesOffering")){
			rareRate *= AbstractDungeon.player.getPower("AthamesOffering").amount;
		}
	}
}
