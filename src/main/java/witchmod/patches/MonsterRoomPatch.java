package witchmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


@SpirePatch(cls="com.megacrit.cardcrawl.rooms.MonsterRoom", method="getCardRarity")
public class MonsterRoomPatch {
	@SpireInsertPatch(rloc=4)
	public static void Insert(Object __obj_instance, int rareRate) {
		if (AbstractDungeon.player.hasPower("AthamesOffering") && AbstractDungeon.player.getPower("AthamesOffering").amount > 0){
			rareRate *= AbstractDungeon.player.getPower("AthamesOffering").amount;
		}
	}
}
