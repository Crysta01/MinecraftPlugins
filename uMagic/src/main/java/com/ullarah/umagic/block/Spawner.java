package com.ullarah.umagic.block;

import com.ullarah.umagic.MagicFunctions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;

public class Spawner extends MagicFunctions {

    public Spawner(Block block, BlockFace face) {

        super(false);

        CreatureSpawner originalSpawner = (CreatureSpawner) block.getState();
        EntityType originalEntityType = originalSpawner.getSpawnedType();
        int originalEntityDelay = originalSpawner.getDelay();

        Location blockLocation = block.getLocation();
        int blockX = blockLocation.getBlockX();
        int blockY = blockLocation.getBlockY();
        int blockZ = blockLocation.getBlockZ();

        Block newSpawn = block.getWorld().getBlockAt(blockLocation);

        switch (face) {

            case NORTH:
                newSpawn = block.getWorld().getBlockAt(blockX, blockY, blockZ + 1);
                break;

            case EAST:
                newSpawn = block.getWorld().getBlockAt(blockX - 1, blockY, blockZ);
                break;

            case SOUTH:
                newSpawn = block.getWorld().getBlockAt(blockX, blockY, blockZ - 1);
                break;

            case WEST:
                newSpawn = block.getWorld().getBlockAt(blockX + 1, blockY, blockZ);
                break;

            case UP:
                newSpawn = block.getWorld().getBlockAt(blockX, blockY - 1, blockZ);
                break;

            case DOWN:
                newSpawn = block.getWorld().getBlockAt(blockX, blockY + 1, blockZ);
                break;

        }

        if (newSpawn.getType().equals(Material.AIR)) {

            block.setType(Material.AIR);
            newSpawn.setType(Material.MOB_SPAWNER);

            BlockState blockState = newSpawn.getState();

            ((CreatureSpawner) blockState).setSpawnedType(originalEntityType);
            ((CreatureSpawner) blockState).setDelay(originalEntityDelay);

            blockState.update(true);

        }

    }

}
