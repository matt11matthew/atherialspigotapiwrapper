package com.gmail.stefvanschiedev.customblocks.block;

import com.gmail.stefvanschiedev.customblocks.manager.CustomBlockManager;
import com.gmail.stefvanschiedev.customblocks.util.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.net.URL;

public class CustomBlock {

	/* armorstands formatted like this:
	 * 
	 * bottom north west
	 * bottom north east
	 * bottom south east
	 * bottom south west
	 * top north west
	 * top north east
	 * top south east
	 * top south west
	 */
	ArmorStand[] armorStands;
	Location location;
	
	ItemStack[] heads;
	
	public CustomBlock(ItemStack[] heads) {
		if (heads.length != 8)
			throw new IllegalArgumentException("Amount of heads not allowed");
		
		armorStands = new ArmorStand[8];
		this.heads = heads;
		
		CustomBlockManager.getInstance().addCustomBlock(this);
	}
	
	public CustomBlock(ItemStack head) {
		this(new ItemStack[] {head, head, head, head, head, head, head, head});
	}
	
	public CustomBlock(URL[] heads) {
		this(new ItemStack[] {Utils.getSkull(heads[0].toString()),
				Utils.getSkull(heads[1].toString()),
				Utils.getSkull(heads[2].toString()),
				Utils.getSkull(heads[3].toString()),
				Utils.getSkull(heads[4].toString()),
				Utils.getSkull(heads[5].toString()),
				Utils.getSkull(heads[6].toString()),
				Utils.getSkull(heads[7].toString())});
	}
	
	public CustomBlock(URL head) {
		this(new URL[] {head, head, head, head, head, head, head, head});
	}
	
	public void destroy() {
		CustomBlockManager.getInstance().removeCustomBlock(this);
		
		location.getBlock().setType(Material.AIR);
		
		for (ArmorStand armorStand : armorStands)
			armorStand.setHealth(0);
	}
	
	public ArmorStand[] getArmorStands() {
		return armorStands;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void place(Location loc) {
		//center location
		loc.setX(loc.getBlockX() + .5);
		loc.setY(loc.getBlockY() - 1.5);
		loc.setZ(loc.getBlockZ() + .5);
		loc.setYaw(180);
		loc.setPitch(0);
		
		location = loc.clone().add(0, 1.5, 0);
		
		//bottom
		ArmorStand bnw = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(-.25, 0, -.25), EntityType.ARMOR_STAND);
		bnw.setHelmet(heads[0]);
		bnw.setGravity(false);
		bnw.setVisible(false);
		armorStands[0] = bnw;
		
		ArmorStand bne = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(.25, 0, -.25), EntityType.ARMOR_STAND);
		bne.setHelmet(heads[1]);
		bne.setGravity(false);
		bne.setVisible(false);
		armorStands[1] = bne;
		
		ArmorStand bse = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(.25, 0, .25), EntityType.ARMOR_STAND);
		bse.setHelmet(heads[2]);
		bse.setGravity(false);
		bse.setVisible(false);
		armorStands[2] = bse;
		
		ArmorStand bsw = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(-.25, 0, .25), EntityType.ARMOR_STAND);
		bsw.setHelmet(heads[3]);
		bsw.setGravity(false);
		bsw.setVisible(false);
		armorStands[3] = bsw;
		
		//top
		ArmorStand tnw = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(-.25, .5, -.25), EntityType.ARMOR_STAND);
		tnw.setHelmet(heads[4]);
		tnw.setGravity(false);
		tnw.setVisible(false);
		armorStands[4] = tnw;
		
		ArmorStand tne = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(.25, .5, -.25), EntityType.ARMOR_STAND);
		tne.setHelmet(heads[5]);
		tne.setGravity(false);
		tne.setVisible(false);
		armorStands[5] = tne;
		
		ArmorStand tse = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(.25, .5, .25), EntityType.ARMOR_STAND);
		tse.setHelmet(heads[6]);
		tse.setGravity(false);
		tse.setVisible(false);
		armorStands[6] = tse;
		
		ArmorStand tsw = (ArmorStand) loc.getWorld().spawnEntity(loc.clone().add(-.25, .5, .25), EntityType.ARMOR_STAND);
		tsw.setHelmet(heads[7]);
		tsw.setGravity(false);
		tsw.setVisible(false);
		armorStands[7] = tsw;
	}
	
	public void setUnderlyingBlock(Material material) {
		location.getBlock().setType(material);
	}
}